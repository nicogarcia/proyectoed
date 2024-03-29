package TDAPila;

/**
 * Clase nodo utilizado para modelar una pila con estructura enlazada de tipo E.
 * @author Martin Schiaffino [93718] 
 * @author Nicolas Garcia [9307]
 *
 * @param <E> Tipo generico de objetos almacenados en la pila.
 */
public class Node<E> {
	
	//Atributos.
	private E element; 
	private Node<E> next;

	
	/**
	 * Crea un nodo con el elemento dado y el siguiente nodo.
	 * 
	 * @param e Elemento del nuevo nodo.
	 * @param n Nodo siguiente del nuevo nodo.
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

	/// Comandos para modificaciones.
	
	
	/**
	 * Setea el elemento del nodo.
	 * 
	 * @param newElem Nuevo elemento a setear.
	 */
	public void setElement(E newElem) {
		element = newElem;
	}

	/* Sets the next node of this node. */
	/**
	 * Setea el nodo siguiente del nodo que recibe el mensaje
	 * 
	 * @param newNext Nuevo nodo siguiente a setear.
	 */
	public void setNext(Node<E> newNext) {
		next = newNext;
	}
}