package cl.araucana.spl.actions.pagobes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;

import com.bh.talon.User;

/**

 */
public class SalidaPagoAction extends AppAction {
	private static final Logger log = Logger.getLogger(SalidaPagoAction.class);
	
	//protected abstract TransaccionEft parseMensajeFinal(String mensaje) throws PagoEnLineaException;
	//protected abstract String getCodigoMedio();
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		log.info("Inicio SalidaPagoAction... banco: " + Constants.MEDIO_CODIGO_BES);
		HttpSession session = req.getSession();
		
		// Vengo desde el banco: Recibo los parametros, los almaceno 
		//en sesion y el jsp cierra el popup y redirige en pagina padre.

		String codigoIdTrx = req.getParameter("retorno");			
		
		log.info("codigoIdTrx: " + codigoIdTrx);
								
		session.setAttribute("retorno", codigoIdTrx);
		
		return mapping.findForward("cierraPopup");

	}
}