package TDALista;
<<<<<<< .mine
import java.util.Iterator;

=======
import Excepciones.*;

>>>>>>> .r21
public class Lista<E> implements PositionList<E> {

	/**
	 * @param args
	 */
<<<<<<< .mine
	private Node<E> head;
	private Node<E> tail;
	private Node<E> current;
=======
	private Node<E> head;
	private Node<E> tail;
>>>>>>> .r21
	private int size;

	/**
	 * Devuelve el tama�o de la lista
	 */
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("Lista:: first() :List is empty");
		return head;
	}

	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException(
					"Lista::last():: La lista esta vac�a");
		return tail;
	}
<<<<<<< .mine

	public Node<E> valido(Position<E> p) throws InvalidPositionException {
		// agregar mensaje!!
		if (p == null)
			throw new InvalidPositionException("agregar mensaje");
		try {
			Node<E> aux = (Node<E>) p;
			return aux;
		} catch (ClassCastException e) {
			//agregar mensaje!!
			throw new InvalidPositionException("agregar mensaje");
		}

	}

	@Override
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFirst(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(E e) {
		// TODO Auto-generated method stub
		
=======

	public Position<E> next(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		Node<E> nodo = checkposition(p);
		if (nodo == tail)
			throw new BoundaryViolationException(
					"Lista::next():: La posicion recibida es la ultima de la lista.");
		Node<E> next = nodo.getNext();
		return next;
	}

	private Node<E> checkposition(Position<E> p)
			throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException(
					"Lista::checkposition():: La posicion pasada es nula.");
		try {
			Node<E> temp = (Node<E>) p;
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException(
					"Lista::checkposition():: La posicion es de tipo incorrecto.");
		}
	}

	public Position<E> prev(Position<E> p) throws BoundaryViolationException,
			InvalidPositionException {
		Node<E> nodo = checkposition(p);
		if (nodo == head)
			throw new BoundaryViolationException(
					"Lista::prev():: La posicion recibida es la primera de la lista.");
		Node<E> aux = head;
		while (aux.getNext() != nodo)
			aux = aux.getNext();
		return aux;
	}

	public void addFirst(E e) {
		Node<E> nuevo = new Node<E>(e, head);
		head = nuevo;
		if (isEmpty())
			tail = nuevo;
		size++;
	}

	public void addLast(E e) {
		Node<E> nuevo = new Node<E>(e, null);
		tail.setNext(nuevo);
		tail = nuevo;
		if (isEmpty())
			head = nuevo;
		size++;
	}

	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		Node<E> nodo = checkposition(p);
		Node<E> nuevo = new Node<E>(e, nodo.getNext());
		if (nodo == tail)
			tail = nuevo;
		nodo.setNext(nuevo);
		size++;
	}

	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		Node<E> nodo = checkposition(p);
		Node<E> nuevo = new Node<E>(e, nodo);
		if (nodo == head)
			head = nuevo;
		else {
			try {
				((Node<E>) prev(nodo)).setNext(nuevo);
			} catch (BoundaryViolationException exc) {
				System.out
						.println("Lista::addBefore():: Esta excepcion no deberia llegar a dispararse.");
			}
		}
		size++;
>>>>>>> .r21
	}

<<<<<<< .mine
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

=======
	public E remove(Position<E> p) throws InvalidPositionException {
		Node<E> nodo = checkposition(p);
		if (tail == head) {
			head = tail = null;
		} else if (nodo == head) {
			head = nodo.getNext();
			//nodo.setNext(null);
		} else
			try {
				if (nodo == tail) {
					Node<E> anterior = (Node<E>) prev(nodo);
					tail = anterior;
					anterior.setNext(null);
				} else {
					Node<E> anterior = (Node<E>) prev(nodo);
					anterior.setNext(nodo.getNext());
				}
			} catch (BoundaryViolationException e) {
				System.out
						.println("Lista::remove():: La excepcion no deberia haberse disparado.");
			}
		size--;
		return nodo.element();
	}

	public E set(Position<E> p, E e) throws InvalidPositionException {
		Node<E> nodo = checkposition(p);
		E ret = nodo.element();
		nodo.setElement(e);
		return ret;
	}
	//TODO Borrar este metodo! xq no lo piden!
	public String toString() {
		String ret = "[";
		Node<E> nodo = head;
		ret += nodo.element();
		nodo = nodo.getNext();
		while (nodo != null) {
			ret += ", " + nodo.element();
			nodo = nodo.getNext();
		}
		ret += "]";
		return ret;
	}
>>>>>>> .r21
}
