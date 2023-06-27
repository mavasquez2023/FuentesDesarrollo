package cl.araucana.contabilidad.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DummyDAOFactory extends DAOFactory {

  /** Obtiene DAO de Contabilidad */
  public ContabilidadDAO getContabilidadDAO() throws Exception {
    return new DummyContabilidadDAO();
  }

  /** Obtiene DAO de Folio para Contabilidad */
  public FolioDAO getFolioDAO() throws Exception {
	return new DummyFolioDAO();
  }

}