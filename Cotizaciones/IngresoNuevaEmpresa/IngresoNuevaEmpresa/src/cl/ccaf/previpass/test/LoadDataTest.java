package cl.ccaf.previpass.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.ccaf.previpass.util.SqlMapLocator;

public class LoadDataTest {

	static String PATH_ARCHIVOS = "/home/desarrollo/Dropbox/Proyectos/CLILLO/previpass/datos/";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("init");
		
		//String archivo = "convenio";
		//String idSQL = "tmp.insertConvenio";
		//String archivo = "parametro";
		//String idSQL = "tmp.insertParametro";
		
		//procesarArchivo("convenio","tmp.insertConvenio");
		//procesarArchivo("parametro","tmp.insertParametro");
		//procesarArchivo("mapeoconce","tmp.insertMapeoConce");
		//procesarArchivo("grupoconv","tmp.insertGrupoConv");
		//procesarArchivo("mapanom","tmp.insertMapaNom");
		//procesarArchivo("mapacod","tmp.insertMapaCod");
		//procesarArchivo("opcionproc","tmp.insertOpcionProc");
		//procesarArchivo("mapeoapv","tmp.insertMapeoAPV");
		//procesarArchivo("mapeoasfam","tmp.insertMapeoAsfam");
		//procesarArchivo("mapeopens","tmp.insertMapeoPens");
		//procesarArchivo("mapeogen","tmp.insertMapeoGen");
		//procesarArchivo("mapeosalud","tmp.insertMapeoSalud");
		procesarArchivo("mapeotipmp","tmp.insertMapeoTipmp");
	}
	
	public static void procesarArchivo(String archivo, String idSQL){
		
		
		ArrayList registros = LectorArchivo.getContenidoArchivoString(PATH_ARCHIVOS+archivo+".csv");
		for(int i=0; i < registros.size(); i++){
			String[] data = registros.get(i).toString().split(",");
			HashMap registro = new HashMap();
			for(int j = 0; j < data.length ; j++){
				registro.put(j+"", data[j]);
			}
			
			System.out.println("reg: "+ registro);
			ejecutarInsert(idSQL, registro);
		}
		
		System.out.println("cantidad: "+registros.size());
	}
	
	
	public static void ejecutarInsert(String id, HashMap data){
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		
		try {
			sqlMap.insert(id,data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
