package cl.araucana.tesoreria.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DummyDAOFactory extends DAOFactory {

  /** Obtiene DAO de Comprobante */
  public ComprobanteDAO getComprobanteDAO() throws Exception {
    return new DummyComprobanteDAO();
  }

  /** Obtiene DAO de Comprobante */
  public FolioDAO getFolioDAO() throws Exception {
  	if (true) throw new UnsupportedOperationException("Folios no tiene implementación vía DB2");
	return null;
  }

}