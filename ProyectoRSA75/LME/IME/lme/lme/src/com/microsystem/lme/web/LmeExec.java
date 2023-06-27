/*
 * Created on 27-11-2011
 *
 */
package com.microsystem.lme.web;

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

import com.microsystem.lme.ibatis.domain.Ilfe080VO;
import com.microsystem.lme.ibatis.domain.Ilfe081VO;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.InitConexion_LME;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.util.Util;

/**
 * @author microsystem
 *
 */
public class LmeExec extends DispatchAction {

	private static Logger logger = Logger.getLogger(LmeExec.class);
	private IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();
	private SimpleDateFormat shf = new SimpleDateFormat("HHmmss");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	

	public void execLMEDetLcc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//System.out.println(request.getParameter("license").toString());
		//System.out.println(request.getParameter("codOpe").toString());
		String res = null;
		
		try {

			Date now = new Date();
			String lic = URLDecoder.decode(request.getParameter("license"), "UTF-8");
			String codOpe = URLDecoder.decode(request.getParameter("codOpe"), "UTF-8");
			String ultimoEstado = URLDecoder.decode(request.getParameter("ultimoEstado"), "UTF-8");
			String fileparam = URLDecoder.decode(request.getParameter("file"), "UTF-8");
			
			logger.info("SE REGISTRA PROCESO PARTICULAR " + ultimoEstado + ": 'CONSUMO DE DETALLE POR LICENCIA' [" + (new Date()) + "] con licencia: "+lic);
			
			BigInteger numLic = new BigInteger(lic);
			
			
			//se realiza el insert de la tabla  ILFE080
			Ilfe080VO vo = new Ilfe080VO();
			vo.setIdOperador(codOpe);
			vo.setNumLicencia(lic);
			vo.setDigLicencia(Util.dv(new BigInteger(lic)));
			vo.setFechaConsulta(sdf.format(now));
			vo.setHoraConsulta(shf.format(now));
			vo.setEstado("0");
			String respuesta="";
			if(ultimoEstado.equals("72")){
				respuesta = svc_a.insertIlfe085(vo);
			}else{
				respuesta = svc_a.insertIlfe080(vo);
			}
			if("oK".equalsIgnoreCase(respuesta)){
				res = "Se agregó el consumo " + ultimoEstado + " particular de la licencia "+numLic+" a la cola";
			}else{
				res = "Ocurrio un problema al registrar la licencia "+numLic+" a la cola";
			}
			
			

		} catch (UnsupportedEncodingException e) {
			res = e.getMessage();
			logger.error(e.getClass() + "; " + e.getMessage());
		}

		//System.out.println("la RESP 2 (res) es: " + res);
		InitConexion_LME.closeConexion_LME();
		setResponse(response, res);
	}
	
	
	public void execLMEEvenLcc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String res = null;
		try {

			Date now = new Date();
			String dateLcc = URLDecoder.decode(request.getParameter("dateLcc"), "UTF-8");
			String horaDesde = URLDecoder.decode(request.getParameter("horaDesde"), "UTF-8");
			String horaHasta = URLDecoder.decode(request.getParameter("horaHasta"), "UTF-8");
			//Date date = Util.date(dateLcc + " " +  horaDesde, "dd/MM/yyyy MM:mm");
			//Date dateHasta = Util.date(dateLcc + " " +  horaHasta, "dd/MM/yyyy MM:mm");
			
			System.out.println("SE REGISTRA PROCESO PARTICULAR : 'CONSUMO NUEVOS ESTADOS' [" + (new Date()) + "] con fecha: "+dateLcc + " " +  horaDesde  + ", hasta: " + dateLcc + " " +  horaHasta);
			logger.info("SE REGISTRA PROCESO PARTICULAR : 'CONSUMO NUEVOS ESTADOS' [" + (new Date()) + "] con fecha: "+dateLcc + " " +  horaDesde  + ", hasta: " + dateLcc + " " +  horaHasta);
			
			//realizar el insert a la tabla ILFE081 y entregar la respuesta
			Ilfe081VO vo = new Ilfe081VO();
			vo.setFecha(dateLcc + " " +  horaDesde);
			vo.setFechaHasta(dateLcc + " " +  horaHasta);
			vo.setEstadoImed("");
			vo.setEstadoMediPass("");
			vo.setFechaConsulta(sdf.format(now));
			vo.setHoraConsulta(shf.format(now));
			vo.setEstado("0");
			
			String respuesta = svc_a.insertIlfe081(vo);
			
			if("oK".equalsIgnoreCase(respuesta)){
				res = "Se agregó el consumo particular de la fecha "+dateLcc+" a la cola";
			}else{
				res = "Ocurrio un problema al registrar la fecha "+dateLcc+" a la cola";
			}

			
		} catch (UnsupportedEncodingException e) {
			res = e.getMessage();
			logger.error(e.getClass() + "; " + e.getMessage());
		} 

		InitConexion_LME.closeConexion_LME();
		setResponse(response, res);

	}
	

	private void setResponse(HttpServletResponse response, String str) throws IOException {
		//response.setContentType(Constants.CONTENT_TYPE_JSON);
		response.getWriter().write("<br>" + str);
		//System.out.println("la res es: " + str);
	}
}
