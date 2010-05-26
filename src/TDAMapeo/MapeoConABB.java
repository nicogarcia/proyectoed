package TDAMapeo;

import java.util.Comparator;
import java.util.Map.Entry;

import Excepciones.InvalidKeyException;
import Excepciones.ItemNotFoundException;

//FIXME COPIADO EN CLASE!
public class MapeoConABB<K, V> implements Mapeo<K, V> {
	protected ABB<K, V> abb;

	// constructor
	public MapeoConABB(Comparator<K> c) {
		abb = new ABB<K, V>(c);
	}

	public V put(K k, V v) {
		return abb.insertar(new Entrada<K, V>(k, v));
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		return abb.entries();
	}

	// TODO PREGUNTAR SOBRE EL TRY
	@Override
	public V get(K key) {
		try {
			return abb.find(key).getValue();
		} catch (InvalidKeyException e) {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return abb.isEmpty();
	}

	@Override
	public Iterable<K> keys() {
		return abb.keys();
	}

	@Override
	public V remove(K key) {
		try {
			return abb.remove(key);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public int size() {
		return abb.size();
	}

	@Override
	public Iterable<V> values() {
		return abb.values();
	}

	public String toString() {
		return abb.toString();
	}
}
