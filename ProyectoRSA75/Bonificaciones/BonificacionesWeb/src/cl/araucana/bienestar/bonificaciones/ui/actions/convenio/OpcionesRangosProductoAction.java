package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Rango;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesRangosProductoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		Logger logger = Logger.getLogger(OpcionesRangosProductoAction.class);

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;

		Producto producto=(Producto)request.getSession(false).getAttribute("producto");
		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");

		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		int indice=0;
		String index=(String)dynaValidatorActionForm.get("indice");
		Rango rango=new Rango();
		if(index==null) index="";
		if(!index.equals(""))	
		{
			indice=(int)Integer.parseInt(index);			
		}
		else{
			if(opcion!=1 && opcion!=4) opcion=0;

		}
		if(opcion==1 || opcion==2){		
			rango.setRangoFin((double)Double.parseDouble((String)dynaValidatorActionForm.get("finRango")));
			rango.setRangoInicio((double)Double.parseDouble((String)dynaValidatorActionForm.get("inicioRango")));
			rango.setRangoPorcentajeCobertura((double)Double.parseDouble((String)dynaValidatorActionForm.get("porcentajeCobertura")));

			dynaValidatorActionForm.set("inicioRango",String.valueOf((int)rango.getRangoFin()));
			dynaValidatorActionForm.set("finRango","0");
		}		
		String target="listaRangos";;
		logger.debug("opcion final:"+opcion);
		switch(opcion){ 
			case 1:		producto.addRango(rango);	
						break;
			case 2:		producto.removeRango(indice);
						producto.addRango(rango);
						break;
			case 3:		producto.removeRango(indice);		
						break;
			case 4:		ServicesConveniosDelegate delegate=new ServicesConveniosDelegate();
						delegate.actualizaRangoProducto(producto,convenio.getCodigo());		
						target="success";
						break;
			default:	break;
		}

		// Guardar en memoria el resultado
		String referer="/getListaRangos.do?source=convenio";
		request.getSession(false).setAttribute("referer",referer);

		ArrayList listaRangos=producto.getRango();
		request.getSession(false).setAttribute("lista.rangos",listaRangos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);
	}

}
