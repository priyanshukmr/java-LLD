package RateLimiter.ThreadSafe;

import java.util.function.IntConsumer;

public class Main {

    private static IntConsumer consumer= (x) -> System.out.println("Processed reqId:"+x);

    public static void main(String[] args) {
        UserBucketContainer rateLimiter = new UserBucketContainer();
        rateLimiter.addUser("101", new FixedWindowSafe(5, consumer));


        for(int i=0; i<10; i++) {
            rateLimiter.grantAccess("101",i);
        }
    }
}
