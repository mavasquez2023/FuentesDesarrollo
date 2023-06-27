package cl.araucana.leasing.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DB2DAOFactory extends DAOFactory {

  /** Obtiene DAO de Leasing */
  public LeasingDAO getLeasingDAO() throws Exception {
    return new DB2LeasingDAO();
  }

}