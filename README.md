# vaye-shortLink-server

## 项目背景

### 什么是短链接？

短链接就是将很长的一个链接转换为非常短的链接，我们举个例子，比如我们现在有一个很长的网址：<https://mvnrepository.com/artifact/org.sonarsource.api.plugin/sonar-plugin-api/9.11.0.290>经过转换后生成一个短链接：<https://mvnrepository.com/an1s>。我们访问短链接和访问长链接效果是一样的，访问短链接的时候能够跳转到长链接上面。

### 短链接使用场景

- 向客户发送带链接的短信
- 二维码内嵌链接，实现扫码跳转
- 将自己平台的内容分享到微信、QQ等社交平台

## 项目概述

vaye-shortLink-server是一个短链接服务平台，其核心功能就是将长链接转换为短链接，同时还实现当用户点击短链接的时候，实现自动跳转到原来的长链接。

## 核心功能

- 将长链接变为短链接，当然是越短越好
- 用户点击短链接的时候，实现自动跳转到原来的长链接

## 项目核心功能实现

### 长链接转为短链接

我们主要是通过生成唯一的ID，然后再把ID转换成唯一的字符串key，使key与长链接对应。生成完成后我们把key与长链接存到数据库中，为了提高性能，我们在生成的时候除了将其保存到Mysql数据库中，还要存Redis缓存中一份，这样在访问的时候提高访问性能。接下来我们就来说一下长链接转短链接涉及到的关键技术点。

**1、怎么生成唯一ID？**

- 单机情况下我们可以采用数据库的ID自增的方式来实现ID唯一
- 通过Redis的incr自增的方式生成唯一ID，不过要考虑Redis服务宕机重启有可能之前的ID丢失情况（Redis做成高可用或者其他方式都行）
- 通过Twitter的雪花算法生成唯一ID（本项目采用的方式）

**2、如果把唯一ID转换为字符串key？**

- 使用Hashids生成唯一的字符key（该方式有个局限性，要转换的id不能大于9007199254740992L）

  ```java
  private static final Hashids hashids = new Hashids(SlConstants.HASHIDS_SALT,4);
  
      public static final long MAX_NUMBER = 9007199254740992L;
  
      /**
       * 获取短链接（本方法适用于id < 9007199254740992L）
       * PS：如果传入的参数id > 9007199254740992L，建议使用另外一个获取方法：{@link BaseUtils#getShortKey(long)}
       * 虽然本方法支持大于9007199254740992L，但是生成的短链接字符串位数长度位17左右
       * @author wangzhiyong
       * @date 2022/9/12 下午5:12
       * @param id
       * @return java.lang.String
       */
      public static String getShortKey(long id){
          if (id > MAX_NUMBER) {
              long[] ids = new long[3];
              ids[0] = id / MAX_NUMBER;
              ids[1] = id % MAX_NUMBER;
              ids[2] = ThreadLocalRandom.current().nextLong(1000);
              return hashids.encode(ids);
          } else {
              return hashids.encode(id);
          }
      }
  ```

- 将唯一ID由十进制转换成62进制（该方式请参照源码）

3、生成id以及ID转换为key需要花费时间，我们这里可以考虑用一个容器，在项目启动的时候初始化一部分数据到里面，生成短链接的时候直接从容器里面取，当容器里面的元素数量少于1/4的时候，我们开启一个线程往里面补充元素。本项目采用的容器为：`ArrayBlockingQueue`,具体做法请查阅源码

### 短链接访问

```java
@GetMapping("/{key}")
    public void lookup(@PathVariable String key, HttpServletResponse response) throws Exception{
        String originalUrl;
        originalUrl = redisUtil.get(String.format(CacheConstant.SHORT_KEY_PREFIX, key), String.class);
        if (!StringUtils.isEmpty(originalUrl)) {
            response.sendRedirect(originalUrl);
        } else {
            originalUrl = shortLinkService.lookup(key);
            if (StringUtils.isBlank(originalUrl)) {
                // 如果没有找到长链接，跳转到我们的网站首页站或者定制一个 404 页面
                response.sendRedirect("https://www.baidu.com");
            }
            redisUtil.set(String.format(CacheConstant.SHORT_KEY_PREFIX,key),originalUrl,1, TimeUnit.HOURS);
            response.sendRedirect(originalUrl);
        }
    }
```

