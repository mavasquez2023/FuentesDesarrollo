package cl.araucana.contabilidad.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DB2DAOFactory extends DAOFactory {

  /** Obtiene DAO de Contabilidad */
  public ContabilidadDAO getContabilidadDAO() throws Exception {
    return new DB2ContabilidadDAO();
  }

  /** Obtiene DAO de Folio para Contabilidad */
  public FolioDAO getFolioDAO() throws Exception {
	return new DB2FolioDAO();
  }

}