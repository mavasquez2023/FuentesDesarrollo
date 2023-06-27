package cl.araucana.personal.dao;

/**
 * Fabrica generica que genera los DAOs para DB2
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: schema ltda.</p>
 * @author Alejandro Ituarte
 * @version 1.0
 */
public class DB2DAOFactory extends DAOFactory {

  /** Obtiene DAO de Empleados */
  public PersonalDAO getPersonalDAO() throws Exception {
    return new DB2PersonalDAO();
  }
  
  /** Obtiene DAO de Descuento */
  public DescuentoDAO getDescuentoDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Descuentos no tiene implementación vía DB2");
	return null;
  }
  
  /** Obtiene DAO de Personal en Axis */
  public AxisDAO getAxisDAO() throws Exception {
	if (true) throw new UnsupportedOperationException("Axis no tiene implementación vía DB2");
	return null;
  }  

}