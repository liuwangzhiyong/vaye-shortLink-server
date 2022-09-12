package com.sl.shortLink.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.shortLink.intercepter.GlobalInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author wangzhiyong
 * @date 2021年12月22日 上午11:15
 */
@Configuration
@Slf4j
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("init GlobalInterceptor");
        GlobalInterceptor globalInterceptor = new GlobalInterceptor(objectMapper);
        registry.addInterceptor(globalInterceptor).addPathPatterns("/**");
    }
}
