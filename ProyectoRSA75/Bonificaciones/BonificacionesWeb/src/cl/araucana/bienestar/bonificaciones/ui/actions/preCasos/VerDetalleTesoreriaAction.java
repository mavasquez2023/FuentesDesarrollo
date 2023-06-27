package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author		Alejandro Sepúlveda
 */
public class VerDetalleTesoreriaAction extends Action{
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();

		String target=null;
		
		String idCaso=request.getParameter("casoId");
		
		long casoId=0;
		if(idCaso!=null)
			casoId=Long.valueOf(idCaso).longValue();
		
		request.getSession(false).setAttribute("caso",casosDelegate.getCasoVO(casoId));
		
		ArrayList listaMovimientosTesoreria = casosDelegate.getMovimientosTesoreriaPreCaso(casoId);
		
		request.getSession(false).setAttribute("listaMovimientosTesoreria",listaMovimientosTesoreria);		

		target="datosTesoreria";

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}	

}
