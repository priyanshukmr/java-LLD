package RateLimiter;

/*
Bucket contains requests here

Pros:
- no burst issue as Rate_out is fixed

Cons:
- O(n) memory

 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.IntConsumer;

public class LeakyBucket {
    private final int capacity;
    private Deque<Integer> bucket;
    private final int leakRatePerSec;
    private final IntConsumer consumer;
    private long lastBucketUpdate;

    LeakyBucket(int capacity, int leakRatePerSec, IntConsumer consumer) {
        this.capacity = capacity;
        this.bucket = new ArrayDeque<>();
        this.leakRatePerSec = leakRatePerSec;
        this.consumer = consumer;
        this.lastBucketUpdate = System.currentTimeMillis();
    }

    private void leakBucket() {
        long current = System.currentTimeMillis();
        double elapsedSec = (current-lastBucketUpdate)/1000.0;
        int leaks = (int)( elapsedSec* leakRatePerSec);
        if(leaks>0) {
            while (!bucket.isEmpty() && leaks>0) {
                int reqId = bucket.removeFirst();
                this.consumer.accept(reqId);
                leaks--;
            }
            lastBucketUpdate = current;
        }
    }

    public boolean grantAccess(int reqId) {
        boolean granted = false;
        if(bucket.size()<capacity) {
            bucket.add(reqId);
            granted = true;
        }
        leakBucket();
        return granted;
    }
}
