package classes;// Copyright (C) 2016 Meituan
// All rights reserved

import classes.SumTraitWrapper;
import objects.BasicCalUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 16/10/11 上午10:07
 */

public class JavaMain {

    public static Long calSum(Long start, Long right) {
        Long sum = 0L;
        if (start > right) {
            return 0L;
        }
        for (Long i = start; i <= right; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello from java!");

        /*
        ScalaPerson peter = new ScalaPerson("peter", 33);
        peter.setAge(32);
        System.out.println(peter);
        System.out.println(peter.getAge());
        */

        /*
        ScalaEmail email = new ScalaEmail();
        System.out.println(email);
        email.setName("dengtianzhi");
        email.setDomain("meituan.com");
        System.out.println(email);
        */

        /*
        System.out.println(new Minus().minus(5, 3));
        System.out.println(new SumTraitWrapper().sum(5, 3));
        */

        /*
        Integer i = BasicCalUtils.sum(new Integer(3), new Integer(5));
        System.out.println(i);
        System.out.println(i.getClass());
        System.out.println(BasicCalUtils.join("Hello, ", "from scala!"));
        */

        /*
        System.out.println(BasicCalUtils.sumList(1));
        System.out.println(BasicCalUtils.sumList(1, 2, 3));
        */

        /*
        try {
            BasicCalUtils.exceptionThrower(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Exception cannot stop us!");
         */

        /*
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //list.forEach(System.out::println);
        //list.stream().map(integer -> integer * 2).forEach(System.out::println);
        System.out.println(list.stream().map(integer -> integer * 2).collect(Collectors.toList()).getClass());
        //System.out.println(BasicCalUtils.sumTheList(list).getClass());
        //BasicCalUtils.sumTheList(list).forEach(System.out::println);
        */

        Long start = 0L;
        List<Long> ends = new ArrayList<>();
        ends.add(9L);
        for (int i = 1; i < 9; i++) {
            ends.add(ends.get(i - 1) * 10 + 9L);
        }

        List<List<Double>> results = new ArrayList<>();
        for (Long end : ends) {
            List<Double> result = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Long t1 = System.nanoTime();
                System.out.println(calSum(start, end));
                Long t2 = System.nanoTime();
                System.out.println(new ForkJoinPool().invoke(new CalculateSum(start, end)));
                Long t3 = System.nanoTime();
                System.out.println("" + (t2 - t1) + "\n" + (t3 - t2) + "\n" + (double) (t3 - t2) / (t2 - t1));
                result.add((double) (t3 - t2) / (t2 - t1));
                System.out.println();
            }
            results.add(result);
            System.out.println();
        }

        for (List<Double> list : results) {
            System.out.println(list.stream().reduce(0.0, (a, b) -> a + b) / 20);
        }
    }
}