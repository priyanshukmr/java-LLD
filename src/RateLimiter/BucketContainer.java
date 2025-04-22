package RateLimiter;

import java.util.Map;
import java.util.TreeMap;

public class BucketContainer {
    Map<Integer, RateLimiter> userToBucket;

    BucketContainer() {
        userToBucket = new TreeMap<>();
    }

    void addUserBucket(int userId, RateLimiter rateLimiter) {
        userToBucket.put(userId, rateLimiter);
    }

    RateLimiter getBucket(int userId) {
        return userToBucket.get(userId);
    }
}
