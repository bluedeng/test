package classes;// Copyright (C) 2016 Meituan
// All rights reserved

import classes.SumTraitWrapper;
import objects.BasicCalUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 16/10/11 上午10:07
 */

public class JavaMain {

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

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //list.forEach(System.out::println);
        //list.stream().map(integer -> integer * 2).forEach(System.out::println);
        System.out.println(list.stream().map(integer -> integer * 2).collect(Collectors.toList()).getClass());
        //System.out.println(BasicCalUtils.sumTheList(list).getClass());
        //BasicCalUtils.sumTheList(list).forEach(System.out::println);
    }
}