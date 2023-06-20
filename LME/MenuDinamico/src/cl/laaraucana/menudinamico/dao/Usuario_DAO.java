package cl.laaraucana.menudinamico.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;



import cl.laaraucana.menudinamico.dao.conf.InitConexion;
import cl.laaraucana.menudinamico.dao.conf.MyClassSqlConfig;
import cl.laaraucana.menudinamico.vo.UsuarioVO;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 *
 */
public class Usuario_DAO {

	private static Logger logger = Logger.getLogger(Usuario_DAO.class);
	
	public static String insertar_User(UsuarioVO ilfajaUserVO) throws Exception {
		SqlMapClient sqlMap = null;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		
//		try{
//			sqlMap.insert("insertar_User", ilfajaUserVO);
//			sqlMap.openSession().close();
//		}catch(Exception e){
//			logger.error("Error query: " + e.getMessage());
//			e.printStackTrace();
//		}
		
		try {			
			sqlMap.insert("insertar_User", ilfajaUserVO);
            return "OK";
        } catch (SQLException e) {
        	logger.error("Error query: " + e.getMessage());
            return e.getMessage();
        }
		
	}//end 	insertar_User
	
	public static int eliminar_User(UsuarioVO ilfajaUserVO) throws Exception {
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
			res=sqlMap.delete("del_User", ilfajaUserVO);
			if(res>0){
				//elimina relacion usuario-menu, donde el usuario coincida con el idUser eliminado,
				MenuPorUsario_DAO.eliminar_UsuarioPorMenu(ilfajaUserVO.getId_user());
			}
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}//end 	eliminar_User
	
	public static String actualizar_User(UsuarioVO ilfajaUserVO) throws Exception {
		SqlMapClient sqlMap = null;
//		* 0 si ocurrio un problema
//		 * 1 si no se actualizo ningun registro.
//		 * 2 si el usuario se actualizo correctamente.
//		 * 3 si el usuario 
		int resAux=0;
		String res="";
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
//		try{
//			resAux=sqlMap.update("up_User", ilfajaUserVO);
//			sqlMap.openSession().close();
//		}catch(Exception e){
//			logger.error("Error query: " + e.getMessage());
//			e.printStackTrace();
//		}
				
		try {			
			resAux=sqlMap.update("up_User", ilfajaUserVO);			
			if(resAux>0){
				res="OK";
			}else{
				res="NOK";
			}			
            return res;
        } catch (SQLException e) {
        	logger.error("Error query: " + e.getMessage());
            return e.getMessage();
        }
		
	}//end 	actualizar_User
	
	public static UsuarioVO buscarUnico_UserCod(int codUser) throws Exception {
		SqlMapClient sqlMap = null;
		UsuarioVO userRegis=null;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{			
			HashMap<String,String> mapDatos=new HashMap<String,String>();
			mapDatos.put("id_user", String.valueOf(codUser));
			
			userRegis=(UsuarioVO) sqlMap.queryForObject("getBuscar_UserById", mapDatos);
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return userRegis;
	}//end 	buscarUnico_User
	
	public static UsuarioVO buscarUsuario_Rut(String rut) throws Exception {
		SqlMapClient sqlMap = null;
		UsuarioVO userRegis=null;
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			HashMap<String,String> mapDatos=new HashMap<String,String>();
			mapDatos.put("rut_user", rut);
			userRegis=(UsuarioVO) sqlMap.queryForObject("getBuscar_UserByRut", mapDatos);
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return userRegis;
	}//end 	buscarUnico_User
	
	public static ArrayList buscarTodo_User() throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList userList=new ArrayList();
		try{			
			//sqlMap = MyClassSqlConfig.getSqlMapInstance();
			sqlMap = InitConexion.getConexion();
		}catch(Exception e){
			logger.error("Error al obtener instancia" + e.getMessage());
			e.printStackTrace();
		}
		try{
			Integer i = null;
			userList=(ArrayList) sqlMap.queryForList("getBuscar_User", i);
			sqlMap.openSession().close();
		}catch(Exception e){
			logger.error("Error query: " + e.getMessage());
			e.printStackTrace();
		}
		return userList;
	}//end 	buscarTodo_User
	
}//end class
