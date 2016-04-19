package principal;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/** Ventana que permite la apertura y el guardado de archivos
 * @author kevincifuentes
 *
 */
public class VentanaGuardado {
	
	private File lugarGuardado;
	private File lugarApertura;
	private boolean estado;
	
	/** Constructor que crea la VentanaGuardado pasandole un booleano
	 * @param estado1 Es un booleano que determina si es una ventana de apertura o de guardado de
	 * archivos. Si es True es de guardado, si no será de apertura.
	 */
	public VentanaGuardado(boolean estado1)
	{
		estado=estado1;
		//true = guardar / false= abrir
		JFileChooser jfcGuardar= new JFileChooser();
		jfcGuardar.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter f1;
		if(!estado)
		{
			jfcGuardar.setDialogType(JFileChooser.OPEN_DIALOG);
			jfcGuardar.setDialogTitle("Abrir proyecto");
			jfcGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
			f1 = new FileNameExtensionFilter("JPG, GIF & PNG", "jpg", "gif","png");
			jfcGuardar.setFileFilter(f1);
			jfcGuardar.setToolTipText("La extensión del archivo solo puede set jpg, gif o png");
		}
		else
		{
			jfcGuardar.setDialogType(JFileChooser.SAVE_DIALOG);
			jfcGuardar.setApproveButtonText("Guardar");
			jfcGuardar.setDialogTitle("Guardar proyecto (será guardado en jpg)");
			jfcGuardar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		}
	
		jfcGuardar.setAcceptAllFileFilterUsed(false);
		if(jfcGuardar.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{

			System.out.println("Directorio:"+jfcGuardar.getSelectedFile());
			if(estado){
				lugarGuardado=jfcGuardar.getSelectedFile();
			}
			else{
				lugarApertura=jfcGuardar.getSelectedFile();
			}
			
		}
		else
		{
			lugarGuardado=lugarApertura=null;
			System.out.println("No hay selección");
		}
	}
	
	/** Metodo que devuelve el File que ha seleccionado el usuario.
	 * @return Devuelve el File seleccionado en la ventana de JFileChooser
	 */
	public File getFile()
	{
		
		if(estado)
			{
			return lugarGuardado;
			}	
		else
			{
			return lugarApertura;
			}
	}
	
	
	public static void main(String[]args)
	{
		VentanaGuardado vE= new VentanaGuardado(true);
		
	}
}
