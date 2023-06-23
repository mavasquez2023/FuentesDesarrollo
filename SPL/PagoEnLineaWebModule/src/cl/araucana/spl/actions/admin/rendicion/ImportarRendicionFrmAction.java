package cl.araucana.spl.actions.admin.rendicion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.forms.admin.rendicion.ImportarRendicionForm;
import cl.araucana.spl.mgr.MedioPagoManager;

import com.bh.talon.User;

/**
 * Action que lleva a despliegue formulario de importacion.
 * @author malvarez
 * @since 1.0 / 28-03-2008
 * 
 */
public class ImportarRendicionFrmAction extends AppAction {
	private final Logger logger = Logger.getLogger(ImportarRendicionFrmAction.class);
	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ImportarRendicionForm frm = (ImportarRendicionForm) form;
		try {
			MedioPagoManager mgr = new MedioPagoManager();
			List medios = mgr.getMediosPagoActivos();
			
			frm.setMedios(medios);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			frm.setMedios(new ArrayList());
		}
		return mapping.findForward("target");
	}

}
