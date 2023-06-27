package cl.araucana.adminCpe.presentation.struts.actions.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.PersonaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.usuario.ListarAdministradorActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.Usuario;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;

import com.bh.talon.User;

/*
 * @(#) ListaAdministradorAction.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author vagurto
 * @author cchamblas
 * 
 * @version 1.8
 */
public class ListarAdministradorAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarUsuariosAction.class);

	/**
	 * listar administrador
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ListarAdministradorActionForm actForm = (ListarAdministradorActionForm) form;

		Session session = null;
		Transaction tx = null;
		ActionErrors ae = new ActionErrors();
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
			if (actForm.getMostrarLista() == null)
				actForm.setMostrarLista("SI");

			logger.info("ListarAdministradorAction.doTask():operacion *** \"" + request.getParameter("operacion") + "\"");
			if (request.getParameter("mostrarLista") == null)
				actForm.setMostrarLista("SI");
			else if ("NO".equals(request.getParameter("mostrarLista")))
				actForm.setMostrarLista("NO");
			if (request.getParameter("operacion") == null)
				actForm.setMostrarLista("NO");
			else
			{
				if (request.getParameter("operacion").equals("Crear Administrador"))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "usuarioCrear");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));
					return redirect;
				}
			}
			List personas = personaMgr.getListaAdministrador(actForm.getRut(), actForm.getNombre(), actForm.getApellidos(), actForm.getNombreGrupoConvenio(), actForm.getCodigoGrupoConvenio(), actForm
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
			{
				actForm.setConsulta(consulta);
			}
			tx.commit();

			this.saveMessages(request, am);
			this.saveErrors(request, ae);
			if (!bImprimir)
				return mapping.findForward("exito");
			return mapping.findForward("imprimir");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListarAdministradorAction.doTask()", ex);
			if (tx != null)
				tx.rollback();

			return mapping.findForward("error");
		}
	}
}
