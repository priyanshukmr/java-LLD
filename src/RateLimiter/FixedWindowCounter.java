package RateLimiter;

import java.util.function.IntConsumer;

/*

Pros:
- O(1) memory

Cons:
- causes burst at window boundary
 */

public class FixedWindowCounter implements RateLimiter{

    private final int perMinCapacity;
    private long lastMinute;
    private int lastMinuteLoad;
    private final IntConsumer consumer;

    FixedWindowCounter(int perMinCapacity, IntConsumer consumer) {
        this.perMinCapacity = perMinCapacity;
        this.consumer = consumer;
    }

    public boolean grantAccess(int reqId) {
        long minute = System.currentTimeMillis() / (1000*60);
        if(minute!=lastMinute) {
            lastMinute = minute;
            lastMinuteLoad = 0;
        }
        if(lastMinuteLoad < perMinCapacity) {
            consumer.accept(reqId);
            lastMinuteLoad++;
            return true;
        }
        return false;
    }
}
