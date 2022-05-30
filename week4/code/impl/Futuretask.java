package java0.conc0303.homework.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;

/**
 * Method1: Futuretask
 */
public class Futuretask extends ComputeBase{

    @Override
    public int doAsyncCompute() throws ExecutionException, InterruptedException {
        System.out.println("方式：FutureTask");
        FutureTask<Integer> futureTask = new FutureTask<>(ComputeBase::sum);
        ForkJoinPool.commonPool().submit(futureTask);
        return futureTask.get(); //这是得到的返回值
    }
}
