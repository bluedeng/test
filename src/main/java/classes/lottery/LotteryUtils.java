package classes.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2019/2/12.
 */
public class LotteryUtils {

    public static List<Integer> adjustBoundList(List<Integer> probabilityList, List<Integer> stockList,
            AtomicIntegerArray countArray) {
        Integer totalClaimCount = 0;
        for (int i = 0; i < countArray.length(); i++) {
            totalClaimCount += countArray.get(i);
        }

        if (totalClaimCount == 0) {
            return initialBoundList(probabilityList);
        } else {
            return adjustBoundListUseClaimRate(probabilityList, stockList, countArray, totalClaimCount);
        }
    }

    private static List<Integer> initialBoundList(List<Integer> probabilityList) {
        List<Integer> boundList = new ArrayList<>(probabilityList);
        for (int i = 1; i < boundList.size(); i++) {
            boundList.set(i, boundList.get(i) + boundList.get(i - 1));
        }
        return boundList.stream()
                .map(i -> i * 100)
                .collect(Collectors.toList());
    }

    private static List<Integer> adjustBoundListUseClaimRate(List<Integer> probabilityList, List<Integer> stockList,
            AtomicIntegerArray countArray, Integer totalClaimCount) {
        List<Integer> boundList = new ArrayList<>(probabilityList);
        boundList.set(0,
                getCurBoundUseClaimRate(boundList.get(0), probabilityList.get(0), countArray.get(0), totalClaimCount,
                        stockList.get(0)));
        for (int i = 1; i < boundList.size(); i++) {
            boundList.set(i, boundList.get(i - 1) +
                    getCurBoundUseClaimRate(boundList.get(i), probabilityList.get(i), countArray.get(i), totalClaimCount,
                            stockList.get(i)));
        }
        return boundList.stream()
                .map(i -> i * 100)
                .collect(Collectors.toList());
    }

    private static int getCurBoundUseClaimRate(int bound, int probability, int claimCount, int totalClaimCount, int stock) {
        int curRate = claimCount * 10000 / totalClaimCount;
        if (claimCount == 0) {
            return bound;
        } else if (claimCount == stock) {
            return 0;
        } else {
//            return bound;
            return bound * probability / curRate;
        }
    }
}
