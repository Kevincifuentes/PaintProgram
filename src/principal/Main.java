package principal;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.beeger.squareness.SquarenessLookAndFeel;

/** Clase Main que ejecuta el programa completo.
 * @author kevincifuentes
 *
 */
public class Main {
	
	public static void main(String[]args)
	{
		try {
			UIManager.setLookAndFeel(new SquarenessLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VentanaEleccion vE= new VentanaEleccion();
		vE.setVisible(true);
		
	}
}
