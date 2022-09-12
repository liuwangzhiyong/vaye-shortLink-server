package com.sl.shortLink.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

/**
 *
 * @author wangzhiyong
 * @date 2022/3/14 下午2:37
 * @param
 * @return null
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig{

    private final int SCHEDULER_POOL_SIZE = 30;

    private final int CORE_POOL_SIZE = 10;

    private final int MAX_POOL_SIZE = 50;

    private final int QUEUE_CAPACITY = 1024;

    private final int KEEP_ALIVE_SECONDS = 60*10;


    @Bean
    public Executor taskExecutor(){
        log.info("初始化taskExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 5);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setThreadNamePrefix("asyncExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean
    public TaskScheduler taskScheduler(){
        log.info("初始化taskScheduler");
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(SCHEDULER_POOL_SIZE);
        scheduler.setThreadNamePrefix("scheduled-task-pool-");
        scheduler.initialize();
        return scheduler;
    }
}
