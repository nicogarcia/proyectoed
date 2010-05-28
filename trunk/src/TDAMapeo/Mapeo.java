package TDAMapeo;

import java.util.Map.Entry;

public interface Mapeo<K, V> {
	/**
	 * Retorna el tamaño del mapeo
	 * @return tamaño del mapeo
	 */
	public int size();
	/**
	 * Retorna si el mapeo esta vacio
	 * @return true si esta vacio, false si no lo esta
	 */
	public boolean isEmpty();
	/**
	 * Retorna el valor asociado a una clave
	 * @param key Clave asociada al valor buscado
	 * @return Valor asociado a la clave
	 */
	public V get(K key);
	/**
	 * Inserta una clave y un valor en el mapeo
	 * @param key Clave a insertar
	 * @param value Valor a insertar
	 * @return Valor previamente almacenado, nulo si no existe la clave
	 */
	public V put(K key, V value);
	/**
	 * Borra la entrada que contiene a la clave
	 * @param key Clave de referencia
	 * @return Valor asociado a la clave
	 */
	public V remove(K key);
	/**
	 * Retorna una coleccion iterable de claves
	 * @return Coleccion iterable de claves
	 */
	public Iterable<K> keys();
	/**
	 * Retorna una coleccion iterable de valores
	 * @return Coleccion iterable de valores
	 */
	public Iterable<V> values();
	/**
	 * Retorna una coleccion iterable de entradas
	 * @return Coleccion iterable de entradas
	 */
	public Iterable<Entry<K,V>> entries();

}
