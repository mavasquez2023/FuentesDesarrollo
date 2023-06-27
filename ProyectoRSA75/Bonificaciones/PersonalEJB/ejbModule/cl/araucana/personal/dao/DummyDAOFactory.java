package cl.araucana.personal.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Sep�lveda
 * @version 1.0
 */
public class DummyDAOFactory extends DAOFactory {

  /** Obtiene DAO de Empleados */
  public PersonalDAO getPersonalDAO() throws Exception {
	return new DummyPersonalDAO();
  }
  
  /** Obtiene DAO de Descuento */
  public DescuentoDAO getDescuentoDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Personal no tiene implementaci�n v�a Dummy");
	return null;
  }
  
  /** Obtiene DAO de Axis */
  public AxisDAO getAxisDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Axis no tiene implementaci�n v�a Dummy");
	return null;
  }  

}