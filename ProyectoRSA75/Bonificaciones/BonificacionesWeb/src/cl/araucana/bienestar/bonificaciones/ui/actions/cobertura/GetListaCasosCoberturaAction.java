package cl.araucana.bienestar.bonificaciones.ui.actions.cobertura;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCasosCoberturaAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Cobertura cobertura=(Cobertura)request.getSession(false).getAttribute("cobertura");	
		String target=null;
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();

		ArrayList listaCasos=null;
		listaCasos=delegate.getListaCasosCobertura(cobertura.getCodigo());
		target="listaCasosCobertura";
	
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.casos",listaCasos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
