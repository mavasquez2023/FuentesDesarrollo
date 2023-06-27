package cl.araucana.bienestar.bonificaciones.dao;

/**
 * Fabrica generica que genera los DAOs para XML
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class XMLDAOFactory extends DAOFactory {

  /** Obtiene DAO de Bonificaciones */
  public BonificacionesDAO getBonificacionesDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Bonificaciones no tiene implementación vía XML");
	return null;
    
  }
  
  /** Obtiene DAO de Parametros */
  public ParametrosDAO getParametrosDAO() throws Exception {
	return new XMLParametrosDAO();
  }

}