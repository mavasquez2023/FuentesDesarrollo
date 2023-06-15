package ztest;

import cl.liv.cargas.masivas.impl.CargaMasivaImpl;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;

public class TestCargasMasivas {
	public static void main(String[] args) {
		PropertiesUtil.cargar();
		MiHashMap map = new MiHashMap();
				
		map.put("ESTADO","1"); 
		map.put("ID_CARGA","carga_SAP"); 
		map.put("TIPO","FILE");
		map.put("CODIGO","SAP"); 
		map.put("CLASE_IMPLEMENTACION",""); 
		map.put("ID_PROPERTIES","planillas");
		map.put("CLASE_POST_EXEC",""); 
		map.put("ARCHIVO","FILE;NRPNRP201807.txt");
		map.put("ORDEN_EJECUCION","1");
		map.put("ID","1");
		map.put("NOMBRE","Nominas SAP"); 
		map.put("ORIGEN","C:\\workspaces\\java\\clillo\\proyectos\\nrp\\files\\NRP202102_add2_100.txt");
		
		
		map.put("ESTADO","1"); 
		map.put("ID_CARGA","carga_SAP_anexo"); 
		map.put("TIPO","FILE");
		map.put("CODIGO","SAP"); 
		map.put("CLASE_IMPLEMENTACION",""); 
		map.put("ID_PROPERTIES","planillas");
		map.put("CLASE_POST_EXEC",""); 
		map.put("ARCHIVO","FILE;NRPNRP201807.txt");
		map.put("ORDEN_EJECUCION","1");
		map.put("ID","1");
		map.put("NOMBRE","Nominas Anexo SAP"); 
		map.put("ORIGEN","C:\\workspaces\\java\\clillo\\proyectos\\nrp\\files\\NRP202102_A.txt");
		
		

		/*map.put("ESTADO","1"); 
		map.put("ID_CARGA","carga_ips_bp"); 
		map.put("TIPO","FILE");
		map.put("CODIGO","PREVISIONAL"); 
		map.put("CLASE_IMPLEMENTACION",""); 
		map.put("ID_PROPERTIES","planillas");
		map.put("CLASE_POST_EXEC",""); 
		map.put("ARCHIVO","FILE;PREV_201809.txt_0.1843449250832948");
		map.put("ORDEN_EJECUCION","1");
		map.put("ID","1");
		map.put("NOMBRE","Previsional"); 
		map.put("ORIGEN","/home/clillo/nrp/input/PREV_201809.txt_0.1843449250832948");

		*/
		new CargaMasivaImpl().execute(map);
	}
}
