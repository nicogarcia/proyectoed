public class Lista<E> implements PositionList<E> {

	/**
	 * @param args
	 */
	private Position<E> head;
	private Position<E> tail;
	private Position<E> current;
	private int size;

	/**
	 * Devuelve el tamaño de la lista
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
			throw new EmptyListException("Lista::last():: La lista esta vacía");
		return tail;
	}

}
