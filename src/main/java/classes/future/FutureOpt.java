// Copyright (C) 2017 Meituan
// All rights reserved
package classes.future;

import Utils.BasicUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author dengtianzhi
 * 解决的问题：有一组产品ID，模拟从远程服务获取这些产品ID对应的全部产品价格
 * 1、Stream
 * 2、ParallelStream
 * 3、Stream + CompletableFuture
 * @version 1.0
 * @created 2017/7/2.
 */
public class FutureOpt {

    public static void main(String... args) {

        List<Integer> productIdList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

        double nanoTimeBase = 1000000000.0;

//        BasicUtils.println("测试stream，无future");
//        Long t1 = System.nanoTime();
//        productIdList.stream().
//                map(productId -> "产品ID " + productId + " 价格为：" + getPrice(productId))
//                .forEach(BasicUtils::println);
//        Long t2 = System.nanoTime();
//        BasicUtils.println("测试stream，无future，耗时 " + (t2 - t1) / nanoTimeBase);
//        BasicUtils.enter();

        BasicUtils.println("测试parallelStream，无future");
        Long t3 = System.nanoTime();
        productIdList.parallelStream().
                map(productId -> "产品ID " + productId + " 价格为：" + getPrice(productId))
                .forEach(BasicUtils::println);
        Long t4 = System.nanoTime();
        BasicUtils.println("测试parallelStream，无future，耗时 " + (t4 - t3) / nanoTimeBase);
        BasicUtils.enter();

        BasicUtils.println("测试stream，completableFuture二阶段，定制执行器");
        Long t5 = System.nanoTime();
        List<CompletableFuture<String>> completableFutureList = productIdList.stream().
                map(productId ->
                        CompletableFuture.supplyAsync(
                                () -> "产品ID " + productId + " 价格为：" + getPrice(productId), executor))
                .collect(Collectors.toList());
        completableFutureList.stream().map(CompletableFuture::join).forEach(BasicUtils::println);
        Long t6 = System.nanoTime();
        BasicUtils.println("测试stream，completableFuture二阶段，定制执行器，耗时 " + (t6 - t5) / nanoTimeBase);
        BasicUtils.enter();
    }

    private static Integer getPrice(Integer productId) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Logger.getGlobal().info(e.getMessage());
        }

        return ThreadLocalRandom.current().nextInt(100) * productId;
    }

    private static Executor executor = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });
}
