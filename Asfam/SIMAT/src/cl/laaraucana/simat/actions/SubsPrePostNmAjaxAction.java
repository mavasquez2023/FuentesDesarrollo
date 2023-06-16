package cl.laaraucana.simat.actions;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.SubsPrePostNMVO;
import cl.laaraucana.simat.forms.SubsPrePostNMForm;
import cl.laaraucana.simat.mannagerDB2.SubsPrePostNmMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class SubsPrePostNmAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		SubsPrePostNMForm subsPrePostNMForm = (SubsPrePostNMForm) form;
		SubsPrePostNMVO subs = new SubsPrePostNMVO();
		SubsPrePostNmMannager mannager = new SubsPrePostNmMannager();
		String datosActualizar = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		subs.setId(subsPrePostNMForm.getId());

		System.out.println(".id_ajax" + subsPrePostNMForm.getId());

		subs = mannager.BuscarByID(subs);
		ManejoHoraFecha hfa = new ManejoHoraFecha();

		if (subs != null) {
			datosActualizar = subs.getMes_informacion() + "," + subs.getCodigo_entidad() + "," + subs.getAgencia_entidad() + "," + subs.getRut_empleador() + "," + subs.getNombre_empleador() + ","
					+ subs.getRun_beneficiario() + "," + subs.getNombre_beneficiario() + "," + subs.getNro_licencia() + "," + subs.getCodigo_diagnostico() + "," + subs.getVinculo_beneficiario_menor()
					+ "," + subs.getActividad_laboral_trabajador() + "," + subs.getCod_comuna_beneficiario()
					+ ","
					+ subs.getNro_resolucion()
					+ ","
					+ subs.getExtension_postnatal()
					+ ","
					+ subs.getNro_nacimientos()
					+ ","
					+ subs.getNum_dias_licencia_autorizados()

					//+","+sdf.format(subs.getFecha_inicio_reposo_Char())
					//+","+sdf.format(subs.getFecha_termino_reposo_Char())
					+ "," + subs.getFecha_inicio_reposo_Char() + "," + subs.getFecha_termino_reposo_Char()

					+ "," + subs.getNum_dias_sub_pagadados() + "," + subs.getTipo_de_pago() + "," + subs.getMonto_sub_dfl44_1978() + "," + subs.getMonto_sub_pagado() + "," + subs.getTipo_emision()
					+ "," + subs.getMes_nomina() + ","
					+ subs.getMod_pago()
					+ ","
					+ subs.getSerie_documento()
					+ ","
					+ subs.getNum_documento()

					//+","+hfa.getFechaStringNull(subs.getFecha_emision_documento())
					//+","+subs.getFecha_emision_documento()
					+ "," + subs.getFecha_emision_documento_Char() + "," + subs.getMonto_documento() + "," + subs.getCodigo_banco() + "," + subs.getCta_cte() + "," + subs.getCausal_emision() + ","
					+ subs.getNum_dias_cotiz_pagados() + "," + subs.getMonto_renum_imponible() + "," + subs.getMonto_fp() + "," + subs.getMonto_salud() + "," + subs.getMonto_salud_ad() + ","
					+ subs.getMonto_desahucio() + "," + subs.getMonto_cotiz_fu() + "," + subs.getMonto_cotiz_sc() + "," + subs.getEntidad_previsional() + "," + subs.getSubsidio_iniciado() + ","
					+ subs.getIndicador_convenio();

		} else {
			System.out.println(".-no existe id buscado.");
		}

		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		System.out.println(" get form");

		//out.println("hello "+subsParentalForm.getNombre());
		//out.println("hello "+usr);
		out.println(datosActualizar);
		out.flush();
		System.out.println(" antes return");
		return null;
	}
}
