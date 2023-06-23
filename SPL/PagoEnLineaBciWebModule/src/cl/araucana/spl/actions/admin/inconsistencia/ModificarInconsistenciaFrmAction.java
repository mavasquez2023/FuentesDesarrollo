package cl.araucana.spl.actions.admin.inconsistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Estado;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.forms.admin.inconsistencia.ModificarInconsistenciaForm;
import cl.araucana.spl.mgr.InconsistenciasManager;
import cl.araucana.spl.mgr.PagoManager;

import com.bh.talon.User;

/**
 * Action que lleva a despliegue formulario de ingreso de bitacota.
 * @author malvarez
 * @since 1.0 / 14-04-2008
 * 
 */
public class ModificarInconsistenciaFrmAction extends AppAction {
	private final Logger logger = Logger.getLogger(ModificarInconsistenciaFrmAction.class);
	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = new String("target");
		
		try {
			ModificarInconsistenciaForm frm = (ModificarInconsistenciaForm) form;
			BigDecimalValidator valBig = BigDecimalValidator.getInstance();
			InconsistenciasManager mgrIncons = new InconsistenciasManager();
			PagoManager mgrPago = new PagoManager();
			boolean modificable = true;
			
			//Gets
			String idPagoStr = request.getParameter("idPago");			
			BigDecimal idPago = valBig.validate(idPagoStr);
			
			if (idPago!=null) { //Ir a ingreso de bitacora
				Pago pago = mgrPago.getPagoById(idPago);
				List listaEstados = new ArrayList();
				
				//Analizar el estado del pago
				Estado estado = pago.getEstado();
				if (estado!=null) {
					BigDecimal idEstado = estado.getId();
					if (Constants.ESTADO_PAGO_SIN_CUADRAR.equals(idEstado) || Constants.ESTADO_PAGO_CONSISTENTE.equals(idEstado)) {
						//No es posible modificar el estado
						modificable = false;
						listaEstados.add(mgrIncons.getEstadoById(idEstado));
					} else {
						if (Constants.ESTADO_PAGO_INCONSISTENTE.equals(idEstado)) {
							listaEstados.add(mgrIncons.getEstadoById(Constants.ESTADO_PAGO_CORREGIDO));
						} else {
							listaEstados.add(mgrIncons.getEstadoById(Constants.ESTADO_PAGO_INCONSISTENTE));
						}
					}
					estado = mgrIncons.getEstadoById(idEstado);
				}
				
				List listaPagados = mgrPago.getListaPagado();
				mgrPago.removePagado(listaPagados, pago.getPagado());
				
				//Sets form y request
				frm.setPago(pago);
				frm.setEstado(estado);
				frm.setEstadoIdActual(estado.getId().toString());
				frm.setPagado(pago.getPagado().toString());
				frm.setModificable(new Boolean(modificable));
				frm.setEstados(listaEstados);
				frm.setPagoPagados(listaPagados);
				frm.setIdPago(idPago);
				
				request.setAttribute("_PAGADO", pago.getPagado().toString());
				
			} else {
				//Id no valido
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward(target);
	}

}
