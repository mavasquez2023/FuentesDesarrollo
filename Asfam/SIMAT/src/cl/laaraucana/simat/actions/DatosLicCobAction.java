package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.DatosLicCobVO;
import cl.laaraucana.simat.forms.DatosLicCobForm;
import cl.laaraucana.simat.mannagerDB2.DatosLicCobMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoEspacios;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class DatosLicCobAction extends AbstractAction
/*
 * clase para realizar operaciones sobre mantenedor de tabla SMF07, DatosLibCob.
 */
{

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {

			DatosLicCobMannager mannager = new DatosLicCobMannager();
			ArrayList listaDatosLicCob = new ArrayList();
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
				listaDatosLicCob = mannager.BuscarListaAvanzar(keyFin);

				if (listaDatosLicCob == null || listaDatosLicCob.size() == 0) {
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
				listaDatosLicCob = mannager.BuscarListaRetroceder(keyInicio);
				if (listaDatosLicCob == null || listaDatosLicCob.size() == 0) {
					msgPaginacion = "no existen registros anteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(0));
					request.setAttribute("keyFinCopy", String.valueOf(cantidad));
					listaDatosLicCob = mannager.BuscarListaRetroceder(0);
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				}
			}

			request.setAttribute("listaDatosLicCob", listaDatosLicCob);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT7");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}

		return (forward);
	}

	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			DatosLicCobForm datosLicCobForm = (DatosLicCobForm) form;
			DatosLicCobMannager mannager = new DatosLicCobMannager();
			ArrayList listaDatosLicCob = new ArrayList();

			String mes_informacion = datosLicCobForm.getMes_informacion();
			listaDatosLicCob = (ArrayList) mannager.BuscarByMesInf(mes_informacion);

			request.setAttribute("listaDatosLicCob", listaDatosLicCob);

			forward = mapping.findForward("successDatosLicCobByMes");
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
			ArrayList listaDatosLicCob = new ArrayList();
			String rutBenef = (String) request.getParameter("rutBenef");

			DatosLicCobMannager mannager = new DatosLicCobMannager();
			DatosLicCobVO datosLicCob = new DatosLicCobVO();

			datosLicCob.setRun_beneficiario(rutBenef);
			listaDatosLicCob = (ArrayList) mannager.getDatosLicCobByRutBenef(datosLicCob);
			request.setAttribute("listaDatosLicCob", listaDatosLicCob);
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
			ManejoEspacios ue = new ManejoEspacios();
			DatosLicCobForm datosLicCobForm = (DatosLicCobForm) form;
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			ArrayList listaDatosLicCob = new ArrayList();
			DatosLicCobMannager mannager = new DatosLicCobMannager();
			DatosLicCobVO datos = new DatosLicCobVO();

			System.out.println("llego al action");

			datos.setMes_informacion(datosLicCobForm.getMes_informacion());
			datos.setCodigo_entidad(datosLicCobForm.getCodigo_entidad());
			datos.setNro_licencia(datosLicCobForm.getNro_licencia());
			datos.setRun_beneficiario(datosLicCobForm.getRun_beneficiario());
			datos.setNombre_beneficiario(datosLicCobForm.getNombre_beneficiario());
			datos.setEdad(datosLicCobForm.getEdad());
			datos.setSexo(datosLicCobForm.getSexo());
			//    			datosLicCob.setFecha_emision_licencia(datosLicCobForm.getFecha_emision_licencia());
			try {
				datos.setFecha_emision_licencia(hfa.getFechaISO_sql(datosLicCobForm.getFecha_emision_licencia()));
			} catch (Exception ex) {
				datos.setFecha_emision_licencia(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicCob.setFecha_inicio_reposo(datosLicCobForm.getFecha_inicio_reposo());
			try {
				datos.setFecha_inicio_reposo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_inicio_reposo()));
			} catch (Exception ex) {
				datos.setFecha_inicio_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicCob.setFecha_termino_reposo(datosLicCobForm.getFecha_termino_reposo());
			try {
				datos.setFecha_termino_reposo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_termino_reposo()));
			} catch (Exception ex) {
				datos.setFecha_termino_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			datos.setNro_dias_licencia(datosLicCobForm.getNro_dias_licencia());
			datos.setNum_dias_licencia_autorizados(datosLicCobForm.getNum_dias_licencia_autorizados());
			datos.setRun_menor_enfermo(datosLicCobForm.getRun_menor_enfermo());
			datos.setNombre_menor_enfermo(datosLicCobForm.getNombre_menor_enfermo());

			try {
				datos.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_nac_menor_enfermo()));
			} catch (Exception ex) {
				datos.setFecha_nac_menor_enfermo(null);
			}
			//datosLicCob.setFecha_nac_menor_enfermo(datosLicCobForm.getFecha_nac_menor_enfermo());
			try {
				datos.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_nac_menor_enfermo()));
			} catch (Exception ex) {
				datos.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql("0001-01-01"));
			}

			datos.setCod_tipo_licencia(datosLicCobForm.getCod_tipo_licencia());

			datos.setCod_comuna_beneficiario(ue.getCompletaCeros(datosLicCobForm.getCod_comuna_beneficiario(), 5));

			datos.setRun_profesional(datosLicCobForm.getRun_profesional());
			datos.setNombre_profesional(datosLicCobForm.getNombre_profesional());
			datos.setRegistro_colegio_profesional(datosLicCobForm.getRegistro_colegio_profesional());
			datos.setCodigo_diagnostico(datosLicCobForm.getCodigo_diagnostico());
			datos.setRut_empleador(datosLicCobForm.getRut_empleador());
			datos.setNombre_empleador(datosLicCobForm.getNombre_empleador());
			datos.setCalidad_trabajador(datosLicCobForm.getCalidad_trabajador());

			mannager.Insertar(datos);

			int keyFin = 0;
			listaDatosLicCob = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDatosLicCob", listaDatosLicCob);
			forward = mapping.findForward("successInsertarDatosLicCob");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			DatosLicCobForm datosLicCobForm = (DatosLicCobForm) form;
			DatosLicCobVO datosLicCob = new DatosLicCobVO();
			DatosLicCobMannager mannager = new DatosLicCobMannager();

			datosLicCob.setId(datosLicCobForm.getId());
			mannager.eliminar(datosLicCob);

			ArrayList listaDatosLicCob = new ArrayList();
			int keyFin = 0;
			listaDatosLicCob = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDatosLicCob", listaDatosLicCob);
			forward = mapping.findForward("successEliminarDatosLicCob");
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// Finish with
		return (forward);

	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			ManejoEspacios ue = new ManejoEspacios();
			DatosLicCobForm datosLicCobForm = (DatosLicCobForm) form;
			DatosLicCobVO datosLicCob = new DatosLicCobVO();
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			DatosLicCobMannager mannager = new DatosLicCobMannager();

			datosLicCob.setId(datosLicCobForm.getId());
			datosLicCob.setMes_informacion(datosLicCobForm.getMes_informacion());
			datosLicCob.setCodigo_entidad(datosLicCobForm.getCodigo_entidad());
			datosLicCob.setNro_licencia(datosLicCobForm.getNro_licencia());
			datosLicCob.setRun_beneficiario(datosLicCobForm.getRun_beneficiario());
			datosLicCob.setNombre_beneficiario(datosLicCobForm.getNombre_beneficiario());
			datosLicCob.setEdad(datosLicCobForm.getEdad());
			datosLicCob.setSexo(datosLicCobForm.getSexo());

			//datosLicCob.setFecha_emision_licencia(datosLicCobForm.getFecha_emision_licencia());
			try {
				datosLicCob.setFecha_emision_licencia(hfa.getFechaISO_sql(datosLicCobForm.getFecha_emision_licencia()));
			} catch (Exception ex) {
				datosLicCob.setFecha_emision_licencia(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicCob.setFecha_inicio_reposo(datosLicCobForm.getFecha_inicio_reposo());
			try {
				datosLicCob.setFecha_inicio_reposo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_inicio_reposo()));
			} catch (Exception ex) {
				datosLicCob.setFecha_inicio_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			//datosLicCob.setFecha_termino_reposo(datosLicCobForm.getFecha_termino_reposo());
			try {
				datosLicCob.setFecha_termino_reposo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_termino_reposo()));
			} catch (Exception ex) {
				datosLicCob.setFecha_termino_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			datosLicCob.setNro_dias_licencia(datosLicCobForm.getNro_dias_licencia());
			datosLicCob.setNum_dias_licencia_autorizados(datosLicCobForm.getNum_dias_licencia_autorizados());
			datosLicCob.setRun_menor_enfermo(datosLicCobForm.getRun_menor_enfermo());
			datosLicCob.setNombre_menor_enfermo(datosLicCobForm.getNombre_menor_enfermo());

			try {
				datosLicCob.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_nac_menor_enfermo()));
			} catch (Exception ex) {
				datosLicCob.setFecha_nac_menor_enfermo(null);
			}
			//datosLicCob.setFecha_nac_menor_enfermo(datosLicCobForm.getFecha_nac_menor_enfermo());
			try {
				datosLicCob.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql(datosLicCobForm.getFecha_nac_menor_enfermo()));
			} catch (Exception ex) {
				datosLicCob.setFecha_nac_menor_enfermo(hfa.getFechaISO_sql("0001-01-01"));
			}

			datosLicCob.setCod_tipo_licencia(datosLicCobForm.getCod_tipo_licencia());

			datosLicCob.setCod_comuna_beneficiario(ue.getCompletaCeros(datosLicCobForm.getCod_comuna_beneficiario(), 5));

			datosLicCob.setRun_profesional(datosLicCobForm.getRun_profesional());
			datosLicCob.setNombre_profesional(datosLicCobForm.getNombre_profesional());
			datosLicCob.setRegistro_colegio_profesional(datosLicCobForm.getRegistro_colegio_profesional());
			datosLicCob.setCodigo_diagnostico(datosLicCobForm.getCodigo_diagnostico());
			datosLicCob.setRut_empleador(datosLicCobForm.getRut_empleador());
			datosLicCob.setNombre_empleador(datosLicCobForm.getNombre_empleador());
			datosLicCob.setCalidad_trabajador(datosLicCobForm.getCalidad_trabajador());

			mannager.actualizar(datosLicCob);

			ArrayList listaDatosLicCob = new ArrayList();
			int keyFin = 0;
			listaDatosLicCob = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaDatosLicCob", listaDatosLicCob);
			forward = mapping.findForward("successActualizarDatosLicCob");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}
}
