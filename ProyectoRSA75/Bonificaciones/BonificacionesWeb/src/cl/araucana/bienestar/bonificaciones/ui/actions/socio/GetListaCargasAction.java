package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCargasAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
										
		String target="listaCargas";
		
		String source=request.getParameter("source");
		if(source==null) source="";
		Carga carga = new Carga();
		carga.setRutCarga((String)dynaValidatorActionForm.get("rutCarga"));
		carga.setNombreCarga((String)dynaValidatorActionForm.get("nombreCarga"));
		carga.setRutSocio((String)dynaValidatorActionForm.get("rutSocio"));
		carga.setNombreSocio((String)dynaValidatorActionForm.get("nombreSocio"));
		carga.setTipoCarga((String)dynaValidatorActionForm.get("tipoCarga"));

		if(source.equals("caso")){
			Caso caso=(Caso)request.getSession(false).getAttribute("caso");
			target="listaCargasCaso";
			carga.setRutSocio(caso.getRutSocio());
		}

		ServicesSociosDelegate delegate = new ServicesSociosDelegate();
		ArrayList lista = delegate.getListaCargas(carga);

		
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.cargas",lista);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
