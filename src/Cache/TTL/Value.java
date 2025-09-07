package Cache.TTL;

class Value {
    private String key;
    private final int value;
    private final long expiryTs;

    Value(String key, int value, long expiryTs) {
        this.key = key;
        this.value = value;
        this.expiryTs = expiryTs;
    }

    public long getExpiryTs() {
        return expiryTs;
    }

    public Object getValue() {
        return value;
    }

}
