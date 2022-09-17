package com.sl.shortLink.listener;

import com.sl.shortLink.config.LocalQueueStore;
import com.sl.shortLink.constants.SlConstants;
import com.sl.shortLink.events.ShortKeyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 分类名称
 *
 * @author wangzhiyong
 * @module 炫云-云模型
 * @date 2022年09月13日 下午3:03
 */
@Slf4j
@Component
@Async("taskExecutor")
public class CustomEventListener {

    private final ReentrantLock lock = new ReentrantLock(true);

    /**
     * 添加短链接事件监听
     * @author wangzhiyong
     * @date 2022/9/13 下午3:07
     * @param event
     */
    @EventListener(ShortKeyEvent.class)
    public void generateShortKeys(ShortKeyEvent event) {
        lock.lock();
        try {
            log.info("开始生成并往队列中添加短链接");
            Integer count = event.getCount();
            int size = LocalQueueStore.getSize();
            if (ObjectUtils.isEmpty(count) || count <= 0) {
                log.error("CustomEventListener-generateShortKeys is error,count={}",count);
                return;
            }
            if (size > SlConstants.ADD_QUEUE_ELEMENT_THRESHOLD) {
                log.info("当前元素个数:{}无需重复添加元素",size);
                return;
            }
            StopWatch watch = new StopWatch();
            watch.start();
            LocalQueueStore.addShortKeys(count);
            watch.stop();
            log.info("{}个短链接生成完毕耗时：({})毫秒", count, watch.getTotalTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成并往队列中添加短链接出现异常");
        }finally {
            lock.unlock();
        }

    }
}
