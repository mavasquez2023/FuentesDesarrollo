package cl.araucana.spl.actions.admin.inconsistencia;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Estado;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.forms.admin.inconsistencia.ModificarInconsistenciaForm;
import cl.araucana.spl.mgr.InconsistenciasManager;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.util.ResourceHelper;

import com.bh.talon.User;

/**
 * Action que lleva a despliegue formulario de ingreso de bitacota.
 * @author malvarez
 * @since 1.0 / 14-04-2008
 * 
 */
public class ModificarInconsistenciaAction extends AppAction {
	private final Logger logger = Logger.getLogger(ModificarInconsistenciaAction.class);
	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = new String("target");
		String modificado = "ok";
		
		try {
			ModificarInconsistenciaForm frm = (ModificarInconsistenciaForm) form;
			PagoManager mgrPago = new PagoManager();
			InconsistenciasManager mgrIncon = new InconsistenciasManager();
			ResourceHelper rh = ResourceHelper.getInstance();
			
			//Gets
			BigDecimal idPago = frm.getIdPago();			
			String idEstadoStr = ("".equals(frm.getEstadoId())?frm.getEstadoIdActual():frm.getEstadoId());
			BigDecimal idEstado = new BigDecimal(idEstadoStr);
			String pagadoStr = ("".equals(frm.getPagado())?frm.getPagadoActual():frm.getPagado());
			Integer pagado = new Integer(pagadoStr);
			String observacion = frm.getObservacion();
			String usuario = user.getLogin();
			
			DaoConfig.startTransaction();
			
			mgrPago.procesarInconsistencia(idPago, idEstado, pagado, observacion, usuario);
			
			DaoConfig.commitTransaction();
			logger.debug("Commit ok, terminada la modificacion de inconsitencia");
			
			//Sets
			Estado estadoNuevo = mgrIncon.getEstadoById(idEstado);
			frm.setEstado(estadoNuevo);
			frm.setPagadoDesc(rh.getProperty(Constants.PAGO_PAGADO_AUX + pagado));
			request.setAttribute("idPago", idPago.toString());
			
			
		} catch (Exception e) {
			modificado = "nok";
			logger.error(e.getMessage(), e);			
		} finally {
			DaoConfig.endTransaction();			
		}
		
		
		request.setAttribute("modificado", modificado);
		return mapping.findForward(target);
	}
	
}
