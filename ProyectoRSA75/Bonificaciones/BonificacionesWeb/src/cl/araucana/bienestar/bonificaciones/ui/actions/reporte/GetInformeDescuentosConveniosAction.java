package cl.araucana.bienestar.bonificaciones.ui.actions.reporte;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosConveniosVO;

/**
 * @version 	1.0
 * @author
 */
public class GetInformeDescuentosConveniosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
								
		String codigoDescuento=request.getParameter("codigo");
		int codDscto=0;
		if(codigoDescuento==null) codigoDescuento="";
		if(!codigoDescuento.equals(""))
			codDscto=Integer.parseInt(codigoDescuento);
		
		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		
		InformeDescuentosConveniosVO reporte = delegate.getInformeDescuentosConvenios(codDscto);
								
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("reporte",reporte);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("informeDescuentosConvenios");
		return (forward);

	}
}
