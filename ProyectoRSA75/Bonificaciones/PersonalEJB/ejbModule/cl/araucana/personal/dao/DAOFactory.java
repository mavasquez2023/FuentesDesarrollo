package cl.araucana.personal.dao;

/**
 * Fabrica generica que genera los acceso a las Fábricas de DAOs Específicos
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 */
abstract public class DAOFactory {
  public final static int DB2 = 0;
  public final static int TXT = 1;
  public final static int SQLSERVER = 100;
  public final static int DUMMY = 50000;

  public abstract PersonalDAO getPersonalDAO() throws Exception;
  public abstract DescuentoDAO getDescuentoDAO() throws Exception;
  public abstract AxisDAO getAxisDAO() throws Exception;

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
      case TXT:
      	return new TxtDAOFactory();
      case SQLSERVER:
        	return new SqlServerDAOFactory();      	
      case DUMMY:
	  	return new DummyDAOFactory();
      default:
        return null;

    }
  }

}
