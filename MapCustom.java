package HashMapCustom;

import java.util.Comparator;
import java.util.LinkedList;

public class MapCustom<K,V> implements Comparator<Entry<K, V>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private int numBuckets;
    LinkedList<Entry<K, V>>[] buckets;

    public MapCustom() {
        this.numBuckets = INITIAL_CAPACITY;
        size = 0;
        buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % numBuckets);
    }

    public void put(K key, V value) {
        if (shouldResize()) {
            resize();
        }
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    private void resize() {
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        numBuckets *= 2;
        size = 0;
        buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            for (Entry<K, V> entry : bucket) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private boolean shouldResize() {
        return (float) size / numBuckets >= LOAD_FACTOR;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public int compare(Entry<K, V> o1, Entry<K, V> o2) {
        return 0;
    }
}

