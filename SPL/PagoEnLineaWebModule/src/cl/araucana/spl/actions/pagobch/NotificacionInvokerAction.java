package cl.araucana.spl.actions.pagobch;

import cl.araucana.spl.actions.pagobase.NotificacionInvokerEftBaseAction;
import cl.araucana.spl.base.Constants;

/**
 * @author malvarez
 *
 */
public class NotificacionInvokerAction extends NotificacionInvokerEftBaseAction {

	protected String getCodigoMedio() {		
		return Constants.MEDIO_CODIGO_BCH;
	}
}
