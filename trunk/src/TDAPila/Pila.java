package TDAPila;

import Excepciones.EmptyStackException;

public class Pila<E> implements Stack<E> {

	//Atributos
	private Node<E> head;
	private int size;

	/**
	 * Crea una nueva pila vacía.
	 */
	public Pila() {
		head = null;
		size = 0;
	}

	/**
	 * Consulta si la pila está vacía.	
	 * 
	 * @return true si la pila está vacía, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Elimina y retorna el elemento en el tope de la pila.
	 * 
	 * @return El elemento al tope de la pila.
	 */
	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila :: pop() :: La pila esta vacia.");
		E toReturn = head.element();
		head = head.getNext();
		size--;
		return toReturn;
	}

	/**
	 * Inserta un elemento en el tope de la pila.
	 * 
	 * @param element Elemento a insertar.
	 */
	public void push(E element) {
		Node<E> nuevo = new Node<E>(element, head);
		head = nuevo;
		size++;
	}

	/**
	 * Retorna el tamaño de la pila.
	 * 
	 * @return Tamaño de la pila.
	 */
	public int size() {
		return size;
	}

	/**
	 * Inspecciona el elemento en el tope de la pila.
	 * 
	 * @return Elemento en el tope de la pila.
	 */
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException(
					"Pila :: top() :: La pila esta vacia.");
		return head.element();
	}

	// TODO ELIMINAR ESTE METODO DE PRUEBA
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
