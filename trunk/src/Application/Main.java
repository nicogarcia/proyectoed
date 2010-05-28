package Application;

import javax.swing.UIManager;

/**
 * Programa principal
 * 
 * @author Martin Schiaffino [93718]
 * @author Nicolas Garcia [93078]
 * 
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		NewJFrame gui = new NewJFrame();
		gui.setTitle("Proyecto Esctructuras de Datos 2010" );
		gui.setVisible(true);
	}
}
