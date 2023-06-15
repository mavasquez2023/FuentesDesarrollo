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
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.afiliacion.forms.GeneraCertificadoForm;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtilSinAS400;

/**
 * @version 1.0
 * @author
 */
public class AfiliacionEmpresaAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();
		GeneraCertificadoForm form = (GeneraCertificadoForm) _form;

		String rutEmpleado = form.getRut().replace(".", "");
		sesion.removeAttribute("rut");
		sesion.removeAttribute("nombreAfiliado");
		sesion.removeAttribute("fechaAfiliacion");
		sesion.removeAttribute("nombreEmpresa");
		
		try {

			UsuarioVO user = (UsuarioVO) sesion.getAttribute("datosUsuario");

			UsuarioAfiliadoVO bpStatus = new UsuarioAfiliadoVO();
			String rutEmpresa = user.getRut() + "-" + user.getDv();
			bpStatus = UsuarioServiceUtilSinAS400.obtenerAfiliado(rutEmpleado);

			if (bpStatus.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				logger.debug("codigo de error igual a " + ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);

				List<DetalleEmpresaAfiliado> empresas = bpStatus.getDetalleEmpresaList();

				for (DetalleEmpresaAfiliado detalleEmpresaAfiliado : empresas) {
					if (detalleEmpresaAfiliado.getRutEmpresa().trim().equals(rutEmpresa.trim())) {

						String rut = rutEmpleado;
						String nAfiliado = bpStatus.getNombreAfiliado().trim();
						String fAfiliacion = detalleEmpresaAfiliado.getFechaAfiliacion();
						String nombreEmpresa= detalleEmpresaAfiliado.getNombreEmpresa();
										
						request.setAttribute("fechaEmision", Utils.getFechaCompleta());
						sesion.setAttribute("rut", rutEmpleado);
						sesion.setAttribute("fechaAfiliacion", fAfiliacion);
						sesion.setAttribute("nombreAfiliado", nAfiliado);
						sesion.setAttribute("nombreEmpresa", nombreEmpresa);
						
						request.setAttribute("error", "0");
						
						if (rut == null || nAfiliado == null || fAfiliacion == null || rut.length() == 0 || nAfiliado.length() == 0 || fAfiliacion.length() == 0) {
							// Cuando faltan datos para llenar el certificado
							request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
							request.setAttribute("message", Constants.REPORT_DATA_ERROR);
							return mapping.findForward("customError");
						}
						return mapping.findForward("generaCertificado");

					} else {
						request.setAttribute("error", "El rut ingresado no pertenece a la empresa");
						forward= mapping.findForward("ingresaRut");
					}
				}
				return forward;

			} else {
				request.setAttribute("error",bpStatus.getMensaje());
				return mapping.findForward("ingresaRut");
			}
			} catch (Exception e) {
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}
		logger.debug(">>Salida CertificadoAfiliacionAction");
		return forward;

	}

}
