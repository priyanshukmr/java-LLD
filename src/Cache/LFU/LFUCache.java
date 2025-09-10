package Cache.LFU;

import java.util.*;

/**
 * https://leetcode.com/problems/lfu-cache/
 *
 * using LinkedHashSet
 */

class LFUCache {

    int capacity;
    int minf;
    Map<Integer, LinkedHashSet<Integer> > cache; // freq -> <LRU_set_keys>
    Map<Integer, List<Integer>> keyMap; // key -> [freq, val]

    public LFUCache(int capacity) {
        cache = new HashMap<>();
        keyMap = new HashMap<>();
        this.capacity = capacity;
        this.minf = 1;
    }

    private void insert(int key, int val, int freq) {
        LinkedHashSet<Integer> updatedSet = cache.getOrDefault(freq, new LinkedHashSet<>());
        updatedSet.add(key);
        cache.put(freq, updatedSet);
        keyMap.put(key, List.of(freq, val));
    }

    private void remove(int key, int freq) {
        cache.get(freq).remove(key);
        if(cache.get(freq).size()==0 && freq==minf) {
            minf++;
        }
        keyMap.remove(key);
    }

    public int get(int key) {
        if(!keyMap.containsKey(key)) return -1;
        int freq = keyMap.get(key).get(0);
        int val = keyMap.get(key).get(1);
        remove(key, freq);
        insert(key, val, freq+1);
        return val;
    }

    public void put(int key, int value) {
        if(!keyMap.containsKey(key)) {
            if(keyMap.size()==capacity) {
                remove(cache.get(minf).iterator().next(), minf);
            }
            insert(key, value, 1);
            minf=1;
            return;
        }
        int freq = keyMap.get(key).get(0);
        remove(key, freq);
        insert(key, value, freq+1);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
