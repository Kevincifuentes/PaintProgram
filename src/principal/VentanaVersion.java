package principal;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

/** Ventana que determina la versión del programa y permite mirar si hay actualizaciones del programa.
 * @author kevincifuentes
 *
 */
public class VentanaVersion extends JFrame{
	
	private JButton actualizacion;
	private JLabel version;
	
	/**
	 *  Constructor por defecto de la VentanaVersion
	 */
	public VentanaVersion()
	{
		Container c= this.getContentPane();
		c.setLayout(new BorderLayout());
		
		this.setMinimumSize(new Dimension(400,300));
		this.setLocation(400, 400);
		
		version= new JLabel("Su programa tiene la version 1.0",JLabel.CENTER);
		actualizacion=new JButton("Buscar actualizaciones",new ImageIcon("iconos/actualizar.png"));
		c.add(version,"Center");
		c.add(actualizacion,"South");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[]args)
	{
		VentanaVersion vE= new VentanaVersion();
		vE.setVisible(true);
		
	}

}
