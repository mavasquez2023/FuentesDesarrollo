package cl.laaraucana.simat.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.DocsRevalReemVO;
import cl.laaraucana.simat.forms.DocsRevalReemForm;
import cl.laaraucana.simat.mannagerDB2.DocsRevalReemMannager;

/**
 * @version 	1.0
 * @author
 */
public class DocsRevalReemAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ActionErrors errors = new ActionErrors();
		//ActionForward forward = new ActionForward(); // return value

		DocsRevalReemForm docsRevalReemForm = (DocsRevalReemForm) form;
		DocsRevalReemVO docs = new DocsRevalReemVO();
		DocsRevalReemMannager mannager = new DocsRevalReemMannager();
		String datosActualizar = "";

		System.out.println("llego actionajax");

		docs.setId(docsRevalReemForm.getId());

		System.out.println("form: " + docsRevalReemForm.getId());
		docs = mannager.BuscarByID(docs);

		if (docs != null) {
			datosActualizar = docs.getMes_informacion() + "," + docs.getCodigo_entidad() + "," + docs.getTiposubsidio() + "," + docs.getMod_pago_original() + "," + docs.getCodigo_banco_original()
					+ "," + docs.getSerie_documento_original() + "," + docs.getNum_documento_original()

					//+","+docs.getFecha_emision_documento_original()
					+ "," + docs.getFecha_emision_documento_original_Char()

					+ "," + docs.getMonto_documento_original() + "," + docs.getEstado_documento_original() + "," + docs.getMod_pago_nuevo() + "," + docs.getCodigo_banco_nuevo() + ","
					+ docs.getSerie_documento_nuevo() + "," + docs.getNum_documento_nuevo()

					//+","+docs.getFecha_emision_documento_nuevo()
					+ "," + docs.getFecha_emision_documento_nuevo_Char()

					+ "," + docs.getMonto_documento_nuevo();
			System.out.println("Encontrado");
		} else {
			System.out.println("No existe el identificador que busca para la tabla n° 5.");
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
