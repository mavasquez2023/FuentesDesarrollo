package cl.laaraucana.simat.actions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.UsuariosVO;
import cl.laaraucana.simat.forms.UsuariosForm;
import cl.laaraucana.simat.mannagerDB2.UsuariosMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

/**
 * @version 	1.0
 * @author
 */
public class UsuariosAction extends AbstractAction

{

	public ActionForward buscarById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		System.out.println("Llego al Action buscar id(rut) user");
		try {
			ArrayList listaUsuarios = new ArrayList();
			UsuariosForm usuariosForm = (UsuariosForm) form;
			UsuariosMannager mannager = new UsuariosMannager();
			String nombreUsuario = usuariosForm.getNombre_usuario();
			UsuariosVO uvo = new UsuariosVO();
			uvo = (UsuariosVO) mannager.BuscarByName(nombreUsuario);
			ManejoHoraFecha mhf = new ManejoHoraFecha();
			if (uvo != null) {
				String test = uvo.getUltima_coneccion();
				boolean j = mhf.checkFechaDefault(test);
				if (j) {
					uvo.setUltima_coneccion("-");
				}
				listaUsuarios.add(uvo);
			} else {
				listaUsuarios = new ArrayList();
			}
			System.out.println("encontrados: " + listaUsuarios.size());
			request.setAttribute("listaUsuarios", listaUsuarios);
			forward = mapping.findForward("successBusquedaUsuariosById");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		return (forward);
	}

	public ActionForward insertar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value    	
		try {
			String msg = "";
			UsuariosForm usuariosForm = (UsuariosForm) form;
			UsuariosMannager mannager = new UsuariosMannager();
			UsuariosVO usuario = new UsuariosVO();
			System.out.println("Llego al Action insertar user");

			usuario.setNombre_usuario(usuariosForm.getNombre_usuario());
			usuario.setAcceso(usuariosForm.getAcceso());

			ManejoHoraFecha hfa = new ManejoHoraFecha();
			Timestamp aux = null;
			//aux=hfa.getFechaHoraTimeStamp();
			aux = hfa.getTimeStampDefault();
			usuario.setUltima_coneccion(aux.toString());

			msg = mannager.Insertar(usuario);

			ArrayList listaUsuarios = new ArrayList();
			ArrayList listaAux = new ArrayList();
			listaAux = mannager.BuscarTodo();
			UsuariosVO uvo = new UsuariosVO();
			String test = "";
			ManejoHoraFecha mhf = new ManejoHoraFecha();
			Iterator it = listaAux.iterator();
			while (it.hasNext()) {
				uvo = (UsuariosVO) it.next();
				test = uvo.getUltima_coneccion();
				boolean j = mhf.checkFechaDefault(test);
				if (j) {
					uvo.setUltima_coneccion("-");
				}
				listaUsuarios.add(uvo);
			}

			request.setAttribute("listaUsuarios", listaUsuarios);
			request.setAttribute("msg", msg);
			forward = mapping.findForward("successInsertarUsuarios");

		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}//end insertar

	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value 
		try {
			UsuariosForm usuariosForm = (UsuariosForm) form;
			UsuariosMannager mannager = new UsuariosMannager();
			UsuariosVO usuario = new UsuariosVO();
			usuario.setId(usuariosForm.getId());
			mannager.Eliminar(usuario);
			ArrayList listaUsuarios = new ArrayList();
			ArrayList listaAux = new ArrayList();
			listaAux = mannager.BuscarTodo();
			UsuariosVO uvo = new UsuariosVO();
			String test = "";
			ManejoHoraFecha mhf = new ManejoHoraFecha();
			Iterator it = listaAux.iterator();
			while (it.hasNext()) {
				uvo = (UsuariosVO) it.next();
				test = uvo.getUltima_coneccion();
				boolean j = mhf.checkFechaDefault(test);
				if (j) {
					uvo.setUltima_coneccion("-");
				}
				listaUsuarios.add(uvo);
			}

			request.setAttribute("listaUsuarios", listaUsuarios);
			forward = mapping.findForward("successInsertarUsuarios");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value

		try {
			UsuariosForm usuariosForm = (UsuariosForm) form;
			UsuariosMannager mannager = new UsuariosMannager();
			UsuariosVO usuario = new UsuariosVO();
			System.out.println("Llego al Action actualizar user");

			usuario.setId(usuariosForm.getId());
			usuario.setNombre_usuario(usuariosForm.getNombre_usuario());
			usuario.setAcceso(usuariosForm.getAcceso());
			//Timestamp aux=null;
			//aux=usuariosForm.getUltima_coneccion();
			//usuario.setUltima_coneccion(aux.toString());
			usuario.setUltima_coneccion(usuariosForm.getUltima_coneccion());

			mannager.Actualizar(usuario);

			ArrayList listaUsuarios = new ArrayList();
			ArrayList listaAux = new ArrayList();
			listaAux = mannager.BuscarTodo();
			UsuariosVO uvo = new UsuariosVO();
			String test = "";
			ManejoHoraFecha mhf = new ManejoHoraFecha();
			Iterator it = listaAux.iterator();
			while (it.hasNext()) {
				uvo = (UsuariosVO) it.next();
				test = uvo.getUltima_coneccion();
				boolean j = mhf.checkFechaDefault(test);
				if (j) {
					uvo.setUltima_coneccion("-");
				}
				listaUsuarios.add(uvo);
			}
			request.setAttribute("listaUsuarios", listaUsuarios);
			forward = mapping.findForward("successInsertarUsuarios");

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}
		// Finish with
		return (forward);
	}

}
