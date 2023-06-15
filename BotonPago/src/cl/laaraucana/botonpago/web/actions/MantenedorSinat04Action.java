package cl.laaraucana.botonpago.web.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat04;
import cl.laaraucana.botonpago.web.forms.Sinat04Form;
import cl.laaraucana.botonpago.web.manager.ManagerSINADTA;
import cl.laaraucana.botonpago.web.utils.Constantes;

/**
 * @version 1.0
 * @author
 */
public class MantenedorSinat04Action extends DispatchAction {

	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward agregarSinat04(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value

		try {

			Sinat04Form form = (Sinat04Form) _form;

			// obtiene los datos para insertar el usuario
			// inserta el sinat04
			Sinat04 sinat04 = new Sinat04();
			sinat04.setAnopro(form.getAnopro());
			sinat04.setCodpro(Constantes.getInstancia().COD_PRO_SINAT04);
			sinat04.setNrodes(form.getNrodes());
			sinat04.setNrohas(form.getNrohas());
			sinat04.setPorcen(form.getPorcen());
			sinat04.setFecsis(form.getFecsis());
			sinat04.setHorsis(form.getHorsis());
			sinat04.setIduser(form.getIduser());

			ManagerSINADTA.agregarSinat04(sinat04);
			// obtiene la lista actualizada
			List<Sinat04> usuariosList = ManagerSINADTA.getSinat04(Constantes.getInstancia().COD_PRO_SINAT04);
			request.setAttribute("sinat04List", usuariosList);
			forward = mapping.findForward("tabla");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al registrar el usuario");
		}

		return (forward);

	}

	public ActionForward buscarEditarSinat04(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {
			Sinat04Form form = (Sinat04Form) _form;
			Sinat04 sinat04Edit = new Sinat04();

			sinat04Edit.setAnopro(form.getAnopro());
			sinat04Edit.setCodpro(Constantes.getInstancia().COD_PRO_SINAT04);
			sinat04Edit.setNrodes(form.getNrodes());
			sinat04Edit.setNrohas(form.getNrohas());

			Sinat04 sinat04 = ManagerSINADTA.buscarEditarSinat04(sinat04Edit);
			sinat04.setIduser(sinat04.getIduser().trim());
			request.setAttribute("sinat04", sinat04);
			forward = mapping.findForward("formulario");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (forward);

	}

	public ActionForward editarSinat04(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {
			// update a la tabla del usuario
			Sinat04Form form = (Sinat04Form) _form;

			Sinat04 sinat04 = new Sinat04();
			sinat04.setAnopro(form.getAnopro());
			sinat04.setCodpro(Constantes.getInstancia().COD_PRO_SINAT04);
			sinat04.setNrodes(form.getNrodes());
			sinat04.setNrohas(form.getNrohas());
			sinat04.setPorcen(form.getPorcen());
			sinat04.setFecsis(form.getFecsis());
			sinat04.setHorsis(form.getHorsis());
			sinat04.setIduser(form.getIduser());

			sinat04.setAnoproEdit(form.getAnoproEdit());
			sinat04.setCodproEdit(Constantes.getInstancia().COD_PRO_SINAT04);
			sinat04.setNrodesEdit(form.getNrodesEdit());
			sinat04.setNrohasEdit(form.getNrohasEdit());

			ManagerSINADTA.editarSinat04(sinat04);

			// busca la lista actualizada
			List<Sinat04> usuariosList = ManagerSINADTA.getSinat04(Constantes.getInstancia().COD_PRO_SINAT04);
			request.setAttribute("sinat04List", usuariosList);
			forward = mapping.findForward("tabla");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al registrar el usuario");
		}

		return (forward);

	}

	/*
	 * public ActionForward buscarSinat04(ActionMapping mapping, ActionForm _form,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception {
	 * 
	 * ActionForward forward = new ActionForward(); // return value
	 * 
	 * try {
	 * 
	 * Sinat04Form form = (Sinat04Form) _form;
	 * 
	 * String entrada = form.getTipcod(); if (entrada.length() == 0) { List<Sinat04>
	 * usuariosList = ManagerSINADTA.getSinat04();
	 * request.setAttribute("sinat04List", usuariosList); forward =
	 * mapping.findForward("tabla"); } else { List<Sinat04> usuariosList =
	 * ManagerSINADTA.buscarSinat04(entrada); request.setAttribute("sinat04List",
	 * usuariosList); forward = mapping.findForward("tabla"); } } catch (Exception
	 * e) { e.printStackTrace();
	 * 
	 * }
	 * 
	 * return (forward);
	 * 
	 * }
	 */

	public ActionForward eliminarSinat04(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value

		try {

			// elimina el usuario
			Sinat04Form form = (Sinat04Form) _form;
			Sinat04 sinat04 = new Sinat04();
			sinat04.setAnopro(form.getAnopro());
			sinat04.setCodpro(Constantes.getInstancia().COD_PRO_SINAT04);
			sinat04.setNrodes(form.getNrodes());
			sinat04.setNrohas(form.getNrohas());
			ManagerSINADTA.eliminarSinat04(sinat04);
			// obtiene la lista actualizada
			List<Sinat04> usuariosList = ManagerSINADTA.getSinat04(Constantes.getInstancia().COD_PRO_SINAT04);
			request.setAttribute("sinat04List", usuariosList);
			forward = mapping.findForward("tabla");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al eliminar el sinat04");
		}

		return (forward);

	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		try {
			List<Sinat04> usuariosList = ManagerSINADTA.getSinat04(Constantes.getInstancia().COD_PRO_SINAT04);
			request.setAttribute("sinat04List", usuariosList);
			forward = mapping.findForward("cargaTotal");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al consultar por sinat04: " + e.getMessage());
			request.setAttribute("errorMsg",
					"Error; Ha ocurrido un error al consultar los datos de % Condonación de Gravámenes .");
			return mapping.findForward("error");
		}
		return (forward);
	}

}
