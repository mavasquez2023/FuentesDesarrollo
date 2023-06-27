package cl.araucana.tesoreria.dao;

/**
 * Fabrica generica que genera los DAOs para Cobol
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class CobolDAOFactory extends DAOFactory {

	/** Obtiene DAO de Comprobante */
	public ComprobanteDAO getComprobanteDAO() throws Exception {
	  if (true) throw new UnsupportedOperationException("Comprobantes no tiene implementación vía COBOL");
	  return null;
	}

	/** Obtiene DAO de Comprobante */
	public FolioDAO getFolioDAO() throws Exception {
		return new CobolFolioDAO();
	}

}