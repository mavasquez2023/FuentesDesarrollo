/*
 * Created on 21-11-2011
 *
 */
package com.microsystem.lme.web;

import java.io.IOException;
import java.math.BigInteger;
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
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import org.json.simple.JSONValue;

import com.microsystem.lme.ibatis.domain.IlfeOpeVO;
import com.microsystem.lme.ibatis.domain.UrlBorderVO;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.InitConexion_LME;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.Constants;
import com.microsystem.lme.util.EndPointUtil;
import com.microsystem.lme.util.Util;

import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.lme.ws.cliente.operador.excepciones.ErrorRespuestaOperadorException;
import conector.vo.SalidaLMEDetLcc;

/**
 * @author microsystem
 *
 */
public class LmeQuery extends DispatchAction {
	private static Logger logger = Logger.getLogger(LmeQuery.class);

	private static final String SVC_NAME = "DETALLE";
	private IAS400Svc_LME svc_a = null;

	public void getOpeVordel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		svc_a = SvcFactory_LME.getAS400Svc_LME();

		UrlBorderVO vo = new UrlBorderVO();
		vo.setNombreServicio(SVC_NAME);
		List l = svc_a.getOpeVordel(vo);

		List list = new LinkedList();
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			UrlBorderVO o = (UrlBorderVO) iter.next();
			Map h = new HashMap();
			h.put("codOpe", o.getCodOpe());
			h.put("nomOpe", o.getNombreServicio().trim());

			list.add(h);
		}
		InitConexion_LME.closeConexion_LME();
		String jsonString = JSONValue.toJSONString(list);
		response.setContentType(Constants.CONTENT_TYPE_JSON);
		response.getWriter().write(jsonString);

	}

	public void getLMEDetLcc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//INIT
		logger.info("INICIO DEL PROCESO PARTICULAR : 'CONSULTA DEL DETALLE POR LICENCIA' [" + (new Date()) + "]");

		MessageResources m = getResources(request);
		boolean logDetail = Boolean.valueOf(m.getMessage("logDetail")).booleanValue();
		if (m.getMessage("http.proxy").equals("yes")) {
			System.getProperties().put("http.proxyHost", m.getMessage("http.proxyHost"));
			System.getProperties().put("http.proxyPort", m.getMessage("http.proxyPort"));
		}

		String res = null;
		String codOpe = request.getParameter("codOpe");
		String lic = request.getParameter("lic");

		try {
			IlfeOpeVO o = getIlfeOpe(codOpe);

			String codCcaf = o.getCodCcaf().trim();
			String pwdCcaf = o.getPwdCcaf().trim();
			//String urlOpe = getUrlVordel(codOpe, SVC_NAME);
			String nombreHash = "DETALLE";
			/*********************************************/

			String url1 = null;
			Boolean error1 = EndPointUtil.getInstance().getEstadoError(codOpe, "1");
			if (error1 != null && error1 == Boolean.FALSE) {
				//    			System.out.println("No tiene error en la prioridad 1");
				url1 = EndPointUtil.getInstance().getEndPoint(codOpe, nombreHash, "1");
			}

			String url2 = null;
			Boolean error2 = EndPointUtil.getInstance().getEstadoError(codOpe, "2");
			if (error2 != null && error2 == Boolean.FALSE) {
				//    			System.out.println("No tiene error en la prioridad 2");
				url2 = EndPointUtil.getInstance().getEndPoint(codOpe, nombreHash, "2");
			}

			/************************************************/

			BigInteger numLicencia = new BigInteger(lic);
			String digLicencia = Util.dv(numLicencia);

			ServiciosMultiOperador servicio = new ServiciosMultiOperador(codOpe, Constants.TIPO_INSTITUCION, codCcaf, pwdCcaf, url1, url2);
			//RespuestaDetalleLicencia respuesta = servicio.consultaDetalleLicencia(numLicencia, digLicencia);
			SalidaLMEDetLcc respuesta = servicio.consultaDetalleLicencia2(numLicencia, digLicencia);

			/*System.out.println("Zona Cero");
			System.out.println(respuesta.getZona0().toString());
			System.out.println("Zona A");
			System.out.println(respuesta.getZonaA().toString());
			System.out.println("Zona B");
			System.out.println(respuesta.getZonaB().toString());
			System.out.println("Zona C");
			System.out.println(respuesta.getZonaC().toString());
			System.out.println("Zona D");
			System.out.println(respuesta.getZonaD().toString());
			*/

			res = respuesta.getRespuesta().getXmlLME();
			if(logDetail){
				logger.info("XML LICENCIA CONSUMIDA : " + respuesta.getRespuesta().getXmlLME());
			}
			//System.out.println(Util.transformXmlXsl(res));
			//response.getWriter().write(respuesta.getXmlLME());
		} catch (NumberFormatException e) {
			res = Util.xmlError(e.getMessage());
			//logger.error(e.getClass() + "; "+ e.getMessage());			
		} catch (ConfiguracionException e) {
			res = Util.xmlError(e.getMessage());
			//logger.error(e.getClass() + "; "+ e.getMessage());
		} catch (ErrorInvocacionOperadorException e) {
			res = Util.xmlError(e.getMessage());
			//logger.error(e.getClass() + "; "+ e.getMessage());
		} catch (ErrorRespuestaOperadorException e) {
			res = Util.xmlError(e.getMessage());
		} catch (Exception e) {
			res = Util.xmlError(e.getMessage());
		}

		InitConexion_LME.closeConexion_LME();
		response.getWriter().write(res);
		logger.info("TERMINO DEL PROCESO PARTICULAR : 'CONSULTA DEL DETALLE POR LICENCIA'  [" + (new Date()) + "]");
	}

	private IlfeOpeVO getIlfeOpe(String codOpe) {
		IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
		ilfeOpeVO.setCodOpe(codOpe);
		ilfeOpeVO.setStsOpe(Integer.valueOf("1"));
		try {
			svc_a = SvcFactory_LME.getAS400Svc_LME();
			List l = svc_a.getIlfeOpe(ilfeOpeVO);
			ilfeOpeVO = (IlfeOpeVO) l.iterator().next();
		} catch (SvcException e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}

		InitConexion_LME.closeConexion_LME();
		return ilfeOpeVO;
	}

	/*private String getUrlVordel(String codOpe, String service) {
		UrlBorderVO urlVO = new UrlBorderVO();
		urlVO.setCodOpe(codOpe);
		urlVO.setNombreServicio(service);

		String url = null;

		try {
			svc = SvcFactory.getAS400Svc();
			url = svc.getUrlVordel(urlVO);
		} catch (SvcException e) {
			e.printStackTrace();
		}

		return url;
	}*/

}
