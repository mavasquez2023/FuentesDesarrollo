package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesProductoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));

		Producto producto=(Producto)request.getSession(false).getAttribute("producto");
		
		if(opcion==1 || opcion==2){
			if(((String)dynaValidatorActionForm.get("descuento")).trim().equals("")){
				producto.setDescuento(0);
			}
			else{
				producto.setDescuento((double)Double.parseDouble((String)dynaValidatorActionForm.get("descuento")));
			}
			producto.setPorcentajeAporteConvenio(Integer.parseInt((String)dynaValidatorActionForm.get("porcentajeAporteConvenio")));
		}
		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");
		String target=null;
		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
 
		switch(opcion){ 
			case 1:		delegate.registraProducto(producto,convenio.getCodigo());	
						target="success";
						break;
			case 2:		delegate.actualizaProducto(producto,convenio.getCodigo());		
						target="success";
						break;
			case 3:		delegate.eliminaProducto(producto,convenio.getCodigo());		
						target="success";
						break;
			default:	target="noservice";
						break;
		}

		// Guardar en memoria el resultado
		String referer="/getListaCoberturasConvenio.do?source=convenio";
		request.getSession(false).setAttribute("referer",referer);
		request.getSession(false).setAttribute("convenio",convenio);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);

	}
}
