// Copyright (C) 2016 Meituan
// All rights reserved
package classes;

import traites.MinusTrait;
import traites.SumTrait;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2016/11/18 下午6:03
 */
public class Compute implements MinusTrait, SumTrait {

    public int minus(int x, int y) {
        return x - y;
    }
}