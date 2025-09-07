package Cache.TTL;

public interface Cache {
    Object get(String key);

    void set(String key, int value, long TTL_millis);

    void delete(String key);
}