package TDACola;

import Excepciones.EmptyQueueException;

public interface Queue<E> {
	/**
	 * Retorna el número de elementos en la cola.
	 * 
	 * @return número de elementos en la cola.
	 */
	public int size();

	/**
	 * Consulta si la lista está vacía.	
	 * 
	 * @return true si la cola está vacía, falso en caso contrario.
	 */
	public boolean isEmpty();

	/**
	 * Inspecciona el elemento al frente de la cola.
	 * 
	 * @return elemento al frente de la cola.
	 * 
	 * @exception EmptyQueueException si la cola está vacía. 
	 */
	public E front() throws EmptyQueueException;

	/**
	 * Inserta un elemento al final de la cola.
	 * 
	 * @param elemento nuevo que se desea insertar. 
	 */
	public void enqueue(E element);

	/**
	 * Remueve el elemento al frente de la cola.
	 * 
	 * @return elemento removido.
	 * 
	 * @exception EmptyQueueException si la cola está vacía.
	 */
	public E dequeue() throws EmptyQueueException;
}
