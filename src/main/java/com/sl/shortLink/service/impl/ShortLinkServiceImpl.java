package com.sl.shortLink.service.impl;

import com.sl.shortLink.common.ResultBuilder;
import com.sl.shortLink.common.ResultModel;
import com.sl.shortLink.config.LocalQueueStore;
import com.sl.shortLink.constants.CacheConstant;
import com.sl.shortLink.constants.SlConstants;
import com.sl.shortLink.dao.ShortLinkDao;
import com.sl.shortLink.dto.params.BatchGenerateShortLinkParams;
import com.sl.shortLink.dto.params.GenerateShortLinkParams;
import com.sl.shortLink.entity.ShortLink;
import com.sl.shortLink.events.ShortKeyEvent;
import com.sl.shortLink.service.ShortLinkService;
import com.sl.shortLink.utils.BaseUtils;
import com.sl.shortLink.utils.RedisUtil;
import com.sl.shortLink.utils.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * (ShortLink)表服务实现类
 *
 * @author makejava
 * @since 2022-09-12 16:17:10
 */
@Slf4j
@Service("shortLinkService")
public class ShortLinkServiceImpl implements ShortLinkService, ApplicationContextAware {

    @Resource
    private ShortLinkDao shortLinkDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${shortLink.domain}")
    private String domain;

    private ApplicationContext applicationContext;

    private final AtomicInteger count = new AtomicInteger(LocalQueueStore.getSize());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public ResultModel generate(String biz, GenerateShortLinkParams params) {
        Pair<Long, String> pair = LocalQueueStore.getShortKey();
        log.info("pair={}",pair);
        long primaryKey;
        String shortKey;
        if (ObjectUtils.isEmpty(pair)) {
            primaryKey = SnowflakeIdUtil.nextId();
            shortKey = BaseUtils.getShortKey(primaryKey);
        } else {
            primaryKey = pair.getLeft();
            shortKey = pair.getRight();
            int i = count.decrementAndGet();
            if (i <= SlConstants.ADD_QUEUE_ELEMENT_THRESHOLD) {
                applicationContext.publishEvent(new ShortKeyEvent(SlConstants.REPLENISH_QUEUE_ELEMENT_COUNT));
                count.set(LocalQueueStore.getSize());
            }
        }
        String originalUrl = params.getUrl();
        ShortLink shortLink = new ShortLink();
        shortLink.setId(primaryKey);
        shortLink.setShortKey(shortKey);
        shortLink.setOriginalUrl(originalUrl);
        shortLink.setBiz(biz);
        shortLink.setGmtCreated(new Date());
        shortLinkDao.insert(shortLink);
        redisUtil.set(String.format(CacheConstant.SHORT_KEY_PREFIX,shortKey),originalUrl,1, TimeUnit.HOURS);
        return ResultBuilder.buildSuccessData(domain + shortKey);
    }

    @Override
    public String lookup(String key) {
        return shortLinkDao.selectByShortKey(key);
    }

}
