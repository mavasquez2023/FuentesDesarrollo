package cl.araucana.cotcarserv.main.actions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.cotcarserv.dao.VO.EmpresaVO;
import cl.araucana.cotcarserv.servlets.EmpresasLDAP;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.araucana.cotcarserv.utils.ProxyLDAP;

public class InitAction extends Action {
	private static Logger logger = Logger.getLogger("InitAction");
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); 
		HttpSession sesion = request.getSession();
		sesion = request.getSession();
		Map<String, String> listamap=null;
		String rolUsuario="";
		//if(sesion.getAttribute("empresas")==null){
			
			Principal principal = request.getUserPrincipal();
			String username= principal.getName();
			
			sesion.setAttribute("usuario", username);
			String name= ProxyLDAP.getUser(username).getName() + " " + ProxyLDAP.getUser(username).getFirstName();
			sesion.setAttribute("nameuser", name);
			
			//Roles
			String app= CertificadoConst.RES_CERTIFICADOS.getString("ldap.cotycar.app");
			String rol_cesacion= CertificadoConst.RES_CERTIFICADOS.getString("ldap.cotycar.rol.usuario");
			String rol_atencion= CertificadoConst.RES_CERTIFICADOS.getString("ldap.cotycar.rol.ejecutivo");
			List listaroles= (List)ProxyLDAP.getUsersAppRoles(app, rol_cesacion);
			if(listaroles.contains(username)){
				rolUsuario=rol_cesacion;
			}
			listaroles= (List)ProxyLDAP.getUsersAppRoles(app, rol_atencion);
			if(listaroles.contains(username)){
				rolUsuario=rol_atencion;
			}
			
			if(rolUsuario.equals("")){
				listamap= EmpresasLDAP.getEmpresasLDAP(username);
				if(listamap.size()>0){
					rolUsuario="Encargado";
				}
				sesion.setAttribute("empresas", listamap);
			}
			logger.info("Rol usuario:" + rolUsuario);
			sesion.setAttribute("rol", rolUsuario);
			
		//}
		forward = mapping.findForward("inicio");
		return forward;
	}

}
