/*
 * @(#) NotificacionPagoAction.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.actions.pagobes;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBes;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.AsynchonizedNotifierBes;
import cl.araucana.spl.util.XmlHelperBes;

import com.bh.talon.User;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Alejandro Sepúlveda Page <BR> asepulveda@schema.cl </TD>
 *            <TD> Versión inicial. Se encarga de la recepción de la notificación del banco estado
 *            cuando ha finalizado un pago. 
 *            </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Alejandro Sepúlveda Page (asepulveda@schema.cl)
 *
 * @version 1.0
 */

public class NotificacionPagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(NotificacionPagoAction.class);
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.debug("Entrando a NotificacionPago del banco: " + Constants.MEDIO_CODIGO_BES);
		String codigoIdTrx = null;
		String mensaje="";		
		try {

			mensaje = parseParameter("xml", mensaje);			
			mensaje = request.getParameter("xml");			
			logger.debug("El mensaje que llega NotificacionPago es: " + mensaje);
			
			TransaccionBes trx = getTransaccionRequest(mensaje);			
			if (logger.isDebugEnabled())
				logger.debug("TransaccionRequest: "+trx.toString());
			
			codigoIdTrx = trx.getId().toString();			
			
		} catch (Exception ex) {
			//continuar...
		}
		
		//Ejecuta logica de negocio en forma asincrona
	    AsynchonizedNotifierBes notifier = new AsynchonizedNotifierBes(mensaje);
	    Thread t = new Thread(notifier);
	    
	    t.setDaemon(true);
	    t.setName("NotifierBesPago-" + codigoIdTrx);
	    t.start();

		return null;
	}
	
	private TransaccionBes getTransaccionRequest(String mensaje) throws PagoEnLineaException {
		TransaccionBes trx = null;
		try {
			logger.debug("Estoy en getTransaccionRequest BES");
			
			//mensaje = parseParameter("xml", mensaje);
			trx = XmlHelperBes.parseMensajeNotificacion(mensaje, new TransaccionBes());
			
		} catch (TransformerConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (UnsupportedEncodingException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (ParserConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);			
		} catch (TransformerException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (ParseException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		}		
		return trx;
	}

}
