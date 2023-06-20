/*
 * Created on 11-10-2011
 *
 */

package cl.araucana.lme.svc.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.lme.ibatis.domain.Ilfe000VO;
import cl.araucana.lme.ibatis.domain.Ilfe002VO;
import cl.araucana.lme.ibatis.domain.Ilfe004VO;
import cl.araucana.lme.ibatis.domain.Ilfe021VO;
import cl.araucana.lme.ibatis.domain.Ilfe051VO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.ibatis.domain.LmeLogVO;
import cl.araucana.lme.ibatis.domain.ParametroVO;
import cl.araucana.lme.ibatis.domain.UrlBorderVO;
import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.InitConexion_LME;
import cl.araucana.lme.svc.exception.SvcException;
import cl.araucana.lme.util.Configuraciones;
import cl.araucana.lme.util.SQLExceptionHandler;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;


/**
 * @author j-factory
 *
 */

public class AS400SvcImpl_LME implements IAS400Svc_LME {

	//	private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());


	public List getIdeOpe(UrlBorderVO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getOpeVordel", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			throw new SvcException(e.getMessage());
		}
		return salida;
	}
	
	public List getIlfe002R_Consumo(Map param) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe002R_Consumo", param);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			e.printStackTrace();
			throw new SvcException(e.getMessage());
		}
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}

	public Object getIlfe004R(Map param){
		SqlMapClient sqlMapLocal = null;
		Object salida=null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForObject("getIlfe004R", param);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			e.printStackTrace();
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return salida;
	}
	
	public Object getIlfe009R(Map param){
		SqlMapClient sqlMapLocal = null;
		Object salida=null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForObject("getIlfe009R", param);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			e.printStackTrace();
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return salida;
	}
	
	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#getIlfe051()
	 */
	public int getIlfe051(Ilfe002VO cab_licencia) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		Integer salida= new Integer(0);
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = (Integer)sqlMapLocal.queryForObject("getIlfe051", cab_licencia);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida.intValue();
	}
	

	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#getIlfeOpe(cl.araucana.lme.ibatis.domain.IlfeOpeVO)
	 */
	public List getIlfeOpe(IlfeOpeVO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
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
	 * @see cl.araucana.lme.svc.IAS400Svc#insertIlfe002(java.util.Map)
	 */
	public String insertIlfe002(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe002", h);
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
	 * @see cl.araucana.lme.svc.IAS400Svc#insertIlfe004(java.util.Map)
	 */
	public String insertIlfe004(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe004", h);
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
	 * @see cl.araucana.lme.svc.IAS400Svc#insertIlfe005(java.util.Map)
	 */
	public String insertIlfe005(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe005", h);
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
	 * @see cl.araucana.lme.svc.IAS400Svc#insertIlfe006(java.util.Map)
	 */
	public String insertIlfe006(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe006", h);
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
	 * @see cl.araucana.lme.svc.IAS400Svc#insertIlfe007(java.util.Map)
	 */
	public String insertIlfe007(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe007", h);
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
	 * @see cl.araucana.lme.svc.IAS400Svc#insertLog(cl.araucana.lme.ibatis.domain.LmeLogVO)
	 */
	public String insertLog(LmeLogVO logVO) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
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
			sqlMapLocal = InitConexion_LME.getConexion();
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


	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#getOpeVordel(cl.araucana.lme.ibatis.domain.UrlBorderVO)
	 */
	public List getOpeVordel(UrlBorderVO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getOpeVordel", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#insertIlfe008(java.util.Map)
	 */
	public String insertIlfe008(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe008", h);
			salida = "OK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getMessage();
		}
		return salida;
	}

	/*
	 * (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#insertIlfe009(java.util.Map)
	 */
	public String insertIlfe009(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe009", h);
			salida = "OK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getMessage();
		}
		return salida;
	}

	public String insertIlfe000HER() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		String res = "oK";
		try {
			sqlMapLocal.insert("insertSelectIlfe000HER", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			res = "NoK";
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return res;
	}

	public Map getEndPoints() throws Exception {
		SqlMapClient sqlMapLocal = null;
		Map endPointMap = null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			String prioridad = Configuraciones.getConfig("prioridad.db");
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
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
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
			sqlMapLocal = InitConexion_LME.getConexion();
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

	public int updateIlfe002R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe002R", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}

	public int updateIlfe002RError(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe002RError", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}

	public boolean updateLMECero(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.update("updateLMECero", h);
			respuesta = true;
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			log.error(e.getClass() + "; " + e.getMessage());
			respuesta = false;

		}
		//InitConexion_LME.closeConexion_LME();
		return respuesta;

	}

	public boolean updateLMECeroNumImprela(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.update("updateLMECeroNumImprela", h);
			respuesta = true;
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			log.error(e.getClass() + "; " + e.getMessage());
			respuesta = false;
		}
		//InitConexion_LME.closeConexion_LME();
		return respuesta;
	}

	public boolean updateLMECeroNumImprela8600(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.update("updateLMECeroNumImprela8600", h);
			respuesta = true;
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			log.error(e.getClass() + "; " + e.getMessage());
			respuesta = false;
		}
		//InitConexion_LME.closeConexion_LME();
		return respuesta;
	}
	
	public int deleteIlfe000InIlfe002R() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe000InIlfe002R", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		} 
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}
	
	public int deleteIlfe002R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe002R", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		} 
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}

	public int deleteIlfe003R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe003R", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}

	public int deleteIlfe004R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe004R", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}

	public int deleteIlfe005R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe005R", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}
	
	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#updateIlfe031(cl.araucana.lme.ibatis.domain.Ilfe031VO)
	 */
	public boolean getAF03F1(int rutEmpresa, int rutAfiliado) {
		SqlMapClient sqlMapLocal = null;
		Integer salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			Map param= new HashMap();
			param.put("emprut", new Integer(rutEmpresa));
			param.put("afirut" , new Integer(rutAfiliado ));
			salida = (Integer)sqlMapLocal.queryForObject("getAF03F1", param);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = new Integer(e.getErrorCode());
		}
		return salida.intValue()>0?true:false;
	}
	
	public String insertIlfe051(Ilfe051VO vo) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe051", vo);
			salida = "OK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			e.printStackTrace();
			salida = e.getMessage();
		}
		return salida;
	}
	
	public String insertIlfe021(Ilfe021VO vo) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe021", vo);
			salida = "OK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			e.printStackTrace();
			salida = e.getMessage();
		}
		return salida;
	}
	
	public String updateIlf8600(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = "";
			int resultado= sqlMapLocal.update("updateIlf8600", h);
			if (resultado>0){
				salida = "OK";
			}
		
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			e.printStackTrace();
			salida = e.getMessage();
		}
		return salida;
	}
	
	/* (non-Javadoc)
	 * @see cl.araucana.lme.svc.IAS400Svc#updateIlfe031(cl.araucana.lme.ibatis.domain.Ilfe031VO)
	 */
	public int getIndiceConvenio(int rutemp, int rutafi) {
		SqlMapClient sqlMapLocal = null;
		Integer salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = (Integer)sqlMapLocal.queryForObject("getTAF1000", new Integer(rutemp));
			if(salida.intValue()>0){
				HashMap valor= new HashMap();
				valor.put("RUTEMPLE", new Integer(rutemp));
				valor.put("AFIRUT", new Integer(rutafi));
				salida = (Integer)sqlMapLocal.queryForObject("getILF1000", valor);
				String ind_salud= Configuraciones.getConfig("indice.convenio.salud");
				if(salida==null){
					salida= new Integer(Integer.parseInt(ind_salud));
				}
			}else{
				salida = (Integer)sqlMapLocal.queryForObject("getConvenioGeneral", new Integer(rutemp));
				if(salida.intValue()>0){
					salida= new Integer(1);
				}
			}
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = new Integer(e.getErrorCode());
		}
		return salida.intValue();
	}
	
	public Map getLicContinua(Map h){
		SqlMapClient sqlMapLocal = null;
		Map salida=null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = (Map)sqlMapLocal.queryForObject("getLicenciaContinua", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return salida;
	}
	
	public Map getLicMismoPeriodo(Map h){
		SqlMapClient sqlMapLocal = null;
		Map salida=null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = (Map)sqlMapLocal.queryForObject("getLicenciaMismoPeriodo", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return salida;
	}
	
	public int getArchivoRentas(Map h) {
		SqlMapClient sqlMapLocal = null;
		Integer salida=null;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = (Integer)sqlMapLocal.queryForObject("getArchivoRentas", h);
		} catch (SQLException e) {
			SQLExceptionHandler.handleSQLException(e);
		}
		if(salida==null){
			return 0;
		}else{
			return salida.intValue();
		}
	}
	
	public String insertEstadistica(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
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
	
	public List getEstadisticas(int periodo) throws SvcException{
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			if(periodo>300000){
				salida = sqlMapLocal.queryForList("getEstadistica", new Integer(periodo));
			}else{
				salida = sqlMapLocal.queryForList("getEstadisticaPeriodo", new Integer(periodo));
			}
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		//InitConexion_LME.closeConexion_LME();
		return salida;
	}
	
	public void startTransaction() throws Exception{
		InitConexion_LME.getConexion().startTransaction();
	}
	
	public void endTransaction() throws Exception{
		InitConexion_LME.getConexion().endTransaction();
	}
	
	public void commitTransaction() throws Exception{
		InitConexion_LME.getConexion().commitTransaction();
	}
	
	public void closeConnection() throws Exception{
		InitConexion_LME.closeConexion_LME();
	}

	public int existsIlfe051(Ilfe051VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		Integer salida= new Integer(0);
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = (Integer)sqlMapLocal.queryForObject("existsIlfe051", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida.intValue();

	}

	public int updateIlfe051(Ilfe051VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida=0;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.update("updateIlfe051", vo);
			salida = 1;
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			e.printStackTrace();
		}
		return salida;
	}

}
