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
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.microsystem.lme.ibatis.domain.IlfeOpeVO;
import com.microsystem.lme.job.ConsumoOperadorService;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.IAS400Svc_SIL;
import com.microsystem.lme.svc.InitConexion_LME;
import com.microsystem.lme.svc.InitConexion_SIL;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.svc.SvcFactory_SIL;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.LabelValueVO;
import com.microsystem.lme.util.Util;

/**
 * @author microsystem
 *
 */
public class LmeExecOLD extends DispatchAction {

	private static Logger logger = Logger.getLogger(LmeExecOLD.class);
	private IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();
	private IAS400Svc_SIL svc_b = SvcFactory_SIL.getAS400Svc_SIL();

	public void execLMEEvenLcc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String res = null;
		logger.info("INICIO DEL PROCESO PARTICULAR : 'CONSUMO NUEVOS ESTADOS' [" + (new Date()) + "]");
		try {

			String dateLcc = URLDecoder.decode(request.getParameter("dateLcc"), "UTF-8");
			Date date = Util.date(dateLcc, "dd/MM/yyyy");

			ConsumoOperadorService consumoOperadorService = new ConsumoOperadorService(getResources(request));

			consumoOperadorService.setDateLcc(date);
			consumoOperadorService.setLmeList(new ArrayList());

			consumoOperadorService.consumirEstadosLME("");

			//			List lmeList = consumoOperadorService.getLmeList();
			//			Iterator i = lmeList.iterator();
			//			if(lmeList.size()>0)consumoOperadorService.startProcess("ConsumirDetallesLME");

			//			while(i.hasNext()){
			//				Ilfe000VO vo = (Ilfe000VO)i.next();
			//				consumoOperadorService.consumirDetalleLME(vo.toIlfeOpeVO());
			//			}			

			consumoOperadorService.consumirDetallesLME();
			consumoOperadorService.actualizarLmeCCAF();

			res = consumoOperadorService.getLogLcc().toString();
		} catch (UnsupportedEncodingException e) {
			res = e.getMessage();
			log.error(e.getClass() + "; " + e.getMessage());
		} catch (ParseException e) {
			res = e.getMessage();
			log.error(e.getClass() + "; " + e.getMessage());
		}

		setResponse(response, res);
		logger.info("TERMINO DEL PROCESO PARTICULAR : 'CONSUMO NUEVOS ESTADOS' [" + (new Date()) + "]");

	}

	public void execLMEDetLcc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//System.out.println(request.getParameter("license").toString());
		//System.out.println(request.getParameter("codOpe").toString());
		String res = null;
		logger.info("INICIO DEL PROCESO PARTICULAR : 'CONSUMO DE DETALLE POR LICENCIA' [" + (new Date()) + "]");

		try {

			String lic = URLDecoder.decode(request.getParameter("license"), "UTF-8");
			String codOpe = URLDecoder.decode(request.getParameter("codOpe"), "UTF-8");
			BigInteger numLic = new BigInteger(lic);

			IlfeOpeVO ilfeOpeVO = new IlfeOpeVO();
			ilfeOpeVO.setCodOpe(codOpe);
			ilfeOpeVO.setStsOpe(Integer.valueOf("1"));

			List l = svc_a.getIlfeOpe(ilfeOpeVO);
			ilfeOpeVO = (IlfeOpeVO) l.get(0);
			ilfeOpeVO.setNumLicencia(lic);
			ilfeOpeVO.setDigLicencia(Util.dv(numLic));
			LabelValueVO err;

			Util.log("LIC={0} CODOPE={1} CODCCAF={2} PWDCCAF={3} ", new Object[] { lic, codOpe, ilfeOpeVO.getCodCcaf(), ilfeOpeVO.getPwdCcaf() });

			ConsumoOperadorService consumoOperadorService = new ConsumoOperadorService(getResources(request));

			consumoOperadorService.setDateLcc(new Date());
			consumoOperadorService.startProcess("ConsumirDetallesLME");
			err = consumoOperadorService.consumirDetalleLME(ilfeOpeVO, "");
			Map h = new Hashtable();
			h.put("ideOpe", ilfeOpeVO.getIdeOpe());
			h.put("numImpre", ilfeOpeVO.getNumLicencia());
			h.put("label", err.getLabel().toString());
			svc_b.updateIlfe002RError(h);

			if ("OK".equalsIgnoreCase(err.getLabel().toString())) {
				consumoOperadorService.actualizarLmeCCAF();
			}

			//if (consumoOperadorService.getLogLcc().toString().length()>0){
			//	res = consumoOperadorService.getLogLcc().toString();
			//}else{
			//res = err.getValue().toString() + err.getLabel().toString();
			res = err.getLabel().toString();
			//}	
			//System.out.println("la RESP 1 (res) es: " + res);

		} catch (UnsupportedEncodingException e) {
			res = e.getMessage();
			log.error(e.getClass() + "; " + e.getMessage());
		} catch (SvcException e) {
			res = e.getMessage();
			log.error(e.getClass() + "; " + e.getMessage());
		}

		InitConexion_LME.closeConexion_LME();
		InitConexion_SIL.closeConexion_SIL();
		//System.out.println("la RESP 2 (res) es: " + res);
		setResponse(response, res);
		logger.info("TERMINO DEL PROCESO PARTICULAR : 'CONSUMO DE DETALLE POR LICENCIA'  [" + (new Date()) + "]");
	}

	private void setResponse(HttpServletResponse response, String str) throws IOException {
		//response.setContentType(Constants.CONTENT_TYPE_JSON);
		response.getWriter().write(str);
		//System.out.println("la res es: " + str);
	}
}
