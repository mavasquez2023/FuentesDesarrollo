package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCasosConvenioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");	
		String target=null;
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();

		ArrayList listaCasos=null;
		Caso caso=new Caso();
		caso.setCodigoConvenio(convenio.getCodigo());
		caso.setEstado("");
		listaCasos=delegate.getListaCasos(caso);
		target="listaCasosConvenio";
		
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.casos",listaCasos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
