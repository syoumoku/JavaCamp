package java0.conc0303.homework;

import java0.conc0303.homework.constants.ComputeMethod;

import java.util.concurrent.ExecutionException;

public class ComputeMain{

    private static ICompute computer;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        computer = new ComputeMethodFactory().getMethod(ComputeMethod.COMPLETABLEFUTURE);

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        int result = computer.doAsyncCompute();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
