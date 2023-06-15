package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.DatosLicResolVO;
import cl.laaraucana.simat.forms.DatosLicResolForm;
import cl.laaraucana.simat.mannagerDB2.DatosLicResolMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoEspacios;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class DatosLicResolAction extends AbstractAction

{

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {

			ArrayList listaDatosLicResol = new ArrayList();
			DatosLicResolMannager mannager = new DatosLicResolMannager();
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
				listaDatosLicResol = mannager.BuscarListaAvanzar(keyFin);

				if (listaDatosLicResol == null || listaDatosLicResol.size() == 0) {
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
				listaDatosLicResol = mannager.BuscarListaRetroceder(keyInicio);
				if (listaDatosLicResol == null || listaDatosLicResol.size() == 0) {
					msgPaginacion = "no existen registros anteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(0));
					request.setAttribute("keyFinCopy", String.valueOf(cantidad));
					listaDatosLicResol = mannager.BuscarListaRetroceder(0);
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				}

			}

			request.setAttribute("listaDatosLicResol", listaDatosLicResol);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT8");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}

		return (forward);
	}

	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println("Antes try");

		try {
			ArrayList listaDatosLicResol = new ArrayList();
			DatosLicResolMannager mannager = new DatosLicResolMannager();
			DatosLicResolForm datosLicResolForm = (DatosLicResolForm) form;

			String mes_informacion = datosLicResolForm.getMes_informacion();
			System.out.println("Entro Actions");
			listaDatosLicResol = (ArrayList) mannager.BuscarByMesInf(mes_informacion);

			request.setAttribute("listaDatosLicResol", listaDatosLicResol);
			forward = mapping.findForward("successDatosLicResolByMes");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// Finish with
		return (forward);

	}

	public ActionForward buscarByRutBenef(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {

			String rutBenef = (String) request.getParameter("rutBenef");

			ArrayList listaDatosLicResol = new ArrayList();
			DatosLicResolMannager mannager = new DatosLicResolMannager();
			DatosLicResolVO datosLicResol = new DatosLicResolVO();

			datosLicResol.setRun_beneficiario(rutBenef);
			listaDatosLicResol = (ArrayList) mannager.getDatosLicResolByRutBenef(datosLicResol);
			request.setAttribute("listaDatosLicResol", listaDatosLicResol);
			forward = mapping.findForward("sucessBuscarByRutBenef");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByRutBenef

	/*
	 * 
	 */

	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ManejoEspacios ue = new ManejoEspacios();
			DatosLicResolForm datosLicResolForm = (DatosLicResolForm) form;
			DatosLicResolMannager mannager = new DatosLicResolMannager();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			DatosLicResolVO datosLicResol = new DatosLicResolVO();
			ArrayList listaDatosLicResol = new ArrayList();

			datosLicResol.setMes_informacion(datosLicResolForm.getMes_informacion());
			datosLicResol.setCodigo_entidad(datosLicResolForm.getCodigo_entidad());
			datosLicResol.setNro_licencia(datosLicResolForm.getNro_licencia());
			datosLicResol.setReconsideracion(datosLicResolForm.getReconsideracion());
			datosLicResol.setRun_beneficiario(datosLicResolForm.getRun_beneficiario());
			datosLicResol.setNombre_beneficiario(datosLicResolForm.getNombre_beneficiario());
			datosLicResol.setVinculo_beneficiario_menor(datosLicResolForm.getVinculo_beneficiario_menor());
			datosLicResol.setEdad(datosLicResolForm.getEdad());
			datosLicResol.setSexo(datosLicResolForm.getSexo());

			datosLicResol.setActividad_laboral_trabajador(ue.getCompletaCeros(datosLicResolForm.getActividad_laboral_trabajador(), 2));

			datosLicResol.setNro_nacimientos(datosLicResolForm.getNro_nacimientos());

			//datosLicResol.setFecha_emision_licencia(datosLicResolForm.getFecha_emision_licencia());
			try {
				datosLicResol.setFecha_emision_licencia(hfa.getFechaISO_sql(datosLicResolForm.getFecha_emision_licencia()));
			} catch (Exception ex) {
				datosLicResol.setFecha_emision_licencia(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicResol.setFecha_inicio_reposo(datosLicResolForm.getFecha_inicio_reposo());
			try {
				datosLicResol.setFecha_inicio_reposo(hfa.getFechaISO_sql(datosLicResolForm.getFecha_inicio_reposo()));
			} catch (Exception ex) {
				datosLicResol.setFecha_inicio_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicResol.setFecha_termino_reposo(datosLicResolForm.getFecha_termino_reposo());
			try {
				datosLicResol.setFecha_termino_reposo(hfa.getFechaISO_sql(datosLicResolForm.getFecha_termino_reposo()));
			} catch (Exception ex) {
				datosLicResol.setFecha_termino_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			datosLicResol.setNum_dias_licencia(datosLicResolForm.getNum_dias_licencia());
			datosLicResol.setNum_dias_licencia_autorizados(datosLicResolForm.getNum_dias_licencia_autorizados());
			datosLicResol.setNum_dias_licencia_rechazados(datosLicResolForm.getNum_dias_licencia_rechazados());
			datosLicResol.setRun_menor_enfermo(datosLicResolForm.getRun_menor_enfermo());
			datosLicResol.setNombre_menor_enfermo(datosLicResolForm.getNombre_menor_enfermo());

			//datosLicResol.setFecha_nac_menor_enfermo(datosLicResolForm.getFecha_nac_menor_enfermo());
			try {
				datosLicResol.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql(datosLicResolForm.getFecha_nac_menor_enfermo()));
			} catch (Exception ex) {
				datosLicResol.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql("0001-01-01"));
			}
			datosLicResol.setCod_comuna_beneficiario(ue.getCompletaCeros(datosLicResolForm.getCod_comuna_beneficiario(), 5));

			datosLicResol.setRun_profesional(datosLicResolForm.getRun_beneficiario());
			datosLicResol.setNombre_profesional(datosLicResolForm.getNombre_profesional());
			datosLicResol.setRegistro_colegio_profesional(datosLicResolForm.getRegistro_colegio_profesional());
			datosLicResol.setCod_tipo_licencia(datosLicResolForm.getCod_tipo_licencia());
			datosLicResol.setCodigo_diagnostico(datosLicResolForm.getCodigo_diagnostico());
			datosLicResol.setRut_empleador(datosLicResolForm.getRut_empleador());
			datosLicResol.setNombre_empleador(datosLicResolForm.getNombre_empleador());
			datosLicResol.setCalidad_trabajador(datosLicResolForm.getCalidad_trabajador());
			datosLicResol.setEstado_tramitacion(datosLicResolForm.getEstado_tramitacion());
			datosLicResol.setCausal_rechazo_licencia(datosLicResolForm.getCausal_rechazo_licencia());

			mannager.Insertar(datosLicResol);

			int keyFin = 0;
			listaDatosLicResol = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDatosLicResol", listaDatosLicResol);

			forward = mapping.findForward("successInsertarDatosLicResol");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);
	}

	/*
	 * 
	 */
	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ManejoEspacios ue = new ManejoEspacios();
			ArrayList listaDatosLicResol = new ArrayList();
			DatosLicResolVO datosLicResol = new DatosLicResolVO();
			DatosLicResolMannager mannager = new DatosLicResolMannager();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			DatosLicResolForm datosLicResolForm = (DatosLicResolForm) form;

			datosLicResol.setId(datosLicResolForm.getId());
			datosLicResol.setMes_informacion(datosLicResolForm.getMes_informacion());
			datosLicResol.setCodigo_entidad(datosLicResolForm.getCodigo_entidad());
			datosLicResol.setNro_licencia(datosLicResolForm.getNro_licencia());
			datosLicResol.setReconsideracion(datosLicResolForm.getReconsideracion());
			datosLicResol.setRun_beneficiario(datosLicResolForm.getRun_beneficiario());
			datosLicResol.setNombre_beneficiario(datosLicResolForm.getNombre_beneficiario());
			datosLicResol.setVinculo_beneficiario_menor(datosLicResolForm.getVinculo_beneficiario_menor());
			datosLicResol.setEdad(datosLicResolForm.getEdad());
			datosLicResol.setSexo(datosLicResolForm.getSexo());

			datosLicResol.setActividad_laboral_trabajador(ue.getCompletaCeros(datosLicResolForm.getActividad_laboral_trabajador(), 2));

			datosLicResol.setNro_nacimientos(datosLicResolForm.getNro_nacimientos());

			//datosLicResol.setFecha_emision_licencia(datosLicResolForm.getFecha_emision_licencia());
			try {
				datosLicResol.setFecha_emision_licencia(hfa.getFechaISO_sql(datosLicResolForm.getFecha_emision_licencia()));
			} catch (Exception ex) {
				datosLicResol.setFecha_emision_licencia(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicResol.setFecha_inicio_reposo(datosLicResolForm.getFecha_inicio_reposo());
			try {
				datosLicResol.setFecha_inicio_reposo(hfa.getFechaISO_sql(datosLicResolForm.getFecha_inicio_reposo()));
			} catch (Exception ex) {
				datosLicResol.setFecha_inicio_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicResol.setFecha_termino_reposo(datosLicResolForm.getFecha_termino_reposo());
			try {
				datosLicResol.setFecha_termino_reposo(hfa.getFechaISO_sql(datosLicResolForm.getFecha_termino_reposo()));
			} catch (Exception ex) {
				datosLicResol.setFecha_termino_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			datosLicResol.setNum_dias_licencia(datosLicResolForm.getNum_dias_licencia());
			datosLicResol.setNum_dias_licencia_autorizados(datosLicResolForm.getNum_dias_licencia_autorizados());
			datosLicResol.setNum_dias_licencia_rechazados(datosLicResolForm.getNum_dias_licencia_rechazados());
			datosLicResol.setRun_menor_enfermo(datosLicResolForm.getRun_menor_enfermo());
			datosLicResol.setNombre_menor_enfermo(datosLicResolForm.getNombre_menor_enfermo());

			//datosLicResol.setFecha_nac_menor_enfermo(datosLicResolForm.getFecha_nac_menor_enfermo());
			try {
				datosLicResol.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql(datosLicResolForm.getFecha_nac_menor_enfermo()));
			} catch (Exception ex) {
				datosLicResol.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql("0001-01-01"));
			}

			datosLicResol.setCod_comuna_beneficiario(ue.getCompletaCeros(datosLicResolForm.getCod_comuna_beneficiario(), 5));

			datosLicResol.setRun_profesional(datosLicResolForm.getRun_beneficiario());
			datosLicResol.setNombre_profesional(datosLicResolForm.getNombre_profesional());
			datosLicResol.setRegistro_colegio_profesional(datosLicResolForm.getRegistro_colegio_profesional());
			datosLicResol.setCod_tipo_licencia(datosLicResolForm.getCod_tipo_licencia());
			datosLicResol.setCodigo_diagnostico(datosLicResolForm.getCodigo_diagnostico());
			datosLicResol.setRut_empleador(datosLicResolForm.getRut_empleador());
			datosLicResol.setNombre_empleador(datosLicResolForm.getNombre_empleador());
			datosLicResol.setCalidad_trabajador(datosLicResolForm.getCalidad_trabajador());
			datosLicResol.setEstado_tramitacion(datosLicResolForm.getEstado_tramitacion());
			datosLicResol.setCausal_rechazo_licencia(datosLicResolForm.getCausal_rechazo_licencia());

			mannager.actualizar(datosLicResol);

			int keyFin = 0;
			listaDatosLicResol = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDatosLicResol", listaDatosLicResol);

			forward = mapping.findForward("successActualizarControlDocu");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);
	}

	/*
	 * 
	 */
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			DatosLicResolMannager mannager = new DatosLicResolMannager();
			DatosLicResolForm datosLicResolForm = (DatosLicResolForm) form;
			DatosLicResolVO datosLicResol = new DatosLicResolVO();

			datosLicResol.setId(datosLicResolForm.getId());
			mannager.eliminar(datosLicResol);

			ArrayList listaDatosLicResol = new ArrayList();
			int keyFin = 0;
			listaDatosLicResol = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDatosLicResol", listaDatosLicResol);

			forward = mapping.findForward("successEliminarControlDocu");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}
		// Finish with
		return (forward);

	}
}
