package cl.laaraucana.simat.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.UsuariosVO;
import cl.laaraucana.simat.forms.UsuariosForm;
import cl.laaraucana.simat.mannagerDB2.UsuariosMannager;

/**
 * @version 	1.0
 * @author
 */
public class UsuariosAjaxAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		UsuariosForm usuariosForm = (UsuariosForm) form;
		UsuariosMannager mannager = new UsuariosMannager();
		UsuariosVO usuario = new UsuariosVO();
		String datosActualizar = "t9";

		usuario.setId(usuariosForm.getId());
		usuario = mannager.BuscarByIdUnico(usuario);

		if (usuario != null) {

			datosActualizar = usuario.getId() + "," + usuario.getNombre_usuario() + "," + usuario.getAcceso() + "," + usuario.getUltima_coneccion();
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
