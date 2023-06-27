/*
 * @(#) InicioPagoAction.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.actions.pagobes;

import java.io.IOException;
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
import cl.araucana.spl.beans.TransaccionBes;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBesManager;
import cl.araucana.spl.util.XmlHelperBes;

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
 *            <TD> Versión inicial.
 *            Marca el inicio de una transaccion Banco Estado y redirige al usuario a la pagina del banco.
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

public class InicioPagoAction extends InicioPagoBaseAction {
	private static final Logger logger = Logger.getLogger(InicioPagoAction.class);
	
	protected ActionForward redireccionCgi(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, Transaccion trx) throws PagoEnLineaException {
		TransaccionBes trxBes = (TransaccionBes) trx;
		
		Date fecha = new Date(System.currentTimeMillis());
		trxBes.getPago().setFechaTransaccion(fecha);
		
		String url = trxBes.getUrlCgi();
		String msg = new String("");
		try {
			msg = XmlHelperBes.formatMensajeInicial(trxBes);
		} catch (TransformerConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBes, e);
		} catch (ParserConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBes, e);
		} catch (TransformerException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBes, e);
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
		logger.debug("En InicioPagoAction BES");
		PagoBesManager pagoBesManager = new PagoBesManager();
		TransaccionBes trx = pagoBesManager.createTransaccion(wxml, getCodigoMedio(), codigoSistema);
		if (logger.isInfoEnabled()) {
			logger.info("Transaccion BES creada: " + trx + ", url retorno: " + wxml.getUrlRetorno());
		}

		return trx;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BES;
	}

	protected String preIngresoCompra(HttpServletResponse response, Transaccion trx){
		// TODO Auto-generated method stub
		return null;
	}	

}
