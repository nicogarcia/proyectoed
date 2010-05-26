package Application;

import java.awt.*;

public class MiPanelDeDibujoArbol extends Canvas {

	public void paint(Graphics e) {
		Graphics2D g = (Graphics2D) e;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (TestingApplication.started)
			if (getName().equals("canvas0"))
				if (NewJFrame.selectedArbol)
					((NewJFrame) this.getParent()).graficarArbol(g);
				else
					((NewJFrame) this.getParent()).graficarNiveles(g);
			else if (getName().equals("canvas1"))
				((NewJFrame) this.getParent()).graficarRecorrido(g);
			else
				((NewJFrame) this.getParent()).graficarPila(g);
	}

}