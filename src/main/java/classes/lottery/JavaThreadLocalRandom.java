package classes.lottery;

import java.util.concurrent.ThreadLocalRandom;

import static Utils.BasicUtils.println;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2019/2/1.
 */
public class JavaThreadLocalRandom {

    public static void main(String... args) {
        testRandom1();
    }

    private static void testRandom1() {
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;

        int total = 10000;
        for (int i = 0; i < 10; i++) {
            println("round: " + i);
            for (int j = 0; j < 1000; j++) {
                int random = ThreadLocalRandom.current().nextInt(total);
                if (random < 2500) {
                    c1++;
                } else if (random < 5000) {
                    c2++;
                } else if (random < 7500) {
                    c3++;
                } else {
                    c4++;
                }
            }
            println("result, c1 = " + c1 + ", c2 = " + c2 + ", c3 = " + c3 + ", c4 = " + c4);
        }
    }
}
