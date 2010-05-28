package TDACola;

import Excepciones.EmptyQueueException;

/**
 * Esta clase define el comportamiento de una cola con objetos de tipo gen�rico E.
 * 
 * @author Martin Schiaffino [93718]
 * @author Nicolas Garcia [93078]
 * 
 * @param <E>
 *            Tipo generico de objetos de la cola.
 */
public class Cola<E> implements Queue<E> {

	// Atributos
	private int front, rear;
	private E[] array;
	public int capacity;

	/**
	 * Constructor con capacidad por defecto.
	 */
	public Cola() {
		front = rear = 0;
		capacity = 100;
		array = (E[]) new Object[capacity];
	}

	/**
	 * Constructor con capacidad determinada por el usuario.
	 * 
	 * @param capacity
	 *            Capacidad inicial de la cola.
	 */
	public Cola(int capacity) {
		front = rear = 0;
		this.capacity = capacity;
		array = (E[]) new Object[capacity];
	}

	/**
	 * Remueve el elemento al frente de la cola.
	 * 
	 * @return elemento removido.
	 * 
	 * @exception EmptyQueueException
	 *                si la cola esta vacia.
	 */
	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException(
					"Cola::dequeue():: La cola esta vacia.");
		E ret = array[front];
		array[front] = null;
		front = (front + 1) % capacity;
		return ret;
	}

	/**
	 * Inserta un elemento al final de la cola.
	 * 
	 * @param elemento
	 *            nuevo que se desea insertar.
	 */
	public void enqueue(E element) {
		if (isArrayFull())
			growArray(array);
		array[rear] = element;
		rear = (rear + 1) % capacity;
	}

	/**
	 * Inspecciona el elemento al frente de la cola.
	 * 
	 * @return elemento al frente de la cola.
	 * 
	 * @exception EmptyQueueException
	 *                si la cola esta vacia.
	 */
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola::front():: La cola esta vacia.");
		return array[front];
	}

	//TODO cambiar a privado
	public void growArray(E[] array) {
		final int incremento = 50;
		E[] arrayNuevo = (E[]) new Object[capacity + incremento];

		for (int i = front, e = 0; i != rear; i = (i + 1) % capacity, e++) {
			arrayNuevo[e] = array[i];
		}
		front = 0;
		rear = size() - 1;
		capacity += incremento;
		this.array = arrayNuevo;
	}

	// Creado para mejorar la legibilidad
	private boolean isArrayFull() {
		return size() == capacity - 1;
	}

	/**
	 * Consulta si la cola esta vacia.
	 * 
	 * @return true si la cola esta vacia, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return front == rear;
	}

	/**
	 * Retorna el número de elementos en la cola.
	 * 
	 * @return número de elementos en la cola.
	 */
	public int size() {
		return (capacity - front + rear) % capacity;
	}


	public String toString() {
		String ret = "[ ";
		ret += array[front];
		for (int i = front + 1; i != rear; i = (i + 1) % capacity) {
			ret += ", " + array[i].toString();
		}
		return ret + " ]";
	}

}
