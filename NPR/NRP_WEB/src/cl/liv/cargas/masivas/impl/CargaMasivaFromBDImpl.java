package cl.liv.cargas.masivas.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.cargas.masivas.dto.CargaDTO;
import cl.liv.cargas.masivas.service.ICargaMasivaService;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class CargaMasivaFromBDImpl implements ICargaMasivaService{
	
	public static boolean detenerProceso = false;
	//String resource, String id, String idProperties, String configuracionOrigen
	public int execute(MiHashMap config){
		int estado = 0;
		int cantidadRegistros = 0;
		UtilLogWorkflow.debug("ejecutar carga from BD ");
		CargaDTO carga = LoadConfiguracionesCargas.getDataCarga(config.get("ID_CARGA").toString(),config.get("ID_PROPERTIES").toString());
		if(carga != null){
			
			SqlMapClient sqlMap = SqlMapLocator.getSqlMap(config.get("ORIGEN").toString());
			config.put("cantidad_registros_encontrados", new Integer(0));
			try {
				ArrayList registros = (ArrayList) sqlMap.queryForList(config.get("ARCHIVO").toString());

				if(registros.size()>0){
					for(int i=0; i< registros.size(); i++){
						if(detenerProceso){
							i = registros.size(); 
							UtilLogCargas.debug("deteniendo el proceso carga from db...");
						}
						cantidadRegistros++;
						String error = procesarAction(carga, (HashMap) registros.get(i), config.get("CODIGO").toString());
						if(error != null){
							estado = -1;
						}
					}
				}
				
				if(carga.getActionFinish() != null && carga.getActionFinish().length()>0){
					procesarActionFinish(carga);
				}
				config.put("cantidad_registros_encontrados", new Integer(registros.size()));
			} catch (SQLException e) {
				UtilLogErrores.error(e);
				e.printStackTrace();
			}
			
			
		}
		
		
		if(cantidadRegistros > 0 && estado == 0){
			estado = 1;
		}
		else{
			estado = 0;
		}
		
		return estado;
		
	} 
	
	public boolean validate(MiHashMap config){
		UtilLogWorkflow.debug("ejecutar carga from BD ");
		CargaDTO carga = LoadConfiguracionesCargas.getDataCarga(config.get("id_carga").toString(),config.get("id_properties").toString());
		UtilLogWorkflow.debug("carga: "+ carga);
		if(carga != null){
			
			SqlMapClient sqlMap = SqlMapLocator.getSqlMap(config.get("origen").toString());
			
			try {
				MiHashMap pars = new MiHashMap();
				pars.put("limite", Boolean.TRUE);
				ArrayList registros = (ArrayList) sqlMap.queryForList(config.get("archivo").toString(), pars);
				if(registros.size()>0){
					return true;
				}
			} catch (SQLException e) {
				UtilLogErrores.error(e);
				e.printStackTrace();
			}
			
			
		}
		return false;
		
	} 
	
	
	
	
	private String procesarAction(CargaDTO carga, HashMap parametros , String codigoTarea){
		
		if(parametros != null){
			parametros.put("__origen", codigoTarea);
		}
		
		if(carga.getAction().contains("persistencia.ibatis")){
		}
		else if(carga.getAction().contains("java_reflection")){
			String[] partesDefault = carga.getAction().split(";");
			
			Class[] paramsTypes = new Class[1];
			paramsTypes[0] = HashMap.class;
			
			Object[] pars = new Object[1];
			pars[0] = parametros;
			
			UtilReflectionImpl.executeReflection(partesDefault[1], partesDefault[2],paramsTypes, pars);
		} 
		return null;
	}
	
	private String procesarActionFinish(CargaDTO carga ){
		UtilLogCargas.info("procesando finish...");
		
		if(carga.getAction().contains("java_reflection")){
			UtilLogCargas.info("java reflection...["+carga.getActionFinish()+"]");
			String[] partesDefault = carga.getActionFinish().split(";");
			
			Class[] paramsTypes = new Class[0];
			Object[] pars = new Object[0];
			UtilLogCargas.info("java refrection: ["+partesDefault[1]+"]["+partesDefault[2]+"]");
			UtilReflectionImpl.executeReflection(partesDefault[1], partesDefault[2],paramsTypes, pars);
		} 
		return null;
	}
	
}
