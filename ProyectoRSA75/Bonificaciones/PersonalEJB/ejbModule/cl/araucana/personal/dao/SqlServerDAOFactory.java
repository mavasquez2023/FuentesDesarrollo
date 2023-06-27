package cl.araucana.personal.dao;

/**
 * Fabrica generica que genera los DAOs para SQL Server
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Sepúlveda
 * @version 1.0
 */
public class SqlServerDAOFactory extends DAOFactory {

  /** Obtiene DAO de Empleados en Axis */
  public AxisDAO getAxisDAO() throws Exception {
    return new SqlServerAxisDAO();
  }
  
  /** Obtiene DAO de Empleados */
  public PersonalDAO getPersonalDAO() throws Exception {
		if (true) throw new UnsupportedOperationException("Personal no tiene implementación vía Sql Server");
		return null;
  }  
  
  /** Obtiene DAO de Descuento */
  public DescuentoDAO getDescuentoDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Descuentos no tiene implementación vía DB2");
	return null;
  }

}