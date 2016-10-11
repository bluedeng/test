// Copyright (C) 2016 Meituan
// All rights reserved

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 16/10/11 上午10:07
 */
public class JavaMain {
    public static void main(String[] args) {
        System.out.println("Hello from java!");

        ScalaPerson peter = new ScalaPerson("peter", 33);
        System.out.println(peter.getName());
        System.out.println(peter.getAge());
    }
}