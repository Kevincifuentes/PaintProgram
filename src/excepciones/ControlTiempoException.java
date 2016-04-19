package excepciones;

/** Excepción que se lanza cuando el tiempo elegido es erroneo.
 * @author kevincifuentes
 *
 */
public class ControlTiempoException  extends Exception{
	
	public static String mensajeMostrar;
	public static int mins;
	
	/** Constructor de la excepción ControlTiempoException
	 * @param mensaje El mensaje que se quiere mostrar
	 * @param minutos Los minutos que se seleccionaron
	 */
	public ControlTiempoException(String mensaje, int minutos)
	{
		mensajeMostrar=mensaje;
		mins=minutos;
	}

}
