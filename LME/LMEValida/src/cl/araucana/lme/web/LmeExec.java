/*
 * Created on 27-11-2011
 *
 */
package cl.araucana.lme.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.InitConexion_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.util.Util;


/**
 * @author j-factory
 *
 */
public class LmeExec extends DispatchAction {

	private static Logger logger = Logger.getLogger(LmeExec.class);
	private IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();
	private SimpleDateFormat shf = new SimpleDateFormat("HHmmss");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	

	private void setResponse(HttpServletResponse response, String str) throws IOException {
		//response.setContentType(Constants.CONTENT_TYPE_JSON);
		response.getWriter().write(str);
		//System.out.println("la res es: " + str);
	}
}
