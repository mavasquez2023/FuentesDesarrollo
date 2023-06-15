package cl.araucana.autoconsulta.bo.impl;

import java.util.Collection;

import org.apache.log4j.Logger;

import cl.araucana.autoconsulta.bo.IUsuarioBO;
import cl.araucana.autoconsulta.dao.usuarioServicio.DB2UsuarioServicioDAO;
import cl.araucana.autoconsulta.dao.usuarioServicio.IUsuarioServicioDAO;
import cl.araucana.autoconsulta.vo.AccesoServicioVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

public class UsuarioBO implements IUsuarioBO {

	IUsuarioServicioDAO usDAO = new DB2UsuarioServicioDAO();
	private static Logger logger = Logger.getLogger(UsuarioBO.class);

	public Collection consultaServicios(EmpresaACargoVO usuarioEmpresa) throws Exception, BusinessException {
		return usDAO.consultaServicios(usuarioEmpresa);
	}

	public Collection tieneServicio(EmpresaACargoVO usuarioEmpresa, AccesoServicioVO servicio) throws Exception, BusinessException {
		return usDAO.tieneServicio(usuarioEmpresa, servicio);
	}

	public Collection consultaEncargados(long rutEmpresa) throws Exception, BusinessException {
		return usDAO.consultaEncargados(rutEmpresa);
	}
	
	public EmpresaACargoVO esEncargado(long rutEmoresa, long rutUsuario) throws Exception, BusinessException {
		Collection col = usDAO.esEncargado(rutEmoresa, rutUsuario);
		if (col==null || col.size()==0) {
			return null;
		}
		return (EmpresaACargoVO)col.iterator().next();
	}
	
	public EmpresaACargoVO autenticar(long rutEmoresa, long rutUsuario, int clave) throws Exception, BusinessException {
		Collection col = usDAO.autenticar(rutEmoresa, rutUsuario, clave);
		if (!(col instanceof EmpresaACargoVO) || col.size()==0) {
			return null;
		}
		return (EmpresaACargoVO)col.iterator().next();
	}

	public EmpresaACargoVO autenticar(long rutEmoresa, long rutUsuario, int clave, int servicio) throws Exception, BusinessException {
		Collection col = usDAO.autenticar(rutEmoresa, rutUsuario, clave, servicio);
		
		if (col ==null || col.size()==0) {
			logger.info("autenticar: no encontro usuario [" + rutEmoresa  + "][" + rutUsuario + "][" + servicio + "]");
			return null;
		}
		return (EmpresaACargoVO)col.iterator().next();
	}

	public boolean actualizaServicios(EmpresaACargoVO usuarioEmpresa, Collection servicios) throws Exception, BusinessException {
		return usDAO.actualizaServicios(usuarioEmpresa, servicios);
	}

	public boolean eliminaServicios(EmpresaACargoVO usuarioEmpresa) throws Exception, BusinessException {
		return usDAO.eliminaServicios(usuarioEmpresa);
	}

	public boolean insertarServicios(EmpresaACargoVO usuarioEmpresa, Collection servicios) throws Exception, BusinessException {
		return usDAO.insertarServicios(usuarioEmpresa, servicios);
	}

}
