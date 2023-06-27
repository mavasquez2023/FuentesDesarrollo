package cl.araucana.tesoreria.dao;

/**
 * Fabrica generica que genera los acceso a las Fábricas de DAOs Específicos
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 */
abstract public class DAOFactory {
  public final static int DB2 = 0;
  public final static int COBOL = 1;
  public final static int DUMMY = 50000;

  public abstract ComprobanteDAO getComprobanteDAO() throws Exception;
  public abstract FolioDAO getFolioDAO() throws Exception;

  public DAOFactory() {
  }

  /**
   * Entrega el DAO Factory segun el tipo de Repositorio
   * @param whichFactory: indicador del repositorio
   */
  public static DAOFactory getDAOFactory(int whichFactory) throws Exception {
    switch (whichFactory) {
      case DB2:
        return new DB2DAOFactory();
	case COBOL:
	  return new CobolDAOFactory();
	  case DUMMY:
		return new DummyDAOFactory();	  
      default:
        return null;
    }
  }

}
