package cl.laaraucana.menudinamico.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.menudinamico.forms.Menu_Form;
import cl.laaraucana.menudinamico.manager.ManagerAdministracionMenu;
import cl.laaraucana.menudinamico.manager.Manager_Adm_MenuUsuario;
import cl.laaraucana.menudinamico.util.menuArbol.Constantes;
import cl.laaraucana.menudinamico.util.menuArbol.ElementoMenu;
import cl.laaraucana.menudinamico.util.menuArbol.ManipuladorMenuUsuario;
import cl.laaraucana.menudinamico.vo.MenuVO;

/**
 * Clase Actions Struts para manejo de mantenedor de administración de ítems de
 * menú.
 * 
 * @version 1.0
 * @author
 */
public class AdministracionMenuAction extends DispatchAction {
	private Logger log = Logger.getLogger(this.getClass());
	private String msgListNull = " ";

	ManagerAdministracionMenu manager = new ManagerAdministracionMenu();

	/**
	 * Método para cargar interfaz de administración de menú.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goAdministracionMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a admMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		Menu_Form forms = (Menu_Form) form;
		ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
		String msg = "";
		String msgItemListNull = null;
		try {
			long nodoPadre = forms.getNodoPadre();

			boolean key = true;

			// Cargar listas de pantalla administración de ítems menú.
			menuList = manager.getListadoInicial();

			if (menuList.size() == 0) {
				msgItemListNull = "No se han encontrado datos de usuarios válidos en el sistema.";
				log.info(msgItemListNull);
			}
			//obtiene lista ordenada
			menuList = manager.getListadoInicialSorted();

			//genera el arbol
			ManipuladorMenuUsuario manipulador = new ManipuladorMenuUsuario(menuList);
			long padre = 0;
			manipulador.set_menuSecundario(padre, "#");

			ArrayList<String> elementosMenu = new ArrayList<String>();
			for (int i = 0; i < manipulador.get_menuSecundario().size(); i++) {
				ElementoMenu elem = (ElementoMenu) manipulador.get_menuSecundario().get(i);
				elementosMenu.add(elem.display2());
			}

			//envia el arbol al jsp
			request.setAttribute("arbolMenu", elementosMenu);
			request.setAttribute("nodoPadre", nodoPadre);
			request.setAttribute("msgListNull", msgItemListNull);
			//request.setAttribute("menuList", menuList);
			forward = mapping.findForward("successAdmMenu");

		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la cargar listado de menú.";
			request.setAttribute("msg", msg);
			forward = mapping.findForward("cargarListado_UsuarioMenu");
			log.error("Error, actions goAdminstracionMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}

		// Finish with
		return (forward);
	}

	/**
	 * Actions para eliminación de menú.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward eliminarMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a eliminarMenus. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {
			boolean key = false;
			if (form != null) {
				Menu_Form mForm = (Menu_Form) form;
				ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();
				MenuVO menuVO = new MenuVO();
				menuVO.setCodMenu(mForm.getCodMenu());

				ArrayList<MenuVO> lista = mgrMenu.obtenerTodosLosHijos(mForm.getCodMenu());
				lista.add(menuVO);

				int res = mgrMenu.eliminarMenus(lista);

				// 0 si ocurrio un problema
				// 1 si el item no se pudo eliminar
				// 2 si el item se elimino correctamente.
				if (res == 0) {
					msg = "Ocurrió un problema al intentar realizar la operación: eliminar menú.";
					key = false;
				}
				if (res == 1) {
					msg = "No se encontro el ítem de menú para poder eliminarlo.";
					key = true;
				}
				if (res == 2) {
					msg = "Se elimino correctamente el menú";
					key = true;
				}
			}

			request.setAttribute("KeyOperacion", false);
			request.setAttribute("msg", msg);
			forward = mapping.findForward("successEliminarMenu");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: eliminar menú.";
			// forward = mapping.findForward("error");
			request.setAttribute("KeyOperacion", false);
			request.setAttribute("msg", msg);
			log.error("Error, actions eliminarMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}
		// Finish with
		return (forward);
	}// end eliminarMenu

	/**
	 * Actions para actualización de menú.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward actualizarMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a actualizarMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		String mensaje = "";
		try {
			boolean key = false;
			if (form != null) {
				Menu_Form mForm = (Menu_Form) form;
				MenuVO menuVO = new MenuVO();
				MenuVO menuOld = new MenuVO();

				menuVO.setCodMenu(mForm.getCodMenu());
				menuVO.setDescripcion(mForm.getDescripcion());
				menuVO.setEnlace(mForm.getEnlace());
				menuVO.setEtiqueta(mForm.getEtiqueta());
				menuVO.setFlgHoja(mForm.getFlgHoja());
				menuVO.setNivel(mForm.getNivel());
				menuVO.setNodoPadre(mForm.getNodoPadre());
				menuVO.setSeguridad(mForm.getSeguridad());
				menuVO.setLinkInterno(mForm.getLinkInterno());

				ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();

				menuOld = mgrMenu.buscarMenu_Cod(menuVO.getCodMenu());

				boolean continuarActualizacion = true;

				if (menuOld.getFlgHoja().equals("0") && menuVO.getFlgHoja().equals("1")) {
					ArrayList<MenuVO> hijosDirectos = mgrMenu.obtenerHijosDirectos(menuVO.getCodMenu());
					if (hijosDirectos.size() > 1) {
						msg = "El campo hoja no se puede actualizar mientras existan ítems asociados.";
						continuarActualizacion = false;
					} else if (hijosDirectos.size() == 1) {
						if (hijosDirectos.get(0).getEtiqueta().toLowerCase().contains("volver")) {
							//delete volver	
							continuarActualizacion = true;
							mgrMenu.eliminarMenu(hijosDirectos.get(0));
						} else {
							msg = "El campo hoja no se puede actualizar mientras existan ítems asociados.";
							continuarActualizacion = false;
						}

					} else if (hijosDirectos.size() == 0) {
						continuarActualizacion = true;
					}
				}
				if (menuVO.getEtiqueta().toLowerCase().contains("volver") && menuVO.getFlgHoja().equals("0")) {
					msg = "El tem volver solo puede ser de tipo hoja";
					continuarActualizacion = false;
				}
				if (continuarActualizacion) {

					int res = mgrMenu.actualizarMenu(menuVO);
					//crea boton volver auomatico
					if (menuOld.getFlgHoja().equals("1") && menuVO.getFlgHoja().equals("0") && menuVO.getLinkInterno().equals("1") && menuVO.getNodoPadre() != 0) {
						log.info("inserta volver automatico");
						String r = crearVolverAutomatico(menuVO);
						log.info(r);
					}
					// 0 si ocurrio un problema
					// 1 si el usuario no se pudo actualizar(no se encontro)
					// 2 si el usuario se actualizo correctamente.
					if (res == 0) {
						msg = "Ocurrió un problema al intentar realizar la operación: actualizar menú";
					}
					if (res == 1) {
						msg = "No se encontro el ítem de menú para poder actualizarlo";
					}
					if (res == 2) {
						msg = "Se actualizo correctamente el menú";
					}
				}
			}

			request.setAttribute("KeyOperacion", false);
			request.setAttribute("msg", msg + mensaje);
			forward = mapping.findForward("successActualizarMenu");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: actualizar menú";
			request.setAttribute("KeyOperacion", false);
			forward = mapping.findForward("successActualizarMenu");
			request.setAttribute("msg", msg);
			// forward = mapping.findForward("error");
			log.error("Error, actions actualizarMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}
		// Finish with
		return (forward);
	}// end actualizarMenu

	/**
	 * Actions para insertar menú.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward insertarMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a insertarMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		String mensaje = "";
		try {
			boolean key = false;
			if (form != null) {
				Menu_Form mForm = (Menu_Form) form;
				MenuVO menuVO = new MenuVO();
				ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();

				long codigoMenu = mgrMenu.getCodigoMenu();

				menuVO.setCodMenu(codigoMenu);
				menuVO.setDescripcion(mForm.getDescripcion());
				menuVO.setEnlace(mForm.getEnlace());
				menuVO.setEtiqueta(mForm.getEtiqueta());
				menuVO.setFlgHoja(mForm.getFlgHoja());
				menuVO.setNivel(mForm.getNivel());
				menuVO.setNodoPadre(mForm.getNodoPadre());
				menuVO.setSeguridad(mForm.getSeguridad());
				menuVO.setLinkInterno(mForm.getLinkInterno());

				log.info("* * * * * insertarMenu :  ");
				/*
				 * log.info("\n* * * * * getDescripcion.: " +
				 * mForm.getDescripcion());
				 * log.info("\n* * * * * getEnlace......: " +
				 * mForm.getEnlace()); log.info("\n* * * * * getEtiqueta....: "
				 * + mForm.getEtiqueta());
				 * log.info("\n* * * * * getFlgHoja.....: " +
				 * mForm.getFlgHoja()); log.info("\n* * * * * getNivel.......: "
				 * + mForm.getNivel()); log.info("\n* * * * * getNodoPadre...: "
				 * + mForm.getNodoPadre());
				 * log.info("\n* * * * * getSeguridad...: " +
				 * mForm.getSeguridad());
				 */
				int res = mgrMenu.insertarMenu(menuVO);

