/*
 * Created on 11-10-2011
 *
 */

package com.microsystem.lme.svc.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.microsystem.lme.ibatis.domain.Ilfe000VO;
import com.microsystem.lme.ibatis.domain.Ilfe011VO;
import com.microsystem.lme.ibatis.domain.Ilfe013VO;
import com.microsystem.lme.ibatis.domain.Ilfe021VO;
import com.microsystem.lme.ibatis.domain.Ilfe031VO;
import com.microsystem.lme.ibatis.domain.Ilfe033VO;
import com.microsystem.lme.ibatis.domain.Ilfe034VO;
import com.microsystem.lme.ibatis.domain.Ilfe051RVO;
import com.microsystem.lme.ibatis.domain.Ilfe051VO;
import com.microsystem.lme.ibatis.domain.Ilfe080VO;
import com.microsystem.lme.ibatis.domain.Ilfe081VO;
import com.microsystem.lme.ibatis.domain.Ilfe082VO;
import com.microsystem.lme.ibatis.domain.IlfeOpeVO;
import com.microsystem.lme.ibatis.domain.LmeLogVO;
import com.microsystem.lme.ibatis.domain.ParametroVO;
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.InitConexion_LME;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.Configuraciones;
import com.microsystem.lme.util.SQLExceptionHandler;

/**
 * @author microsystem
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


	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.AS400Svc#getIlfe031()
	 */
	public List getIlfe031(Ilfe031VO ilfe031VO) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			//log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n"+e1);
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe031", ilfe031VO);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/*20120819 INICIO*/

	public List getIlfe051R() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe051R", "");
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/*FIN*/

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe033(com.microsystem.lme.ibatis.domain.Ilfe033VO)
	 */
	public List getIlfe033(Ilfe033VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe033", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe034(com.microsystem.lme.ibatis.domain.Ilfe034VO)
	 */
	public List getIlfe034(Ilfe034VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe034", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}


	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe031(com.microsystem.lme.ibatis.domain.Ilfe031VO)
	 */
	public int updateIlfe031(Ilfe031VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
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

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe021()
	 */
	public List getIlfe021() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe021", "");
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe021(com.microsystem.lme.ibatis.domain.Ilfe021VO)
	 */
	public int updateIlfe021(Ilfe021VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe021", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe051()
	 */
	public List getIlfe051() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe051", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe051(com.microsystem.lme.ibatis.domain.Ilfe051VO)
	 */
	public int updateIlfe051(Ilfe051VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe051", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	/*20120819*/

	public int updateIlfe051R(Ilfe051RVO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe051R", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	/***********/

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe011()
	 */
	public List getIlfe011() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe011", "");
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe013(com.microsystem.lme.ibatis.domain.Ilfe013VO)
	 */
	public List getIlfe013(Ilfe013VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe013", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe011(com.microsystem.lme.ibatis.domain.Ilfe011VO)
	 */
	public int updateIlfe011(Ilfe011VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe011", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#deleteIlfe000()
	 */
	public int deleteIlfe000() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe000", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}


	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfeOpe(com.microsystem.lme.ibatis.domain.IlfeOpeVO)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe000(com.microsystem.lme.ibatis.domain.Ilfe000VO)
	 */
	public String insertIlfe000(Ilfe000VO vo) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe000", vo);
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
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe002(java.util.Map)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe003(java.util.Map)
	 */
	public String insertIlfe003(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe003", h);
			salida = "OK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getMessage();
		}
		return salida;
	}

	//insertIlfeRED
	public String insertIlfeRED(Map h) {
		SqlMapClient sqlMapLocal = null;
		String salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfeRED", h);
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
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe004(java.util.Map)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe005(java.util.Map)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe031(java.util.Map)
	 */
	public int updateIlfe031(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe031Map", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe006(java.util.Map)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe007(java.util.Map)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe000(java.util.Map)
	 */
	public int updateIlfe000(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe000", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertLog(com.microsystem.lme.ibatis.domain.LmeLogVO)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#getLog(com.microsystem.lme.ibatis.domain.LmeLogVO)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#getOpeVordel(com.microsystem.lme.ibatis.domain.UrlBorderVO)
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

	public int deleteIlfe002() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe002", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public int deleteIlfe003() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe003", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public int deleteIlfe004() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe004", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public int deleteIlfe005() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe005", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public int deleteIlfe006() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe006", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public int deleteIlfe007() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe007", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}
	
	public int deleteIlfe008() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe008", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}
	
	public int deleteIlfe009() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe009", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public int deleteIlfe051(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe051", h);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	/*
	 * (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe008(java.util.Map)
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
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe009(java.util.Map)
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

	public String insertIlfe080(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		String res = "";
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		res = "oK";
		try {
			sqlMapLocal.insert("insertIlfe080", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			res = "NoK";
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return res;
	}

	public String insertIlfe081(Ilfe081VO vo) {
		SqlMapClient sqlMapLocal = null;
		String res = "";
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe081", vo);
			res = "oK";
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			res = "NoK";
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return res;
	}
	
	public String insertIlfe085(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		String res = "";
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		res = "oK";
		try {
			sqlMapLocal.insert("insertIlfe085", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			res = "NoK";
			//log.error(e.getClass() + "; " + e.getMessage());
		}
		return res;
	}

	public List getIlfe080() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe080All", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}
	
	public List getIlfe085() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe085", null);
		} catch (SQLException e) {
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}
	
	public List getIlfe081() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe081", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		return salida;
	}



	public int deleteIlfe081(Ilfe081VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe081", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}
	
	public int deleteIlfe085(String estado) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe085", estado);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public int updateIlfe080(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe080", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}
	
	public int updateIlfe085(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.update("updateIlfe085", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			//log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		return salida;
	}

	public boolean insertIlfe082(Ilfe082VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		boolean res = false;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			sqlMapLocal.insert("insertIlfe082", vo);
			res = true;
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			res = false;
			log.error(e.getClass() + "; " + e.getMessage());
		}
		return res;
	}
	
	public int deleteIlfe082(Ilfe082VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		int salida = 0;
		try {
			sqlMapLocal = InitConexion_LME.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try{
			salida = sqlMapLocal.delete("deleteIlfe082", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			log.error(e.getClass() + "; " + e.getMessage());
		}
		return salida;
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
}
