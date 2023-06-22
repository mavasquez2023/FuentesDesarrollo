package cl.laaraucana.recepcionsil.persistencia;

import org.apache.log4j.Logger;

import cl.laaraucana.config.Config;
import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.recepcionsil.persistencia.dao.ServIngDaoI;

public abstract class DAOFactory {
	private static Logger log = Logger.getLogger(DAOFactory.class);
	private static DAOFactory factory = null;

	public abstract ServIngDaoI getServIngresoDao();

	public static DAOFactory getDaoFactory() throws DaoException {
		if (factory == null) {
			String clase = Config.getConfig("factory.class");
			try {
				factory = (DAOFactory) Class.forName(clase).newInstance();
				return factory;
			} catch (Exception e) {
				log.error("Error al crear instancia de DAOFactory: ", e);
				throw new DaoException("Error al crear instancia de DAOFactory", e);
			}
		}
		return factory;
	}
}
