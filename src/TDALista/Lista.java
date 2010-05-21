package TDALista;

//TODO Faltan javadocs
import java.util.Iterator;

import Excepciones.*;

public class Lista<E> implements PositionList<E> {

	/**
	 * @param args
	 */
	private Node<E> head;
	private int size;
	private Node<E> tail;

	public Lista() {
		head = tail = null;
		size = 0;
	}

	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		Node<E> nodo = checkPosition(p);
		Node<E> nuevo = new Node<E>(e, nodo.getNext());
		if (nodo == tail)
			tail = nuevo;
		nodo.setNext(nuevo);
		size++;
	}

	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		Node<E> nodo = checkPosition(p);
		Node<E> nuevo = new Node<E>(e, nodo);
		if (nodo == head)
			head = nuevo;
		else {
			try {
				((Node<E>) prev(nodo)).setNext(nuevo);
			} catch (BoundaryViolationException exc) {
				System.out
						.println("Lista::addBefore():: Esta excepcion no deberia llegar a dispararse.");
			}
		}
		size++;
	}

	public void addFirst(E e) {
		Node<E> nuevo = new Node<E>(e, head);
		head = nuevo;
		if (isEmpty())
			tail = nuevo;
		size++;
	}

	public void addLast(E e) {
		Node<E> nuevo = new Node<E>(e, null);
		if (isEmpty())
			head = tail = nuevo;
		else {
			tail.setNext(nuevo);
			tail = nuevo;
		}
		size++;
	}

	private Node<E> checkPosition(Position<E> p)
			throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException(
					"Lista::checkposition():: La posicion pasada es nula.");
		try {
			Node<E> temp = (Node<E>) p;
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException(
					"Lista::checkposition():: La posicion es de tipo incorrecto.");
		}
	}

	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("Lista:: first() :List is empty");
		return head;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<E> iterator() {
		return new ListIterator<E>(this);
	}

	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("Lista::last():: La lista esta vac�a");
		return tail;
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		Node<E> nodo = checkPosition(p);
		if (nodo == tail)
			throw new BoundaryViolationException(
					"Lista::next():: La posicion recibida es la ultima de la lista.");
		Node<E> next = nodo.getNext();
		return next;
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> P = new Lista<Position<E>>();
		try {
			if (!isEmpty()) {
				Position<E> p = first();
				while (true) {
					P.addLast(p);
					if (p == last())
						break;
					p = next(p);
				}
			}
		} catch (EmptyListException e) {
			System.out.println("Esta excepcion no deberia dispararse");

		} catch (InvalidPositionException e) {
			System.out.println("Esta excepcion no deberia dispararse");

		} catch (BoundaryViolationException e) {
			System.out.println("Esta excepcion no deberia dispararse");
		}
		return P;
	}

	public Position<E> prev(Position<E> p) throws BoundaryViolationException,
			InvalidPositionException {
		Node<E> nodo = checkPosition(p);
		if (nodo == head)
			throw new BoundaryViolationException(
					"Lista::prev():: La posicion recibida es la primera de la lista.");
		Node<E> aux = head;
		while (aux.getNext() != nodo)
			aux = aux.getNext();
		return aux;
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		Node<E> nodo = checkPosition(p);
		if (tail == head) {
			head = tail = null;
		} else if (nodo == head) {
			head = nodo.getNext();
			// nodo.setNext(null);
		} else
			try {
				if (nodo == tail) {
					Node<E> anterior = (Node<E>) prev(nodo);
					tail = anterior;
					anterior.setNext(null);
				} else {
					Node<E> anterior = (Node<E>) prev(nodo);
					anterior.setNext(nodo.getNext());
				}
			} catch (BoundaryViolationException e) {
				System.out
						.println("Lista::remove():: La excepcion no deberia haberse disparado.");
			}
		size--;
		return nodo.element();
	}

	public E set(Position<E> p, E e) throws InvalidPositionException {
		Node<E> nodo = checkPosition(p);
		E ret = nodo.element();
		nodo.setElement(e);
		return ret;
	}

	/**
	 * Devuelve el tama�o de la lista
	 */
	public int size() {
		return size;
	}

	// TODO Borrar este metodo! xq no lo piden!
	// FIXME NO ANDA!!
	public String toString() {
		String ret = "[";
		Node<E> nodo = head;
		ret += nodo.element();
		nodo = nodo.getNext();
		while (nodo != null) {
			ret += ", " + nodo.element();
			nodo = nodo.getNext();
		}
		ret += "]";
		return ret;
	}
}
