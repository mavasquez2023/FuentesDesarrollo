package cl.araucana.autoconsulta.dao.usuarioServicio;

import java.util.Collection;

import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.AccesoServicioVO;
import cl.araucana.common.BusinessException;

public interface IUsuarioServicioDAO {
	/** 
	 * Registra los datos de identificación de un certificado
	 * @param DatosValidacionVO datosValidacion
	 * @return void
	 */
	public boolean insertarServicios(EmpresaACargoVO usuarioEmpresa, Collection servicios)
		throws Exception, BusinessException;
	
	public Collection consultaServicios(EmpresaACargoVO usuarioEmpresa)
	throws Exception, BusinessException;

	public Collection consultaEncargados(long rutEmpresa)
	throws Exception, BusinessException;

	public boolean eliminaServicios(EmpresaACargoVO usuarioEmpresa)
	throws Exception, BusinessException;
	
	public boolean actualizaServicios(EmpresaACargoVO usuarioEmpresa, Collection servicios)
	throws Exception, BusinessException;

	public Collection tieneServicio(EmpresaACargoVO usuarioEmpresa, AccesoServicioVO servicio)
	throws Exception, BusinessException;

	public Collection esEncargado(long rutEmpresa, long rutUsuario)
	throws Exception, BusinessException;
	
	public Collection autenticar(long rutEmpresa, long rutUsuario, int clave)
	throws Exception, BusinessException;
	
	public Collection autenticar(long rutEmpresa, long rutUsuario, int clave, int servicio)
	throws Exception, BusinessException;

}
