package TDAMapeo;

import java.util.Comparator;
import java.util.Map.Entry;

import Excepciones.InvalidKeyException;
import Excepciones.ItemNotFoundException;

//FIXME COPIADO EN CLASE! FALTA JAVADOC DE CLASE
public class MapeoConABB<K, V> implements Mapeo<K, V> {
	protected ABB<K, V> abb; //arbol binario de busqueda que almacena las claves

	/**
	 * Constructor
	 * @param c Comparador de claves
	 */
	public MapeoConABB(Comparator<K> c) {
		abb = new ABB<K, V>(c);
	}

	public V put(K k, V v) {
		return abb.insertar(new Entrada<K, V>(k, v));
	}

	
	public Iterable<Entry<K, V>> entries() {
		return abb.entries();
	}
	
	public V get(K key) {
		try {
			return abb.find(key).getValue();
		} catch (InvalidKeyException e) {
			return null;
		}
	}

	
	public boolean isEmpty() {
		return abb.isEmpty();
	}

	
	public Iterable<K> keys() {
		return abb.keys();
	}

	
	public V remove(K key) {
		try {
			return abb.remove(key);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	
	public int size() {
		return abb.size();
	}

	
	public Iterable<V> values() {
		return abb.values();
	}

	public String toString() {
		return abb.toString();
	}
}
