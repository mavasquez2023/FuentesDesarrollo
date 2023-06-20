/**
 * 
 */
package cl.araucana.ldap.servlet;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

import cl.araucana.core.registry.User;
import cl.araucana.core.util.Rut;
import cl.araucana.ldap.api.ProxyLDAP;
import cl.araucana.ldap.ws.ClienteInfoAfiliado;
import cl.araucana.ldap.ws.vo.SalidainfoAfiliadoVO;


/**
 * @author usist24
 *
 */
public class Iniciar extends HttpServlet {
	private static Logger log = Logger.getLogger(Iniciar.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String forward= "ldap.jsp";
			Principal principal = request.getUserPrincipal();
			String rol="";
			//String rol="Agente";
			//session.setAttribute("rutuser", "15419640-4");
			if (principal != null && principal.getName() != null) {
				String username= principal.getName();
				log.info("RUT: " + username + " ha iniciado sesión:");
				
				//User user= ProxyLDAP.getUser(username);
				ClienteInfoAfiliado clientInfo = new ClienteInfoAfiliado();
				SalidainfoAfiliadoVO salidaInfo = (SalidainfoAfiliadoVO) clientInfo.getDataAfiliado(username);
				
				log.info("Ingresando IntegradorLDAP usuario: " + salidaInfo.getNombreCompleto());
				session.setAttribute("user", salidaInfo.getNombreCompleto());
				
				//se formatea rut de username ya que lista usuarios en roles viene así.
				/*Rut rut= new Rut(username.substring(0, username.indexOf('-')));
				username= rut.toString();*/
				
				log.info("Username:" + username);
				session.setAttribute("rutuser", username.replaceAll("\\.", ""));
				
				//Se busca rol de usuario
				List listroles= (List)ProxyLDAP.getRolesUserinApp(username, "IntegradorLDAP");
				if(listroles.size()>0){
					rol= listroles.get(0).toString();
				}else{
					rol="Ejecutivo";
				}
				/*List listager= (List)ProxyLDAP.getUsersAppRoles("IntegradorLDAP", "Gerente");
				log.info("Lista Gerentes:" + listager);
				if(listager.contains(username)){
					rol="Gerente";
				}else{
					List listaadm= (List)ProxyLDAP.getUsersAppRoles("IntegradorLDAP", "Administrador");
					log.info("Lista Administradores:" + listaadm);
					if(listaadm.contains(username)){
						rol="Administrador";
					}else{
						List listaage= (List)ProxyLDAP.getUsersAppRoles("IntegradorLDAP", "Agente");
						log.info("Lista Agentes:" + listaage);
						if(listaage.contains(username)){
							rol="Agente";
						}else{
							List listaeje= (List)ProxyLDAP.getUsersAppRoles("IntegradorLDAP", "Ejecutivo");
							log.info("Lista Ejecutivo:" + listaeje);
							if(listaeje.contains(username)){
								rol="Ejecutivo";
							}
						}
					}
					
				}*/
			}
			log.info("Rol usuario: " + rol);
			session.setAttribute("rol", rol);
			session.setAttribute("editar", "-1");
			log.info("Forward a:" + request.getContextPath() + "/" + forward);
			request.getRequestDispatcher("/" + forward).forward(request,response);
		}catch (Exception e) {
				e.printStackTrace();
		}			
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
