package RateLimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.IntConsumer;


public class SlidingWindowSafe implements RateLimiter{
    private final Deque<Long> queue;
    private final int capacity;
    private final int timeRangeMillis;
    private final IntConsumer intConsumer;

    SlidingWindowSafe(int capacity, int timeRangeMillis, IntConsumer intConsumer) {
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

    private synchronized boolean checkAccessSafely() {
        trimQueue();
        if(queue.size()<capacity) {
            queue.add(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public boolean grantAccess(int reqId) {
        if(checkAccessSafely()) {
            intConsumer.accept(reqId);
            return true;
        }
        return false;
    }
}
