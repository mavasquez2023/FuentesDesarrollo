package cl.jfactory.planillas.service.util;

import java.io.BufferedReader;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class ValidarInconsistenciaOficinas {

	static String PATH_GENERACION = "";
	static String PERIODO = UtilesComunes.reemplazarVariables("sys.YearMonth");
	static{
		PATH_GENERACION = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+PERIODO;
	}
	public static void main(String[] args) {
		
		new ValidarInconsistenciaOficinas().validar();
		
	}
	
	public void validar(){
		if(true) return;
		
		File folder = new File(PATH_GENERACION);
		
		if(folder != null){
			
			File[] archivos = folder.listFiles();
			
			for(int i=0; i< archivos.length; i++){
				File archivo = archivos[i];
				if(archivo.isDirectory()){
					File[] nominas = archivo.listFiles();
					for(int j=0; j< nominas.length; j++){
						try{
							File nomina = nominas[j];
							String[] partes = nomina.getName().split(".txt")[0].split("-");
							String codigoNomina = partes[2];
							BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(nomina.getAbsolutePath(), 0, null);
							String linea = ManejoArchivoTXT.getLineFromFileOpened(br);
							String comparador = "";
							
							//oficina Pago
							comparador = linea.substring(0,3);
							if(!comparador.equals(partes[1])){
								eliminarComandoEntidad(archivo.getName());
								archivo.delete();
								j = nominas.length;
								
							}
							
							//Codigo nomina
							comparador = linea.substring(13,16);
							if(!comparador.equals(partes[2])){
								eliminarComandoEntidad(archivo.getName());
								archivo.delete();
								j = nominas.length;
							}
							
							ManejoArchivoTXT.closeFileToRead(br);
						}catch(Exception e){
							//e.printStackTrace();
						}
					}
				}
				
			}
			
		}
		
	}
	
	public void eliminarComandoEntidad(String entidad){
		UtilLogWorkflow.debug("Eliminando entidad con inconsistencias");
		ArrayList entidades = new ArrayList();
		String query = "carga_SAP.eliminarComandoEntidadConInconsistencia";
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		pars.put("PERIODO", PERIODO);
		pars.put("entidad", entidad.trim());
		try {
			int resultado = sqlMap.delete(query, pars);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
