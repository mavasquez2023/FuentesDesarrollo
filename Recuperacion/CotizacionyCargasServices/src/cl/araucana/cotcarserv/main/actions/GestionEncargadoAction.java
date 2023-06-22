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

import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.dao.VO.EmpresaVO;
import cl.araucana.cotcarserv.utils.ProxyLDAP;
import cl.araucana.cotcarserv.utils.VO.RolUsuarioEmpVO;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.recursos.EnviarMail;


/**
 * @version 1.0
 * @author
 */
public class GestionEncargadoAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected String appID="PorEmpAdhe";
	protected String rolID="PorEmpAdheEnc";
	@SuppressWarnings("unchecked")
	public ActionForward modificaEncargado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
				
		HttpSession sesion = request.getSession();
		
		
		String rutEncargado= request.getParameter("rutEncargado");
		String rutEmpresa= request.getParameter("rutEmpresa");
		String subaccion= request.getParameter("subaccion");
		
		if(rutEncargado==null || rutEmpresa==null){
			forward = mapping.findForward("success");
			request.setAttribute("error", "-2");
			return forward;
		}
		
		rutEncargado= rutEncargado.replaceAll("\\.", "");
		rutEmpresa= rutEmpresa.replaceAll("\\.", "");
		
		logger.info("eliminar Encargado " + rutEncargado);

		try {
			RolUsuarioEmpVO rolemp= new RolUsuarioEmpVO();
			rolemp.setRutUsuario(rutEncargado);
			rolemp.setRutEmpresa(rutEmpresa);
			rolemp.setApp(appID);
			rolemp.setApprol(rolID);
			if(subaccion.equals("del")){
				rolemp.setEstado("E");
			}else{
				rolemp.setEstado("N");
			}
			String mensaje= ProxyLDAP.assignAppRolEnterprise(rolemp);
			if (mensaje!=null){
				request.setAttribute("error", "-2");
				request.setAttribute("mensaje", mensaje);
			}else{
				request.setAttribute("error", "0");
				request.setAttribute("resultado", subaccion);
			}


		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en eliminar encargado Rut: " + rutEncargado);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		request.setAttribute("menu", "administracion");
		
		if(subaccion.equals("del")){
			List<EmpresaVO> listaEmpresas= (List<EmpresaVO>)sesion.getAttribute("empresasLDAP");
			for (Iterator iterator = listaEmpresas.iterator(); iterator
					.hasNext();) {
				EmpresaVO empresaVO = (EmpresaVO) iterator.next();
				if(empresaVO.getRutEmpresa().equals(rutEmpresa)){
					listaEmpresas.remove(empresaVO);
				}
			}
			sesion.setAttribute("empresasLDAP", listaEmpresas);
			sesion.setAttribute("countEmpresasLDAP", listaEmpresas.size());
			forward = mapping.findForward("success");
		}else{
			forward = mapping.findForward("agregar");
		}
		return (forward);

	}
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		Map<String, String> listamap=null;
		
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
		String rutEncargado = request.getParameter("rutEncargado");
		if(accion==null){
			accion="";
		}
		
		if(accion.equals("modificaEncargado")){
			return modificaEncargado(mapping, form, request, response);
		}
		
		if(accion.equals("nuevoEncargado")){
			forward = mapping.findForward("agregar");
			return forward;
		}
		
		if(rutEncargado==null || accion.equals("volver")){
			sesion.setAttribute("rutEncargado", new CotizacionesVO());
			forward = mapping.findForward("success");
			return forward;
		}
		rutEncargado= rutEncargado.replaceAll("\\.", "");
		RutUtil rutentrada= new RutUtil();
		if(!rutentrada.IsRutValido(rutEncargado)){
			request.setAttribute("error", "2");
			forward = mapping.findForward("success");
			return (forward);
		}
		

		String rutEncargado_aux= rutEncargado.substring(0, rutEncargado.length()-2);
		
		logger.info("Ingreso a consulta info encargado " + rutEncargado);

		try {
			User user=null;
			try {
				user = ProxyLDAP.getUser(rutEncargado);
			} catch (Exception e) {
				logger.info("RUT Usuario no registrado en LDAP" + rutEncargado);
			}
			if(user!=null){
				
				logger.info("Consultando Encargado en LDAP");
				listamap=(Map<String, String>)sesion.getAttribute("empresas");
				List<Enterprise> empresasAutorizadas= (List<Enterprise>)ProxyLDAP.getUserEnterprisesAut(rutEncargado, appID, rolID);
				if(empresasAutorizadas!=null){
					CotizacionesVO dataTra= new CotizacionesVO();
					dataTra.setRutTrabajador(Integer.parseInt(rutEncargado.split("-")[0]));
					dataTra.setNombre(user.getFullName());
					sesion.setAttribute("encargado", dataTra);
					List<EmpresaVO> listaEmpresas= new ArrayList();
					for (Iterator iterator = empresasAutorizadas.iterator(); iterator
							.hasNext();) {
						Enterprise enterprise = (Enterprise) iterator.next();
						if(listamap.containsKey(enterprise.getID())){
							EmpresaVO empresa= new EmpresaVO();
							empresa.setRutEmpresa(enterprise.getID());
							empresa.setNombreEmpresa(enterprise.getName());
							listaEmpresas.add(empresa);
						}
					}
					if(listaEmpresas.size()>0){
						sesion.setAttribute("empresasLDAP", listaEmpresas);
						sesion.setAttribute("countEmpresasLDAP", listaEmpresas.size());
						request.setAttribute("error", "0");
					}else{
						request.setAttribute("error", "-3");
					}
				}else{
					request.setAttribute("error", "-1");
				}
			}else{
				request.setAttribute("error", "-1");
			}
			
			
			request.setAttribute("mensaje", rutEncargado);
			//sesion.setAttribute("trabajador", "");
			forward = mapping.findForward("success");		



		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Rut: " + rutEncargado);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		forward = mapping.findForward("success");
		return (forward);

	}
	
	 
//		Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
	    public void enviarMail( String[] mailDestinatarios, String[] mailEncargados, String periodo, String adjunto) {
	    	String subject="";
			try {			
				EnviarMail mail= new EnviarMail("aplica", "aplica");
				StringBuffer body= new  StringBuffer();
				subject= "Informe Cargas Autorizadas y Compensadas La Araucana" ;
				body.append("Estimado: se adjunta archivo con información de cargas compensadas, periodo:" + periodo+ "<BR>");
				body.append("<br><br>");
				body.append("Saluda atte. a Ud. "+"<BR>");
				body.append("La Araucana - Soluciones Sociales.");
				mail.attach(adjunto);
				mail.mailTo("aplica@laaraucana.cl", mailDestinatarios, mailEncargados, null , subject, body.toString());

				System.out.println(".............. EMAIL GENERADO .................... " );

				}catch(Exception e) {
					System.out.println("CAI EN MAIL  " );
					e.printStackTrace();
				}
	 	 }
	    
}
