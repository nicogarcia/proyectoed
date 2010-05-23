package GeneralTree;

//TODO Faltan javadocs. Agregar!

import java.util.Iterator;

import Excepciones.*;
import TDACola.Cola;
import TDALista.*;

public class Arbol<E> implements GeneralTree<E> {

	protected TNode<E> root;
	protected int size;

	public Arbol(E rotuloRaiz) {
		root = new TNode<E>(null, rotuloRaiz);
	}

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

	public Iterable<Position<E>> children(Position<E> v)
			throws InvalidPositionException {
		TNode<E> p = checkPosition(v);
		Lista<Position<E>> lista = new Lista<Position<E>>();
		for (TNode<E> h : p.getChildren()) {
			lista.addLast(h);
		}
		return lista;
	}

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

	public boolean isEmpty() {
		return root == null;
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return (nodo.getChildren() == null);
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return (nodo.getChildren() != null);

	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return nodo == root;
	}

	public Iterator<E> iterator() {
		Lista<E> lista = new Lista<E>();
		for (Position<E> p : positions())
			lista.addLast(p.element());
		return lista.iterator();
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		TNode<E> nodo = checkPosition(v);
		return nodo.getParent();
	}

	public Iterable<Position<E>> positions() {
		return preOrderPositions();
	}

	// FIXME PREGUNTAR SI SE PUEDE HACER UN POSITIONPRE Y OTRO POSITIONPOS
	public Iterable<Position<E>> preOrderPositions() {
		Lista<Position<E>> lista = new Lista<Position<E>>();
		preOrder(lista, root);
		return lista;
	}

	public Iterable<Position<E>> posOrderPositions() {
		Lista<Position<E>> lista = new Lista<Position<E>>();
		posOrder(lista, root);
		return lista;
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return nodo.setElement(e);
	}

	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException(
					"Arbol:: root():: El arbol esta vacio.");
		return root;
	}

	public int size() {
		return size;
	}

	private void preOrder(Lista<Position<E>> l, TNode<E> n) {
		l.addLast(n);
		for (TNode<E> h : n.getChildren())
			preOrder(l, h);
	}

	private void posOrder(Lista<Position<E>> l, TNode<E> n) {
		for (TNode<E> h : n.getChildren())
			posOrder(l, h);
		l.addLast(n);
	}

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

	public E removeNode(Position<E> p) throws InvalidPositionException,
			EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException(
					"Arbol :: eliminarNodo() :: El arbol está vacío.");
		if (p == root())
			throw new InvalidPositionException(
					"Arbol :: eliminarNodo() :: No puedo eliminar la raíz del árbol.");
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
					.println("Arbol :: eliminarNodo() :: EmptyList :: Esta excepción debería dispararse nunca.");
		} catch (BoundaryViolationException e) {
			System.out
					.println("Arbol :: eliminarNodo() :: Boundary :: Esta excepción no debería dispararse nunca.");
		}

		return toReturn;
	}
}
