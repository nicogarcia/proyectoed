package TDALista;

import Excepciones.*;

public interface PositionList<E> extends Iterable<E> {

	/**
	 * Devuelve el numero de elementos de la lista.
	 * 
	 * @return Tamaño de la lista
	 */
	public int size();

	/**
	 * Devuelve si la lista esta vacia o no.
	 * 
	 * @return Verdadero si esta vacia o falso en caso contrario.
	 */
	public boolean isEmpty();

	/**
	 * Devuelve el primer nodo de la lista.
	 * 
	 * @return Primer nodo
	 */
	public Position<E> first() throws EmptyListException;

	/**
	 * Retorna el ultimo nodo de la lista.
	 * 
	 * @throws EmptyListException
	 *             si la lista esta vacia
	 */
	public Position<E> last() throws EmptyListException;

	/**
	 * Devuelve el nodo siguiente a uno dado en la lista.
	 * 
	 * @param p
	 *            Nodo de referencia
	 * @return Nodo siguiente
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException;

	/**
	 * Insrta un elemento al principio de la lista devolviendo una nueva
	 * posicion.
	 * 
	 * @param e
	 *            Elemento a insertar
	 */
	public void addFirst(E e);

	/**
	 * Inserta un elemento al final de la lista devolviendo una nueva posicion.
	 * 
	 * @param e
	 *            Elemento a insertar
	 */
	public void addLast(E e);

	/**
	 * Inserta en la lista un elemento despues de un nodo dado.
	 * 
	 * @param e
	 *            Elemento a insertar.
	 * @param p
	 *            Posicion de referencia.
	 */
	public void addAfter(Position<E> p, E e) throws InvalidPositionException;

	/** Inserta en la lista un elemento antes de un nodo dado.
	 * @param e
	 * 	Elemento a insertar
	 * @param p Posicion de referencia */
	public void addBefore(Position<E> p, E e) throws InvalidPositionException;

	/**
	 * Borra un nodo de la lista y devuelve el elemento que estaba en esa
	 * posicion.
	 * @param p Posicion del nodo a remover.
	 * @return Elemento previamente almacenado en el nodo removido.
	 */
	public E remove(Position<E> p) throws InvalidPositionException;

	/**
	 * Reemplaza el elemento almacenado en un nodo dado devolviendo el elemento
	 * viejo.
	 * @param e Elemento nuevo.
	 * @param p Posicion a la que se le cambiara el elemento.
	 * @return Elemento previamente almacenado en el nodo.
	 */
	public E set(Position<E> p, E e) throws InvalidPositionException;

}
