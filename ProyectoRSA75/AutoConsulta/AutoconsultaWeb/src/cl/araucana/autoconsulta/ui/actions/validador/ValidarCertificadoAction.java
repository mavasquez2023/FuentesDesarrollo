package cl.araucana.autoconsulta.ui.actions.validador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.DatosValidacionVO;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class ValidarCertificadoAction extends Action {
	
	private static Logger logger = Logger.getLogger(ValidarCertificadoAction.class);
	
	public static final String GLOBAL_FORWARD_ingresoDatos = "ingresoDatos";
	public static final String GLOBAL_FORWARD_showDatos = "showDatos";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession(true);
		DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
		String target=null;
		String textIdCertificado = null;
		long idCertificado=0;

		textIdCertificado =(String)daf.get("id");
		
		if(textIdCertificado!=null && textIdCertificado.length()>0) {
			idCertificado=Long.parseLong(textIdCertificado);
			DatosValidacionVO datosValidacion = delegate.getDatosValidacionCertificado(idCertificado);
			if(datosValidacion!=null){
				session.removeAttribute("validation.message");
				session.setAttribute("datosValidacion",datosValidacion);
				target=GLOBAL_FORWARD_showDatos;
			}
			else {
				session.setAttribute("validation.message","errors.validation.idCertificadoInvalido");
				target=GLOBAL_FORWARD_ingresoDatos;
			}
		}
		else {
			session.removeAttribute("validation.message");
			target=GLOBAL_FORWARD_ingresoDatos;
		}
		
		logger.debug("Envía a: "+target);
		return mapping.findForward(target);
	}
}
