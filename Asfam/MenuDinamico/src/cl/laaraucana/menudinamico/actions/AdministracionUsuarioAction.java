package cl.laaraucana.menudinamico.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.menudinamico.forms.Usuario_Form;
import cl.laaraucana.menudinamico.manager.ManagerAdministracionUsuario;
import cl.laaraucana.menudinamico.vo.UsuarioVO;

/**
 * Clase Actions Struts para manejo de mantenedor de administración de usuarios.
 * @version 	1.0
 * @author
 */
public class AdministracionUsuarioAction extends DispatchAction
{
	private Logger log = Logger.getLogger(this.getClass());
	private String msgListNull = " ";
	
	ManagerAdministracionUsuario manager = new ManagerAdministracionUsuario();
	
	/**
	 * Actions para ingreso desde interfaz principal a interfaz de administración de 
	 * usuario.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward goAdminstracionUsuario(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception 
	{
    	log.info(" ---- Ingreso a goAdminstracionUsuario. ---- ");
		ActionForward forward = new ActionForward(); // return value
		Usuario_Form forms = (Usuario_Form) form;
		try {
			long codMenu = forms.getCodMenu();
			long nodoPadre = forms.getNodoPadre();
			
			System.out.println("---------------------------------");
			System.out.println("---------------------------------");
			System.out.println("codMenu = " + codMenu);
			System.out.println("nodoPadre = " + nodoPadre);
			System.out.println("---------------------------------");
			System.out.println("---------------------------------");
			
			
			//Obtención listado de usuarios existentes en el sistema.
			ArrayList<UsuarioVO> userList = new ArrayList<UsuarioVO>();
			userList = manager.getListadoInicial();
			
			if(userList.size()==0){
				userList = new ArrayList<UsuarioVO>();
				msgListNull = "No se ha encontrado ningún dato de usuario " +
					"en el sistema.";
			}
			
			log.info("Tamaño listado de usuarios existentes en el sistema : " 
				+ userList.size());
			
			request.setAttribute("codMenu", codMenu);
			request.setAttribute("nodoPadre", nodoPadre);
			request.setAttribute("msgListNull", msgListNull);
			request.setAttribute("userList", userList);
			forward =  mapping.findForward("successAdmUser");
		} catch (Exception ex) {
			log.error("Error, actions goAdminstracionUsuario : \n " + ex.getMessage());
			request.setAttribute("mensaje", "Error, actions goAdminstracionUsuario : \n " + ex.getMessage());
			forward = mapping.findForward("error");
			ex.printStackTrace();
		}
	// Finish with
	return (forward);
    }
    
    /**
	 * Actions para eliminación de usuario. 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward eliminarUsuario(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception 
	{
    	log.info(" ---- Ingreso a eliminarUsuario. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg="";
		try {
			boolean key=false;
			if(form!=null){
				Usuario_Form uForm =(Usuario_Form) form;
				UsuarioVO userVO =new UsuarioVO();
				ManagerAdministracionUsuario mgrUsuario = new ManagerAdministracionUsuario();				
				userVO.setId_user(uForm.getId_user());
				//userVO.setRut_user(uForm.getRut_user());
				
				int res=mgrUsuario.eliminarUsuario(userVO);
				
				//0 si ocurrio un problema
				//1 si el usuario no se pudo eliminar
				//2 si el usuario se elimino correctamente.
				if(res==0){					
					msg="Ocurrió un problema al intentar realizar la operación: eliminar usuario.";
				}
				if(res==1){
					msg="No se encontro el usuario para poder eliminarlo.";
				}
				if(res==2){					
					msg="Se elimino correctamente el usuario.";
				}
			}
			
			request.setAttribute("keyOperacion", false);
	   		request.setAttribute("msg", msg);
	   		forward =  mapping.findForward("successEliminarUsuario");
		} catch (Exception ex) {
			msg="Ocurrió un problema al intentar realizar la operación: eliminar usuario.";
			//forward =  mapping.findForward("error");
			request.setAttribute("keyOperacion", false);
	   		request.setAttribute("msg", msg);
	   		forward =  mapping.findForward("successEliminarUsuario");
			log.error("Error, actions eliminarUsuario : \n " + ex.getMessage());
			ex.printStackTrace();
		}
	// Finish with
	return (forward);
    }//end eliminarUsuario
    
    /**
	 * Actions para actualización de usuario.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward actualizarUsuario(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception 
	{
    	log.info(" ---- Ingreso a actualizarUsuario. ---- ");
		ActionForward forward = new ActionForward(); // return value
		String msg="";
		try {
			boolean key=false;
			if(form!=null){
				Usuario_Form uForm =(Usuario_Form) form;
				UsuarioVO userVO =new UsuarioVO();
				ManagerAdministracionUsuario mgrUsuario = new ManagerAdministracionUsuario();
				
				userVO.setId_user(uForm.getId_user());
				userVO.setRut_user(uForm.getRut_user().trim());
   				userVO.setApe_materno(uForm.getApe_materno().trim());				
   				userVO.setApe_paterno(uForm.getApe_paterno().trim());
   				userVO.setEmail_user(uForm.getEmail_user().trim());
   				userVO.setNombre_user(uForm.getNombre_user().trim());
				
				int res=mgrUsuario.actualizarUsuario(userVO);
				
				//0 si ocurrio un problema
				//1 si el usuario no se pudo actualizar(no se encontro)
				//2 si el usuario se actualizo correctamente.
				if(res==0){					
					msg="Ocurrió un problema al intentar realizar la operación: actualizar usuario.";
				}
				if(res==1){
					msg="No se completo la operacion de actualización";
				}
				if(res==2){					
					msg="Se actualizo correctamente el usuario.";
				}
				
			}			
			request.setAttribute("keyOperacion", false);
   			request.setAttribute("msg", msg);
			forward =  mapping.findForward("successActualizarUsuario");
			
		} catch (Exception ex) {
			msg="Ocurrió un problema al intentar realizar la operación: actualizar usuario.";
			//forward =  mapping.findForward("error");
			request.setAttribute("keyOperacion", false);
   			request.setAttribute("msg", msg);
			forward =  mapping.findForward("successActualizarUsuario");
			log.error("Error, actions actualizarUsuario : \n " + ex.getMessage());
			ex.printStackTrace();
		}
	// Finish with
	return (forward);
    }//end actualizarUsuario
    
    /**
   	 * Actions para actualización de usuario .
   	 * @param mapping
   	 * @param form
   	 * @param request
   	 * @param response
   	 * @return
   	 * @throws Exception
   	 */
       public ActionForward insertarUsuario(ActionMapping mapping, ActionForm form,
   	    HttpServletRequest request, HttpServletResponse response)
   	    throws Exception 
   	{
       	log.info(" ---- Ingreso a insertarUsuario. ---- ");
   		ActionForward forward = new ActionForward(); // return value
   		String msg="";
   		try {
   			boolean key=false;
   			if(form!=null){
   				Usuario_Form uForm =(Usuario_Form) form;
   				UsuarioVO userVO =new UsuarioVO();
   				ManagerAdministracionUsuario mgrUsuario = new ManagerAdministracionUsuario();
   				
   				userVO.setId_user(uForm.getId_user());
   				userVO.setRut_user(uForm.getRut_user().trim());
   				userVO.setApe_materno(uForm.getApe_materno().trim());				
   				userVO.setApe_paterno(uForm.getApe_paterno().trim());
   				userVO.setEmail_user(uForm.getEmail_user().trim());
   				userVO.setNombre_user(uForm.getNombre_user().trim());
   				
   				int res=mgrUsuario.insertarUsuario(userVO);
   				
   				//0 si ocurrio un problema
   				//1 si el usuario ya existe
   				//2 si el usuario se inserto correctamente.
   				if(res==0){					
   					msg="Ocurrió un problema al intentar realizar la operación: insertar usuario.";
   				}
   				if(res==1){
   					msg="El usuario ya existe.";
   				}
   				if(res==2){   					
   					msg="Se inserto correctamente el usuario.";
   				}
   			}			
   			
   			request.setAttribute("keyOperacion", false);
   			request.setAttribute("msg", msg);
   			forward =  mapping.findForward("successInsertarUsuario");
   			
   		} catch (Exception ex) {
   			msg="Ocurrió un problema al intentar realizar la operación: insertar usuario.";
   			//forward =  mapping.findForward("error");
   			request.setAttribute("keyOperacion", false);
   			request.setAttribute("msg", msg);
   			forward =  mapping.findForward("successInsertarUsuario");
   			log.error("Error, actions actualizarUsuario : \n " + ex.getMessage());
   			ex.printStackTrace();
   		}
   	// Finish with
   	return (forward);
       }//end insertarUsuario
    
