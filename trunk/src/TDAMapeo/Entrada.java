package TDAMapeo;
import java.util.Map.Entry;

//FIXME COPIADO EN CLASE!

public class Entrada<K, V> implements Entry<K, V> {
	private K key;
	private V value;

	public Entrada(K k, V V) {
		key = k;
		value = V;
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

}
