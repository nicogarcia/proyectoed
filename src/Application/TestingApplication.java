package Application;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Comparator;

import Excepciones.EmptyStackException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidLevelException;
import Excepciones.InvalidPositionException;
import GeneralTree.Arbol;
import GeneralTree.TNode;
import TDALista.Lista;
import TDALista.Position;
import TDAMapeo.Mapeo;
import TDAMapeo.MapeoConABB;
import TDAPila.Pila;

public class TestingApplication {

	protected static Arbol<Character> miArbol;
	protected static Mapeo<Character, Integer> mapeo;
	protected static Comparator<Character> comp;

	public static void cargarArbol(Character rotuloRaiz) {
		miArbol = new Arbol<Character>(rotuloRaiz);
		comp = new Comparator<Character>() {
			public int compare(Character c1, Character c2) {
				return c1 - c2;
			}
		};
	}

	public static void agregarNodo(Character rotulo, Character rPadre) {
		miArbol.insertar(rotulo, rPadre);
	}

	public static void actualizarMapeo() {
		mapeo = new MapeoConABB<Character, Integer>(comp);
		for (Position<Character> pos : miArbol.positions()) {
			try {
				mapeo.put(pos.element(), miArbol.height(pos));
			} catch (InvalidPositionException e) {
				System.out
						.println("Esta excepcion no deberia llegar a dispararse.");
			}
		}
	}

	public static String printPreOrder() {
		String ret = "[ ";
		for (Position<Character> pos : miArbol.preOrderPositions())
			ret += pos.element() + ", ";
		ret = ret.substring(0, ret.length() - 2) + " ]";
		return ret;
	}

	public static String printPosOrder() {
		String ret = "[ ";
		for (Position<Character> pos : miArbol.posOrderPositions())
			ret += pos.element() + ", ";
		ret = ret.substring(0, ret.length() - 2) + " ]";
		return ret;
	}

	public static String printNiveles() {
		String ret = "[ ";
		for (Character pos : miArbol.listadoNiveles()) {
			if (pos == null)
				ret += "\n";
			else
				ret += pos + ", ";
		}
		ret = ret.substring(0, ret.length() - 2) + " ]";
		return ret;
	}

	/**
	 * 
	 * @param c
	 *            Caracter del nodo a eliminar.
	 * @return El caracter del nodo eliminado, o null en caso de que no se haya
	 *         encontrado ning�n nodo con el caracter recibido.
	 * @throws InvalidPositionException
	 * @throws EmptyTreeException
	 */
	public static Character eliminarNodo(Character c)
			throws InvalidPositionException, EmptyTreeException {
		Character toReturn = null;
		for (Position<Character> pos : miArbol.positions())
			// Este for each busca la posici�n con el caracter recibido
			if (pos.element() == c) {
				toReturn = pos.element();
				miArbol.removeNode(pos);
			}
		return toReturn;
	}

	/**
	 * 
	 * @param nivel
	 *            Nivel que se desea eliminar.
	 * @return Una pila con los rotulos de los nodos eliminados.
	 * 
	 * @throws EmptyTreeException
	 * @throws InvalidLevelException
	 */
	// FIXME EN CASO DE QUE EL NIVEL SEA 1, LA RAIZ NO SE ELIMINA, PERO DEVUELVE
	// UNA PILA CON EL ELEMENTO DE LA RAIZ COMO SI HUBIESE SIDO ELIMINADO
	public static Pila<Character> eliminarNivel(int nivel)
			throws EmptyTreeException, InvalidLevelException {
		if (nivel == 1)
			throw new InvalidLevelException(
					"eliminarNivel() :: No se puede eliminar el primer nivel, porque la ra�z no puede ser eliminada.");
		Pila<Character> eliminados = new Pila<Character>();
		if (!miArbol.isEmpty()) {
			try {
				int nivelActual = 1;
				for (Position<Character> pos : miArbol.listadoNiveles()
						.positions()) {
					if (pos.element() == null)
						nivelActual++;
					if (nivelActual == nivel && pos.element() != null) {
						eliminados.push(pos.element());
						eliminarNodo(pos.element());
					}
				}

				if (nivelActual < nivel) // No se llego nunca al nivel que se
					// deseaba eliminar.
					throw new InvalidLevelException(
							"TestingApplication :: eliminarNivel() :: El nivel que se desea eliminar no existe.");
			} catch (InvalidPositionException e) {
				System.out.println("Esta excepci�n no deber�a dispararse.");
			}
		} else
			throw new EmptyTreeException(
					"No se puede eliminar ning�n nivel, el �rbol est� vac�o.");

		return eliminados;
	}

