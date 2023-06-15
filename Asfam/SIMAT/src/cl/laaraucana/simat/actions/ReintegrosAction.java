package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.ReintegrosVO;
import cl.laaraucana.simat.forms.ReintegrosForm;
import cl.laaraucana.simat.mannagerDB2.ReintegrosMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;

/**
 * @version 	1.0
 * @author
 */
public class ReintegrosAction extends AbstractAction

{

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		HttpSession sesionActual = null;
		try {
			sesionActual = request.getSession(true);

			ArrayList listaReintegros = new ArrayList();
			ReintegrosMannager mannager = new ReintegrosMannager();
			String msgPaginacion = "";
			String direccion = request.getParameter("keyAvance");
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			int keyInicio = 0;
			int keyFin = 0;

			keyInicio = Integer.parseInt(request.getParameter("keyInicio"));
			keyFin = Integer.parseInt(request.getParameter("keyFin"));

			if (direccion.equals("a")) {
				//avanzamos
				listaReintegros = mannager.BuscarListaAvanzar(keyFin);

				if (listaReintegros == null || listaReintegros.size() == 0) {
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
				if (keyInicio > 0) {
					keyFin = keyInicio;
					keyInicio = (keyInicio - cantidad);
				} else {
					keyFin = 0;
					keyInicio = 0;
				}
				listaReintegros = mannager.BuscarListaRetroceder(keyInicio);
				if (listaReintegros == null || listaReintegros.size() == 0) {
					msgPaginacion = "no existen registros anteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(0));
					request.setAttribute("keyFinCopy", String.valueOf(0));
					listaReintegros = mannager.BuscarListaRetroceder(0);
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				}
			}

			request.setAttribute("listaReintegros", listaReintegros);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT1");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		return (forward);
	}//end mostrarPaginacion

	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		//System.out.println("Advertencia "+request.getParameter("MES_INFORMACION"));
		try {
			ArrayList listaReintegros = new ArrayList();
			ReintegrosForm reintegrosForm = (ReintegrosForm) form;
			String mes_informacion = reintegrosForm.getMes_informacion();
			System.out.println("MES_INFORMACIÓN reeintegros= " + mes_informacion);

			ReintegrosMannager mannager = new ReintegrosMannager();
			listaReintegros = (ArrayList) mannager.BuscarByMesInf(mes_informacion);

			System.out.println("Lista generada, tamaño: " + listaReintegros.size());

			request.setAttribute("listaReintegros", listaReintegros);
			forward = mapping.findForward("sucessBuscarReintegrosByMes");
		} catch (Exception e) {
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);

	}

	public ActionForward buscarByRutBenef(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			ArrayList listaReintegros = new ArrayList();
			String rutBenef = (String) request.getParameter("rutBenef");

			ReintegrosMannager mannager = new ReintegrosMannager();
			ReintegrosVO reintegros = new ReintegrosVO();

			reintegros.setRun_beneficiario(rutBenef);
			listaReintegros = (ArrayList) mannager.getReintegrosByRutBenef(reintegros);
			request.setAttribute("listaReintegros", listaReintegros);
			forward = mapping.findForward("sucessBuscarByRutBenef");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByRutBenef

	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ReintegrosForm reintegrosForm = (ReintegrosForm) form;
			ReintegrosVO reintegros = new ReintegrosVO();
			System.out.println("Entrada Set-Get");

			reintegros.setMes_informacion(reintegrosForm.getMes_informacion());
			reintegros.setCodigo_entidad(reintegrosForm.getCodigo_entidad());
			reintegros.setMes_nomina(reintegrosForm.getMes_nomina());
			reintegros.setRun_beneficiario(reintegrosForm.getRun_beneficiario());
			reintegros.setNombre_beneficiario(reintegrosForm.getNombre_beneficiario());
			reintegros.setTipo_subsidio(reintegrosForm.getTipo_subsidio());
			reintegros.setNro_licencia(reintegrosForm.getNro_licencia());
			reintegros.setRut_empleador(reintegrosForm.getRut_empleador());
			reintegros.setNombre_empleador(reintegrosForm.getNombre_empleador());
			reintegros.setOrigen_reintegro(reintegrosForm.getOrigen_reintegro());
			reintegros.setMonto_total_a_reintegrar(reintegrosForm.getMonto_total_a_reintegrar());
			reintegros.setMonto_recuperado(reintegrosForm.getMonto_recuperado());
			reintegros.setMonto_recuperado_acum(reintegrosForm.getMonto_recuperado_acum());
			reintegros.setTotal_saldo_a_reintegrar(reintegrosForm.getTotal_saldo_a_reintegrar());

			System.out.println("Salida Set-Get");

			ReintegrosMannager mannager = new ReintegrosMannager();
			mannager.Insertar(reintegros);

			ArrayList listaReintegros = new ArrayList();
			int keyFin = 0;
			listaReintegros = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaReintegros", listaReintegros);

			forward = mapping.findForward("successInsertarReintegros");
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
			ReintegrosForm reintegrosForm = (ReintegrosForm) form;

			ReintegrosVO reintegros = new ReintegrosVO();
			reintegros.setId(reintegrosForm.getId());

			ReintegrosMannager mannager = new ReintegrosMannager();
			mannager.eliminar(reintegros);

			ArrayList listaReintegros = new ArrayList();
			int keyFin = 0;
			listaReintegros = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaReintegros", listaReintegros);
			forward = mapping.findForward("successEliminarReintegros");

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
			ReintegrosForm reintegrosForm = (ReintegrosForm) form;
			ReintegrosVO reintegros = new ReintegrosVO();

			reintegros.setId(reintegrosForm.getId());
			reintegros.setMes_informacion(reintegrosForm.getMes_informacion());
			reintegros.setCodigo_entidad(reintegrosForm.getCodigo_entidad());
			reintegros.setMes_nomina(reintegrosForm.getMes_nomina());
			reintegros.setRun_beneficiario(reintegrosForm.getRun_beneficiario());
			reintegros.setNombre_beneficiario(reintegrosForm.getNombre_beneficiario());
			reintegros.setTipo_subsidio(reintegrosForm.getTipo_subsidio());
			reintegros.setNro_licencia(reintegrosForm.getNro_licencia());
			reintegros.setRut_empleador(reintegrosForm.getRut_empleador());
			reintegros.setNombre_empleador(reintegrosForm.getNombre_empleador());
			reintegros.setOrigen_reintegro(reintegrosForm.getOrigen_reintegro());
			reintegros.setMonto_total_a_reintegrar(reintegrosForm.getMonto_total_a_reintegrar());
			reintegros.setMonto_recuperado(reintegrosForm.getMonto_recuperado());

			reintegros.setMonto_recuperado_acum(reintegrosForm.getMonto_recuperado_acum());
			System.out.println("recu: " + reintegrosForm.getMonto_recuperado_acum());
			reintegros.setTotal_saldo_a_reintegrar(reintegrosForm.getTotal_saldo_a_reintegrar());

			ReintegrosMannager mannager = new ReintegrosMannager();
			mannager.actualizar(reintegros);

			ArrayList listaReintegros = new ArrayList();
			int keyFin = 0;
			listaReintegros = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaReintegros", listaReintegros);

			forward = mapping.findForward("successActualizarReintegros");
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
