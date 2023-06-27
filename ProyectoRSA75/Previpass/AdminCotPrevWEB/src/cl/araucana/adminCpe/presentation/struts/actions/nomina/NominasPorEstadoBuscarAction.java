package cl.araucana.adminCpe.presentation.struts.actions.nomina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.NominaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.nomina.NominaForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;

import com.bh.talon.User;

/*
 * @(#) NominasPorEstadoBuscarAction.java 1.14 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.14
 */
public class NominasPorEstadoBuscarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(NominasPorEstadoBuscarAction.class);

	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NominaForm frm = (NominaForm) form;
		String mappingFoward = "buscar";
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();

			// Objetos locales
			NominaMgr nominaMgr = new NominaMgr(session);

			// Logica general
			List estados = nominaMgr.getEstadosNomina();
			List tipoProcesos = nominaMgr.getTiposNomina();
			frm.setEstados(nominaMgr.getComboEstadosNominaOrdenados(estados));
			frm.setTiposNomina(nominaMgr.getComboTiposNominaOrdenados(tipoProcesos));
			logger.info("accion:" + this.accion);
			logger.info("SubAccion:" + this.subAccion);
			if ("listar".equals(this.accion))
			{
				List nominas = new ArrayList();
				int grupoConvenioAux = 0;
				int rutEmpresaAux = 0;
				int estadoIdAux = 0;
				String grupoConvenio = frm.getGrupoConvenioBuscar();
				String rutEmpresa = frm.getRutEmpresaBuscar();
				String estadoId = frm.getEstadoIdBuscar();
				String tipoNominaId = (frm.getTipoNominaIdBuscar() == null || frm.getTipoNominaIdBuscar().equals("") ? "" + Constants.TIPO_NOM_REMUNERACION : frm.getTipoNominaIdBuscar());

				// Se agrega esto para cuando viene desde el boton volver desde cotizantesLista.jsp
				String rutBuscar = "";
				if (request.getParameter("rutEmpresaBuscar") != null)
				{
					rutBuscar = request.getParameter("rutEmpresaBuscar").toString();
					if (!"".equals(rutBuscar))
						rutEmpresa = rutBuscar;
				}
				String convBuscar = "";
				if (request.getParameter("grupoConvenioBuscar") != null)
				{
					convBuscar = request.getParameter("grupoConvenioBuscar").toString();
					if (!"".equals(convBuscar))
						grupoConvenio = convBuscar;
				}
				String tipoBuscar = "";
				if (request.getParameter("tipoNominaIdBuscar") != null)
				{
					tipoBuscar = request.getParameter("tipoNominaIdBuscar").toString();
					if (!"".equals(tipoBuscar))
						tipoNominaId = tipoBuscar.substring(0, 1);
				}
				String estadoBuscar = request.getParameter("estadoIdBuscar");
				if (request.getParameter("estadoIdBuscar") != null)
				{
					estadoBuscar = request.getParameter("estadoIdBuscar").toString();
					if (!"".equals(estadoBuscar))
						estadoId = estadoBuscar;
				}
				// Seteamos en el formulario los filtros ingresados
				frm.setGrupoConvenio(convBuscar);
				frm.setRutEmpresa(rutBuscar);
				frm.setEstadoId(estadoId);
				frm.setTipoNominaId(tipoNominaId);
				// fin desde volver

				grupoConvenioAux = (grupoConvenio == null || "".equals(grupoConvenio)) ? Constants.NUMBER_NULL : Integer.parseInt(grupoConvenio);
				estadoIdAux = (estadoId == null || "".equals(estadoId)) ? Constants.NUMBER_NULL : Integer.parseInt(estadoId);
				if (rutEmpresa == null || "".equals(rutEmpresa))
					rutEmpresaAux = Constants.NUMBER_NULL;
				else
				{
					rutEmpresa = rutEmpresa.replaceAll("\\.", "");
					int pos = rutEmpresa.indexOf('-');
					if (pos > -1)
						rutEmpresa = rutEmpresa.substring(0, pos);
					rutEmpresaAux = Integer.parseInt(rutEmpresa);
				}

				nominas = nominaMgr.getNominasAdminNominas(tipoNominaId, rutEmpresaAux, grupoConvenioAux, estadoIdAux);

				int numeroTotalRegistros = nominas.size();
				if (numeroTotalRegistros > 0)
				{
					int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
					HashMap paginacion = Utils.llenarPaginacionAdmin(pagina, nominas);
					frm.setNominas((List) paginacion.get("data"));
					frm.setNumeroFilas((List) paginacion.get("paginas"));// enlaces listos, llegar y mostrar. llama a funcion JS
				} else
				{
					frm.setNumeroFilas(new LinkedList());
					frm.setNominas(nominas);
				}
			}
			tx.commit();
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListarNodoAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
		return mapping.findForward(mappingFoward);
	}
}
