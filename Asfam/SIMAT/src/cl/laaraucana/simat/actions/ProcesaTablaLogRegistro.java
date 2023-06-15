package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.mannagerDB2.LogProcesosMannager;

/**
 * @version 	1.0
 * @author
 */
public class ProcesaTablaLogRegistro extends AbstractAction

{

	public ActionForward buscarPorTabla(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		String tabla = null;
		boolean key = false;

		System.out.println("action buscar_tabla: ");
		tabla = (String) request.getParameter("tabla");
		if (tabla != null) {
			System.out.println("TABLA: " + tabla);

			try {
				ArrayList listaProcesos = new ArrayList();
				LogProcesosMannager mannager = new LogProcesosMannager();
				listaProcesos = mannager.BuscarByTable(tabla);

				request.setAttribute("listaProcesos", listaProcesos);

				forward = mapping.findForward("successBusquedaTablaLogProceso");
				key = false;
			} catch (Exception e) {
				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
				key = true;
			}
		} else {
			System.out.println("-> forward null: ");
			key = true;
		}

		if (key) {
			System.out.println("evaluando key para error");
			ArrayList listaProcesos = new ArrayList();
			request.setAttribute("listaProcesos", listaProcesos);
			forward = mapping.findForward("error");
		}

		// Finish with
		return (forward);
	}

}
