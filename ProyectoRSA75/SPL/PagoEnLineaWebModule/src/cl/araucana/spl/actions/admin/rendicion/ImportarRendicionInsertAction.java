package cl.araucana.spl.actions.admin.rendicion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.RendicionBase;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.mgr.RendicionManager;
import cl.araucana.spl.util.ActionTools;

import com.bh.talon.User;

public class ImportarRendicionInsertAction extends AppAction {
	
	private static final Logger logger = Logger.getLogger(ImportarRendicionInsertAction.class);

	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String codMedioPago = new String("");
		try {
			RendicionManager mgrRend = new RendicionManager();
			HttpSession session = request.getSession();
			
			List listaInconsistentes = (ArrayList) session.getAttribute(RendicionBase.LISTA_INCONSISTENTES);
			List listaInconsistentesPagos = (ArrayList) session.getAttribute(RendicionBase.LISTA_INCONSISTENTES_PAGOS);
			List listaRendicionesOK = (ArrayList) session.getAttribute(RendicionBase.LISTA_RENDICIONES_OK);			
			Object rendicion = (Object) session.getAttribute(RendicionBase.BEAN_RENDICION);
			codMedioPago = (String) session.getAttribute(RendicionBase.CODIGO_MEDIO_PAGO);

			DaoConfig.startTransaction();
			
			mgrRend.importarRendicion(rendicion, codMedioPago, listaInconsistentes, listaInconsistentesPagos, listaRendicionesOK);
			
			DaoConfig.commitTransaction();
			logger.debug("Commit ok, terminada la insercion de la rendicion");
			
			ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.insertOK", codMedioPago));
			
		} catch (Exception e) {
			ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.insertNOK", codMedioPago));
			logger.error(e.getMessage(), e);
		} finally {
			DaoConfig.endTransaction();
		}
		
		return mapping.findForward("target");
	}
	
}
