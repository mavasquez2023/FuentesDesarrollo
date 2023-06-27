package cl.araucana.autoconsulta.dao;

import cl.araucana.autoconsulta.dao.credito.ICreditoDAO;
import cl.araucana.autoconsulta.dao.usuarioServicio.IUsuarioServicioDAO;

/**
 * Fabrica generica que genera los acceso a las Fábricas de DAOs Específicos
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 */
abstract public class DAOFactory {
  public final static int DUMMY = 50000;
  public final static int DB2 = 0;

  public abstract AutoconsultaDAO getAutoconsultaDAO() throws Exception;
  public abstract ICreditoDAO getCreditoDAO() throws Exception;
  public abstract IUsuarioServicioDAO getUsuarioServicioDAO() throws Exception;

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
	  //return new DummyDAOFactory();
      default:
        return null;

    }
  }

}
