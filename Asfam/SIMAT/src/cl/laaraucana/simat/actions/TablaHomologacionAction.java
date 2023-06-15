package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.TablaHomologacionVO;
import cl.laaraucana.simat.forms.TablaHomologacionForm;
import cl.laaraucana.simat.mannagerDB2.TablaHomologacionMannager;

/**
 * @version 	1.0
 * @author
 */
public class TablaHomologacionAction extends AbstractAction

{

	public ActionForward buscarByIdRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			TablaHomologacionForm homologacionForm = (TablaHomologacionForm) form;
			TablaHomologacionVO homologacionVO = new TablaHomologacionVO();
			ArrayList listaHomologacion = new ArrayList();

			homologacionVO.setId_registro(homologacionForm.getId_registro());

			TablaHomologacionMannager mannager = new TablaHomologacionMannager();
			homologacionVO = mannager.BuscarByIdRegistro(homologacionVO);

			if (homologacionVO != null) {
				listaHomologacion.add(homologacionVO);
				request.setAttribute("listaHomologacion", listaHomologacion);
			} else {
				listaHomologacion = new ArrayList();
				request.setAttribute("listaHomologacion", listaHomologacion);
			}

			forward = mapping.findForward("successHomologacionByIdRegistro");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

	public ActionForward buscarByClasificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		ArrayList listaHomologacion = new ArrayList();
		try {
			TablaHomologacionForm homologacionForm = (TablaHomologacionForm) form;
			TablaHomologacionVO homologacionVO = new TablaHomologacionVO();
			homologacionVO.setClasificacion(homologacionForm.getClasificacion());

			TablaHomologacionMannager mannager = new TablaHomologacionMannager();
			listaHomologacion = mannager.buscarByClasificacion(homologacionVO);
			request.setAttribute("listaHomologacion", listaHomologacion);
			forward = mapping.findForward("successHomologacionByClasificacion");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			System.out.println("* * * * * CATCH: ");
			request.setAttribute("listaHomologacion", listaHomologacion);
			forward = mapping.findForward("successHomologacionByClasificacion");
			//errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println("Llego al Action");
		try {
			TablaHomologacionVO homologacionVO = new TablaHomologacionVO();
			TablaHomologacionForm homologacionForm = (TablaHomologacionForm) form;

			homologacionVO.setClasificacion(homologacionForm.getClasificacion());
			homologacionVO.setDescripcion(homologacionForm.getDescripcion());
			homologacionVO.setCodigo_suceso(homologacionForm.getCodigo_suceso());
			homologacionVO.setCodigo_la(homologacionForm.getCodigo_la());

			TablaHomologacionMannager mannager = new TablaHomologacionMannager();
			mannager.Insertar(homologacionVO);

			ArrayList listaHomologacion = new ArrayList();
			listaHomologacion = mannager.buscarTodoHomologacion();
			request.setAttribute("listaHomologacion", listaHomologacion);

			forward = mapping.findForward("successInsertarHomologacion");

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
			TablaHomologacionForm homologacionForm = (TablaHomologacionForm) form;
			TablaHomologacionMannager mannager = new TablaHomologacionMannager();
			TablaHomologacionVO homologacion = new TablaHomologacionVO();
			homologacion.setId_registro(homologacionForm.getId_registro());

			//homologacion.setId_registro(Integer.parseInt((String)request.getAttribute("id")));

			System.out.println("Llego al Action del: " + homologacionForm.getId_registro());
			mannager.Eliminar(homologacion);

			//mannager.Eliminar(homologacion);
			ArrayList listaHomologacion = new ArrayList();
			listaHomologacion = mannager.buscarTodoHomologacion();
			request.setAttribute("listaHomologacion", listaHomologacion);
			forward = mapping.findForward("successEliminarHomologacion");
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
		System.out.println("Entro al Action");
		try {
			TablaHomologacionVO homologacionVO = new TablaHomologacionVO();
			TablaHomologacionForm homologacionForm = (TablaHomologacionForm) form;

			System.out.println("Llego al action up");
			homologacionVO.setId_registro(homologacionForm.getId_registro());
			homologacionVO.setClasificacion(homologacionForm.getClasificacion());
			homologacionVO.setDescripcion(homologacionForm.getDescripcion());
			homologacionVO.setCodigo_suceso(homologacionForm.getCodigo_suceso());
			homologacionVO.setCodigo_la(homologacionForm.getCodigo_la());

			TablaHomologacionMannager mannager = new TablaHomologacionMannager();
			mannager.Actualizar(homologacionVO);

			ArrayList listaHomologacion = new ArrayList();
			listaHomologacion = mannager.buscarTodoHomologacion();
			request.setAttribute("listaHomologacion", listaHomologacion);
			forward = mapping.findForward("successActualizarHomologacion");
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