				//crea boton volver auomatico
				if (menuVO.getFlgHoja().equals("0") && menuVO.getLinkInterno().equals("1") && menuVO.getNodoPadre() != 0) {
					log.info("inserta volver automatico");
					String r = crearVolverAutomatico(menuVO);
					log.info(r);
				}

				// 0 si ocurrio un problema
				// 1 si el usuario ya existe
				// 2 si el usuario se inserto correctamente.
				if (res == 0) {
					msg = "Ocurrió un problema al intentar realizar la operación: insertar menú. ";
					// key=false;
				}
				if (res == 1) {
					msg = "El ítem de menú ya existe. ";
					// key=true;
				}
				if (res == 2) {
					msg = "Se inserto correctamente el menú. ";
					// key=true;
				}
			}
			request.setAttribute("KeyOperacion", false);
			request.setAttribute("msg", msg + mensaje);
			forward = mapping.findForward("successInsertarMenu");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: insertar menu";
			// forward = mapping.findForward("error");
			request.setAttribute("KeyOperacion", false);
			forward = mapping.findForward("successInsertarMenu");
			log.error("Error, actions insertarMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}
		// Finish with
		return (forward);
	}// end insertarMenu

	/**
	 * Actions para buscar un ítem de menú.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward buscarMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a buscarMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {
			ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
			MenuVO menuAux=new MenuVO();
			if (form != null) {
				Menu_Form mForm = (Menu_Form) form;
				ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();
				//busca menu y lo ordenado
				menuList = mgrMenu.buscarMenu_Etiqueta(mForm.getEtiqueta());
				
			}
			
			if(menuList!=null){
				int vc=0;
				for(vc=0;vc<menuList.size();vc++){
					menuList.get(vc).setNodoPadre(0);
					menuList.get(vc).setNivel(0);
					menuList.get(vc).setFlgHoja("1");
				}
			}
			
			
				//genera el arbol
				ManipuladorMenuUsuario manipulador = new ManipuladorMenuUsuario(menuList);
				long padre = 0;
				manipulador.set_menuSecundario(padre, "#");
				ArrayList<String> elementosMenu = new ArrayList<String>();
				for (int i = 0; i < manipulador.get_menuSecundario().size(); i++) {
					ElementoMenu elem = (ElementoMenu) manipulador.get_menuSecundario().get(i);
					elementosMenu.add(elem.display2());
				}
				//envia el arbol al jsp
				request.setAttribute("arbolMenu", elementosMenu);
			
			
			request.setAttribute("msg", msg);
			//			request.setAttribute("menuList", menuList);
			forward = mapping.findForward("successCargarListadoMenu");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: buscar un ítem de menú.";
			// forward = mapping.findForward("error");
			forward = mapping.findForward("successCargarListadoMenu");
			log.error("Error, actions buscarMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}
		// Finish with
		return (forward);
	}// end buscarMenu

	public ActionForward buscarMenu_form(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a buscarMenu_form. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {
			MenuVO menuVO = null;
			MenuVO menuAux = null;
			boolean key = false;
			if (form != null) {
				Menu_Form mForm = (Menu_Form) form;
				menuVO = new MenuVO();
				ManagerAdministracionMenu mgrMenu = new ManagerAdministracionMenu();
				ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();

				menuVO.setCodMenu(mForm.getCodMenu());

				// menuVO=mgrMenu.buscarMenu(menuVO);

				menuAux = mgrMenu.buscarMenu_Cod(menuVO.getCodMenu());
				if (menuAux != null) {
					key = true;
				} else {
					msg = "Ocurrió un problema al intentar realizar la operación: buscar un ítem de menú.";
					key = false;
					request.setAttribute("msg", msg);
				}
			}

			request.setAttribute("KeyOperacion", key);
			request.setAttribute("menuRegistro", menuAux);
			forward = mapping.findForward("successBuscarMenu_form");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: insertar menu";
			// forward = mapping.findForward("error");
			request.setAttribute("KeyOperacion", false);
			forward = mapping.findForward("successInsertarMenu");
			log.error("Error, actions insertarMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}
		// Finish with
		return (forward);
	}// end insertarMenu

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cargarListado_Menu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a buscarMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {

			// Obtención listado de usuarios existentes en el sistema.
			ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();

			//obtiene lista ordenada
			menuList = manager.getListadoInicialSorted();
			ManipuladorMenuUsuario manipulador = new ManipuladorMenuUsuario(menuList);

			//se ordena el arbol
			long padre = 0;
			manipulador.set_menuSecundario(padre, "#");
			ArrayList<String> elementosMenu = new ArrayList<String>();
			for (int i = 0; i < manipulador.get_menuSecundario().size(); i++) {
				ElementoMenu elem = (ElementoMenu) manipulador.get_menuSecundario().get(i);
				elementosMenu.add(elem.display2());
			}
			//se envial arbol al jsp
			request.setAttribute("arbolMenu", elementosMenu);
			//request.setAttribute("menuList", menuList);

			forward = mapping.findForward("successCargarListadoMenu");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: Cargar listado de menu";
			// forward = mapping.findForward("error");
			request.setAttribute("msg", msg);
			forward = mapping.findForward("successCargarListadoMenu");
			log.error("Error, actions goAdminstracionMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}

		// Finish with
		return (forward);
	}// end cargarListado_Menu

	/**
	 * Actions para cargar formulario vacio.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mostrarFormMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a mostrarFormMenu. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try {
			// Obtención listado de usuarios existentes en el sistema.
			MenuVO menuRegistro = null;
			// keyOperacion: en true, permite ver el formulario
			// keyOperacion: en false, permite ver el msg
			request.setAttribute("KeyOperacion", true);
			request.setAttribute("menuRegistro", menuRegistro);
			forward = mapping.findForward("successMostrarFormMenu");
		} catch (Exception ex) {
			msg = "Ocurrió un problema al intentar realizar la operación: Abrir Formulario de Menú";
			// forward = mapping.findForward("error");
			request.setAttribute("KeyOperacion", false);
			request.setAttribute("msg", msg);
			forward = mapping.findForward("successMostrarFormMenu");
			log.error("Error, actions mostrarFormMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}

		// Finish with
		return (forward);
	}// end cargarListado_Menu

	private String crearVolverAutomatico(MenuVO menuPadre) throws Exception {
		String mensaje = "";

		long codigoMenuVolver = manager.getCodigoMenu();
		MenuVO menu = new MenuVO();
		menu.setCodMenu(codigoMenuVolver);
		menu.setDescripcion("volver automatico");
		menu.setEnlace(Constantes.getInstancia().URL_MENUDINAMICO + "?op=goMenuPrincipal&codMenu=" + menuPadre.getNodoPadre() + "&flgHoja=0");
		menu.setEtiqueta("Volver");
		menu.setFlgHoja("1");
		menu.setNivel(99);
		menu.setNodoPadre(menuPadre.getCodMenu());
		menu.setSeguridad("0");
		menu.setLinkInterno(menuPadre.getSeguridad());
		int r = manager.insertarMenu(menu);

		if (r == 0 || r == 1) {
			mensaje = " Ocurrió un problema al insertar el boton volver. ";
		}
		Manager_Adm_MenuUsuario mgr = new Manager_Adm_MenuUsuario();
		int i = mgr.asignar_MenuAUsuariosConMenu(menuPadre.getCodMenu(), codigoMenuVolver);

		mensaje = "se ingreso menu volver y se asigno a "+i+" usuarios";
		return mensaje;
	}

}// end class
