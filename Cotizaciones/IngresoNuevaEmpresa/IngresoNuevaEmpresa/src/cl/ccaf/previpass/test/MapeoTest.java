package cl.ccaf.previpass.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cl.ccaf.previpass.util.SqlMapLocator;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MapeoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	
	public static void initProceso(){

		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		HashMap parametro = obtenerRegistro(sqlMap,"mapeo.obtenerGrupoConvenioDefault", null);

		System.out.println(" parametro : "+ parametro);
		HashMap grupoConvenioTemplate = obtenerRegistro(sqlMap,"mapeo.obtenerDatagrupoConvenioDefault", parametro);
		System.out.println(" grupo Conv : "+ grupoConvenioTemplate);
		
		HashMap idMapaNom = obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleMapaNom",null);
		System.out.println("idMapaNom: "+ idMapaNom);
		
		HashMap idMapaCod = obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleMapaCod",null);
		System.out.println("idMapaCod: "+ idMapaCod);
		
		HashMap idOpcion = obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleOpcionProc",null);
		System.out.println("idOpcion: "+ idOpcion);
		
		HashMap idGrupoConv = obtenerRegistro(sqlMap,"mapeo.obtenerProximoIdDisponibleGrupoConv",null);
		System.out.println("idGrupoConv: "+ idGrupoConv);
		
		int idMapaNomRem = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString());
		int idMapaNomGra = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString()) + 1;
		int idMapaNomDep = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString()) + 2;
		int idMapaNomRel = Integer.parseInt( getValue(idMapaNom,"id_mapanom").toString()) + 3;
		
		duplicarMapeos(sqlMap,idMapaNomRem,"selectMapaNomRem","selectMapeoConceRem",grupoConvenioTemplate,"MAPEO REMUNERACIONES GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
		duplicarMapeos(sqlMap,idMapaNomGra,"selectMapaNomGra","selectMapeoConceGra",grupoConvenioTemplate,"MAPEO GRATIFICACIONES GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
		duplicarMapeos(sqlMap,idMapaNomDep,"selectMapaNomDep","selectMapeoConceDep",grupoConvenioTemplate,"MAPEO DEPOSITOS GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
		duplicarMapeos(sqlMap,idMapaNomRel,"selectMapaNomRel","selectMapeoConceRel",grupoConvenioTemplate,"MAPEO RELIQUIDACIONES GRUPO CONVENIO : "+ getValue(idGrupoConv, "id_grupo_convenio").toString());
		
		HashMap parametroEntidadesPrevisionales = new HashMap();
		parametroEntidadesPrevisionales.put("ID_MAPACOD", getValue(idMapaCod,"id_mapacod"));
		parametroEntidadesPrevisionales.put("DESCRIPCION", "Mapeo GRUPO CONVENIO "+ getValue(idGrupoConv,"id_grupo_convenio"));
		
		System.out.println(parametroEntidadesPrevisionales);
		ejecutarInsert(sqlMap,"mapeo.insertMapaCod", parametroEntidadesPrevisionales);
		
		parametroEntidadesPrevisionales.put("ID_MAPACOD_TEMPLATE", getValue(grupoConvenioTemplate,"ID_MAPACOD"));
		System.out.println("GC: "+grupoConvenioTemplate);
		System.out.println("parametro: "+parametroEntidadesPrevisionales);
		duplicarMapeosPrevisonales(sqlMap,"selectMapeoAPV", "insertMapeoAPV", parametroEntidadesPrevisionales);
		duplicarMapeosPrevisonales(sqlMap,"selectMapeoAsfam", "insertMapeoAsfam", parametroEntidadesPrevisionales);
		duplicarMapeosPrevisonales(sqlMap,"selectMapeoPens", "insertMapeoPens", parametroEntidadesPrevisionales);
		duplicarMapeosPrevisonales(sqlMap,"selectMapeoGen", "insertMapeoGen", parametroEntidadesPrevisionales);
		duplicarMapeosPrevisonales(sqlMap,"selectMapeoSalud", "insertMapeoSalud", parametroEntidadesPrevisionales);
		duplicarMapeosPrevisonales(sqlMap,"selectMapeoTipmp", "insertMapeoTipmp", parametroEntidadesPrevisionales);
		
	}
	
	
	public static ArrayList obtenerRegistros(SqlMapClient sqlMap, String id, HashMap data){
		ArrayList resultado = new ArrayList();
		try {
			resultado = (ArrayList) sqlMap.queryForList(id,data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static HashMap obtenerRegistro(SqlMapClient sqlMap, String id, HashMap data){
		ArrayList resultado = obtenerRegistros(sqlMap,id, data);
		if(resultado!= null){
			return (HashMap) resultado.get(0);
		}
		return null;
		
	}
	

	public static void ejecutarInsert(SqlMapClient sqlMap,String id,  HashMap data){
		
		try {
			sqlMap.insert(id, data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Object getValue(HashMap data,String key){
		return data.get(key.toUpperCase());
	}

	
	public static void duplicarMapeos(SqlMapClient sqlMap,int idMapaNomRem,String selectMapaNom,String selectMapeoConce,HashMap data, String texto){
		ArrayList mapasRemNom = obtenerRegistros(sqlMap,"mapeo."+selectMapaNom,data);
		System.out.println(mapasRemNom);
		
		for(int i=0; i<mapasRemNom.size();i++){
			HashMap mapaNomRem = (HashMap) mapasRemNom.get(i);
			mapaNomRem.put("ID_MAPANOM", idMapaNomRem+"");
			mapaNomRem.put("DESCRIPCION", texto );
			ejecutarInsert(sqlMap,"mapeo.duplicarMapaNom", mapaNomRem);
		}
		ArrayList mapeosConceRem = obtenerRegistros(sqlMap,"mapeo."+selectMapeoConce,data);
		for(int i=0; i<mapeosConceRem.size();i++){
			HashMap dataMapeoConceRem = (HashMap) mapeosConceRem.get(i);
			dataMapeoConceRem.put("ID_MAPANOM", idMapaNomRem+"");
			System.out.println(dataMapeoConceRem);
			ejecutarInsert(sqlMap,"mapeo.duplicarMapeoConce", dataMapeoConceRem);
		}
		
		
	}
	
	public static void duplicarMapeosPrevisonales(SqlMapClient sqlMap,String selectMapeo,String insertMapeo,HashMap data){

				List mapeos = obtenerRegistros(sqlMap,"mapeo."+selectMapeo,data);
				for(int i=0; i<mapeos.size();i++){
					HashMap mapeo = (HashMap) mapeos.get(i);
					mapeo.put("ID_MAPACOD", getValue(data, "ID_MAPACOD"));
					System.out.println(mapeo);
					ejecutarInsert(sqlMap,"mapeo."+insertMapeo, mapeo);
				}
		
	}
	
}
