package cl.araucana.adminCpe.presentation.struts.actions.convenio;

import java.util.ArrayList;
import java.util.Collection;
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
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.struts.actions.empresa.ListarEmpresasAction;
import cl.araucana.adminCpe.presentation.struts.forms.convenio.ListaConveniosActionForm;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;

import com.bh.talon.User;

/*
 * @(#) ListarCOnveniosAction.java 1.6 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.6
 */
public class ListaConveniosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarEmpresasAction.class);

	private EmpresaMgr empresaMgr;
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
	 * <dd>conveniosLista</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) para la que se quiere mostrar los convenios.</dd>
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
		ListaConveniosActionForm actForm = (ListaConveniosActionForm) form;

		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			this.empresaMgr = new EmpresaMgr(session);
			this.convenioMgr = new ConvenioMgr(session);

			int rut = -1;

			if (request.getParameter("operacion") != null)
			{
				// Lamado desde dentro
				if (request.getParameter("operacion").equals("Crear convenio"))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("crear"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "conveniosCrear");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

					return redirect;
				} else if (request.getParameter("operacion").equals("Crear rango"))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("crearRango"));
					redirect.addParameter("accion", "admin");
					redirect.addParameter("subAccion", "empresas");
					redirect.addParameter("subSubAccion", "conveniosCrearRango");
					redirect.addParameter("rutEmpresa", request.getParameter("rutEmpresa"));

					return redirect;
				}
			} else
				// Lamado desde afuera
				rut = Integer.parseInt(request.getParameter("rutEmpresa"));

			Collection convenios = this.convenioMgr.getConveniosEmpresa(rut);
			ConvenioVO convenio;
			List consulta = new ArrayList();
			for (Iterator it = convenios.iterator(); it.hasNext();)
			{
				convenio = (ConvenioVO) it.next();
				if (convenio.getIdEmpresa() != rut)
					continue;
				consulta.add(convenio);
			}
			Collections.sort(consulta);

			actForm.setRutEmpresa(rut);
			actForm.setRutEmpresaFmt(Utils.formatRut(rut));

			int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
			HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, consulta);
			actForm.setConsulta((List) paginacion.get("data"));
			actForm.setNumeroFilas((List) paginacion.get("paginas"));// enlaces listos, llegar y mostrar. llama a funcion JS

			EmpresaVO empresa = this.empresaMgr.getEmpresa(rut);
			actForm.setNombreEmpresa(empresa.getRazonSocial().trim());

			tx.commit();

			// Formatea lo entregado
			for (Iterator it = consulta.iterator(); it.hasNext();)
			{
				convenio = (ConvenioVO) it.next();
				convenio.setDescripcion(convenio.getDescripcion().trim());
			}

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaConveniosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
