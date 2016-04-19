package principal;

import java.awt.*;

import javax.swing.JFrame;

/** Clase que permite cerrar la ventana haciendola transparente poco a poco.
 * No tiene funcionalidad con el programa, ya que no funcionaba correctamente.
 * @author kevincifuentes
 *
 */
public class VentanaPrincipal extends JFrame{
	
	/**
	 * Constructor por defecto de la VentanaPrincipal
	 */
	public VentanaPrincipal()
	{

//	        if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
//	            System.err.println(
//	                "Transulencia no soportada");
//	                System.exit(0);}
	        
	}
	public void cerrarPrincipal()
	{
		GraphicsEnvironment ge = 
	            GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice gd = ge.getDefaultScreenDevice();
	        
		HiloCerrar cerrarVentana= new HiloCerrar(this);
		cerrarVentana.start();
		
	}
	
	class HiloCerrar extends Thread
	{
		JFrame ventana;
		HiloCerrar(JFrame v)
		{
			ventana=v;
		}
		public void run()
		{
//			 SwingUtilities.invokeLater(new Runnable() {
//		            @Override
//		            public void run() {
//		                VentanaPrincipal tw = new VentanaPrincipal();
//		 		
//		                while(tw.getOpacity()>0)
//						{
//							tw.setOpacity(window.getOpacity()-0.5f);
//							try {
//
//								Thread.sleep(100);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//		            }
//		        });
			
		}
	}
	
}
