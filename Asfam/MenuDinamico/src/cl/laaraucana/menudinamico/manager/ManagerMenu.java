package cl.laaraucana.menudinamico.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.laaraucana.menudinamico.dao.MenuPorUsario_DAO;
import cl.laaraucana.menudinamico.vo.MenuPorUsuarioVO;

public class ManagerMenu {
	
	private Logger log = Logger.getLogger(this.getClass());

	public ArrayList<MenuPorUsuarioVO> getMenu(String rut_user){
		MenuPorUsuarioVO menu = new MenuPorUsuarioVO();
		try{
			//Nuevo listado de datos de menú.
			ArrayList<MenuPorUsuarioVO> list = new ArrayList<MenuPorUsuarioVO>();
			
			//Obtiene listado de datos para armar menú principal y secundario.
			list = MenuPorUsario_DAO.getMenuPorUsuario(rut_user);
			log.info("Tamaño listado MenuPorUsario, menú principal :" + list.size());
			
			return list;
		}catch(Exception ex){
			log.error("Error, ManagerMenu.getMenu : \n " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	
}


////Ordena datos por nivel.
//HashMap<String, MenuPorUsuarioVO> map = new HashMap<String, MenuPorUsuarioVO>();
//
//if(list.size()!= 0){
//	for(int i = 0; i < list.size(); i++){
//		//Se carga en el mapa datos por nivel.
//		map.put(String.valueOf(list.get(i).getNivel()), menu);
//		//Se agrega dato a lista a desplegar en interfaz.
//		menu_userList.add(map);
//	}
//}