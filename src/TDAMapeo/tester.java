package TDAMapeo;

import java.util.Comparator;

public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		MapeoConABB<Integer, String> mapeo = new MapeoConABB<Integer, String>(
				comp);

		System.out.println(mapeo.isEmpty());
		mapeo.put(20, "A");
		mapeo.put(25, "J");
		mapeo.put(80, "K");
		mapeo.put(15, "B");
		mapeo.put(7, "C");
		mapeo.put(17, "G");
		mapeo.put(23, "I");
		mapeo.put(18, "H");
		mapeo.put(16, "G");
		mapeo.put(2, "D");
		mapeo.put(10, "E");
		System.out.println(mapeo.toString());
		System.out.println(mapeo.size());
		System.out.println(mapeo.get(23).toString());
		mapeo.put(16,"*");
		System.out.println(mapeo.toString());
	}
}