	public static String camino(Character r1, Character r2) {
		Lista<Character> lista;
		try {
			lista = route(r1, r2);
			return lista.toString();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Character ancestroComun(Character rot1, Character rot2)
			throws InvalidPositionException, EmptyTreeException {
		TNode<Character> nodo1 = (TNode<Character>) miArbol.findNodo(rot1);
		TNode<Character> nodo2 = (TNode<Character>) miArbol.findNodo(rot2);
		Pila<Character> ancestros1 = miArbol.ancestros(nodo1);
		Pila<Character> ancestros2 = miArbol.ancestros(nodo2);
		Lista<Character> camino1 = new Lista<Character>();
		Lista<Character> camino2 = new Lista<Character>();
		Character AC = null;
		boolean encontre = false;
		try {
			while (!encontre && !ancestros1.isEmpty() && !ancestros2.isEmpty())
			// Si encontr� un ancestro distinto o se vaci� una pila termina.
			{
				if (ancestros1.top() == ancestros2.top())
					AC = ancestros1.top(); // Guardo el posible ancestro mas
				// cercano.
				else
					encontre = true; // Cuando encuentra un ancestro distinto
				// sale del while.
				ancestros1.pop();
				ancestros2.pop();
			}
		} catch (EmptyStackException e) {
			System.out
					.println("Esta excepci�n no deber�a llegar a dispararse (el bucle no se ejecuta si una pila est� vac�ai.");
		}
		while (nodo1 != miArbol.root()) {
			nodo1 = nodo1.getParent();
			camino1.addLast(nodo1.element());
		}

		while (nodo2 != miArbol.root()) {
			nodo2 = nodo2.getParent();
			camino2.addLast(nodo2.element());
		}
		return AC;

	}

	public static Lista<Character> route(Character rotulo1, Character rotulo2)
			throws InvalidPositionException, EmptyTreeException {
		TNode<Character> nodo1 = (TNode<Character>) miArbol.findNodo(rotulo1);
		TNode<Character> nodo2 = (TNode<Character>) miArbol.findNodo(rotulo2);

		Character ancestrocomun = ancestroComun(rotulo1, rotulo2);

		Pila<Character> pila = new Pila<Character>();
		while (!nodo1.element().equals(ancestrocomun)) {
			pila.push(nodo1.element());
			nodo1 = nodo1.getParent();
		}

		pila.push(ancestrocomun);
		Pila<Character> pila2 = new Pila<Character>();

		while (!nodo2.element().equals(ancestrocomun)) {
			pila2.push(nodo2.element());
			nodo2 = nodo2.getParent();
		}

		Lista<Character> lista = new Lista<Character>();
		try {
			while (!pila.isEmpty())
				lista.addFirst(pila.pop());

			while (!pila2.isEmpty())
				lista.addLast(pila2.pop());

		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	// TODO MIRAR LAS EXCEPCIONES
	public static void calcularCentros(int ancho, Point2D position,
			TNode<Character> inicio) throws InvalidPositionException {
		int dif = 100;
		int i = 0;
		// establece el centro del nodo
		Point2D center = new Point((int) position.getX() + ancho / 2,
				(int) position.getY());
		inicio.setCorner(center);

		if (miArbol.isInternal(inicio)) {
			for (TNode<Character> nodo : inicio.getChildren()) {
				Point2D newpos = new Point2D.Double(position.getX()
						+ (ancho / inicio.getChildren().size()) * i, position
						.getY()
						+ dif);
				i++;
				calcularCentros(ancho / inicio.getChildren().size(), newpos,
						nodo);
			}
		}
	}
}
