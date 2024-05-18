package HashMapCustom;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CustomIterator<K,V> implements Iterator<Entry<K,V>> {
    private final MapCustom<K,V> map;
    private int index;
    private Iterator<Entry<K,V>> currentBucketIterator;

    public CustomIterator(MapCustom<K, V> map) {
        this.map = map;
        this.index = 0;
        if(!map.buckets[index].isEmpty()){
            currentBucketIterator = map.buckets[index].iterator();
        }
    }

    @Override
    public boolean hasNext() {
        while (index < map.buckets.length){
            LinkedList<Entry<K,V>> bucket = map.buckets[index];
            if(!bucket.isEmpty()){
                if(currentBucketIterator == null || !currentBucketIterator.hasNext()){
                    currentBucketIterator = bucket.iterator();
                }
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public Entry<K, V> next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        Entry<K,V> result = currentBucketIterator.next();
        if(!currentBucketIterator.hasNext()){
            index++;
            while (index < map.buckets.length && map.buckets[index].isEmpty()){
                index++;
            }
            if(index < map.buckets.length){
                currentBucketIterator = map.buckets[index].iterator();
            }
            currentBucketIterator = null;
        }
        return result;
    }
}
