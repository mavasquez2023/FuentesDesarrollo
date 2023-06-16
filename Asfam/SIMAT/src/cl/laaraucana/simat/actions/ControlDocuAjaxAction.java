package cl.laaraucana.simat.actions;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.ControlDocuVO;
import cl.laaraucana.simat.forms.ControlDocuForm;
import cl.laaraucana.simat.mannagerDB2.ControlDocuMannager;

/**
 * @version 	1.0
 * @author
 */
public class ControlDocuAjaxAction extends Action

/*
 * clase para mantenedor de tabla SMF05, controlDocu, permite ver los datos del registro a atualizar.
 */
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ActionErrors errors = new ActionErrors();
		//ActionForward forward = new ActionForward(); // return value

		ControlDocuForm controlForm = (ControlDocuForm) form;
		ControlDocuVO control = new ControlDocuVO();
		ControlDocuMannager mannager = new ControlDocuMannager();

		String datosActualizar = "ajax t5";

		control.setIdControlDocu(controlForm.getId());

		System.out.println("idbuscarajax" + controlForm.getId());
		control = mannager.BuscarById(control);

		if (control != null) {
			System.out.println(control.getIdControlDocu());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			datosActualizar = control.getMes_informacion() + "," + control.getCodigo_entidad() + "," + control.getTipo_subsidio() + "," + control.getRut_empleador() + ","
					+ control.getNombre_empleador() + "," + control.getRun_beneficiario() + "," + control.getNombre_beneficiario() + "," + control.getMod_pago() + "," + control.getSerie_documento()
					+ "," + control.getNum_documento()

					//+","+sdf.format(control.getFecha_emision_documento())
					+ "," + control.getFecha_emision_documento_Char()

					+ "," + control.getMonto_documento() + "," + control.getCodigo_banco() + "," + control.getEstado_documento()

					//+","+sdf.format(control.getFecha_cambio_estado())
					+ "," + control.getFecha_cambio_estado_Char();
			System.out.println("Emcontrado");
		} else {
			System.out.println("No existe el identificador que busca para la tabla n° 5.");
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
