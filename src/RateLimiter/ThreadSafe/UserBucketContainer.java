package RateLimiter.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserBucketContainer {
    private final ConcurrentMap<String, RateLimiter> userToBucket;

    UserBucketContainer() {
        userToBucket = new ConcurrentHashMap<>();
    }

    public void addUser(String userId, RateLimiter rateLimiter) {
        userToBucket.putIfAbsent(userId, rateLimiter);
    }

    // below method is only thread-safe because there is not removeUser feature
    public boolean grantAccess(String userId, int reqId) {
        RateLimiter rateLimiter = userToBucket.get(userId);
        if(userId==null) {
            return false;
        }
        return rateLimiter.grantAccess(reqId);
    }
}
