package com.yu.config.sync;

import com.yu.model.PayReqItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import java.util.concurrent.Future;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/12 10:22 
 */
@Component
public class Sync {
    Logger logger = LoggerFactory.getLogger(Sync.class);

    /**
     * 异步无返回值
     */
    @Async
    public void sync() {
        logger.info("异步线程执行Sync：thread name:" + Thread.currentThread().getName() + "thread id:" + Thread.currentThread().getId());

    }

    /**
     * 异步有返回值
     * @return
     */
    @Async
    public Future<PayReqItem> asyncMethodWithReturnType() {
        logger.info("method asyncMethodWithReturnType Thread name:" + Thread.currentThread().getName() + "Thread id:" +Thread.currentThread().getId() );
        PayReqItem payReqItem = new PayReqItem();
        payReqItem.setOrderNo("3434343434");
        payReqItem.setAmount("111.22");
        return new AsyncResult<PayReqItem>(payReqItem);
    }
}
