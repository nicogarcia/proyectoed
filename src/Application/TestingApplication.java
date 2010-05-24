package Application;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidLevelException;
import Excepciones.InvalidPositionException;
import GeneralTree.Arbol;
import GeneralTree.TNode;
import TDALista.Lista;
import TDALista.Node;
import TDALista.Position;
import TDAPila.Pila;

public class TestingApplication {

	public static void main(String[] args) {
		cargarArbol('A');
		// System.out.println(printPreOrder());
		agregarNodo('B', 'A');
		agregarNodo('C', 'B');
		agregarNodo('D', 'A');
		agregarNodo('E', 'A');
		agregarNodo('F', 'E');
		agregarNodo('G', 'E');
		System.out.println(printNiveles());
		try {
			System.out.println("Se eliminaron los siguientes elementos:"
					+ eliminarNivel(2).toString());

		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLevelException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		System.out.println(printNiveles());

	}

	private static Arbol<Character> miArbol;

	public static void cargarArbol(Character rotuloRaiz) {
		miArbol = new Arbol<Character>(rotuloRaiz);
	}

	public static void agregarNodo(Character rotulo, Character rPadre) {
		miArbol.insertar(rotulo, rPadre);
	}

	// FIXME MODIFICAR PARA HACER EL DIBUJITO DE NICO
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
	 *         encontrado ningï¿½n nodo con el caracter recibido.
	 * @throws InvalidPositionException
	 * @throws EmptyTreeException
	 */
	public static Character eliminarNodo(Character c)
			throws InvalidPositionException, EmptyTreeException {
		Character toReturn = null;
		for (Position<Character> pos : miArbol.positions())
			// Este for each busca la posiciï¿½n con el caracter recibido
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
					"eliminarNivel() :: No se puede eliminar el primer nivel, porque la raíz no puede ser eliminada.");
		Pila<Character> eliminados = new Pila<Character>();
		int nivelActual = 1;
		Lista<Character> listaNiveles = miArbol.listadoNiveles();
		Position<Character> current;
		if (!miArbol.isEmpty()) {
			try {
				current = listaNiveles.first();
				while (nivelActual != nivel) // Me posiciono en la lista de
				// niveles, donde empieza el
				// nivel recibido.
				{
					if (current.element() == null)
						nivelActual++;
					current = listaNiveles.next(current);
				}
				while (nivelActual == nivel) {
					eliminados.push(current.element());
					eliminarNodo(current.element());
					current = listaNiveles.next(current);
					if (current.element() == null)
						nivelActual++;
				}

			} catch (EmptyListException e1) {
				System.out
						.println("Esta excepciï¿½n no deberï¿½a dispararse, ya se verificï¿½ que el arbol "
								+ "no estï¿½ vacï¿½o.");
			} catch (InvalidPositionException e) {
				System.out.println("Esta excepciï¿½n no deberï¿½a dispararse.");
			} catch (BoundaryViolationException e) {
				throw new InvalidLevelException(
						"eliminarNivel :: El nivel que se desea eliminar no existe.");
			}

		} else
			throw new EmptyTreeException(
					"No se puede eliminar ningï¿½n nivel, el ï¿½rbol estï¿½ vacï¿½o.");

		return eliminados;
	}

	public String camino(Character r1, Character r2) {
		String str = "[ ";

		return str;
	}

	public Character ancestroComun(Character rot1, Character rot2) {
		TNode<Character> nodo1 = miArbol.findNodo(rot1);
		TNode<Character> nodo2 = miArbol.findNodo(rot2);
		Pila<Character> ancestros1 = miArbol.ancestros(nodo1);
		Pila<Character> ancestros2 = miArbol.ancestros(nodo2);
		Lista<Character> camino1 = new Lista<Character>();
		Lista<Character> camino2 = new Lista<Character>();
		Character AC;
		boolean encontre = false;
		while (!encontre) {
			AC = ancestros1.top(); // Guardo el posible ancestro más cercano.
			if (ancestros1.pop() != ancestros2.pop())
				encontre = true; // Cuando encuentra un ancestro distinto sale
									// del while.
		}
		while (nodo1 != miArbol.root()) {
			nodo1 = nodo1.getParent();
			camino1.addLast(nodo1.element());
		}

		while (nodo2 != miArbol.root()) {
			nodo2 = nodo2.getParent();
			camino1.addLast(nodo2.element());
		}
		return AC;

	}

}
