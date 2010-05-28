package TDAMapeo;

import java.util.Comparator;

//FIXME COPIADO EN CLASE!
import java.util.Map.Entry;

import TDALista.Lista;
import TDALista.Position;
import TDALista.PositionList;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import Excepciones.ItemNotFoundException;
/**
 * Clase que define el comportamiento de un arbol binario 
 * @author Martin
 *
 * @param <K> Tipo generico de las claves
 * @param <V> Tipo generico de los valores
 */
public class ABB<K, V> {
	protected NodoABB<Entry<K, V>> raiz; //referencia a la raiz del arbol
	protected Comparator<K> comp; //comparador para las claves
	protected int size; //tamaño del arbol

	/**
	 * Constructor
	 * @param comp	comparador para las claves
	 */
	public ABB(Comparator<K> comp) {
		size = 0;
		this.comp = comp;
		raiz = new NodoABB<Entry<K, V>>();
	}
/**
 * Retorna si el arbol esta vacio
 * @return	true si el arbol esta vacio, false si no lo esta
 */
	public boolean isEmpty() {
		return size == 0;
	}
/**
 * Retorna la cantidad de elementos del arbol
 * @return cantidad de elementos
 */
	public int size() {
		return size;
	}

	/**
	 * Retorna una lista con las posiciones del arbol
	 * @return lista con las posiciones del arbol
	 */
	public PositionList<Position<Entry<K, V>>> positions() {
		PositionList<Position<Entry<K, V>>> lista = new Lista<Position<Entry<K, V>>>();
		preOrderPositions(lista, raiz);
		return lista;
	}
/**
 * Inserta las posiciones del arbol en una lista, realizando un recorrido en preOrden
 * @param lista Lista a modificar
 * @param nodo	Nodo a insertar
 */
	public void preOrderPositions(PositionList<Position<Entry<K, V>>> lista,
			NodoABB<Entry<K, V>> nodo) {
		if (!isEmpty()) {
			lista.addLast(nodo);
			if (nodo.hasLeft())
				preOrderPositions(lista, nodo.getLeft());
			if (nodo.hasRight())
				preOrderPositions(lista, nodo.getRight());
		}
	}
/**
 * Retorna una lista con todas las claves del arbol
 * @return lista con todas las claves del arbol
 */
	public PositionList<K> keys() {
		PositionList<K> lista = new Lista<K>();
		for (Position<Entry<K, V>> pos : positions())
			lista.addLast(pos.element().getKey());
		return lista;
	}
/**
 * Retorna una lista con todas las entradas del arbol
 * @return lista con todas las entradas del arbol
 */
	public PositionList<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> lista = new Lista<Entry<K, V>>();
		for (Position<Entry<K, V>> pos : positions())
			lista.addLast(pos.element());
		return lista;
	}
/**
 * Retorna una lista con todos los valores del arbol
 * @return lista con todos los valores el arbol
 */
	public PositionList<V> values() {
		PositionList<V> lista = new Lista<V>();
		for (Position<Entry<K, V>> pos : positions())
			lista.addLast(pos.element().getValue());
		return lista;
	}
/**
 * Retorna la clave de una entrada almacenada en una posicion
 * @param position Posicion que contiene a la entrada
 * @return	clave almacenada en la entrada
 */
	public K key(Position<Entry<K, V>> position) {
		return position.element().getKey();
	}
/**
 * Retorna el valor de una entrada almacenado en una posicion
 * @param position	Posicion que contiene a la entrada
 * @return valor almacenado en la entrada
 */
	public V value(Position<Entry<K, V>> position) {
		return position.element().getValue();
	}
/**
 * Retorna la entrada almacenada en una posicion
 * @param position Posicion que almacena a la entrada
 * @return Entrada almacenada en la posicion
 */
	public Entry<K, V> entry(Position<Entry<K, V>> position) {
		return position.element();
	}
/**
 * Inserta una entrada en el arbol
 * @param e Entrada a insertar
 * @return Valor almacenado en la entrada si ya existia, sino nulo
 */
	public V insertar(Entry<K, V> e) {
		return insertAux(raiz, e);
	}
/**
 * Metodo auxiliar recursivo para insertar entradas
 * @param v Nodo donde se insertara la entrada
 * @param e Entrada a insertar
 * @return Valor almacenado en la entrada si ya existia, sino nulo
 */
	private V insertAux(NodoABB<Entry<K, V>> v, Entry<K, V> e) {
		if (v.element() == null) {
			// llegue a una hoja
			v.setElement(e);
			size++;
			return null;
		} else {
			int comparacion = comp.compare(e.getKey(), v.element().getKey());
			if (comparacion == 0) {
				// la clave ya estaba y actualizo el valor
				Entry<K, V> viejaEntrada = v.element();
				v.setElement(e);
				return viejaEntrada.getValue();
			} else if (comparacion < 0) {
				if (!v.hasLeft())
					v.setLeft(new NodoABB<Entry<K, V>>());
				return insertAux(v.getLeft(), e);
			} else {
				if (!v.hasRight())
					v.setRight(new NodoABB<Entry<K, V>>());
				return insertAux(v.getRight(), e);
			}
		}
	}
/**
 * Busca un entrada a partir de su clave
 * @param key Clave de referencia
 * @return Entrada cuya clave es K
 * @throws InvalidKeyException Si la clave es invalida
 */
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Position<Entry<K, V>> keypos;
		try {
			keypos = treeSearch(key, raiz);
			// TODO En el libro aca hay un actionPos=keypos
			//if (isInternal(keypos))
				return entry(keypos);
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

/**
 * Retorna la posicion de la raiz
 * @return Posicion de la raiz
 */
	public Position<Entry<K, V>> raiz() {
		return raiz;
	}
/**
 * Verifica que la clave sea valida
 * @param key Clave a evaluar
 * @return Clave
 * @throws InvalidKeyException Si la clave es invalida
 */
	private K checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException(
					"ABB::checkKey():: La clave es invalida.");
		else
			return key;
	}
