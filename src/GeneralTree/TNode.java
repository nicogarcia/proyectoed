package GeneralTree;

import TDALista.*;

public class TNode<E> implements Position<E> {
	protected TNode<E> parent;
	protected PositionList<TNode<E>> children;
	private E element;

	public TNode(TNode<E> parent, PositionList<TNode<E>> children) {
		this.parent = parent;
		this.children = children;
	}

	// TODO AGREGAR ALGO AL MANUAL SOBRE ESTO
	public TNode(TNode<E> parent) {
		this(parent, null);
	}

	//
	public E element() {
		return element;
	}

	public TNode<E> getParent() {
		return parent;
	}

	public PositionList<TNode<E>> getChildren() {
		return children;
	}

	public void setParent(TNode<E> parent) {
		this.parent = parent;
	}

	public void setChildren(PositionList<TNode<E>> children) {
		this.children = children;
	}

}
