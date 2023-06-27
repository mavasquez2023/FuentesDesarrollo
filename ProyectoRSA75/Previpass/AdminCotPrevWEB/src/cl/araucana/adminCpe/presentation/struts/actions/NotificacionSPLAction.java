package cl.araucana.adminCpe.presentation.struts.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.ComprobanteDAO;
import cl.araucana.adminCpe.hibernate.dao.ParametroDAO;
import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.struts.forms.NotificacionSPLActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.spl.util.crypto.CryptResult;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;
import cl.araucana.spl.util.crypto.TripleDesEngineException;

import com.bh.talon.User;


public class NotificacionSPLAction extends AppAction
{
	private static Logger logger = Logger.getLogger(NotificacionSPLAction.class);
	public static final String FORWARD = "exito";
	
	public NotificacionSPLAction() {
		super();
	}

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NotificacionSPLActionForm actForm = (NotificacionSPLActionForm) form;
		ActionMessages am = new ActionMessages();
		
		Session session = null;
		Session sessionSPL = null;
		String operacion = request.getParameter("operacion");
		try
		{
			if (request.getParameter("operacion") != null){
				if(operacion.equals("Guardar")){
					session = HibernateUtil.getSession();
					sessionSPL = HibernateUtil.getSession("SPL");
					
					cl.araucana.adminCpe.hibernate.beans.SPLPagoVO pago = (new ComprobanteDAO(sessionSPL)).getSPLPago(new Long(actForm.getIdPagoSpl()).longValue());
					
					if(pago != null){
							
						ParametroDAO parametroDAO = new ParametroDAO(session);
						
						List listaParams = new ArrayList();
						listaParams.add(new Integer(Constants.PARAM_CLAVE_SPL));
						listaParams.add(new Integer(Constants.PARAM_COD_SISTEMA_SPL));
						listaParams.add(new Integer(Constants.PARAM_URL_SPL));
						
						ParametrosHash paramHash =parametroDAO.getParametrosHash(listaParams); 
											
						CryptResult resultadoEncriptacion = getEncriptaResult(paramHash.get("" + Constants.PARAM_CLAVE_SPL), actForm.getIdPagoSpl(), "ISO-8859-1");
						SimpleEncoder encoder = new SimpleEncoder();
			
						byte[] crypted = resultadoEncriptacion.getCrypted();
						String sCrypted = encoder.bytesToHex(crypted);
						byte[] ivector = resultadoEncriptacion.getIvector();
						String sIvector = encoder.bytesToHex(ivector);
			
						logger.info("id Pago:" + sCrypted + "::");
						logger.info("vector:" + sIvector + "::");
						
							int independiente = pago.getUrlNotificacionOrigen().indexOf("CotPrevIndependienteWeb");
							int empresa = pago.getUrlNotificacionOrigen().indexOf("CotizacionPrevisionalWeb");
		
							if(independiente > 1){
							 	   logger.info("URL: "+"/CotPrevIndependienteWeb/ServletNotificacionManual?trx="+sCrypted+"&vector="+sIvector);
								
								response.sendRedirect("/CotPrevIndependienteWeb/ServletNotificacionManual?trx="+sCrypted+"&vector="+sIvector);
				
							}else if(empresa > 1){
							 	  logger.info("URL: "+"/CotizacionPrevisionalWeb/ServletNotificacionManual?trx="+sCrypted+"&vector="+sIvector);
								
								response.sendRedirect("/CotizacionPrevisionalWeb/ServletNotificacionManual?trx="+sCrypted+"&vector="+sIvector);
							}else{
								logger.info("Id pago no pertenece a CPE : "+ actForm.getIdPagoSpl());
								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.pagoNoCPE"));
								this.saveMessages(request.getSession(), am);
							}
					}else{
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.pagoNoExiste"));
						this.saveMessages(request.getSession(), am);
					}
					
				}else if(operacion.equals("ok")){
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardarPago"));
					this.saveMessages(request.getSession(), am);
					
				}else if(operacion.equals("error")){
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.pago"));
					this.saveMessages(request.getSession(), am);
	
				}
			}
			
		} catch (Exception e)
		{
			logger.error("error al notificar el pago en cpe:", e);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.pago"));
			this.saveMessages(request.getSession(), am);

	
		}
		return mapping.findForward(FORWARD);
	}
	/**
	 * encripta result
	 * @param key
	 * @param plain
	 * @param charset
	 * @return
	 * @throws TripleDesEngineException
	 */
	private CryptResult getEncriptaResult(String key, String plain, String charset) throws TripleDesEngineException
	{
		TripleDesEngine cipher = new TripleDesEngine();
		return cipher.encrypt(key, plain, charset);
	}
}