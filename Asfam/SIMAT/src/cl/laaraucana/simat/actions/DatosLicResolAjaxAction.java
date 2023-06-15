package cl.laaraucana.simat.actions;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.DatosLicResolVO;
import cl.laaraucana.simat.forms.DatosLicResolForm;
import cl.laaraucana.simat.mannagerDB2.DatosLicResolMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class DatosLicResolAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DatosLicResolMannager mannager = new DatosLicResolMannager();
		DatosLicResolForm datosLicResolForm = (DatosLicResolForm) form;
		DatosLicResolVO datosLicResol = new DatosLicResolVO();
		String datosActualizar = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		datosLicResol.setId(datosLicResolForm.getId());
		datosLicResol = mannager.BuscarByID(datosLicResol);
		ManejoHoraFecha hfa = new ManejoHoraFecha();
		if (datosLicResol != null) {
			datosActualizar = datosLicResol.getMes_informacion() + "," + datosLicResol.getCodigo_entidad() + "," + datosLicResol.getNro_licencia() + "," + datosLicResol.getReconsideracion() + ","
					+ datosLicResol.getRun_beneficiario() + "," + datosLicResol.getNombre_beneficiario() + "," + datosLicResol.getVinculo_beneficiario_menor()
					+ ","
					+ datosLicResol.getEdad()
					+ ","
					+ datosLicResol.getSexo()
					+ ","
					+ datosLicResol.getActividad_laboral_trabajador()
					+ ","
					+ datosLicResol.getNro_nacimientos()

					//+","+sdf.format(datosLicResol.getFecha_emision_licencia())
					+ ","
					+ datosLicResol.getFecha_emision_licencia_Char()

					//+","+sdf.format(datosLicResol.getFecha_inicio_reposo())
					+ ","
					+ datosLicResol.getFecha_inicio_reposo_Char()

					//+","+sdf.format(datosLicResol.getFecha_termino_reposo())
					+ "," + datosLicResol.getFecha_termino_reposo_Char()

					+ "," + datosLicResol.getNum_dias_licencia() + "," + datosLicResol.getNum_dias_licencia_autorizados() + "," + datosLicResol.getNum_dias_licencia_rechazados() + ","
					+ datosLicResol.getRun_menor_enfermo()
					+ ","
					+ datosLicResol.getNombre_menor_enfermo()

					//+","+hfa.getFechaStringNull(datosLicResol.getFecha_nac_menor_enfermo())
					+ "," + datosLicResol.getFecha_nac_menor_enfermo_Char()

					+ "," + datosLicResol.getCod_comuna_beneficiario() + "," + datosLicResol.getRun_profesional() + "," + datosLicResol.getNombre_profesional() + ","
					+ datosLicResol.getRegistro_colegio_profesional() + "," + datosLicResol.getCod_tipo_licencia() + "," + datosLicResol.getCodigo_diagnostico() + ","
					+ datosLicResol.getRut_empleador() + "," + datosLicResol.getNombre_empleador() + "," + datosLicResol.getCalidad_trabajador() + "," + datosLicResol.getEstado_tramitacion() + ","
					+ datosLicResol.getCausal_rechazo_licencia();
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
