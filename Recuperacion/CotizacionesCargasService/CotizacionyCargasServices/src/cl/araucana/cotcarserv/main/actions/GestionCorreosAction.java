package cl.araucana.cotcarserv.main.actions;

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
import cl.araucana.cotcarserv.dao.VO.CorreoVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
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
		String rutEmpresa= request.getParameter("rutEmpresa");
		
		try {
			
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			consultaDAO.deleteCorreoByID(id);
			int rutEmpresaInt= Integer.parseInt(rutEmpresa.split("-")[0]);
			List<CorreoVO>correos= consultaDAO.consultaCorreos(rutEmpresaInt);
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
		forward = mapping.findForward("agregar");
		
		return (forward);

	}
	
	public ActionForward agregaCorreo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
				
		HttpSession sesion = request.getSession();

		String rutEmpresa= request.getParameter("rutEmpresa");
		String correo= request.getParameter("correo");
		
		if( correo==null || correo.equals("")){
			forward = mapping.findForward("success");
			request.setAttribute("error", "-2");
			return forward;
		}
		
		rutEmpresa= rutEmpresa.replaceAll("\\.", "");
		

		try {
			int rutEmpresaInt= Integer.parseInt(rutEmpresa.split("-")[0]);
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			CorreoVO correoVO= new CorreoVO();
			correoVO.setCorreo(correo);
			correoVO.setRutEmpresa(rutEmpresaInt);
			correoVO.setDvEmpresa(rutEmpresa.split("-")[1]);
			correoVO.setUsuario((String)sesion.getAttribute("usuario"));
			//eliminar registro vacío en caso de existir
			consultaDAO.deleteCorreoVacio(rutEmpresaInt);
			
			//insertar nuevo correo
			Integer res= consultaDAO.insertCorreo(correoVO);
			
			List<CorreoVO>correos= consultaDAO.consultaCorreos(rutEmpresaInt);
			if(correos.size()>0){
				request.setAttribute("correos", correos);
				request.setAttribute("error", "0");
			}

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en   Rut: " + rutEmpresa);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		request.setAttribute("menu", "administracion");
		forward = mapping.findForward("agregar");
		return (forward);

	}
	
	public ActionForward noRecibirCorreo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
				
		HttpSession sesion = request.getSession();

		String rutEmpresa= request.getParameter("rutEmpresa");
		String recibir= request.getParameter("recibir");
		
		rutEmpresa= rutEmpresa.replaceAll("\\.", "");

		try {
			int rutEmpresaInt= Integer.parseInt(rutEmpresa.split("-")[0]);
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			//Si desea no recibir correo
			if(recibir.equals("NR")){
				//eliminar registros existentes
				consultaDAO.deleteCorreoByRUT(rutEmpresaInt);
				
				//Crear registro con correo vacío
				CorreoVO correoVO= new CorreoVO();
				correoVO.setCorreo("");
				correoVO.setRutEmpresa(rutEmpresaInt);
				correoVO.setDvEmpresa(rutEmpresa.split("-")[1]);
				correoVO.setUsuario((String)sesion.getAttribute("usuario"));
				Integer res= consultaDAO.insertCorreo(correoVO);
				
				request.setAttribute("norecibir", "checked");
			}else{
				//eliminar registro vacío
				consultaDAO.deleteCorreoVacio(rutEmpresaInt);
			}
			
			

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en   Rut: " + rutEmpresa);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		request.setAttribute("menu", "administracion");
		forward = mapping.findForward("agregar");
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
			if(sesion.getAttribute("empresas")==null || sesion.getAttribute("rol")==null){
				forward = mapping.findForward("init");
				return forward;
			}

			Principal principal = request.getUserPrincipal();
			String username= principal.getName();
			sesion.setAttribute("usuario", username);
			request.setAttribute("error", "-99");

			String accion = request.getParameter("accion");
			String rutEmpresa = request.getParameter("rutEmpresa");
			if(accion==null){
				accion="";
			}

			if(accion.equals("eliminaCorreo")){
				return eliminaCorreo(mapping, form, request, response);
			}

			if(accion.equals("agregaCorreo")){
				return agregaCorreo(mapping, form, request, response);
			}
			
			if(accion.equals("noRecibirCorreo")){
				return noRecibirCorreo(mapping, form, request, response);
			}
			
			if(accion.equals("editaCorreo")){
				Map<String, String> indiceEmpresas= (TreeMap<String, String>)sesion.getAttribute("empresas");
				String razonSocial= indiceEmpresas.get(rutEmpresa);
				sesion.setAttribute("rutEmpresa", rutEmpresa);
				sesion.setAttribute("razonSocial", razonSocial);

				ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
				int rutEmpresaInt= Integer.parseInt(rutEmpresa.split("-")[0]);
				List<CorreoVO>correos= consultaDAO.consultaCorreos(rutEmpresaInt);
				request.setAttribute("norecibir", "");
				if(correos.size()>0){
					if(correos.get(0).getCorreo().trim().equals("")){
						request.setAttribute("norecibir", "checked");
						correos.clear();
					}
					request.setAttribute("correos", correos);
					request.setAttribute("error", "0");
				}
				forward = mapping.findForward("agregar");
				return forward;
			}

			if(rutEmpresa==null || rutEmpresa.equals("") || accion.equals("volver")){
				forward = mapping.findForward("success");
				return forward;
			}


		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Gestion Correo RutEmpresa: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");

		}
		forward = mapping.findForward("success");
		return (forward);

	}
	
	    
}
