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

/**
 * Programa manejador de estructuras de datos
 * 
 * @author Martin Schiaffino [93718]
 * @author Nicolas Garcia [93078]
 * 
 */
public class TestingApplication {

	protected static Arbol<Character> miArbol;
	protected static Mapeo<Character, Integer> mapeo;
	protected static Comparator<Character> comp;
/**
 * Inicializa el arbol y el comparador del mapeo
 * @param rotuloRaiz Rotulo de la raiz
 */
	public static void cargarArbol(Character rotuloRaiz) {
		miArbol = new Arbol<Character>(rotuloRaiz);
		comp = new Comparator<Character>() {
			public int compare(Character c1, Character c2) {
				return c1 - c2;
			}
		};
	}
/**
 * Agrega un nodo al arbol
 * @param rotulo Rotulo del nodo
 * @param rPadre Rotulo del padre
 */
	public static void agregarNodo(Character rotulo, Character rPadre) {
		miArbol.insertar(rotulo, rPadre);
	}
/**
 * Elimina uno nodo
 * @param pos Posicion del nodo a eliminar
 */
	public static void borrarNodo(Position<Character> pos) {
		try {
			if (!miArbol.isEmpty())
				miArbol.removeNode(pos);
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (EmptyTreeException e) {
			System.out.println("Esta excepcion no deberia dispararse.");
		}
	}
/**
 * Actualiza el mapeo con las nuevas alturas
 */
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
/**
 * Devuelve una cadena con los elementos del arbol en preorden
 * @return Cadena con los elementos del arbol en preorden
 */
	public static String printPreOrder() {
		String ret = "[ ";
		for (Position<Character> pos : miArbol.preOrderPositions())
			ret += pos.element() + ", ";
		ret = ret.substring(0, ret.length() - 2) + " ]";
		return ret;
	}
	/**
	 * Devuelve una cadena con los elementos del arbol en posorden
	 * @return Cadena con los elementos del arbol en posorden
	 */
	public static String printPosOrder() {
		String ret = "[ ";
		for (Position<Character> pos : miArbol.posOrderPositions())
			ret += pos.element() + ", ";
		ret = ret.substring(0, ret.length() - 2) + " ]";
		return ret;
	}
/**
 * Devuelve una cadena con los elementos del arbol por niveles
 * @return Cadena con los elementos del arbol por niveles
 */
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
	 * Elimina un nodo
	 * @param c
	 *            Caracter del nodo a eliminar.
	 * @return El caracter del nodo eliminado, o null en caso de que no se haya
	 *         encontrado ning�n nodo con el caracter recibido.
	 * @throws InvalidPositionException si la posicion es invalida
	 * @throws EmptyTreeException si el arbol esta vacio
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
	 * Elimina un nivel del arbol
	 * @param nivel
	 *            Nivel que se desea eliminar.
	 * @return Una pila con los rotulos de los nodos eliminados.
	 * 
	 * @throws EmptyTreeException si el arbol esta vacio
	 * @throws InvalidLevelException si el nivel no existe
	 */
	public static Pila<Character> eliminarNivel(int nivel)
			throws EmptyTreeException, InvalidLevelException {
		if (nivel == 0)
			throw new InvalidLevelException(
					"eliminarNivel() :: No se puede eliminar el primer nivel, porque la ra�z no puede ser eliminada.");
		Pila<Character> eliminados = new Pila<Character>();
		if (!miArbol.isEmpty()) {
			try {
				int nivelActual = 0;
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
/**
 * Devuelve una cadena con el camino de un nodo a otro
 * @param r1 Caracter nodo 1
 * @param r2 Caracter nodo 2
 * @return Cadena con el camino
 */
	public static String camino(Character r1, Character r2) {
		if (miArbol != null) {
			Lista<Character> lista;
			try {
				lista = route(r1, r2);
				return lista.toString();
			} catch (EmptyTreeException e) {
				System.out.println("Esta excepcion no deberia dispararse");
			}
		}
		return null;
	}
/**
 * Devuelve el ancestro comun mas cercano a dos nodos
 * @param rot1 Caracter nodo 1
 * @param rot2 Caracter nodo 2
 * @return Devuelve el ancestro
 * @throws EmptyTreeException si el arbol esta vacio
 */
	public static Character ancestroComun(Character rot1, Character rot2)
			throws EmptyTreeException {
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
/**
 * Devuelve una lista con los rotulos de los nodos del camino
 * @param rotulo1 Caracter del nodo 1
 * @param rotulo2 Caracter del nodo 2
 * @return Devuelve una lista con los rotulos de los nodos del camino
 * @throws EmptyTreeException
 */
	public static Lista<Character> route(Character rotulo1, Character rotulo2)
			throws EmptyTreeException {
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
			System.out.println("Esta excepcion no deberia dispararse");
		}

		return lista;
	}
/**
 * Calcula la ubicacion de los nodos para graficarlos
 * @param ancho Ancho del area de graficos
 * @param position Margenes
 * @param inicio Nodo de inicio
 * @throws InvalidPositionException
 */
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
