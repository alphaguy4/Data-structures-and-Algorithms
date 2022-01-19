package com.ds.linkedlist.problems;

import java.util.HashMap;
import java.util.Map;

/*
* What is an LRU cache ?
* Give most recently used value in O(1) time
* Inserts new key value pair in O(1)
* Fetches value for any key in O(1) time
* Has a limited size
* It will be implemented using a Hashtable and DoublyLinkedList
* */
public class LRUCacheProgram {

    static class LRUCache {
        Map<String, DoublyLinkedListNode> cache;
        int maxSize;
        int currentSize;
        DoublyLinkedList listOfMostRecentNodes;

        public LRUCache(int size) {
            this.maxSize = size;
            this.currentSize = 0;
            listOfMostRecentNodes = new DoublyLinkedList();
            cache = new HashMap<>();
        }

        // O(1)
        public LRUResult getValueFromKey(String key) {
            if(!cache.containsKey(key)) {
                return new LRUResult(false, null);
            }
            updateMostRecent(cache.get(key));
            return new LRUResult(true, cache.get(key).value);
        }

        // O(1) time
        public String getMostRecentKey() {
            if(listOfMostRecentNodes.head == null) {
                return "";
            }
            return listOfMostRecentNodes.head.key;
        }

        // O(1) time
        public void insertKeyValuePair(String key, String value) {
            if(!cache.containsKey(key)) {
                if(cache.size() == maxSize) {
                    evictLeastRecent();
                } else {
                    currentSize++;
                }
                cache.put(key, new DoublyLinkedListNode(key, value));
            } else {
                replaceKey(key, value);
            }
            updateMostRecent(cache.get(key));
        }

        private void evictLeastRecent() {
            String keyToRemove = listOfMostRecentNodes.tail.key;
            listOfMostRecentNodes.removeTail();
            cache.remove(keyToRemove);
        }

        private void replaceKey(String key, String value) {
            if(!cache.containsKey(key)) {
                return;
            }
            DoublyLinkedListNode node = cache.get(key);
            node.value = value;
        }

        private void updateMostRecent(DoublyLinkedListNode node) {
            listOfMostRecentNodes.setHeadTo(node);
        }


    }

    static class DoublyLinkedList {
        DoublyLinkedListNode head;
        DoublyLinkedListNode tail;

        /*
        * Either this is a new node
        * Or Already present in the DLL
        * */
        void setHeadTo(DoublyLinkedListNode node) {
            if(head == node) {
                return;
            } else if(head == null) {
                head = node;
                tail = head;
            } else if(head == tail) {
                node.next = head;
                node.prev = null;
                head = node;
            } else {
                // this required to adjust the new tail
                if(tail == node) {
                    removeTail();
                }
                node.removeBindings();
                node.prev = null;
                node.next = head;
                head = node;
            }

        }

        void removeTail() {
            if(tail == null) {
                return;
            }
            else if(head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
        }

    }

    static class DoublyLinkedListNode {
        String value;
        String key;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;

        public DoublyLinkedListNode(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public void removeBindings() {
            if(prev != null) {
                prev.next = next;
            }

            if(next != null) {
                next.prev = prev;
            }
            prev = null;
            next = null;
        }
    }

    static class LRUResult {
        boolean found;
        String value;

        public LRUResult(boolean found, String value) {
            this.found = found;
            this.value = value;
        }
    }
}
