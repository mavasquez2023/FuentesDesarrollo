package cl.laaraucana.satelites.certificados.compTotal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.core.util.UserPrincipal;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.certificados.finiquito.utils.FiniquitoLocalUtil;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtil;

/**
 * @version 	1.0
 * @author
 */
public class ListadoCreditosAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
    	
	    logger.debug("<< Ingreso a Compromiso total");
		HttpSession sesion = request.getSession();
		String rutAConsultar = null;
		
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
		String uc = request.getParameter("uc");
		
		if(usuario!=null){
			rutAConsultar = usuario.getRut()+"-"+usuario.getDv();
		}else
		if(uc!=null){
			UserPrincipal newUser = UserPrincipal.decodeUserCredentials(uc);
			rutAConsultar= newUser.getName();
		}
		
		if (rutAConsultar==null) {
			request.setAttribute("title", "Error: ");
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}
		
				
		//UsuarioAfiliadoVO user = null;
		
		//matar este if para certificados CRM
		//Si es empresa, solicita ingresar rut empleado y valida si pertenece a empresa
//		if(usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()){
/*		if(false){
			if(request.getParameter("rutEmpleado") == null){
				//Redireccionar a pagina de seleccion empleado nuevamente
				return mapping.findForward("seleccionEmpresa");
			}else{
				rutAConsultar = request.getParameter("rutEmpleado").replace(".", "");
				//Consultar si el rut ingresado corresponde a empresa
				FiniquitoLocalUtil finiquitoUtil = new FiniquitoLocalUtil();
				user = UsuarioServiceUtil.obtenerAfiliado(rutAConsultar);
				DetalleEmpresaAfiliado detalleEmpresa = finiquitoUtil.obtenerDetalleEmpresa(usuario.getRut()+"-"+usuario.getDv(), user);
				if(detalleEmpresa == null){
					request.setAttribute("error","El rut del afiliado ingresado no pertenece a la empresa");
					return mapping.findForward("seleccionEmpresa");
				}				
			}
		}*/

		sesion.setAttribute("rutEmpleado",rutAConsultar);
		
		//response.sendRedirect(request.getServletContext().getInitParameter("compTotalURL") + "?rut=" + rutAConsultar);
		response.sendRedirect(ServiciosConst.URL_COMPTOTAL + "?rut=" + rutAConsultar);
		
		return null;
    }
}
