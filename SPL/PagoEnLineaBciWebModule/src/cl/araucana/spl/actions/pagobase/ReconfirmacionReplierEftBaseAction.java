package cl.araucana.spl.actions.pagobase;

import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.XmlHelper;

import com.bh.talon.User;

/**
 * @author rgili
 *
 */
public abstract class ReconfirmacionReplierEftBaseAction extends AppAction {
	
	protected abstract TransaccionEft parseMensajeBanco(String contenido) throws PagoEnLineaException; 

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String contenido = getContenidoLlamada(request);
		
		TransaccionEft trx = parseMensajeBanco(contenido);
		
		// se setea algunos valores en la respuesta
		trx.setCodRetorno(new BigDecimal("0000"));
		trx.setDescRetorno("Transaccion%20OK");
		trx.setNroPagos(new BigDecimal("1"));
		trx.setFechaTransaccion(new java.sql.Timestamp(System.currentTimeMillis()));
		trx.setFechaContable(new Date(System.currentTimeMillis()));
		trx.setNroComprobante(new BigDecimal("0011223344"));
		
		String mensajeSalida = XmlHelper.formatRespuestaConfirmacion(trx);
		
		sendResponse(mensajeSalida, response);
		
		return null;
	}

}
