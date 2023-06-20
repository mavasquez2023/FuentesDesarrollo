/*
 * Created on 21-11-2011
 *
 */
package cl.araucana.lme.web;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import org.json.simple.JSONValue;

import cl.araucana.lme.ibatis.domain.EstadisticTO;
import cl.araucana.lme.ibatis.domain.IlfeOpeVO;
import cl.araucana.lme.ibatis.domain.UrlBorderVO;
import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.InitConexion_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.svc.exception.SvcException;
import cl.araucana.lme.util.Constants;
import cl.araucana.lme.util.EndPointUtil;
import cl.araucana.lme.util.Util;


import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.lme.ws.cliente.operador.excepciones.ErrorRespuestaOperadorException;
import conector.vo.SalidaLMEDetLcc;

/**
 * @author j-factory
 *
 */
public class LmeQuery extends DispatchAction {
	private static Logger logger = Logger.getLogger(LmeQuery.class);

	private static final String SVC_NAME = "DETALLE";
	private IAS400Svc_LME svc_a = null;
	private SimpleDateFormat formatoPeriodo = new SimpleDateFormat("yyyyMM");
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception
	{
		ActionForward forward = new ActionForward();
		
		svc_a = SvcFactory_LME.getAS400Svc_LME();
		
		String periodo = request.getParameter("periodo");
		if(periodo==null || periodo.equals("")){
			periodo=getFechaActual();
		}
		List l=new ArrayList();
		try {
			l = svc_a.getEstadisticas(Integer.parseInt(periodo));
		} catch (SvcException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		request.setAttribute("time", "false");
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			EstadisticTO est = (EstadisticTO) iter.next();
			est.setDateEvento(formatFecha(est.getFechaEvento()));
			if(periodo.length()==8){
				request.setAttribute("time", "true");
				est.setTimeEvento(formatHora(est.getHoraEvento()));
			}
		}
		//InitConexion_LME.closeConexion_LME();

		request.setAttribute("estadistica", l);
		request.setAttribute("role", "admin");
		request.setAttribute("periodo", periodo);
		forward = mapping.findForward("OK");

		return forward;
	}
	
	public String getFechaActual() throws Exception {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date());
		return formatoPeriodo.format(calendario.getTime());
	}

	public String formatFecha(String fecha){
		String salida= fecha.substring(0, 4) + "-" + fecha.substring(4, 6) + "-" +fecha.substring(6);
		return salida;
	}
	public String formatHora(String hora){
		String salida= hora.substring(0, 2) + ":" + hora.substring(2, 4) + ":" +hora.substring(4);
		return salida;
	}
}
