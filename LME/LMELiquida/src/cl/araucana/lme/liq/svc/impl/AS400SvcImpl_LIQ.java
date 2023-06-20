/*
 * Created on 11-10-2011
 *
 */

package cl.araucana.lme.liq.svc.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.lme.liq.ibatis.domain.Ilfe011VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe031VO;
import cl.araucana.lme.liq.ibatis.domain.Ilfe033VO;
import cl.araucana.lme.liq.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.liq.ibatis.domain.LmeLogVO;
import cl.araucana.lme.liq.ibatis.domain.ParametroVO;
import cl.araucana.lme.liq.svc.IAS400Svc_LIQ;
import cl.araucana.lme.liq.svc.InitConexion_LIQ;
import cl.araucana.lme.liq.svc.exception.SvcException;
import cl.araucana.lme.liq.util.ConfiguracionesLiq;
import cl.araucana.lme.liq.util.SQLExceptionHandler;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * @author j-factory
 *
 */

public class AS400SvcImpl_LIQ implements IAS400Svc_LIQ {

	//	private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());

	
	public List getIlfe011_Consumo(Map param) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe011_Consumo", param);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			e.printStackTrace();
			throw new SvcException(e.getMessage());
		}
		//InitConexion_LIQ.closeConexion_LME();
		return salida;
	}
	
	public int limpiaIlfe011(Ilfe011VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("limpiaIlfe011", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}
	
	public int updateIlfe031(Ilfe031VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe031", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}
	
	public Object getIlfe031(Ilfe011VO vo11) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		Object salida=null;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForObject("getIlfe031_aux", vo11);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			e.printStackTrace();
			throw new SvcException(e.getMessage());
		}
		//InitConexion_LIQ.closeConexion_LME();
		return salida;
	}
	
	public String insertIlfe033(Ilfe033VO vo) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe033", vo);
			salida = "OK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getMessage();
		}
		return salida;
	}
	
	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#getIlfeOpe(cl.araucana.lme.ibatis.domain.IlfeOpeVO)
	 */
	public List getIlfeOpe(IlfeOpeVO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfeOpe", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#insertLog(cl.araucana.lme.ibatis.domain.LmeLogVO)
	 */
	public String insertLog(LmeLogVO logVO) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertLog", logVO);
			salida = "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getMessage();
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#getLog(cl.araucana.lme.ibatis.domain.LmeLogVO)
	 */
	public List getLog(LmeLogVO logVO) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getLog", logVO);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	public Map getEndPoints() throws Exception {
		SqlMapClient sqlMapLocal = null;
		Map endPointMap = null;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			String prioridad = ConfiguracionesLiq.getConfig("prioridad.db");
			endPointMap = (HashMap) sqlMapLocal.queryForMap("getEndPoints", prioridad, "key", "value");
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new Exception("Error al obtener la lista de endpoint");
		}
		return endPointMap;
	}


	public Map getParametrosBd() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		Map map = null;
		int jo=0;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
			e1.printStackTrace();
		}
		try {
			map= (HashMap) sqlMapLocal.queryForMap("getParametros","", "key","value");
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			throw new SvcException("Error al obtener la lista de parámetros desde BD");
		}
		return map;
	}


	public int updateParametro(ParametroVO entrada) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		int cant = 0;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			cant = sqlMapLocal.update("updateParametro", entrada);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			throw new SvcException("Error al actualizar parámetro", e);
		}
		return cant;
	}
		
	public String insertEstadistica(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LIQ.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertEstadistica", h);
			salida = "OK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error("Error insertando estadísticas," + e.getClass() + "; " + e.getMessage());
			//e.printStackTrace();
			salida = e.getMessage();
		}
		return salida;
	}
	
	
	public void startTransaction() throws Exception{
		InitConexion_LIQ.getConexion().startTransaction();
	}
	
	public void endTransaction() throws Exception{
		InitConexion_LIQ.getConexion().endTransaction();
	}
	
	public void commitTransaction() throws Exception{
		InitConexion_LIQ.getConexion().commitTransaction();
	}
	
	public void closeConnection() throws Exception{
		InitConexion_LIQ.closeConexion_LME();
	}

}
