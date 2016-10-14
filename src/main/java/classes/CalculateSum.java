package classes;

import java.util.concurrent.RecursiveTask;

/**
 * Created by DTZ on 2016/10/12 23:03.
 **/
public class CalculateSum extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    public CalculateSum(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long sum = 0L;
        if (end < start) {
            return 0L;
        } else if (end - start <= 100) {
            for(Long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            Long mid = (start + end) / 2;
            CalculateSum left = new CalculateSum(start, mid);
            left.fork();
            CalculateSum right = new CalculateSum(mid + 1, end);
            right.fork();

            sum += left.join();
            sum += right.join();
        }
        return sum;
    }
}
