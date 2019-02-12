package classes.lottery;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

import static Utils.BasicUtils.println;

/**
 * @author dengtianzhi
 * 能处理的场景：抽中概率分布与库存分布相对独立
 * 抽中概率分布由概率数值组成的数值区间实现，10%,20%,30%,40%=10,30,60,100
 * 库存分布由库存对应的数值区间组成
 * 数值区间的生成/律动策略影响抽奖命中的均匀性
 * 律动策略均匀性影响：不律动 > 关联领取率vs.抽中概率
 * @version 1.0
 * @created 2019/2/1.
 */
public class Lottery {

    private static List<Integer> probabilityList = Arrays.asList(3000, 3000, 3000, 1000);
    private static List<Integer> stockList = Arrays.asList(3000, 3000, 3000, 1000);
    private static AtomicIntegerArray countArray = new AtomicIntegerArray(4);
    private static AtomicInteger repeatCount = new AtomicInteger(0);
    private static AtomicInteger decreaseCount = new AtomicInteger(0);
    private static AtomicLong misSmoothStat = new AtomicLong(0);

    public static void main(String... args) {
        for (int i = 0; i < 5; i++) {
            test1();
        }

//        test2();
    }

    private static void test1() {
        countArray = new AtomicIntegerArray(4);
        repeatCount = new AtomicInteger(0);
        decreaseCount = new AtomicInteger(0);
        misSmoothStat = new AtomicLong(0);
        for (int i = 0; i < 1000; i++) {
            countArray = new AtomicIntegerArray(4);

            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream().parallel().forEach(item -> testRandom1());
        }

        println("repeat count: " + (repeatCount.longValue() + decreaseCount.longValue()) / 1000);
        println("mis smooth count: " + misSmoothStat.longValue() / 1000);
        println("");
    }

    private static void test2() {
        countArray = new AtomicIntegerArray(4);
        repeatCount.set(0);
        decreaseCount.set(0);
        misSmoothStat.set(0);
        for (int i = 0; i < 10; i++) {
            testRandom1();
        }

        println("repeat count: " + repeatCount);
        println("decrease count: " + decreaseCount);
        println("mis smooth count: " + misSmoothStat.longValue());
        println("");
    }

    private static void testRandom1() {
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 100; j++) {
                List<Integer> boundList = adjustBoundList();
                Integer rightBound = boundList.get(boundList.size() - 1);
                if (rightBound <= 0) {
                    repeatClaimCount();
                    j--;
                    continue;
                }
                int random = ThreadLocalRandom.current().nextInt(rightBound);

                for (int k = 0; k < boundList.size(); k++) {
                    if (random < boundList.get(k)) {
                        if (!isClaimable(k)) {
                            repeatClaimCount();
                            j--;
                            break;
                        } else if (incrementClaimCount(k) > stockList.get(k)) {
                            decrementClaimCount(k);
                            j--;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }

            record233();
//            println("result: " + countArray);
        }
    }

    private static List<Integer> adjustBoundList() {
        return LotteryUtils.adjustBoundList(probabilityList, stockList, countArray);
    }

    private static boolean isClaimable(int index) {
        return stockList.get(index) > countArray.get(index);
    }

    private static int incrementClaimCount(int index) {
        return countArray.incrementAndGet(index);
    }

    private static void decrementClaimCount(int index) {
        countArray.decrementAndGet(index);
        decreaseCount.incrementAndGet();
    }

    private static void repeatClaimCount() {
        repeatCount.incrementAndGet();
    }

    private static void record233() {
        Integer totalClaimCount = 0;
        for (int i = 0; i < countArray.length(); i++) {
            totalClaimCount += countArray.get(i);
        }

        for (int i = 0; i < countArray.length(); i++) {
            long cur = (long) ((double) countArray.get(i) / totalClaimCount * 10000);
            misSmoothStat.addAndGet(Math.abs(probabilityList.get(i) - cur));
        }
    }
}
