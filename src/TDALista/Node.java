package TDALista;

public class Node<E> implements Position<E> {
	
	//Atributos.
	private E element;
	private Node<E> next;

	/**
	 * Crea un nodo con el elemento dado y el siguiente nodo.
	 * 
	 * @param e
	 *            Elemento del nuevo nodo.
	 * @param n
	 *            Nodo siguiente del nuevo nodo.
	 */
	public Node(E e, Node<E> n) {
		element = e;
		next = n;
	}

	/**
	 * Retorna el elemento del nodo.
	 * 
	 * @return El elemento del nodo
	 */
	public E element() {
		return element;
	}

	/**
	 * Retorna el siguiente nodo del nodo que recibe el mensaje.
	 * 
	 * @return El siguiente nodo del nodo que recibe el mensaje.
	 */
	public Node<E> getNext() {
		return next;
	}

	// Comandos para modificaciones.
	
	
	/**
	 * Setea el elemento del nodo.
	 * 
	 * @param newElem Nuevo elemento a setear.
	 */
	public void setElement(E newElem) {
		element = newElem;
	}

	/**
	 * Setea el nodo siguiente del nodo que recibe el mensaje
	 * 
	 * @param newNext Nuevo nodo siguiente a setear.
	 */
	public void setNext(Node<E> newNext) {
		next = newNext;
	}
}