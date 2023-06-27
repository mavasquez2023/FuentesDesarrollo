package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

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
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesDetallesCasoAction extends Action {
	Logger logger = Logger.getLogger(OpcionesDetallesCasoAction.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;

		Caso caso=(Caso)request.getSession(false).getAttribute("caso");
		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		int indice=0;
		String index=(String)dynaValidatorActionForm.get("indice");
		DetalleCaso detalle=new DetalleCaso();
		Producto prod=new Producto();
		Cobertura cobertura=(Cobertura)request.getSession(false).getAttribute("cobertura");
		prod.setCobertura(cobertura);
		detalle.setProducto(prod);
		if(index==null) index="";
		if(!index.equals(""))	 
		{
			indice=(int)Integer.parseInt(index);			
		}
		else{
			if(opcion!=1) opcion=0;
		}

		if(opcion==1 || opcion==2){
			if(((String)dynaValidatorActionForm.get("valorPrestacion")).trim().equals("")){
				detalle.setMonto(0);
			}
			else{
				detalle.setMonto((int)Integer.parseInt((String)dynaValidatorActionForm.get("valorPrestacion")));
			}
			if(((String)dynaValidatorActionForm.get("descuento")).trim().equals("")){
				detalle.setMontoDescuento(0);
			}
			else{
				detalle.setMontoDescuento((int)Integer.parseInt((String)dynaValidatorActionForm.get("descuento")));
			}
			if(((String)dynaValidatorActionForm.get("aporteIsapre")).trim().equals("")){
				detalle.setAporteIsapre(0);
			}
			else{
				detalle.setAporteIsapre((int)Integer.parseInt((String)dynaValidatorActionForm.get("aporteIsapre")));
			}
			if(((String)dynaValidatorActionForm.get("cantidadOcurrencias")).trim().equals("")){
				detalle.setCantidadOcurencias(1);
			}
			else{
				detalle.setCantidadOcurencias((int)Integer.parseInt((String)dynaValidatorActionForm.get("cantidadOcurrencias")));
			}			
			detalle.setDocumento((String)dynaValidatorActionForm.get("documento"));
		}
		
		logger.debug("detalle.getAporteIsapre()="+detalle.getAporteIsapre());
		logger.debug("detalle.getAporteBienestar()="+detalle.getAporteBienestar());
		logger.debug("detalle.getAporteSocio()="+detalle.getAporteSocio());
		logger.debug("detalle.getMonto()="+detalle.getMonto());
		logger.debug("detalle.getMontoDescuento()="+detalle.getMontoDescuento());
		logger.debug("detalle.getTotal()="+detalle.getTotal());
		logger.debug("detalle.getDocumento()="+detalle.getDocumento());
		logger.debug("detalle.getCantidadOcurencias()="+detalle.getCantidadOcurencias());
		String target="listaDetallesCaso";
		ServicesCasosDelegate delegate=new ServicesCasosDelegate();
		switch(opcion){
			case 1:		caso=delegate.registraDetalle(caso,detalle);	
						break;
			case 2:		caso=delegate.actualizaDetalle(caso,indice,detalle);
						break;
			case 3:		caso=delegate.eliminaDetalle(caso,indice);		
						break;
			default:	break;
		}

		dynaValidatorActionForm.set("indice","");
		dynaValidatorActionForm.set("cobertura",new Long(0));
		dynaValidatorActionForm.set("valorPrestacion","");
		dynaValidatorActionForm.set("descuento","");
		dynaValidatorActionForm.set("aporteIsapre","");
		dynaValidatorActionForm.set("documento","");
		dynaValidatorActionForm.set("cantidadOcurrencias","");
		// Guardar en memoria el resultado
		String referer="/getDetallesCaso.do";
		request.getSession(false).setAttribute("referer",referer);
		request.getSession(false).setAttribute("caso",caso);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);


	}
}
