
package cl.laaraucana.satelites.certificados.deuda.actions;

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
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.deuda.forms.CertificadoDeudaForm;
import cl.laaraucana.satelites.certificados.deuda.forms.ListadoCreditosForm;

/**
 * @version 	1.0
 * @author
 */
public class ListadoCreditosAction extends Action

{
	protected Logger logger1 = Logger.getLogger(this.getClass());
	
	private final static String SUCCESS = "success";
	
    public ActionForward execute(ActionMapping mapping, ActionForm _form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	ActionForward forward = new ActionForward(); // return value
	ListadoCreditosForm form = (ListadoCreditosForm) _form;
	HttpSession sesion = request.getSession();
	UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
	String fecha= form.getFechaAdmisibilidad();
	if (fecha==null || fecha.equals("")){
		fecha= Utils.fechaWeb();
	}
	sesion.setAttribute("fechaAdmisibilidad", fecha);
	
	if (usuario == null) {
		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
		request.setAttribute("message", Constants.SESION_EXPIRED);
		return mapping.findForward("customError");
	}

	forward = mapping.findForward(SUCCESS);
	// Finish with
	return (forward);

    }
}
