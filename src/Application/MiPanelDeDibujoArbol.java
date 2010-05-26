package Application;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.EmptyStackException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidPositionException;
import GeneralTree.Arbol;
import GeneralTree.TNode;
import TDALista.Lista;
import TDALista.Node;
import TDALista.Position;
import TDALista.PositionList;
import TDAPila.Pila;

/**
 * 
 * @author nico
 */

public class MiPanelDeDibujoArbol extends Canvas {

	private Color marron = new Color(84, 25, 0), verde = new Color(0, 125, 0);
	Image hoja, flecha, hoja_sel, background;
	static Pila<Character> pila;
	static boolean mostrar_camino = false;
	static boolean mostrar_ancestro = false;
	static Character rotuloD, rotuloH, rotulo1, rotulo2;

	public static void setPila(Pila<Character> pila_nueva) {
		pila = pila_nueva;
	}

	public static void setMostrarCamino(boolean mostrar, Character desde,
			Character hasta) {
		mostrar_camino = mostrar;
		rotuloD = desde;
		rotuloH = hasta;
	}
	
	public static void setMostrarAncestro(boolean mostrar, Character rot1, Character rot2) {
		mostrar_ancestro=mostrar;
		rotulo1= rot1;
		rotulo2=rot2;
	}

	public MiPanelDeDibujoArbol() {
		super();

		try {
			hoja = ImageIO.read(getClass().getResource(
					"/images/green_glossy_small.png"));
			hoja_sel = ImageIO.read(getClass().getResource(
					"/images/black_glossy_small.png"));
			flecha = ImageIO.read(getClass().getResource(
					"/images/flecha_small.png"));
			background = ImageIO.read(getClass().getResource(
			"/images/fondo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics e) {
		Graphics2D g = (Graphics2D) e;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (TestingApplication.started)
			if (getName().equals("canvas0"))
				if (NewJFrame.selectedArbol)
					graficarArbol(g);
				else
					graficarNiveles(g);
			else if (getName().equals("canvas1"))
				graficarRecorrido(g);
			else
				graficarPila(g);
	}

	private void graficarPila(Graphics2D e) {
		e.drawImage(background, 0,0,null);
		if (pila != null) {
			try {
				Dimension2D size = new Dimension(48, 48);
				int dist_nodos = 56;
				int i = 0; // indice de nodo actual para el for
				int margen_derecho = 20, margen_superior = 20;

				while (!pila.isEmpty()) {
					Character rotulo = pila.pop();
					Point2D loc = new Point2D.Double(margen_derecho,
							margen_superior + dist_nodos * i);
					i++;
					e.drawImage(hoja_sel, (int) loc.getX(), (int) loc.getY(),
							null);

					e.setFont(new Font(e.getFont().toString(), Font.BOLD, 14));

					e.setColor(Color.white);

					e.drawString(rotulo.toString(), (int) (loc.getX()
							+ size.getWidth() / 2 - 5), (int) (loc.getY()
							+ size.getHeight() / 2 + 5));
				}
				if (i * dist_nodos + margen_derecho > getHeight())
				setSize(getWidth(), i * dist_nodos + margen_superior);

			} catch (EmptyStackException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void graficarRecorrido(Graphics2D e) {
		e.drawImage(background, 0,0,null);
		Arbol<Character> arbol = TestingApplication.miArbol;
		if (!arbol.isEmpty()) {
			Dimension2D size = new Dimension(48, 48);
			PositionList<Position<Character>> lista;
			if (NewJFrame.selectedPreorder)
				lista = (PositionList<Position<Character>>) arbol
						.preOrderPositions();
			else
				lista = (PositionList<Position<Character>>) arbol
						.posOrderPositions();

			// distancia entre los dibujos de los nodos para que entren las
			// flechas
			int dist_nodos = 96;
			int i = 0; // indice de nodo actual para el for
			int margen_derecho = 20, margen_superior = 20;
			for (Position<Character> pos : lista) {
				Point2D loc = new Point2D.Double(margen_derecho + dist_nodos
						* i, margen_superior);
				i++;
				// crear variables para reducir calculos
				e.drawImage(hoja, (int) loc.getX(), (int) loc.getY(), null);
				if (i != lista.size())
					e.drawImage(flecha, (int) loc.getX()
							+ (int) size.getWidth() + 12,
							(int) loc.getY() + 12, null);
				e.setFont(new Font(e.getFont().toString(), Font.BOLD, 14));
				e.setColor(Color.black);
				e.drawString(pos.element().toString(), (int) (loc.getX()
						+ size.getWidth() / 2 - 5), (int) (loc.getY()
						+ size.getHeight() / 2 + 5));
			}
			if (i * dist_nodos + margen_derecho > getWidth())
				setSize(i * dist_nodos + margen_derecho, getHeight());
		}
	}

	private void graficarArbol(Graphics2D e) {
		e.drawImage(background, 0,0,null);
		Arbol<Character> arbol = TestingApplication.miArbol;
		// TODO PRESTAR ATENCION CDO SE CAMBIEN LOS STATIC DE TESTING APP
		if (!arbol.isEmpty()) {
			Dimension2D size = new Dimension(48, 48);
			try {
				TNode<Character> raiz = (TNode<Character>) arbol.root();
				Line2D arco;
				// TODO REDUCIR A UN FOR
				// Se dibujan los arcos y luego se superponen los circulos
				Point2D centropadre;
				Point2D centro;
				TNode<Character> nodo, padre;
				for (Position<Character> pos : arbol.positions()) {
					nodo = (TNode<Character>) pos;
					padre = nodo.getParent();
					if (!arbol.isRoot(pos)) {
						arco = new Line2D.Double();
						centro = new Point2D.Double(nodo.getCorner().getX()
								+ size.getWidth() / 2, nodo.getCorner().getY()
								+ size.getHeight() / 2);
						centropadre = new Point2D.Double(padre.getCorner()
								.getX()
								+ size.getWidth() / 2, padre.getCorner().getY()
								+ size.getHeight() / 2);
						e.setColor(Color.blue);
						e.setStroke(new BasicStroke(5));
						arco.setLine(centro, centropadre);
						e.draw(arco);
					}
				}
				for (Position<Character> pos : arbol.positions()) {
					nodo = (TNode<Character>) pos;
					e.setColor(verde);
					e.drawImage(hoja, (int) nodo.getCorner().getX(), (int) nodo
							.getCorner().getY(), null);
					e.setFont(new Font(e.getFont().toString(), Font.BOLD, 14));
					e.setColor(Color.black);
					e.drawString(nodo.element().toString(), (int) (nodo
							.getCorner().getX()
							+ size.getWidth() / 2 - 5), (int) (nodo.getCorner()
							.getY()
							+ size.getHeight() / 2 + 5));
				}

			} catch (EmptyTreeException e1) {
				System.out.println("Esta excepcion no deberia dispararse.");
			} catch (InvalidPositionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (mostrar_ancestro)
				resaltarAncestro();
			if (mostrar_camino)
				resaltarCamino();

		}
	}

	public void resaltarCamino() {
		Lista<Character> lista;
		Arbol<Character> arbol = TestingApplication.miArbol;
		Dimension2D size = new Dimension(48, 48);
		Graphics2D e = (Graphics2D) getGraphics();
		try {
			lista = TestingApplication.route(rotuloD, rotuloH);
			Line2D arco;
			Point2D centropadre;
			Point2D centro;
			Position<Character> pos = lista.first();
			boolean salir = false;
			while (!salir) {
				boolean last = pos == lista.last();
				TNode<Character> nodo = (TNode<Character>) arbol.findNodo(pos
						.element());
				if (!last) {
					TNode<Character> siguiente = (TNode<Character>) arbol
							.findNodo(lista.next(pos).element());
					// supone que si el siguiente es el padre entonces pertenece
					// a
					// la lista
					arco = new Line2D.Double();
					centro = new Point2D.Double(nodo.getCorner().getX()
							+ size.getWidth() / 2, nodo.getCorner().getY()
							+ size.getHeight() / 2);
					centropadre = new Point2D.Double(siguiente.getCorner()
							.getX()
							+ size.getWidth() / 2, siguiente.getCorner().getY()
							+ size.getHeight() / 2);
					e.setColor(Color.red);
					e.setStroke(new BasicStroke(5));
					arco.setLine(centro, centropadre);
					e.draw(arco);
				}
				e.setColor(Color.black);
				e.drawImage(hoja_sel, (int) nodo.getCorner().getX(), (int) nodo
						.getCorner().getY(), null);
				e.drawString(nodo.element().toString(), (int) (nodo.getCorner()
						.getX()
						+ size.getWidth() / 2 - 5), (int) (nodo.getCorner()
						.getY()
						+ size.getHeight() / 2 + 5));
				if (!last)
					pos = lista.next(pos);
				else
					salir = true;
			}
		} catch (InvalidPositionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyTreeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyListException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BoundaryViolationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void resaltarAncestro() {
		Dimension2D size = new Dimension(48, 48);
		Graphics2D e = (Graphics2D) getGraphics();
		Arbol<Character> arbol = TestingApplication.miArbol;
		TNode<Character> nodo;
		try {
			nodo = (TNode<Character>) arbol.findNodo(TestingApplication.ancestroComun(rotuloD, rotuloH));
		
		e.setColor(Color.black);
		e.drawImage(hoja_sel, (int) nodo.getCorner().getX(), (int) nodo
				.getCorner().getY(), null);
		e.drawString(nodo.element().toString(), (int) (nodo.getCorner().getX()
				+ size.getWidth() / 2 - 5), (int) (nodo.getCorner().getY()
				+ size.getHeight() / 2 + 5));
		} catch (InvalidPositionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyTreeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void graficarNiveles(Graphics2D e) {
		e.drawImage(background, 0,0,null);
		Arbol<Character> arbol = TestingApplication.miArbol;
		int nivel = 1;
		int renglon = 56, margentxt = 20;
		int index = 0;
		int anchotxt = 100;
		e.setFont(new Font(e.getFont().toString(), Font.BOLD, 20));
		e.setColor(Color.black);
		for (Position<Character> pos : arbol.listadoNiveles().positions()) {
			if (nivel == 1) {
				e.drawImage(hoja, margentxt + anchotxt, renglon - 32, null);
				e.drawString("Nivel 1:", margentxt, renglon);
				e.drawString(pos.element().toString(), margentxt + anchotxt
						+ 16, renglon);
				nivel++;
			} else if (pos.element() == null) {
				Point2D txtLoc = new Point2D.Double(20, renglon * nivel);
				e.drawString("Nivel " + nivel + ":", (int) txtLoc.getX(),
						(int) txtLoc.getY());
				nivel++;
				index = 0;
			} else {
				int locX = anchotxt + margentxt + index * 56, locY = renglon
						* (nivel - 1);
				e.drawImage(hoja, locX, locY - 32, null);
				e.drawString(pos.element().toString(), locX + 16, locY);
				index++;
			}

		}
	}
}
