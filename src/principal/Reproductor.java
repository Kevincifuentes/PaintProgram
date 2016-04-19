package principal;

import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;


/** Clase que permite utilizar el sonido en el programa
 * @author kevincifuentes
 *
 */
public class Reproductor {
	//Es una clase de una librería, que permite utilizar el sonido.
    public BasicPlayer player;
    
    /**
     * Constructor por defecto que crea un BasicPlayer, el cual permitirá utilizar el sonido
     */
    public Reproductor() {
        player = new BasicPlayer();
    }
    /** Metodo que permite iniciar el BasicPlayer, es decir, permite iniciar el sonido
     * @throws Exception lanza esta excepción, si no se puede iniciar
     */
    public void Play() throws Exception {
        player.play();
    }

    /** Metodo que abre el archivo de musica que se quiere reproducir con el BasicPlayer
     * @param ruta La ruta en la que se encuentra el archivo
     * @throws Exception Lanza esta excepción si no se puede abrir el archivo
     */
    public void AbrirFichero(String ruta) throws Exception {
        player.open(new File(ruta));
    }

    /** Metodo que permite pausar el BasicPlayer, es decir, pausar la música
     * @throws Exception Lanza esta excepción si no pudo pausarlo
     */
    public void Pausa() throws Exception {
        player.pause();
    }

    /** Metodo que después de haber pausado el BasicPlayer permite reanudarlo, es decir, 
     * poner otra vez el sonido desde donde estaba
     * @throws Exception Lanza esta excepción si no puede reanudarlo
     */
    public void Continuar() throws Exception {
        player.resume();
    }

    /** Metodo que para el reproductor y el sonido. Una vez parado de esta manera no se podrá reanudar.
     * @throws Exception
     */
    public void Stop() throws Exception {
        player.stop();
    }
 
 
    public static void main(String args[]) throws Exception{
     Reproductor y = new Reproductor();
     y.AbrirFichero("audios/pin.wav");
     y.Play();
    }
}