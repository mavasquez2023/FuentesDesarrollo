/*
 * Created on 11-10-2011
 *
 */

package com.microsystem.lme.svc.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.microsystem.lme.ibatis.domain.Ilf1000VO;
import com.microsystem.lme.ibatis.domain.Ilfe000VO;
import com.microsystem.lme.ibatis.domain.Ilfe002InversoVO;
import com.microsystem.lme.ibatis.domain.Ilfe002VO;
import com.microsystem.lme.ibatis.domain.Ilfe080VO;
import com.microsystem.lme.svc.IAS400Svc_SIL;
import com.microsystem.lme.svc.InitConexion_SIL;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.SQLExceptionHandler;

/**
 * @author microsystem
 * 
 */

public class AS400SvcImpl_SIL implements IAS400Svc_SIL {

	private Logger log = Logger.getLogger(this.getClass());

	public int deleteIlfe000InIlfe002R() {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int deleteIlfe002R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int deleteIlfe003R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int deleteIlfe004R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int deleteIlfe005R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int deleteIlfe080(String estado) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.delete("deleteIlfe080", estado);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int deleteIlfe080(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida= sqlMapLocal.delete("deleteIlfe080a", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			salida = e.getErrorCode();
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}
	
	/*No se llama desde ningun lado */
	
	public boolean existeEnIlfe000(Ilfe000VO vo) {
		boolean exist = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			List resp = sqlMapLocal.queryForList("getIlfe000", vo);
			if (resp.size() != 0) {
				exist = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
			exist = false;
		}
		InitConexion_SIL.closeConexion_SIL();
		return exist;
	}

	public List getIlf1000(Ilf1000VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlf1000", vo);
		} catch (SQLException e) {
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public List getIlfe002R(Ilfe002VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe002R", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public List getIlfe082() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlf082ToDelete", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public List getInverso(Ilfe002InversoVO vo) throws SvcException {

		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getIlfe002RInverso", vo);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public List getLicencias() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getLicencias", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}
	
	public List getLicenciasMixtas() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getLicenciasMixtas", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLException(e);
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public List getLmeCero() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		List salida = new ArrayList();
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		try {
			salida = sqlMapLocal.queryForList("getLmeCero", null);
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			SQLExceptionHandler.handleSQLExceptionNoValidate(e);
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int updateIlfe002R(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public int updateIlfe002RError(Map h) {
		SqlMapClient sqlMapLocal = null;
		int salida;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return salida;
	}

	public boolean updateLMECero(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return respuesta;

	}

	public boolean updateLMECeroNumImprela(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return respuesta;
	}

	public boolean updateLMECeroNumImprela8600(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion_SIL.getConexion();
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
		InitConexion_SIL.closeConexion_SIL();
		return respuesta;
	}

}
