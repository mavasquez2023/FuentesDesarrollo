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
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.liv.persitencia.jdbc.util.JDBCUtil;

public class SAPAnexoHelper {

static CargaNoBatch cargaNoBatch = null;
	
	public static boolean limpiarTabla(){
		UtilLogWorkflow.debug("Limpiando tabla...");

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			int deleteData = sqlMap.delete("carga_SAP.deleteDataSAPAnexo", periodo );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public static void procesandoRegistro(HashMap data) throws SQLException{
		
		if(cargaNoBatch == null){
			limpiarTabla();
			cargaNoBatch = new CargaNoBatch("CARGA_SAP", "nrpdta/nrp10f1a");
		}
		
		MiHashMap o = new MiHashMap().toMiHashMap(data);
		
		String registro = JDBCUtil.getQueryCompleta("query.jdbc.registro.sap.anexo", o);
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
			texto = texto.replaceAll("Á","A");
			texto = texto.replaceAll("É","E");
			texto = texto.replaceAll("Í","I");
			texto = texto.replaceAll("Ó","O");
			texto = texto.replaceAll("Ú","U");
			texto = texto.replaceAll("Ñ","N");
		}
		
		return texto;
	}
	
	
	public static void validarUltimosRegistros() throws IOException{
		if(cargaNoBatch != null){
			cargaNoBatch.ejecutarComando();
			cargaNoBatch.terminarCarga();
			PostCargaIPS.IPSPBSEjecutado=true;
		}
		cargaNoBatch = null;
		
	}
		
}
