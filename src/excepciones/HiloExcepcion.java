package excepciones;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/** Hilo que realiza la aparición de una ventana de advertencia para la Excepción de tiempo.
 * @author kevincifuentes
 *
 */
public class HiloExcepcion extends Thread{
	
		private String mensaje;
		private int mins;
		
		/** Constructor del hilo
		 * @param mensaje El mensaje que se debe mostrar
		 * @param minutos El número de minutos que se selecciono
		 */
		public HiloExcepcion( String mensaje, int minutos) {
			this.mensaje = mensaje;
			this.mins=minutos;
		}
		
		private JFrame ventana;
		
		public void run() {
			
			ventana = new JFrame();
			ventana.setTitle( "Minutos Erroneos" );
			ventana.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
			ventana.setSize( 400, 100 );
			ventana.setLocation(500, 500);
			
			ventana.setLayout(new BorderLayout());
			JLabel etiqueta= new JLabel("",SwingConstants.CENTER);
			JButton cerrar= new JButton("Cerrar");
			ventana.add(etiqueta, BorderLayout.CENTER);
			ventana.add(cerrar,BorderLayout.SOUTH);
			cerrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ventana.dispose();
					
				}
			});
			
			etiqueta.setText("Has seleccionado: "+mins+" minutos");
			ventana.setVisible(true);
			
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int pos=mensaje.indexOf(".");
			String mensaje1=mensaje.substring(0,pos);
			etiqueta.setText(mensaje1);
			String mensaje2= mensaje.substring(pos+1);
			
			
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			etiqueta.setText(""+mensaje2);
			
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ventana.dispose();
		}
	
}
