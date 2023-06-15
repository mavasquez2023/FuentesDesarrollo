package cl.jfactory.planillas.service.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.IFSFile;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.liv.archivos.as400.impl.ArchivosAS400;
import cl.liv.archivos.as400.impl.ProxyAS400;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;

public class CargaNoBatch {
	String id = "";
	PrintWriter archivoQuerys = null;
	String nombreArchivo = "";
	static String path_archivo = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.repositorio.local.archivos") ;
	String tablaDestino = "";
	String pathRepositorioAS400 = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.repositorio.archivos");
	Date tiempoInicioCarga = new Date();
	final int TIEMPO_VIDA_HEBRA_COLGADA = 60000 * 30;
	final int TIEMPO_ESPERA_PARA_VALIDAR = 30000;
	boolean copiaTerminada = false;
	public static int contador = 0;
	private long indice = 0l;
	
	public long getIndice() {
		return indice;
	}

	public void setIndice(long indice) {
		this.indice = indice;
	}
	
	public static void limpiarArchivosTemporales() {
		File folder = new File(path_archivo);
		if(folder.exists() && folder.isDirectory()) {
			File[] archivos = folder.listFiles();
			if(archivos != null && archivos.length>0) {
				for(int i=0; i< archivos.length; i++) {
					File archivo = archivos[i];
					if(archivo != null && archivo.exists() && archivo.isFile()) {
						archivo.delete();
					}
				}
			}
			
		}
	}

	public CargaNoBatch(String _id, String _tablaDestino){
		nombreArchivo = UtilesComunes.reemplazarVariables("sys.YearMonth") + (int)(Math.random()* 100);
		archivoQuerys = ManejoArchivoTXT.getOpenedFileToWrite(path_archivo+nombreArchivo);
		id = _id;
		tablaDestino = _tablaDestino;
		contador = 0;

		UtilLogCargas.debug(" *****************  CARGA NO BATCH ***********");
		UtilLogCargas.debug("archivo para carga ["+_tablaDestino+"] -> "+ path_archivo+nombreArchivo);
	}
	
	public void agregarRegistro(String registro){
		ManejoArchivoTXT.putLineFromFileOpened(archivoQuerys, registro);
		contador++;
		if(contador % 1000 == 0){
			UtilLogCargas.debug("["+id+"] registro -> "+ contador);
			System.out.print(".");
			archivoQuerys.flush();
		}
		if(contador % 50000 == 0){
			UtilLogCargas.debug("["+id+"] registro -> "+ contador);
			System.out.print("\n*["+contador+"].");
		}
	}
	
	public void ejecutarComando() throws IOException{

		UtilLogCargas.debug("["+id+"] registro -> "+ contador);
		ManejoArchivoTXT.closeFileToWrite(archivoQuerys);
		final ArchivosAS400 as400 = new ArchivosAS400();
		//final AS400 conexion = ProxyAS400.getInstancia().getAs400();
		final AS400 conexion = new ProxyAS400().getAs400();

		as400.eliminarArchivosExistentes(conexion, pathRepositorioAS400 + nombreArchivo);
		
		tiempoInicioCarga = new Date();
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				while(!copiaTerminada){
					boolean resultado;
					try {
						resultado = as400.existeArchivo(conexion,pathRepositorioAS400 + nombreArchivo);
						if(new IFSFile(conexion, pathRepositorioAS400 + nombreArchivo).exists()){
							
							int sizeArchivoLocal =  (int) new File(path_archivo+ nombreArchivo).length()  / (1024 * 1024);
							int sizeArchivoAS400 = (int) new IFSFile(conexion, pathRepositorioAS400 + nombreArchivo).length() / (1024 * 1024);
							
							UtilLogCargas.debug("["+id+"]copiado: "+ sizeArchivoAS400 +"MB | "+ sizeArchivoLocal+"MB");
							UtilLogCargas.debug("tiempo : "+ (new Date().getTime() - tiempoInicioCarga.getTime()));
							
							if(sizeArchivoAS400 == 0 && TIEMPO_VIDA_HEBRA_COLGADA < (new Date().getTime() - tiempoInicioCarga.getTime())){
								//bye
								UtilLogCargas.debug("saliendo hebra colgada ");
								copiaTerminada = true;
							}
							else{
								Thread.sleep(TIEMPO_ESPERA_PARA_VALIDAR);
							}
						}
						else{
							UtilLogProcesos.debug("["+id+"] No existe archivo");
							if( TIEMPO_VIDA_HEBRA_COLGADA < (new Date().getTime() - tiempoInicioCarga.getTime())){
								//bye
								UtilLogCargas.debug("saliendo hebra colgada ");
								copiaTerminada = true;
							}
							else{
								Thread.sleep(TIEMPO_ESPERA_PARA_VALIDAR);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		as400.copiarArchivo(conexion, path_archivo+nombreArchivo, pathRepositorioAS400 + nombreArchivo);

		copiaTerminada = true;
		CommandCall command = new CommandCall(ProxyAS400.getInstancia().getAs400());
		 String commandStr = "CPYFRMIMPF FROMSTMF('"+pathRepositorioAS400+nombreArchivo+"') TOFILE("+tablaDestino+") MBROPT(*ADD) RCDDLM(*ALL) STRDLM(*NONE) FLDDLM(';') RPLNULLVAL(*FLDDFT) ";
		 UtilLogProcesos.debug("["+id+"] Ejecutando comando: " + commandStr);
		   try {
			   UtilLogProcesos.debug("["+id+"] ejecutando...");
			   boolean success = command.run(commandStr);

				 UtilLogProcesos.debug("["+id+"] Comando Ejecutado: " + commandStr);
		   }
		   catch(Exception e){

				UtilLogErrores.error(e);
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
		   }
		   if( Boolean.parseBoolean( PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.nobatch.eliminar.archivo.local")) ){
			   
			   new File(path_archivo+nombreArchivo).delete();
		   }
	}
	

	public void terminarCarga(){
		UtilLogProcesos.debug("["+id+"] Terminando carga " );
	}
}
