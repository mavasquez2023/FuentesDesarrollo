package cl.laaraucana.menudinamico.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.menudinamico.forms.UsuarioMenu_Form;
import cl.laaraucana.menudinamico.manager.ManagerAdministracionMenu;
import cl.laaraucana.menudinamico.manager.ManagerAdministracionUsuario;
import cl.laaraucana.menudinamico.manager.Manager_Adm_MenuUsuario;
import cl.laaraucana.menudinamico.util.menuArbol.ElementoMenu;
import cl.laaraucana.menudinamico.util.menuArbol.ManipuladorMenuUsuario;
import cl.laaraucana.menudinamico.vo.MenuPorUsuarioVO;
import cl.laaraucana.menudinamico.vo.MenuVO;
import cl.laaraucana.menudinamico.vo.UsuarioVO;
import cl.laaraucana.menudinamico.vo.adm_MenuPorUsuarioVO;


/**
 * Clase Actions Struts para manejo de mantenedor de administración de ítems de
 * menú.
 * 
 * @version 1.0
 * @author
 */
public class Adm_MenuUsuario_Action extends DispatchAction {

	private Logger log = Logger.getLogger(this.getClass());
	private String msgListNull = " ";

	/**
	 * Método 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goAdministracionUSRMEN(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a buscarMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		UsuarioMenu_Form forms = (UsuarioMenu_Form)form;
		String msg = "";
		String msgItemListNull = null;
		String msgUserListNull = null;
		try {
			long nodoPadre = forms.getNodoPadre();
			
			boolean key=true;
			//Obtención listado de usuarios existentes en el sistema.
			ArrayList<MenuVO> admMenuList = new ArrayList<MenuVO>();
			ArrayList<UsuarioVO> admUserList = new ArrayList<UsuarioVO>();
			ArrayList<MenuVO> menuUsrList = new ArrayList<MenuVO>();
			ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();
			ManagerAdministracionUsuario mgrUser= new ManagerAdministracionUsuario();
			
			menuUsrList = mgrMenu.getListadoInicialSorted();
			
			if(menuUsrList.size()==0){
				msgItemListNull = "No se ha encontrado ningún dato para usuarios válidos en el" +
					"sistema.";
			}
			
			admUserList = mgrUser.getListadoInicial();
			
			if(admUserList.size()==0){
				msgUserListNull = "No se ha encontrado ningún dato para ítems de menú en el" +
					"sistema.";
			}

			request.setAttribute("nodoPadre", nodoPadre);
			request.setAttribute("admMenuList", menuUsrList);
			request.setAttribute("admUserList", admUserList);
			request.setAttribute("msgItemListNull", msgItemListNull);
			request.setAttribute("msgUserListNull", msgUserListNull);
			
			ManipuladorMenuUsuario manipulador = new ManipuladorMenuUsuario(menuUsrList);
			
			long padre =0;
			manipulador.set_menuSecundario(padre,"#");
			
			ArrayList<String> elementosMenu = new ArrayList<String>();
			for (int i = 0; i < manipulador.get_menuSecundario().size(); i++) {
				ElementoMenu elem = (ElementoMenu) manipulador.get_menuSecundario().get(i);
				elementosMenu.add(elem.display());
			}
			
			request.setAttribute("arbolMenu", elementosMenu);
			
			forward = mapping.findForward("cargarListado_UsuarioMenu");

		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: Cargar listado de menu";
			// forward = mapping.findForward("error");
			request.setAttribute("msg", msg);
			forward = mapping.findForward("cargarListado_UsuarioMenu");
			log.error("Error, actions goAdminstracionMenu : \n "
					+ ex.getMessage());
			ex.printStackTrace();
		}

		// Finish with
		return (forward);
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarListado_UsuarioMenu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a buscarMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {
			// Obtención listado de usuarios existentes en el sistema.
			ArrayList<MenuVO> admMenuList = new ArrayList<MenuVO>();
			ArrayList<UsuarioVO> admUserList = new ArrayList<UsuarioVO>();
			ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
			ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();
			ManagerAdministracionUsuario mgrUser = new ManagerAdministracionUsuario();

			admMenuList = mgrMenu.getListadoInicial();
			admUserList = mgrUser.getListadoInicial();

			request.setAttribute("admMenuList", admMenuList);
			request.setAttribute("admUserList", admUserList);
			request.setAttribute("msg", msg);
			forward = mapping.findForward("cargarListado_UsuarioMenu");

		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: Cargar listado de menu";
			// forward = mapping.findForward("error");
			request.setAttribute("msg", msg);
			forward = mapping.findForward("cargarListado_UsuarioMenu");
			log.error("Error, actions goAdminstracionMenu : \n "
					+ ex.getMessage());
			ex.printStackTrace();
		}

		// Finish with
		return (forward);
	}// end cargarListado_Menu

	public ActionForward listar_MenuPorUsuario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a listar_MenuPorUsuario. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {
			// Obtención listado de usuarios existentes en el sistema.
			ArrayList<adm_MenuPorUsuarioVO> menuPorUsuarioList = new ArrayList<adm_MenuPorUsuarioVO>();
			if (form != null) {
				UsuarioMenu_Form umForm = (UsuarioMenu_Form) form;
				log.info(" umForm.getRut_user().trim(): "
						+ umForm.getRut_user().trim());
				Manager_Adm_MenuUsuario managerMxU = new Manager_Adm_MenuUsuario();
				menuPorUsuarioList = managerMxU.getAdmMenuPorUsuario(umForm.getRut_user().trim());
				log.info("size " + menuPorUsuarioList.size());
			}

			request.setAttribute("menuPorUsuarioList", menuPorUsuarioList);
			
			String arrayJS = "";
			for (int i = 0; i < menuPorUsuarioList.size(); i++) {
				arrayJS+=menuPorUsuarioList.get(i).getCodMenu();
				arrayJS+=(menuPorUsuarioList.size()-1>i)? ",":"";
			}
			request.setAttribute("arrayJS", arrayJS);
			
			request.setAttribute("msg", msg);
			forward = mapping.findForward("cargarListado_menuPorUsuario");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: Cargar listado de menu por usuarios";
			// forward = mapping.findForward("error");
			request.setAttribute("msg", msg);
			forward = mapping.findForward("cargarListado_menuPorUsuario");
			log.error("Error, actions listar_MenuPorUsuario : \n ",ex);
		}

		// Finish with
		return (forward);
	}// end cargarListado_Menu

	public ActionForward asignar_MenuPorUsuario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a listar_MenuPorUsuario. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		int res = 0;
		try {
			// Obtención listado de usuarios existentes en el sistema.
			ArrayList<adm_MenuPorUsuarioVO> menuPorUsuarioList = new ArrayList<adm_MenuPorUsuarioVO>();
			if (form != null) {
				UsuarioMenu_Form umForm = (UsuarioMenu_Form) form;
				String asignacion[] = umForm.getConcat().split("#");

				log.info(" getConcat(): " + umForm.getConcat().trim());
				log.info(" getRut_user(): " + asignacion[0]);

				Manager_Adm_MenuUsuario managerMxU = new Manager_Adm_MenuUsuario();
				res = managerMxU.asignar_MenuPorUsuario(umForm.getConcat());
				menuPorUsuarioList = managerMxU.getAdmMenuPorUsuario(asignacion[0]);
				log.info("size " + menuPorUsuarioList.size());
			}
			// 0: ocurrio un problema
			// 1: se asignaron todos los menu.
			// 2: no se asignaron todos los menu, puede que alguno ya exista
			// asignado.

			if (res == 0) {
				msg = "Ocurrió un problema al momento de la asignación";
			}
			if (res == 1) {
				msg = "Se asignaron correctamente los menú seleccionados";
			}
			if (res == 2) {
				msg = "Se re-asignaron los menu seleccionados";
			}

			request.setAttribute("menuPorUsuarioList", menuPorUsuarioList);
			request.setAttribute("msg", msg);
			forward = mapping.findForward("asignar_menuPorUsuario");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: Cargar asignar menu a usuario";
			// forward = mapping.findForward("error");
			request.setAttribute("msg", msg);
			forward = mapping.findForward("asignar_menuPorUsuario");
			log.error("Error, actions asignar_menuPorUsuario : \n "
					+ ex.getMessage());
		}

		// Finish with
		return (forward);
	}// end asignar_MenuPorUsuario

	public ActionForward quitar_MenuPorUsuario(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a listar_MenuPorUsuario. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {
			// Obtención listado de usuarios existentes en el sistema.
			ArrayList<adm_MenuPorUsuarioVO> menuPorUsuarioList = new ArrayList<adm_MenuPorUsuarioVO>();
			if (form != null) {
				UsuarioMenu_Form umForm = (UsuarioMenu_Form) form;
				String asignacion[] = umForm.getConcat().split("#");

				log.info(" getConcat(): " + umForm.getConcat().trim());
				log.info(" getRut_user(): " + asignacion[0]);

				Manager_Adm_MenuUsuario managerMxU = new Manager_Adm_MenuUsuario();
				// quitar menu asignados.
				managerMxU.quitar_MenuPorUsuario(umForm.getConcat());
				// recargar lista.
				menuPorUsuarioList = managerMxU.getAdmMenuPorUsuario(asignacion[0]);
				log.info("size " + menuPorUsuarioList.size());
			}

			request.setAttribute("menuPorUsuarioList", menuPorUsuarioList);
			request.setAttribute("msg", msg);
			forward = mapping.findForward("quitar_MenuPorUsuario");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: Quitar menu a usuario";
			// forward = mapping.findForward("error");
			request.setAttribute("msg", msg);
			forward = mapping.findForward("quitar_MenuPorUsuario");
			log.error("Error, actions quitar_MenuPorUsuario : \n "
					+ ex.getMessage());
		}

		// Finish with
		return (forward);
	}// end quitar_MenuPorUsuario

}// end class
