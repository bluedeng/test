// Copyright (C) 2017 Meituan
// All rights reserved
package classes.stream;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2017/11/13.
 */
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" + "trader=" + trader + ", year=" + year + ", value=" + value + '}';
    }
}
