package cl.laaraucana.satelites.certificados.afiliacion.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtilSinAS400;

/**
 * @version 1.0
 * @author
 */

public class SeleccionEmpresaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("datosUsuario") == null) {
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.SESION_EXPIRED);
			return mapping.findForward("customError");
		}

		UsuarioVO user = (UsuarioVO) sesion.getAttribute("datosUsuario");

		//matar este if para certificados CRM	
		if (false) {
//			if (user.isEsEmpresa() || user.isEsEmpresaPublica()) {
			return mapping.findForward("ingresaRut");

		}
		//-----

		UsuarioAfiliadoVO bpStatus = new UsuarioAfiliadoVO();
		String rut = user.getRut() + "-" + user.getDv();
		bpStatus = UsuarioServiceUtilSinAS400.obtenerAfiliado(rut);

		if (bpStatus.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			logger.debug("codigo de error igual a " + ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			List<DetalleEmpresaAfiliado> empresas = bpStatus.getDetalleEmpresaList();

			if (empresas.isEmpty()) {
				// Cuando no devuelve ninguna empresa
				return new ActionForward(mapping.findForward("error").getPath() + "?errorMsg=el rut no tiene asociada ninguna empresa.");
			} else {
				// Seleccion de empresas
				sesion.setAttribute("empresasList", empresas);
				sesion.setAttribute("rut", user.getRut() + "-" + user.getDv());
				sesion.setAttribute("nombreAfiliado", bpStatus.getNombreAfiliado().toUpperCase());

				return mapping.findForward("seleccionEmpresa");

			}

		} else {
			// BP no encontrado o falla en el servicio

			logger.error("codigo de error distinto de 0 cause:" + bpStatus.getMensaje());
			// return new
			// ActionForward(mapping.findForward("error").getPath() +
			// "?errorMsg=Error con el servicio, intente mas tarde.");
			return new ActionForward(mapping.findForward("error").getPath() + "?errorMsg=" + bpStatus.getMensaje());
		}

	}

}
