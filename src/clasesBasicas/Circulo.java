package clasesBasicas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;

import javax.swing.ImageIcon;



/** Clase Circulo. Hereda de Figura
 * @author kevincifuentes
 *
 */
public class Circulo extends Figura
{
		
	/**
	 * Constructor con valores
	 * @param ini punto inicial de arriba a la izquierda
	 * @param fin punto final de abajo a la derecha
	 */
	public Circulo(Point ini, Point fin)
	{
		super(ini,fin);
	}
	
	/**
	 * Constructor por defecto
	 */
	public Circulo()
	{
		this(new Point(), new Point());
	}
	
	
	
	
	@Override
	public void dibujar(Graphics g, Point ini, Point fin) {
		//Dibuja un circulo gracias al punto de inicio y el final
		Graphics2D g2= (Graphics2D) g;
		actualizarFin(fin);
//		int ancho= Math.abs(ini.x-fin.x);
//		int alto= Math.abs(ini.x-fin.x);
//		int iniX= Math.min(ini.y, fin.x);
//		int iniY= Math.min(ini.y, fin.y);
		g2.drawOval((int)ini.getX(), (int)ini.getY(), ((int)fin.getX()-(int)ini.getX()), ((int)fin.getY()-(int)ini.getY()));
		
	}
	public static void main (String [] args)
	{
		Circulo circle= new Circulo(new Point(10,20), new Point(20,30));
		System.out.println(circle.getFin());
		System.out.println(circle.getIni());
		
	}

	@Override
	public Figura contiene(int x, int y) {
		int radiox=(fin.x-ini.x)/2;
		int radioy=(fin.y-ini.x)/2;
		int centrox=ini.x+radiox;
		int centroy=ini.y+radioy;
		
		double resultado=(Math.pow((x-centrox),2)/Math.pow(radiox, 2))+(Math.pow((y-centroy),2)/Math.pow(radioy, 2));
		
		if(resultado<=1.0)
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
		g.fillOval((int)ini.getX(), (int)ini.getY(), ((int)fin.getX()-(int)ini.getX()), ((int)fin.getY()-(int)ini.getY()));
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
