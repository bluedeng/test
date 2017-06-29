// Copyright (C) 2017 Meituan
// All rights reserved
package classes.future;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2017/6/27.
 */
public class JavaCompletableFuture {

    public static void main(String[] args) {

        CompletableFuture future;
        CompletableFuture<String> future1;
        CompletableFuture<String> future2;

        //complete
        future = new CompletableFuture<String>();
        future.complete("complete");
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //runAsync runnable
        future = CompletableFuture.runAsync(() -> println("runAsync!"));
        FutureUtils.executeCompletableFuture(future);
        enter();

        //supplyAsync
        future = CompletableFuture.supplyAsync(() -> "supplyAsync!");
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //supplyAsync, thenAccept
        future = CompletableFuture.supplyAsync(() -> "supplyAsync").thenAccept(str -> println(str + " " + "thenAccept!"));
        FutureUtils.executeCompletableFuture(future);
        enter();

        //supplyAsync, thenApply
        future = CompletableFuture.supplyAsync(() -> 1).thenApply(n -> n * 2).thenApply(n -> n * Math.PI);
        System.out.print("supplyAsync thenApply ");
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //exceptionally
        //IndexOutOfBoundsException
        future = CompletableFuture.supplyAsync(() -> Collections.emptyList().get(0))
                .exceptionally(Throwable::getMessage);
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //exceptionally
        future = CompletableFuture.supplyAsync(() -> "there is no exception, test exceptionally")
                .exceptionally(Throwable::getMessage);
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //handle
        //IndexOutOfBoundsException
        future = CompletableFuture.supplyAsync(() -> Collections.emptyList().get(0))
                .handle((ok, ex) -> {
            if (ok != null) {
                return ok;
            } else {
                return "there is a bug: " + ex.getMessage();
            }});
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //handle
        future = CompletableFuture.supplyAsync(() -> "there is no exception, test handle")
                .handle((ok, ex) -> {
                    if (ok != null) {
                        return ok;
                    } else {
                        return "there is a bug: " + ex.getMessage();
                    }});
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //combine two CompletableFuture
        future1 = CompletableFuture.supplyAsync(() -> "hello, ");
        future2 = CompletableFuture.supplyAsync(() -> "testing combine");
        future1 = future1.thenCombine(future2, (a, b) -> a + "we are " + b + " operator");
        FutureUtils.printlnCompletableFutureResult(future1);
        enter();

        println("compare thenApply and thenCompose");
        //thenApply just like map
        future = CompletableFuture.supplyAsync(() -> Arrays.asList(1, 2, 3)).thenApply(List::size);
        FutureUtils.printlnCompletableFutureResult(future);
        //thenCompose invoke function which must return a CompletableFuture
        future = CompletableFuture.supplyAsync(() -> Arrays.asList(1, 2, 3)).thenCompose(l -> CompletableFuture.supplyAsync(() -> l));
        FutureUtils.printlnCompletableFutureResult(future);
        enter();

        //thenAcceptBoth runAfterBoth
        CompletableFuture.supplyAsync(() -> "we test ")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> "thenAcceptBoth"), (a, b) -> println(a + b + " success"));
        print("we test runAfterBoth");
        CompletableFuture.supplyAsync(() -> "lala")
                .runAfterBoth(CompletableFuture.supplyAsync(() -> "yiyi"), () -> println(" success"));
        enter();

        //allOf
        future = CompletableFuture.allOf(
                Arrays.asList(
                        CompletableFuture.supplyAsync(() -> "111").thenAccept(JavaCompletableFuture::println),
                        CompletableFuture.supplyAsync(() -> 222).thenAccept(JavaCompletableFuture::println),
                        CompletableFuture.supplyAsync(() -> Boolean.TRUE).thenAccept(JavaCompletableFuture::println),
                        CompletableFuture.runAsync(() -> println("test allOf"))
                ).toArray(new CompletableFuture[0]));
        FutureUtils.executeCompletableFuture(future);
    }

    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    private static void enter() {
        System.out.println();
    }
}
