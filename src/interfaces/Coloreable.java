package interfaces;

import java.awt.Color;

/** Interfaz que permite poner colores a las figuras. Contiene dos metodos, setColor y getColor.
 * @author kevincifuentes
 *
 */
public interface Coloreable {

	/** Cambia el color de la figura al color c.
	 * @param c El color al cual queremos cambiar.
	 */
	void setColor(Color c);
	
	/** Devuelve el color actual de la figura
	 * @return Devuelve el color de la figura
	 */
	Color getColor();
}