/**
 * Busca la entrada que contiene a la clave
 * @param key Clave a buscar
 * @param pos Posicion de inicio de busqueda
 * @return La posicion que contiene a la clave
 * @throws InvalidPositionException Si la posicion recibida es invalida
 */
	public Position<Entry<K, V>> treeSearch(K key, Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		if (isExternal(pos))
			return pos;
		else {
			K curKey = key(pos);
			int comparador = comp.compare(key, curKey);
			if (comparador < 0)
				return treeSearch(key, left(pos));
			else if (comparador > 0)
				return treeSearch(key, right(pos));
			return pos;
		}

	}
/**
 * Retorna la posicion del hijo derecho de un nodo
 * @param pos Posicion del nodo
 * @return Posicion del hijo derecho del nodo
 * @throws InvalidPositionException Si la posicion es invalida
 */
	private Position<Entry<K, V>> right(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		NodoABB<Entry<K, V>> nodo = checkPosition(pos);
		return (Position<Entry<K, V>>) nodo.getRight();
	}
/**
 * Retorna si la posicion es una hoja del arbol
 * @param pos Posicion a evaluar
 * @return true si la posicion es una hoja, falso en caso contrario
 * @throws InvalidPositionException Si la posicino recibida es invalida
 */
	public boolean isExternal(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		return ((left(pos) == null) && (right(pos) == null));
	}

	/**
	 * Retorna la posicion del hijo izquierdo de un nodo
	 * @param pos Posicion del nodo
	 * @return Posicion del hijo izquierdo del nodo
	 * @throws InvalidPositionException Si la posicion es invalida
	 */
	private Position<Entry<K, V>> left(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		NodoABB<Entry<K, V>> nodo = checkPosition(pos);
		return (Position<Entry<K, V>>) nodo.getLeft();
	}
/**
 * Retorna si el la posicion no es una hoja
 * @param pos Posicino a evaluar
 * @return true si no es una hoja, false en caso contrario
 * @throws InvalidPositionException
 */
	public boolean isInternal(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		return !isExternal(pos);
	}
/**
 * Verifica que la posicion sea valida y devuelve el nodo en esa posicion
 * @param pos Posicion a verificar
 * @return Nodo ubicado en la posicion
 * @throws InvalidPositionException
 */
	private NodoABB<Entry<K, V>> checkPosition(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		if (pos == null)
			throw new InvalidPositionException(
					"ABB::checkPosition():: La posicion es nula.");
		try {
			NodoABB<Entry<K, V>> nodo = (NodoABB<Entry<K, V>>) pos;
			return nodo;
		} catch (ClassCastException e) {
			throw new InvalidPositionException(
					"ABB:: checkPosition():: El tipo de la posicion dada es invalido.");
		}
	}
/**
 * Borra una entrada a partir de una clave
 * @param key Clave de referencia
 * @return Valor almacenado en la entrada
 * @throws ItemNotFoundException Si la entrada no fue encontrada
 */
	public V remove(K key) throws ItemNotFoundException {
		V value = remove(key, raiz).element().getValue();
		if (value!=null)
			size--;		
		return value;
	}
/**
 * Borra un nodo a partir de la clave
 * @param key Clave de referencia
 * @param nodo Nodo a partir del cual se borra
 * @return Nodo borrado
 * @throws ItemNotFoundException
 */
 //FIXME QUE ES EL NODO DE ARRIBA??
	protected NodoABB<Entry<K, V>> remove(K key, NodoABB<Entry<K, V>> nodo)
			throws ItemNotFoundException {
		if (nodo == null)
			throw new ItemNotFoundException("ABB::remove():: El nodo no fue encontrado");
		if (comp.compare(key, nodo.element().getKey()) < 0)
			nodo.setLeft(remove(key, nodo.getLeft()));
		else if (comp.compare(key, nodo.element().getKey()) > 0)
			nodo.setRight(remove(key, nodo.getRight()));
		else if (nodo.getLeft() != null && nodo.getRight() != null)
		{
			nodo.setElement(findMin(nodo.getRight()).element());
			nodo.setRight(removeMin(nodo.getRight()));
		} else
			nodo = (nodo.getLeft() != null) ? nodo.getLeft() : nodo.getRight();
		return nodo;
	}
/**
 * Borra el minimo nodo
 * @param nodo
 * @return
 * @throws ItemNotFoundException
 */
	//FIXME FALTA JAVADOC!!	
	protected NodoABB<Entry<K, V>> removeMin(NodoABB<Entry<K, V>> nodo)
			throws ItemNotFoundException {
		if (nodo == null)
			throw new ItemNotFoundException("El nodo a borrar no existe");
		else if (nodo.getLeft() != null) {
			nodo.setLeft(removeMin(nodo.getLeft()));
			return nodo;
		} else
			return nodo.getRight();
	}
/**
 * Busca el minimo de los hijos de un nodo
 * @param nodo Nodo superior
 * @return Nodo minimo
 */
	protected NodoABB<Entry<K, V>> findMin(NodoABB<Entry<K, V>> nodo) {
		if (nodo != null)
			while (nodo.getLeft() != null)
				nodo = nodo.getLeft();
		return nodo;
	}

	public String toString() {
		if (isEmpty())
			return "[ ]";
		else
			return ((Lista<Entry<K, V>>) entries()).toString();
	}
}
