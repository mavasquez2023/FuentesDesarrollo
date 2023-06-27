/*
 * Created on 11-10-2011
 *
 */

package com.microsystem.lme.svc.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.microsystem.lme.ibatis.domain.Ilf1000VO;
import com.microsystem.lme.ibatis.domain.Ilfe000VO;
import com.microsystem.lme.ibatis.domain.Ilfe002InversoVO;
import com.microsystem.lme.ibatis.domain.Ilfe002VO;
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
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.svc.IAS400Svc;
import com.microsystem.lme.svc.InitConexion;
import com.microsystem.lme.svc.exception.SvcException;

/**
 * @author microsystem
 *
 */

public class AS400SvcImpl implements IAS400Svc {

	//	private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());

	public void closeSqlMap() {
		//		try {
		//			sqlMap.endTransaction();
		//		} catch (SQLException e) {
		//			log.error(e.getClass() + "; "+ e.getMessage());
		//		}
		//		sqlMap = null;
	}

	public boolean updateLMECero(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {

			sqlMapLocal.update("updateLMECero", h);
			respuesta = true;
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			respuesta = false;

		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}

		return respuesta;

	}

	//pto
	public boolean updateLMECeroNumImprela(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {

			sqlMapLocal.update("updateLMECeroNumImprela", h);
			respuesta = true;
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			respuesta = false;

		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}

		return respuesta;

	}

	public boolean updateLMECeroNumImprela8600(Map h) {
		boolean respuesta = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {

			sqlMapLocal.update("updateLMECeroNumImprela8600", h);
			respuesta = true;
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			respuesta = false;

		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}

		return respuesta;

	}

	//fin pto

	/*11-01-2013*/

