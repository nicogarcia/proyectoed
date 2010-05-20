package TDAMapeo;
//FIXME COPIADO EN CLASE!
public class NodoABB<E> implements Position<E>{
	protected NodoABB<E> padre;
	protected E rotulo;
	protected NodoABB<E> left, right;

	public NodoABB() {
		padre = left = right = null;
		rotulo = null;
	}

	public E element() {
		return rotulo;
	}

	public NodoABB<E> getLeft() {
		return left;
	}

	public NodoABB<E> getRight() {
		return right;
	}

	public NodoABB<E> getParent() {
		return padre;
	}

	public void setParent(NodoABB<E> parent) {
		padre = parent;
	}

	public void setRight(NodoABB<E> derecho) {
		right = derecho;
	}

	public void setLeft(NodoABB<E> izquierdo) {
		left = izquierdo;
	}

	public void setElement(E e) {
		rotulo = e;
	}
}
