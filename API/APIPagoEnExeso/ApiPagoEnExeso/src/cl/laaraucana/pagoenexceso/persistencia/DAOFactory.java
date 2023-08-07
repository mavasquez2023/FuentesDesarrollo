package cl.laaraucana.pagoenexceso.persistencia;

import org.apache.log4j.Logger;

import cl.laaraucana.config.Config;
import cl.laaraucana.pagoenexceso.persistencia.dao.AfiliadoDaoI;
import cl.laaraucana.pagoenexceso.persistencia.dao.PagoEnExcesoDaoI;

public abstract class DAOFactory {
	private static Logger log = Logger.getLogger(DAOFactory.class);
	private static DAOFactory factory = null;
	
	public abstract PagoEnExcesoDaoI getPagoEnExcesoDao();
	public abstract AfiliadoDaoI getAfiliadoDao();

	
	public static DAOFactory getDaoFactory(){
		if(factory == null){
			String clase = Config.getConfig("factory.class");
			try {
				factory = (DAOFactory) Class.forName(clase).newInstance();
				return factory;
			} catch (Exception e) {
				log.error("Error al crear instancia de DAOFactory: ", e);
			}
		}
		return factory;
	}
}
