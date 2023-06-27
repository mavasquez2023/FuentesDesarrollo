package cl.araucana.bienestar.bonificaciones.ui.actions.caso;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetDetallesCasoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
		Logger logger = Logger.getLogger(GetDetallesCasoAction.class);

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;

		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		String target=null;
		
		request.getSession(false).removeAttribute("permiteOcurencias");
	
		Caso caso = (Caso)request.getSession(false).getAttribute("caso");
		ServicesCasosDelegate delegateCaso = new ServicesCasosDelegate();
		caso=delegateCaso.getDetallesCaso(caso); 

		String codigoCobertura=request.getParameter("cobertura");
		if(codigoCobertura==null) codigoCobertura="";
 
		Cobertura cobertura=null; 
		Producto producto=null;
		logger.debug("codigoCobertura='"+codigoCobertura+"'");
		if(!codigoCobertura.equals("") && !codigoCobertura.equals("0")){
			ServicesConveniosDelegate delegateConvenio = new ServicesConveniosDelegate();
			logger.debug("caso.getCodigoConvenio()="+caso.getCodigoConvenio());
			logger.debug("codigoCobertura="+codigoCobertura);
			producto = delegateConvenio.getProducto((long)Long.parseLong(codigoCobertura),caso.getCodigoConvenio());
			cobertura=producto.getCobertura();
			if(cobertura.getTieneOcurrencias().equals(Cobertura.TIENE_OCURRENCIAS_SI)){
				request.getSession(false).setAttribute("permiteOcurencias","yes");
				dynaValidatorActionForm.set("cantidadOcurrencias",String.valueOf(1));
			}
		}

		if (userinformation.hasAccess("casDetAgrega")) {
			opciones.add("Agregar Detalle");
			opcionesValores.add("1");
		}
		if(caso.getDetalleCaso().size()>0){
			if (userinformation.hasAccess("casDetModifica")) {
				opciones.add("Modificar Detalle");
				opcionesValores.add("2");
			}
			if (userinformation.hasAccess("casDetElimina")) {
				opciones.add("Eliminar Detalle");
				opcionesValores.add("3");
			}
		}
		target="listaDetallesCaso";

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("cobertura",cobertura);
		request.getSession(false).setAttribute("producto",producto);
		request.getSession(false).setAttribute("caso",caso);
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
