/*
Applicaton should be able to initialise ratelimiter and keep granting access as required
 */

package RateLimiter;

public interface RateLimiter {
    boolean submitReqToBucket(int req);
}
