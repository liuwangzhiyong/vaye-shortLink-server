package com.sl.shortLink.controller;

import static com.sl.shortLink.common.ResultBuilder.*;
import com.sl.shortLink.common.ResultModel;
import com.sl.shortLink.config.LocalQueueStore;
import com.sl.shortLink.dto.params.GenerateShortLinkParams;
import com.sl.shortLink.service.ShortLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试控制器
 * @author wangzhiyong
 * @date 2022年09月13日 下午4:41
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestRestController {

    private final ExecutorService executorService = Executors.newFixedThreadPool(20);

    private final Semaphore semaphore = new Semaphore(1);

    @Autowired
    private ShortLinkService shortLinkService;

    /**
     * 获取队列元素数量
    * @author wangzhiyong
     * @date 2022/9/14 上午10:43
     * @return com.sl.shortLink.common.ResultModel
     */
    @RequestMapping(value = "/getQueueSize", method = RequestMethod.GET)
    public ResultModel getQueueSize() {
        return buildSuccessData(LocalQueueStore.getSize());
    }

    /**
     * 生成短链接并发测试
     * @author wangzhiyong
     * @date 2022/9/14 上午10:07
     */
    @RequestMapping(value = "/concurrencyTest", method = RequestMethod.GET)
    public ResultModel concurrencyTest() {
        for (int i = 0; i < 30; i++) {
            executorService.execute(()->{
                log.info("threadId={}",Thread.currentThread().getId());
                GenerateShortLinkParams generateShortLinkParams = new GenerateShortLinkParams();
                generateShortLinkParams.setUrl("https://tietu.znzmo.com/tietu/733557681.html");
                shortLinkService.generate("test",generateShortLinkParams);
            });
        }
        return buildSuccess();
    }
}
