package TDAMapeo;

import java.util.Map.Entry;

//FIXME COPIADO EN CLASE! FALTA EL JAVADOC DE CLASE!

public class Entrada<K, V> implements Entry<K, V> {
	private K key;//clave que almacena la entrada
	private V value;//valor que almacena la entrada
/**
 * Constructor
 * @param k Clave a almacenar
 * @param V Valor a almacenar
 */
	public Entrada(K k, V v) {
		key = k;
		value = v;
	}
	
	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V arg0) {
		V temp = value;
		value = arg0;
		return temp;
	}

	public String toString() {
		return "[ " + key + " - " + value + " ]";
	}
}
