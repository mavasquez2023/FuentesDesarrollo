package cl.araucana.wsatento.business.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cl.araucana.wsatento.business.dao.UsuarioDao;
import cl.araucana.wsatento.business.dao.WebServicesDao;
import cl.araucana.wsatento.business.dao.impl.UsuarioDaoImpl;
import cl.araucana.wsatento.business.dao.impl.WebServicesDaoImpl;
import cl.araucana.wsatento.business.service.SeguridadService;
import cl.araucana.wsatento.business.to.ListaUsuariosTO;
import cl.araucana.wsatento.business.to.UsuarioTO;
import cl.araucana.wsatento.business.to.WebServicesTO;
import cl.araucana.wsatento.common.util.ConfigUtil;
import cl.araucana.wsatento.common.util.EstadoUtil;
import cl.araucana.wsatento.common.util.FechaUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class SeguridadServiceImpl implements SeguridadService{
	private static ListaUsuariosTO usuarios;
	private static Integer horasRecarga;

	private static final String KEY_TIME_RELOAD = "araucana.wsatento.seguridad.horasrecarga";
	
	public SeguridadServiceImpl() throws WSAtentoException{
		if(horasRecarga == null){
			String horas = ConfigUtil.getValor(KEY_TIME_RELOAD);
			if(horas != null){
				horasRecarga = new Integer(horas);
			}else{
				horasRecarga = new Integer(6);
			}
		}
		
		if(usuarios != null){
			Integer horas = FechaUtil.diferenciasDeFechasEnHoras(usuarios.getUltimaActualizacion(), new Date());
			if(horas.intValue() > horasRecarga.intValue()){
				SeguridadServiceImpl.recargarUsuarios();
			}
		}else{
			SeguridadServiceImpl.cargarUsuarios();
		}
	}
	
	private static synchronized void cargarUsuarios() throws WSAtentoException{
		//if(usuarios == null){
			usuarios = new ListaUsuariosTO(new Date());
			
			UsuarioDao usuDao = new UsuarioDaoImpl();
			List listUsu = usuDao.getUsuarios();
			for(Iterator it = listUsu.iterator(); it.hasNext(); ){
				UsuarioTO objUsu = (UsuarioTO)it.next();
				
				WebServicesDao wsDao = new WebServicesDaoImpl();
				List listWS = wsDao.getWebServicesByUsuario(objUsu.getUId());
				objUsu.setListWebServices(listWS);
				
				System.out.println("USUARIO: " + objUsu.getUsuario() + ", Nombre: " + objUsu.getNombre());
				usuarios.add(objUsu);
			}
			
		//}
	}
	
	private static synchronized void recargarUsuarios() throws WSAtentoException{
		Integer horas = FechaUtil.diferenciasDeFechasEnHoras(usuarios.getUltimaActualizacion(), new Date());
		if(horas.intValue() > 1){
			usuarios.clear();
			UsuarioDao usuDao = new UsuarioDaoImpl();
			List listUsu = usuDao.getUsuarios();
			for(Iterator it = listUsu.iterator(); it.hasNext(); ){
				UsuarioTO objUsu = (UsuarioTO)it.next();
				
				WebServicesDao wsDao = new WebServicesDaoImpl();
				List listWS = wsDao.getWebServicesByUsuario(objUsu.getUId());
				objUsu.setListWebServices(listWS);
				System.out.println("USUARIO: " + objUsu.getUsuario() + ", Nombre: " + objUsu.getNombre());
				usuarios.add(objUsu);
			}
			usuarios.setUltimaActualizacion(new Date());
		}
	}
	
	public void login(String usuario, String clave, String nomWS) throws WSAtentoException{
		
		if(usuario == null || usuario.equals(""))
			throw new WSAtentoException("0011","Usuario en blanco.");
		if(clave == null || clave.equals(""))
			throw new WSAtentoException("0012","Clave en blanco.");
		
		UsuarioTO user = new UsuarioTO(usuario, clave);
		if(!authentication(user)){
			throw new WSAtentoException("0031","Usuario no existe o clave incorrecta.");
		}
		
		if(!validateAccess(user, nomWS)){
			throw new WSAtentoException("0032","Usuario no tiene permisos para este servicio web.");
		}
	}
	
	public boolean authentication(UsuarioTO user) {
		if(usuarios != null){
			for(Iterator it = usuarios.iterator(); it.hasNext();){
				UsuarioTO objUser = (UsuarioTO)it.next();
				if(objUser.getUsuario() != null && objUser.getUsuario().equals(user.getUsuario()) 
						&& objUser.getClave() != null && objUser.getClave().equals(user.getClave())
						&& objUser.getEstado().equals(EstadoUtil.ACTIVO)){
					user.setListWebServices(objUser.getListWebServices());
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean validateAccess(UsuarioTO user, String nomWS) {
		
		List listWS = user.getListWebServices();
		for(Iterator it = listWS.iterator(); it.hasNext();){
			WebServicesTO objWS = (WebServicesTO)it.next();
			if(objWS.getNombre() != null && objWS.getNombre().equals(nomWS) && objWS.getEstado().equals(EstadoUtil.ACTIVO)){
				return true;
			}
		}
		return false;
	}
}
