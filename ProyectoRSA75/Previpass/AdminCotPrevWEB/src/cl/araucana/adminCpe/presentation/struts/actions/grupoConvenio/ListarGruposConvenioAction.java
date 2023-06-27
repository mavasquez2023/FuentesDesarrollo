package cl.araucana.adminCpe.presentation.struts.actions.grupoConvenio;

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
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.struts.forms.grupoConvenio.GruposConvenioActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

import com.bh.talon.User;

/*
 * @(#) ListarGruposConvenioAction.java 1.9 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author malvarez
 * 
 * @version 1.9
 */
public class ListarGruposConvenioAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarGruposConvenioAction.class);
	private ConvenioMgr convenioMgr;

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
	 * <dd>grupoConveniosLista</dd>
	 * </dl>
	 * 
	 * @param usuario
	 *            el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param mapping
	 *            el objeto con los mapeos de accion para este <code>Action</code>.
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

		GruposConvenioActionForm actForm = (GruposConvenioActionForm) form;

		Session session = null;
		Transaction tx = null;
		boolean bImprimir = false;
		if (request.getParameter("imprimir") != null)
			bImprimir = true;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			this.convenioMgr = new ConvenioMgr(session);

			if ((request.getParameter("operacion") != null) && (request.getParameter("operacion").equals("Crear grupo")))
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "grupoCrear");

				tx.commit();
				return redirect;
			}

			Integer idGrupoBuscar = null;
			String nombreGrupoBuscar = null;

			if ((actForm.getIdHidden() != null) && !actForm.getIdHidden().trim().equals(""))
				idGrupoBuscar = new Integer(actForm.getIdHidden());
			if ((actForm.getNombreHidden() != null) && !actForm.getNombreHidden().trim().equals(""))
				nombreGrupoBuscar = actForm.getNombreHidden().trim();

			List listaGrupos = actForm.getIdHidden() == null ? new ArrayList() : this.convenioMgr.getListaGruposConvenio(idGrupoBuscar, nombreGrupoBuscar);
			List consulta = new ArrayList();
			actForm.setConsulta(consulta);

			// Formatea antes de entregar
			GrupoConvenioVO grupo, grupoP;
			for (Iterator it = listaGrupos.iterator(); it.hasNext();)
			{
				grupo = (GrupoConvenioVO) it.next();
				grupoP = new GrupoConvenioVO();
				grupoP.setIdGrupoConvenio(grupo.getIdGrupoConvenio());
				grupoP.setNombre(grupo.getNombre().trim());
				grupoP.setHabilitado(grupo.getHabilitado());
				consulta.add(grupoP);
			}

			Collections.sort(consulta);
			// paginacion
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
			if (!bImprimir)
				return mapping.findForward("exito");
			return mapping.findForward("imprimir");

		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListarEmpresasAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
