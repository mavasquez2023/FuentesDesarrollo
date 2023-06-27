package cl.araucana.adminCpe.presentation.struts.actions.nomina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConceptoMgr;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.CotizanteMgr;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.nomina.NominaForm;
import cl.araucana.adminCpe.utils.FactoryView;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;

import com.bh.talon.User;

/*
 * @(#) NominasTrabajadoresVerAction.java 1.12 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.12
 */
public class ListaTrabajadoresAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaTrabajadoresAction.class);

	/**
	 * Nominas Trabajadores ver
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NominaForm frm = (NominaForm) form;
		String mappingFoward = "verTrabajadores";
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
			String tipo = request.getParameter("tipo");
			if (tipo != null)
				frm.setNombreProceso(tipo);
			// Objetos locales
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			ConceptoMgr conceptoMgr = new ConceptoMgr(session);
			FactoryView fv = new FactoryView();
			CotizanteVO cotizante = null;

			int idConvenio = 0;
			String rutEmpresa = "";
			char tipoProceso = ' ';

			String trabajadorRut = "";
			String trabajadorNombre = "";
			String trabajadorApPaterno = "";

			// Valores de filtros originales
			String rutEmBuscar = "";
			String idConvBuscar = "";
			String tipoNomBuscar = "";
			String idEstBuscar = "";

			// Obtener valores
			String accionInterna = request.getParameter("accion");

			if ("buscar".equalsIgnoreCase(accionInterna))
			{
				if (frm.getConvenioId() == null)
					idConvenio = new Integer(request.getParameter("idConv")).intValue();
				else
					idConvenio = new Integer(frm.getConvenioId()).intValue();
				rutEmpresa = frm.getRutEmpresa();
				if (rutEmpresa == null)
					rutEmpresa = request.getParameter("rutEmp");
				if (frm.getTipoNominaId() == null)
					tipoProceso = request.getParameter("tipoNom").charAt(0);
				else
					tipoProceso = frm.getTipoNominaId().charAt(0);
				trabajadorRut = frm.getB_rut();
				if (trabajadorRut == null)
					trabajadorRut = request.getParameter("rut");
				trabajadorNombre = frm.getB_nombre();
				if (trabajadorNombre == null)
					trabajadorNombre = request.getParameter("nom");
				trabajadorApPaterno = frm.getB_apellido();
				if (trabajadorApPaterno == null)
					trabajadorApPaterno = request.getParameter("ap");

				trabajadorNombre = trabajadorNombre.trim();
				trabajadorApPaterno = trabajadorApPaterno.trim();
				
				frm.setTrabajadorRut(trabajadorRut);
				frm.setTrabajadorNombre(trabajadorNombre);
				frm.setTrabajadorApPaterno(trabajadorApPaterno);
				frm.setB_rut(trabajadorRut);
				frm.setB_nombre(trabajadorNombre);
				frm.setB_apellido(trabajadorApPaterno);
				String rutTrab = "";
				if (trabajadorRut.length() > 3)
				{
					rutTrab = String.valueOf(Utils.desFormatMonto(trabajadorRut.substring(0, trabajadorRut.length() - 1)));
					logger.info("RUT:: " + rutTrab);
				}
				if (!rutTrab.equals("") || !trabajadorNombre.equals("") || !trabajadorApPaterno.equals(""))
				{
					cotizante = new CotizanteVO();
					cotizante.setIdCotizante(new Integer(rutTrab).intValue());
					cotizante.setNombre(trabajadorNombre);
					cotizante.setApellidoPat(trabajadorApPaterno);
				}
			} else
			{
				idConvenio = new Integer(request.getParameter("idConv")).intValue();
				rutEmpresa = request.getParameter("rutEmp");

				// Rescatamos los filtros y luego los seteamos en request
				rutEmBuscar = request.getParameter("rutEmpresaBuscar");
				idConvBuscar = request.getParameter("grupoConvenioBuscar");
				tipoNomBuscar = request.getParameter("tipoNominaIdBuscar");
				idEstBuscar = request.getParameter("estadoIdBuscar");

				request.setAttribute("rutEmpBuscar", rutEmBuscar);
				request.setAttribute("idConvBuscar", idConvBuscar);
				request.setAttribute("tipoNomBuscar", tipoNomBuscar);
				request.setAttribute("idEstBuscar", idEstBuscar);
			}
			if (request.getParameter("tipoNom") != null)
				tipoProceso = request.getParameter("tipoNom").charAt(0);

			EmpresaVO empresa = empresaMgr.getEmpresa(Integer.parseInt(rutEmpresa));
			frm.setConvenioId("" + idConvenio);
			frm.setRutEmpresa(rutEmpresa);
			frm.setTipoNominaId("" + tipoProceso);

			try
			{
				int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
				int primerReg = Constants.NUM_REG_PAG_ADMIN * (pagina - 1);
				HashMap dataTrabajadores = cotizanteMgr.getTrabPaginados(pagina, primerReg, new Integer(rutEmpresa).intValue(), idConvenio, tipoProceso, cotizante);
				List listaTrabajadores = (List) dataTrabajadores.get("aprobados");
				List listaTrabajadoresAvisos = (List) dataTrabajadores.get("avisos");
				List listaTrabPend = (List) dataTrabajadores.get("pendientes");
				int nPaginas = ((Integer)dataTrabajadores.get("nPaginas")).intValue();
				HashMap sucursales = empresaMgr.getSucursalesHash(rutEmpresa);
				frm.setTrabajadores(FactoryView.cotizanteVOtoView(tipoProceso, listaTrabajadores, sucursales));
				frm.setTrabAvisos(FactoryView.cotizanteAvisoVOtoView(tipoProceso, listaTrabajadoresAvisos, sucursales));
				
				if (listaTrabPend.size() > 0)
				{
					ConvenioVO convenio = (new ConvenioMgr(session)).getConvenio(new Integer(rutEmpresa).intValue(), new Integer(idConvenio).intValue());
					GrupoConvenioVO grupoConvenio = (new ConvenioMgr(session)).getGrupoConvenio(convenio.getIdGrupoConvenio());
					List listaConceptos = conceptoMgr.getListaConceptos("" + tipoProceso);
					List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(tipoProceso), "" + tipoProceso);
					Properties mapConceptos = new Properties();
					mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
					fv.setListasConceptos(listaConceptos, listaMapeo, mapConceptos);
					listaTrabPend = fv.pendientestoView(tipoProceso, listaTrabPend, sucursales);
					Collections.sort(listaTrabPend);
					//pagina resultados pendientes
					int cant = Math.min(listaTrabPend.size(), primerReg + Constants.NUM_REG_PAG_ADMIN) - primerReg;
					frm.setTrabPendientes(listaTrabPend.subList(primerReg, primerReg + cant));
				} else
					frm.setTrabPendientes(new ArrayList());
				frm.setNumeroFilas(Utils.generaPaginacion(pagina, nPaginas, Constants.NUM_PAG_ADMIN));
				
				if (empresa != null)
				{
					logger.debug("Empresa encontrada");

					frm.setEmpresaNombre(empresa.getRazonSocial().trim());
					frm.setEmpresaRut(Utils.formatRut(empresa.getIdEmpresa()));
				} else
					logger.debug("Empresa vacio");
			} catch (NumberFormatException e)
			{
				ActionErrors ae = new ActionErrors();
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.informacion"));
				saveErrors(request.getSession(), ae);
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
