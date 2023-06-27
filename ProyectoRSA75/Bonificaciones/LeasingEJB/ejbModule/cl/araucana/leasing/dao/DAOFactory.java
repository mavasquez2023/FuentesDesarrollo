package cl.araucana.leasing.dao;

/**
 * Fabrica generica que genera los acceso a las F�bricas de DAOs Espec�ficos
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 */
abstract public class DAOFactory {
  public final static int DB2 = 0;
  public final static int DUMMY = 50000;
  public static final int NUMBER_NULLVALUE = -1;

  public abstract LeasingDAO getLeasingDAO() throws Exception;

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
      case DUMMY:
      	return new DummyDAOFactory(); 
      default:
        return null;

    }
  }

}
