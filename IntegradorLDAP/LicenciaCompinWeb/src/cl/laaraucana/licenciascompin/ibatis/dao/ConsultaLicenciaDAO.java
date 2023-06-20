package cl.laaraucana.licenciascompin.ibatis.dao;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.laaraucana.licenciascompin.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.licenciascompin.ibatis.vo.ViewLicenciasPendientes;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * 
 * @author Paula Morales
 *
 */

public class ConsultaLicenciaDAO {


	protected static Logger logger = Logger.getLogger("ConsultaLicenciaDAO");
	
	/**
	 * Método el lista las licencias pendientes de un afiliado.
	 * 
	 * @param rut
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ViewLicenciasPendientes> listaLicenciasPendientes(int rut) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {	
			
			@SuppressWarnings("unchecked")
			ArrayList<ViewLicenciasPendientes> viewLicenciasPendientes = (ArrayList<ViewLicenciasPendientes>)sqlMap.queryForList("mandato.licenciasPendientes", rut);
			return viewLicenciasPendientes;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error(e.getMessage());
			
		}
		return null;
	}

	/**
	 * Método que obtiene una licencia pendiente por folio.
	 * 
	 * @param folio
	 * @return
	 * @throws Exception
	 */
	public ViewLicenciasPendientes obtenerLicenciaPendiente(String folio) throws Exception {
		
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {	
			
			ViewLicenciasPendientes  viewLicenciasPendientes= (ViewLicenciasPendientes)sqlMap.queryForObject("mandato.licenciaPendiente", folio);
			return viewLicenciasPendientes;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error(e.getMessage());
			
		}
		return null;
	}
}
