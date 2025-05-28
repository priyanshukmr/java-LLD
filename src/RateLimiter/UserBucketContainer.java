package RateLimiter;

import java.util.*;

public class UserBucketContainer {
    private Map<String, RateLimiter> userToBucket;

    UserBucketContainer() {
        userToBucket = new HashMap<String, RateLimiter>();
    }

    public void addUser(String userId, RateLimiter rateLimiter) {
        if(!userToBucket.containsKey(userId)) {
            userToBucket.put(userId, rateLimiter);
        }
    }

    public boolean grantAccess(String userId, int reqId) {
        return userToBucket.get(userId).grantAccess(reqId);
    }
}
