package com.system.framework.manager;

import com.system.common.constant.ConfigConstants;
import com.system.common.utils.Threads;
import com.system.common.utils.spring.SpringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 * @author system
 */
public class AsyncManager {

    /**
     * 单例模式
     */
    private static AsyncManager me = new AsyncManager();

    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService scheduledExecutorService
            = SpringUtils.getBean(ConfigConstants.SCHEDULED_EXECUTOR_SERVICE);

    /**
     * 自定义线程池
     */
    private ThreadPoolExecutor threadPoolTaskExecutor
            = SpringUtils.getBean(ConfigConstants.THREAD_POOL_EXECUTOR);

    public static AsyncManager getInstance() {
        return me;
    }

    /**
     * 执行任务
     * @param task 任务
     */
    public void executeScheduleTask(TimerTask task) {
        scheduledExecutorService.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 执行任务
     * @param callable
     */
    public void executeTask(Callable callable) {
        threadPoolTaskExecutor.submit(callable);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(scheduledExecutorService);
        Threads.shutdownAndAwaitTermination(threadPoolTaskExecutor);
    }
}
