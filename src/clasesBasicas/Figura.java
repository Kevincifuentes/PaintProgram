package clasesBasicas;

import interfaces.Coloreable;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.Vector;

import javax.swing.SwingConstants;

/**
 * Clase padre de todas las figuras geométricas
 * @author kevincifuentes
 *
 */
public abstract class Figura implements Coloreable
{
	
	
	protected Point ini,fin;
	private int grosura;
	protected Color color;
	public boolean rellenado;
	
	/**
	 * @param fin punto final de abajo a la derecha
	 */
	public void actualizarFin(Point fin)
	{
	//	this.fin=fin;
	}
	
	/**
	 * Metodo que actualiza los valores de la figura
	 * 
	 * @param ini punto inicial de arriba a la izquierda
	 * @param fin punto final de abajo a la derecha
	 */
	public void actualizar(Point ini, Point fin)
	{
		this.ini=ini;
		this.fin=fin;
		
	}
	
	
	/**
	 * Constructor por defecto que crea dos puntos.
	 */
	public Figura()
	{
		
		this(new Point(),new Point());
		rellenado=false;
	}
	
	/**
	 * Constructor Figura con valores
	 * 
	 * @param ini punto inicial de arriba a la izquierda
	 * @param fin punto final de abajo a la derecha
	 */
	public Figura(Point ini, Point fin)
	{
		this.ini=ini;
		this.fin=fin;
	}
	
	/**
	 * Metodo que dibuja cada figura mientras movemos el raton
	 * (Metodo abstracto, se declara en las clases hijas)
	 * @param g El Graphics que se utilizará para dibujar en el Lienzo
	 * @param ini punto inicial de arriba a la izquierda
	 * @param fin punto final de abajo a la derecha
	 */
	public abstract void dibujar(Graphics g,Point ini, Point fin) ;


	/**
	 * Metodo abstracto implementado en las clases hijas.
	 * Determina si un punto está en el área de una figura
	 * @param x Valor de x del punto a comprobar
	 * @param y Valor de y del punto a comprobar
	 * @return Devuelve la figura, la cual contiene el punto. Null si no contiene ese punto
	 */
	public abstract Figura contiene(int x, int y);
	
	
	/**Metodo que permite rellenar la figura actual con el color que tenga la figura.
	 * @param g El Graphics necesario para dibujar en el lienzo.
	 */
	public abstract void rellenar(Graphics2D g);
	
	/** Metodo que devuelve el valor del punto inicial de arriba a la izquierda
	 * @return Devuelve el punto inicial de arriba a la izquierda
	 */
	public Point getIni() {
		return ini;
	}


	/** Metodo que cambia el valor del punto inicial de arriba a la izquierda
	 * @param ini Cambia el valor punto inicial de arriba a la izquierda
	 */
	public void setIni(Point ini) {
		this.ini = ini;
	}


	/** Metodo que devuelve el valor del punto final de abajo a la derecha
	 * @return fin Devuelve el punto final de abajo a la derecha
	 */
	public Point getFin() {
		return fin;
	}


	/**
	 * Metodo que cambia el valor del punto final de abajo a la derecha
	 * @param fin El punto final que queremos modificar
	 * 
	 */
	public void setFin(Point fin) {
		this.fin = fin;
	}
	
	public static void main(String[]args)
	{
		Vector figuras= new Vector();
		Circulo c= new Circulo();
		Cuadrado c2= new Cuadrado();
		figuras.add(c);
		figuras.add(c2);
		
		Figura f=(Figura) figuras.get(1);
		f.actualizar(new Point(), new Point());
	}

	/** Metodo que devuelve la grosura de la figura
	 * @return Devuelve un entero que determina la grosura de la figura
	 */
	public int getGrosura() {
		return grosura;
	}

	/** Metodo que cambia la grosura de la figura
	 * @param grosura La nueva grosura de la figura
	 */
	public void setGrosura(int grosura) {
		this.grosura = grosura;
	}
	
	
	/** Metodo que determina si la figura en cuestión esta rellena.
	 * @return Devuelve un booleano. True si la figura está rellena, false en caso contrario.
	 */
	public boolean getRellenado()
	{
		return rellenado;
	}
	
	
	
}
