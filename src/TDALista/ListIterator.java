package TDALista;
//TODO Insertar javadocs
import java.util.Iterator;

import Excepciones.*;

/**
 * Clase utilizado para iterar sequencialmente sobre una colección de objetos.
 * @param <E> Tipo de objeto generico que se itera.
 */
public class ListIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor;

	public ListIterator(PositionList<E> L) {
		list = L;
		try {
			cursor = (list.isEmpty() ? null : list.first());
		} catch (EmptyListException e) {
			System.out.println("Esta excepcion no deberia dispararse");
		}
	}

	/**
	 * Determina si hay mas elementos para iterar.
	 */
	public boolean hasNext() {
		return cursor != null;
	}

	/**
	 * Retorna el siguiente  el siguiente objeto en la iteración
	 */
	public E next() throws NoSuchElementException {
		if (cursor == null)
			throw new NoSuchElementException(
					"ListIterator::next():: No hay elemento siguiente.");
		E toReturn = cursor.element();

		try {
			cursor = ((cursor == list.last()) ? null : list.next(cursor));
		} catch (EmptyListException e) {
			System.out.println("Esta excepcion no deberia dispararse");

		} catch (InvalidPositionException e) {
			System.out.println("Esta excepcion no deberia dispararse");

		} catch (BoundaryViolationException e) {
			System.out.println("Esta excepcion no deberia dispararse");
		}
		return toReturn;
	}

	public void remove() {

	}
}
