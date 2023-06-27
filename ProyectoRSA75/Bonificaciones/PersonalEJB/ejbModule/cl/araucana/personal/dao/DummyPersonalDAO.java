package cl.araucana.personal.dao;

import java.util.ArrayList;

import cl.araucana.common.BusinessException;
import cl.araucana.personal.vo.CargaVO;
import cl.araucana.personal.vo.EmpleadoVO;

import com.schema.util.InstanceGenerator;
/**
 * @author asepulveda
 *
 */
public class DummyPersonalDAO implements PersonalDAO {
	
	/**
	 * Obtiene la lista de Empleados Activos de La Araucana desde el Sistema de Personal
	 * Permite filtar por Rut y Nombre del Socio
	 * @param String rut, String nombre
	 * @return EmpleadoVO
	 */
	public ArrayList getListaEmpleados(String rut, String nombre) throws Exception,BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(EmpleadoVO.class);
			
	}
	
	 /**
	 * Obtiene un Empleado de La Araucana desde el Sistema de Personal
	 * @param String rut, Rut del Empleado
	 * @return EmpleadoVO
	 */
	public EmpleadoVO getEmpleado(String rut) throws Exception,BusinessException {

		return (EmpleadoVO)InstanceGenerator.buildCollection(EmpleadoVO.class);
	
	} 


	/**
	 * Obtiene una Carga Familiar desde el Sistema de Beneficios
	 * @param long rut, rut de carga familiar
	 * @return CargaVO
	 */
	public CargaVO getCargaFamiliar(long rut) throws Exception,BusinessException {

		return (CargaVO)InstanceGenerator.buildCollection(CargaVO.class);		
	}
	
	/**
	 * Obtiene las Cargas Familiares de un Empleado desde el Sistema de Beneficios
	 * @param long rut, rut de Empleado
	 * @return ArrayList de CargaVO
	 */
	public ArrayList getListaCargasFamiliaresEmpleado(long rut) throws Exception,BusinessException {

		return (ArrayList)InstanceGenerator.buildCollection(CargaVO.class);		
	}
	
	/**
	 * Obtiene la glosa de una Oficina
	 * @param String codigoOficina, lugar de pago (cod Oficina)
	 * @return  String
	 */
	public String getOficinaEmpleado(String codigoOficina) throws Exception,BusinessException {

		return (String)InstanceGenerator.build(String.class);
				
	}

	/**
	 * Obtiene la glosa de un Departamento
	 * @param String codigoCargo, codigo de Cargo de un funcionario
	 * @return  String 
	 */
	public String getDeptoEmpleadoByCodigoCargo(String codigoCargo) throws Exception,BusinessException {

		return (String)InstanceGenerator.build(String.class);	
	}
	
	/**
	 * Obtiene la glosa de un Departamento
	 * @param String codigoDepartamento, codigo de departamento
	 * @return  String 
	 */
	public String getDeptoEmpleadoByCodigoDepto(String codigoDepartamento) throws Exception,BusinessException {

		return (String)InstanceGenerator.build(String.class);	
	}	

	/**
	 * Traduce codigo de Ciudad
	 * @param String codCiudad
	 * @return String con nombre de Ciudad
	 * @throws Exception
	 * @throws BusinessException
	 */
   public String getCiudad(String codCiudad) throws Exception,BusinessException {

		return (String)InstanceGenerator.build(String.class);
				
   }
   
   /**
	* Traduce codigo de Comuna
	* @param String codComuna
	* @return String con nombre de Comuna
	* @throws Exception
	* @throws BusinessException
	*/
  public String getComuna(String codComuna) throws Exception,BusinessException {

		return (String)InstanceGenerator.build(String.class);
			
  }

}
