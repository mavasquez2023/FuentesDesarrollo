package cl.laaraucana.menudinamico.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;



import cl.laaraucana.menudinamico.dao.conf.InitConexion;
import cl.laaraucana.menudinamico.dao.conf.MyClassSqlConfig;
import cl.laaraucana.menudinamico.vo.MenuVO;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 *
 */
public class Menu_DAO {

	private static Logger logger = Logger.getLogger(Menu_DAO.class);
	
	public static String insertar_Menu(MenuVO ilfajamenVO){
		SqlMapClient sqlMap = null;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
//		try{
//			sqlMap.insert("insertar_Menu", ilfajamenVO);
//			sqlMap.openSession().close();
//		}catch(Exception e){
//			logger.error("Error query: " + e.getMessage());
//			e.printStackTrace();
//		}
		
		try {
			if (ilfajamenVO.getCodMenu() == 0) {
				sqlMap.insert("insertar_MenuCodAuto", ilfajamenVO);
			}else{
				sqlMap.insert("insertar_Menu", ilfajamenVO);
			}			
			
            return "OK";
        } catch (SQLException e) {
        	logger.error("Error query: " + e.getMessage());
            return e.getMessage();
        }
		
	}//end 	insertar_ILFJAMEN
	
	public static int eliminar_Menu(MenuVO ilfajamenVO) throws Exception {
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
			res=sqlMap.delete("del_Menu", ilfajamenVO);
			if(res>0){
				//se eliminan relacion de menu por usuario. donde el menu coincida con el codigo eliminado.
				MenuPorUsario_DAO.eliminar_MenuPorUsuario(ilfajamenVO.getCodMenu());
			}
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}//end 	eliminar_Menu
	
	public static ArrayList<MenuVO> getTodosHijos(long ilfajamenVO) throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList<MenuVO> res= new ArrayList<MenuVO>();
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			res= (ArrayList<MenuVO>) sqlMap.queryForList("getTodosHijos", ilfajamenVO);
			
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}//end 	getTodosHijos
	
	public static int actualizar_Menu(MenuVO ilfajamenVO) throws Exception {
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
			res=sqlMap.update("up_Menu", ilfajamenVO);
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}//end 	actualizar_Menu
	
	
	public static MenuVO buscarCodMenu(long codMenu) throws Exception {
		SqlMapClient sqlMap = null;
		MenuVO menuRegis=null;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			String aux="";
			HashMap<String,String> mapDatos=new HashMap<String,String>();
			
			aux=String.valueOf(codMenu);
			if(!aux.equalsIgnoreCase("")){
				mapDatos.put("codMenu", aux);
			}
			
			logger.info("* * * * * mapDatos codMenu: " + mapDatos.get("codMenu"));
						
			menuRegis=(MenuVO) sqlMap.queryForObject("getBuscar_Menu", mapDatos);
			//logger.info("* * * * * resultLIST: " + menuList.size());
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return menuRegis;
	}//end 	buscarCodMenu

	public static ArrayList<MenuVO> buscarEtiquetaMenu(String etiqueta) throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList<MenuVO> menuList=null;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			
			HashMap<String,String> mapDatos=new HashMap<String,String>();
						
			if(!etiqueta.equalsIgnoreCase("")){
				mapDatos.put("etiqueta", etiqueta.trim().toUpperCase());
			}
						
			menuList=(ArrayList<MenuVO>) sqlMap.queryForList("getBuscar_MenuByEtiqueta", mapDatos);
			//logger.info("* * * * * resultLIST: " + menuList.size());
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return menuList;
	}//end 	buscarCodMenu
	
	
	
	public static ArrayList buscarTodo_Menu() throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList menuList=new ArrayList();
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			menuList=(ArrayList) sqlMap.queryForList("getBuscar_Menu", null);
			logger.info("* * * * * resultLIST: " + menuList.size());
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return menuList;
	}//end 	buscarTodo_Menu
	
	public static ArrayList buscarTodo_MenuSorted() throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList menuList=new ArrayList();
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			menuList=(ArrayList) sqlMap.queryForList("getBuscar_MenuSORTED", null);
			logger.info("* * * * * resultLIST: " + menuList.size());
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return menuList;
	}//end 	buscarTodo_Menu
	
	
	public static long getCodMenu() throws Exception {
		SqlMapClient sqlMap = null;
		long codigo = 0;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
												
			codigo = (Long) sqlMap.queryForObject("getCodigoMenu", null);
			//logger.info("* * * * * resultLIST: " + menuList.size());
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return codigo;
	}//end 	buscarCodMenu

	public static int eliminarMenus(ArrayList<MenuVO> lista) {
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
			res=sqlMap.delete("del_Menus", lista);
			if(res>0){
				//se eliminan relacion de menu por usuario. donde el menu coincida con el codigo eliminado.
				for (MenuVO menuVO : lista) {
					MenuPorUsario_DAO.eliminar_MenuPorUsuario(menuVO.getCodMenu());
				}
			}
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}
	
}//end class
