package cl.laaraucana.simat.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.SubsParentalVO;
import cl.laaraucana.simat.forms.SubsParentalForm;
import cl.laaraucana.simat.mannagerDB2.SubsParentalMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class SubsParentalAjaxAction extends Action

{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" llego al action");

		SubsParentalForm subsParentalForm = (SubsParentalForm) form;

		System.out.println("id recibido : " + subsParentalForm.getId());

		SubsParentalVO subsParental = new SubsParentalVO();
		subsParental.setIdParental(subsParentalForm.getId());

		SubsParentalMannager subsParentalMannager = new SubsParentalMannager();

		subsParental = subsParentalMannager.BuscarByID(subsParental);

		String datosActualizar = "";

		if (subsParental != null) {
			System.out.println(subsParental.getIdParental());

			ManejoHoraFecha hfa = new ManejoHoraFecha();

			//System.out.println("Fecha Formateada: "+sdf.format(subsParental.getFecha_emision_documento()));
			datosActualizar = subsParental.getMes_informacion() + "," + subsParental.getCodigo_entidad() + "," + subsParental.getAgencia_entidad() + "," + subsParental.getRut_empleador() + ","
					+ subsParental.getNombre_empleador() + "," + subsParental.getRun_beneficiario_parental() + "," + subsParental.getNombre_beneficiario_parental() + "," + subsParental.getEdad()
					+ "," + subsParental.getSexo() + "," + subsParental.getCalidad_trabajador() + "," + subsParental.getActividad_laboral_trabajador() + ","
					+ subsParental.getCod_comuna_beneficiario() + "," + subsParental.getRun_beneficiario_postnatal() + "," + subsParental.getNombre_beneficiario_postnatal() + ","
					+ subsParental.getNro_licencia()
					+ ","
					+ subsParental.getVinculo_beneficiario_menor()
					+ ","
					+ subsParental.getNro_resolucion()
					+ ","
					+ subsParental.getTipo_extension_postnatal_parental()

					+ ","
					+ subsParental.getFecha_inicio_postnatal_parental_Char()
					+ ","
					+ subsParental.getFecha_termino_postnatal_parental_Char()
					//			+","+hfa.getFechaStringNull(subsParental.getFecha_inicio_postnatal_parental())			
					//			+","+hfa.getFechaStringNull(subsParental.getFecha_termino_postnatal_parental())		

					+ "," + subsParental.getNum_dias_permiso_pagado() + "," + subsParental.getTipo_de_pago() + "," + subsParental.getMonto_sub_dfl44_1978() + "," + subsParental.getMonto_sub_pagado()
					+ "," + subsParental.getTipo_emision() + "," + subsParental.getMes_nomina() + "," + subsParental.getMod_pago() + "," + subsParental.getSerie_documento() + ","
					+ subsParental.getNum_documento()

					+ ","
					+ subsParental.getFecha_emision_documento_Char()
					//+","+hfa.getFechaStringNull(subsParental.getFecha_emision_documento())

					+ "," + subsParental.getMonto_documento() + "," + subsParental.getCodigo_banco() + "," + subsParental.getCta_cte() + "," + subsParental.getCausal_emision() + ","
					+ subsParental.getNum_dias_cotiz_pagados() + "," + subsParental.getMonto_remun_imponible() + "," + subsParental.getMonto_fp() + "," + subsParental.getMonto_salud() + ","
					+ subsParental.getMonto_salud_ad() + "," + subsParental.getMonto_desahucio() + "," + subsParental.getMonto_cotiz_fu() + "," + subsParental.getMonto_cotiz_sc() + ","
					+ subsParental.getEntidad_previsional() + "," + subsParental.getTraspaso() + "," + subsParental.getSubsidio_iniciado() + "," + subsParental.getIndicador_convenio();
			System.out.println("encontrado");
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
