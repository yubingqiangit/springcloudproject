package com.yu.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步配置类
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/12 10:20 
 */
@EnableAsync
@Configuration
@ComponentScan("com.yu.config.sync")
public class SyncConfig {

    /**
     * 配置线程
     * @return
     */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);        // 设置核心线程数
        executor.setMaxPoolSize(10);        // 设置最大线程数
        executor.setQueueCapacity(20);      // 设置队列容量
        executor.setKeepAliveSeconds(60);   // 设置线程活跃时间（秒）
        executor.setThreadNamePrefix("myThread");  // 设置默认线程名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 设置拒绝策略
        executor.setWaitForTasksToCompleteOnShutdown(true); // 等待所有任务结束后再关闭线程池
        return executor;
    }

}
