package com.sl.shortLink.extension;

import com.sl.shortLink.config.LocalQueueStore;
import com.sl.shortLink.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 * @date 2021年12月18日 上午10:56
 */
@Component
@Slf4j
@Order(Integer.MIN_VALUE + 1)
public class InitSourceRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LocalQueueStore.init();
    }
}
