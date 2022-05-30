package java0.conc0303.homework.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Method4 : Wait and Notify
 */
public class WaitNotify extends ComputeBase{
    static Object oo = new Object();
    @Override
    public int doAsyncCompute() throws ExecutionException, InterruptedException {
        System.out.println("方式： Wait and Notify");
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            synchronized(oo) {
                result.set(sum());
                oo.notify();
            }
        }).start();
        synchronized(oo) {
            oo.wait();
        }
        return result.get();
    }
}
