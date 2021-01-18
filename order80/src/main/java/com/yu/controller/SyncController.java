package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.config.SyncConfig;
import com.yu.config.sync.Sync;
import com.yu.model.PayReqItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步线程测试
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/12 10:48 
 */
@RestController
public class SyncController {

    static final Logger logger = LoggerFactory.getLogger(SyncController.class);

    /**
     * 异步没有返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/sync/test")
    public void sync() throws ExecutionException, InterruptedException {
        logger.info("sync线程执行：thread name:" + Thread.currentThread().getName() + "thread id:" + Thread.currentThread().getId());
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SyncConfig.class);
        Sync bean = ac.getBean(Sync.class);
        bean.sync();
        System.out.println("异步函数执行完成");
    }

    /**
     * 异步接收返回值
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/sync/callback")
    public String syncCallBack() throws ExecutionException, InterruptedException {
        logger.info("syncCallBack线程执行：thread name:" + Thread.currentThread().getName() + "thread id:" + Thread.currentThread().getId());
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SyncConfig.class);
        Sync bean = ac.getBean(Sync.class);
        Future<PayReqItem> future = bean.asyncMethodWithReturnType();
        while (true) {                              //这里使用了循环判断，等待获取结果信息
            if (future.isDone()) {
                //判断是否执行完毕,执行完成跳出循环
                System.out.println("syncCallBack Result from asynchronous process:: - " + JSON.toJSONString(future.get()));
                break;
            }
        }
        return JSON.toJSONString(future.get());
    }
}
