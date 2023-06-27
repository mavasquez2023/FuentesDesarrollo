package cl.laaraucana.capaservicios.database.dao;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.capaservicios.database.vo.DatosClienteVO;
import cl.laaraucana.capaservicios.util.RutUtil;
import cl.laaraucana.capaservicios.util.Utils;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ClienteVO;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.EmpresaVO;

public class DatosClienteDAO extends DaoParent{
	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * Obtiene datos personales y lista de empresas a las que se encuentra asociado desde el modelo de datos corporativo
	 * @param rut
	 * @return
	 * @throws Exception
	 */
	public DatosClienteVO getDatosClienteCorp(String rut) throws Exception{
		SqlMapClient sqlMap = getConn();
		DatosClienteVO datos = null;
		ClienteVO clienteVO = null;
		try {
			datos = new DatosClienteVO(RutUtil.getLongRut(rut), RutUtil.getDv(rut));
			clienteVO = (ClienteVO) sqlMap.queryForObject("getDatosClienteCorp", datos);
			//Formatear campos
			if(clienteVO != null){
				if(!clienteVO.getFechaNac().trim().isEmpty())clienteVO.setFechaNac(Utils.pasaFechaSAPaWEB(clienteVO.getFechaNac()));
				clienteVO.setApellidoMaterno(Utils.toCamelCase(clienteVO.getApellidoMaterno()));
				clienteVO.setApellidoPaterno(Utils.toCamelCase(clienteVO.getApellidoPaterno()));
				clienteVO.setNombres(Utils.toCamelCase(clienteVO.getNombres()));
				if(clienteVO.getEmail()==null || clienteVO.getEmail().isEmpty()) clienteVO.setEmail(" ");
				if(clienteVO.getNroDpto()==null || clienteVO.getNroDpto().trim().isEmpty()) clienteVO.setNroDpto(" ");
			}
			datos.setCliente(clienteVO);
		} catch (Exception e) {
			logger.error("Error al obtener los datos del cliente", e);
			throw new Exception(e.getMessage());
		}
		return datos;
	}
	
	/**
	 * Obtiene datos personales y lista de empresas a las que se encuentra asociado desde el modelo de datos corporativo
	 * @param rut
	 * @return
	 * @throws Exception
	 */
	public DatosClienteVO getDatosCliente(String rut) throws Exception{
		SqlMapClient sqlMap = getConn();
		DatosClienteVO datos = null;
		ClienteVO clienteVO = null;
		try {
			datos = new DatosClienteVO(RutUtil.getLongRut(rut), RutUtil.getDv(rut));
			clienteVO = (ClienteVO) sqlMap.queryForObject("getDatosCliente", datos);
			
			//Convertir fecha a formato web
			if(clienteVO != null){
				if(!clienteVO.getFechaNac().trim().isEmpty())clienteVO.setFechaNac(Utils.pasaFechaSAPaWEB(clienteVO.getFechaNac()));
				if(clienteVO.getEmail()==null || clienteVO.getEmail().isEmpty()) clienteVO.setEmail(" ");
				if(clienteVO.getNroDpto()==null || clienteVO.getNroDpto().trim().isEmpty()) clienteVO.setNroDpto(" ");
			}
			datos.setCliente(clienteVO);
		} catch (Exception e) {
			logger.error("Error al consultar datos cliente:", e);
			throw new Exception(e.getMessage());
		}
		return datos;
	}
	
	/**
	 * Obtiene datos de empresa desde modelo de datos crédito especial AS400.
	 * @param rut
	 * @return
	 * @throws Si ocurre una excepción, se captura (no se mostrará nombre de empresa en la web)
	 */
	public EmpresaVO getDatosEmpresa(String rut){
		DatosClienteVO datos = null;
		EmpresaVO emp = null;
		try {
			SqlMapClient sqlMap = getConn();
			datos = new DatosClienteVO(RutUtil.getLongRut(rut), RutUtil.getDv(rut));
			emp = (EmpresaVO) sqlMap.queryForObject("getDatosEmpresa", datos);
		} catch (Exception e) {
			logger.error("Error al consultar datos empresa:", e);
			//throw new Exception(e.getMessage());
		}
		return emp;
	}
	
	
	/**
	 * Actualiza datos de cliente en modelo de crédito especial
	 * @param cliente
	 * @return
	 * @throws Exception 
	 */
	public boolean actualizarDatosCliente(DatosClienteVO cliente) throws Exception{
		SqlMapClient sqlMap = getConn();
		try {
			int res =  sqlMap.update("actualizarDatosCliente", cliente);
			return (res >0) ? true : false;
		} catch (Exception e) {
			logger.error("Error al actualizar datos cliente:", e);
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Ingresar datos de cliente en modelo de crédito especial, esto se realiza cuando no existen datos del cliente
	 * en el modelo de la aplicación
	 * @param cliente
	 * @return
	 * @throws Exception 
	 */
	public boolean ingresarDatosCliente(DatosClienteVO cliente) throws Exception{
		SqlMapClient sqlMap = getConn();
		boolean res = false;
		try {
			sqlMap.insert("ingresarDatosCliente", cliente);
			res = true;
		} catch (Exception e) {
			logger.error("Error al ingresar datos cliente:", e);
			throw new Exception(e.getMessage());
		}
		return res;
	}
}
