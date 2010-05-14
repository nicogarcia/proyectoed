package TDACola;

import Excepciones.EmptyQueueException;

public class Cola<E> implements Queue<E> {
	private int front, rear;
	private E[] array;
	public int capacity;

	public Cola() {
		front = rear = 0;
		capacity = 100;
		array = (E[]) new Object[capacity];
	}

	public Cola(int capacity) {
		front = rear = 0;
		this.capacity = capacity;
		array = (E[]) new Object[capacity];
	}

	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException(
					"Cola::dequeue():: La cola esta vacia.");
		E ret = array[front];
		array[front] = null;
		front = (front + 1) % capacity;
		return ret;
	}

	public void enqueue(E element) {
		if (isArrayFull())
			growArray(array);
		array[rear] = element;
		rear = (rear + 1) % capacity;
	}

	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola::front():: La cola esta vacia.");
		return array[front];
	}

	// cambiar a privado
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

	// creado para mejorar la legibilidad
	private boolean isArrayFull() {
		return size() == capacity - 1;
	}

	public boolean isEmpty() {
		return front == rear;
	}

	public int size() {
		return (capacity - front + rear) % capacity;
	}

	// TODO Eliminar esto!!
	public String toString() {
		String ret = "[ ";
		ret += array[front];
		for (int i = front + 1; i != rear; i = (i + 1) % capacity) {
			ret += ", " + array[i].toString();
		}
		return ret + " ]";
	}

}
