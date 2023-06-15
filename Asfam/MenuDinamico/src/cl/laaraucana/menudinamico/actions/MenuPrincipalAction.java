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

import cl.laaraucana.menudinamico.forms.MenuPrincipal_Form;
import cl.laaraucana.menudinamico.manager.ManagerAdministracionMenu;
import cl.laaraucana.menudinamico.manager.ManagerAdministracionUsuario;
import cl.laaraucana.menudinamico.manager.ManagerMenu;
import cl.laaraucana.menudinamico.manager.Manager_Adm_MenuUsuario;
import cl.laaraucana.menudinamico.vo.MenuPorUsuarioVO;
import cl.laaraucana.menudinamico.vo.MenuVO;
import cl.laaraucana.menudinamico.vo.UsuarioVO;

/**
 * Clase para manejo de menú dinámico de aplicativo.
 * 
 * @version 1.0
 * @author
 */
public class MenuPrincipalAction extends DispatchAction {
	private Logger log = Logger.getLogger(this.getClass());
	private String mensaje = null;
	
//	private String msgUserListNull = null;
//	private String msgItemListNull = null;
	private String msgListNull = null;

	
	ManagerAdministracionMenu managerItem = new ManagerAdministracionMenu();
	ManagerAdministracionUsuario managerUsr = new ManagerAdministracionUsuario();
	Manager_Adm_MenuUsuario managerUsrMn = new Manager_Adm_MenuUsuario();
	
	/**
	 * Actions para ingreso desde interfaz principal a interfaz de
	 * administración de menú.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goMenuPrincipal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a goMenuPrincipal. ---- ");
		MenuPrincipal_Form forms = (MenuPrincipal_Form) form;
		ActionForward forward = new ActionForward(); // return value
		boolean key=false;
		try {
			long codMenu = forms.getCodMenu();
			String flgHoja = forms.getFlgHoja();
			
			System.out.println("------ Datos ------");
			System.out.println("codMenu = " + codMenu);
			System.out.println("flgHoja = " + flgHoja);
			//System.out.println("nodoPadre = " + nodoPadre);

			ArrayList<MenuPorUsuarioVO> lista = new ArrayList<MenuPorUsuarioVO>();

			// Obtención listado de usuarios existentes en el sistema.
			HttpSession sesion = request.getSession(true);
			if(sesion!=null){
				//ArrayList<MenuPorUsuarioVO> menuList = (ArrayList<MenuPorUsuarioVO>) sesion.getAttribute("menuLista");
				//recuperacion de id
				
				String userID = (String)sesion.getAttribute("login");
				//recuperacion de menu por id
				ManagerMenu manager = new ManagerMenu();
				ArrayList<MenuPorUsuarioVO> menuList = manager.getMenu(userID);
				
				if(menuList.size()!=0){
					key=true;
					log.info("Tamaño listado de menú existentes en el sistema : " + menuList.size());
		
					// Setea mensaje en caso de que listado sea vacio.
					if (menuList.size() == 0) {
						menuList = new ArrayList<MenuPorUsuarioVO>();
						msgListNull = "No se ha encontrado ningún dato de ítems de menú "
							+ "en el sistema.";
					} else {
						/* Entrada de listado desde LoginActions (1° vez), se carga
						 * lista nivel = 0. */
						if (codMenu == 0) {
							flgHoja = "0";
						}
						
