package cl.araucana.prestamo.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Sepúlveda
 * @version 1.0
 */
public class DummyDAOFactory extends DAOFactory {

  /** Obtiene DAO de Prestamo */
  public PrestamoDAO getPrestamoDAO() throws Exception {
    return new DummyPrestamoDAO();
  }

}