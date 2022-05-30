package java0.conc0303.homework.impl;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Method2 : ThreadJoin
 */
public class ThreadJoin extends ComputeBase {


    @Override
    public int doAsyncCompute() throws ExecutionException, InterruptedException {
        System.out.println("方式： Thread Join");
        AtomicInteger result = new AtomicInteger();
        Thread doComputation = new Thread(() -> {
            result.set(sum());
        });

        doComputation.start();
        //join将主线程挂起，doComputation完成以后会notify通知主线程继续执行，所以是异步阻塞模式
        doComputation.join();

        return result.get();
    }
}