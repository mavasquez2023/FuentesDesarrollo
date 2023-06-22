package cl.laaraucana.mediopagosilws.core.database.dao;

public abstract class DAOFactory {
	// private static Logger log = Logger.getLogger(DAOFactory.class);
	private static DAOFactory factory = null;

	public abstract MedioPagoDaoI getMedioPagoDao();

	public static DAOFactory getDaoFactory() {
		if (factory == null) {
			//String clase = DB2Factory.class.toString();
			try {
				factory = (DAOFactory) DB2Factory.class.newInstance();
				return factory;
			} catch (Exception e) {
				// log.error("Error al crear instancia de DAOFactory: ", e);
			}
		}
		return factory;
	}
}
