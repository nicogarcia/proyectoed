package TDALista;
import Excepciones.*;

public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		pruebaLista();
	}

	public static void pruebaLista() {
		try {
			Lista<String> lista = new Lista<String>();

			lista.addFirst("kaka");
			lista.addFirst("hola");
			lista.addLast("nico");

			lista.addFirst("tincho");
			lista.addAfter(lista.last(), "anteultimo");

			lista.addAfter(lista.first(), "segundo");
			System.out.println(lista.toString());
			lista.remove((lista.first()));			

			System.out.println(lista.toString());
		} catch (Exception e)
		{
			System.out.println("lpm!!");
		}
	}

}
