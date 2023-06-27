package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class PrepareUpLoadFileAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(PrepareUpLoadFileAction.class);

		String codigoConvenio=request.getParameter("codigoConvenio");
		String codigoProducto=request.getParameter("codigoProducto");
		
		long codConvenio = 0;
		long codProducto = 0;
		
		if(codigoConvenio!=null)
			codConvenio=Long.parseLong(codigoConvenio);
		
		if(codigoProducto!=null)
			codProducto=Long.parseLong(codigoProducto);
		
		Producto producto = new Producto();
		Convenio convenio = new Convenio();
		
		logger.debug("codigoConvenio: "+codConvenio);
		logger.debug("codProducto: "+codProducto);
		
		ServicesConveniosDelegate delegateConvenio = new ServicesConveniosDelegate();	
					
		if(codConvenio>0) {
			convenio = delegateConvenio.getConvenio(codConvenio);
			request.getSession(false).setAttribute("convenio",convenio);
			if(codProducto>0) {
				producto = delegateConvenio.getProducto(codProducto,convenio.getCodigo());
				request.getSession(false).setAttribute("producto",producto);
			}else {
				request.getSession(false).removeAttribute("producto");
			}
			request.getSession(false).setAttribute("buscarCobertura","true");
		}
		else {
			request.getSession(false).removeAttribute("convenio");
			request.getSession(false).removeAttribute("producto");
			request.getSession(false).removeAttribute("buscarCobertura");
		}

		String target="upLoadFile";
		
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