						// Nivel = 1 en adelante.
						for (int i = 0; i < menuList.size(); i++) {
							if (flgHoja.equalsIgnoreCase("0")) {
								
//								log.info("codMenu = " + codMenu +" NodoPadre = " 
//									+ menuList.get(i).getNodoPadre());
								
								if (codMenu == menuList.get(i).getNodoPadre()) {
									lista.add(menuList.get(i));
								}
							}
						}
					}						
				}					
			}else{
				log.info("Error, sesión null.");
			}

			
			if(key){
				log.info("Tamaño listado que se desplegará : " + lista.size());
				
				request.setAttribute("flgHoja", flgHoja);
				request.setAttribute("msgListNull", msgListNull);
				request.setAttribute("lista", lista);
				forward = mapping.findForward("successMenuPrincipal");
			}else{
				log.info("sesion invalida.");
				request.setAttribute("mensaje", "Su sesion a expirado.");
				forward = mapping.findForward("error");
			}
			
		} catch (Exception ex) {
			
			log.error("Error, actions goMenuPrincipal : \n " + ex.getMessage());
			ex.printStackTrace();
			request.setAttribute("mensaje", "Error al intentar cargar los item de menú.");
			forward = mapping.findForward("error");
		}
		// Finish with
		return (forward);
	}

	
	/**
	 * Manejo de opciones de menú que sean links interno de está misma 
	 * aplicación.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goLinkInterno(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a goLinkInterno. ---- ");

		MenuPrincipal_Form forms = (MenuPrincipal_Form) form;
		ActionForward forward = new ActionForward(); // return value
		boolean key=false;
		
			try {
				HttpSession sesion = request.getSession(true);
				String userID = (String)sesion.getAttribute("login");
				
				System.out.println("userID = " + userID);
				
				if(userID.length()!=0){
					
					ArrayList<UsuarioVO> userList = new ArrayList<UsuarioVO>();
					ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
					
					long codMenu = forms.getCodMenu();
					long nivel = forms.getNivel();
					String flgHoja = forms.getFlgHoja();
					String url = forms.getUrl();
					
					if(url.length()!=0){
						System.out.println("Obteniendo actions");
						
					}
				}
				
				if(key==false){
					log.info("Sesión invalida.");
					request.setAttribute("mensaje", "Su sesión a expirado.");
					forward = mapping.findForward("error");
				}
			} catch (Exception ex) {
				log.error("Error, actions goMenuSecundario : \n " + ex.getMessage());
				ex.printStackTrace();
				request.setAttribute("mensaje", "Error al obtener el menú de usuario.");
				forward = mapping.findForward("error");
				
			}
		// Finish with
		return (forward);
	}
}



//try {
//	HttpSession sesion = request.getSession(true);
//	String userID = (String)sesion.getAttribute("login");
//	
//	System.out.println("userID = " + userID);
//	
//	if(userID.length()!=0){
//		
//		ArrayList<UsuarioVO> userList = new ArrayList<UsuarioVO>();
//		ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
//		
//		long codMenu = forms.getCodMenu();
//		long nivel = forms.getNivel();
//		String flgHoja = forms.getFlgHoja();
//		String url = forms.getUrl();
//		
//
//		if(url.indexOf("AdministracionUsuarios.jsp")!=-1){
//			System.out.println("1");
//			key=true;
//			//Cargar listas de pantalla administración de usuario.
//			userList = managerUsr.getListadoInicial();
//			
//			if(userList.size()==0){
//				msgUserListNull = "No se han encontrado datos de usuarios válidos en el sistema.";
//				log.info(msgUserListNull);
//			}
//			
//			request.setAttribute("msgListNull", msgUserListNull);
//			request.setAttribute("userList", userList);
//			forward = mapping.findForward("successAdmUser");
//			
//		}else if(url.indexOf("AdministracionItemMenu.jsp")!=-1){
//			System.out.println("2");
//			key=true;
//			//Cargar listas de pantalla administración de ítems menú.
//			menuList = managerItem.getListadoInicial();
//			
//			if(menuList.size()==0){
//				msgItemListNull = "No se han encontrado datos de usuarios válidos en el sistema.";
//				log.info(msgItemListNull);
//			}
//			request.setAttribute("msgListNull", msgItemListNull);
//			request.setAttribute("menuList", menuList);
//			forward = mapping.findForward("successAdmItemsMenu");
//			
//		}else if(url.indexOf("admMenuPorUsuarios.jsp")!=-1){
//			System.out.println("3");
//			key=true;
//			// Obtención listado de usuarios existentes en el sistema.
//			ArrayList<MenuVO> admMenuList = new ArrayList<MenuVO>();
//			ArrayList<UsuarioVO> admUserList = new ArrayList<UsuarioVO>();
//			ArrayList<MenuVO> menuUsrList = new ArrayList<MenuVO>();
//			ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();
//			ManagerAdministracionUsuario mgrUser= new ManagerAdministracionUsuario();
//			
//			menuUsrList = mgrMenu.getListadoInicial();
//			
//			if(menuUsrList.size()==0){
//				msgItemListNull = "No se ha encontrado ningún dato para usuarios válidos en el" +
//					"sistema.";
//			}
//			
//			admUserList = mgrUser.getListadoInicial();
//			
//			if(admUserList.size()==0){
//				msgUserListNull = "No se ha encontrado ningún dato para ítems de menú en el" +
//					"sistema.";
//			}
//
//			request.setAttribute("admMenuList", menuUsrList);
//			request.setAttribute("admUserList", admUserList);
//			request.setAttribute("msgItemListNull", msgItemListNull);
//			request.setAttribute("msgUserListNull", msgUserListNull);
//			
//			forward = mapping.findForward("cargarListado_UsuarioMenu");
//			
//		}else{
//			key=true;
//			log.info("Otra opción : CodMenu = ["+codMenu+"] - FlgHoja = ["+flgHoja+"] - URL ["+url+"]");
//			
//			//Recuperación de menu por id.
//			ManagerMenu manager = new ManagerMenu();
//			ArrayList<MenuPorUsuarioVO> listMenu = manager.getMenu(userID);
//			
//			ArrayList<MenuPorUsuarioVO> lista = new ArrayList<MenuPorUsuarioVO>();
//			
//			if(listMenu!=null){
//				key=true;
//				log.info("Tamaño listado de menú existentes en el sistema : " + menuList.size());
//	
//				//Setea mensaje en caso de que listado sea vacio.
//				if (listMenu.size() == 0) {
//					listMenu = new ArrayList<MenuPorUsuarioVO>();
//					msgListNull = "No se ha encontrado ningún dato de ítems de menú "
//						+ "en el sistema.";
//				} else {
//					
//					//Nivel = 1 en adelante.
//					for (int i = 0; i < listMenu.size(); i++) {
//						if (flgHoja.equalsIgnoreCase("0")) {
//							
//							if (codMenu == listMenu.get(i).getNodoPadre()) {
//								lista.add(listMenu.get(i));
//							}
//						}
//					}
//				}						
//			}
//			if(key){
//				log.info("Tamaño listado que se desplegará : " + lista.size());
//				
//				request.setAttribute("flgHoja", flgHoja);
//				request.setAttribute("msgListNull", msgListNull);
//				request.setAttribute("lista", lista);
//				forward = mapping.findForward("successMenuPrincipal");
//			}
//		}	
//	}else{
//		key=false;
//		log.info("Opción errónea, goLinkInterno.");
//		request.setAttribute("mensaje", "Se ha seleccionado una opción no válida.");
//		forward = mapping.findForward("error");
//	}
//	
//	if(key==false){
//		log.info("Sesión invalida.");
//		request.setAttribute("mensaje", "Su sesión a expirado.");
//		forward = mapping.findForward("error");
//	}
//}
