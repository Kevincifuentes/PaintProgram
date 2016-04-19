package principal;

import java.awt.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.*;
import java.awt.peer.*;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

import contrib.com.blogofbug.utility.ImageUtilities;
import clasesBasicas.*;
//import excepciones.DibujoException;

/** Clase Lienzo, la cual es un JComponent. Es la que recrea el lugar de dibujo.
 * @author kevincifuentes
 *
 */
public class Lienzo extends JComponent{

	private static final long serialVersionUID = 1L;
	//donde dibujaremos
	private Image imagen;
	 //lo que nos permitirá dibujar
	private Graphics2D g2D;
	private int grosuraPincel=1;
	private Vector <Figura>figurasGuardadas= new Vector<Figura>();
	private Figura figActual;
	int xActual,yActual,xVieja,yVieja;
	private boolean dibujandoFig=false;
	private Color colorActual=Color.BLACK;
	public static Color fondo=null;
	private JPopupMenu jPMmenu;
	private JComponent jLienzo;
	private Point puntoPopUp;
	private boolean borrando=false;
	private File ficheroAImprimir;
	private Image gD;
	
	/**
	 * Constructor de Clase Lienzo
	 */
	public Lienzo()
	{
		jLienzo=this;
		//Cursor Personalizado
		Toolkit tk= Toolkit.getDefaultToolkit();
		Cursor c=tk.createCustomCursor(new ImageIcon("iconos/myPencil.gif").getImage(), new Point(0,16), "Cursor Lienzo");
		this.setCursor(c);
		
		//Menu popup
		jPMmenu= new JPopupMenu();
		JMenuItem mIRellenar= new JMenuItem("Rellenar figura", new ImageIcon("iconos/rellenar.jpeg"));
		JMenuItem mIEliminarFig= new JMenuItem("Eliminar", new ImageIcon("iconos/eliminar.jpeg"));
		jPMmenu.add(mIRellenar);
		jPMmenu.add(mIEliminarFig);
		
		//Imagen de fondo (servirá para el pincel de dibujo)
		
		
		//escuchadores
		mIRellenar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Point este=puntoPopUp;
				obtenerFiguraEnEsaArea(este.x, este.y);
				
				if(figActual!=null)
				{
					figActual.rellenar(g2D);
					repaint();
					figurasGuardadas.remove(figActual);
					figurasGuardadas.add(figActual);
				}
				puntoPopUp=null;
				figActual=null;
				
			}
		});
		
		mIEliminarFig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Point este=puntoPopUp;
				obtenerFiguraEnEsaArea(este.x, este.y);
				System.out.println("AQUIII"+figActual);
				
				if(figActual!=null)
				{
					figurasGuardadas.remove(figActual);
					System.out.println(figurasGuardadas.toString());
					figActual=null;
					
					Image g2=ImageUtilities.copyImage((BufferedImage) gD);
					imagen=g2;
					g2D=(Graphics2D) g2.getGraphics();
					g2D.setPaint(colorActual);
					
					//pongo por defecto en negro
					g2D.setPaint(Color.BLACK);
					paint(g2D);
					repaint();
					
				}
				puntoPopUp=null;
				
			}
		});
			//AGREGO EL MENU
			this.add(jPMmenu);
			
		
		//visualizado=jf;
		this.setBorder(BorderFactory.createLineBorder(Color.RED,5));
		this.setBackground(fondo);
		//jf.setBackground(Color.WHITE);
		//Mientras este pulsado el raton, se van cambiando las coordenadas
		
		//No lo dibuja en buffer secundario
		this.setDoubleBuffered(false);
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.isPopupTrigger())
				{
					puntoPopUp=e.getPoint();
					jPMmenu.show(jLienzo,e.getX(),e.getY());
					
				}
				else
				{
				xVieja = e.getX();
				yVieja = e.getY();
				if(!Paint.figura.equalsIgnoreCase("seleccion"))
				{
					dibujandoFig=true;
				}
				else
				{
					
					figActual=null;
					if(!figurasGuardadas.isEmpty())
					{
						obtenerFiguraEnEsaArea(xVieja,yVieja);
					}
				}
			}
			}
		});
		
		
		//hace una linea en donde el raton se va moviendo, cambiando las antiguas coordenadas por las actuales
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				if(!e.isPopupTrigger())
				{
					
					if(Paint.figura.equals(""))
					{

//						Image g2=ImageUtilities.copyImage((BufferedImage) gD);
//						imagen=g2;
//						g2D=(Graphics2D) g2.getGraphics();
//						g2D.setPaint(colorActual);
						
						xActual= e.getX();
						yActual = e.getY();
						
							if(g2D != null)
								g2D.drawLine(xVieja, yVieja, xActual, yActual);
							if(!figurasGuardadas.isEmpty() && !borrando){
								paint(g2D);
								
								}
						xVieja = xActual;
						yVieja = yActual;
						repaint();
						
						gD=ImageUtilities.copyImage((BufferedImage) imagen);
						
						//	imagen = createImage(getSize().width, getSize().height);
					
					}
					else
					{
						
						int xAnterior=xActual;
						int yAnterior=yActual;
						xActual= e.getX();
						yActual = e.getY();
						if(Paint.figura.equals("circulo"))
						{
							if(g2D != null){
								//g2D.setPaint(Color.WHITE);
								//g2D.drawOval(xVieja, yVieja, xAnterior, yAnterior);
								//g2D.fillRect(0, 0, getSize().width, getSize().height);
								//pongo por defecto en negro
							//g2D.setPaint(getBackground());
//							g2D.setPaint(fondo);
//							g2D.fillRect(0, 0, getSize().width, getSize().height);
							
							//pongo por defecto en negro
//							g2D.setPaint(Color.BLACK);
							Image g2=ImageUtilities.copyImage((BufferedImage) gD);
							imagen=g2;
							g2D=(Graphics2D) g2.getGraphics();
							g2D.setPaint(colorActual);
							
							paint(g2D);
						//	g2D.setPaint(colorActual);
							g2D.drawOval(xVieja, yVieja, (xActual-xVieja), (yActual-yVieja));
							System.out.println("dibujando");
							System.out.println("x0,y0= "+xVieja+"-"+yVieja);
							System.out.println("x1,y1= "+xActual+"-"+yActual);
							repaint();
//								xVieja = xActual;
//								yVieja = yActual;
							}
							
						}
						else if(Paint.figura.equals("cuadrado"))
						{
	
							if(g2D != null)
							{
								//el punto anterior
	//							int anchoanterior=xVieja-xAnterior;
	//							int altoanterior=xVieja-xAnterior;
								
								int ancho= Math.abs(xVieja-xActual);
								int alto= Math.abs(xVieja-xActual);
								
	//							g2D.setPaint(Color.WHITE);
	//							g2D.drawRect(xVieja, yVieja, anchoanterior, altoanterior);
								//g2D.setPaint(getBackground());
								/*Image g=imagen;
								imagen = createImage(getSize().width, getSize().height);
								*/
//								Image g=imagen.getScaledInstance(getSize().width, getSize().height, java.awt.Image.SCALE_DEFAULT);
//								
//								g2D = (Graphics2D)imagen.getGraphics();
//								g2D.setBackground(fondo);
//								g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
								
								//Image g=ImageUtilities.copyImage((BufferedImage) imagen);
								
//								g2D.setPaint(fondo);
//								g2D.fillRect(0, 0, getSize().width, getSize().height);
								
								Image g2=ImageUtilities.copyImage((BufferedImage) gD);
								imagen=g2;
								g2D=(Graphics2D) g2.getGraphics();
								g2D.setPaint(colorActual);
								//imagen=g2;
								//g2D=(Graphics2D) imagen.getGraphics();
								paint(g2D);
								g2D.setPaint(colorActual);
								g2D.drawRect(xVieja, yVieja, ancho, alto);
								//repaint();
//								imagen=g;
//								g2D=(Graphics2D) imagen.getGraphics();
								repaint();
							}
						}
						else if(Paint.figura.equals("triangulo"))
						{
							Image g2=ImageUtilities.copyImage((BufferedImage) gD);
							imagen=g2;
							g2D=(Graphics2D) g2.getGraphics();
							g2D.setPaint(colorActual);
							//imagen=g2;
							//g2D=(Graphics2D) imagen.getGraphics();
							paint(g2D);
							g2D.setPaint(colorActual);
							g2D.drawLine(xVieja, yActual, xActual, yActual);
							g2D.drawLine(xVieja, yActual, (xActual)/2, yVieja);
							g2D.drawLine(xActual, yActual, (xActual)/2, yVieja);
							//repaint();
//							imagen=g;
//							g2D=(Graphics2D) imagen.getGraphics();
							repaint();
							
//							g2D.setPaint(fondo);
//							g2D.fillRect(0, 0, getSize().width, getSize().height);
//							g2D.setPaint(colorActual);
//							paint(g2D);
//							g2D.setPaint(colorActual);
//							g2D.drawLine(xVieja, yActual, xActual, yActual);
//							g2D.drawLine(xVieja, yActual, (xActual)/2, yVieja);
//							g2D.drawLine(xActual, yActual, (xActual)/2, yVieja);
//							repaint();
						}
						else if(Paint.figura.equals("linea"))
						{
							Image g2=ImageUtilities.copyImage((BufferedImage) gD);
							imagen=g2;
							g2D=(Graphics2D) g2.getGraphics();
							g2D.setPaint(colorActual);
							//imagen=g2;
							//g2D=(Graphics2D) imagen.getGraphics();
							paint(g2D);
							g2D.setPaint(colorActual);
							g2D.drawLine(xVieja, yVieja, xActual, yActual);
							//repaint();
//							imagen=g;
//							g2D=(Graphics2D) imagen.getGraphics();
							repaint();
							
//							g2D.setPaint(fondo);
//							g2D.fillRect(0, 0, getSize().width, getSize().height);
//							g2D.setPaint(colorActual);
//							paint(g2D);
//							g2D.setPaint(colorActual);
//							g2D.drawLine(xVieja, yVieja, xActual, yActual);
//							repaint();
						}
						else if(Paint.figura.equalsIgnoreCase("seleccion"))
						{
							xActual = e.getX();
							yActual = e.getY();
							if(figActual!=null)
							{
//								g2D.setPaint(fondo);
//								g2D.fillRect(0, 0, getSize().width, getSize().height);
//								g2D.setPaint(figActual.getColor());
								Image g2=ImageUtilities.copyImage((BufferedImage) gD);
								imagen=g2;
								g2D=(Graphics2D) g2.getGraphics();
								g2D.setPaint(colorActual);
								
								paint(g2D);
								g2D.setPaint(figActual.getColor());
								int sumaAX=xActual-xVieja;
								int sumaAY=yActual-yVieja;
								xVieja=xVieja+sumaAX;
								yVieja=yVieja+sumaAY;
								figActual.setIni(new Point(figActual.getIni().x+sumaAX,figActual.getIni().y+sumaAY));
								figActual.setFin(new Point(figActual.getFin().x+sumaAX,figActual.getFin().y+sumaAY));
								figActual.dibujar(g2D, figActual.getIni(), figActual.getFin());
								if(figActual.rellenado)
								{
									figActual.rellenar(g2D);
								}
								repaint();
								
							}
					}
				}
			}
			}
		});
		
		this.addMouseListener(new MouseAdapter(){
			
			public void mouseReleased(MouseEvent e){
				if(puntoPopUp==null){
				if(!Paint.figura.equals(""))
				{
					if(Paint.figura.equals("circulo"))
					{
						//pruebas
						System.out.println("GUARDADO");
						System.out.println("x0,y0= "+xVieja+"-"+yVieja);
						System.out.println("x1,y1= "+e.getPoint().getX()+"-"+e.getPoint().getY());
						
						Circulo c= new Circulo(new Point(xVieja,yVieja),e.getPoint());
						c.setColor(colorActual);
						c.setGrosura(grosuraPincel);
						figActual=c;
						figurasGuardadas.add(c);
						
					}
					else if(Paint.figura.equals("cuadrado"))
					{
						System.out.println("GUARDADO");
						Cuadrado c= new Cuadrado(new Point(xVieja,yVieja),e.getPoint());
						c.setColor(colorActual);
						c.setGrosura(grosuraPincel);
						figActual=c;
						figurasGuardadas.add(c);
						
					}
					else if(Paint.figura.equals("linea"))
					{
						System.out.println("GUARDADO");
						Linea c= new Linea(new Point(xVieja,yVieja),e.getPoint());
						c.setColor(colorActual);
						c.setGrosura(grosuraPincel);
						figActual=c;
						figurasGuardadas.add(c);
						
					}
					else if(Paint.figura.equals("triangulo"))
					{
						System.out.println("GUARDADO");
						Triangulo t = new Triangulo(new Point(xVieja,yVieja), e.getPoint());
						t.setColor(colorActual);
						t.setGrosura(grosuraPincel);
						figActual=t;
						figurasGuardadas.add(t);
						
					}
					else if(Paint.figura.equals("seleccion") && figActual!=null)
					{
						System.out.println("GUARDADO");
						figurasGuardadas.add(figActual);
						figActual=null;
						colorActual= Color.BLACK;
					}
					
				}
				dibujandoFig=false;
				xVieja=0;
				yVieja=0;
				xActual=0;
				yActual=0;	
				
			}
			}
		});
		
		
		
	}
	
	/**Metodo que permite obtener la figura (guardandolo en la variable figActual) en la cual está el punto (x,y)
	 * @param x El valor de x del punto
	 * @param y El valor de y del punto
	 */
	public void obtenerFiguraEnEsaArea(int x, int y) {
		
		for(int i=figurasGuardadas.size()-1;i>=0;i--)
		{
			figActual=figurasGuardadas.get(i);
			System.out.println(figActual);
			System.out.println(figActual.getIni().toString()+figActual.getFin().toString());
			System.out.println("x="+xVieja+" y="+yVieja);
			figActual=figActual.contiene(x,y);
			System.out.println(figActual);
			if(figActual!=null)
			{
				figActual=figurasGuardadas.remove(i);
				System.out.println("ENCONTRADO");
				break;
				
			}
			else
			{
			figActual=null;
			}
		}
		
	}

	//El Graphics2D es inicializado con la imagen, siempre que no haya nada en la imagen. En esta imagen dibujaremos.
	//Se ha modificado el paintcomponent. Crea la imagen si no existe y la dibuja.
	public void paintComponent(Graphics g){
		if(imagen == null){
			imagen = createImage(getSize().width, getSize().height);
			g2D = (Graphics2D)imagen.getGraphics();
			g2D.setBackground(fondo);
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setStroke(new BasicStroke(grosuraPincel));
			//creo otra imagen, la cual me servirá para lo que pinte con el pincel.
			gD=createImage(getSize().width, getSize().height);
			((Graphics2D)(gD.getGraphics())).setBackground(fondo);
			((Graphics2D)(gD.getGraphics())).setStroke(new BasicStroke(grosuraPincel));
			 ((Graphics2D)gD.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//clear();
		}
		g.drawImage(imagen, 0, 0, null);	
	}

	/**
	 * Utiliza la goma con el pincel, lo que permite borrar lo dibujado;
	 */
	public  void clear() {
		borrando=true;
		g2D.setStroke(new BasicStroke(10));
		//g2D.setPaint(this.getBackground());
		System.out.println(fondo);
		System.out.println(g2D.getBackground());
		System.out.println(this.getBackground());
		g2D.setPaint(fondo);
		colorActual=fondo;
		repaint();
	}
	
	
	/**
	 * Borra todo lo que estaba en la VentanaDibujo, no se puede recuperar.
	 */
	public void clearAll()
	{
		//g2D.setPaint(this.getBackground());
		g2D.setPaint(fondo);
		System.out.println(fondo);
		g2D.fillRect(0, 0, getSize().width, getSize().height);
		//pongo por defecto en negro
		figurasGuardadas.removeAllElements();
		repaint();
		g2D.setPaint(colorActual);
	}
	
	

	/**
	 * Cambia el color a rojo
	 */
	public void rojo() {
		borrando=false;
		colorActual=Color.RED;
		g2D.setPaint(Color.RED);
		repaint();
	}

	/**
	 * Cambia el color a azul
	 */
	public  void azul() {
		borrando=false;
		colorActual=Color.BLUE;
		g2D.setPaint(Color.BLUE);
		repaint();
	}	
	
	/**
	 * Cambia el color a verde
	 */
	public void verde() {
		borrando=false;
		colorActual=Color.GREEN;
		g2D.setPaint(Color.GREEN);
		repaint();
	}

	
	/**
	 * Guarda la imagen en el directorio preterminado
	 * @throws Si no se encuentra el fichero donde hay que guardar la imagen, lanzará IOException.
	 */
	public void guardarImagen() {
		//JFileChooser
		VentanaGuardado vG= new VentanaGuardado(true);
		
		if(vG.getFile()!=null)
		{
		System.out.println(vG.getFile().getPath());
		String nombre=JOptionPane.showInputDialog(null, null, "¿Nombre del proyecto?", JOptionPane.PLAIN_MESSAGE);
		if(nombre!=null)
		{
			File fGuardar= new File(vG.getFile().getPath()+"/"+nombre+".jpg");
			ficheroAImprimir=fGuardar;
			Image i=imagen;
			g2D.setBackground(fondo);
			try {
					ImageIO.write((RenderedImage) i, "jpg", fGuardar); // guarda la imagen en el fichero
				} catch (IOException ex) {
						
					JOptionPane.showMessageDialog(null, "Error al guardar la imagen. Se ha cancelado su guardado.","Error",JOptionPane.ERROR_MESSAGE);
				}
			
			if(fGuardar.exists())
			{
				JOptionPane.showMessageDialog(null, "La foto se ha guardado correctamente","¡Éxito!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		}
		
		
	}
	
/**
 * Abre la imagen seleccionada
 */
public void abrirImagen() {
		//No me funciona correctamente. No hay error, pero no me abre la imagen.
		VentanaGuardado vG= new VentanaGuardado(false);
		System.out.println(vG.getFile());
		File fAbrir= vG.getFile();
		Image i;
		g2D.drawImage(new ImageIcon(fAbrir.getParent()).getImage(), 0, 0, null);	
		System.out.println("aqui");
	}	

	/**
	 * Metodo que cambia el diametro del pincel
	 * @param s String que determina el diametro del pincel
	 */
	public void setPincel(String s) {

		if(s.equalsIgnoreCase("fino"))
		{
			grosuraPincel=5;	
		}
		else if(s.equalsIgnoreCase("medio"))
		{
			grosuraPincel=10;
		}
		else
		{
			grosuraPincel=15;
		}
		g2D.setStroke(new BasicStroke(grosuraPincel));
	}
	
	/**Dibuja todas las figuras que se hayan dibujado hasta el momento
	 * @param g El parametro de Graphics del Lienzo
	 */
	public void paint(Graphics2D g)
	{
		for( int i =0;i<figurasGuardadas.size(); i++)
		{
			Figura fig= (Figura)figurasGuardadas.elementAt(i);
			System.out.println(fig);
			System.out.println("redibujando");
			System.out.println("x0,y0= "+fig.getIni().getX()+"-"+fig.getIni().getY());
			System.out.println("x1,y1= "+fig.getFin().getX()+"-"+fig.getFin().getY());
			g2D.setColor(fig.getColor());
			g2D.setStroke(new BasicStroke(fig.getGrosura()));
			fig.dibujar(g, fig.getIni(), fig.getFin());
			if(fig.rellenado)
			{
				fig.rellenar(g2D);
			}
			g2D.setColor(colorActual);
			g2D.setStroke(new BasicStroke(grosuraPincel));
		}
	}

	/**
	 * Metodo que permite imprimir la imagen del lienzo. Es obligatorio guardar primero la imagen.
	 * @throws Si no se encuentra el fichero a imprimir, lanzará IOException.
	 */
	public void imprimirImagen() {
		JOptionPane.showMessageDialog(null, "Primero debes guardar la imagen.", "Impresión", JOptionPane.INFORMATION_MESSAGE);
		this.guardarImagen();
		Desktop d= Desktop.getDesktop();
		
		if(d.isDesktopSupported())
		{
			try {
				if(ficheroAImprimir!=null)
				{
				d.print(ficheroAImprimir);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "No pudo realizarse la impresión. Compruebe que tiene una impresora conectada.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
	}
	
	
	
}
