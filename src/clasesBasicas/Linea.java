package clasesBasicas;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;

import javax.swing.ImageIcon;


/** Clase Linea. Hereda de Figura
 * @author kevincifuentes
 *
 */
public class Linea extends Figura
{
	
	/**
	 * Constructor por defecto
	 */
	public Linea()
	{
		this(new Point(),new Point());
	}

	/**
	 * Constructor con valores
	 * @param ini punto inicial de arriba a la derecha
	 * @param fin punto final de abajo a la izquierda
	 */
	public Linea(Point ini, Point fin) {
		
		super(ini,fin);
	}

	@Override
	public void dibujar(Graphics g, Point ini, Point fin) {

		actualizar(ini,fin);
		g.drawLine(ini.x, ini.y, fin.x, fin.y);
	}

	
	public static void main(String []args)
	{
		Linea lin= new Linea(new Point(10,20), new Point(20,30));
		System.out.println(lin.getFin());
		System.out.println(lin.getIni());
	}

	@Override
	public Figura contiene(int x, int y) {
		
		if(estaEnLaLinea(x,y))
		{
			return this;
		}
		else
		{
			return null;
		}
	}
	
	/** Metodo que determina si un punto está en una linea o no.
	 * @param x El valor de x del punto
	 * @param y El valor de y del punto
	 * @return Devuelve un booleano. True si está dentro, False si no lo está.
	 */
	public boolean estaEnLaLinea(int x, int y)
	{
		Point test= new Point(x,y);
		if(distancia(this.ini,test)+distancia(this.fin,test)==distancia(this.ini,this.fin))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/** Metodo que devuelve la distancia que hay entre dos puntos
	 * @param p Uno de los puntos. Generalmente uno de los puntos de la linea.
	 * @param test El otro punto. El punto que se quiere comprobar.
	 * @return Devuelve la distancia entre los dos puntos.
	 */
	private int distancia(Point p, Point test) {
		double operacion=Math.pow((p.x-test.x),2)+Math.pow((p.y-test.y), 2);
		return (int) Math.sqrt(operacion);
	}

	@Override
	public void rellenar(Graphics2D g) {
		//No hará nada, ya que una linea no se puede rellenar.
	}
	
	@Override
	public void setColor(Color c) {
		this.color=c;
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}


}
