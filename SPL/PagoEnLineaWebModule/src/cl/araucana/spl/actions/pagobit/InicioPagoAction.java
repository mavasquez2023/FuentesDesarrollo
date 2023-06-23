package cl.araucana.spl.actions.pagobit;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.actions.pagobase.InicioPagoBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Transaccion;
import cl.araucana.spl.beans.TransaccionBit;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBitManager;
import cl.araucana.spl.util.XmlHelperBit;

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

public class InicioPagoAction extends InicioPagoBaseAction {
	private static final Logger logger = Logger.getLogger(InicioPagoAction.class);
	
	protected ActionForward redireccionCgi(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, Transaccion trx) throws PagoEnLineaException {
		TransaccionBit trxBit = (TransaccionBit) trx;
		
		Date fecha = new Date(System.currentTimeMillis());
		trxBit.getPago().setFechaTransaccion(fecha);
		
		String url = trxBit.getUrlCgi();
		String msg = new String("");
		try {
			msg = XmlHelperBit.formatMensajeInicial(trxBit);
		} catch (TransformerConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBit, e);
		} catch (ParserConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBit, e);
		} catch (TransformerException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBit, e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Ejecutando redireccion a URL del banco: " + url);
			logger.debug("Mensaje inicial: " + msg);
		}
		request.setAttribute("url_cgi", url);
		request.setAttribute("mensaje_inicial", msg);
		return mapping.findForward("target");
	}
	
	protected Transaccion createTransaccion(WrapperXmlMedioPago wxml, String codigoMedio, String codigoSistema) throws PagoEnLineaException {
		logger.debug("En InicioPagoAction BIT");
		PagoBitManager pagoBitManager = new PagoBitManager();
		TransaccionBit trx = pagoBitManager.createTransaccion(wxml, getCodigoMedio(), codigoSistema);
		if (logger.isInfoEnabled()) {
			logger.info("Transaccion BIT creada: " + trx + ", url retorno: " + wxml.getUrlRetorno());
		}

		return trx;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BIT;
	}	

}
