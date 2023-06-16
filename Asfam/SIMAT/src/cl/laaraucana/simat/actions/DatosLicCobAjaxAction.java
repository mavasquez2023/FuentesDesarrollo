package cl.laaraucana.simat.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.DatosLicCobVO;
import cl.laaraucana.simat.forms.DatosLicCobForm;
import cl.laaraucana.simat.mannagerDB2.DatosLicCobMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class DatosLicCobAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ActionErrors errors = new ActionErrors();
		//ActionForward forward = new ActionForward(); // return value

		DatosLicCobForm datosLicCobForm = (DatosLicCobForm) form;
		DatosLicCobMannager mannager = new DatosLicCobMannager();
		DatosLicCobVO datosLicCob = new DatosLicCobVO();
		String datosActualizar = "";

		datosLicCob.setId(datosLicCobForm.getId());
		datosLicCob = mannager.BuscarByID(datosLicCob);

		ManejoHoraFecha hfa = new ManejoHoraFecha();
		if (datosLicCob != null) {
			datosActualizar = datosLicCob.getMes_informacion() + "," + datosLicCob.getCodigo_entidad() + "," + datosLicCob.getNro_licencia() + "," + datosLicCob.getRun_beneficiario() + ","
					+ datosLicCob.getNombre_beneficiario()
					+ ","
					+ datosLicCob.getEdad()
					+ ","
					+ datosLicCob.getSexo()

					//+","+datosLicCob.getFecha_emision_licencia()
					+ ","
					+ datosLicCob.getFecha_emision_licencia_Char()

					//+","+datosLicCob.getFecha_inicio_reposo()
					+ ","
					+ datosLicCob.getFecha_inicio_reposo_Char()

					//+","+datosLicCob.getFecha_termino_reposo()
					+ "," + datosLicCob.getFecha_termino_reposo_Char()

					+ "," + datosLicCob.getNro_dias_licencia() + "," + datosLicCob.getNum_dias_licencia_autorizados() + "," + datosLicCob.getRun_menor_enfermo()
					+ ","
					+ datosLicCob.getNombre_menor_enfermo()

					//+","+hfa.getFechaStringNull(datosLicCob.getFecha_nac_menor_enfermo())
					+ "," + datosLicCob.getFecha_nac_menor_enfermo_Char()

					+ "," + datosLicCob.getCod_tipo_licencia() + "," + datosLicCob.getCod_comuna_beneficiario() + "," + datosLicCob.getRun_profesional() + "," + datosLicCob.getNombre_profesional()
					+ "," + datosLicCob.getRegistro_colegio_profesional() + "," + datosLicCob.getCodigo_diagnostico() + "," + datosLicCob.getRut_empleador() + "," + datosLicCob.getNombre_empleador()
					+ "," + datosLicCob.getCalidad_trabajador();
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
