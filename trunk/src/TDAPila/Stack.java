package TDAPila;

import Excepciones.EmptyStackException;
//FIXME Hay que poner esto en las interfaces?
/** 
 * Interface for a stack: a collection of objects that are inserted
 * and removed according to the last-in first-out principle.  This
 * interface includes the main methods of java.util.Stack.
 * 
 * @author Roberto Tamassia
 * @author Michael Goodrich
 * @see EmptyStackException
 */

public interface Stack<E> {
/**
 * Devuelve el numero de elmentos de la pila
 * @return numero de elementos de la pila. 
 */
 public int size();
/** 
 * Devuelve si la pila esta vacia.
 * @return Verdadero si la pila esta vacia, falso en caso contrario. 
 */
 public boolean isEmpty();
/** 
 * Devuelve el elemento que esta en el tope de la pila.
 * @return Elemento almacenado en el tope de la pila.  
 * @exception EmptyStackException si la pila esta vacia. 
 */
 public E top() 
   throws EmptyStackException;  
/**
 * Inserta un elemento en el tope de la pila.
 * @param Elemento a insertar.
 */
 public void push (E element); 
/** 
 * Elimina el elemento que esta en el tope de la pila.
 * @return Elemento eliminado.
 * @exception EmptyStackException si la pila esta vacia.
 */
 public E pop()
   throws EmptyStackException; 
}