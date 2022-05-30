package java0.conc0303.homework.impl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class ByCyclicBarrier extends ComputeBase{
    @Override
    public int doAsyncCompute() throws ExecutionException, InterruptedException {
        System.out.println("方法： CyclicBarrier");
        AtomicInteger result = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(1, () -> {
            System.out.println("Barrier的各子线程执行完以后，最后的子线程"+Thread.currentThread().getName() +"回调此函数");
        });

        new Thread(() -> {
            result.set(sum());
            try {
                barrier.await();
                System.out.println("然后子线程从await处继续执行");
                synchronized(this) {
                    System.out.println("唤醒主线程" + this.toString());
                    notify();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        synchronized(this) {
            System.out.println("阻塞主线程" + this.toString());
            wait();
        }
        System.out.println("主线程被唤醒，返回计算结果");
        return result.get();
    }
}
