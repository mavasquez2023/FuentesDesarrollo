package cl.araucana.adminCpe.presentation.struts.actions.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.PersonaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.usuario.ListarUsuariosActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.Usuario;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;

import com.bh.talon.User;

/*
 * @(#) ListaUsuariosAction.java 1.15 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.15
 */
public class ListarUsuariosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarUsuariosAction.class);

	/**
	 * Procesa el request para generar la respuesta html que se le entregara al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>admin</dd>
	 * <dt>subAccion</dt>
	 * <dd>empresas</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>usuarioLista</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "borrar", se eliminara el usuario con del parametro idUsuarioBorrar. Si es "Crear Usuario", redireccionara a la creacion de usuarios.</dd>
	 * </dl>
	 * 
	 * @param usuario
	 *            el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param mapping
	 *            el objeto con los mapeos de accion para este <code>Action</code>
	 * @param form
	 *            el objeto <code>ActionForm</code> correspondiente
	 * @param request
	 *            el objeto <code>request</code> con los parametros para procesar
	 * @param response
	 *            el objeto <code>response</code> con la respuesta al cliente
	 * @return el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListarUsuariosActionForm actForm = (ListarUsuariosActionForm) form;

		Session session = null;
		Transaction tx = null;
		ActionMessages am = new ActionMessages();
		boolean bImprimir = false;
		if (request.getParameter("imprimir") != null)
			bImprimir = true;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			PersonaMgr personaMgr = new PersonaMgr(session);
			actForm.setMostrarLista("SI");
			logger.info("ListarUsuariosAction.doTask():operacion *** \"" + request.getParameter("operacion") + "\"");
			if (request.getParameter("operacion") == null)
				actForm.setMostrarLista("NO");
			else
			{
				if (request.getParameter("operacion").equals("borrar"))
				{
					int idUsuarioBorrar = Integer.parseInt(request.getParameter("idUsuarioBorrar"));
					logger.debug("BORRARA: " + idUsuarioBorrar);
					personaMgr.borraEncargadoLector(idUsuarioBorrar);

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borraPermisos", "encargado de convenios", Utils.formatRut(idUsuarioBorrar)));
					this.saveMessages(request, am);
				} else if (request.getParameter("operacion").equals("Crear Usuario"))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioCrear");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

					return redirect;
				}
			}
			List personas = personaMgr.getListaEncLector(actForm.getRut(), actForm.getNombre(), actForm.getApellidos(), actForm.getNombreGrupoConvenio(), actForm.getCodigoGrupoConvenio(), actForm
					.getRazonSocial(), actForm.getRutEmpresa());

			// Formatea lo entregado
			List consulta = new ArrayList();
			Usuario usuarioFormat;
			PersonaVO persona;

			// Agregar paginacion
			for (Iterator it = personas.iterator(); it.hasNext();)
			{
				persona = (PersonaVO) it.next();
				usuarioFormat = new Usuario();
				usuarioFormat.setRut(persona.getIdPersona().intValue());
				usuarioFormat.setRutFormat(Utils.formatRut(persona.getIdPersona().intValue()));
				usuarioFormat.setNombre(persona.getNombres().trim());
				usuarioFormat.setApellidos(persona.getApellidoPaterno().trim() + " " + persona.getApellidoMaterno().trim());
				consulta.add(usuarioFormat);
			}
			Collections.sort(consulta);
			if (!bImprimir)
			{
				int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, consulta);
				actForm.setConsulta((List) paginacion.get("data"));
				actForm.setNumeroFilas((List) paginacion.get("paginas"));// enlaces listos, llegar y mostrar. llama a funcion JS
			} else
				actForm.setConsulta(consulta);
			tx.commit();

			this.saveMessages(request, am);
			if (!bImprimir)
				return mapping.findForward("exito");
			return mapping.findForward("imprimir");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListarUsuariosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();

			return mapping.findForward("error");
		}
	}
}
