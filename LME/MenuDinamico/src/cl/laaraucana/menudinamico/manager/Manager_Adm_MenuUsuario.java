package cl.laaraucana.menudinamico.manager;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import cl.laaraucana.menudinamico.dao.MenuPorUsario_DAO;
import cl.laaraucana.menudinamico.vo.IlfusrmnKey;
import cl.laaraucana.menudinamico.vo.MenuPorUsuarioVO;
import cl.laaraucana.menudinamico.vo.UsuarioVO;
import cl.laaraucana.menudinamico.vo.adm_MenuPorUsuarioVO;


public class Manager_Adm_MenuUsuario {

	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Método para obtener listado de todos los Menus 
	 * existentes en el sistema.
	 * @return
	 */
	public ArrayList<MenuPorUsuarioVO> getListadoMenuPorUsuario(String rut_user){
		ArrayList<MenuPorUsuarioVO> menuPorUsuario_List=null;
		try{
			menuPorUsuario_List = MenuPorUsario_DAO.getMenuPorUsuario(rut_user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return menuPorUsuario_List;
	}//end getListadoInicial
	
	
	
	public ArrayList<adm_MenuPorUsuarioVO> getAdmMenuPorUsuario(String rut_user){
		ArrayList<adm_MenuPorUsuarioVO> menuPorUsuario_List=null;
		try{
			menuPorUsuario_List = MenuPorUsario_DAO.getAdmMenuPorUsuario(rut_user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return menuPorUsuario_List;
	}//end getAdmMenuPorUsuario
	
	/**
	 * asigna los menu seleccionado en web aun usuario determinado.
	 * responde
	 * 0: ocurrio un problema
	 * 1: se asignaron todos los menu.
	 * 2: no se asignaron todos los menu, puede que alguno ya exista asignado.
	 * **/
	public int asignar_MenuPorUsuario(String concat){
		int res=0;
		String aux="";
		int count=0;
		int vc=0;
		try{	
			adm_MenuPorUsuarioVO mXu = new adm_MenuPorUsuarioVO();
			UsuarioVO user= new UsuarioVO();
			ManagerAdministracionUsuario mgrUser =new ManagerAdministracionUsuario();
			
			String asignacion[]=null;
			asignacion=concat.split("#");
			log.info(" quitar_MenuPorUsuario");
						
			if(asignacion!=null){
				log.info(" asignacion: "+asignacion.length);
				//primera posicion, contiene rut.
				user=mgrUser.buscarUsuario_Rut(asignacion[0]);	
				if(user!=null){
					mXu.setId_user(user.getId_user());					
					log.info(" Coduser: "+user.getId_user());
					
					//Eliminar todos los menus asociados el usuario, para luego ingresar las nuevas selecciones.
					MenuPorUsario_DAO.eliminar_UsuarioPorMenu(user.getId_user());
					
					for(vc=1 ; vc<asignacion.length; vc++){						
						mXu.setCodMenu(Integer.parseInt(asignacion[vc]));
						log.info(" CodMenu: "+asignacion[vc]);
						aux = MenuPorUsario_DAO.asignar_MenuPorUsuario(mXu);
						if(aux.equalsIgnoreCase("OK")){
							count++;
						}
					}
					if(count==(asignacion.length-1)){
						res=1;
					}
					if(count<(asignacion.length-1)){
						res=2;
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			res=0;
		}
		return res;
	}//end quitar_MenuPorUsuario
	
	
	/**
	 * quita uno o varios menu(s) a un usuario.
	 * retorna 
	 * 0: ocurrio un problema
	 * 1: se quitaron todos los menu
	 * 2: no se quitaron todos los menu
	 * 
	 * */
	public int quitar_MenuPorUsuario(String concat){
		int res=0;
		int count=0;
		int vc=0;
		try{	
			adm_MenuPorUsuarioVO mXu = new adm_MenuPorUsuarioVO();
			UsuarioVO user= new UsuarioVO();
			ManagerAdministracionUsuario mgrUser =new ManagerAdministracionUsuario();
			
			String asignacion[]=null;
			asignacion=concat.split("#");
			log.info(" quitar_MenuPorUsuario");
			
			
			if(asignacion!=null){
				log.info(" asignacion: "+asignacion.length);
				//primera posicion, contiene rut.
				user=mgrUser.buscarUsuario_Rut(asignacion[0]);	
				if(user!=null){
					mXu.setId_user(user.getId_user());					
					log.info(" Coduser: "+user.getId_user());
					for(vc=1 ; vc<asignacion.length; vc++){					
						mXu.setCodMenu(Integer.parseInt(asignacion[vc]));
						log.info(" CodMenu: "+asignacion[vc]);
						res = MenuPorUsario_DAO.quitar_MenuPorUsuario(mXu);
						if(res>0){
							count++;
						}
					}
					if(count<(asignacion.length-1)){
						res=2;
					}else{
						res=0;
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}//end quitar_MenuPorUsuario
	

	public int asignar_MenuAUsuariosConMenu(long codMenuUpd ,long codMenuToAsign){
		String aux="";
		int count=0;
		try{	
			log.info("asignar_MenuAUsuariosConMenu");
			
			ArrayList<IlfusrmnKey> list	=	MenuPorUsario_DAO.obtenerUsuariosPorMenu(codMenuUpd);
			adm_MenuPorUsuarioVO mXu = new adm_MenuPorUsuarioVO();
			mXu.setCodMenu(codMenuToAsign);
			for (IlfusrmnKey ilfusrmnKey : list) {
				mXu.setId_user(ilfusrmnKey.getIdproc());
				aux = MenuPorUsario_DAO.asignar_MenuPorUsuario(mXu);
				if(aux.equalsIgnoreCase("OK")){
					count++;
				}
			}		
			
					
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return count;
	}//end asignar_MenuAUsuariosConMenu
	
}//end class
