/**
 * 
 */
package cl.domino.rentaspro;

import lotus.domino.*;
import java.util.*;
import java.io.*;

import cl.recursos.Archivo;

/**
 * @author usist24
 *
 */
public class ExtraerArchivosErroneos extends AgentBase {
	int [][] poslar = new int[18][2] ;
	private Vector mensaje;
	private ParametrosEnvio param=null;
	ParametrosSistema paramSistema=null;
	private Database db;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExtraerArchivosErroneos valida= new ExtraerArchivosErroneos();
		valida.NotesMain();

	}

	public void NotesMain() {
		String dirBase="/Rentas/Erroneos/";
		try {
			System.out.println(" .....................COMIENZA (Extraer Erroneos)   ............");
			//Session s = getSession();
			Session s= NotesFactory.createSession("10.10.10.106", "clillo", "claudio7");
			db =   s.getDatabase(s.getServerName(), "circular2511.nsf");
			View viewerroneos = db.getView("listadoerror");
			ViewEntryCollection vec = viewerroneos.getAllEntries();
			System.out.println("numero de archivos errones a extraer:" + vec.getCount());
			if ( vec.getCount()>0 ){
				mensaje= new Vector();
				ViewEntry entry=null;
				for (int i=0; i<vec.getCount(); i++){
					entry	 	= vec.getNthEntry(i+1);
					Document doc= entry.getDocument();
					mensaje= new Vector();
					param= new ParametrosEnvio(doc);
					EmbeddedObject obj= param.getArchivo();
					String pathfile= dirBase + obj.getSource();
					obj.extractFile(pathfile);
					Archivo archivo = new Archivo();
					archivo.crearArchivo(pathfile + ".html", doc.getItemValue("mensaje"));
				} //fin de For
			} 
			System.out.println("Fin extraer archivos " );

		} catch(Exception e) {
			System.out.println("VAB: CAI EN MAIN " );
			e.printStackTrace();
		}
	}
}
