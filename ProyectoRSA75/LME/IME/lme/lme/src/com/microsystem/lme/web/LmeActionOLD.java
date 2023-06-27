/*
 * Created on 11-10-2011
 *
 */
package com.microsystem.lme.web;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.microsystem.lme.job.LmeJob;
import com.microsystem.lme.trigger.LmeTrigger;

/**
 * @author microsystem
 * 
 */
public class LmeActionOLD extends DispatchAction {
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
			MessageResources m = getResources(request);
			LmeJob.setProperties(m);
			LmeTrigger.getInstance().start();

		} catch (Throwable e) {
			log.error(e.getClass() + "; " + e.getMessage());
		}

		//		LmeTask.setProperties(getResources(request));
		//		LmeTask.getInstance().start();
		//		status(mapping, form, request, response);

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
		LmeTrigger.getInstance().stop();

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
		boolean b = LmeTrigger.getInstance().getStatus();
		//		boolean b =  LmeTask.getInstance().getStatus();
		String msg = b ? "PROCESO LME INICIADO" : "PROCESO LME DETENIDO";
		String data = "version:\"LME version={0}\", value:{1}, message:\"{2}\"";
		response.getWriter().write("{" + MessageFormat.format(data, new Object[] { version, String.valueOf(b), msg }) + "}");
	}

}
