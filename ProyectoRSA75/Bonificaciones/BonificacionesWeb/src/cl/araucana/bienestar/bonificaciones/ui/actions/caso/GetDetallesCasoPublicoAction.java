package cl.araucana.bienestar.bonificaciones.ui.actions.caso;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetDetallesCasoPublicoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(GetDetallesCasoPublicoAction.class);
		logger.debug("Entro a detalle caso Publico");
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
		

		String target=null;
		ServicesSociosDelegate delegateSocio=new ServicesSociosDelegate();
		ServicesConveniosDelegate delegateConvenio=new ServicesConveniosDelegate();
		ServicesCasosDelegate delegateCaso=new ServicesCasosDelegate();
		
		String codigo=request.getParameter("codigo");
		
		if(codigo!= null && !codigo.equals("")){	
			logger.debug("ID Caso a buscar : " + codigo);
			Caso caso=delegateCaso.getCasoVO((long)Long.parseLong(codigo));
			caso=delegateCaso.getCasoVO((long)Long.parseLong(codigo));
			
			Socio socio=delegateSocio.getSocio(caso.getRutSocio());
			request.getSession(false).setAttribute("socio",socio);
			
			Convenio conv=delegateConvenio.getConvenio(caso.getCodigoConvenio());
			request.getSession(false).setAttribute("convenio",conv);
			
			caso=delegateCaso.getDetallesCaso(caso); 
			logger.debug("Cantidad de Detalles : " + caso.getDetalleCaso().size());
			target="listaDetallesCaso";
	
			request.getSession(false).setAttribute("caso",caso);
	
		}else{
			
		}
		
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
