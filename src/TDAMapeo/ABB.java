package TDAMapeo;

import java.util.Comparator;

//FIXME COPIADO EN CLASE!
import java.util.Map.Entry;

import TDALista.Position;

import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import GeneralTree.TNode;

public class ABB<K, V> {
	protected NodoABB<Entrada<K, V>> raiz;
	protected Comparator<K> comp;
	protected int size;

	public ABB(Comparator<K> comp) {
		size = 0;
		this.comp = comp;
		raiz = new NodoABB<Entry<K, V>>();
	}

	public K key(Position<Entrada<K, V>> position) {
		return position.element().getKey();
	}

	public V value(Position<Entrada<K, V>> position) {
		return position.element().getValue();
	}

	public Entrada<K, V> entry(Position<Entrada<K, V>> position) {
		return position.element();
	}

	public V insertar(Entry<K, V> e) {
		return insertAux(raiz, e);
	}

	private V insertAux(NodoABB<Entry<K, V>> v, Entry<K, V> e) {
		if (v.element() == null) {
			// llegue a una hoja
			v.setElement(e);
			// creo hijos
			v.setLeft(new NodoABB<Entry<K, V>>());
			v.setRight(new NodoABB<Entry<K, V>>());
			v.getLeft().setParent(v);
			v.getRight().setParent(v);
			return null;
		} else {
			int comparacion = comp.compare(e.getKey(), v.element().getKey());
			if (comparacion == 0) {
				// la clave ya estaba y actualizo el valor
				Entry<K, V> viejaEntrada = v.element();
				v.setElement(e);
				return viejaEntrada.getValue();
			} else if (comparacion < 0)
				return insertAux(v.getLeft(), e);
			else
				return insertAux(v.getRight(), e);
		}
	}

	public Entrada<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Position<Entrada<K, V>> keypos = treeSearch(key, raiz());

		return null;
	}

	// TODO PREGUNTAR LA ADVERTENCIA DEL CASTING
	private Position<Entrada<K, V>> raiz() {
		return (Position<Entrada<K, V>>) raiz;
	}

	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException(
					"ABB::checkKey():: La clave es invalida.");
	}

	// FIXME Que hacemos con las excepciones??
	public Position<Entrada<K, V>> treeSearch(K key, Position<Entrada<K, V>> pos) {
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

	private Position<Entrada<K, V>> right(Position<Entrada<K, V>> pos)
			throws InvalidPositionException {
		NodoABB<Entrada<K, V>> nodo = checkPosition(pos);
		return (Position<Entrada<K, V>>) nodo.getRight();
	}

	public boolean isExternal(Position<Entrada<K, V>> pos)
			throws InvalidPositionException {
		return ((left(pos) == null) && (right(pos) == null));
	}

	private Position<Entrada<K, V>> left(Position<Entrada<K, V>> pos)
			throws InvalidPositionException {
		NodoABB<Entrada<K, V>> nodo = checkPosition(pos);
		return (Position<Entrada<K, V>>) nodo.getLeft();
	}

	public boolean isInternal(Position<Entrada<K, V>> pos)
			throws InvalidPositionException {
		return !isExternal(pos);
	}

	private NodoABB<Entrada<K, V>> checkPosition(Position<Entrada<K, V>> pos)
			throws InvalidPositionException {
		if (pos == null)
			throw new InvalidPositionException(
					"ABB::checkPosition():: La posicion es nula.");
		try {
			NodoABB<Entrada<K, V>> nodo = (NodoABB<Entrada<K, V>>) pos;
			return nodo;
		} catch (ClassCastException e) {
			throw new InvalidPositionException(
					"ABB:: checkPosition():: El tipo de la posicion dada es invalido.");
		}
	}
}
