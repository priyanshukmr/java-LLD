package Cache.TTL;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class CacheTTL implements Cache {
    private Map<String, Value> cacheMap = new HashMap<>();
    private Queue<String> sortedKeys = new PriorityQueue<>();

    CacheTTL() {
        this.cleanup();
    }

    public Object get(String key) {
        if(!cacheMap.containsKey(key)) return null;
        long expiry = cacheMap.get(key).getExpiryTs();
        if(expiry < System.currentTimeMillis()) {
            delete(key);
            return null;
        }
        return cacheMap.get(key).getValue();
    }


    public void set(String key, int value, long TTL_millis) {
        long expiry = TTL_millis + System.currentTimeMillis();
        Value cacheVal = new Value(key, value, expiry);
        cacheMap.put(key, cacheVal);
        sortedKeys.add(expiry + "," + key);
    }

    public void delete(String key){
        cacheMap.remove(key);
    }

    static private long extractExpiryTs(String key) {
        return Long.parseLong(key.split(",")[0]);
    }

    static private String extractKey(String key) {
        return key.split(",")[1];
    }

    public void printCache(){
        for(String key: cacheMap.keySet()) {
            System.out.println(key + ": " + cacheMap.get(key).getValue());
        }
    }

    private void cleanup() {
        printCache();
        Runnable runnable = ()->{
            while(true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                long now = System.currentTimeMillis();
                String earliest = sortedKeys.peek();
                while(earliest!=null && extractExpiryTs(earliest) < now) {
                    String earliestKey = extractKey(earliest);
                    cacheMap.remove(earliestKey);
                    printCache();
                    sortedKeys.remove();
                    earliest = sortedKeys.peek();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void main(String[] args) {
        Long now = System.currentTimeMillis();
        Cache cache = new CacheTTL();
        cache.set("x", 10, 2000);
        cache.set("y", 20, 4000);

    }

}


 
