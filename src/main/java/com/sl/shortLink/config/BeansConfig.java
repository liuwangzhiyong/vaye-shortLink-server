package com.sl.shortLink.config;

import com.sl.shortLink.utils.SlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sl.shortLink.utils.RedisUtil;
/**
 * 自定义bean配置
 *
 * @author wangzhiyong
 * @date 2022年09月13日 上午10:46
 */
@Configuration
@Slf4j
public class BeansConfig {

    @Bean
    public RedisUtil  redisUtil(){
        return new RedisUtil();
    }
}
