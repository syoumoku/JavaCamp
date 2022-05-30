package java0.conc0303.homework.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Method 3: CountDownLatch
 */
public class CountLatch extends ComputeBase{
    @Override
    public int doAsyncCompute() throws ExecutionException, InterruptedException {
        System.out.println("方法：CountDownLatch");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            result.set(sum());
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        return result.get();
    }
}
