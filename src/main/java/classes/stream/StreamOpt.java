// Copyright (C) 2017 Meituan
// All rights reserved
package classes.stream;

import Utils.BasicUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                .forEach(BasicUtils::println);

        BasicUtils.enter();

        //交易员都在哪些不同的城市工作过
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(BasicUtils::println);

        BasicUtils.enter();

        //查找所有来自于剑桥的交易员，并按姓名排序
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(BasicUtils::println);

        BasicUtils.enter();

        //返回所有交易员的姓名字符串，按字母顺序排序
        BasicUtils.println(transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining()));

        BasicUtils.enter();

        //有没有交易员是在米兰工作的
        BasicUtils.println(transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan")));

        BasicUtils.enter();

        //所有交易中，最高的交易额是多少
        BasicUtils.println(transactions.stream()
                .map(Transaction::getValue)
                .max(Comparator.comparing(c -> c)));

        BasicUtils.enter();

        //生成1-100范围的IntStream
        BasicUtils.println(IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).count());

        BasicUtils.enter();

        //读文件
        try(Stream<String> lines = Files.lines(Paths.get("/Users/bluedeng/Downloads/cargo-福利.yaml"))) {
            lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .flatMap(line -> Arrays.stream(line.split(":")))
                    .distinct()
                    .forEach(BasicUtils::println);
        } catch (IOException e) {}

        BasicUtils.enter();

        //无限流模式
        //Stream.generate(ThreadLocalRandom.current()::nextInt).limit(10).forEach(BasicUtils::println);
        Stream.iterate(0, i -> i + 2).limit(10).forEach(BasicUtils::println);

        BasicUtils.enter();

        //斐波那契前20个数
        Stream.iterate(new int[]{1, 1}, intArr -> new int[]{intArr[1], intArr[0] + intArr[1]})
                .limit(10)
                .forEach(intArr -> BasicUtils.println(intArr[0]));

        BasicUtils.enter();

        BasicUtils.println(transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getTrader))
                .toString());

        BasicUtils.enter();

        IntSummaryStatistics statistics = transactions.stream().collect(Collectors.summarizingInt(Transaction::getValue));
        BasicUtils.println(statistics);

        BasicUtils.enter();

        BasicUtils.println(transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getYear, Collectors.groupingBy(Transaction::getTrader))));

        BasicUtils.enter();

        BasicUtils.println(transactions.stream()
                .collect(Collectors.partitioningBy(transaction -> transaction.getYear() == 2011,
                        Collectors.groupingBy(Transaction::getTrader))));

        BasicUtils.enter();
    }
}