package RateLimiter.ThreadSafe;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;


public class SlidingWindowSafe implements RateLimiter{
    private final Deque<Long> queue;
    private final int capacity;
    private final int timeRangeMillis;
    private final IntConsumer intConsumer;
    private final Lock lock;

    SlidingWindowSafe(int capacity, int timeRangeMillis, IntConsumer intConsumer) {
        this.queue = new ArrayDeque<>();
        this.capacity = capacity;
        this.timeRangeMillis = timeRangeMillis;
        this.intConsumer = intConsumer;
        this.lock = new ReentrantLock();
    }

    private void trimQueue() {
        long current = System.currentTimeMillis();
        while(!queue.isEmpty() && current-queue.peekFirst() > timeRangeMillis) {
            queue.removeFirst();
        }
    }


    public boolean grantAccess(int reqId) {
        boolean granted = false;
        synchronized (lock) {
            trimQueue();
            if(queue.size()<capacity) {
                queue.add(System.currentTimeMillis());
                granted = true;
            }
        }
        if(granted) {
            intConsumer.accept(reqId);
        }
        return granted;
    }
}
