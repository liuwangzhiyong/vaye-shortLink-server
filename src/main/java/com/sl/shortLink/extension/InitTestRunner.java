package com.sl.shortLink.extension;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 * @date 2021年12月18日 上午10:56
 */
@Component
@Slf4j
public class InitTestRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean flag = true;
        if (flag) {
        }
    }
}