	public List getIlf1000(Ilf1000VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlf1000", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public List getIdeOpe(UrlBorderVO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getOpeVordel", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public List getLmeCero() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();
		try {
			return sqlMapLocal.queryForList("getLmeCero", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.AS400Svc#getIlfe031()
	 */

	public List getIlfe031(Ilfe031VO ilfe031VO) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			//log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n"+e1);
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();
		try {
			return sqlMapLocal.queryForList("getIlfe031", ilfe031VO);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; "+ e.getMessage());
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				//log.error(e.getClass() + "; "+ e.getMessage());
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/*20120819 INICIO*/

	public List getIlfe051R() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe051R", "");
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/*FIN*/

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe033(com.microsystem.lme.ibatis.domain.Ilfe033VO)
	 */
	public List getIlfe033(Ilfe033VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe033", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe034(com.microsystem.lme.ibatis.domain.Ilfe034VO)
	 */
	public List getIlfe034(Ilfe034VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe034", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getUrlBorder(com.microsystem.lme.ibatis.domain.UrlBorderVO)
	 */
	public String getUrlVordel(UrlBorderVO vo) throws SvcException {
		String url = null;
		List l = null;

		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			l = sqlMapLocal.queryForList("getUrlBorder", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}

		if (null != l && l.size() > 0) {
			UrlBorderVO o = (UrlBorderVO) l.iterator().next();
			url = o.getUrlOpe().trim() + o.getUrldeTOpe().trim();
			//			logger.logInfo("URL ---->" + url);
		}
		return url;
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe031(com.microsystem.lme.ibatis.domain.Ilfe031VO)
	 */
	public int updateIlfe031(Ilfe031VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe031", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe021()
	 */
	public List getIlfe021() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe021", "");
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe021(com.microsystem.lme.ibatis.domain.Ilfe021VO)
	 */
	public int updateIlfe021(Ilfe021VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe021", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe051()
	 */
	public List getIlfe051() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe051", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe051(com.microsystem.lme.ibatis.domain.Ilfe051VO)
	 */
	public int updateIlfe051(Ilfe051VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe051", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/*20120819*/

	public int updateIlfe051R(Ilfe051RVO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe051R", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/***********/

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe011()
	 */
	public List getIlfe011() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe011", "");
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe013(com.microsystem.lme.ibatis.domain.Ilfe013VO)
	 */
	public List getIlfe013(Ilfe013VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe013", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe011(com.microsystem.lme.ibatis.domain.Ilfe011VO)
	 */
	public int updateIlfe011(Ilfe011VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe011", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#deleteIlfe000()
	 */
	public int deleteIlfe000() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe000", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe000InIlfe002R() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe000InIlfe002R", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfeOpe(com.microsystem.lme.ibatis.domain.IlfeOpeVO)
	 */
	public List getIlfeOpe(IlfeOpeVO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfeOpe", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe000(com.microsystem.lme.ibatis.domain.Ilfe000VO)
	 */
	public String insertIlfe000(Ilfe000VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe000", vo);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public boolean existeEnIlfe000(Ilfe000VO vo) {
		boolean exist = false;
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			List resp = sqlMapLocal.queryForList("getIlfe000", vo);
			if (resp.size() != 0) {
				exist = true;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
			exist = false;
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
		return exist;

	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getLicencias()
	 */
	public List getLicencias() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getLicencias", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getIlfe002R(com.microsystem.lme.ibatis.domain.Ilfe002VO)
	 */
	public List getIlfe002R(Ilfe002VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe002R", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public List getInverso(Ilfe002InversoVO vo) throws SvcException {

		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe002RInverso", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe002(java.util.Map)
	 */
	public String insertIlfe002(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe002", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe003(java.util.Map)
	 */
	public String insertIlfe003(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe003", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				//log.error(e.getClass() + "; "+ e.getMessage());
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	//insertIlfeRED
	public String insertIlfeRED(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfeRED", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe004(java.util.Map)
	 */
	public String insertIlfe004(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe004", h);
			return "OK";
		} catch (SQLException e) {
			log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe005(java.util.Map)
	 */
	public String insertIlfe005(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe005", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe031(java.util.Map)
	 */
	public int updateIlfe031(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe031Map", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe006(java.util.Map)
	 */
	public String insertIlfe006(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe006", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe007(java.util.Map)
	 */
	public String insertIlfe007(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe007", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe000(java.util.Map)
	 */
	public int updateIlfe000(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe000", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getLicenciasMixtas()
	 */
	public List getLicenciasMixtas() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getLicenciasMixtas", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)

	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe002R(java.util.Map)

	 */

	public int updateIlfe002R(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe002R", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#deleteInjection(java.util.Map)
	 */
	public int deleteIlfe002R(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe002R", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#deleteIlfe003R(java.util.Map)
	 */
	public int deleteIlfe003R(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe003R", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#deleteIlfe004R(java.util.Map)
	 */
	public int deleteIlfe004R(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe004R", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#deleteIlfe005R(java.util.Map)
	 */
	public int deleteIlfe005R(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe005R", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertLog(com.microsystem.lme.ibatis.domain.LmeLogVO)
	 */
	public String insertLog(LmeLogVO logVO) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertLog", logVO);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getLog(com.microsystem.lme.ibatis.domain.LmeLogVO)
	 */
	public List getLog(LmeLogVO logVO) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getLog", logVO);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#updateIlfe002RError(java.util.Map)
	 */
	public int updateIlfe002RError(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe002RError", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#getOpeVordel(com.microsystem.lme.ibatis.domain.UrlBorderVO)
	 */
	public List getOpeVordel(UrlBorderVO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getOpeVordel", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe002() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe002", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe003() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe003", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe004() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe004", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe005() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe005", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe006() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe006", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe007() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe007", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}
	
	public int deleteIlfe008() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe008", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}
	
	public int deleteIlfe009() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe009", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe051(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe051", h);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe008(java.util.Map)
	 */
	public String insertIlfe008(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe008", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.microsystem.lme.svc.IAS400Svc#insertIlfe009(java.util.Map)
	 */
	public String insertIlfe009(Map h) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			sqlMapLocal.insert("insertIlfe009", h);
			return "OK";
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getMessage();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public String insertIlfe000HER() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();
		String res = "oK";
		try {
			sqlMapLocal.insert("insertSelectIlfe000HER", null);
		} catch (SQLException e) {
			res = "NoK";
			//log.error(e.getClass() + "; " + e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
		return res;
	}

	public String insertIlfe080(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		String res = "";
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();
		res = "oK";
		try {
			sqlMapLocal.insert("insertIlfe080", vo);
		} catch (SQLException e) {
			res = "NoK";
			//log.error(e.getClass() + "; " + e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
		return res;
	}

	public String insertIlfe081(Ilfe081VO vo) {
		SqlMapClient sqlMapLocal = null;
		String res = "";
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();
		try {
			sqlMapLocal.insert("insertIlfe081", vo);
			res = "oK";
		} catch (SQLException e) {
			res = "NoK";
			//log.error(e.getClass() + "; " + e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
		return res;
	}

	public List getIlfe080() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe080All", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public List getIlfe081() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.queryForList("getIlfe081", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe080() {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe080", null);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe080(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe080a", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int deleteIlfe081(Ilfe081VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.delete("deleteIlfe081", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public int updateIlfe080(Ilfe080VO vo) {
		SqlMapClient sqlMapLocal = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
		SqlMapSession session = sqlMapLocal.openSession();

		try {
			return sqlMapLocal.update("updateIlfe080", vo);
		} catch (SQLException e) {
			//log.error(e.getClass() + "; " + e.getMessage());
			return e.getErrorCode();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				log.error(e.getClass() + "; " + e.getMessage());
			}
		}
	}

	public boolean insertIlfe082(Ilfe082VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		SqlMapSession session = null;
		boolean res = false;
		try {
			sqlMapLocal = InitConexion.getConexion();
			session = sqlMapLocal.openSession();
			session.insert("insertIlfe082", vo);
			res = true;
		} catch (Exception e) {
			session.close();
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException("Error al insertar registro en ILFE082: " + e.getMessage());
		}
		return res;
	}

	public List getIlfe082() throws SvcException {
		SqlMapClient sqlMapLocal = null;
		SqlMapSession session = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
			session = sqlMapLocal.openSession();
			return session.queryForList("getIlf082ToDelete", null);
		} catch (Exception e) {
			session.close();
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
	}

	public int deleteIlfe082(Ilfe082VO vo) throws SvcException {
		SqlMapClient sqlMapLocal = null;
		SqlMapSession session = null;
		try {
			sqlMapLocal = InitConexion.getConexion();
			session = sqlMapLocal.openSession();
			return session.delete("deleteIlfe082", vo);
		} catch (Exception e) {
			session.close();
			log.error(e.getClass() + "; " + e.getMessage());
			throw new SvcException(e.getMessage());
		}
	}
}
