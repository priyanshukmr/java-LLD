package Cache.LRU;

import java.util.*;

/**
 * https://leetcode.com/problems/lru-cache/
 * O(1) put, get
 */

class LRUCache {

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    static class DoublyLinkedList {
        Node head; //MRU
        Node tail; //LRU

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
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
    }

    DoublyLinkedList list = new DoublyLinkedList();
    Map<Integer, Node> keyToNode = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!keyToNode.containsKey(key)) return -1;
        Node node = keyToNode.get(key);
        list.remove(node);
        list.addMRU(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(!keyToNode.containsKey(key)) {
            if(keyToNode.size()==this.capacity) {
                Node LRU = list.tail.prev;
                keyToNode.remove(LRU.key);
                list.remove(LRU);
            }
        }
        else {
            list.remove(keyToNode.get(key));
        }
        Node node = new Node(key, value);
        list.addMRU(node);
        keyToNode.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */