package cl.araucana.bienestar.bonificaciones.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DB2DAOFactory extends DAOFactory {

	/** Obtiene DAO de Bonificaciones */
   public BonificacionesDAO getBonificacionesDAO() throws Exception {
	  return new DB2BonificacionesDAO();
   }
  
   /** Obtiene DAO de Parametros */
   public ParametrosDAO getParametrosDAO() throws Exception {
	 if (true) throw new UnsupportedOperationException("Parametros no tiene implementación vía DB2");
	 return null;
   }
  
}