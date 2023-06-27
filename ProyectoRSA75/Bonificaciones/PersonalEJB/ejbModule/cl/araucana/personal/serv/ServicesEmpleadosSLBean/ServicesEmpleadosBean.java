package cl.araucana.personal.serv.ServicesEmpleadosSLBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.personal.dao.AxisDAO;
import cl.araucana.personal.dao.DAOFactory;
import cl.araucana.personal.dao.DescuentoDAO;
import cl.araucana.personal.dao.PersonalDAO;
import cl.araucana.personal.vo.CargaVO;
import cl.araucana.personal.vo.EmpleadoVO;

import com.schema.util.FileSettings;

/**
 * @author aituarte
 * Bean implementation class for Enterprise Bean: ServicesEmpleados
 * Servicios de Consulta a Información de Empleados de La Araucana
 */
public class ServicesEmpleadosBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private PersonalDAO personalDao;
	private DescuentoDAO descuentoDao;
	private AxisDAO axisDao;
	
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
		// Recurso DAO DB2 de Personal
		int personalDaoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/personal/personal-dao-type"));
		// Recurso DAO Txt de Personal
		int descuentoDaoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/personal/descuento-dao-type"));
		// Recurso DAO SQL Server de Personal
		int axisDaoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/personal/axis-dao-type"));		
		
			 
		try {
			//DAO Personal
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(personalDaoType);
			personalDao = daoFactory.getPersonalDAO();
			//DAO Descuentos
			daoFactory = (DAOFactory)DAOFactory.getDAOFactory(descuentoDaoType);
			descuentoDao = daoFactory.getDescuentoDAO();
			//DAO Axis
			daoFactory = (DAOFactory)DAOFactory.getDAOFactory(axisDaoType);
			axisDao = daoFactory.getAxisDAO();			
			
		} catch (Exception e) {
			throw new javax.ejb.CreateException(e.getMessage());
		}
	
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	
	/** 
	 * Entrega la lista de Empleados de La Araucana
	 * @param rut String de busqueda en Rut
	 * @param nombre String de busqueda en nombre
	 * @return ArrayList de EmpleadoVO
	 * @throws Exception
	 */
	public ArrayList getListaEmpleados(String rut, String nombre) throws Exception,BusinessException {
		return personalDao.getListaEmpleados(rut,nombre);
		
	}
	
	/**
	 * Obtiene un Empleado de La Araucana desde el Sistema de Personal
	 * @param rut de Empleado
	 * @return Empleado EmpleadoVO
	 * @throws Exception
	 */
   public EmpleadoVO getEmpleado(String rut) throws Exception,BusinessException {
   		
		EmpleadoVO empleado = personalDao.getEmpleado(rut);
		if (empleado.getFecInicioBeneficio() == null)
			empleado.setFecInicioBeneficio(new Date());
		return empleado;
   }
   
   /**
	* Obtiene una Carga Familiar desde el Sistema de Beneficios
	* @param rut de carga familiar
	* @return Carga Familiar CargaVO
	* @throws Exception
	*/
   public CargaVO getCargaFamiliar(long rut) throws Exception,BusinessException {
		return personalDao.getCargaFamiliar(rut);
   }
   
   /**
	* Obtiene las Cargas Familiares de un Empleado desde el Sistema de Beneficios
	* @param rut de Empleado
	* @return ArrayList de CargaVO
	* @throws Exception
	*/	
   public ArrayList getListaCargasFamiliaresEmpleado(long rut) throws Exception,BusinessException {
		return personalDao.getListaCargasFamiliaresEmpleado(rut);
   }
   
   /**
	* Obtiene la glosa de una Oficina
	* Param lugar de pago (cod Oficina)
	*/
   public String getOficinaEmpleado(String codigoOficina) throws Exception,BusinessException {
	return personalDao.getOficinaEmpleado(codigoOficina);
   }
   
   /**
	* Obtiene la glosa de un Departamento
	* Param codigo de Cargo de un funcionario
	*/
   public String getDeptoEmpleadoByCodigoCargo(String codigoCargo) throws Exception,BusinessException {
		return personalDao.getDeptoEmpleadoByCodigoCargo(codigoCargo);
   }
   
   /**
	* Obtiene la glosa de un Departamento
	* Param String, codigo de departamento
	*/
   public String getDeptoEmpleadoByCodigoDepto(String codigoDepartamento) throws Exception,BusinessException {
	 return personalDao.getDeptoEmpleadoByCodigoDepto(codigoDepartamento);
	}   
   
   /**
	* Traduce codigo de Ciudad
	* @param codCiudad
	* @return String con nombre de Ciudad
	* @throws Exception
	* @throws BusinessException
	*/
  public String getCiudad(String codCiudad) throws Exception,BusinessException {
	return personalDao.getCiudad(codCiudad);
  }
  
  /**
   * Traduce codigo de Comuna
   * @param codComuna
   * @return String con nombre de Comuna
   * @throws Exception
   * @throws BusinessException
   */
 public String getComuna(String codComuna) throws Exception,BusinessException {
	return personalDao.getComuna(codComuna);
 }
 
 /**
  * Recibe información de los Descuentos
  * @param descuentos
  * @return
  * @throws Exception
  * @throws BusinessException
  */
 public void generarDescuentos(ArrayList descuentos, String fileName) throws Exception,BusinessException {
 
	if (descuentos == null || descuentos.size() == 0)
		throw new BusinessException("CCAF_PERSO_DESCUENTOINVALIDO",
							   "La Información de los Descuentos es incorrecta");
							   
	descuentoDao.insertDescuento(descuentos,fileName);
 }
 
 /** 
  * Entrega la lista de bancos en un HashMap
  * 
  * @return HashMap de BancoVO
  * @throws Exception
  */
 public HashMap getListaBancos() throws Exception,BusinessException {
		return axisDao.getListaBancos();
	}

}
