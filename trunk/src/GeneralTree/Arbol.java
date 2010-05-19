package GeneralTree;
//TODO Faltan javadocs

import java.util.Iterator;

import Excepciones.*;
import TDALista.*;

public class Arbol<E> implements GeneralTree<E> {

	protected TNode<E> root;
	protected int size;

	public Arbol() {
		root = null;
	}

	public Iterable<Position<E>> children(Position<E> v)
			throws InvalidPositionException {
		TNode<E> p = checkPosition(v);
		// TODO PREGUNTAR SI VA UN p==null
		Lista<Position<E>> lista = new Lista<Position<E>>();
		for (TNode<E> h : p.getChildren()) {
			lista.addLast(h);
		}
		return lista;
	}

	private TNode<E> checkPosition(Position<E> v)
			throws InvalidPositionException {
		if (v == null)
			throw new InvalidPositionException(
					"Arbol::checkposition():: La posicion es invalida.");
		try {
			TNode<E> nodo = (TNode<E>) v;
			return nodo;
		} catch (ClassCastException c) {
			throw new InvalidPositionException(
					"Arbol::checkposition():: El tipo de la posicion es invalido.");
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return (nodo.getChildren() == null);
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return (nodo.getChildren() != null);

	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return nodo == root;
	}

	public Iterator<E> iterator() {
		Lista<E> lista = new Lista<E>();
		for (Position<E> p : positions())
			lista.addLast(p.element());
		return new TreeIterator<E>(lista);
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		TNode<E> nodo = checkPosition(v);
		return nodo.getParent();
	}

	public Iterable<Position<E>> positions() {
		Lista<Position<E>> lista = new Lista<Position<E>>();
		preOrder(lista, root);
		return lista;
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		// TODO Consultar con Tincho (hecho por propia cuenta)
		return nodo.setElement(e);
	}
	
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException(
					"Arbol:: root():: El arbol esta vacio.");
		return root;
	}

	public int size() {
		return size;
	}

	private void preOrder(Lista<Position<E>> l, TNode<E> n) {
		l.addLast(n);
		for (TNode<E> h : n.getChildren())
			preOrder(l, h);
	}

	private void posOrder(Lista<Position<E>> l, TNode<E> n) {
		for (TNode<E> h : n.getChildren())
			preOrder(l, h);
		l.addLast(n);
	}
}
