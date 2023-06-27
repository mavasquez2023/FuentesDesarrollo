package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Evento;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaEventoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Evento evento=new Evento();
		evento.setFechaIngreso(new Date());
		evento.setTipo("");
		//evento.setUsuario("User");
		evento.setComentario("");
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();		
		opciones.add("Grabar Evento");
		opcionesValores.add("1");

		// pongo el objeto Concepto y Opciones en memoria
		request.getSession(false).setAttribute("evento",evento);
		request.setAttribute("evento.opciones",opciones);
		request.setAttribute("evento.opciones.valores",opcionesValores);
	
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("evento");
		this.saveToken(request);
		return (forward);

	}
}
