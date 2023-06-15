package cl.laaraucana.menudinamico.manager;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.ibm.xylem.optimizers.partialeval.OrEvaluator;

import cl.laaraucana.menudinamico.dao.Menu_DAO;
import cl.laaraucana.menudinamico.vo.MenuVO;

public class ManagerAdministracionMenu {

	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Método para obtener listado de todos los Menus existentes en el sistema.
	 * 
	 * @return
	 */
	public ArrayList<MenuVO> getListadoInicial() {
		ArrayList<MenuVO> menuList = null;
		try {
			menuList = Menu_DAO.buscarTodo_Menu();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return menuList;
	}// end getListadoInicial

	/**
	 * Método para obtener listado de todos los Menus existentes en el sistema.
	 * 
	 * @return
	 */
	public ArrayList<MenuVO> getListadoInicialSorted() {
		ArrayList<MenuVO> menuList = null;
		try {
			menuList = Menu_DAO.buscarTodo_MenuSorted();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return menuList;
	}// end getListadoInicial

	/**
	 * Método para obtener los datos de un menu existentes en el sistema.
	 * 
	 * @return
	 */
	public ArrayList<MenuVO> buscarMenu_List(MenuVO menuVO) {
		ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
		MenuVO menuAux = null;
		try {
			// menuList = Menu_DAO.buscarUnico_Menu(menuVO);
			menuList = Menu_DAO.buscarTodo_Menu();
			log.info("* * * * * menuLIST: " + menuList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return menuList;
	}// end buscarMenu

	/**
	 * Método para obtener los datos de un menu, por codigo menu.
	 * 
	 * @return
	 */
	public MenuVO buscarMenu_Cod(long codMenu) {
		MenuVO menuRegis = null;
		try {
			// menuList = Menu_DAO.buscarUnico_Menu(menuVO);
			menuRegis = Menu_DAO.buscarCodMenu(codMenu);
			log.info("* * * * * menuRegis: " + menuRegis);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return menuRegis;
	}// end buscarMenu_Cod

	/**
	 * Método para obtener los datos de un menu, por etiqueta.
	 * 
	 * @return
	 */
	public ArrayList<MenuVO> buscarMenu_Etiqueta(String etiqueta) {
		ArrayList<MenuVO> menuList = null;
		try {
			// menuList = Menu_DAO.buscarUnico_Menu(menuVO);
			menuList = Menu_DAO.buscarEtiquetaMenu(etiqueta);
			log.info("* * * * * menuList: " + menuList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return menuList;
	}// end buscarMenu_Cod

	/**
	 * Método para eliminar Menu. existentes en el sistema. 0 si ocurrio un
	 * problema 1 si el menu no se pudo eliminar 2 si el menu se elimino
	 * correctamente.
	 * 
	 * @return
	 */
	public int eliminarMenu(MenuVO menuVO) {
		int resEstado = 0;
		try {
			if (Menu_DAO.eliminar_Menu(menuVO) > 0) {
				resEstado = 2;
			} else {
				resEstado = 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resEstado;
	}// end eliminarMenu;

	public int eliminarMenus(ArrayList<MenuVO> lista) {
		int resEstado = 0;
		try {
			if (Menu_DAO.eliminarMenus(lista) > 0) {
				resEstado = 2;
			} else {
				resEstado = 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resEstado;
	}// end eliminarMenu;
	
	
	ArrayList<MenuVO> res = new ArrayList<MenuVO>();
	int i = -1;

	public ArrayList<MenuVO> obtenerTodosLosHijos(long codigo) {
		i++;
		try {
			ArrayList<MenuVO> list = Menu_DAO.getTodosHijos(codigo);
			res.addAll(list);
			while (i < res.size()) {
				if (res.get(i).getFlgHoja().equals("0")) {
					obtenerTodosLosHijos(res.get(i).getCodMenu());
				}
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}// end obtenerTodosLosHijos;

	public ArrayList<MenuVO> obtenerHijosDirectos(long codigo) {
		ArrayList<MenuVO> list = new ArrayList<MenuVO>();
		try {
			list = Menu_DAO.getTodosHijos(codigo);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}// end obtenerHijosDirectos;
	
	/**
	 * Método para actualizar Menu. existentes en el sistema. responde con: 0 si
	 * ocurrio un problema 1 si el menu no existe. 2 si el menu se actualizo
	 * correctamente.
	 * 
	 * @return
	 */
	public int actualizarMenu(MenuVO menuVO) {
		int resEstado = 0;
		try {
			if (this.existeMenu(menuVO)) {
				// existe.
				if (Menu_DAO.actualizar_Menu(menuVO) > 0) {
					resEstado = 2;
				}
			} else {
				// no existe.
				resEstado = 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resEstado;
	}// end actualizarMenu

	/**
	 * Método para insertar Menu. responde con: 0 si ocurrio un problema
	 * inesperado. 1 si el menu ya existe 2 si el menu se inserto correctamente.
	 * 
	 * @return
	 */
	public int insertarMenu(MenuVO menuVO) {
		int resEstado = 0;
		try {
			String res = Menu_DAO.insertar_Menu(menuVO);
			if (res.equalsIgnoreCase("ok")) {
				resEstado = 2;
			} else if (res.indexOf("SQL0803") > -1) {
				resEstado = 1;
			}
		} catch (Exception ex) {
			resEstado = 0;
			ex.printStackTrace();
		}
		log.info("* * * * * resEstado: " + resEstado);
		return resEstado;
	}// end insertarMenu

	/**
	 * Método para consultar existencia de Menu responde con: true, si existe
	 * false, si no exciste.
	 * 
	 * @return
	 */
	private boolean existeMenu(MenuVO menuVO) {
		boolean key = false;
		MenuVO menuAux = null;
		menuAux = this.buscarMenu_Cod(menuVO.getCodMenu());
		if (menuAux != null) {
			key = true;
		}
		return key;
	}// end existeMenu

	public long getCodigoMenu() {
		long cod = 0;
		try {
			cod = Menu_DAO.getCodMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cod != 0) {
			cod++;
		}

		return cod;

	}// end existeMenu

}// end class
