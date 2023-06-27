package cl.araucana.prestamo.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DB2DAOFactory extends DAOFactory {

  /** Obtiene DAO de Prestamo */
  public PrestamoDAO getPrestamoDAO() throws Exception {
    return new DB2PrestamoDAO();
  }

}