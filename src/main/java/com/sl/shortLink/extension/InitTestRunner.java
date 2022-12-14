package com.sl.shortLink.extension;

import com.sl.shortLink.dto.params.GenerateShortLinkParams;
import com.sl.shortLink.service.ShortLinkService;
import com.sl.shortLink.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangzhiyong
 * @date 2021年12月18日 上午10:56
 */
@Component
@Slf4j
public class InitTestRunner implements ApplicationRunner {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean flag = false;
        if (flag) {
        }
    }
}
