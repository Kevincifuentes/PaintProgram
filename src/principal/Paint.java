package principal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import excepciones.*;

import javax.swing.*;


/** Clase que permite la visualización del programa, es decir, es la ventana que aparecerá en pantalla.
 * @author kevincifuentes
 *
 */
public class Paint 
{
	public static String figura="";
	public static boolean audioActivado=true;
	private int mins=0;
	private JLabel lTiempoRestante;

	/**
	 * Constructor de la Clase Paint
	 */
	public Paint(){
		
		//Ventana de visualización
		final JFrame elFrame= new JFrame();
		//elFrame.setResizable(false);
		Toolkit tk= Toolkit.getDefaultToolkit();
		Cursor c=tk.createCustomCursor(new ImageIcon("iconos/cursorFlecha.png").getImage(), new Point(0,0), "Cursor Paint");
		elFrame.setCursor(c);
		elFrame.setTitle("Dibuja y Colorea con Pocoyo");
		elFrame.setIconImage(new ImageIcon("iconos/pocoyo.jpeg").getImage());
		elFrame.setLocation(new Point(0,0));
		//DibujarFondo(elFrame.getGraphics(), new Dimension(1000,1000));
		//elFrame.setBackground(c);
		//elFrame.setUndecorated(true);
		elFrame.setSize(new Dimension(700,700));
		elFrame.setLocation(200, 0);
		//elFrame.setMaximumSize(new Dimension(500,500));
		//elFrame.setResizable(false);
		
		//Contenedor
		Container contenedor= (Container)elFrame.getContentPane();
		contenedor.setLayout(new BorderLayout());
		elFrame.setBackground(Lienzo.fondo);
		//Creo una ventana de dibujo
		final Lienzo vLienzo= new Lienzo();
		vLienzo.setSize(elFrame.getSize());
		System.out.println(vLienzo.getSize());
		//Añado al contenedor la ventana de dibujo
		contenedor.add(vLienzo,BorderLayout.CENTER);
		
		//Creo paneles
		JPanel panelD= new JPanel();
		panelD.setLayout(new GridLayout(6,1));
		
		JPanel panelS= new JPanel();
		panelS.setLayout(new GridLayout(1,3));
		
		JPanel panelI= new JPanel();
		panelI.setLayout(new GridLayout(3,1));
		
		JPanel panelInf= new JPanel();
		panelInf.setLayout(new GridLayout(1,4));
		
		//Modifico
		panelD.setPreferredSize(new Dimension(100,100));
		
		//Botones
			//colores
			JButton bBorrar = new JButton("Borrar");
			bBorrar.setIcon(new ImageIcon("iconos/goma.png"));
			bBorrar.setBackground(Color.GRAY);
			JButton bBorrarTodo = new JButton("Borrar Todo");
			bBorrarTodo.setToolTipText("Elimina todo lo que haya en el panel de dibujo");
			JButton bAzul = new JButton("Azul");
			bAzul.setIcon(new ImageIcon("iconos/azul.png"));
			bAzul.setBackground(Color.BLUE);
			JButton bVerde = new JButton("Verde");
			bVerde.setIcon(new ImageIcon("iconos/verde.png"));
			bVerde.setBackground(Color.GREEN);
			JButton bRojo = new JButton("Rojo");
			bRojo.setIcon(new ImageIcon("iconos/rojo.png"));
			bRojo.setBackground(Color.RED);
			
			//Menu 
			JMenuBar mBbarraOpciones= new JMenuBar();
			JMenu mAyuda= new JMenu("Ayuda");
			JMenu mPrograma= new JMenu("Programa");
			
			JMenu mGuardar= new JMenu("Opciones del proyecto");
			JMenuItem mINuevoPro= new JMenuItem("Nuevo");
			mGuardar.add(mINuevoPro);
			JMenuItem mIAbrir= new JMenuItem("Abrir imagen");
			mGuardar.add(mIAbrir);
			JMenuItem mIGuardarComo= new JMenuItem("Guardar imagen");
			mGuardar.add(mIGuardarComo);
			mPrograma.add(mGuardar);
			JMenuItem mISalir= new JMenuItem("Salir");
			mPrograma.add(mISalir);
			
			JMenuItem mIAyudaGeneral=new JMenuItem("Ayuda general");
			mAyuda.add(mIAyudaGeneral);
			JMenuItem mIDesarrollador=new JMenuItem("Acerca del desarollador");
			mAyuda.add(mIDesarrollador);
			JMenuItem mIVersion= new JMenuItem("Versión");
			mAyuda.add(mIVersion);
			
			
			mBbarraOpciones.add(mPrograma);
			mBbarraOpciones.add(mAyuda);
			
			elFrame.setJMenuBar(mBbarraOpciones);
			elFrame.pack();
			//contenedor.add(barraOpciones);
			
			
			//Pincel
			
			JButton bFino= new JButton("Pincel Fino");
			bFino.setToolTipText("Utiliza un pincel de grosura pequeña para dibujar");
			bFino.setIcon(new ImageIcon("iconos/fino.png"));
			JButton bMedio= new JButton("Pincel Medio");
			bMedio.setToolTipText("Utiliza un pincel de grosura media para dibujar");
			bMedio.setIcon(new ImageIcon("iconos/medio.png"));
			JButton grueso= new JButton("Pincel Grueso");
			grueso.setToolTipText("Utiliza un pincel grueso para dibujar");
			grueso.setIcon(new ImageIcon("iconos/grueso.png"));
			
			//Etiqueta
			JLabel lColores=new JLabel("Colores");
			lColores.setToolTipText("Permite colorear con diferentes colores: Verde, Rojo y Azul");
			lColores.setHorizontalAlignment(1/2);
			lColores.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,8));
			//JLabel pocoyo= new JLabel(new ImageIcon("iconos/pocoyoBueno.jpg"));
		
			
			//CheckBox
			JRadioButton jRBLinea = new JRadioButton("Linea",new ImageIcon("iconos/linea.png"));
			jRBLinea.setToolTipText("Permite dibujar una linea recta.");
			jRBLinea.setSelectedIcon(new ImageIcon("iconosalpulsar/linea.png"));
			JRadioButton jRBCirculo=new JRadioButton("Circulo", new ImageIcon("iconos/circulo.png"));
			jRBCirculo.setToolTipText("Permite dibujar un circulo.");
			jRBCirculo.setSelectedIcon(new ImageIcon("iconosalpulsar/circulo.png"));
			final JRadioButton jRBPincel= new JRadioButton("Pincel", new ImageIcon("iconos/pincel.png"));
			jRBPincel.setToolTipText("Permite dibujar con un pincel.");
			jRBPincel.setSelectedIcon(new ImageIcon("iconosalpulsar/pincel.png"));
			JRadioButton jRBCuadrado=new JRadioButton("Cuadrado", new ImageIcon("iconos/cuadrado.png"));
			jRBCuadrado.setSelectedIcon(new ImageIcon("iconosalpulsar/cuadrado.png"));
			jRBLinea.setToolTipText("Permite dibujar un cuadrado.");
			JRadioButton jRBTriangulo= new JRadioButton("Triangulo", new ImageIcon("iconos/triangulo.png"));
			jRBLinea.setToolTipText("Permite dibujar un triángulo.");
			jRBTriangulo.setSelectedIcon(new ImageIcon("iconosalpulsar/triangulo.png"));
			JRadioButton jRBSeleccion= new JRadioButton("Seleccionar", new ImageIcon("iconos/seleccionar.jpeg"));
			jRBSeleccion.setToolTipText("Permite seleccionar las figuras que están dibujadas.");
			jRBSeleccion.setSelectedIcon(new ImageIcon("iconos/seleccionado.jpeg"));
			ButtonGroup botonesGrupo= new ButtonGroup();
			
			botonesGrupo.add(jRBLinea);
			botonesGrupo.add(jRBCirculo);
			botonesGrupo.add(jRBCuadrado);
			botonesGrupo.add(jRBPincel);
			botonesGrupo.add(jRBTriangulo);
			botonesGrupo.add(jRBSeleccion);
			
			//Botones audio
			final JButton bAudio= new JButton(new ImageIcon("iconos/altavoz.gif"));
			
			final Reproductor reproductor= new Reproductor();
			try {
				reproductor.AbrirFichero("audios/pin.wav");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				reproductor.Play();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*final Thread Sonido= new Sonido();
			Sonido.start();*/
			
			//Imprimir
			JButton bImprimir= new JButton(new ImageIcon("iconos/impresora.jpg"));
		
			//Control Parental- Limita el tiempo de utilización del programa
			JButton bControlParental= new JButton("Control Parental");
			lTiempoRestante= new JLabel("", SwingConstants.CENTER);
			bControlParental.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String eleccion;
					if(mins==0)
					{
					eleccion=JOptionPane.showInputDialog(null,"Inserte en número de minutos que quiere que su hijo utilize este programa:","Control Parental",JOptionPane.QUESTION_MESSAGE);
					}
					else
					{
					eleccion=JOptionPane.showInputDialog(null,"¿Desea prorrogar el tiempo? Inserte cuantos minutos:","Tiempo Extra",JOptionPane.QUESTION_MESSAGE);
					System.out.println(eleccion);
					}
					
					try{
						
							int tiempo=Integer.parseInt(eleccion);
							if(tiempo==1 && mins==0)
							{
									try {
										throw new ControlTiempoException("El tiempo elegido es mínimo. Es necesario poner más de un minuto",1);
									} catch (ControlTiempoException e1) {
										sacarMensajeExcepcion(e1.mensajeMostrar,e1.mins);
									}
							}
							else if(tiempo>120)
							{
									try {
										throw new ControlTiempoException("El tiempo máximo recomendado de uso son 2 horas. Por su salud, el tiempo está limitado hasta 120 minutos.", tiempo);
									} catch (ControlTiempoException e1) {
										sacarMensajeExcepcion(e1.mensajeMostrar, e1.mins);
									}
							}
							else{
							mins=mins+tiempo;
								}
						}
						catch(NumberFormatException exc)
						{
							if(eleccion!=null)
							{
							JOptionPane.showMessageDialog(null, "El caracter introducido no es correcto","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						if(mins!=0)
						{
							lTiempoRestante.setText("  "+mins+" minutos restantes");
							final Timer temporizador= new Timer(60000, new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									mins=mins-1;
									if(mins>0)
									{
									lTiempoRestante.setText("  "+mins+" minutos restantes");
									}
									else if(mins==0)
									{
										int eleccion = JOptionPane.showConfirmDialog(null, "¿deseas guardar tu proyecto?","El tiempo ha expirado", 0, 1, new ImageIcon("iconos/pocoyoLlorando.jpg"));
								        if ( eleccion == 0) {
								        	vLienzo.guardarImagen();
								        	try {
												reproductor.Stop();
											} catch (Exception ex) {
												JOptionPane.showMessageDialog(null,"Ocurrió un error y no se pudo cerrar el programa.","Error",JOptionPane.ERROR_MESSAGE);
											}
								        } 
								        System.exit(0);
									}
									
								}
							});
							temporizador.start();
						
					}
					
				}
			});
			
		//Escuchadores
			
			//permite pausar o reanudar el sonido
			bAudio.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if(audioActivado)
					{
						audioActivado=false;
						bAudio.setIcon(new ImageIcon("iconos/altavozapagado.gif"));
						try {
							reproductor.Pausa();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else
					{
						audioActivado=true;
						bAudio.setIcon(new ImageIcon("iconos/altavoz.gif"));
						try {
							reproductor.Continuar();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
					}
				
				
			});
			
			//Muestra un pdf con un manual. Ayuda general
			mIAyudaGeneral.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Desktop d = Desktop.getDesktop();
			        //Verifica que lo soporte el sistema
			        if(d.isDesktopSupported()){
			           try {
						d.open(new File("manual.pdf"));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "El documento de ayuda no se encuentra. Puede que haya sido eliminado.","Error",JOptionPane.ERROR_MESSAGE);
					}
			           
			        }
			        else{
			            //de lo contrario muestra un error
			        	JOptionPane.showMessageDialog(null, "No se puede abrir el archivo de ayuda, el sistema no lo soporta","Error",JOptionPane.ERROR_MESSAGE); 
			        }
				}
				
			});
			
			//Muestra la versión del programa
			mIVersion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					VentanaVersion vv= new VentanaVersion();
					vv.setVisible(true);
					
				}
			});
			
			//Abre un nuevo proyecto, permitiendo guardar el actual
			mINuevoPro.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int eleccion =JOptionPane.showConfirmDialog(null, "¿Quiere guardar el proyecto actual?", null, 0, 0, new ImageIcon(""));
					if(eleccion==0)
					{
						vLienzo.guardarImagen();
					}
					else
					{
						vLienzo.clearAll();
					}
				}
				
			});
			
			//Selecciona la linea
			jRBLinea.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					figura="linea";
					
				}
				
			});
			
			//Selecciona el triangulo
			jRBTriangulo.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					figura="triangulo";
					
				}
				
			});
			
			//Selecciona el cuadrado
			jRBCuadrado.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					figura="cuadrado";
					
					
				}
				
			});
			
			//Selecciona el circulo
			jRBCirculo.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					figura="circulo";
					
				}
				
			});
			
			//Selecciona el pincel
			jRBPincel.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					figura="";
					
				}
				
			});
			//Da la opción de seleccionar las figuras
			jRBSeleccion.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					figura="seleccion";
					
				}
				
			});
			
			//Muestra información sobre el desarrollador
			mIDesarrollador.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					VentanaDesarrollador v1= new VentanaDesarrollador();
					v1.setVisible(true);
				}
				
			});
			
			//Permite salir del programa
			mISalir.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					int eleccion=JOptionPane.showConfirmDialog(null, "¿Deseas abandonar el programa?",null,0,1,new ImageIcon("iconos/pocoyo.jpeg"));
					if(eleccion==0)
					{
						System.exit(0);
					}
					
				}
				
			});
			
			//Permite imprimir el dibujo
			bImprimir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vLienzo.imprimirImagen();
					
				}
			});
			
			//Permite abrir una imagen (aún sin funcionabilidad)
			mIAbrir.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					vLienzo.abrirImagen();
					
				}
				
			});
			
			//Permite guardar la imagen
			mIGuardarComo.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					vLienzo.guardarImagen();
				}
				
			});
			
			//Borra todo lo dibujado
			bBorrarTodo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					vLienzo.clearAll();
				}
			});
			
			//permite dibujar con pincel fino
			bFino.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					vLienzo.setPincel("Fino");
				}
			});
			
			//permite dibujar con pincel medio
			bMedio.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					vLienzo.setPincel("Medio");
				}
			});
			
			//permite dibujar con pincel grueso
			grueso.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					vLienzo.setPincel("Grueso");
				}
			});
		
			
			//Borra lo que esta visualizado en la VentanaDibujo
			bBorrar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					figura="";
					jRBPincel.setSelected(true);
					vLienzo.clear();
				}
			});
		
			//boton azul
			bAzul.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					vLienzo.azul();
				}
			});
			
			//boton rojo
			bRojo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					vLienzo.rojo();
				}
			});
			
			//boton verde
			bVerde.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					vLienzo.verde();
				}
			});
		
		
		
		bVerde.setPreferredSize(new Dimension(10,10));
		bBorrar.setPreferredSize(new Dimension(10,10));
		bAzul.setPreferredSize(new Dimension(10,10));
		bRojo.setPreferredSize(new Dimension(10,10));
		
		
		
		//Añadimos
		
		panelD.add(bBorrar);
		panelD.add(bBorrarTodo);
		panelD.add(lColores);
		panelD.add(bVerde);
		panelD.add(bRojo);
		
		panelD.add(bAzul);
		panelS.add(bFino);
		panelS.add(bMedio);
		panelS.add(grueso);
		
		panelI.add(jRBLinea);
		panelI.add(jRBCirculo);
		panelI.add(jRBCuadrado);
		panelI.add(jRBTriangulo);
		panelI.add(jRBPincel);
		panelI.add(jRBSeleccion);
		
		panelInf.add(bAudio);
		panelInf.add(bImprimir);
		panelInf.add(bControlParental);
		panelInf.add(lTiempoRestante);
		
		
		contenedor.add(panelD,BorderLayout.WEST);
		contenedor.add(panelS,BorderLayout.NORTH);
		contenedor.add(panelI,BorderLayout.EAST);
		contenedor.add(panelInf,BorderLayout.SOUTH);
		
		elFrame.setSize(1000, 1000);
		elFrame.setVisible(true);
		
		elFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		elFrame.addWindowListener(new WindowAdapter(){
			
		    public void windowClosing(WindowEvent we){
		        int eleccion = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres dejarme?",null, 0, 1, new ImageIcon("iconos/pocoyoLlorando.jpg"));
		        if ( eleccion == 0) {
		        	JOptionPane.showMessageDialog(null, "Saliendo del programa. ¡Vuelve con Pocoyo!", "Cierre de Dibuja y Colorea con Pocoyo", 0, new ImageIcon("iconos/pocoyo.jpeg"));
		        	try {
						reproductor.Stop();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
		            System.exit(0);
		            
		        }   
	
		    }
		
		});
		
		jRBPincel.setSelected(true);
		
	}
	
	
	protected void sacarMensajeExcepcion(String mensajeMostrar, int minutos) {
		
		Thread HiloExcepcion= new HiloExcepcion(mensajeMostrar, minutos);
		HiloExcepcion.start();
		
	}


	public static void main(String[]args)
	{
		
//		VentanaEleccion vEleccion= new VentanaEleccion();
//		vEleccion.setVisible(true);
//		vEleccion.setAlwaysOnTop(true);
		Paint o = new Paint();
		//vEleccion.setLocationRelativeTo(o);
	
		
	}
	//set opaque, y que vaya desapareciendo
	

}
	


	
//Pruebas de sonido

/*class Sonido extends Thread{
	public void run()
	{
		try {
            FileInputStream fis;
            Player player;
            fis = new FileInputStream("audios/mythica.mp3");
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new Player(bis); // Llamada a constructor de la clase Player
            player.play();          // Llamada al método play
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		
		
		/*AudioClip sonido= java.applet.Applet.newAudioClip(getClass().getResource("audios/mythica.mp3"));
		if(Paint.audioActivado)
		{
			sonido.stop();
		}
		else
		{
			sonido.play();
		}*/
	




