package cl.laaraucana.menudinamico.manager;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import cl.laaraucana.menudinamico.dao.Usuario_DAO;
import cl.laaraucana.menudinamico.vo.UsuarioVO;


public class ManagerAdministracionUsuario {

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Método para obtener listado de todos los usuarios 
	 * existentes en el sistema.
	 * @return
	 */
	public ArrayList<UsuarioVO> getListadoInicial(){
		ArrayList<UsuarioVO> userList=null;
		try{
			userList = Usuario_DAO.buscarTodo_User();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return userList;
	}//end getListadoInicial
	
	/**
	 * Método para obtener los
	 * datos de un usuario
	 * existentes en el sistema.
	 * @return
	 */
	public UsuarioVO buscarUsuario_Cod(int cod_user){
		UsuarioVO userAux=null;
		try{
			userAux = Usuario_DAO.buscarUnico_UserCod(cod_user);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return userAux;
	}//end buscarUsuario
	
	public UsuarioVO buscarUsuario_Rut(String rut){
		UsuarioVO userAux=null;
		try{
			userAux = Usuario_DAO.buscarUsuario_Rut(rut);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return userAux;
	}//end buscarUsuario
	
	/**
	 * Método para eliminar usuarios. 
	 * existentes en el sistema.
	 * 0 si ocurrio un problema
	 * 1 si el usuario no se pudo eliminar
	 * 2 si el usuario se elimino correctamente. 
	 * @return
	 */
	public int eliminarUsuario(UsuarioVO userVO){
		int resEstado=0;
		try{
			if(Usuario_DAO.eliminar_User(userVO)>0){
				resEstado=2;
			}else{
				resEstado=1;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resEstado;
	}//end eliminarUsuario
	
	/**
	 * Método para actualizar usuarios. 
	 * existentes en el sistema.
	 * responde con: 
	 * 0 si ocurrio un problema
	 * 1 si no se actualizo ningun registro.
	 * 2 si el usuario se actualizo correctamente.
	 * 3 si el rut actualizado ya existe.
	 * @return
	 */
	public int actualizarUsuario(UsuarioVO userVO){
		int resEstado=0;
		String res="";
		try{
//			if(this.existeUser(userVO)){
//				//existe.
//				if(Usuario_DAO.actualizar_User(userVO)>0){
//					resEstado=2;
//				}
//			}else{
//				//no existe.
//				resEstado=1;
//			}
			res=Usuario_DAO.actualizar_User(userVO);
			if(res.equalsIgnoreCase("ok")){
				//actualizacion ok
				resEstado=2;				
			}
			if(res.equalsIgnoreCase("nok")){
				//no se actualizaron registros.
				resEstado=1;				
			}
			if(res.indexOf("SQL0803")>-1){
				//el rut actualizado, ya existe, no se actualizo el registro.
				resEstado=3;
			}			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resEstado;
	}// end actualizarUsuario
	
	/**
	 * Método para insertar usuarios. 
	 * responde con:
	 * 0 si ocurrio un problema
	 * 1 si el usuario ya existe
	 * 2 si el usuario se inserto correctamente. 
	 * @return
	 */
	public int insertarUsuario(UsuarioVO userVO){
		int resEstado=0;
		
		try{
			String res=Usuario_DAO.insertar_User(userVO);
			if(res.equalsIgnoreCase("ok")){				
				resEstado=2;				
			}else if(res.indexOf("SQL0803")>-1){
				resEstado=1;
			}			
		}catch(Exception ex){	
			resEstado=0;
			ex.printStackTrace();
		}
				
		return resEstado;
	}//end insertarUsuario
	
	/**
	 * Método para consultar existencia de usuario
	 * responde con:
	 * true, si existe
	 * false, si no exciste. 
	 * @return
	 */
	private boolean existeUser(UsuarioVO userVO){
		boolean key=false;
			UsuarioVO userAux=null;
			userAux=this.buscarUsuario_Cod(userVO.getId_user());
			if(userAux!=null){
				key=true;
			}			
		return key;
	}//end existeUser
	
}//end class
