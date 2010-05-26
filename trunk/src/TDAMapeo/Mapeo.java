package TDAMapeo;

import java.util.Map.Entry;

public interface Mapeo<K, V> {
	
	public int size();
	public boolean isEmpty();
	public V get(K key);
	public V put(K key, V value);
	public V remove(K key);
	public Iterable<K> keys();
	public Iterable<V> values();
	public Iterable<Entry<K,V>> entries();

}
