package cl.araucana.spl.actions.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.FiltroInconsistencias;
import cl.araucana.spl.beans.ResumenInconsistencias;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.forms.admin.InconsistenciasForm;
import cl.araucana.spl.mgr.InconsistenciasManager;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.Utiles;

import com.bh.paginacion.DataPage;
import com.bh.talon.User;

public class ListarInconsistenciasAction extends AppAction {
	private static final Logger logger = Logger.getLogger(ListarInconsistenciasAction.class);

	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm f, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		InconsistenciasForm form = (InconsistenciasForm) f;
		InconsistenciasManager inconsManager = new InconsistenciasManager();
		SistemaManager sisManager = new SistemaManager();
		
		try {
			DaoConfig.startTransaction();
			form.setSistemas(sisManager.getSistemas());
			form.setEstados(inconsManager.getEstados());
	
			MedioPagoManager medioManager  = new MedioPagoManager();
			form.setBancos(medioManager.getMediosPago());
			
			if (Utiles.isNotEmpty(form.getBuscar())) {
				int limit = Constants.PAGE_SIZE;
				
				FiltroInconsistencias filtro = form.getFiltro();
				InconsistenciasPageParameter params = new InconsistenciasPageParameter(request, limit, filtro);
				filtro.setLimit(params.getLimit());
				filtro.setOffset(params.getOffset());
				DataPage pagina = inconsManager.getPaginaInconsistencias(filtro);
				if (logger.isDebugEnabled()) {
					logger.debug("pagina: " + pagina);
				}
				params.setToView(pagina);
				form.setInconsistencias(pagina.getItems());
				
				ResumenInconsistencias resumen = inconsManager.getResumenInconsistencias(form.getFiltro());
				form.setResumen(resumen);
			}
			DaoConfig.commitTransaction();
		} finally {
			DaoConfig.endTransaction();
		}
		
		return mapping.findForward("target");
	}
}
