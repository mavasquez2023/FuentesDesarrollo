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

public class TestGeneracionNominasMasivas {

	public static void main(String[] args) {
		
		
		ejecutarProcesoGeneracion();
	}
	
	
	
	public static boolean ejecutarProcesoGeneracion(){
		GeneradorNominasHelper.inicializar();
		
		try {
			
			HashMap parametros = new HashMap();

			parametros.put("codigo_entidad", "");
			
			new ConfiguradorHelper().generarNominas(new HashMap(), parametros);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(true) return false;
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			MiHashMap parametros = new MiHashMap();
			
			parametros.put("PERIODO", periodo);
			
			try{
				sqlMap.delete("carga_SAP.eliminarTablaProcesos", parametros);
			}catch(Exception e){
				e.printStackTrace();
			}
			sqlMap.insert("carga_SAP.crearTablaProcesos", parametros);
			

			///GeneradorNominasHelper.ejecutarProcesoPorEntidad();
			//System.out.println("comandos..."+ comandosGeneracion.size());
			//if(false){
			/*
			for(int i=0; i< cantidadHebras; i++){
				Thread t = 
				new Thread( new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						try {
							contadorProceso = 0;
							ejecutarProcesoPorEntidad();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				});
				
				t.setName("gen_"+i);
				UtilLogCargas.debug("Iniciando Hebra [gen_"+i+"]...");
				t.start();
				UtilLogCargas.debug("Estado Hebra [gen_"+i+"] "+ t.getState());
			}
			*/
			
			return true;
		
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
