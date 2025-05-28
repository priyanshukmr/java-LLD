package RateLimiter;


import java.util.function.IntConsumer;

/*
- token count is maintained and refilled at fixed rate
(bucket contains token)

Pros
- O(1) memory
- avoid boundary burst

Cons
- still burst issue when bucket is full
 */

public class TokenBucket implements RateLimiter{
    private long tokens;
    private final int capacity;
    private final int refillRatePerSec;
    private final IntConsumer consumer;
    private long lastBucketUpdate;

    TokenBucket(int capacity, int tokenRefillRatePerSec, IntConsumer consumer) {
        this.tokens = capacity;
        this.capacity = capacity;
        this.refillRatePerSec = tokenRefillRatePerSec;
        this.consumer = consumer;
        this.lastBucketUpdate = System.currentTimeMillis();
    }

    private void refillBucket() {
        long current = System.currentTimeMillis();
        double elapsed = (current - lastBucketUpdate)/1000.0;
        tokens = Math.min(capacity, (long)(tokens + elapsed*refillRatePerSec));
        lastBucketUpdate = current;
    }

    public boolean grantAccess(int reqId) {
        refillBucket();
        if(tokens>0) {
            this.consumer.accept(reqId);
            tokens--;
            return true;
        }
        return false;
    }

}
