package GeneralTree;

import TDALista.*;

public class TNode<E> implements Position<E> {
	protected TNode<E> parent; //Nodo padre
	protected PositionList<TNode<E>> children;//Lista de hijos
	private E element;// Elemento que almacena el nodo

	/**
	 * Constructor Crea un nodo con un padre y una lista de hijos
	 * 
	 * @param parent
	 *            Nodo Padre
	 * @param children
	 *            Lista de hijos
	 */
	public TNode(TNode<E> parent, PositionList<TNode<E>> children) {
		this.parent = parent;
		this.children = children;
	}

	/**
	 * Constructor Crea un nodo sin hijos
	 * 
	 * @param parent
	 *            Nodo Padre
	 */
	public TNode(TNode<E> parent) {
		this(parent, null);
	}

	public void caca() {

	}

	// FIXME esta bien este javadoc?
	/**
	 * Devuelve el elemento que almacena el nodo
	 */
	public E element() {
		return element;
	}

	/**
	 * Devuelve el padre del nodo
	 * 
	 * @return Padre del nodo
	 */
	public TNode<E> getParent() {
		return parent;
	}

	/**
	 * Devuelve una lista nodos que representan a los hijos
	 * 
	 * @return lista de hijos
	 */
	public PositionList<TNode<E>> getChildren() {
		return children;
	}

	/**
	 * Establece el padre del nodo
	 * 
	 * @param parent
	 *            Nuevo padre del nodo
	 */
	public void setParent(TNode<E> parent) {
		this.parent = parent;
	}

	/**
	 * Establece la lista de hijos
	 * 
	 * @param children
	 *            Lista de hijos
	 */
	public void setChildren(PositionList<TNode<E>> children) {
		this.children = children;
	}

	/**
	 * Establece el elemento que almacena el nodo
	 * 
	 * @param e
	 *            Elemento nuevo
	 * @return Elemento previamente almacenado
	 */
	public E setElement(E e) {
		E toReturn = element();
		element = e;
		return toReturn;
	}
}
