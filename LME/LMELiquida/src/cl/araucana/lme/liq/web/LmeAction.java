/*
 * Created on 11-10-2011
 *
 */
package cl.araucana.lme.liq.web;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import cl.araucana.lme.liq.ibatis.domain.ParametroVO;
import cl.araucana.lme.liq.job.LmeInicio;
import cl.araucana.lme.liq.svc.IAS400Svc_LIQ;
import cl.araucana.lme.liq.svc.SvcFactory_LIQ;
import cl.araucana.lme.liq.svc.exception.SvcException;


/**
 * @author j-factory
 * 
 */
public class LmeAction extends DispatchAction {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public void start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
						
			LmeInicio.setIniciado(1);
			LmeInicio.IniciarProcesoCompleto();
		} catch (Throwable e) {
			log.error(e.getClass() + "; " + e.getMessage());
		}

		status(mapping, form, request, response);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void stop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//		LmeTask.getInstance().stop();
		//LmeTrigger.getInstance().stop();
		try {
			/*IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();
			ParametroVO param = new ParametroVO();
			param.setPar_nombre("ULTIMO_ESTADO_JOB");
			param.setPar_valor("0");
			svc_a.updateParametro(param);
			*/
			LmeInicio.setIniciado(0);
		} catch (Exception e) {
			log.error(e.getClass() + "; " + e.getMessage());
		}
		status(mapping, form, request, response);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void status(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String version = getResources(request).getMessage("version");
		//boolean b = LmeTrigger.getInstance().getStatus();
		//		boolean b =  LmeTask.getInstance().getStatus();
		boolean b;
		
		if(LmeInicio.getIniciado() == 1){
			b = true;
		}else{
			b = false;
		}
		String msg = b ? "PROCESO LME LIQ INICIADO" : "PROCESO LME LIQ DETENIDO";
		String data = "version:\"LME version={0}\", value:{1}, message:\"{2}\"";
		response.getWriter().write("{" + MessageFormat.format(data, new Object[] { version, String.valueOf(b), msg }) + "}");
	}

}
