package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosSocioVO;

/**
 * @version 	1.0
 * @author
 */
public class GetDetalleDescuentosAplicadosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

								
		String codigo=request.getParameter("codigo");
		Socio socio=(Socio)request.getSession(false).getAttribute("socio");	
		int cod=0;
		if(codigo==null) codigo="";
		if(!codigo.equals("")){
			cod=Integer.parseInt(codigo);
		}
		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		DetalleDescuentosSocioVO detalle = delegate.getDetalleDescuentoSocio(socio.getRut(),cod);
							
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("reporte",detalle);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("detalleDescuentos");
		return (forward);

	}
}
