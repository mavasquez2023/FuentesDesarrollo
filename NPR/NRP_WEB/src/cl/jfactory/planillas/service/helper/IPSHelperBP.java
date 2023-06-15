package cl.jfactory.planillas.service.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.negocio.post.carga.PostCargaIPS;
import cl.jfactory.planillas.service.util.CargaNoBatch;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.liv.persitencia.jdbc.util.JDBCUtil;

public class IPSHelperBP {

	static CargaNoBatch cargaNoBatch = null;
	
	public static boolean limpiarTabla(){
		UtilLogWorkflow.debug("Limpiando tabla...");

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			int deleteData = sqlMap.delete("carga_SAP.deleteDataBP");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static void procesandoRegistroIPS(HashMap data) throws SQLException{
		
		if(cargaNoBatch == null){
			cargaNoBatch = new CargaNoBatch("CARGA_IPS_BP", "nrpdta/nrp80f1");
		}
		
		MiHashMap o = new MiHashMap().toMiHashMap(data);
		
		String registro = JDBCUtil.getQueryCompleta("query.jdbc.registro.ips.bp", o);
		registro = registro.replaceAll("'","");
		//ManejoArchivoTXT.putLineFromFileOpened(archivoQuerys, registro);

		registro = registro.replaceAll("                      ;", ";");
		registro = registro.replaceAll("                     ;", ";");
		registro = registro.replaceAll("                    ;", ";");
		registro = registro.replaceAll("                   ;", ";");
		registro = registro.replaceAll("                  ;", ";");
		registro = registro.replaceAll("                 ;", ";");
		registro = registro.replaceAll("                ;", ";");
		registro = registro.replaceAll("               ;", ";");
		registro = registro.replaceAll("              ;", ";");
		registro = registro.replaceAll("             ;", ";");
		registro = registro.replaceAll("            ;", ";");
		registro = registro.replaceAll("           ;", ";");
		registro = registro.replaceAll("          ;", ";");
		registro = registro.replaceAll("         ;", ";");
		registro = registro.replaceAll("        ;", ";");
		registro = registro.replaceAll("       ;", ";");
		registro = registro.replaceAll("      ;", ";");
		registro = registro.replaceAll("     ;", ";");
		registro = registro.replaceAll("    ;", ";");
		registro = registro.replaceAll("   ;", ";");
		registro = registro.replaceAll("  ;", ";");
		registro = registro.replaceAll(" ;", ";");
		cargaNoBatch.agregarRegistro(registro);
		
	}
	
	public static String reemplazarCaracteres(String texto){
		if(texto != null){
			texto = texto.toUpperCase();
			texto = texto.replaceAll("Ã�","A");
			texto = texto.replaceAll("Ã‰","E");
			texto = texto.replaceAll("Ã�","I");
			texto = texto.replaceAll("Ã“","O");
			texto = texto.replaceAll("Ãš","U");
			texto = texto.replaceAll("Ã‘","N");
		}
		String cadena = "";
		for(int i=0; i< texto.length();i++){
			
			char caracter = texto.charAt(i);
			if(caracter == 65533){

				cadena = cadena + "Ã‘";
			}
			else{
				cadena = cadena + texto.charAt(i);
			}
			
		}
		return cadena;
	}
	
	
	public static void validarCargaDatosIPS() throws IOException{
		if(cargaNoBatch != null){
			cargaNoBatch.ejecutarComando();
			cargaNoBatch.terminarCarga();
			PostCargaIPS.IPSBPEjecutado=true;
		}
		cargaNoBatch = null;
		
	}
}
