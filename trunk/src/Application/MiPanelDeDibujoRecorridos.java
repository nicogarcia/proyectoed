package Application;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import Excepciones.EmptyTreeException;
import Excepciones.InvalidPositionException;
import GeneralTree.Arbol;
import GeneralTree.TNode;
import TDALista.Position;
import Application.TestingApplication;

/**
 * 
 * @author nico
 */

//TODO BORRAR ESTA CLASE!
public class MiPanelDeDibujoRecorridos extends Canvas {

	private Color marron = new Color(84, 25, 0), verde = new Color(0, 125, 0);
	private JRadioButton radio;
	
	public void recorrido(JRadioButton radio) {
		this.radio=radio;
		this.repaint();
	}

	public void paint(Graphics e) {
		Graphics2D g = (Graphics2D) e;
		Dimension2D size = (Dimension2D) getSize();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if(TestingApplication.started)
			graficarRecorrido(g);
	}

	private void graficarRecorrido(Graphics2D e) {
		Arbol<Character> arbol = TestingApplication.miArbol;
		if (!arbol.isEmpty()) {
			Ellipse2D elipse;
			Dimension2D size = new Dimension(30, 30);
			int i = 0;
			
			for (Position<Character> pos : arbol.posOrderPositions()) {
				elipse = new Ellipse2D.Double();
				Point2D loc = new Point2D.Double(20 + 50 * i, 20);
				elipse.setFrame(loc, size);
				i++;
				e.setColor(verde);
				e.draw(elipse);
				e.fill(elipse);
				e.setFont(new Font(e.getFont().toString(), Font.BOLD, 10));
				e.setColor(Color.white);
				e.drawString(pos.element().toString(), (int) (loc.getX()
						+ size.getWidth() / 2 - 5), (int) (loc.getY()
						+ size.getHeight() / 2 + 3));
			}
		}
	}
}
