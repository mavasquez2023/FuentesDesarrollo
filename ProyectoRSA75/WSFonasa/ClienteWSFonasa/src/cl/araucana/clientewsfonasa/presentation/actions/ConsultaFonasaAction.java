package cl.araucana.clientewsfonasa.presentation.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import cl.araucana.clientewsfonasa.business.services.CallWSFonasaService;
import cl.araucana.clientewsfonasa.business.services.LogWSFonasaService;
import cl.araucana.clientewsfonasa.business.services.impl.CallWSFonasaServiceImpl;
import cl.araucana.clientewsfonasa.business.services.impl.LogWSFonasaServiceImpl;
import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.LogWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.RequestWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO;
import cl.araucana.clientewsfonasa.common.enum.EstadoCallWSFonasaEnum;
import cl.araucana.clientewsfonasa.common.enum.TipoCallWSFonasaEnum;
import cl.araucana.clientewsfonasa.common.enum.TipoLogWSFonasaEnum;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;
import cl.araucana.clientewsfonasa.presentation.forms.ConsultaFonasaForm;

public class ConsultaFonasaAction extends MappingDispatchAction{
	
	private final String FORWARD_SUCCESS = "SUCCESS";
	private final String FORWARD_ERROR = "ERROR";
	
	public ActionForward formularioConsulta (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {

      return mapping.findForward(FORWARD_SUCCESS);
	}
	
	public ActionForward consultarFonasa (ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		ConsultaFonasaForm consFonaForm = (ConsultaFonasaForm)form;
		
//		Inicia los servicios que se ocuparan en la consulta
		CallWSFonasaService callServ = new CallWSFonasaServiceImpl();
		LogWSFonasaService logServ = new LogWSFonasaServiceImpl();
		
//		Inicializa la llamada al servicio web de fonasa
		RequestWSFonasaTO reqTo = new RequestWSFonasaTO(consFonaForm.getRut());
		CallWSFonasaTO callTo = new CallWSFonasaTO(new Date(), 
				EstadoCallWSFonasaEnum.NO_PROCESADO.getCodEstado(), 
				TipoCallWSFonasaEnum.WEB.getCodTipo(), reqTo);
		try {
//			Registra la llamada al servicio web de fonasa
			callServ.saveCallWSFona(callTo);
			logServ.saveLogWSFona(new LogWSFonasaTO(null, 
					callTo.getIdCall(), new Date(), 
					TipoLogWSFonasaEnum.REGISTRO_CONSULTA_WEB.getCodLog(), 
					TipoLogWSFonasaEnum.REGISTRO_CONSULTA_WEB.getDescLog()));
		} catch (DaoException e) {
			e.printStackTrace();
//			Despliega pantalla de error
			return mapping.findForward(FORWARD_ERROR);
		} catch (ServiceException e) {
			e.printStackTrace();
//			Despliega pantalla de error
			return mapping.findForward(FORWARD_ERROR);
		}
		
		ResponseWSFonasaTO resTo = null;
		try {
			resTo = callServ.consultarRutFonasa(callTo);
			logServ.saveLogWSFona(new LogWSFonasaTO(null, 
					callTo.getIdCall(), new Date(), 
					TipoLogWSFonasaEnum.MOSTRAR_RESULTADO_POR_WEB.getCodLog(), 
					TipoLogWSFonasaEnum.MOSTRAR_RESULTADO_POR_WEB.getDescLog()));
		} catch (ServiceException e) {
			e.printStackTrace();
			return mapping.findForward(FORWARD_ERROR);
		} catch (DaoException e) {
			e.printStackTrace();
			return mapping.findForward(FORWARD_ERROR);
		}
		
		request.setAttribute("resWSFonasaTo", resTo);
		return mapping.findForward(FORWARD_SUCCESS);
	}
}
