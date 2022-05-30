package java0.conc0303.homework.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ByCompletableFuture extends ComputeBase{
    @Override
    public int doAsyncCompute() throws ExecutionException, InterruptedException {

        return CompletableFuture.supplyAsync(ComputeBase::sum).get();
    }
}
