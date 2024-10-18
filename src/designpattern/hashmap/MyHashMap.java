package designpattern.hashmap;

import java.util.Map;

public class MyHashMap<K, V> {

    private static final int INITIAL_SIZE = 1 << 4; // 16
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private Entry<K, V>[] hashTable;

    public MyHashMap() {
        hashTable = new Entry[INITIAL_SIZE];
    }

    public MyHashMap(int capacity) {
        int tableSize = tableSizeFor(capacity);
        hashTable = new Entry[tableSize];
    }

    final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return (n < 8) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static class Entry<K, V> {
        public K key;
        public V value;
        public Entry<K, V> next;

        Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    public void put(K key, V value) {
        int hashCode = key.hashCode() % hashTable.length; // Ensure non-negative hash
        Entry<K, V> node = hashTable[hashCode];

        if (node == null) {
            hashTable[hashCode] = new Entry<>(key, value);
        } else {
            while (true) {
                if (node.key.equals(key)) { // Use .equals for key comparison
                    node.value = value;
                    return;
                }
                if (node.next == null) {
                    break; // Reached end of chain
                }
                node = node.next;
            }
            node.next = new Entry<>(key, value); // Add new entry at the end
        }
    }

    public V get(K key) {
        int hashCode = key.hashCode() % hashTable.length; // Ensure non-negative hash
        Entry<K, V> node = hashTable[hashCode];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value; // No need for casting
            }
            node = node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>(7);
        map.put(1, "Hi");
        map.put(2, "my");
        map.put(2, "name"); // This will replace the previous value for key 2
        map.put(3, "is");
        map.put(4, "Raman");

        String value = map.get(2);
        System.out.println(value); // Should print "name"
    }
}
