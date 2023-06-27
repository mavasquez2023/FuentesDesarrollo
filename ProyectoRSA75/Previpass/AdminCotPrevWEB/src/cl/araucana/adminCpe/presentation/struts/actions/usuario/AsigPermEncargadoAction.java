package cl.araucana.adminCpe.presentation.struts.actions.usuario;

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
import cl.araucana.adminCpe.presentation.struts.forms.usuario.AsigPermEncargadoActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;

import com.bh.talon.User;

/*
 * @(#) AsigPermEncargadoAction.java 1.6 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.6
 */
public class AsigPermEncargadoAction extends AppAction
{
	private static Logger logger = Logger.getLogger(AsigPermEncargadoAction.class);

	/**
	 * Asigna permisos encargado
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionMessages am = new ActionMessages();
		AsigPermEncargadoActionForm form = (AsigPermEncargadoActionForm) formulario;
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
			logger.info("AsigPermEncargadoAction:operacion:" + operacion + "::");
			if (operacion.equals("Siguiente") || operacion.equals("Buscar"))
			{// carga permisos ya asignados, hace busquedas para buscar nuevos permisos
			// URL:accion=admin&subAccion=usuarios&subSubAccion=usuarioEditar&operacion=Siguiente&idUsuario=${filaConsulta.idUsuario}"
				idUsuario = (form.getIdEncargado() > 0 ? form.getIdEncargado() : new Integer(request.getParameter("idUsuario")).intValue());
				if (form.getIdEncargado() == 0)
					form.setIdEncargado(idUsuario);
				PersonaVO persona = personaMgr.getPersona(idUsuario);
				form.setIdEncargadoFmt(Utils.formatRut(idUsuario));
				form.setNombre(persona.getNombres().trim());
				form.setApellidos(persona.getApellidoPaterno().trim() + " " + persona.getApellidoMaterno().trim());

				/*
				 * retorna lista con permisos ya asignados, en posicion 0 y lista con permisos por asignar, en posicion 1
				 */
				String razonSocBusqueda = (form.getRazonSocial() != null ? form.getRazonSocial().trim() : "");
				String nomGrBusqueda = (form.getNombreGrupo() != null ? form.getNombreGrupo().trim() : "");
				int idEmpBusqueda = (form.getRutEmpresa() != null && !"".equals(form.getRutEmpresa().trim()) ? new Integer(Utils.desFormatRut(form.getRutEmpresa().trim())).intValue()
						: Constants.RUT_EMPRESA_FALSA);
				int idGRBusqueda = (form.getIdGrupo() != null && !"".equals(form.getIdGrupo().trim()) ? new Integer(form.getIdGrupo().trim()).intValue() : Constants.ID_GRUPO_FALSO);
				List permisos = personaMgr.getPermEncargadoLector(idUsuario, idEmpBusqueda, razonSocBusqueda, idGRBusqueda, nomGrBusqueda);
				form.setPermisos((List) permisos.get(0));
				form.setNewPermisos((List) permisos.get(1));

			} else if (operacion.equals("Guardar"))
			{// borra permisos anteriores y guarda nuevos, solo si hay al menos un permiso seleccionado (si no hay permisos para guardar, muestra mensaje)

				idUsuario = form.getIdEncargado();
				PersonaVO persona = personaMgr.getPersona(idUsuario);

				EncargadoVO encargado = new EncargadoVO();
				encargado.setIdEncargado(idUsuario);
				encargado.setHabilitado(1);
				encargado.setPersona(persona);
				personaMgr.guardaAccesoEncargadoLector(encargado, form.getPermisos(), form.getNewPermisos());

				ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "usuarioLista");

				tx.commit();

				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Encargado", Utils.formatRut(idUsuario)));
				this.saveMessages(request.getSession(), am);

				return redirect;
			} else if (operacion.equals("Cancelar"))
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("Cancelar"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "usuarioLista");
				tx.commit();

				return redirect;
			}
			tx.commit();
			return mapping.findForward("asigPermEncargado");
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
