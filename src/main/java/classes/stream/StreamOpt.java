// Copyright (C) 2017 Meituan
// All rights reserved
package classes.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2017/11/13.
 */
public class StreamOpt {

    public static void main(String[] args) {

        //data here
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        //找出2011年的所有交易并按交易额排序（从低到高）
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);

        //交易员都在哪些不同的城市工作过
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        //查找所有来自于剑桥的交易员，并按姓名排序
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        //返回所有交易员的姓名字符串，按字母顺序排序
        System.out.println(transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining()));

        //有没有交易员是在米兰工作的
        System.out.println(transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan")));

        //所有交易中，最高的交易额是多少
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .max(Comparator.comparing(c -> c)));
    }
}
