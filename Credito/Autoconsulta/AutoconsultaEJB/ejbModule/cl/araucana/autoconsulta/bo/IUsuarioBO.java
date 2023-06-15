package cl.araucana.autoconsulta.bo;

import java.util.Collection;

import cl.araucana.autoconsulta.vo.AccesoServicioVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.common.BusinessException;

public interface IUsuarioBO {
	
	public Collection consultaServicios(EmpresaACargoVO usuarioEmpresa) throws Exception, BusinessException;
	public Collection consultaEncargados(long rutEmpresa) throws Exception, BusinessException;
	public Collection tieneServicio(EmpresaACargoVO usuarioEmpresa, AccesoServicioVO servicio) throws Exception, BusinessException;
	public EmpresaACargoVO esEncargado(long rutEmpresa, long rutUsuario) throws Exception, BusinessException;
	public EmpresaACargoVO autenticar(long rutEmpresa, long rutUsuario, int clave) throws Exception, BusinessException;
	public EmpresaACargoVO autenticar(long rutEmpresa, long rutUsuario, int clave, int servicio) throws Exception, BusinessException;
	
	public boolean insertarServicios(EmpresaACargoVO usuarioEmpresa, Collection servicio) throws Exception, BusinessException;
	public boolean eliminaServicios(EmpresaACargoVO usuarioEmpresa) throws Exception, BusinessException;
	public boolean actualizaServicios(EmpresaACargoVO usuarioEmpresa, Collection servicio) throws Exception, BusinessException;
	
}
