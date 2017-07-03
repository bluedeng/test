// Copyright (C) 2017 Meituan
// All rights reserved
package classes.future;

import Utils.FutureUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2017/1/8 下午3:20
 */
public class JavaFuture {
    public static void main(String[] args) {

        // Callable实现的是有返回值的并发，而Runable实现的是没有返回值的情况
        Callable<Integer> callable = () -> ThreadLocalRandom.current().nextInt(100);

        // one task future.run
        FutureTask<Integer> future = new FutureTask<>(callable);
        future.run();

        FutureUtils.printFutureResult(future);


        /*
        // one task executorService.submit
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(callable);

        FutureUtils.printFutureResult(future);
        threadPool.shutdownNow();
        */

        /*
        // multi task CompletionService.submit
        // 这种方式的返回值是无序的，CompletionService里面元素按完成的先后顺序排列
        // 如果希望结果的返回有序，可以使用上面第二种方案，将submit的返回值放入一个List<Future>中，保持结果的有序
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
        for (int i = 0; i < 5; i++) {
            completionService.submit(callable);
        }

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdownNow();
        */
    }
}