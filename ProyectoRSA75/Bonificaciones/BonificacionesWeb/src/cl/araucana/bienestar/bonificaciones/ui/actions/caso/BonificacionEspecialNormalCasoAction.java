package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.Evento;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.ParamAporteEspecialVO;
import cl.araucana.common.BusinessException;

/**
 * @version 	1.0
 * @author
 */
public class BonificacionEspecialNormalCasoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;

		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		
		int montoAporte=Integer.parseInt((String)dynaValidatorActionForm.get("montoAporte"));
		int coPagoSocio=Integer.parseInt((String)dynaValidatorActionForm.get("coPagoSocio"));


		
		String observacion=(String)dynaValidatorActionForm.get("observacion");
		Caso caso = (Caso)request.getSession(false).getAttribute("caso");

		long codigoCobertura=0;
		
		//que hermoso :) Gran método que tuve que reutilizar.
		caso = delegate.getDetallesCaso(caso);
		if (caso.getDetalleCaso() != null){
			DetalleCaso detalleCaso = (DetalleCaso) caso.getDetalleCaso().get(0);
			Cobertura cob = detalleCaso.getProducto().getCobertura();
			codigoCobertura = cob.getCodigo();
		}


		if(codigoCobertura == 0)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
			"No se puede obtener el código de la cobertura");
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		
		ParamAporteEspecialVO paramAporteEspecialVO = new ParamAporteEspecialVO();
		paramAporteEspecialVO.setCasoID(caso.getCasoID());
		
		
		
		paramAporteEspecialVO.setCodigoCobertura(codigoCobertura);
		paramAporteEspecialVO.setCoPagoSocio(coPagoSocio);
		paramAporteEspecialVO.setMontoAporte(montoAporte);
				
		Evento evento = new Evento();
		evento.setComentario(observacion);
		evento.setUsuario(userinformation.getUsuario());
		
		delegate.registrarAporteEspecialSinTopeCobertura(paramAporteEspecialVO,evento);
		delegate.actualizarCaso(caso);
		String target="success";
		String referer="/getListaCasos.do";
		request.getSession(false).setAttribute("referer",referer);
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);
	}
}
