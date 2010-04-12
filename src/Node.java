/** Node of a singly linked list of strings. */
public class Node<E> implements Position<E> {
	private E element; // we assume elements are character strings
	private Node<E> next;

	/** Creates a node with the given element and next node. */
	public Node(E e, Node<E> n) {
		element = e;
		next = n;
	}

	/** Returns the element of this node. */
	public E element() {
		return element;
	}

	/** Returns the next node of this node. */
	public Node<E> getNext() {
		return next;
	}

	// Modifier methods:
	/** Sets the element of this node. */
	public void setElement(E newElem) {
		element = newElem;
	}

	/** Sets the next node of this node. */
	public void setNext(Node<E> newNext) {
		next = newNext;
	}
}