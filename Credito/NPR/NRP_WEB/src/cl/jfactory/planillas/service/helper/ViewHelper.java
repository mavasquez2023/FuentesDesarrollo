package cl.jfactory.planillas.service.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class ViewHelper {


	public static HashMap<String, Object> getFichasHuachipato(Object session, Object parameters){
		HashMap salida = new HashMap();
		
		

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			JSONObject json = new JSONObject(((HashMap) parameters).get("data").toString());
			MiHashMap pars = new MiHashMap();
			pars.put("CAMPO", json.getString("campo"));
			pars.put("DATA_TO_SEARCH", json.getString("data_to_search"));
			if(pars.get("data_to_search").toString().trim().length() == 0) {
				pars = new MiHashMap();
			}
			ArrayList fichas = (ArrayList) sqlMap.queryForList("carga_SAP.getFichasHuachipato",pars);
			salida.put("fichas", fichas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return salida;
	}	

	public static HashMap<String, Object> addFichaHuachipato(Object session, Object parameters){
		HashMap salida = new HashMap();
		

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			JSONObject json = new JSONObject(((HashMap) parameters).get("data").toString());
			MiHashMap pars = new MiHashMap();
			pars.put("EMPRUT", json.getString("emprut"));
			pars.put("EMPRUTDV", json.getString("emprutdv"));
			pars.put("AFIRUT", json.getString("afirut"));
			pars.put("AFIRUTDV", json.getString("afirutdv"));
			pars.put("FICHA", json.getString("ficha"));
			pars.put("ROL", json.getString("rol"));
			
			sqlMap.update("carga_SAP.insertFichaHuachipato", pars);
			salida.put("status", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			salida.put("status", false);
		}
		
		
		return salida;
	}
	
	public static HashMap<String, Object> editFichaHuachipato(Object session, Object parameters){
		HashMap salida = new HashMap();
		

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			JSONObject json = new JSONObject(((HashMap) parameters).get("data").toString());
			MiHashMap pars = new MiHashMap();
			pars.put("EMPRUT", json.getString("emprut"));
			pars.put("EMPRUTDV", json.getString("emprutdv"));
			pars.put("AFIRUT", json.getString("afirut"));
			pars.put("AFIRUTDV", json.getString("afirutdv"));
			pars.put("FICHA", json.getString("ficha"));
			pars.put("ROL", json.getString("rol"));
			
			sqlMap.update("carga_SAP.updateFichaHuachipato", pars);
			salida.put("status", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return salida;
	}
	
	public static HashMap<String, Object> removeFichaHuachipato(Object session, Object parameters){
		HashMap salida = new HashMap();
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			JSONObject json = new JSONObject(((HashMap) parameters).get("data").toString());
			MiHashMap pars = new MiHashMap();
			pars.put("EMPRUT", json.getString("emprut"));
			pars.put("AFIRUT", json.getString("afirut"));
			
			sqlMap.delete("carga_SAP.deteleFichaHuachipato", pars);
			salida.put("status", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return salida;
	}

	
	public static HashMap<String, Object> setPeriodo(Object session, Object parameters){
		HashMap salida = new HashMap();
		
		System.out.println("Parmeters-> "+ parameters);
		
		System.out.println("Session-> "+ session);
		
		if(parameters != null && ((HashMap)parameters) != null && ((HashMap)parameters).get("periodo")!= null ) {
			System.out.println("seteando periodo [actual, nuevo]["+UtilesComunes.reemplazarVariables("sys.YearMonth")+","+((HashMap)parameters).get("periodo")+"]");
			UtilesComunes.variablesEstaticas.put("sys.YearMonth", ((HashMap)parameters).get("periodo").toString());
			System.out.println("seteando periodo final [->]["+UtilesComunes.reemplazarVariables("sys.YearMonth")+"]");
		}
		
		
		return salida;
	}

		
}
