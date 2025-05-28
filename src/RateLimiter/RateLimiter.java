package RateLimiter;

/*
Application code should be able to initialise RateLimiter for each user
 */

public interface RateLimiter {
    boolean grantAccess(int reqId);
}
