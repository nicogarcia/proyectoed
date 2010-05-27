package GeneralTree;

import java.awt.geom.Point2D;

import TDALista.*;

/**
 * Clase nodo utilizada para modelar en comportamiento de un arbol de tipo generico E.
 * @author Martin Schiaffino [93718] 
 * @author Nicolas Garcia [9307]
 *
 * @param <E>
 */
public class TNode<E> implements Position<E> {
	protected TNode<E> parent; // Nodo padre
	protected PositionList<TNode<E>> children;// Lista de hijos
	private E element;// Elemento que almacena el nodo
	private Point2D corner; // Este atributo se utiliza para graficar el arbol,

	// no afecta el funcionamiento del TDAArbol

	/**
	 * Constructor Crea un nodo con un padre y una lista de hijos
	 * 
	 * @param parent
	 *            Nodo Padre
	 * @param children
	 *            Lista de hijos
	 */
	public TNode(TNode<E> parent, PositionList<TNode<E>> children, E element) {
		this.parent = parent;
		if (children == null)
			this.children = new Lista<TNode<E>>();
		this.element = element;
		corner = null;
	}

	/**
	 * Constructor Crea un nodo sin hijos
	 * 
	 * @param parent
	 *            Nodo Padre
	 */
	public TNode(TNode<E> parent, E element) {
		this(parent, null, element);
	}

	/**
	 * Devuelve el elemento que almacena el nodo
	 * 
	 * @return Elemento almacenado en el nodo
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

	/**
	 * Getter del corner
	 * @return Ubicación del nodo en la representacion grafica.
	 */
	public Point2D getCorner() {
		return corner;
	}

	/**
	 * Setter del corner.
	 * @param corner Ubicación del nodo en la representacion grafica.
	 */
	public void setCorner(Point2D corner) {
		this.corner = corner;
	}
}
