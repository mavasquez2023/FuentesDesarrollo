package cl.laaraucana.botonpago.web.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat03;
import cl.laaraucana.botonpago.web.forms.Sinat03Form;
import cl.laaraucana.botonpago.web.manager.ManagerSINADTA;

/**
 * @version 1.0
 * @author
 */
public class MantenedorSinat03Action extends DispatchAction {

	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward agregarSinat03(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value

		try {

			Sinat03Form form = (Sinat03Form) _form;

			// obtiene los datos para insertar el usuario
			// inserta el sinat03
			Sinat03 sinat03 = new Sinat03();
			sinat03.setTipcod(form.getTipcod());
			sinat03.setPorcen(form.getPorcen());
			sinat03.setFecsis(form.getFecsis());
			sinat03.setHorsis(form.getHorsis());
			sinat03.setIduser(form.getIduser());
			ManagerSINADTA.agregarSinat03(sinat03);
			// obtiene la lista actualizada
			List<Sinat03> usuariosList = ManagerSINADTA.getSinat03();
			request.setAttribute("sinat03List", usuariosList);
			forward = mapping.findForward("tabla");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al registrar el usuario");
		}

		return (forward);

	}

	public ActionForward buscarEditarSinat03(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {
			Sinat03Form form = (Sinat03Form) _form;
			String entrada = form.getTipcod();
			Sinat03 sinat03 = ManagerSINADTA.buscarEditarSinat03(entrada);
			sinat03.setIduser(sinat03.getIduser().trim());
			request.setAttribute("sinat03", sinat03);
			forward = mapping.findForward("formulario");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (forward);

	}

	public ActionForward buscarSinat03(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {

			Sinat03Form form = (Sinat03Form) _form;

			String entrada = form.getTipcod();
			if (entrada.length() == 0) {
				List<Sinat03> usuariosList = ManagerSINADTA.getSinat03();
				request.setAttribute("sinat03List", usuariosList);
				forward = mapping.findForward("tabla");
			} else {
				List<Sinat03> usuariosList = ManagerSINADTA.buscarSinat03(entrada);
				request.setAttribute("sinat03List", usuariosList);
				forward = mapping.findForward("tabla");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return (forward);

	}

	public ActionForward editarSinat03(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {

			// update a la tabla del usuario
			Sinat03Form form = (Sinat03Form) _form;

			Sinat03 sinat03 = new Sinat03();
			sinat03.setTipcod(form.getTipcod());
			sinat03.setPorcen(form.getPorcen());
			sinat03.setFecsis(form.getFecsis());
			sinat03.setHorsis(form.getHorsis());
			sinat03.setIduser(form.getIduser());
			ManagerSINADTA.editarSinat03(sinat03);

			// busca la lista actualizada
			List<Sinat03> usuariosList = ManagerSINADTA.getSinat03();
			request.setAttribute("sinat03List", usuariosList);
			forward = mapping.findForward("tabla");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al registrar el usuario");
		}

		return (forward);

	}

	public ActionForward eliminarSinat03(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {

			// elimina el usuario
			Sinat03Form form = (Sinat03Form) _form;
			Sinat03 sinat03 = new Sinat03();
			sinat03.setTipcod(form.getTipcod());
			ManagerSINADTA.eliminarSinat03(sinat03);
			// obtiene la lista actualizada
			List<Sinat03> usuariosList = ManagerSINADTA.getSinat03();
			request.setAttribute("sinat03List", usuariosList);
			forward = mapping.findForward("tabla");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al eliminar el sinat03");
		}

		return (forward);

	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		try {
			List<Sinat03> usuariosList = ManagerSINADTA.getSinat03();
			request.setAttribute("sinat03List", usuariosList);
			forward = mapping.findForward("cargaTotal");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al consultar por sinat03: " + e.getMessage());
			request.setAttribute("errorMsg",
					"Error; Ha ocurrido un error al consultar los datos de % Condonación de Gravámenes .");
			return mapping.findForward("error");
		}
		return (forward);
	}

}
