package RateLimiter.ThreadSafe;

import java.util.function.IntConsumer;

/*

Pros:
- O(1) memory

Cons:
- causes burst at window boundary
 */

public class FixedWindowSafe implements RateLimiter{

    private final int perMinCapacity;
    private long lastMinute;
    private int lastMinuteLoad;
    private final IntConsumer consumer;

    FixedWindowSafe(int perMinCapacity, IntConsumer consumer) {
        this.perMinCapacity = perMinCapacity;
        this.consumer = consumer;
    }

    private synchronized boolean checkAccessSafely() {
        long minute = System.currentTimeMillis() / (1000*60);
        if(minute!=lastMinute) {
            lastMinute = minute;
            lastMinuteLoad = 0;
        }
        if(lastMinuteLoad < perMinCapacity) {
            lastMinuteLoad++;
            return true;
        }
        return false;
    }

    public boolean grantAccess(int reqId) {
        if (checkAccessSafely()) {
            consumer.accept(reqId); // call outside the synchronized context
            return true;
        }
        return false;
    }

}
