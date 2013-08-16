package stni.atlassian.remote.script;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public abstract class DynamicMap<K, V> implements Map<K, V> {
    public abstract V apply(K key);

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean containsValue(Object value) {
        return false;
    }

    public V get(Object key) {
        return apply((K) key);
    }

    public V put(K key, V value) {
        return null;
    }

    public V remove(Object key) {
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
    }

    public void clear() {
    }

    public Set<K> keySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
