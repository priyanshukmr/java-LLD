package RateLimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.IntConsumer;

/*
- queue of timestamps is maintained

Pros:
- does not suffer from boundary burst

Cons:
- O(n) memory
- but still can cause burst when window is empty
 */

public class SlidingWindowLog implements RateLimiter{
    private final Deque<Long> queue;
    private final int capacity;
    private final int timeRangeMillis;
    private final IntConsumer intConsumer;

    SlidingWindowLog(int capacity, int timeRangeMillis, IntConsumer intConsumer) {
        this.queue = new ArrayDeque<>();
        this.capacity = capacity;
        this.timeRangeMillis = timeRangeMillis;
        this.intConsumer = intConsumer;
    }

    private void trimQueue() {
        long current = System.currentTimeMillis();
        while(!queue.isEmpty() && current-queue.peekFirst() > timeRangeMillis) {
            queue.removeFirst();
        }
    }

    public boolean grantAccess(int reqId) {
        trimQueue();
        if(queue.size()<capacity) {
            intConsumer.accept(reqId);
            queue.add(System.currentTimeMillis());
            return true;
        }
        return false;
    }
}
