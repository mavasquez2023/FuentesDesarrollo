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

import cl.laaraucana.simat.entidades.SubsTscVigVO;
import cl.laaraucana.simat.forms.SubsTscVigForm;
import cl.laaraucana.simat.mannagerDB2.SubsTscVigMannager;

/**
 * @version 	1.0
 * @author
 */
public class SubsTscVigAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		System.out.println(" llego al action");

		SubsTscVigForm subsTscVigForm = (SubsTscVigForm) form;

		System.out.println("id recibido : " + subsTscVigForm.getIdSubsTscVig());
		//System.out.println("id recibido : "+subsParentalForm.getMes_informacion());

		String datosActualizar = "ajax t4";

		SubsTscVigVO subsTscVig = new SubsTscVigVO();
		SubsTscVigVO subsTscVigres = new SubsTscVigVO();

		SubsTscVigMannager subsTscVigMannager = new SubsTscVigMannager();
		subsTscVig.setIdSubsTscVig(subsTscVigForm.getIdSubsTscVig());

		subsTscVigres = subsTscVigMannager.BuscarByID(subsTscVig);

		if (subsTscVigres != null) {
			System.out.println(subsTscVigres.getIdSubsTscVig());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			datosActualizar = subsTscVigres.getMes_informacion() + "," + subsTscVigres.getCodigo_entidad() + "," + subsTscVigres.getAgencia_entidad() + "," + subsTscVigres.getRun_beneficiaria() + ","
					+ subsTscVigres.getNombre_beneficiaria() + "," + subsTscVigres.getEdad() + "," + subsTscVigres.getCod_comuna_beneficiaria() + "," + subsTscVigres.getActividad_laboral_trabajador()
					+ "," + subsTscVigres.getNro_licencia() + "," + subsTscVigres.getCodigo_diagnostico() + "," + subsTscVigres.getNro_nacimientos() + ","
					+ sdf.format(subsTscVigres.getFecha_nacimiento()) + "," + subsTscVigres.getNum_dias_autorizados() + "," + sdf.format(subsTscVigres.getFecha_inicio_subsidio()) + ","
					+ sdf.format(subsTscVigres.getFecha_termino_subsidio()) + "," + subsTscVigres.getNum_dias_subsidio_pagado() + "," + subsTscVigres.getMonto_sub_pagado() + ","
					+ subsTscVigres.getTipo_emision() + "," + subsTscVigres.getMes_nomina() + "," + subsTscVigres.getMod_pago() + "," + subsTscVigres.getSerie_documento() + ","
					+ subsTscVigres.getNum_documento() + "," + sdf.format(subsTscVigres.getFecha_emision_documento()) + "," + subsTscVigres.getMonto_documento() + ","
					+ subsTscVigres.getCodigo_banco() + "," + subsTscVigres.getCta_cte() + "," + subsTscVigres.getCausal_emision() + "," + subsTscVigres.getNum_dias_cotiz_pagados() + ","
					+ subsTscVigres.getMonto_fp() + "," + subsTscVigres.getMonto_salud() + "," + subsTscVigres.getMonto_cotiz() + "," + subsTscVigres.getEntidad_previsional() + ","
					+ subsTscVigres.getSubsidio_iniciado() + "," + subsTscVigres.getIdSubsTscVig();
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
