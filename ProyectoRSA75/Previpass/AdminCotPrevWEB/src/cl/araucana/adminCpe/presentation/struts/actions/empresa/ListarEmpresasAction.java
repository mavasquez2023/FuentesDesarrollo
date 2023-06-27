package cl.araucana.adminCpe.presentation.struts.actions.empresa;

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
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.empresa.ListarEmpresasActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;

import com.bh.talon.User;

/*
 * @(#) ListarEmpresasAction.java 1.14 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.14
 */
public class ListarEmpresasAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarEmpresasAction.class);

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
	 * <dd>empresaLista</dd>
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
		ListarEmpresasActionForm actForm = (ListarEmpresasActionForm) form;

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
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			logger.debug("operacion=" + request.getParameter("operacion"));
			if (request.getParameter("operacion") != null && request.getParameter("operacion").equals("Crear empresa"))
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
				redirect.addParameter("accion", request.getParameter("accion"));
				redirect.addParameter("subAccion", request.getParameter("subAccion"));
				redirect.addParameter("subSubAccion", "empresaCrear");
				tx.commit();
				return redirect;
			}

			int rut = 0;
			String razonSocial = "";
			String nombreGrupoConvenio = "";
			int codGrupoConvenio = 0;
			List listaEmpresas = new ArrayList();
			if ((request.getParameter("operacion") != null && request.getParameter("operacion").equals("Buscar")) || bImprimir)
			{
				if ((actForm.getRutOculto() != null) && !actForm.getRutOculto().trim().equals(""))
				{
					String rutTmp = actForm.getRutOculto().trim();
					rutTmp = rutTmp.replaceAll("\\.", "");

					int pos = rutTmp.indexOf('-');
					if (pos > -1)
						rutTmp = rutTmp.substring(0, pos);
					rut = Integer.parseInt(rutTmp);
				}
				if ((actForm.getRazonOculto() != null) && !actForm.getRazonOculto().trim().equals(""))
					razonSocial = actForm.getRazonOculto().trim();
				if ((actForm.getNombreOculto() != null) && !actForm.getNombreOculto().trim().equals(""))
					nombreGrupoConvenio = actForm.getNombreOculto();
				if ((actForm.getIdGrupoOculto() != null) && !actForm.getIdGrupoOculto().trim().equals(""))
					codGrupoConvenio = new Integer(actForm.getIdGrupoOculto().trim()).intValue();
				actForm.setMostrarLista("SI");

				listaEmpresas = empresaMgr.getListaEmpresas(rut, razonSocial, nombreGrupoConvenio, codGrupoConvenio);
			}

			for (Iterator it = listaEmpresas.iterator(); it.hasNext();)
			{
				EmpresaVO empresa = (EmpresaVO) it.next();
				empresa.setIdEmpresaFmt(Utils.formatRut(empresa.getIdEmpresa()));
			}
			Collections.sort(listaEmpresas);
			if (!bImprimir)
			{
				int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, listaEmpresas);
				actForm.setConsulta((List) paginacion.get("data"));
				actForm.setNumeroFilas((List) paginacion.get("paginas"));// enlaces listos, llegar y mostrar. llama a funcion JS
			} else
			{
				actForm.setConsulta(listaEmpresas);
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
