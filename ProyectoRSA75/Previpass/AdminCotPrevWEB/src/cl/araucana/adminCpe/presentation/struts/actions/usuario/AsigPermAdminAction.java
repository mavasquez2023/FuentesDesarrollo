package cl.araucana.adminCpe.presentation.struts.actions.usuario;

import java.util.ArrayList;
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
import cl.araucana.adminCpe.presentation.struts.forms.usuario.AsigPermAdminActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;

import com.bh.talon.User;

/*
 * @(#) AsigPermAdminAction.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.8
 */
public class AsigPermAdminAction extends AppAction
{
	private static Logger logger = Logger.getLogger(AsigPermAdminAction.class);

	/**
	 * Asigna permisos administrador
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form2, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionMessages am = new ActionMessages();
		AsigPermAdminActionForm form = (AsigPermAdminActionForm) form2;
		Session session = null;
		Transaction tx = null;
		int idUsuario = -1;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			PersonaMgr personaMgr = new PersonaMgr(session);
			String operacion = (String) request.getAttribute("operacion");
			if (operacion == null)
				if (request.getParameter("operacion") != null && !request.getParameter("operacion").trim().equals(""))
					operacion = request.getParameter("operacion").trim();
				else
					operacion = "";
			logger.info("AsigPermAdminAction:operacion:" + (operacion == null ? "(null)" : operacion) + "::");
			if (operacion != null && (operacion.equals("Siguiente") || operacion.equals("Buscar")))
			{// carga permisos ya asignados, hace busquedas para buscar nuevos permisos
			// accion=admin&subAccion=usuarios&subSubAccion=usuarioEditar&operacion=Siguiente&idUsuario=${filaConsulta.idUsuario}"
				idUsuario = (form.getIdAdmin() > 0 ? form.getIdAdmin() : new Integer(request.getParameter("idUsuario")).intValue());
				if (form.getIdAdmin() == 0)
					form.setIdAdmin(idUsuario);
				PersonaVO persona = personaMgr.getPersona(idUsuario);
				form.setIdAdminFmt(Utils.formatRut(idUsuario));
				form.setNombre(persona.getNombres().trim());
				form.setApellidos(persona.getApellidoPaterno().trim() + " " + persona.getApellidoMaterno().trim());

				List empresasAdmin = personaMgr.getListaEmpresasEsAdmin(idUsuario);
				form.setPermisos(empresasAdmin);
				/** ***** FILTRO PERMISOS DE ADMINISTRADOR ****** */
				List empresasAdminBorrar = new ArrayList();
				List empresasAdminOtros = new ArrayList();
				EmpresaVO lPAE;
				for (Iterator it = empresasAdmin.iterator(); it.hasNext();)
				{
					lPAE = (EmpresaVO) it.next();
					logger.debug(Integer.toString(lPAE.getIdEmpresa()));
					if (!lPAE.isEsAdmin())
					{
						empresasAdminBorrar.add(lPAE);
						if ((form.getRutEmpresa() == null) || form.getRutEmpresa().trim().equals(""))
							if ((form.getRazonSocial() == null) || form.getRazonSocial().trim().equals(""))
								continue;
						if (!form.getRutEmpresa().trim().equals("") && (lPAE.getIdEmpresa() != Utils.desFormatRut(form.getRutEmpresa().trim())))
							continue;
						if (!form.getRazonSocial().trim().equals("") && (lPAE.getRazonSocial().trim().toUpperCase().indexOf(form.getRazonSocial().trim().toUpperCase()) == -1))
							continue;
						lPAE.setEsAdmin(true);
						empresasAdminOtros.add(lPAE);
					}
				}
				List administrador = new ArrayList();
				if (form.getIdGrupo() != null && form.getNombreGrupo() != null)
					if (!"".equals(form.getIdGrupo().trim()) || !"".equals(form.getNombreGrupo().trim()))
						administrador = personaMgr.getListaEmpresasEsAdminGrupo(form.getIdGrupo(), form.getNombreGrupo(), idUsuario);

				empresasAdmin.removeAll(empresasAdminBorrar);
				if (empresasAdminOtros.size() > 0)
					form.setPermisosPorAsignar(empresasAdminOtros);
				else
					form.setPermisosPorAsignar(administrador);
			} else if (operacion != null && operacion.equals("Guardar"))
			{// borra permisos anteriores y guarda nuevos, solo si hay al menos un permiso seleccionado (si no hay permisos para guardar, muestra mensaje)
				idUsuario = form.getIdAdmin();
				AdministradorVO admin = personaMgr.getAdministrador(idUsuario);
				personaMgr.guardaEmpresasEsAdmin(admin, form.getPermisosPorAsignar());

				ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "administradorLista");

				tx.commit();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Usuario", Utils.formatRut(idUsuario)));
				this.saveMessages(request.getSession(), am);
				logger.debug("-> despues de guardar");
				return redirect;
			} else if (operacion != null && operacion.equals("Cancelar"))
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "administradorLista");
				tx.commit();

				return redirect;
			}
			tx.commit();
			return mapping.findForward("asigPermAdmin");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en AsigPermisosEncargado:doTask()", ex);
			if (tx != null)
				tx.rollback();

			return mapping.findForward("error");
		}
	}

	/**
	 * parametro
	 * 
	 * @param parametro
	 * @return
	 */
	boolean noVacio(String parametro)
	{
		return parametro != null && !"".equals(parametro);
	}
}
