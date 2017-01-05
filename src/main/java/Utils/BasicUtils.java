// Copyright (C) 2016 Meituan
// All rights reserved
package Utils;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2016/10/11 下午5:14
 */
public class BasicUtils {

    public static Integer sum(Integer x, Integer y) {
        return x + y;
    }

    public static void printMultiArgs(String... args) {
        System.out.println(args.getClass());
    }

    public static String ifElseCheck(Integer i) {
        if (i >= 3) {
            return "Prepare~~~";
        } else if (i.equals(2)) {
            return "Are you ready~~";
        } else if (i.equals(1)) {
            return "Ready~";
        } else {
            return "Go!";
        }
    }

    public static String switchCheck(Integer i) {
        String returnStr;
        switch (i) {
        case 3:
            returnStr = "Prepare~~~";
            break;
        case 2:
            returnStr = "Are you ready~~";
            break;
        case 1:
            returnStr = "Ready~";
            break;
        case 0:
            returnStr = "Go!";
            break;
        default:
            returnStr = "What?";
            break;
        }
        return returnStr;
    }
}