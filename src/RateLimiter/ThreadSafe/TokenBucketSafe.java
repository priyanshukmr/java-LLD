package RateLimiter.ThreadSafe;
import java.util.function.IntConsumer;

public class TokenBucketSafe implements RateLimiter {
    private long tokens;
    private final int capacity;
    private final int refillRatePerSec;
    private final IntConsumer consumer;
    private long lastBucketUpdate;

    TokenBucketSafe(int capacity, int tokenRefillRatePerSec, IntConsumer consumer) {
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
        // whole compound logic should be synchronized to make it atomic and processing should be done outside sync block
        boolean granted = false;
        synchronized (this) {
            refillBucket();
            if(tokens>0) {
                tokens--;
                granted = true;
            }
        }
        if(granted)
            consumer.accept(reqId);
        return granted;
    }

}
