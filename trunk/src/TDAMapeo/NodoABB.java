package TDAMapeo;

import TDALista.Position;

public class NodoABB<E> implements Position<E> {
	protected NodoABB<E> padre; //almacena el padre del nodo
	protected E rotulo;//almacena el rotulo del nodo
	protected NodoABB<E> left, right;//almacenan el hijo izquierdo y derecho
/**
 * Constructor
 */
	public NodoABB() {
		padre = left = right = null;
		rotulo = null;
	}

	public E element() {
		return rotulo;
	}
/**
 * Retorna el hijo izquierdo del nodo
 * @return Hijo izquierdo del nodo
 */
	public NodoABB<E> getLeft() {
		return left;
	}
/**
 * Retorna el hijo derecho del nodo
 * @return Hijo derecho del nodo
 */
	public NodoABB<E> getRight() {
		return right;
	}
/**
 * Retorna el padre del nodo
 * @return Padre del nodo
 */
	public NodoABB<E> getParent() {
		return padre;
	}
/**
 * Establece el padre del nodo
 * @param parent Nuevo padre
 */
	public void setParent(NodoABB<E> parent) {
		padre = parent;
	}
/**
 * Establece el hijo derecho del nodo
 * @param derecho Nuevo hijo derecho
 */
	public void setRight(NodoABB<E> derecho) {
		right = derecho;
	}
/**
 * Establece el nodo izquierdo del nodo
 * @param izquierdo Nuevo hijo izquierdo
 */	
	public void setLeft(NodoABB<E> izquierdo) {
		left = izquierdo;
	}
/**
 * Establece el elemento del nodo
 * @param e Nuevo elemento
 */
	public void setElement(E e) {
		rotulo = e;
	}
/**
 * Retorna si el nodo tiene hijo izquierdo
 * @return true si tiene hijo izquierdo, false en caso contrario
 */
	public boolean hasLeft() {
		return left != null;
	}
/**
 * Retorna si el nodo tiene hijo derecho
 * @return true si tiene hijo derecho, false en caso contrario
 */
	public boolean hasRight() {
		return right != null;
	}
}
