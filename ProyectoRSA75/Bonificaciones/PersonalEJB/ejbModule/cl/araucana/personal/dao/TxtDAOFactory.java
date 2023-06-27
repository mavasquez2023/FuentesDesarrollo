package cl.araucana.personal.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class TxtDAOFactory extends DAOFactory {
 
  /** Obtiene DAO de Descuento */
  public DescuentoDAO getDescuentoDAO() throws Exception {
	return new TxtDescuentoDAO();
  }
  
  /** Obtiene DAO de Empleados */
  public PersonalDAO getPersonalDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Personal no tiene implementación vía Txt");
	return null;
  }
  
  /** Obtiene DAO de Axis */
  public AxisDAO getAxisDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Axis no tiene implementación vía Txt");
	return null;
  }  

}