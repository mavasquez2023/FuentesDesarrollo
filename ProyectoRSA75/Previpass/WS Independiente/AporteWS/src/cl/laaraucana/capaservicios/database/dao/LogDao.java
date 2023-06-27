package cl.laaraucana.capaservicios.database.dao;

import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.capaservicios.database.vo.TransferenciaVO;

public class LogDao extends DaoParent {
	
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Registra evento en modelo de datos de crédito especial AS400.
	 * @param transf
	 * @return
	 * @throws Exception
	 */
	public boolean registrarColocacion(TransferenciaVO transf)
			throws Exception {
		SqlMapClient sqlMap = getConn();
		//Separar folio de credito y oficina
		transf.setCodOficina(transf.getFolioCredito().substring(0,3));
		transf.setFolioCredito(transf.getFolioCredito().substring(3, transf.getFolioCredito().length()));
		try {
			sqlMap.insert("registrarColocacion", transf);
			return true;
		} catch (Exception e) {
			//No debe lanzar excepción, ya que no es una tarea invalidante para el proceso de colocación
			logger.error("Error al registrar traza de transferencia: " + transf.getFolioCredito(), e);
		}
		return false;
	}

}
