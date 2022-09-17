package com.sl.shortLink.controller;

import com.sl.shortLink.constants.CacheConstant;
import com.sl.shortLink.service.ShortLinkService;
import com.sl.shortLink.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 短链接访问控制器
 *
 * @author wangzhiyong
 * @date 2022年09月13日 上午10:04
 */
@Controller
public class ShortLinkAccessController {

    @Resource
    private ShortLinkService shortLinkService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 短链接访问跳转
     * @author wangzhiyong
     * @date 2022/9/13 上午10:42
     * @param key
     * @param response
     */
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
}
