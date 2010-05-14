package TDAPila;
import Excepciones.*;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		Pila<String> pila = new Pila<String>();
		pila.push("a");
		pila.push("b");
		pila.push("c");
		pila.push("d");
		pila.push("e");
		
		System.out.println(pila.toString());
		
		System.out.println(pila.pop());
		System.out.println(pila.pop());
		System.out.println(pila.pop());
		System.out.println(pila.pop());
		System.out.println(pila.pop());
		System.out.println(pila.pop());
		System.out.println(pila.toString());

		
		}catch (EmptyStackException e) {System.out.println(e.getMessage());}
	}

}
