package cl.araucana.autoconsulta.dao;

import cl.araucana.autoconsulta.dao.credito.DB2CreditoDAO;
import cl.araucana.autoconsulta.dao.credito.ICreditoDAO;
import cl.araucana.autoconsulta.dao.usuarioServicio.DB2UsuarioServicioDAO;
import cl.araucana.autoconsulta.dao.usuarioServicio.IUsuarioServicioDAO;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DB2DAOFactory extends DAOFactory {

  /** Obtiene DAO de Empresas */
  public AutoconsultaDAO getAutoconsultaDAO() throws Exception {
    return new DB2AutoconsultaDAO();
  }
  public IUsuarioServicioDAO getUsuarioServicioDAO() throws Exception {
	    return new DB2UsuarioServicioDAO();
	  }

  public ICreditoDAO getCreditoDAO() throws Exception {
	    return new DB2CreditoDAO();
	  }

}