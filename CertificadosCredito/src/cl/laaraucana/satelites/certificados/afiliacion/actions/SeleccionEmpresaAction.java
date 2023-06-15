package cl.laaraucana.satelites.certificados.afiliacion.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtilSinAS400;

/**
 * @version 1.0
 * @author
 */

public class SeleccionEmpresaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("datosUsuario") == null) {
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}

		UsuarioVO user = (UsuarioVO) sesion.getAttribute("datosUsuario");
		String rut = user.getRut() + "-" + user.getDv();
		String nombre = (String) sesion.getAttribute("nombre");
		if (nombre==null){
			sesion.setAttribute("rut", rut);
			sesion.setAttribute("nombre", user.getNombre());
			sesion.setAttribute("nombreAfiliado",  user.getNombre());
		}
		String uc = request.getParameter("uc");
		if(uc!=null){
			sesion.setAttribute("uc", uc);
		}
		//sesion.setAttribute("datosUsuario", user);
		
		if(user!=null){
			//matar este if para certificados CRM	
			if (user.isEsEmpresa() || user.isEsEmpresaPublica()) {
				return mapping.findForward("ingresaRut");

			}
		}
		//-----
		
		UsuarioAfiliadoVO afiliadoCRM= (UsuarioAfiliadoVO)UsuarioServiceUtilSinAS400.obtenerAfiliado(rut);

		if (afiliadoCRM.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			logger.debug("codigo de error igual a " + ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			sesion.setAttribute("afiliadoCRM", afiliadoCRM);
			sesion.setAttribute("empresasList", afiliadoCRM.getDetalleEmpresaList());
		}else {
			// BP no encontrado o falla en el servicio
			logger.error("codigo de error distinto de 0 cause:" + afiliadoCRM.getMensaje());
			return new ActionForward(mapping.findForward("error").getPath() + "?errorMsg=" + afiliadoCRM.getMensaje());
		}

		
		String rol= (String)request.getParameter("rol");
		logger.info("Rol del request: " + rol);
		List<String> listaRoles= afiliadoCRM.getListaRoles();
		logger.info("lista de roles de CRM: " + listaRoles);
		
		if (afiliadoCRM.getDetalleEmpresaList().isEmpty() && listaRoles.contains("Trabajador")) {
			// Cuando no devuelve ninguna empresa para afiliado
			logger.warn("lista de empresas vacía para Rut: " + rut);
			return new ActionForward(mapping.findForward("error").getPath() + "?errorMsg=el rut no tiene asociada ninguna empresa.");
		} else {

			//listaRoles.add("Afiliado");
			if(rol== null){
				if(listaRoles.size()>1){
					sesion.setAttribute("rolesList", listaRoles);
					return mapping.findForward("seleccionRol");
				}else{
					rol= listaRoles.get(0);	
				}
			}
			logger.info("Se setea en sesión rol: " + rol);
			sesion.setAttribute("rol", rol);
			if(rol.equals("Trabajador")){
				logger.info("Para rol Trabajador se redirecciona a seleccionar empresa ");
				return mapping.findForward("seleccionEmpresa");
			}else{
				request.setAttribute("error", "0");
				//return mapping.findForward("certificadoPensionado");
				logger.info("Para Pensionado o Independiente se redirecciona a Generer certificado");
				return mapping.findForward("send");
			}
		} 

	}

}
