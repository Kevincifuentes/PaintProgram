package clasesBasicas;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;

import javax.swing.ImageIcon;



/** Clase Cuadrado. Hereda de Figura
 * @author kevincifuentes
 *
 */
public class Cuadrado extends Figura
{
		
	/**
	 * Constructor por defecto del cuadrado
	 */
	public Cuadrado()
	{
		this(new Point(), new Point());
	}

	/**
	 * @param ini punto inicial de arriba a la izquierda
	 * @param fin punto final de abajo a la derecha
	 */
	public Cuadrado(Point ini, Point fin) {
		super(ini,fin);
	}

	@Override
	public void dibujar(Graphics g, Point ini, Point fin) {
		actualizar(ini,fin);
		
		int ancho= Math.abs(ini.x-fin.x);
		int alto= Math.abs(ini.x-fin.x);
		int iniX=Math.min(ini.x, fin.x);
		int iniY= Math.min(ini.y, fin.y);
		
		g.drawRect(iniX, iniY, ancho, alto);
		
	}

	
	public static void main(String[]args)
	{
		Cuadrado cuadrado= new Cuadrado(new Point(10,20), new Point(20,30));
		System.out.println(cuadrado.getFin());
		System.out.println(cuadrado.getIni());
	}

	@Override
	public Figura contiene(int x, int y) {
		
		if(x<=fin.x && x>=ini.x && y>=ini.y && y<=fin.y)
		{
			return this;
		}
		else
		{
		return null;
		}
		
		
	}

	@Override
	public void rellenar(Graphics2D g) {
		this.setColor(g.getColor());
		rellenado=true;
		int ancho= Math.abs(ini.x-fin.x);
		int alto= Math.abs(ini.x-fin.x);
		int iniX=Math.min(ini.x, fin.x);
		int iniY= Math.min(ini.y, fin.y);
		
		g.fillRect(iniX,iniY , ancho, alto);
		
		
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
