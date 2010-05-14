package TDAPila;

import Excepciones.EmptyStackException;

public class Pila<E> implements Stack<E> {

	private Node<E> head;
	private int size;

	// Constructor
	public Pila() {
		head = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException(
					"Pila :: pop() :: La pila está vacía.");
		E toReturn = head.element();
		head = head.getNext();
		size--;
		return toReturn;
	}

	public void push(E element) {
		Node<E> nuevo = new Node<E>(element, head);
		head = nuevo;
		size++;
	}

	public int size() {
		return size;
	}

	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException(
					"Pila :: top() :: La pila está vacía.");
		return head.element();
	}

	// TODO ELIMINAR ESTE MÉTODO DE PRUEBA
	public String toString() {
		Node<E> n;
		String st = "[ ";
		n = head.getNext();
		st += head.element();
		while (n != null) {
			st += ", " + n.element();
			n = n.getNext();
		}
		return st + " ]";
	}

}
