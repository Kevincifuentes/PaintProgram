package principal;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/** Ventana que permite la elección del color de fondo del lienzo.
 * @author kevincifuentes
 *
 */
public class VentanaEleccion extends JFrame implements ActionListener
{
	private JButton azul;
	private JButton rojo;
	private JButton naranja;
	private JButton rosa;
	private JButton verde;
	private JButton gris;
	private JButton amarillo;
	private JButton blanco;
	private String eleccion;
	
	
	/**
	 * Constructor por defecto de la VentanaEleccion
	 */
	public VentanaEleccion()
	{
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		
		this.setLocation(400,200);
		this.setSize(new Dimension(650,400));
		//this.setMinimumSize(new Dimension(650,400));
		this.setTitle("Elije el tipo de fondo a utilizar");
		Container c= (Container)getContentPane();
		c.setLayout(new GridLayout(2, 4));
		
		azul= new JButton("Azul",new ImageIcon("iconos/azulf.png"));
		rojo= new JButton("Rojo",new ImageIcon("iconos/rojof.png"));
		verde= new JButton("Verde",new ImageIcon("iconos/verdef.png"));
		gris= new JButton("Gris",new ImageIcon("iconos/gris.png"));
		amarillo= new JButton("Amarillo",new ImageIcon("iconos/amarillo.png"));
		rosa= new JButton("Morado",new ImageIcon("iconos/rosa.png"));
		blanco= new JButton("Hoja en Blanco",new ImageIcon("iconos/blanco.png"));
		naranja= new JButton("Naranja",new ImageIcon("iconos/naranja.png"));
		
		c.add(blanco);
		c.add(azul);
		c.add(rojo);
		c.add(verde);
		c.add(gris);
		c.add(amarillo);
		c.add(naranja);
		c.add(rosa);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		blanco.addActionListener(this);
		azul.addActionListener(this);
		rojo.addActionListener(this);
		verde.addActionListener(this);
		gris.addActionListener(this);
		naranja.addActionListener(this);
		rosa.addActionListener(this);
		amarillo.addActionListener(this);
		
		
	}
	public static void main(String[]args)
	{
		VentanaEleccion vE= new VentanaEleccion();
		vE.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source=arg0.getSource();
		
		if(source==blanco)
		{
			Lienzo.fondo=Color.WHITE;
		}
		else if(source==rojo)
		{
			Lienzo.fondo=Color.RED;
		}
		else if(source==amarillo)
		{
			Lienzo.fondo=Color.YELLOW;
		}
		else if(source==rosa)
		{
			Lienzo.fondo=Color.PINK;
		}
		else if(source== naranja)
		{
			Lienzo.fondo=Color.ORANGE;
		}
		else if(source==gris)
		{
			Lienzo.fondo=Color.GRAY;
		}
		else if(source==verde)
		{
			Lienzo.fondo=Color.GREEN;
		}
		else if(source==azul)
		{
			Lienzo.fondo=Color.BLUE;
		}
		//Thread HiloDesaparecer = new Thread();
		//this.dispose();
		cerrar(this);
		Paint p= new Paint();
	}
	
	public String getEleccion()
	{
		return eleccion;
	}
	
	public static void cerrar(JFrame ventana)
	{
		ventana.setMinimumSize(new Dimension(0,0));
		Thread desaparecer = new HiloDesaparecer(ventana);
		desaparecer.start();
		
	}
}

//Permite mediante un hilo el cerrado de la ventana, reducciendola hasta 0.
/** Hilo que permite la desaparición de la VentanaEleccion, disminuyendo la altura y su anchura
 * @author kevincifuentes
 *
 */
class HiloDesaparecer extends Thread
{
	JFrame v;
	
	/** Constructor del hilo enviandole la ventana
	 * @param ventana La ventana que queremos que vaya disminuyendo
	 */
	HiloDesaparecer(JFrame ventana)
	{
		v=ventana;
	}
	
	public void run()
	{
		while(!(v.getSize().equals(new Dimension(0,0))))
		{
			v.setSize(new Dimension((int)v.getSize().getWidth()-6, (int)v.getSize().getHeight()-4));

		}
		System.out.println("Se cierra");
		v.dispose();
		
		
	}
}
