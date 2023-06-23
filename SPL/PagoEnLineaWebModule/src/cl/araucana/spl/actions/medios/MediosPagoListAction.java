package cl.araucana.spl.actions.medios;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.forms.medios.MediosPagoForm;
import cl.araucana.spl.mgr.IntegracionManager;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.MailSender;

import com.bh.talon.User;

public class MediosPagoListAction extends AppAction {
	private static final Logger logger = Logger.getLogger(MediosPagoListAction.class);
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm f, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MediosPagoForm form = (MediosPagoForm) f;
		if (form == null) { form = new MediosPagoForm(); }// TODO: Revisar esto.

		MedioPagoManager medioManager = new MedioPagoManager();
		IntegracionManager iManager = new IntegracionManager();
		SistemaManager sisManager = new SistemaManager();
		String forward = "target";
		try{
			logger.debug("VML: INGRESO A MediosPagoListAction");

			String codSistema = request.getParameter("sistema");
			String sCrypted   = request.getParameter("xml");
			String sIvector   = request.getParameter("vector");

			String claveSistema = sisManager.getClaveSistema(codSistema);
			
			WrapperXmlMedioPago wxml = iManager.wrapperXmlMedioPago(claveSistema, sCrypted, sIvector);
			String urlRetorno = wxml.getUrlRetorno(); 
			
			List medios = medioManager.getMediosActivos(wxml.getMediosPagoBeans());
			
			form.setMedios(medios);
			form.setFolios(wxml.getFoliosBeans());
			form.setGlosa(wxml.getGlosa());
			form.setUrlRetorno(urlRetorno);
			form.setCodSistema(codSistema);
			form.setXml(sCrypted);
			form.setVector(sIvector);
			form.setMontoTotal(wxml.getMontoTotal());
			
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex);
			MailSender.sendError(ex.getMessage(), ex);
			throw ex;
		}

		return mapping.findForward(forward);
	}
}
