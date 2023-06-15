package cl.laaraucana.simat.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.ReintegrosVO;
import cl.laaraucana.simat.forms.ReintegrosForm;
import cl.laaraucana.simat.mannagerDB2.ReintegrosMannager;

/**
 * @version 	1.0
 * @author
 */
public class ReintegrosAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		ReintegrosForm reintegrosForm = (ReintegrosForm) form;
		ReintegrosVO reintegros = new ReintegrosVO();

		String datosActualizar = "ajax t1";

		reintegros.setId(reintegrosForm.getId());

		ReintegrosMannager mannager = new ReintegrosMannager();
		reintegros = mannager.BuscarByID(reintegros);
		if (reintegros != null) {
			datosActualizar = reintegros.getMes_informacion() + "," + reintegros.getCodigo_entidad() + "," + reintegros.getMes_nomina() + "," + reintegros.getRun_beneficiario() + ","
					+ reintegros.getNombre_beneficiario() + "," + reintegros.getTipo_subsidio() + "," + reintegros.getNro_licencia() + "," + reintegros.getRut_empleador() + ","
					+ reintegros.getNombre_empleador() + "," + reintegros.getOrigen_reintegro() + "," + reintegros.getMonto_total_a_reintegrar() + "," + reintegros.getMonto_recuperado() + ","
					+ reintegros.getMonto_recuperado_acum() + "," + reintegros.getTotal_saldo_a_reintegrar();

		} else {
			System.out.println("no, encontrado");
		}

		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		System.out.println(" get form");

		out.println(datosActualizar);
		out.flush();
		System.out.println(" antes return");

		return null;
	}
}
