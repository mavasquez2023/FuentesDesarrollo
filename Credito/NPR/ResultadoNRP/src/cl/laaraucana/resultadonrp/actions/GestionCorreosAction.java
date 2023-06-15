package cl.laaraucana.resultadonrp.actions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.resultadonrp.dao.ConsultaServicesDAO;
import cl.laaraucana.resultadonrp.dao.vo.CorreoVO;
import cl.recursos.EnviarMail;


/**
 * @version 1.0
 * @author
 */
public class GestionCorreosAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public ActionForward eliminaCorreo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
				
		HttpSession sesion = request.getSession();

		String id= request.getParameter("id");
		String proceso= request.getParameter("proceso");
		
		try {
			
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			consultaDAO.deleteCorreoByID(Integer.parseInt(id));
			List<CorreoVO>correos= consultaDAO.consultaCorreos(proceso);
			if(correos.size()>0){
				request.setAttribute("correos", correos);
				request.setAttribute("error", "0");
			}

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en   borrar correo: " + id);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		
		request.setAttribute("menu", "administracion");
		forward = mapping.findForward("success");
		
		return (forward);

	}
	
	public ActionForward agregaCorreo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
				
		HttpSession sesion = request.getSession();

		String proceso = request.getParameter("proceso");
		String correo= request.getParameter("correo");
		String usuario= request.getParameter("usuario");
		
		if( correo==null || correo.equals("")){
			forward = mapping.findForward("success");
			request.setAttribute("error", "-2");
			return forward;
		}
		
		

		try {
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			CorreoVO correoVO= new CorreoVO();
			correoVO.setCorreo(correo);
			correoVO.setUsuario(usuario);
			correoVO.setProceso(proceso);
			//eliminar registro vacío en caso de existir
			consultaDAO.deleteCorreoProceso(correoVO);
			
			//insertar nuevo correo
			Integer res= consultaDAO.insertCorreo(correoVO);
			
			List<CorreoVO>correos= consultaDAO.consultaCorreos(proceso);
			if(correos.size()>0){
				request.setAttribute("correos", correos);
				request.setAttribute("error", "0");
			}

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en   Proceso: " + proceso);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		request.setAttribute("menu", "administracion");
		forward = mapping.findForward("success");
		return (forward);

	}
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		Map<String, String> listamap=null;
		try {	
			HttpSession sesion = request.getSession();
			
			request.setAttribute("menu", "administracion");
			String proceso = request.getParameter("proceso");
			if(proceso==null){
				//Default Consolidación
				proceso="CON";
			}
			request.setAttribute("proceso", proceso);
			String accion = request.getParameter("accion");
			if(accion==null){
				accion="";
			}

			if(accion.equals("eliminaCorreo")){
				return eliminaCorreo(mapping, form, request, response);
			}

			if(accion.equals("agregaCorreo")){
				return agregaCorreo(mapping, form, request, response);
			}
			
			if(accion.equals("editaCorreo") || accion.equals("menu")){
				Map<String, String> indiceEmpresas= (TreeMap<String, String>)sesion.getAttribute("empresas");

				ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
				List<CorreoVO>correos= consultaDAO.consultaCorreos(proceso);
				request.setAttribute("correos", correos);
				request.setAttribute("error", "0");
				forward = mapping.findForward("success");
			}


		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Gestion Correo: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");

		}
		forward = mapping.findForward("success");
		return (forward);

	}
	
	    
}
