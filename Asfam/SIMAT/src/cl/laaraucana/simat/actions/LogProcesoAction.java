package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.LogProcesosVO;
import cl.laaraucana.simat.forms.LogProcesosForm;
import cl.laaraucana.simat.mannagerDB2.LogProcesosMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;

/**
 * @version 	1.0
 * @author
 */
public class LogProcesoAction extends AbstractAction

{

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {
			ArrayList listaProcesos = new ArrayList();
			LogProcesosVO proceso = new LogProcesosVO();
			LogProcesosMannager mannager = new LogProcesosMannager();
			String msgPaginacion = "";
			String direccion = request.getParameter("keyAvance");
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			int keyInicio = 0;
			int keyFin = 0;

			keyInicio = Integer.parseInt(request.getParameter("keyInicio"));
			keyFin = Integer.parseInt(request.getParameter("keyFin"));
			//keyFin=cantidad;
			if (direccion.equals("a")) {
				//avanzamos
				listaProcesos = mannager.BuscarListaAvanzar(keyFin);

				if (listaProcesos == null || listaProcesos.size() == 0) {
					msgPaginacion = "no existen registros posteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyFin));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin + cantidad));
				}

			} else if (direccion.equals("r")) {

				//retrocedemos
				if (keyInicio != 0) {
					keyFin = keyInicio;
					keyInicio = (keyInicio - cantidad);
				}
				listaProcesos = mannager.BuscarListaRetroceder(keyInicio);
				if (listaProcesos == null || listaProcesos.size() == 0) {
					msgPaginacion = "no existen registros anteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(0));
					request.setAttribute("keyFinCopy", String.valueOf(cantidad));
					listaProcesos = mannager.BuscarListaRetroceder(0);
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				}
			}

			request.setAttribute("listaProcesos", listaProcesos);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionSMLPR");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}

		return (forward);
	}

	public ActionForward buscarByIdRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ArrayList listaProcesos = new ArrayList();
			LogProcesosForm logProcesosForm = (LogProcesosForm) form;

			String id_registro = String.valueOf(logProcesosForm.getId_registro());

			LogProcesosMannager mannager = new LogProcesosMannager();
			listaProcesos = (ArrayList) mannager.BuscarByIdRegistro(id_registro);

			request.setAttribute("listaProcesos", listaProcesos);
			forward = mapping.findForward("successBusquedaLogProcesoById");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLog");

		}

		// Finish with
		return (forward);
	}

	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ArrayList listaProcesos = new ArrayList();
			LogProcesosForm logProcesosForm = (LogProcesosForm) form;

			String registroLog = String.valueOf(logProcesosForm.getFecha_hora());

			LogProcesosMannager mannager = new LogProcesosMannager();
			//listaProcesos = (ArrayList) mannager

			request.setAttribute("listaProcesos", listaProcesos);
			forward = mapping.findForward("successBusquedaLogProcesoById");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			// forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);
	}

	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			LogProcesosForm logProcesosForm = (LogProcesosForm) form;
			LogProcesosMannager mannager = new LogProcesosMannager();
			LogProcesosVO proceso = new LogProcesosVO();

			proceso.setTipo_log(logProcesosForm.getTipo_log());
			proceso.setFecha_hora(logProcesosForm.getFecha_hora());
			proceso.setPeriodo(logProcesosForm.getPeriodo());
			proceso.setId_usuario(logProcesosForm.getId_usuario());
			proceso.setProceso_afectado(logProcesosForm.getProceso_afectado());
			proceso.setTabla(logProcesosForm.getTabla());
			proceso.setRegistro_id(logProcesosForm.getRegistro_id());
			proceso.setDescripcion(logProcesosForm.getDescripcion());

			mannager.Insertar(proceso);

			ArrayList listaProcesos = new ArrayList();
			request.setAttribute("listaProcesos", listaProcesos);

			forward = mapping.findForward("successInsertarLogProcesos");

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			// forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			LogProcesosForm logProcesosForm = (LogProcesosForm) form;
			LogProcesosMannager mannager = new LogProcesosMannager();
			LogProcesosVO LogProcesos = new LogProcesosVO();

			LogProcesos.setId_registro(logProcesosForm.getId_registro());
			mannager.eliminar(LogProcesos);

			ArrayList listaProcesos = new ArrayList();
			request.setAttribute("listaProcesos", listaProcesos);
			forward = mapping.findForward("successEliminarLogProcesos");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			// forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);
	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {

			LogProcesosForm logProcesosForm = (LogProcesosForm) form;
			LogProcesosMannager mannager = new LogProcesosMannager();
			LogProcesosVO proceso = new LogProcesosVO();

			proceso.setId_registro(logProcesosForm.getId_registro());
			proceso.setTipo_log(logProcesosForm.getTipo_log());
			proceso.setFecha_hora(logProcesosForm.getFecha_hora());
			proceso.setPeriodo(logProcesosForm.getPeriodo());
			proceso.setId_usuario(logProcesosForm.getId_usuario());
			proceso.setProceso_afectado(logProcesosForm.getProceso_afectado());
			proceso.setTabla(logProcesosForm.getTabla());
			proceso.setRegistro_id(logProcesosForm.getRegistro_id());
			proceso.setDescripcion(logProcesosForm.getDescripcion());

			mannager.actualizar(proceso);

			ArrayList listaProcesos = new ArrayList();
			request.setAttribute("listaProcesos", listaProcesos);
			forward = mapping.findForward("successActualizarLogProcesos");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			// forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);
	}

}
