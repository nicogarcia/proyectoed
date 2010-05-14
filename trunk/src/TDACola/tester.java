package TDACola;

public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cola<String> cola = new Cola<String>(3);
		try {
			cola.enqueue("tincho");
			cola.enqueue("nico");
			cola.enqueue("cola");
			cola.enqueue("lista");
			cola.enqueue("martig");
			cola.enqueue("sergio");
			cola.enqueue("gomez");
			cola.enqueue("pollo");
			cola.enqueue("perrito");
			cola.enqueue("caca");
			cola.enqueue("string");
			cola.enqueue("element");
			cola.enqueue("enqueue");
			cola.dequeue();
			System.out.println(cola.toString());
			
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.toString());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.toString());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
			System.out.println(cola.dequeue());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
