package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.SubsPrePostNMVO;
import cl.laaraucana.simat.forms.SubsPrePostNMForm;
import cl.laaraucana.simat.mannagerDB2.SubsPrePostNmMannager;
import cl.laaraucana.simat.utiles.LectorPropiedades;
import cl.laaraucana.simat.utiles.ManejoEspacios;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class SubsPrePostNmAction extends AbstractAction {

	public ActionForward mostrarPaginacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {

			ArrayList listaSubsPrePostNM = new ArrayList();
			SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();

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
				listaSubsPrePostNM = mannager.BuscarListaAvanzar(keyFin);

				if (listaSubsPrePostNM == null || listaSubsPrePostNM.size() == 0) {
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
				listaSubsPrePostNM = mannager.BuscarListaRetroceder(keyInicio);
				if (listaSubsPrePostNM == null || listaSubsPrePostNM.size() == 0) {
					msgPaginacion = "no existen registros anteriores";
					request.setAttribute("msgPaginacion", msgPaginacion);
					request.setAttribute("keyInicioCopy", String.valueOf(0));
					request.setAttribute("keyFinCopy", String.valueOf(cantidad));
					listaSubsPrePostNM = mannager.BuscarListaRetroceder(0);
				} else {
					request.setAttribute("keyInicioCopy", String.valueOf(keyInicio));
					request.setAttribute("keyFinCopy", String.valueOf(keyFin));
				}
			}

			request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarPaginacionT2");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}

		return (forward);
	}

	public ActionForward buscarByMesInf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			ArrayList listaSubsPrePostNM = new ArrayList();
			SubsPrePostNMForm subsPrePostNMForm = (SubsPrePostNMForm) form;
			String mes_informacion = subsPrePostNMForm.getMes_informacion();
			System.out.println("Llego al Action" + mes_informacion);
			SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
			listaSubsPrePostNM = (ArrayList) mannager.BuscarByMesInf(mes_informacion);
			request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
			forward = mapping.findForward("sucessBuscarSubsPrePostNmByMes");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByMesInf

	public ActionForward buscarByNumDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			ArrayList listaSubsPrePostNM = new ArrayList();

			System.out.println("Llego al Action buscar Num Doc");
			SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
			SubsPrePostNMVO subsPrePostNM = new SubsPrePostNMVO();
			String numDoc = (String) request.getParameter("numDoc");
			subsPrePostNM.setNum_documento(numDoc);
			listaSubsPrePostNM = (ArrayList) mannager.getSubsPrePostNMByNumDoc(subsPrePostNM);
			request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
			forward = mapping.findForward("sucessBuscarByNumDoc");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end buscarByNumDoc

	public ActionForward buscarByRutBenef(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			ArrayList listaSubsPrePostNM = new ArrayList();
			String rutBenef = (String) request.getParameter("rutBenef");

			System.out.println("Llego al Action buscar Num Doc: " + rutBenef);
			SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
			SubsPrePostNMVO subsPrePostNM = new SubsPrePostNMVO();

			subsPrePostNM.setRun_beneficiario(rutBenef);
			listaSubsPrePostNM = (ArrayList) mannager.getSubsPrePostNMByRutBenef(subsPrePostNM);
			request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
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
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			ManejoEspacios ue = new ManejoEspacios();
			SubsPrePostNMForm subsPrePostNMForm = (SubsPrePostNMForm) form;
			SubsPrePostNMVO subs = new SubsPrePostNMVO();
			SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
			System.out.println("Llego al action");

			subs.setMes_informacion(subsPrePostNMForm.getMes_informacion());
			subs.setCodigo_entidad(subsPrePostNMForm.getCodigo_entidad());
			subs.setAgencia_entidad(subsPrePostNMForm.getAgencia_entidad());
			subs.setRut_empleador(subsPrePostNMForm.getRut_empleador());
			subs.setNombre_empleador(subsPrePostNMForm.getNombre_empleador());
			subs.setRun_beneficiario(subsPrePostNMForm.getRun_beneficiario());
			subs.setNombre_beneficiario(subsPrePostNMForm.getNombre_beneficiario());
			subs.setNro_licencia(subsPrePostNMForm.getNro_licencia());
			subs.setCodigo_diagnostico(subsPrePostNMForm.getCodigo_diagnostico());
			subs.setVinculo_beneficiario_menor(subsPrePostNMForm.getVinculo_beneficiario_menor());

			/*campo excepcion, se deben completar con ceros.*/
			subs.setActividad_laboral_trabajador(ue.getCompletaCeros(subsPrePostNMForm.getActividad_laboral_trabajador(), 2));
			subs.setCod_comuna_beneficiario(ue.getCompletaCeros(subsPrePostNMForm.getCod_comuna_beneficiario(), 5));

			subs.setNro_resolucion(subsPrePostNMForm.getNro_resolucion());
			subs.setExtension_postnatal(subsPrePostNMForm.getExtension_postnatal());
			subs.setNro_nacimientos(subsPrePostNMForm.getNro_nacimientos());
			subs.setNum_dias_licencia_autorizados(subsPrePostNMForm.getNum_dias_licencia_autorizados());

			//subs.setFecha_inicio_reposo(subsPrePostNMForm.getFecha_inicio_reposo());
			try {
				subs.setFecha_inicio_reposo(hfa.getFechaISO_sql(subsPrePostNMForm.getFecha_inicio_reposo()));
			} catch (Exception ex) {
				subs.setFecha_inicio_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			//subs.setFecha_termino_reposo(subsPrePostNMForm.getFecha_termino_reposo());
			try {
				subs.setFecha_termino_reposo(hfa.getFechaISO_sql(subsPrePostNMForm.getFecha_termino_reposo()));
			} catch (Exception ex) {
				subs.setFecha_termino_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			subs.setNum_dias_sub_pagadados(subsPrePostNMForm.getNum_dias_sub_pagadados());
			subs.setTipo_de_pago(subsPrePostNMForm.getTipo_de_pago());

			subs.setMonto_sub_dfl44_1978(subsPrePostNMForm.getMonto_sub_dfl44_1978());
			subs.setMonto_sub_pagado(subsPrePostNMForm.getMonto_sub_pagado());
			subs.setTipo_emision(subsPrePostNMForm.getTipo_emision());
			subs.setMes_nomina(subsPrePostNMForm.getMes_nomina());
			subs.setMod_pago(subsPrePostNMForm.getMod_pago());
			subs.setSerie_documento(subsPrePostNMForm.getSerie_documento());
			subs.setNum_documento(subsPrePostNMForm.getNum_documento());
			try {
				subs.setFecha_emision_documento(hfa.getFechaISO_sql(subsPrePostNMForm.getFecha_emision_documento()));
			} catch (Exception ex) {
				subs.setFecha_emision_documento(hfa.getFechaISO_sql("0001-01-01"));
			}

			subs.setMonto_documento(subsPrePostNMForm.getMonto_documento());

			/*campo excepcion, se deben completar con ceros.*/
			if (subsPrePostNMForm.getCodigo_banco().trim().equalsIgnoreCase("")) {
				subs.setCodigo_banco("");
			} else {
				subs.setCodigo_banco(ue.getCompletaCeros(subsPrePostNMForm.getCodigo_banco(), 3));
			}

			subs.setCta_cte(subsPrePostNMForm.getCta_cte());
			subs.setCausal_emision(subsPrePostNMForm.getCausal_emision());
			subs.setNum_dias_cotiz_pagados(subsPrePostNMForm.getNum_dias_cotiz_pagados());
			subs.setMonto_renum_imponible(subsPrePostNMForm.getMonto_renum_imponible());
			subs.setMonto_fp(subsPrePostNMForm.getMonto_fp());
			subs.setMonto_salud(subsPrePostNMForm.getMonto_salud());
			subs.setMonto_salud_ad(subsPrePostNMForm.getMonto_salud_ad());
			subs.setMonto_desahucio(subsPrePostNMForm.getMonto_desahucio());
			subs.setMonto_cotiz_fu(subsPrePostNMForm.getMonto_cotiz_fu());
			subs.setMonto_cotiz_sc(subsPrePostNMForm.getMonto_cotiz_sc());

			subs.setEntidad_previsional(subsPrePostNMForm.getEntidad_previsional());
			subs.setSubsidio_iniciado(subsPrePostNMForm.getSubsidio_iniciado());

			subs.setIndicador_convenio(subsPrePostNMForm.getIndicador_convenio());
			//al insertar nuevo registro siempre en cero
			subs.setServ_salud(0);
			//al insertar calcular suma campos montoSalud+montoFP
			subs.setMonto_cot(subs.getMonto_salud() + subs.getMonto_fp());

			System.out.println("saliendo action");

			mannager.Insertar(subs);

			ArrayList listaSubsPrePostNM = new ArrayList();
			int keyFin = 0;
			listaSubsPrePostNM = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
			forward = mapping.findForward("successInsertarSubsPrePostNm");
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
			SubsPrePostNMForm subsPrePostNMForm = (SubsPrePostNMForm) form;
			SubsPrePostNMVO subsPrePostNM = new SubsPrePostNMVO();
			SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
			subsPrePostNM.setId(subsPrePostNMForm.getId());
			mannager.eliminar(subsPrePostNM);

			ArrayList listaSubsPrePostNM = new ArrayList();
			int keyFin = 0;
			listaSubsPrePostNM = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
			forward = mapping.findForward("successEliminarSubsPrePostNm");
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
		//TODO Revisar
		try {
			ManejoHoraFecha hfa = new ManejoHoraFecha();
			ManejoEspacios ue = new ManejoEspacios();
			SubsPrePostNMForm subsPrePostNMForm = (SubsPrePostNMForm) form;
			SubsPrePostNMVO subsPrePostNM = new SubsPrePostNMVO();
			SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();

			subsPrePostNM.setId(subsPrePostNMForm.getId());
			subsPrePostNM.setMes_informacion(subsPrePostNMForm.getMes_informacion());
			subsPrePostNM.setCodigo_entidad(subsPrePostNMForm.getCodigo_entidad());
			subsPrePostNM.setAgencia_entidad(subsPrePostNMForm.getAgencia_entidad());
			subsPrePostNM.setRut_empleador(subsPrePostNMForm.getRut_empleador());
			subsPrePostNM.setNombre_empleador(subsPrePostNMForm.getNombre_empleador());
			subsPrePostNM.setRun_beneficiario(subsPrePostNMForm.getRun_beneficiario());
			subsPrePostNM.setNombre_beneficiario(subsPrePostNMForm.getNombre_beneficiario());
			subsPrePostNM.setNro_licencia(subsPrePostNMForm.getNro_licencia());
			subsPrePostNM.setCodigo_diagnostico(subsPrePostNMForm.getCodigo_diagnostico());

			subsPrePostNM.setVinculo_beneficiario_menor(subsPrePostNMForm.getVinculo_beneficiario_menor());
			subsPrePostNM.setActividad_laboral_trabajador(ue.getCompletaCeros(subsPrePostNMForm.getActividad_laboral_trabajador(), 2));
			subsPrePostNM.setCod_comuna_beneficiario(ue.getCompletaCeros(subsPrePostNMForm.getCod_comuna_beneficiario(), 5));
			subsPrePostNM.setNro_resolucion(subsPrePostNMForm.getNro_resolucion());
			subsPrePostNM.setExtension_postnatal(subsPrePostNMForm.getExtension_postnatal());
			subsPrePostNM.setNro_nacimientos(subsPrePostNMForm.getNro_nacimientos());
			subsPrePostNM.setNum_dias_licencia_autorizados(subsPrePostNMForm.getNum_dias_licencia_autorizados());
			//subsPrePostNM.setFecha_inicio_reposo(subsPrePostNMForm.getFecha_inicio_reposo());
			try {
				subsPrePostNM.setFecha_inicio_reposo(hfa.getFechaISO_sql(subsPrePostNMForm.getFecha_inicio_reposo()));
			} catch (Exception ex) {
				subsPrePostNM.setFecha_inicio_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}

			//subsPrePostNM.setFecha_termino_reposo(subsPrePostNMForm.getFecha_termino_reposo());
			try {
				subsPrePostNM.setFecha_termino_reposo(hfa.getFechaISO_sql(subsPrePostNMForm.getFecha_termino_reposo()));
			} catch (Exception ex) {
				subsPrePostNM.setFecha_termino_reposo(hfa.getFechaISO_sql("0001-01-01"));
			}
			subsPrePostNM.setNum_dias_sub_pagadados(subsPrePostNMForm.getNum_dias_sub_pagadados());

			subsPrePostNM.setTipo_de_pago(subsPrePostNMForm.getTipo_de_pago());
			subsPrePostNM.setMonto_sub_dfl44_1978(subsPrePostNMForm.getMonto_sub_dfl44_1978());
			subsPrePostNM.setMonto_sub_pagado(subsPrePostNMForm.getMonto_sub_pagado());
			subsPrePostNM.setTipo_emision(subsPrePostNMForm.getTipo_emision());
			subsPrePostNM.setMes_nomina(subsPrePostNMForm.getMes_nomina());
			subsPrePostNM.setMod_pago(subsPrePostNMForm.getMod_pago());
			subsPrePostNM.setSerie_documento(subsPrePostNMForm.getSerie_documento());
			subsPrePostNM.setNum_documento(subsPrePostNMForm.getNum_documento());
			try {
				subsPrePostNM.setFecha_emision_documento(hfa.getFechaISO_sql(subsPrePostNMForm.getFecha_emision_documento()));
			} catch (Exception ex) {
				subsPrePostNM.setFecha_emision_documento(hfa.getFechaISO_sql("0001-01-01"));
			}
			subsPrePostNM.setMonto_documento(subsPrePostNMForm.getMonto_documento());

			/*campo excepcion, se deben completar con ceros.*/
			if (subsPrePostNMForm.getCodigo_banco().trim().equalsIgnoreCase("")) {
				subsPrePostNM.setCodigo_banco("");
			} else {
				subsPrePostNM.setCodigo_banco(ue.getCompletaCeros(subsPrePostNMForm.getCodigo_banco(), 3));
			}
			subsPrePostNM.setCta_cte(subsPrePostNMForm.getCta_cte());
			subsPrePostNM.setCausal_emision(subsPrePostNMForm.getCausal_emision());
			subsPrePostNM.setNum_dias_cotiz_pagados(subsPrePostNMForm.getNum_dias_cotiz_pagados());
			subsPrePostNM.setMonto_renum_imponible(subsPrePostNMForm.getMonto_renum_imponible());
			subsPrePostNM.setMonto_fp(subsPrePostNMForm.getMonto_fp());
			subsPrePostNM.setMonto_salud(subsPrePostNMForm.getMonto_salud());
			subsPrePostNM.setMonto_salud_ad(subsPrePostNMForm.getMonto_salud_ad());
			subsPrePostNM.setMonto_desahucio(subsPrePostNMForm.getMonto_desahucio());
			subsPrePostNM.setMonto_cotiz_fu(subsPrePostNMForm.getMonto_cotiz_fu());

			subsPrePostNM.setMonto_cotiz_sc(subsPrePostNMForm.getMonto_cotiz_sc());
			subsPrePostNM.setEntidad_previsional(subsPrePostNMForm.getEntidad_previsional());
			subsPrePostNM.setSubsidio_iniciado(subsPrePostNMForm.getSubsidio_iniciado());

			subsPrePostNM.setIndicador_convenio(subsPrePostNMForm.getIndicador_convenio());

			//    al actualizar calcular suma campos montoSalud+montoFP
			subsPrePostNM.setMonto_cot(subsPrePostNM.getMonto_salud() + subsPrePostNM.getMonto_fp());

			mannager.actualizar(subsPrePostNM);
			ArrayList listaSubsPrePostNM = new ArrayList();
			int keyFin = 0;
			listaSubsPrePostNM = mannager.BuscarListaAvanzar(keyFin);
			request.setAttribute("listaSubsPrePostNM", listaSubsPrePostNM);
			forward = mapping.findForward("successActualizarSubsPrePostNm");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}
}
