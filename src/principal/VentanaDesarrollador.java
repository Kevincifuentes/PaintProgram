package principal;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.*;

/** Ventana que muestrá información sobre el desarrollador
 * @author kevincifuentes
 *
 */
public class VentanaDesarrollador extends JFrame
{
	
	/**
	 * Constructor por defecto de la VentanaDesarrollador
	 */
	public VentanaDesarrollador()
	{
		this.setTitle("Información sobre el desarrollador");
		this.setMinimumSize(new Dimension(650,300));
		this.setMaximumSize(new Dimension(500,300));
		this.setLocation(500, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Panel Principal
		JPanel principal= new JPanel();
		principal.setLayout(new BorderLayout());
		//Panel del medio
		JPanel pMedio= new JPanel();
		pMedio.setLayout(new GridLayout(2,1));
		//Panel sur
		JPanel sur= new JPanel();
		
		//Componentes diferentes
		JLabel ldescripcion1 = new JLabel("Programa desarrollado por Mongatanga.");
		ldescripcion1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel ldescripcion2 = new JLabel("Estudiante de la Universidad de Deusto.");
		ldescripcion2.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel icono= new JLabel(new ImageIcon("iconos/deusto.gif"));
		JButton contactarMail = new JButton("Contactar con Mongatanga o Reportar un error.",new ImageIcon("iconos/correo.png"));
		JButton rellenarEncuesta= new JButton("¡Me importa tu opinión!",new ImageIcon("iconos/encuesta.png"));
		
		sur.add(contactarMail);
		sur.add(rellenarEncuesta);
		pMedio.add(ldescripcion1);
		pMedio.add(ldescripcion2);
		principal.add(icono,"North");
		principal.add(pMedio,"Center",(int) JPanel.CENTER_ALIGNMENT);
		principal.add(sur,"South");
		this.add(principal);
		this.pack();
		
		contactarMail.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Desktop d=Desktop.getDesktop();
				try {
					//para, cc, bcc, asunto, cuerpo, adjunto
					String message = "mailto:monga-tanga@hotmail.com?subject=Programa%20Pinta%20y%20Colorea%20con%20Pocoyo";
					URI uri = URI.create(message);
					d.mail(uri);
				} catch (IOException e) {
					
					JOptionPane.showMessageDialog(null, "Ocurrio un error con su cliente de email.");
					e.printStackTrace();
				}
				
			}
			
		});
		rellenarEncuesta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Desktop d=Desktop.getDesktop();
				try {
					d.browse(URI.create("https://docs.google.com/forms/d/1ofdM1XVQAA8cGQ9SBw3_LNvCLJcVdOB2k2LSXTIt9Ww/viewform"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "No fue posible acceder a la página especificada.");
					e1.printStackTrace();
				}
				
			}
			
			
			
		});
		
	}
	public static void main(String[]args)
	{
		VentanaDesarrollador vE= new VentanaDesarrollador();
		vE.setVisible(true);
		
	}
	
}
