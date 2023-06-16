package cl.laaraucana.simat.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.TablaHomologacionVO;
import cl.laaraucana.simat.forms.TablaHomologacionForm;
import cl.laaraucana.simat.mannagerDB2.TablaHomologacionMannager;

/**
 * @version 	1.0
 * @author
 */
public class TablaHomologacionAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		TablaHomologacionForm homologacionForm = (TablaHomologacionForm) form;
		TablaHomologacionVO homologacionVO = new TablaHomologacionVO();
		TablaHomologacionMannager mannager = new TablaHomologacionMannager();

		String datosActualizar = "";

		homologacionVO.setId_registro(homologacionForm.getId_registro());
		System.out.println("id up." + homologacionForm.getId_registro());
		homologacionVO = mannager.BuscarByIdRegistro(homologacionVO);

		if (homologacionVO != null) {
			datosActualizar = homologacionVO.getClasificacion() + "," + homologacionVO.getDescripcion() + "," + homologacionVO.getCodigo_suceso() + "," + homologacionVO.getCodigo_la();
			System.out.println("Encontrado.");
		} else {
			System.out.println("No se encontro.");
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
