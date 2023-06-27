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
public class ExtraerNoRecepcionados extends AgentBase {
	int [][] poslar = new int[18][2] ;
	private Vector mensaje;
	private ParametrosEnvio param=null;
	ParametrosSistema paramSistema=null;
	private Database db;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExtraerNoRecepcionados valida= new ExtraerNoRecepcionados();
		valida.NotesMain();

	}

	public void NotesMain() {
		String dirBaseCon="/Rentas/NoRecepcionados_ConFormato/";
		String dirBaseSin="/Rentas/NoRecepcionados_SinFormato/";
		try {
			System.out.println(" .....................COMIENZA (Extraer No recepcionados)   ............");
			//Session s = getSession();
			Session s= NotesFactory.createSession("10.10.10.106", "clillo", "claudio7");
			db =   s.getDatabase(s.getServerName(), "circular2511.nsf");
			View viewerroneos = db.getView("norecepcionados");
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
					String pathfile="";
					if(doc.getItemValueString("norecepcionado").equals("1")){
						pathfile= dirBaseCon + doc.getItemValueString("rutArchivo") + "." +  param.getExtensionArchivo();
					}else{
						pathfile= dirBaseSin + obj.getSource();
					}
					obj.extractFile(pathfile);

				} //fin de For
			} 
			System.out.println("ENR: Fin extraer archivos **** " );

		} catch(Exception e) {
			System.out.println("VAB: CAI EN MAIN " );
			e.printStackTrace();
		}
	}
}
