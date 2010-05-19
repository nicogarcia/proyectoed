package GeneralTree;

import java.util.Iterator;

import Excepciones.*;
import TDALista.Position;
import TDALista.PositionList;
//TODO Faltan javadocs
public class TreeIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor;

	public TreeIterator(PositionList<E> L) {
		list = L;
		try {
			cursor = (list.isEmpty() ? null : list.first());
		} catch (EmptyListException e) {
			System.out.println("Esta excepcion no deberia dispararse");
		}
	}

	public boolean hasNext() {
		return cursor != null;
	}

	public E next() throws NoSuchElementException {
		if (cursor == null)
			throw new NoSuchElementException(
					"TreeIterator::next():: No hay elemento siguiente.");
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

	// FIXME Hay que implementarla?
	public void remove() {

	}
}
