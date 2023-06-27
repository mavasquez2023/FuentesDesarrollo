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

public class VerBitacoraAction extends AppAction {
	private static final Logger logger = Logger.getLogger(VerBitacoraAction.class);
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm f, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PagoForm form = (PagoForm) f;
		logger.debug("Recuperando bitacora de pago " + form.getPago());
		PagoManager manager = new PagoManager();
		List eventos = manager.getBitacora(form.getPago());
		form.setEventos(eventos);
		return mapping.findForward("target");
	}

}
