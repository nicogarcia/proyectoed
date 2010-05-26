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

public class ABB<K, V> {
	protected NodoABB<Entry<K, V>> raiz;
	protected Comparator<K> comp;
	protected int size;

	public ABB(Comparator<K> comp) {
		size = 1;
		this.comp = comp;
		raiz = new NodoABB<Entry<K, V>>();
	}

	public boolean isEmpty() {
		return raiz == null;
	}

	public int size() {
		return size;
	}

	public PositionList<Position<Entry<K, V>>> positions() {
		PositionList<Position<Entry<K, V>>> lista = new Lista<Position<Entry<K, V>>>();
		preOrderPositions(lista, raiz);
		return lista;
	}

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

	public PositionList<K> keys() {
		PositionList<K> lista = new Lista<K>();
		for (Position<Entry<K, V>> pos : positions())
			lista.addLast(pos.element().getKey());
		return lista;
	}

	public PositionList<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> lista = new Lista<Entry<K, V>>();
		for (Position<Entry<K, V>> pos : positions())
			lista.addLast(pos.element());
		return lista;
	}

	public PositionList<V> values() {
		PositionList<V> lista = new Lista<V>();
		for (Position<Entry<K, V>> pos : positions())
			lista.addLast(pos.element().getValue());
		return lista;
	}

	public K key(Position<Entry<K, V>> position) {
		return position.element().getKey();
	}

	public V value(Position<Entry<K, V>> position) {
		return position.element().getValue();
	}

	public Entry<K, V> entry(Position<Entry<K, V>> position) {
		return position.element();
	}

	public V insertar(Entry<K, V> e) {
		return insertAux(raiz, e);
	}

	private V insertAux(NodoABB<Entry<K, V>> v, Entry<K, V> e) {
		if (v.element() == null) {
			// llegue a una hoja
			v.setElement(e);
			size++;
			// creo hijos
			// v.setLeft(new NodoABB<Entry<K, V>>());
			// v.setRight(new NodoABB<Entry<K, V>>());
			// v.getLeft().setParent(v);
			// v.getRight().setParent(v);
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

	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Position<Entry<K, V>> keypos;
		try {
			keypos = treeSearch(key, raiz);
			// TODO En el libro aca hay un actionPos=keypos
			if (isInternal(keypos))
				return entry(keypos);
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// TODO PREGUNTAR LA ADVERTENCIA DEL CASTING
	public Position<Entry<K, V>> raiz() {
		return raiz;
	}

	private K checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException(
					"ABB::checkKey():: La clave es invalida.");
		else
			return key;
	}

	// FIXME Que hacemos con las excepciones??
	public Position<Entry<K, V>> treeSearch(K key, Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		if (isExternal(pos))
			return null;
		else {
			K curKey = key(pos);
			int comparador = comp.compare(key, curKey);
			if (comparador < 0)
				treeSearch(key, left(pos));
			else if (comparador > 0)
				treeSearch(key, right(pos));
			return pos;
		}
	}

	private Position<Entry<K, V>> right(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		NodoABB<Entry<K, V>> nodo = checkPosition(pos);
		return (Position<Entry<K, V>>) nodo.getRight();
	}

	public boolean isExternal(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		return ((left(pos) == null) && (right(pos) == null));
	}

	private Position<Entry<K, V>> left(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		NodoABB<Entry<K, V>> nodo = checkPosition(pos);
		return (Position<Entry<K, V>>) nodo.getLeft();
	}

	public boolean isInternal(Position<Entry<K, V>> pos)
			throws InvalidPositionException {
		return !isExternal(pos);
	}

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

	public V remove(K key) throws ItemNotFoundException {
		return remove(key, raiz).element().getValue();
	}

	protected NodoABB<Entry<K, V>> remove(K key, NodoABB<Entry<K, V>> nodo)
			throws ItemNotFoundException {
		if (nodo == null)
			throw new ItemNotFoundException(key.toString());// FIXME CAMBIAR
		// MENSAJE
		if (comp.compare(key, nodo.element().getKey()) < 0)
			nodo.setLeft(remove(key, nodo.getLeft()));
		else if (comp.compare(key, nodo.element().getKey()) > 0)
			nodo.setRight(remove(key, nodo.getRight()));
		else if (nodo.getLeft() != null && nodo.getRight() != null) // Two
		// children
		{
			nodo.setElement(findMin(nodo.getRight()).element());
			nodo.setRight(removeMin(nodo.getRight()));
		} else
			nodo = (nodo.getLeft() != null) ? nodo.getLeft() : nodo.getRight();
		size--;
		return nodo;
	}

	protected NodoABB<Entry<K, V>> removeMin(NodoABB<Entry<K, V>> nodo)
			throws ItemNotFoundException {
		if (nodo == null)
			throw new ItemNotFoundException("La clave no existe");// FIXME
		// CAMBIAR
		// MENSAJE
		else if (nodo.getLeft() != null) {
			nodo.setLeft(removeMin(nodo.getLeft()));
			return nodo;
		} else
			return nodo.getRight();
	}

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
