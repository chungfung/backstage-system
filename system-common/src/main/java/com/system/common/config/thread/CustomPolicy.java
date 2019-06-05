package com.system.common.config.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 自定义线程抛弃策略
 * @Author 丰涌
 * @Date 2018/11/26 19:07
 * @Version 1.0
 */
public class CustomPolicy implements RejectedExecutionHandler {

    private Logger logger = LoggerFactory.getLogger(CustomPolicy.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.info(r.toString());
        // TODO
    }
}
