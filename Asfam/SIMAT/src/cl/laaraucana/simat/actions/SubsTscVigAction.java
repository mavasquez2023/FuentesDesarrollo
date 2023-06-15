package cl.laaraucana.simat.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.SubsTscVigVO;
import cl.laaraucana.simat.forms.SubsTscVigForm;
import cl.laaraucana.simat.mannagerDB2.SubsTscVigMannager;

/**
 * @version 	1.0
 * @author
 */

/*
 * action para mantenedor tabla 4, no dispone de opciones.
 * */

public class SubsTscVigAction extends AbstractAction {
	public ActionForward buscarByMesInformacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" antes try");
		try {

			SubsTscVigMannager subsTscVigMannager = new SubsTscVigMannager();
			ArrayList listaSubsTscVig = new ArrayList();
			SubsTscVigForm subsTscVigForm = (SubsTscVigForm) form;
			listaSubsTscVig = subsTscVigMannager.BuscarByMes(subsTscVigForm.getMes_informacion());

			request.setAttribute("listaSubsTscVig", listaSubsTscVig);
			System.out.println(" forw");
			forward = mapping.findForward("sucessBuscarSubsTscVigByMes");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		return (forward);
	}

	public ActionForward eliminarByID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println(" antes try");
		try {

			SubsTscVigVO subsTscVig = new SubsTscVigVO();
			SubsTscVigForm subsTscVigForm = (SubsTscVigForm) form;
			subsTscVig.setIdSubsTscVig(subsTscVigForm.getIdSubsTscVig());

			SubsTscVigMannager subsTscVigMannager = new SubsTscVigMannager();
			subsTscVigMannager.Eliminar(subsTscVig);

			ArrayList listaSubsTscVig = new ArrayList();
			request.setAttribute("listaSubsTscVig", listaSubsTscVig);
			System.out.println(" forw");
			forward = mapping.findForward("sucessEliminarSubsTscVig");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println(" antes try");
		try {
			SubsTscVigMannager subsTscVigMannager = new SubsTscVigMannager();
			SubsTscVigVO subsTscVig = new SubsTscVigVO();

			SubsTscVigForm subsTscVigForm = (SubsTscVigForm) form;

			System.out.println(" ini try");

			subsTscVig.setIdSubsTscVig(0);
			subsTscVig.setActividad_laboral_trabajador(subsTscVigForm.getActividad_laboral_trabajador());
			subsTscVig.setAgencia_entidad(subsTscVigForm.getAgencia_entidad());
			subsTscVig.setCausal_emision(subsTscVigForm.getCausal_emision());
			subsTscVig.setCod_comuna_beneficiaria(subsTscVigForm.getCod_comuna_beneficiaria());
			subsTscVig.setCodigo_banco(subsTscVigForm.getCodigo_banco());
			subsTscVig.setCodigo_diagnostico(subsTscVigForm.getCodigo_diagnostico());
			subsTscVig.setCodigo_entidad(subsTscVigForm.getCodigo_entidad());
			subsTscVig.setCta_cte(subsTscVigForm.getCta_cte());
			subsTscVig.setEdad(subsTscVigForm.getEdad());
			subsTscVig.setFecha_emision_documento(subsTscVigForm.getFecha_emision_documento());

			subsTscVig.setFecha_inicio_subsidio(subsTscVigForm.getFecha_inicio_subsidio());
			subsTscVig.setFecha_nacimiento(subsTscVigForm.getFecha_nacimiento());
			subsTscVig.setFecha_termino_subsidio(subsTscVigForm.getFecha_termino_subsidio());
			subsTscVig.setMes_informacion(subsTscVigForm.getMes_informacion());
			subsTscVig.setMes_nomina(subsTscVigForm.getMes_nomina());
			subsTscVig.setMod_pago(subsTscVigForm.getMod_pago());
			subsTscVig.setMonto_cotiz(subsTscVigForm.getMonto_cotiz());
			subsTscVig.setMonto_documento(subsTscVigForm.getMonto_documento());
			subsTscVig.setMonto_fp(subsTscVigForm.getMonto_fp());
			subsTscVig.setEntidad_previsional(subsTscVigForm.getEntidad_previsional());

			subsTscVig.setMonto_salud(subsTscVigForm.getMonto_salud());
			subsTscVig.setMonto_sub_pagado(subsTscVigForm.getMonto_sub_pagado());
			subsTscVig.setNombre_beneficiaria(subsTscVigForm.getNombre_beneficiaria());
			subsTscVig.setNro_licencia(subsTscVigForm.getNro_licencia());
			subsTscVig.setNro_nacimientos(subsTscVigForm.getNro_nacimientos());
			subsTscVig.setNum_dias_autorizados(subsTscVigForm.getNum_dias_autorizados());
			subsTscVig.setNum_dias_cotiz_pagados(subsTscVigForm.getNum_dias_cotiz_pagados());
			subsTscVig.setNum_dias_subsidio_pagado(subsTscVigForm.getNum_dias_subsidio_pagado());
			subsTscVig.setNum_documento(subsTscVigForm.getNum_documento());
			subsTscVig.setRun_beneficiaria(subsTscVigForm.getRun_beneficiaria());

			subsTscVig.setSerie_documento(subsTscVigForm.getSerie_documento());
			subsTscVig.setSubsidio_iniciado(subsTscVigForm.getSubsidio_iniciado());
			subsTscVig.setTipo_emision(subsTscVigForm.getTipo_emision());

			subsTscVigMannager.Insertar(subsTscVig);

			ArrayList listaSubsTscVig = new ArrayList();
			request.setAttribute("listaSubsTscVig", listaSubsTscVig);
			System.out.println(" forw");
			forward = mapping.findForward("sucessInsertarSubsTscVig");
		} catch (Exception e) { // Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		return (forward);
	}

	public ActionForward actualizarByID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println(" antes try");
		try {
			SubsTscVigVO subsTscVig = new SubsTscVigVO();

			SubsTscVigForm subsTscVigForm = (SubsTscVigForm) form;

			System.out.println(" ini try");
			subsTscVig.setIdSubsTscVig(subsTscVigForm.getIdSubsTscVig());
			subsTscVig.setActividad_laboral_trabajador(subsTscVigForm.getActividad_laboral_trabajador());
			subsTscVig.setAgencia_entidad(subsTscVigForm.getAgencia_entidad());
			subsTscVig.setCausal_emision(subsTscVigForm.getCausal_emision());
			subsTscVig.setCod_comuna_beneficiaria(subsTscVigForm.getCod_comuna_beneficiaria());
			subsTscVig.setCodigo_banco(subsTscVigForm.getCodigo_banco());
			subsTscVig.setCodigo_diagnostico(subsTscVigForm.getCodigo_diagnostico());
			subsTscVig.setCodigo_entidad(subsTscVigForm.getCodigo_entidad());
			subsTscVig.setCta_cte(subsTscVigForm.getCta_cte());
			subsTscVig.setEdad(subsTscVigForm.getEdad());
			subsTscVig.setFecha_emision_documento(subsTscVigForm.getFecha_emision_documento());
			subsTscVig.setFecha_inicio_subsidio(subsTscVigForm.getFecha_inicio_subsidio());
			subsTscVig.setFecha_nacimiento(subsTscVigForm.getFecha_nacimiento());
			subsTscVig.setFecha_termino_subsidio(subsTscVigForm.getFecha_termino_subsidio());
			subsTscVig.setMes_informacion(subsTscVigForm.getMes_informacion());
			subsTscVig.setMes_nomina(subsTscVigForm.getMes_nomina());
			subsTscVig.setMod_pago(subsTscVigForm.getMod_pago());
			subsTscVig.setMonto_cotiz(subsTscVigForm.getMonto_cotiz());
			subsTscVig.setMonto_documento(subsTscVigForm.getMonto_documento());
			subsTscVig.setMonto_fp(subsTscVigForm.getMonto_fp());
			subsTscVig.setEntidad_previsional(subsTscVigForm.getEntidad_previsional());
			subsTscVig.setMonto_salud(subsTscVigForm.getMonto_salud());
			subsTscVig.setMonto_sub_pagado(subsTscVigForm.getMonto_sub_pagado());
			subsTscVig.setNombre_beneficiaria(subsTscVigForm.getNombre_beneficiaria());
			subsTscVig.setNro_licencia(subsTscVigForm.getNro_licencia());
			subsTscVig.setNro_nacimientos(subsTscVigForm.getNro_nacimientos());
			subsTscVig.setNum_dias_autorizados(subsTscVigForm.getNum_dias_autorizados());
			subsTscVig.setNum_dias_cotiz_pagados(subsTscVigForm.getNum_dias_cotiz_pagados());
			subsTscVig.setNum_dias_subsidio_pagado(subsTscVigForm.getNum_dias_subsidio_pagado());
			subsTscVig.setNum_documento(subsTscVigForm.getNum_documento());
			subsTscVig.setRun_beneficiaria(subsTscVigForm.getRun_beneficiaria());
			subsTscVig.setSerie_documento(subsTscVigForm.getSerie_documento());
			subsTscVig.setSubsidio_iniciado(subsTscVigForm.getSubsidio_iniciado());
			subsTscVig.setTipo_emision(subsTscVigForm.getTipo_emision());

			System.out.println("s VO: " + subsTscVig.getActividad_laboral_trabajador());
			System.out.println("s VO: " + subsTscVig.getAgencia_entidad());
			System.out.println("s VO: " + subsTscVig.getFecha_emision_documento());

			SubsTscVigMannager subsTscVigMannager = new SubsTscVigMannager();
			subsTscVigMannager.Actualizar(subsTscVig);

			ArrayList listaSubsTscVig = new ArrayList();
			request.setAttribute("listaSubsTscVig", listaSubsTscVig);
			System.out.println(" forw");
			forward = mapping.findForward("sucessActualizarSubsTscVig");

		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

}
