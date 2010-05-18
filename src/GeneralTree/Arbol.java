package GeneralTree;

import java.util.Iterator;

import Excepciones.*;
import TDALista.*;

public class Arbol<E> implements GeneralTree<E> {

	protected TNode<E> root;

	public Arbol() {

	}

	public Iterable<Position<E>> children(Position<E> v)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo=checkposition(v);
		
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException(
					"Arbol:: root():: El arbol esta vacio.");
		return root;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