   /**
  	 * Actions para buscar un usuario.
  	 * @param mapping
  	 * @param form
  	 * @param request
  	 * @param response
  	 * @return
  	 * @throws Exception
  	 */
  public ActionForward buscarUsuario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
  	log.info(" ---- Ingreso a buscarUsuario. ---- ");
	ActionForward forward = new ActionForward(); // return value
	String msg="";
	try {
		boolean key=false;
		if(form!=null){
			Usuario_Form uForm =(Usuario_Form) form;
			UsuarioVO userVO =new UsuarioVO();
			ManagerAdministracionUsuario mgrUsuario = new ManagerAdministracionUsuario();
			ArrayList<UsuarioVO> userList=new ArrayList<UsuarioVO>();
			
			//userVO.setId_user(uForm.getId_user());
			userVO.setRut_user(uForm.getRut_user());
			
			userVO=mgrUsuario.buscarUsuario_Rut(uForm.getRut_user());
			
			if(userVO!=null){
				
				userList.add(userVO);
				request.setAttribute("userList", userList);
			}else{
				msg="Ocurrió un problema al intentar realizar la operación: buscar un usuario";
				request.setAttribute("msg", msg);
			}
		}		
		forward =  mapping.findForward("successBuscarUsuario");		
	} catch (Exception ex) {
		msg="Ocurrió un problema al intentar realizar la operación: buscar usuario.";
		//forward =  mapping.findForward("error");
		forward =  mapping.findForward("successBuscarUsuario");	
		request.setAttribute("msg", msg);
		log.error("Error, actions buscarUsuario : \n " + ex.getMessage());
		ex.printStackTrace();
	}
	// Finish with
	return (forward);
  }//end insertarUsuario
      
   
   public ActionForward buscarUsuario_form(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
  	log.info(" ---- Ingreso a buscarUsuario_form. ---- ");
	ActionForward forward = new ActionForward(); // return value
	String msg="";
	try {
		boolean key=false;
		if(form!=null){
			Usuario_Form uForm =(Usuario_Form) form;
			UsuarioVO userVO =new UsuarioVO();
			ManagerAdministracionUsuario mgrUsuario = new ManagerAdministracionUsuario();
			ArrayList<UsuarioVO> userList=new ArrayList<UsuarioVO>();
			
			//userVO.setId_user(uForm.getId_user());
			//userVO.setRut_user(uForm.getRut_user());
			
			userVO=mgrUsuario.buscarUsuario_Cod(uForm.getId_user());
			
			if(userVO!=null){
				//userList.set(0, userVO);
				request.setAttribute("userRegistro", userVO);
				request.setAttribute("keyOperacion", true);
			}else{
				msg="Ocurrió un problema al intentar realizar la operación: buscar un usuario";
				request.setAttribute("keyOperacion", false);
				request.setAttribute("msg", msg);
			}
		}
		forward =  mapping.findForward("successBuscarUsuario_form");
	} catch (Exception ex) {
		msg="Ocurrió un problema al intentar realizar la operación: buscar usuario unico.";
		//forward =  mapping.findForward("error");
		request.setAttribute("keyOperacion", false);
		request.setAttribute("msg", msg);
		forward =  mapping.findForward("successBuscarUsuario_form");
		log.error("Error, actions buscarUsuario_form : \n " + ex.getMessage());
		ex.printStackTrace();
	}
	// Finish with
	return (forward);
  }//end insertarUsuario    
      
      
	  /**
	 * Actions para re_cargar listado 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	  public ActionForward cargarListado_Usuario(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception 
  	{
    	log.info(" ---- Ingreso a cargarListado_Usuario. ---- ");
  		ActionForward forward = new ActionForward(); // return value
  		String msg="";
  		try {
  			//Obtención listado de usuarios existentes en el sistema.
  			ArrayList<UsuarioVO> userList = new ArrayList<UsuarioVO>();
  			userList = manager.getListadoInicial();
  			
  			request.setAttribute("userList", userList);
  			forward =  mapping.findForward("successCargarListadoUsuario");
  		} catch (Exception ex) {
  			msg="Ocurrió un problema al intentar realizar la operación: cargar listado de usuarios.";
  			forward =  mapping.findForward("error");
  			log.error("Error, actions cargarListado_Usuario : \n " + ex.getMessage());
  			ex.printStackTrace();
  		}  		
  	// Finish with
  	return (forward);
    }//end cargarListado_Menu
	  
	  /**
	 * Actions para re_cargar listado 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	  public ActionForward mostrarFormUsuario(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception 
  	{
    	log.info(" ---- Ingreso a cargarListado_Usuario. ---- ");
  		ActionForward forward = new ActionForward(); // return value
  		String msg="";
  		try {
  			//Obtención listado de usuarios existentes en el sistema.
  			UsuarioVO userRegistro = null;
  			
  			//keyOperacion: en true, permite ver el formulario
			//keyOperacion: en false, permite ver el msg
			request.setAttribute("keyOperacion", true);
  			request.setAttribute("userRegistro", userRegistro);
  			forward =  mapping.findForward("successMostrarFormUsuario");
  		} catch (Exception ex) {
  			msg="Ocurrió un problema al intentar realizar la operación: cargar listado de usuarios.";
  			//forward =  mapping.findForward("error");
  			request.setAttribute("keyOperacion", false);
  			request.setAttribute("msg", msg);
  			forward =  mapping.findForward("successMostrarFormUsuario");
  			log.error("Error, actions mostrarFormUsuario : \n " + ex.getMessage());
  			ex.printStackTrace();
  		}  		
  	// Finish with
  	return (forward);
    }//end cargarListado_Menu
	  
}//end class
