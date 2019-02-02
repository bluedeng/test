package classes.lottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

import static Utils.BasicUtils.println;

/**
 * @author dengtianzhi
 * 能处理的场景：库存分布与抽中概率分布一致
 * 抽中概率分布由概率数值组成的数值区间实现，10%,20%,30%,40%=10,30,60,100
 * 数值区间的生成/律动策略影响抽奖命中的均匀性
 * 律动策略均匀性影响：不律动 > 关联绝对领取数量 > 关联领取率
 * @version 1.0
 * @created 2019/2/1.
 */
public class LotteryWhenStockIsProbability {

    private static List<Integer> indexList = Arrays.asList(1, 2, 3, 4);
    private static List<Integer> settingList = Arrays.asList(4000, 2000, 2000, 2000);
    private static AtomicIntegerArray countArray = new AtomicIntegerArray(4);
    private static AtomicInteger decreaseCount = new AtomicInteger(0);
    private static AtomicLong misSmoothStat = new AtomicLong(0);

    public static void main(String... args) {
        test(1);
        test(2);
        test(3);
    }

    private static void test(int u) {
        long millis1 = System.currentTimeMillis();

        countArray = new AtomicIntegerArray(4);
        decreaseCount.set(0);
        misSmoothStat.set(0);
        for (int i = 0; i < 1000; i++) {
            countArray = new AtomicIntegerArray(4);
            decreaseCount.set(0);

            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
                    .parallel()
                    .forEach(item -> testRandom1(u));
        }

        long millis2 = System.currentTimeMillis();

        println("strategy: " + u);
        println("decrease count: " + decreaseCount);
        println("mis smooth count: " + misSmoothStat.longValue() / 100);
        println("use time: " + (millis2 - millis1));
        println("");
    }

    private static void testRandom1(int u) {
        for (int i = 0; i < 10; i++) {
//            println("round: " + i);
            List<Integer> boundList = adjustBoundList(u);
            Integer rightBound = boundList.get(boundList.size() - 1);

            for (int j = 0; j < 100; j++) {
                int random = ThreadLocalRandom.current().nextInt(rightBound);

                for (int k = 0; k < boundList.size(); k++) {
                    if (random < boundList.get(k)) {
                        if (!isClaimable(indexList.get(k))) {
                            boundList = adjustBoundList(u);
                            rightBound = boundList.get(boundList.size() - 1);
                            j--;
                            break;
                        } else if (incrementClaimCount(indexList.get(k)) > settingList.get(k)) {
                            decrementClaimCount(indexList.get(k));
                            boundList = adjustBoundList(u);
                            rightBound = boundList.get(boundList.size() - 1);
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

    private static List<Integer> adjustBoundList(int u) {
        List<Integer> boundList = new ArrayList<>(settingList);

        switch (u) {
        case 1:
            return adjustBoundListNaive(boundList);
        case 2:
            return adjustBoundListUseClaimCount(boundList);
        case 3:
            return adjustBoundListUseClaimRate(boundList);

            default:
                return adjustBoundListNaive(boundList);
        }
    }

    private static List<Integer> adjustBoundListNaive(List<Integer> boundList) {
        for (int i = 1; i < boundList.size(); i++) {
            boundList.set(i, boundList.get(i) + boundList.get(i - 1));
        }

        return boundList;
    }

    private static List<Integer> adjustBoundListUseClaimCount(List<Integer> boundList) {
        boundList.set(0, boundList.get(0) - countArray.get(0));
        for (int i = 1; i < boundList.size(); i++) {
            boundList.set(i, boundList.get(i) - countArray.get(i) + boundList.get(i - 1));
        }

        return boundList;
    }

    private static List<Integer> adjustBoundListUseClaimRate(List<Integer> boundList) {
        Integer total = settingList.stream().mapToInt(Integer::intValue).sum();
        Integer totalClaimCount = 0;
        for (int i = 0; i < countArray.length(); i++) {
            totalClaimCount += countArray.get(i);
        }

        if (totalClaimCount == 0) {
            return adjustBoundListUseClaimCount(boundList);
        } else {
            double generalRate = (double)totalClaimCount / total;
            boundList.set(0, getCurBoundUseClaimRate(boundList.get(0) - countArray.get(0), generalRate,
                    (double)countArray.get(0) / settingList.get(0)));
            for (int i = 1; i < boundList.size(); i++) {
                boundList.set(i, boundList.get(i - 1) +
                        getCurBoundUseClaimRate(boundList.get(i) - countArray.get(i), generalRate,
                                (double)countArray.get(i) / settingList.get(i)));
            }
        }

        return boundList;
    }

    private static int getCurBoundUseClaimRate(int bound, double generalRate, double curRate) {
        if (curRate == 0) {
            return bound;
        } else {
            return (int)(bound * generalRate / curRate);
        }
    }

    private static boolean isClaimable(int index) {
        return settingList.get(index - 1) > countArray.get(index - 1);
    }

    private static int incrementClaimCount(int index) {
        return countArray.incrementAndGet(index - 1);
    }

    private static void decrementClaimCount(int index) {
        countArray.decrementAndGet(index - 1);
        decreaseCount.incrementAndGet();
    }

    private static void record233() {
        long min = Long.MAX_VALUE;
        long max = 0;
        for (int i = 0; i < countArray.length(); i++) {
            long cur = (long)((double)countArray.get(i) / settingList.get(i) * 100);
            if (cur < min) {
                min = cur;
            }
            if (cur > max) {
                max = cur;
            }
        }

        misSmoothStat.addAndGet(Math.abs(max - min));
    }
}
