package cl.laaraucana.menudinamico.dao;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import cl.laaraucana.menudinamico.dao.conf.InitConexion;
import cl.laaraucana.menudinamico.dao.conf.MyClassSqlConfig;
import cl.laaraucana.menudinamico.vo.IlfusrmnKey;
import cl.laaraucana.menudinamico.vo.MenuPorUsuarioVO;
import cl.laaraucana.menudinamico.vo.adm_MenuPorUsuarioVO;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Clase para manejo entre aplicación y BD a través de IBATIS.
 * @author usist42
 *
 */
public class MenuPorUsario_DAO {

	private static Logger logger = Logger.getLogger(MenuPorUsario_DAO.class);
	
	
	/**
	 * Método de comunicación entre aplicación y BD para obtención datos de menú 
	 * a desplegar en interfaz.
	 * @param rut_user
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<MenuPorUsuarioVO> getMenuPorUsuario(String rut_user) 
		throws Exception 
	{
		SqlMapClient sqlMap = null;
		ArrayList<MenuPorUsuarioVO> menuList = new ArrayList<MenuPorUsuarioVO>();
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			//Seteo de datos a buscar en query.
			MenuPorUsuarioVO mXu = new MenuPorUsuarioVO();
			mXu.setRut_user(rut_user);
			
			//Consulta SQL.
			menuList=(ArrayList<MenuPorUsuarioVO>) sqlMap.queryForList("getMenuPorUsuario", mXu);
			
			sqlMap.openSession().close(); sqlMap = null;
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
		}
		return menuList;
	}//end 	buscarUnico_Menu
	
	
	
	
	
	
	
	public static ArrayList<adm_MenuPorUsuarioVO> getAdmMenuPorUsuario(String rut_user) throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList<adm_MenuPorUsuarioVO> menuList = new ArrayList<adm_MenuPorUsuarioVO>();
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			//Seteo de datos a buscar en query.
			MenuPorUsuarioVO mXu = new MenuPorUsuarioVO();
			mXu.setRut_user(rut_user);
			
			//Consulta SQL.
			menuList=(ArrayList<adm_MenuPorUsuarioVO>) sqlMap.queryForList("getAdmMenuPorUsuario", mXu);
			
			sqlMap.openSession().close(); sqlMap = null;
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
		}
		return menuList;
	}//end 	buscarUnico_Menu
	
	/**
	 * elimina relacion usuario-menu, cuando el codigo usuario
	 * se elimino de la tabla usuario
	 * 
	 * **/
	public static int eliminar_UsuarioPorMenu(int codUser) throws Exception {
		SqlMapClient sqlMap = null;		
		int res=0;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			//Seteo de datos a buscar en query.
			adm_MenuPorUsuarioVO mXu = new adm_MenuPorUsuarioVO();
			mXu.setId_user(codUser);
			
			//Consulta SQL.
			res= sqlMap.delete("del_UsuarioPorMenu", mXu);
			
			sqlMap.openSession().close(); sqlMap = null;
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
		}
		return res;
	}//end 	eliminar_UsuarioPorMenu
	
	
	/**
	 * elimina relacion usuario-menu, cuando el codigo menu
	 * se elimino de la tabla menu.
	 * **/
	public static int eliminar_MenuPorUsuario(long codMenu) throws Exception {
		SqlMapClient sqlMap = null;
		int res=0;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();			
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			//Seteo de datos a buscar en query.
			adm_MenuPorUsuarioVO mXu = new adm_MenuPorUsuarioVO();
			mXu.setCodMenu(codMenu);
			
			//Consulta SQL.
			res=sqlMap.delete("del_MenuPorUsuario", mXu);
			
			sqlMap.openSession().close(); sqlMap = null;
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
		}
		return res;
	}//end 	eliminar_MenuPorUsuario
	
	
	/**
	 * elimina relacion usuario-menu, 
	 * cuando se quita asignacion de menu sobre usuario.
	 * **/
	public static int quitar_MenuPorUsuario(adm_MenuPorUsuarioVO mXu) throws Exception {
		SqlMapClient sqlMap = null;
		int res=0;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			//Seteo de datos a buscar en query.
			mXu.setId_user(mXu.getId_user());
			mXu.setCodMenu(mXu.getCodMenu());
						
			//Consulta SQL.
			res=sqlMap.delete("quitar_MenuPorUsuario", mXu);
			
			sqlMap.openSession().close(); sqlMap = null;
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
		}
		return res;
	}//end 	eliminar_MenuPorUsuario
	
	/**
	 * asigna menu a un usuario en tabla relacion
	 * **/
	public static String asignar_MenuPorUsuario(adm_MenuPorUsuarioVO mXu) throws Exception {
		SqlMapClient sqlMap = null;
		String res="";
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			//Seteo de datos a buscar en query.
			mXu.setId_user(mXu.getId_user());
			mXu.setCodMenu(mXu.getCodMenu());
						
			//Consulta SQL.
			sqlMap.insert("asignar_MenuPorUsuario", mXu);			
			sqlMap.openSession().close(); 
			sqlMap = null;
			res="OK";
		}catch(Exception e){			
			res="NOK";
		}
		return res;
	}//end 	asignar_MenuPorUsuario
	
	/**
	 * obtener usuarios por menú
	 * **/
	public static  ArrayList<IlfusrmnKey> obtenerUsuariosPorMenu(long codigoMenu ) throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList<IlfusrmnKey> res = new ArrayList<IlfusrmnKey>();
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
	
			//Consulta SQL.
			res = (ArrayList<IlfusrmnKey>) sqlMap.queryForList("get_UsuariosPorMenu", codigoMenu);			
			sqlMap.openSession().close(); 
			sqlMap = null;
			
		}catch(Exception e){			
			logger.error("Error query: " + e.getMessage());
		}
		return res;
	}//end 	asignar_MenuPorUsuario
	
}
