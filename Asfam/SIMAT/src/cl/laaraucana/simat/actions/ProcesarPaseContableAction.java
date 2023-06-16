package cl.laaraucana.simat.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.ResultadoPaseContable;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;
import cl.laaraucana.simat.mannagerDB2.PaseContableManager;

public class ProcesarPaseContableAction extends Action {

	Logger log = Logger.getLogger(this.getClass());

	/**
	 * Despliega por pantalla la tabla de cuentas y totales para generar el pase contable
	 * Si los montos (debito y credio) no cuadran, no se muestra la opción 'Procesar'
	 * Las cuentas se almacenan en sesión, ya que son recibidas posteriormente por ProcesarPaseContableAction
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesionActual = request.getSession(true);
		String mensaje = "";
		String codigo = "5";
		try {

			if (sesionActual == null || sesionActual.getAttribute("nombre") == null) {
				response.sendRedirect(request.getServletContext().getInitParameter("urlMenuDinamico"));
				return null;
			}

			ProcesaPeriodos procPeriod = new ProcesaPeriodos();
			String periodo = (String) sesionActual.getAttribute("periodo");

			if (sesionActual.getAttribute("cuadrePreBalance") != null && (Boolean) sesionActual.getAttribute("cuadrePreBalance")) {
				if(sesionActual.getAttribute("cuadrePaseContable") != null && (Boolean) sesionActual.getAttribute("cuadrePaseContable")){
					PaseContableManager paseMgr = new PaseContableManager();
					ResultadoPaseContable paseContable = (ResultadoPaseContable) sesionActual.getAttribute("paseContable");
					boolean resProcesoPase = paseMgr.procesarPaseContable(periodo, paseContable.getCuentasPaseContable());
					codigo = "3";
					if (resProcesoPase) {
						mensaje = "Ya existían registros para el pase contable, se generó un nuevo pase contable exitosamente.";
					} else {
						mensaje = "El pase contable fue generado correctamente";
					}
					//Obliga a generar de nuevo desde el comienzo
					sesionActual.setAttribute("obtenerPaseContable", null);
					sesionActual.setAttribute("cuadrePreBalance", null);
				}else{
					//Falta validar pase contable
					codigo = "5";
					mensaje = "Primero debe ejecutar el proceso de validación del pre balance";
					//return mapping.findForward("obtenerPaseContable");
				}
			} else {
				//Falta validar pre balance
				codigo = "5";
				mensaje = "Primero debe ejecutar el proceso de validación del pase contable";
				//return mapping.findForward("validarPreBalance");
			}

		} catch (Exception e) {
			log.error("LOG catch: ", e);
			codigo = "5";
			mensaje = "Se produjo un problema al procesar su solicitud, causa: " + e.getCause();
		}

		request.setAttribute("mensaje", mensaje);
		request.setAttribute("tipoMensaje", codigo);
		forward = mapping.findForward("success");
		return (forward);
	}
}