package Application;

import javax.swing.UIManager;

/**
 * 
 * @author nico
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String[] args) {
		// TODO code application logic here
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
