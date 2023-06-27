package cl.araucana.personal.serv.ServicesEmpleadosSLBean;

import java.util.ArrayList;
import java.util.HashMap;

import cl.araucana.common.BusinessException;
import cl.araucana.personal.vo.CargaVO;
import cl.araucana.personal.vo.EmpleadoVO;

/**
 * Remote interface for Enterprise Bean: ServicesEmpleados
 */
public interface ServicesEmpleados extends javax.ejb.EJBObject {
	
	/** 
	 * Entrega la lista de Empleados de La Araucana
	 * @param rut String de busqueda en Rut
	 * @param nombre String de busqueda en nombre
	 * @return ArrayList de EmpleadoVO
	 * @throws Exception
	 */
	public ArrayList getListaEmpleados(String rut, String nombre) throws Exception,BusinessException;
	
	/**
	 * Obtiene un Empleado de La Araucana desde el Sistema de Personal
	 * @param rut de Empleado
	 * @return Empleado EmpleadoVO
	 * @throws Exception
	 */
   public EmpleadoVO getEmpleado(String rut) throws Exception,BusinessException;
   
   /**
	* Obtiene una Carga Familiar desde el Sistema de Beneficios
	* @param rut de carga familiar
	* @return Carga Familiar CargaVO
	* @throws Exception
	*/
   public CargaVO getCargaFamiliar(long rut) throws Exception,BusinessException;
   
   /**
	* Obtiene las Cargas Familiares de un Empleado desde el Sistema de Beneficios
	* @param rut de Empleado
	* @return ArrayList de CargaVO
	* @throws Exception
	*/	
   public ArrayList getListaCargasFamiliaresEmpleado(long rut) throws Exception,BusinessException;
   
   /**
	* Obtiene la glosa de una Oficina
	* Param lugar de pago (cod Oficina)
	*/
   public String getOficinaEmpleado(String codigoOficina) throws Exception,BusinessException;
   
   /**
	* Obtiene la glosa de un Departamento
	* Param codigo de Cargo de un funcionario
	*/
   public String getDeptoEmpleadoByCodigoCargo(String codigoCargo) throws Exception,BusinessException;
   
   /**
	* Obtiene la glosa de un Departamento
	* Param String, codigo de departamento
	*/
   public String getDeptoEmpleadoByCodigoDepto(String codigoDepartamento) throws Exception,BusinessException;
   
   /**
	* Traduce codigo de Ciudad
	* @param codCiudad
	* @return String con nombre de Ciudad
	* @throws Exception
	* @throws BusinessException
	*/
  public String getCiudad(String codCiudad) throws Exception,BusinessException;
  
  /**
   * Traduce codigo de Comuna
   * @param codComuna
   * @return String con nombre de Comuna
   * @throws Exception
   * @throws BusinessException
   */
  public String getComuna(String codComuna) throws Exception,BusinessException;
 
  /**
   * Recibe información de los Descuentos
   * @param descuentos
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void generarDescuentos(ArrayList descuentos, String fileName) throws Exception,BusinessException;
   
  /** 
   * Entrega la lista de bancos en un HashMap
   * 
   * @return HashMap de BancoVO
   * @throws Exception
   */
  public HashMap getListaBancos() throws Exception,BusinessException;
 	
}
