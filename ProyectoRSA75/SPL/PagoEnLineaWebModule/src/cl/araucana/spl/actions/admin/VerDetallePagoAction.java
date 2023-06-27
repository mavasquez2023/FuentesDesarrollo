package cl.araucana.spl.actions.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.forms.admin.PagoForm;
import cl.araucana.spl.mgr.PagoManager;

import com.bh.talon.User;

public class VerDetallePagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(VerDetallePagoAction.class);
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm f, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PagoForm form = (PagoForm) f;
		PagoManager manager = new PagoManager();

		logger.debug("Recuperando folios de pago " + form.getPago());
		List folios = manager.getFoliosByPago(form.getPago());
		logger.debug("Cantidad de folios: " + folios.size());
		form.setFolios(folios);
		return mapping.findForward("target");
	}
}
