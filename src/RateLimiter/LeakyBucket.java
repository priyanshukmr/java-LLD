package RateLimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LeakyBucket implements RateLimiter{
    BlockingQueue<Integer> bucket;
    int capacity;

    LeakyBucket(int capacity) {
        bucket = new ArrayBlockingQueue<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public boolean submitReqToBucket(int req){
        if(bucket.size()<capacity) {
            bucket.add(req);
            System.out.println(Thread.currentThread().getName()+" added request "+req);
            return true;
        }
        System.out.println(Thread.currentThread().getName() + " bucket overflowed");
        return false;
    }
}
