package clasesBasicas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.swing.ImageIcon;



/** Clase Triangulo. Hereda de Figura
 * @author kevincifuentes
 *
 */
public class Triangulo extends Figura
{	
	/**
	 * Constructor por defecto
	 */
	public Triangulo()
	{
		this(new Point(),new Point());
	}

	
	/**
	 * @param ini punto inicial de arriba a la izquierda
	 * @param fin ounto final de abajo a la derecha
	 */
	public Triangulo(Point ini, Point fin) {
		super(ini,fin);		 
	}

	@Override
	public void dibujar(Graphics g, Point ini, Point fin) {
		actualizar(ini,fin);
		g.drawLine(ini.x, fin.y, fin.x, fin.y);
		g.drawLine(ini.x, fin.y, (fin.x)/2, ini.y);
		g.drawLine(fin.x, fin.y, (fin.x)/2, ini.y);
		
	
	}
	
	public static void main(String[]args)
	{
		Triangulo tri= new Triangulo(new Point(10,20), new Point(20,30));
		System.out.println(tri.getFin());
		System.out.println(tri.getIni());
	}


	@Override
	public Figura contiene(int x, int y) {
		
		Point A= new Point(ini.x,fin.y);
		Point B= new Point(fin.x,fin.y);
		Point C= new Point((fin.x/2),ini.y);
		Point p= new Point (x,y);
		
		double orient1=orientacion(A,B,p);
		double orient2=orientacion(B,C,p);
		double orient3=orientacion(C,A,p);
		
		// Si todas las orientaciones dan positivo o negativo, el punto está dentro del triangulo
		if((orient1>=0 && orient2>=0 && orient3>=0) || (orient1<=0 && orient2<=0 && orient3<=0))
		{
		return this;
		}
		else
		{
		return null;
		}
		
	}
	
	/**
	 * Metodo que calcula la orientación de un triángulo. Devuelve un valor positivo si la orientación es positiva. Devuelve un valor
	 * negativo si la orientación es negativa.
	 * @param A1 Uno de los vértices del triángulo
	 * @param A2 Otro de los vértices del triangulo
	 * @param p El punto que queremos comprobar su orientación; En este método el tercer vértice.
	 * @return Devuelve un real con la orientación
	 */
	public double orientacion(Point A1, Point A2, Point p)
	{
		//Mediante esta fórmula cáculamos la orientación del triángulo resultante de los dos vértices y el punto
		//(A1.x - A3.x) * (A2.y - A3.y) - (A1.y - A3.y) * (A2.x - A3.x)
		
		return (A1.x - p.x) * (A2.y - p.y) - (A1.y - p.y) * (A2.x - p.x);
	}


	@Override
	public void rellenar(Graphics2D g) {
		//Probaré a obtener un poligono con los vértices de mi triángulo para después rellenarlo
		
		this.setColor(g.getColor());
		rellenado=true;
		
		int[]x= new int[3];
		int[]y= new int[3];
		
		x[0]=(this.fin.x)/2;
		x[1]=this.fin.x;
		x[2]=this.ini.x;
		
		y[0]=this.ini.y;
		y[1]=this.fin.y;
		y[2]=this.fin.y;
		
		g.fillPolygon(x, y, 3);
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
