package ztest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.helper.ConfiguradorHelper;
import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class TestGeneracionNominaDirecto {

	public static void main(String[] args) {
		
		
		ejecutarProcesoGeneracion();
	}
	
	
	
	public static boolean ejecutarProcesoGeneracion(){
		
		
		GeneradorNominasHelper.generar("CONF_HO0016_NEWALSIN1", "codigo_entidad:HO0016");
		
		return false;
	}
	
	
}
