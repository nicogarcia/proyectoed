package GeneralTree;

import java.util.Iterator;

import sun.font.EAttribute;

import Excepciones.*;
import TDACola.Cola;
import TDALista.*;
import TDAPila.Pila;

// TODO: Auto-generated Javadoc
/**
 * Esta clase define el comportamiento de un arbol general con elementos
 * genericos de tipo E.
 * 
 * @param <E>
 *            Objeto gen�rico.
 * @author Martin Schiaffino [93718]
 * @author Nicolas Garcia [93078]
 */
public class Arbol<E> implements GeneralTree<E> {

	// Atributos
	/** The root. */
	protected TNode<E> root;

	/** The size. */
	protected int size;

	/**
	 * Construye un arbol cuya raiz tiene como rotulo la recibida por parametro.
	 * 
	 * @param rotuloRaiz
	 *            Rotulo de la raiz.
	 */
	public Arbol(E rotuloRaiz) {
		root = new TNode<E>(null, rotuloRaiz);
	}

	/**
	 * Inserta un nuevo nodo en el arbol.
	 * 
	 * @param rotulo
	 *            Rotulo del nodo a insertar.
	 * @param rPadre
	 *            Rotulo del padre del nodo a insertar.
	 * @return El nuevo nodo insertado.
	 */
	public TNode<E> insertar(E rotulo, E rPadre) {
		for (Position<E> pos : positions())
			if (pos.element() == rPadre) {
				try {
					TNode<E> padre = checkPosition(pos);
					TNode<E> nuevo = new TNode<E>(padre, rotulo);
					padre.getChildren().addLast(nuevo);
					return nuevo;
				} catch (InvalidPositionException e) {
					System.out
							.println("Arbol::insertar():: Esta excepcion no deberia llegar a dispararse.");
				}
			}
		return null;
	}

	/**
	 * Retorna una colecci�n iterable de hijos de la posici�n recibida.
	 * 
	 * @param v
	 *            Posici�n padre de los hijos de la lista.
	 * @return Una colecci�n iterable de hijos.
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	public Iterable<Position<E>> children(Position<E> v)
			throws InvalidPositionException {
		TNode<E> p = checkPosition(v);
		Lista<Position<E>> lista = new Lista<Position<E>>();
		for (TNode<E> h : p.getChildren()) {
			lista.addLast(h);
		}
		return lista;
	}

	/**
	 * Chequea si la posicion recibida es valida.
	 * 
	 * @param v
	 *            Posicion a chequear.
	 * @return Retorna la posicion casteada a nodo.
	 * @throws InvalidPositionException
	 *             En caso de que la posicion sea invalida.
	 */
	private TNode<E> checkPosition(Position<E> v)
			throws InvalidPositionException {
		if (v == null)
			throw new InvalidPositionException(
					"Arbol::checkposition():: La posicion es invalida.");
		try {
			TNode<E> nodo = (TNode<E>) v;
			return nodo;
		} catch (ClassCastException c) {
			throw new InvalidPositionException(
					"Arbol::checkposition():: El tipo de la posicion es invalido.");
		}
	}

	/**
	 * Determina si la cola esta vacia o no.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Determina si la posicion recibida corresponde a un nodo externo.
	 * 
	 * @param v
	 *            Posicion a verificar.
	 * @return true, if is external
	 * @throws InvalidPositionException
	 *             En caso de que la posicion sea invalida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return (nodo.getChildren().isEmpty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GeneralTree.GeneralTree#isInternal(TDALista.Position)
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return (nodo.getChildren() != null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GeneralTree.GeneralTree#isRoot(TDALista.Position)
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return nodo == root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GeneralTree.GeneralTree#iterator()
	 */
	public Iterator<E> iterator() {
		Lista<E> lista = new Lista<E>();
		for (Position<E> p : positions())
			lista.addLast(p.element());
		return lista.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GeneralTree.GeneralTree#parent(TDALista.Position)
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		TNode<E> nodo = checkPosition(v);
		return nodo.getParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GeneralTree.GeneralTree#positions()
	 */
	public Iterable<Position<E>> positions() {
		return preOrderPositions();
	}

	// FIXME PREGUNTAR SI SE PUEDE HACER UN POSITIONPRE Y OTRO POSITIONPOS
	/**
	 * Pre order positions.
	 * 
	 * @return the iterable
	 */
	public Iterable<Position<E>> preOrderPositions() {
		Lista<Position<E>> lista = new Lista<Position<E>>();
		preOrder(lista, root);
		return lista;
	}

	/**
	 * Pos order positions.
	 * 
	 * @return the iterable
	 */
	public Iterable<Position<E>> posOrderPositions() {
		Lista<Position<E>> lista = new Lista<Position<E>>();
		posOrder(lista, root);
		return lista;
	}

	/**
	 * Remplaza el elemento del la posicion recibida.
	 * 
	 * @param v
	 *            Posicion a la cual se le reemplaza el elemento.
	 * 
	 * @param e
	 *            Nuevo elemento de la posicion.
	 * 
	 * @return El elemento anterior de la posicion.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return nodo.setElement(e);
	}

	/**
	 * Getter de la raiz.
	 * 
	 * @returns La raiz del arbol.
	 * @throws EmptyTreeException
	 *             En caso de que el arbol este vacio.
	 */
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException(
					"Arbol:: root():: El arbol esta vacio.");
		return root;
	}

	/**
	 * Getter del tama�o.
	 * 
	 * @return Tama�o del arbol.
	 */
	public int size() {
		return size;
	}

	/**
	 * A�ade a una lista las positions recorriendo en pre-orden.
	 * 
	 * @param l
	 *            Lista a la cual se a�aden las positions.
	 * 
	 * @param n
	 *            Raiz del arbol (cuando es llamado).
	 * 
	 */

