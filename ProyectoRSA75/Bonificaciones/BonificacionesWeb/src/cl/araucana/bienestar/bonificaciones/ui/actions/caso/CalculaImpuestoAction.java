package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.prestamo.serv.ServicesPrestamoDelegate;
import cl.araucana.prestamo.vo.ImpuestoVO;

/**
 * @version 	1.0
 * @author
 */
public class CalculaImpuestoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;

		ServicesPrestamoDelegate delegate = new ServicesPrestamoDelegate();
		double monto=Double.parseDouble((String)dynaValidatorActionForm.get("creditoGirado"));
		int numCuotas=Integer.parseInt((String)dynaValidatorActionForm.get("numeroCuotas"));
		ImpuestoVO impuesto=delegate.calcularImpuesto(monto, numCuotas);

		String target="impuesto";
	
		request.getSession(false).setAttribute("impuesto",impuesto);
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);
	}
}
