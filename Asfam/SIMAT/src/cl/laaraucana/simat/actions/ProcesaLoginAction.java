package cl.laaraucana.simat.actions;

import java.security.Principal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.UsuariosVO;
import cl.laaraucana.simat.mannagerDB2.UsuariosMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

public class ProcesaLoginAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" ---- Ingreso a LoginActions SIMAT ---- ");
		
		ActionForward forward = new ActionForward(); // return value
		String msg = "";
		try{
			//HttpSession session = request.getSession(true);
			
		Principal userPrincipal = request.getUserPrincipal();
		
	    if(userPrincipal!=null){
		String user =  userPrincipal.getName();
	        //if(true){
		    //String user =  "10104927-2";
				ManejoHoraFecha hfa = new ManejoHoraFecha();
				boolean keyLogin = false;
				UsuariosMannager uMgr = new UsuariosMannager();
				//ValidadorLDAP vu = new ValidadorLDAP();
				UsuariosVO uvo = new UsuariosVO();
						
				uvo = uMgr.BuscarByName(user);
				if (uvo != null) {
					//existe en BDSIMAT.
					Timestamp aux = null;
					uvo.setNombre_usuario(user);
					aux = hfa.getFechaHoraTimeStamp();
					uvo.setUltima_coneccion(aux.toString());
					System.out.println("hfa: " + hfa.getFechaHoraTimeStamp());
	
					uMgr.actualizarUsuariosUltimaConeccion(uvo);
					keyLogin = true;
				}
				
				if (keyLogin) {
					System.out.println("key true");
					HttpSession sesionActual = request.getSession(true);
					sesionActual.setAttribute("nombre", user);
					//sesionActual.setAttribute("clave", pass);
					forward = mapping.findForward("direcLoginok");
				} else {
					System.out.println("else key false");
					msg = "usuario o clave no valida, intentelo nuevamente";
					request.setAttribute("errorLogin", msg);
					forward = mapping.findForward("errorLogin");
				}
	        }else{
				forward = mapping.findForward("errorLogin");
			}
		} catch (Exception e) {
			System.out.println("LOG catch: " + e);
			msg = "Problemas al autenticar sus datos ";
			request.setAttribute("errorLogin", msg);
			forward = mapping.findForward("errorLogin");
		}
		//Finish with
		return (forward);
	}

	/*
	public ActionForward cerrarSession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nombre = null;
		String clave = null;
		ActionForward forward;

		System.out.println("cerrando sesion");

		HttpSession sesionActual = request.getSession(true);
		sesionActual.invalidate();
		sesionActual = null;

		if (sesionActual != null) {
			System.out.println("if_1 !=null");
			System.out.println("comprobacion 2: " + sesionActual.getAttribute("nombre"));
			System.out.println("comprobacion 1: " + sesionActual.getAttribute("nombre"));
			request.getSession().removeAttribute("nombre");
			request.getSession().removeAttribute("clave");
			sesionActual.invalidate();
		}
		if (sesionActual == null) {
			System.out.println("forward null");
			forward = mapping.findForward("logout");
		} else {
			System.out.println("forward  !=null");
			forward = mapping.findForward("errorLogin");
		}
		return null;
	}*/
}