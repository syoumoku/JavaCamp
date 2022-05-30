package java0.conc0303.homework;

import java0.conc0303.homework.constants.ComputeMethod;
import java0.conc0303.homework.impl.*;

import java.util.HashMap;


public class ComputeMethodFactory {
    private HashMap<ComputeMethod, ICompute> methodMap;

    public ComputeMethodFactory() {
        HashMap<ComputeMethod, ICompute> map = new HashMap<>();
        map.put(ComputeMethod.FUTURETASK, new Futuretask());
        map.put(ComputeMethod.THREADJOIN, new ThreadJoin());
        map.put(ComputeMethod.COUNTDOWNLATCH, new CountLatch());
        map.put(ComputeMethod.WAITANDNOTIFY, new WaitNotify());
        map.put(ComputeMethod.CYCLICBARRIER, new ByCyclicBarrier());
        map.put(ComputeMethod.COMPLETABLEFUTURE, new ByCompletableFuture());
        this.methodMap = map;
    }


    /**
     *    方法1 Futuretask
     *    方法2 Thread.join()
     *    方法3 CountDownLatch
     *    方法4 Wait and Notify
     *
     */
    public ICompute getMethod(ComputeMethod method) {
        return methodMap.get(method);
    }
}
