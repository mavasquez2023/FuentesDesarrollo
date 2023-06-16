package cl.laaraucana.botonpago.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.botonpago.web.cobol.vo.EntradaDeudorVO;
import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;
import cl.laaraucana.botonpago.web.forms.ActualizaForm;
import cl.laaraucana.botonpago.web.manager.ManagerBPAGC002;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.vo.DatosActualizablesVO;

/**
 * @version 1.0
 * @author
 */
public class ActualizarDatosAction extends DispatchAction {

	Logger logger = Logger.getLogger(this.getClass());

	/**
	 *carga el formulario disponible para actualizar los datos con la informacion actual del usuario
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward cargaInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DatosActualizablesVO datos = new DatosActualizablesVO();
		try {
			HttpSession session = request.getSession();
			String rutDeudor = ((String) session.getAttribute("rutDeudor"));

			String rut = rutDeudor.split("-")[0];
			String dv = rutDeudor.split("-")[1];

			DatosDeudorDAO dao = new DatosDeudorDAO();
			Bpagf001 salida = dao.getDatoDeudorByRut(rut, dv);
			if (salida == null) {
				logger.error("No se encontró al usuario " + rutDeudor + " en la base de datos");
				throw new Exception("No se encontró al usuario en la base de datos.");
			}

			datos = new DatosActualizablesVO(salida.getEstcivil().trim(),//estcivil, 
					salida.getFono1().trim(),//fono1, 
					salida.getFono2().trim(),//fono2, 
					salida.getFono3().trim(),//fono3, 
					salida.getEmail().trim(),//email, 
					salida.getDirdeu().trim(),//direccion, 
					salida.getComudeu().trim(),//comuna,
					salida.getProvdeu().trim(),//provincia,
					salida.getRegdeu().trim(),//region,
					salida.getEnvecta().trim()//envecta
			);

			request.setAttribute("datosDeudor", datos);
			request.setAttribute("comunas", Constantes.getInstancia().COMUNAS);
			request.setAttribute("provincias", Constantes.getInstancia().PROVINCIAS);
			request.setAttribute("regiones", Constantes.getInstancia().REGIONES);
			return mapping.findForward("carga");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("no se pueden obtener los datos del deudor.");
			request.setAttribute("datosDeudor", datos);
			request.setAttribute("comunas", Constantes.getInstancia().COMUNAS);
			request.setAttribute("provincias", Constantes.getInstancia().PROVINCIAS);
			request.setAttribute("regiones", Constantes.getInstancia().REGIONES);
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("mensaje", "" + e.getMessage());
			return mapping.findForward("carga");
		}
	}

	/**
	 * actualiza los datos ingresados y muestra el resultado en la pantalla
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DatosActualizablesVO datos = new DatosActualizablesVO();

		try {
			ActualizaForm actform = (ActualizaForm) form;

			ActionErrors errors = actform.validate(mapping, request);
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
				return mapping.findForward("carga2");
			}
			HttpSession session = request.getSession();
			String[] rutDeudor = ((String) session.getAttribute("rutDeudor")).split("-");

			EntradaDeudorVO deudor = new EntradaDeudorVO();
			deudor.setRut(rutDeudor[0]);
			deudor.setEmail(actform.getEmail());
			deudor.setEnvEECC(actform.getRecibirECVM());
			deudor.setFono1(actform.getFono1());
			deudor.setFono2(actform.getFono2());
			deudor.setFono3(actform.getFono3());
			deudor.setEstCivil(actform.getEstadoCivil());
			deudor.setDireccion(actform.getDireccion());
			deudor.setComuna(actform.getComuna());
			deudor.setProvincia(actform.getProvincia());
			deudor.setRegion(actform.getRegion());

			ManagerBPAGC002 bpagc002 = new ManagerBPAGC002();
			SalidaDeudorVO salida = bpagc002.actializaDeudor(deudor);

			if (salida.getCodSalida().equals("000")) {
				logger.debug("Datos actualizados correctamnte");
			} else {
				logger.error("Error al actualizar los datos del deudor codSalida: " + salida.getCodSalida());
				throw new Exception();
			}
			datos = new DatosActualizablesVO(salida.getEstCivil().trim(),//estcivil, 
					salida.getFono1().trim(),//fono1, 
					salida.getFono2().trim(),//fono2, 
					salida.getFono3().trim(),//fono3, 
					salida.getEmail().trim(),//email, 
					salida.getDireccion().trim(),//direccion, 
					salida.getComuna().trim(),//comuna,
					salida.getProvincia().trim(),//provincia,
					salida.getRegion().trim(),//region,
					salida.getEnvecta().trim()//envecta
			);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("no se pueden actualizar los datos del deudor.");
			request.setAttribute("datosDeudor", datos);
			request.setAttribute("comunas", Constantes.getInstancia().COMUNAS);
			request.setAttribute("provincias", Constantes.getInstancia().PROVINCIAS);
			request.setAttribute("regiones", Constantes.getInstancia().REGIONES);
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("mensaje", "Error al actualizar los datos del usuario");
			return mapping.findForward("carga");
		}
		request.setAttribute("datosDeudor", datos);
		request.setAttribute("comunas", Constantes.getInstancia().COMUNAS);
		request.setAttribute("provincias", Constantes.getInstancia().PROVINCIAS);
		request.setAttribute("regiones", Constantes.getInstancia().REGIONES);
		request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_EXITO);
		request.setAttribute("mensaje", "Sus datos fueron actualizados correctamente.");
		return mapping.findForward("carga");

	}

}
