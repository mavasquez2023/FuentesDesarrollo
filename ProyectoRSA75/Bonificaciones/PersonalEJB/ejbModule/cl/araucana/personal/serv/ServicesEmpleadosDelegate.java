package cl.araucana.personal.serv;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.personal.serv.ServicesEmpleadosSLBean.ServicesEmpleados;
import cl.araucana.personal.serv.ServicesEmpleadosSLBean.ServicesEmpleadosHome;
import cl.araucana.personal.vo.CargaVO;
import cl.araucana.personal.vo.EmpleadoVO;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Empleados
 */
public class ServicesEmpleadosDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesEmpleadosHome.class;
	private ServicesEmpleados services=null;
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesEmpleadosDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/personal/empleados-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesEmpleados)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}
		

	/** 
	 * Entrega la lista de Empleados de La Araucana
	 * @param rut String de busqueda en Rut
	 * @param nombre String de busqueda en nombre
	 * @return ArrayList de EmpleadoVO
	 * @throws Exception
	 */
	public ArrayList getListaEmpleados(String rut, String nombre) throws Exception,BusinessException {
		return services.getListaEmpleados(rut,nombre);		
	}

	/** 
	 * Entrega un Empleado de La Araucana
	 * @param rut String de busqueda en Rut
	 * @return EmpleadoVO
	 * @throws Exception
	 */
	public EmpleadoVO getEmpleado(String rut) throws Exception,BusinessException {
		return services.getEmpleado(rut);		
	}
   
	/**
	 * Obtiene una Carga Familiar desde el Sistema de Beneficios
	 * @param rut de carga familiar
	 * @return Carga Familiar CargaVO
	 * @throws Exception
	 */
	public CargaVO getCargaFamiliar(long rut) throws Exception,BusinessException{
		return services.getCargaFamiliar(rut);
	}
   
	/**
	 * Obtiene las Cargas Familiares de un Empleado desde el Sistema de Beneficios
	 * @param rut de Empleado
	 * @return ArrayList de CargaVO
	 * @throws Exception
	 */	
	public ArrayList getListaCargasFamiliaresEmpleado(long rut) throws Exception,BusinessException{
		return services.getListaCargasFamiliaresEmpleado(rut);
	}
	
	/**
	 * Obtiene la glosa de una Oficina
	 * Param lugar de pago (cod Oficina)
	 */
	public String getOficinaEmpleado(String codigoOficina) throws Exception,BusinessException {
	 return services.getOficinaEmpleado(codigoOficina);
	}
   
	/**
	 * Obtiene la glosa de un Departamento
	 * Param codigo de Cargo de un funcionario
	 */
	public String getDeptoEmpleadoByCodigoCargo(String codigoCargo) throws Exception,BusinessException {
		 return services.getDeptoEmpleadoByCodigoCargo(codigoCargo);
	}
	
	/**
	 * Obtiene la glosa de un Departamento
	 * Param String, codigo de departamento
	 */
	public String getDeptoEmpleadoByCodigoDepto(String codigoDepartamento) throws Exception,BusinessException {
	  return services.getDeptoEmpleadoByCodigoDepto(codigoDepartamento);
	 }	
	
	/**
	 * Traduce codigo de Ciudad
	 * @param codCiudad
	 * @return String con nombre de Ciudad
	 * @throws Exception
	 * @throws BusinessException
	 */
   public String getCiudad(String codCiudad) throws Exception,BusinessException {
	 return services.getCiudad(codCiudad);
   }
  
   /**
	* Traduce codigo de Comuna
	* @param codComuna
	* @return String con nombre de Comuna
	* @throws Exception
	* @throws BusinessException
	*/
  public String getComuna(String codComuna) throws Exception,BusinessException {
	 return services.getComuna(codComuna);
  }
  
  /**
   * Recibe información de los Descuentos
   * @param descuentos
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  
  public void generarDescuentos(ArrayList descuentos, String fileName) throws Exception,BusinessException {
	 services.generarDescuentos(descuentos, fileName);
  }
  
  /** 
   * Entrega la lista de bancos en un HashMap
   * 
   * @return HashMap de BancoVO
   * @throws Exception
   */
  public HashMap getListaBancos() throws Exception,BusinessException {
 		return services.getListaBancos();
 	}  

}
