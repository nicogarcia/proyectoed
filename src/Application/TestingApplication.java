package Application;

import GeneralTree.Arbol;
import TDALista.Position;

public class TestingApplication {
	
	public static void main(String[] args) {
		cargarArbol('A');
		//System.out.println(printPreOrder());
		agregarNodo('B', 'A');
		agregarNodo('c', 'B');
		agregarNodo('D', 'A');
		agregarNodo('E', 'A');
		agregarNodo('F','E');
		agregarNodo('G','E');
		System.out.println(printPreOrder());
	}
	
	
	private static Arbol<Character> miArbol;

	public static void cargarArbol(Character rotuloRaiz) {
		miArbol = new Arbol<Character>(rotuloRaiz);
	}

	public static void agregarNodo(Character rotulo, Character rPadre) {
		miArbol.insertar(rotulo, rPadre);
	}
	
	//FIXME MODIFICAR PARA HACER EL DIBUJITO DE NICO
	public static String printPreOrder() {
		String ret = "[ ";
		for (Position<Character> pos :miArbol.preOrderPositions())
			ret+=pos.element()+", ";
		ret = ret.substring(0, ret.length()-2)+" ]";
		return ret;
	}
	
	public String printPosOrder() {
		String ret = "[ ";
		for (Position<Character> pos :miArbol.posOrderPositions())
			ret+=pos.element()+", ";
		ret = ret.substring(0, ret.length()-2)+" ]";
		return ret;
	}
	
	
	
}
