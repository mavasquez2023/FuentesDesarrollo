package cl.laaraucana.menudinamico.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.laaraucana.menudinamico.forms.LoginForm;
import cl.laaraucana.menudinamico.manager.ManagerMenu;
import cl.laaraucana.menudinamico.vo.MenuPorUsuarioVO;

/**
 * @version 1.0
 * @author
 */
public class LoginAction extends Action {

	private Logger log = Logger.getLogger(this.getClass());
	private String msgListNull = null;
	private String mensaje = "";

	/**
	 * Actions de validación de usuario.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception 
	{
		log.info(" ---- Ingreso a LoginAction.execute ---- ");
		
		LoginForm forms = (LoginForm)form;
		ActionForward forward = new ActionForward(); // return value
		try {
			HttpSession sesion = request.getSession(true);
			String userID = (String)sesion.getAttribute("login");
			
			
			ArrayList<MenuPorUsuarioVO> lista = new ArrayList<MenuPorUsuarioVO>();
			ArrayList<MenuPorUsuarioVO> list = new ArrayList<MenuPorUsuarioVO>();
			
			String flgHoja = "0";
			long codMenu = 0;
			
			//Cargando opciones de menú en interfaz menuPrincipal.
			if(sesion.getAttribute("menuUserList")==null){
				System.out.println(" --- menuUserList con usuario : "+userID+" --- ");
				ManagerMenu manager = new ManagerMenu();
				
				list = manager.getMenu(userID);
				if(list.size()==0){
					msgListNull = "No se ha encontrado ningún dato de ítems de menú "
							+ "en el sistema.";
				}
				else{
					
					codMenu = 0;
					
					//Nivel = 1 en adelante.
					if(flgHoja.equalsIgnoreCase("0")){
						for(int i = 0; i < list.size(); i++){
							if(codMenu==list.get(i).getNodoPadre()){
								lista.add(list.get(i));
							}
						}	
					}
				}
				
				System.out.println("Lista size = " + lista.size());
				
				sesion.setAttribute("mensaje", mensaje);
				sesion.setAttribute("msgListNull", msgListNull);
				sesion.setAttribute("menuLista", list);
				sesion.setAttribute("lista", lista);
				sesion.setAttribute("flgHoja", flgHoja);
			}
			
			request.setAttribute("msgListNull", msgListNull);
			request.setAttribute("lista", lista);
			forward = mapping.findForward("successLogin");
		} catch (Exception ex) {
			mensaje = "Error, LoginAction.execute : \n " + ex.getMessage();
			log.error("Error, LoginAction.execute : \n " + ex.getMessage());
			ex.printStackTrace();
		}
		// Finish with
		return (forward);
	}
	
	
}
