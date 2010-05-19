package TDAMapeo;
import java.util.Comparator;

//FIXME COPIADO EN CLASE!
import java.util.Map.Entry;

public class ABB<K, V> {
	protected NodoABB<Entry<K, V>> raiz;
	protected Comparator<K> comp;
	protected int size;

	public ABB(Comparator<K> comp) {
		size = 0;
		this.comp = comp;
		raiz = new NodoABB<Entry<K, V>>();
	}

	public V insertar(Entry<K, V> e) {
		return insertAux(raiz, e);
	}

	private V insertAux(NodoABB<Entry<K, V>> v, Entry<K, V> e) {
		if (v.getRotulo() == null) {
			// llegue a una hoja
			v.setRotulo(e);
			// creo hijos
			v.setLeft(new NodoABB<Entry<K, V>>());
			v.setRight(new NodoABB<Entry<K, V>>());
			v.getLeft().setParent(v);
			v.getRight().setParent(v);
			return null;
		} else {
			int comparacion = comp.compare(e.getKey(), v.getRotulo().getKey());
			if (comparacion == 0) {
				// la clave ya estaba y actualizo el valor
				Entry<K, V> viejaEntrada = v.getRotulo();
				v.setRotulo(e);
				return viejaEntrada.getValue();
			} else if (comparacion < 0)
				return insertAux(v.getLeft(), e);
			else
				return insertAux(v.getRight(), e);
		}
	}
}
