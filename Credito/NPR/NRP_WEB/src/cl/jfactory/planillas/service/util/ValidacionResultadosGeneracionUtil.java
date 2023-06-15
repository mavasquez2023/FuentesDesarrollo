package cl.jfactory.planillas.service.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.PropertiesUtil;

public class ValidacionResultadosGeneracionUtil {

	ArrayList errores = new ArrayList();
	int iteracionValidacion = 0;
	int CANTIDAD_MAXIMA_REINTENTOS = 5;
	public static void main(String[] args) {
		new ValidacionResultadosGeneracionUtil().ejecutarProcesoValidacion();
	}
	
	public void ejecutarProcesoValidacion(){
		if(true) return;
		UtilLogCargas.debug("Ejecutando la validacion del proceso....");

		UtilLogCargas.debug("Validando existencia de entidades ["+iteracionValidacion+"]");
		String PATH_SALIDA =  PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+ Utiles.getPeriodoActual()+"_validacion_proceso";
		
		validarInconsistencia();
		validarDataEnArchivo();
		validarExistenciaEntidades();
		
		PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(PATH_SALIDA);
		
		if(errores != null){
			
			for(int i=0; i < errores.size(); i++){
				ManejoArchivoTXT.putLineFromFileOpened(pw, errores.get(i).toString());
			}
			
		}
		ManejoArchivoTXT.closeFileToWrite(pw);

	}
	
	public  void validarInconsistencia(){
		

		UtilLogCargas.debug("Validando de inconsistencias ");
		String PATH_OUTPUT_NOMINAS = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+ Utiles.getPeriodoActual();
		
		File directorioNominas = new File(PATH_OUTPUT_NOMINAS);
		if(directorioNominas.exists()){
			
			File[] empresas = directorioNominas.listFiles();
			if(empresas != null && empresas.length>0){
				
				for(int i=0; i< empresas.length; i++){
					
					File[] nominas = empresas[i].listFiles();
					if(nominas.length ==0){
						errores.add(empresas[i].getName()+ "; Sin Nóminas");
					}
					else{
						for(int j=0;j<nominas.length; j++){
							String nombreNomina = nominas[j].getName();
							String[] partes = nombreNomina.split("-");
							if(partes.length==3){
								String entidadAux = Integer.parseInt(partes[0].trim())+"";
								if(entidadAux.length() >5){
									String nombreEntidad = empresas[i].getName();
									if(!nombreEntidad.equals(entidadAux) && !nombreEntidad.startsWith("H")){
										errores.add(nombreEntidad+ "; Contiene una nómina asociado a otra empresa ["+nominas[j].getName()+"], se elimina");
										nominas[j].delete();
									}
								}
							}
						}		
					}
				}	
			}
		}
	}
	
	public void validarDataEnArchivo(){
		

		UtilLogCargas.debug("Validando data en archivo");

		String PATH_RESULTADOS =  PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+ Utiles.getPeriodoActual()+"_res";
		
		BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(PATH_RESULTADOS, 0, null);
		
		String registro = ManejoArchivoTXT.getLineFromFileOpened(br);
		
		while(registro != null){
			
			String[] partes = registro.split(";");
			String rutaArchivo = partes[0];
			int cantidadRegistros = 0;
			
			if(partes.length > 1)
			cantidadRegistros = Integer.parseInt(partes[1]);
			
			if(new File(rutaArchivo).exists()){
				if(!rutaArchivo.contains(".xls")){
					BufferedReader brNomina = ManejoArchivoTXT.getOpenedFileToRead(rutaArchivo, 0, null);
					int contador = 0;
					String data = ManejoArchivoTXT.getLineFromFileOpened(brNomina);
					while(data != null){
						contador++;
						data = ManejoArchivoTXT.getLineFromFileOpened(brNomina);
					}
					if(cantidadRegistros != contador){
						errores.add(rutaArchivo+"; archivo deberÃ­a contener "+cantidadRegistros+", pero tiene "+contador);
					}
					
					ManejoArchivoTXT.closeFileToRead(brNomina);
				}
			}
			else{
				errores.add(rutaArchivo+"; archivo no fue generado");
			}

			registro = ManejoArchivoTXT.getLineFromFileOpened(br);
		}
		
		ManejoArchivoTXT.closeFileToRead(br);
		
	}
	
	public void validarExistenciaEntidades(){

		UtilLogCargas.debug("Validando existencia de entidades ["+iteracionValidacion+"]");
		iteracionValidacion++;
		errores.add(iteracionValidacion + ";ciclo validacion;");
		final ArrayList entidadesSinNominas = new ArrayList();
		boolean existenEntidadesSinNominas = false;
		String PATH_OUTPUT_NOMINAS = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+ Utiles.getPeriodoActual();
		String PATH_RESULTADOS =  PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+ Utiles.getPeriodoActual()+"_reg_enti";
		
		BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(PATH_RESULTADOS, 0, null);
		
		String registro = ManejoArchivoTXT.getLineFromFileOpened(br);
		
		while(registro != null){
			
			String codigoEntidad = registro.trim();
			
			File entidad = new File(PATH_OUTPUT_NOMINAS +"/"+ codigoEntidad);
			
			if(entidad.exists()){
				File[] nominas = entidad.listFiles();
				if(nominas.length == 0){
					errores.add(entidad +"; Se creó el directorio, pero no contiene nóminas ");
					existenEntidadesSinNominas = true;
					entidadesSinNominas.add(codigoEntidad);
				}
			}
			else{
				errores.add(entidad +"; No se creo la carpeta para la entidad ");
			}
			
			registro = ManejoArchivoTXT.getLineFromFileOpened(br);
		}
		
		ManejoArchivoTXT.closeFileToRead(br);
		
		if(existenEntidadesSinNominas){
			
			for(int i=0; i<entidadesSinNominas.size(); i++){
				GeneradorNominasHelper.generarTodas( "codigo_entidad:"+ entidadesSinNominas.get(i));
			}
			new PoolHebras(1, "cl.jfactory.planillas.service.helper.GeneradorNominasHelper", "ejecutarHiloGeneracion", new Class[0], new Object[0], new ITerminadorHebra() {
				
				public void notificarCierre(int tipo) {
					UtilLogCargas.debug("Notificando cierre de validacion");
					if(iteracionValidacion < CANTIDAD_MAXIMA_REINTENTOS){
						validarExistenciaEntidades();
					}
				}
			});
			
		}
		
		
	}
}