	private void preOrder(Lista<Position<E>> l, TNode<E> n) {
		l.addLast(n);
		for (TNode<E> h : n.getChildren())
			preOrder(l, h);
	}

	/**
	 * A�ade a una lista las positions recorriendo en pos-orden.
	 * 
	 * @param l
	 *            Lista a la cual se a�aden las positions.
	 * 
	 * @param n
	 *            Raiz del arbol (cuando es llamado).
	 * 
	 */
	private void posOrder(Lista<Position<E>> l, TNode<E> n) {
		for (TNode<E> h : n.getChildren())
			posOrder(l, h);
		l.addLast(n);
	}

	/**
	 * Devuelve una lista con los elementos del arbol, con nulls que representan
	 * saltos de nivel.
	 * 
	 * @return El listado de niveles.
	 */
	public Lista<E> listadoNiveles() {
		Lista<E> lista = new Lista<E>();
		Cola<TNode<E>> cola = new Cola<TNode<E>>();
		try {
			cola.enqueue(checkPosition(root()));
			cola.enqueue(null);
			while (!cola.isEmpty()) {
				TNode<E> p = cola.dequeue();
				if (p == null) {
					if (!cola.isEmpty()) {
						cola.enqueue(null);
						lista.addLast(null);
					}
				} else {
					lista.addLast(p.element());
					for (TNode<E> h : p.getChildren())
						cola.enqueue(h);
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + "  ::  "
					+ e.getClass().toString());
		}
		return lista;
	}

	/**
	 * Cuenta los niveles del arbol.
	 * 
	 * @return La cantidad de niveles del arbol.
	 */
	public int contarNiveles() {
		int cant = 1;
		for (E el : listadoNiveles())
			if (el == null)
				cant++;

		return cant;
	}

	/**
	 * Elimina un nodo del arbol.
	 * 
	 * @param p
	 *            Position (nodo) a eliminar.
	 * 
	 * @return El elemento del nodo eliminado.
	 * 
	 * @throws InvalidPositionException
	 *             En caso de que la posicion sea invalida.
	 * 
	 * @throws EmptyTreeException
	 *             En caso de que el arbol este vacio.
	 * 
	 */
	public E removeNode(Position<E> p) throws InvalidPositionException,
			EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException(
					"Arbol :: eliminarNodo() :: El arbol est� vac�o.");
		if (p == root())
			throw new InvalidPositionException(
					"Arbol :: eliminarNodo() :: No puedo eliminar la ra�z del �rbol.");
		TNode<E> n = checkPosition(p);
		E toReturn = n.element();
		TNode<E> parent = n.getParent();
		Lista<TNode<E>> brothers = (Lista<TNode<E>>) parent.getChildren();
		Position<TNode<E>> c;
		try {
			c = brothers.first();
			boolean flag = true;
			while (flag) {
				if (c.element() == n)
					flag = false;
				else
					c = brothers.next(c);

			}
			for (TNode<E> node : n.getChildren()) {
				node.setParent(parent);
				brothers.addBefore(c, node);
			}
			brothers.remove(c);
			n.setParent(null);
			n.setChildren(null);
			n.setElement(null);
		} catch (EmptyListException e) {
			System.out
					.println("Arbol :: eliminarNodo() :: EmptyList :: Esta excepci�n deber�a dispararse nunca.");
		} catch (BoundaryViolationException e) {
			System.out
					.println("Arbol :: eliminarNodo() :: Boundary :: Esta excepci�n no deber�a dispararse nunca.");
		}

		return toReturn;
	}

	/**
	 * Crea una pila con los ancestros del nodo recibido.
	 * 
	 * @param nodo
	 *            Nodo de cual se buscan los ancestros.
	 * 
	 * @return Una pila con los ancestros del nodo.
	 */
	public Pila<E> ancestros(TNode<E> nodo) {
		Pila<E> pila = new Pila<E>();

		while (nodo != null) {
			pila.push(nodo.element());
			nodo = nodo.getParent();
		}

		return pila;
	}

	/**
	 * Busca el la posicion correspondiente al rotulo recibido.
	 * 
	 * @param rotulo
	 *            the rotulo
	 * @return La posicion encontrada o nulo en caso de que no haya ninguna con
	 *         el rotulo recibido
	 */
	public Position<E> findNodo(E rotulo) {
		Lista<Position<E>> lista = new Lista<Position<E>>();
		preOrder(lista, root);
		for (Position<E> pos : lista)
			if (pos.element().equals(rotulo))
				return pos;
		return null;
	}

	// FIXME cambiar nombre
	/**
	 * Find nodo piola.
	 * 
	 * @param rotulo
	 *            the rotulo
	 * @param inicio
	 *            the inicio
	 * @return the position
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	public Position<E> findNodoPiola(E rotulo, Position<E> inicio)
			throws InvalidPositionException {
		TNode<E> ini = checkPosition(inicio);
		for (TNode<E> nodo : ini.getChildren()) {
			if (nodo.element().equals(rotulo))
				return (Position<E>) nodo;
			else
				return findNodoPiola(rotulo, nodo);
		}
		return ini;
	}

	/**
	 * Calcula la altura de la posicion recibido.
	 * 
	 * @param pos
	 *            Posicion que se analiza.
	 * 
	 * @return La altura de la posicion.
	 * 
	 * @throws InvalidPositionException
	 *             En caso de que la posicion sea invalida.
	 * 
	 */
	public int height(Position<E> pos) throws InvalidPositionException {
		if (isExternal(pos))
			return 0;
		int h = 0;
		for (Position<E> posit : children(pos))
			h = Math.max(h, height(posit));
		return 1 + h;
	}

}
