package RateLimiter.ThreadSafe;

/*
Bucket contains requests here

Pros:
- no burst issue as Rate_out is fixed

Cons:
- O(n) memory

 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class LeakyBucketSafe {
    private final int capacity;
    private final Deque<Integer> bucket;
    private final int leakRatePerSec;
    private final IntConsumer consumer;
    private long lastBucketUpdate;

    LeakyBucketSafe(int capacity, int leakRatePerSec, IntConsumer consumer) {
        this.capacity = capacity;
        this.bucket = new ArrayDeque<>();
        this.leakRatePerSec = leakRatePerSec;
        this.consumer = consumer;
        this.lastBucketUpdate = System.currentTimeMillis();
    }

    private List<Integer> leakBucket() {
        List<Integer> leaked = new ArrayList<>();
        long current = System.currentTimeMillis();
        double elapsedSec = (current-lastBucketUpdate)/1000.0;
        int leaks = (int)( elapsedSec* leakRatePerSec);
        if(leaks>0) {
            while (!bucket.isEmpty() && leaks>0) {
                int reqId = bucket.removeFirst();
                leaked.add(reqId);

                leaks--;
            }
            lastBucketUpdate = current;
        }
        return leaked;
    }

    public boolean grantAccess(int reqId) {
        boolean granted = false;
        List<Integer> leaked;
        synchronized (this) {
            leaked = leakBucket();
            if(bucket.size()<capacity) {
                bucket.add(reqId);
                granted = true;
            }
        }
        for(int leakReqId: leaked)
            this.consumer.accept(leakReqId);
        return granted;
    }
}
