package java0.conc0303.homework.impl;

import java0.conc0303.homework.ICompute;

public abstract class ComputeBase implements ICompute {
    protected static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
