package cl.araucana.bienestar.bonificaciones.ui.actions.reporte;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosConveniosVO;

/**
 * @version 	1.0
 * @author
 */
public class GetDetalleDescuentosConvenioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

								
		String codigoDescuento=request.getParameter("codigo");
		String codigoConvenio=request.getParameter("codigoConvenio");
		int codDscto=0;
		int codConvenio=0;
		if(codigoDescuento==null) codigoDescuento="";
		if(!codigoDescuento.equals(""))
			codDscto=Integer.parseInt(codigoDescuento);
		if(codigoConvenio==null) codigoConvenio="";
		if(!codigoConvenio.equals(""))
			codConvenio=Integer.parseInt(codigoConvenio);		
			
		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
				
		InformeDescuentosConveniosVO reporte = delegate.getInformeDescuentosConveniosByConvenio(codConvenio,codDscto);
		
		DetalleDescuentosConveniosVO detalleDescuentosConvenios = delegate.getDetalleDescuentosConvenio((DetalleDescuentosConveniosVO)(reporte.getDetalleConvenios().get(0)),(long) codDscto);
						
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("reporte",detalleDescuentosConvenios);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("detalleDescuentosConvenio");
		return (forward);

	}
}
