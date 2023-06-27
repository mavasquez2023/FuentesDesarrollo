package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasDelegate;
import cl.araucana.common.BusinessException;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 1.0
 * @author
 */
public class OpcionesCasoAction extends Action {

	Logger logger = Logger.getLogger(OpcionesCasoAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale
				.getDefault());

		int opcion = (int) Integer.parseInt((String) dynaValidatorActionForm
				.get("opcion"));
		logger.debug("+ + + Opcion: " + opcion);
		Caso caso = (Caso) request.getSession(false).getAttribute("caso");

		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation) request
				.getSession(false).getAttribute("application.userinformation");
		if (userinformation == null)
			userinformation = new cl.araucana.common.ui.UserInformation();

		if (opcion == 1 || opcion == 3) {

			if (((String) dynaValidatorActionForm.get("fechaDeOcurrencia"))
					.trim().equals("")) {
				caso.setFechaDeOcurrencia(formato.parse("00/00/0000"));
			} else {
				caso.setFechaDeOcurrencia(formato
						.parse((String) dynaValidatorActionForm
								.get("fechaDeOcurrencia")));
			}
			logger.debug("+ + + Fecha: " + caso.getFechaDeOcurrencia());

			caso.setTipoCaso((String) dynaValidatorActionForm.get("tipoCaso"));
			if (((String) dynaValidatorActionForm.get("cuotasBienestar"))
					.trim().equals("")) {
				caso.setCuotasBienestar(0);
			} else {
				caso.setCuotasBienestar((int) Integer
						.parseInt((String) dynaValidatorActionForm
								.get("cuotasBienestar")));
			}
			if (((String) dynaValidatorActionForm.get("cuotasConvenio")).trim()
					.equals("")) {
				caso.setCuotasConvenio(0);
			} else {
				caso.setCuotasConvenio((int) Integer
						.parseInt((String) dynaValidatorActionForm
								.get("cuotasConvenio")));
			}
			caso.setNumeroDocumento((String) dynaValidatorActionForm
					.get("numeroDocumento"));
			caso.setTipoDocumento((String) dynaValidatorActionForm
					.get("tipoDocumento"));
			caso
					.setTipoBono((String) dynaValidatorActionForm
							.get("compraBono"));
		}

		if (opcion == 6) { // crear caso

			HttpSession sesion = request.getSession();

			// removemos el objeto Caso asignado a la sesion en
			// setListaCasosAction
			sesion.removeAttribute("caso");
		}

		String referer = "/getListaCasos.do";

		if (caso.getEstado().equals(Caso.STD_PRECASO))
			referer = "/prepareListaPreCasos.do";

		String target = null;
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		ServicesCoberturasDelegate coberturasDelegate = new ServicesCoberturasDelegate();

		switch (opcion) {
		case 1:
			caso.setUsuario(userinformation.getUsuario());
			long idCaso = delegate.registraCaso(caso);
			referer = "/setFichaCaso.do?codigo=" + idCaso;
			target = "success";
			break;
		case 2:
			delegate.activarCaso(caso.getCasoID());
			target = "success";
			break;
		case 3:
			delegate.actualizarCaso(caso);
			target = "success";
			break;
		case 4:
			caso = delegate.simularBonificacion(caso.getCasoID());
			target = "simularCaso";
			request.getSession(false).setAttribute("caso", caso);
			break;
		case 5:
			delegate.bonificarCaso(caso.getCasoID());
			target = "success";
			break;
		case 6:
			target = "nuevoCaso";
			break;
		case 7:
			delegate.eliminarCaso(caso.getCasoID(), userinformation
					.getUsuario());
			target = "success";
			break;
		case 8:
			target = "cierreCaso";
			break;
		case 9:
			target = "bonificacionEspecial"; //FEC
			ArrayList listaCoberturasAdicionales = coberturasDelegate
					.getListaCoberturasEspeciales();
			if (listaCoberturasAdicionales.size() == 0)
				throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
						"No existen coberturas especiales difinidas en el Sistema");
			request.getSession(false).setAttribute(
					"lista.coberturas.especiales", listaCoberturasAdicionales);
			break;
		case 10:
			target = "validaOperacionDeudaTotal";
			break;
		case 11:
			target = "eliminaReembolso";
			break;
		case 12:
			target = "bonificacionEspecialNormal"; //REQ 5088
			/**ArrayList listaCoberturas = coberturasDelegate
			.getListaCoberturasDirectas();
			if (listaCoberturas.size() == 0)
				throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"No existen coberturas difinidas en el Sistema");
			request.getSession(false).setAttribute(
					"lista.coberturas.especiales", listaCoberturas);*/
			break;
		default:
			target = "noservice";
			break;
		}

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("referer", referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);

	}

}
