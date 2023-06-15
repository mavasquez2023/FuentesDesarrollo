package cl.laaraucana.compromisototal.compTotal.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.util.UserPrincipal;
import cl.laaraucana.compromisototal.compTotal.forms.DatosCompromiso;
import cl.laaraucana.compromisototal.utils.RutUtil;
import cl.laaraucana.compromisototal.utils.Utils;

/**
 * @version 1.0
 * @author
 */
public class ListadoContratosAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {

			HttpSession sesion = request.getSession();
			DatosCompromiso form = (DatosCompromiso) _form;
			// Obtener el rut de la sesion
			//String rutEmpleado = (String) sesion.getAttribute("rutEmpleado");
			String rutEmpleado = form.getRut();
			String rutparam = request.getParameter("rut");
			String uc = request.getParameter("uc");
			if(uc!=null){
				sesion.setAttribute("uc", uc);
			}
			
			if(rutEmpleado ==null){
				if(uc!=null){
					UserPrincipal newUser = UserPrincipal.decodeUserCredentials(uc);
					rutEmpleado= newUser.getName();
				}
			}
			
			logger.debug("Entro a listadoAction");
			Date date = new Date();
			request.setAttribute("fechaEmision", date);

			if (rutEmpleado == null) {
				logger.debug("Sesion no valida");
				throw new Exception("Se ha terminado la sesión");
				
			} else if (RutUtil.IsRutValido(rutEmpleado.toUpperCase())) {
				String rut = rutEmpleado.toUpperCase();
				sesion.setAttribute("listaCompletaConsumo", null);
				sesion.setAttribute("listaIntercaja", null);
				sesion.setAttribute("listaAsicom", null);

				logger.debug("rut Valido " + rut);
				logger.debug("fecha " + date);
				
				sesion.setAttribute("rut", rut);
				request.setAttribute("rut", rut);
				request.setAttribute("error", "rutValido");
				forward = mapping.findForward("success");
			} else {
				request.setAttribute("rut", "");
				request.setAttribute("error", "rutNoValido");
				logger.debug("Salio de listadoAcion fallido:");
				forward = mapping.findForward("success");
			}

		} catch (Exception e) {
			// logea la excepcion y la envia a la pagina de errores.
			forward = Utils.returnErrorForward(mapping, e);
			logger.debug(" StackTrace: " + e.getStackTrace());

		}

		return (forward);
	}

}
