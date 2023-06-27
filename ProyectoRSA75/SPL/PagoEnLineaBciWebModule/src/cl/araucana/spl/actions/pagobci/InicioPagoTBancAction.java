package cl.araucana.spl.actions.pagobci;

import cl.araucana.spl.base.Constants;

public class InicioPagoTBancAction extends InicioPagoBciAction {

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_TBC;
	}
}
