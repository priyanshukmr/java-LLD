package RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

public class MainApp {
    public static void main(String[] args) {
        BucketContainer buckets = new BucketContainer();
        buckets.addUserBucket(101, new LeakyBucket(10));

        ExecutorService executors = Executors.newFixedThreadPool(10);

        // Producer logic
        int[] req = new int[1];
        for(req[0]=1; req[0]<=12; req[0]++) {
            executors.execute(()->{
                buckets.getBucket(101).submitReqToBucket(req[0]);
            });
        }

    }
}
