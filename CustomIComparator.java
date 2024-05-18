package HashMapCustom;

import java.util.Comparator;

public class CustomIComparator<K,V> implements Comparator<Entry<K,V>> {
    public final Comparator<K>  keyComparator;

    public CustomIComparator(Comparator<K> keyComparator) {
        this.keyComparator = keyComparator;
    }

    @Override
    public int compare(Entry<K, V> o1, Entry<K, V> o2) {
        return keyComparator.compare(o1.getKey(),o2.getKey());
    }
}
