package ztest;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class BuscarFormatosSinArchivo {

	static ResourceBundle properties = ResourceBundle.getBundle("etc/config_txt");
	public static void main(String[] args) {
		String ruta = "D:\\desarrollo\\git\\clillo\\resources-nrp\\nrp\\workflow\\202003\\";
		//buscar();
	}
	
	public static ArrayList buscar() {
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		ArrayList result = null;
		ArrayList formatosNoEncontrados = new ArrayList();
		try {
			result = (ArrayList)sqlMap.queryForList("carga_SAP.obtenerConfiguracionesNominasEspeciales");
			if(result != null) {
				for(int i=0; i<result.size(); i++) {
					MiHashMap config = (MiHashMap) result.get(i);
					if(config != null) {
						if( ! existeFormato(config)) {
							System.out.println("formato no existe ->"+ config.get("NOMBRE_CONFIG"));
							formatosNoEncontrados.add(config.get("NOMBRE_CONFIG"));
						}
					}
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("cantidad formatos no encontrados ->"+ formatosNoEncontrados.size());
		
		return formatosNoEncontrados;
	}
	
	public static boolean existeFormato(MiHashMap config) {
		
		String formato = (String)config.get("FORMATO_NOMINA");
		

		String ruta = properties.getString("export.path.resources.txt")+"txts/"+formato+"/conf.xml";

		if( new File(ruta).exists()   ) {
			return true;
		}

		
		return false;
	}
}
