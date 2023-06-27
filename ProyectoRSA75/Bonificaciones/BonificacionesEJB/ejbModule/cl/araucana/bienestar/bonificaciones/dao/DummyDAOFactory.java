package cl.araucana.bienestar.bonificaciones.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DummyDAOFactory extends DAOFactory {

	/** Obtiene DAO de Bonificaciones */
   public BonificacionesDAO getBonificacionesDAO() throws Exception {
	  return new DummyBonificacionesDAO();
   }
  
   /** Obtiene DAO de Parametros */
   public ParametrosDAO getParametrosDAO() throws Exception {
	 if (true) throw new UnsupportedOperationException("Parametros no tiene implementación vía DB2");
	 return null;
   }
  
}