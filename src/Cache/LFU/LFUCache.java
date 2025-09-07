package Cache.LFU;

import java.util.*;

/**
 * https://leetcode.com/problems/lfu-cache/
 */

class LFUCache {


    static class Node {
        int key;
        int val;
        int freq;
        Node prev;
        Node next;

        Node(int key, int val, int freq){
            this.freq = freq;
            this.key = key;
            this.val = val;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;

        DoublyLinkedList() {
            head = new Node(0, 0, 0);
            tail = new Node(0, 0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public void addMRU(Node n) {
            n.next = head.next;
            head.next.prev = n;
            head.next = n;
            n.prev = head;
        }

        public void remove(Node n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }

    Map<Integer, DoublyLinkedList> freqToList;
    Map<Integer, Node> keyToNode;
    int capacity;
    int sz;
    int minFreq;

    public LFUCache(int capacity) {
        freqToList = new HashMap<>();
        keyToNode = new HashMap<>();
        sz = 0;
        minFreq = 1;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!keyToNode.containsKey(key)) return -1;
        Node found = keyToNode.get(key);
        int freq = found.freq;
        freqToList.get(freq).remove(found);
        keyToNode.remove(key);
        if(freqToList.get(freq).isEmpty()) {
            freqToList.remove(freq);
            if(freq==minFreq)
                minFreq++;
        }
        DoublyLinkedList list = freqToList.getOrDefault(freq+1, new DoublyLinkedList());
        Node node = new Node(key, found.val, freq+1);
        list.addMRU(node);
        freqToList.put(freq+1, list);
        keyToNode.put(key, node);
        return found.val;
    }

    public void put(int key, int value) {
        int freq;
        //handleRemoval
        if(!keyToNode.containsKey(key)) {
            if(sz==capacity) {
                DoublyLinkedList minList = freqToList.get(minFreq);
                Node LRU = minList.tail.prev;
                minList.remove(LRU);
                if(minList.isEmpty()) {
                    freqToList.remove(minFreq);
                }
                keyToNode.remove(LRU.key);
            }
            else {
                sz++;
            }
            freq = 1;
            minFreq = 1;
        }
        else {
            freq = keyToNode.get(key).freq +1;
            DoublyLinkedList list = freqToList.get(keyToNode.get(key).freq);
            list.remove(keyToNode.get(key));
            if(list.isEmpty()) {
                freqToList.remove(keyToNode.get(key).freq);
                if(minFreq==keyToNode.get(key).freq) {
                    minFreq = freq;
                }
            }
        }
        // handle addition
        Node node = new Node(key, value, freq);
        DoublyLinkedList list = freqToList.getOrDefault(freq, new DoublyLinkedList());
        list.addMRU(node);
        freqToList.put(freq, list);
        keyToNode.put(key, node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */