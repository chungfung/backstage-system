package com.system.common.config.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.system.common.utils.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池配置,可通过配置文件修改线程池参数
 * @author system
 **/
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程池大小
     */
    @Value("${thread.corePoolSize:50}")
    private int corePoolSize;

    /**
     * 最大可创建的线程数
     */
    @Value("${thread.maxPoolSize:200}")
    private int maxPoolSize;

    /**
     * 队列最大长度
     */
    @Value("${thread.queueCapacity:2000}")
    private int queueCapacity;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    @Value("${thread.keepAliveSeconds:300}")
    private int keepAliveSeconds;

    @Bean(name = "threadPoolExecutor")
    public ThreadPoolExecutor ThreadPoolExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("自定义线程池-%d").build();
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(this.corePoolSize, this.maxPoolSize,
                this. keepAliveSeconds, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueCapacity),
                    namedThreadFactory, new CustomPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("预定执行线程池-%d").daemon(true).build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }
}
