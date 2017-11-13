// Copyright (C) 2017 Meituan
// All rights reserved
package classes.stream;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2017/11/13.
 */
public class Trader {

    private final String name;

    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" + "name='" + name + '\'' + ", city='" + city + '\'' + '}';
    }
}
