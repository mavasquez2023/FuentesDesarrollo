package cl.araucana.personal.dao;

/**
 * Fabrica generica que genera los DAOs para SQL Server
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Sep�lveda
 * @version 1.0
 */
public class SqlServerDAOFactory extends DAOFactory {

  /** Obtiene DAO de Empleados en Axis */
  public AxisDAO getAxisDAO() throws Exception {
    return new SqlServerAxisDAO();
  }
  
  /** Obtiene DAO de Empleados */
  public PersonalDAO getPersonalDAO() throws Exception {
		if (true) throw new UnsupportedOperationException("Personal no tiene implementaci�n v�a Sql Server");
		return null;
  }  
  
  /** Obtiene DAO de Descuento */
  public DescuentoDAO getDescuentoDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Descuentos no tiene implementaci�n v�a DB2");
	return null;
  }

}