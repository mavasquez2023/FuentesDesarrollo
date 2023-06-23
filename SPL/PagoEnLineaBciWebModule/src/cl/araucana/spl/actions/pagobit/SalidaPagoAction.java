package cl.araucana.spl.actions.pagobit;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBit;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.MailSender;
import cl.araucana.spl.util.XmlHelper;

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
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versión inicial. </TD>
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
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */
public class SalidaPagoAction extends AppAction  {
	private static final Logger logger = Logger.getLogger(SalidaPagoAction.class);
	
		protected TransaccionEft parseMensajeFinal(String mensaje) throws PagoEnLineaException {
			logger.debug("Entre a parseMensajeFinal en SalidaPagoAction BIT");
			TransaccionEft trx = null;
			try {
				trx = XmlHelper.parseMensajeFinal(mensaje, new TransaccionBit());
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

	protected String getCodigoMedio() {
			return Constants.MEDIO_CODIGO_BIT;
	}
	
    protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
    	logger.info("Inicio SalidaPagoAction... no base banco: " + getCodigoMedio());
		HttpSession session = req.getSession();
		
		try {
			// Vengo desde el banco: Recibo los parametros, los almaceno 
			//en sesion y el jsp cierra el popup y redirige en pagina padre.
			String contenidoLlamada = getContenidoLlamada(req);
			logger.info("El contenido de la llamada es:\n"+contenidoLlamada);
			
			String mensaje = parseParameter("tx", contenidoLlamada);
			logger.info(mensaje);
			
			if (mensaje == null) {
				logger.warn("Mensaje vacio: Tratando de leer desde el query string...");
				mensaje = req.getParameter("tx");
				logger.info("Mensaje directamente desde getParameter: " + mensaje);
			}
			logger.info("Final = " + mensaje);
			TransaccionEft trx = parseMensajeFinal(mensaje);
			session.setAttribute("trx", trx);
			if (logger.isDebugEnabled())
				logger.debug(trx.toString());

			String urlReconfirmacion = parseParameter("url_reconfirmacion", contenidoLlamada);
			if (urlReconfirmacion != null) {
				session.setAttribute("urlReconfirmacion", urlReconfirmacion);
			}
			logger.info("Redirigiendo a jsp de cierre de popup");
			
			logger.info("Fin SalidaPagoAction... banco: " + getCodigoMedio());
			return mapping.findForward("cierraPopup");

		} catch (Exception ex) {
			String mensaje = "El error se produjo al ejecutar la Salida pago (SalidaPagoAction)";
			logger.error(mensaje+": "+ex.getMessage());
			MailSender.sendError(ex.getMessage(), ex);
			throw ex;	
		}
	} 
}