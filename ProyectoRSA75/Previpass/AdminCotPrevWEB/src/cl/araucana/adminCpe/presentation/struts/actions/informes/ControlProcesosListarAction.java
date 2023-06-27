package cl.araucana.adminCpe.presentation.struts.actions.informes;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ComprobanteMgr;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.mgr.NominaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.informes.ControlProcesosListarActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaControlProcesos;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;

import com.bh.talon.User;

/*
 * @(#) ControlprocesoListarAction.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.4
 */
public class ControlProcesosListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ControlProcesosListarAction.class);

	/**
	 * control procesos listar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ControlProcesosListarActionForm actForm = (ControlProcesosListarActionForm) form;

		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			LineaControlProcesos lineaControlProcesos;

			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			NominaMgr nominaMgr = new NominaMgr(session);

			actForm.setNumEmpresasTotal(0);
			actForm.setNumTrabajadoresTotal(0);
			actForm.setTotPagadoTotal(0);

			List estados = new ArrayList();
			estados.add("5");
			estados.add("3");
			estados.add("4");
			List listaComprobantes = comprobanteMgr.getListaComprobantePagoByEstado(estados);
			actForm.setListaConDeclaro(new ArrayList());
			actForm.setListaNoPagadas(new ArrayList());
			actForm.setListaNoPago(new ArrayList());
			actForm.setListaPagadas(new ArrayList());

			int contTrabPA = 0, contTrabNP = 0, contTrabCD = 0;
			long contMontoPA = 0, contMontoNP = 0, contMontoCD = 0;

			List listaEmpresasPA = new ArrayList(), listaEmpresasNP = new ArrayList(), listaEmpresasCD = new ArrayList();
			int tipoPago = 0;
			HashMap estadosCmps = comprobanteMgr.getEstadosCmps();
			if (listaComprobantes.size() > 0)
			{
				List tiposNomina = nominaMgr.getTiposNomina();
				for (int cont = 0; cont < listaComprobantes.size(); cont++)
				{
					ComprobanteVO comprobante = (ComprobanteVO) listaComprobantes.get(cont);
					tipoPago = Integer.parseInt(String.valueOf(comprobante.getIdEstado()));

					for (Iterator it = tiposNomina.iterator(); it.hasNext();)
					{
						TipoNominaVO tn = (TipoNominaVO)it.next();
						NominaVO nomina = nominaMgr.getNomina(tn.getIdTipoNomina(), comprobante.getIdCodigoBarra());
						if (nomina != null)
						{
							int rutEmpresa = nomina.getRutEmpresa();
							long idCodigoBarra = comprobante.getIdCodigoBarra();
							int idConvenio = nomina.getIdConvenio();

							if (tipoPago == Constants.EST_CMP_PAGADO)
								listaEmpresasPA.add(String.valueOf(rutEmpresa));
							else if (tipoPago == Constants.EST_CMP_POR_PAGAR || tipoPago == Constants.EST_CMP_PRECURSADO)
								listaEmpresasNP.add(String.valueOf(rutEmpresa));
							else if (tipoPago == Constants.EST_CMP_PAGO_PARCIAL)
								listaEmpresasCD.add(String.valueOf(rutEmpresa));

							lineaControlProcesos = new LineaControlProcesos();
							lineaControlProcesos.setTipoProceso(tn.getDescripcion().trim());
							lineaControlProcesos.setIdCodigoBarra(String.valueOf(idCodigoBarra));
							lineaControlProcesos.setRutEmpresa(Utils.formatRut(rutEmpresa));
							lineaControlProcesos.setRazonSocial((empresaMgr.getEmpresa(rutEmpresa)).getRazonSocial());
							ConvenioVO convenio = (convenioMgr.getConvenio(rutEmpresa, idConvenio));
							lineaControlProcesos.setConvenio(convenio.getDescripcion().trim());
							GrupoConvenioVO grupoConvenioVO = convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());
							if (grupoConvenioVO != null)
								lineaControlProcesos.setGrupo(grupoConvenioVO.getNombre().trim());
							else
								lineaControlProcesos.setGrupo("*Sin Grupo de Convenio*");

							lineaControlProcesos.setModoPago(getDescripcionEstado(comprobante.getIdEstado(), estadosCmps));

							lineaControlProcesos.setTotalPagado(comprobante.getTotal());
							lineaControlProcesos.setTotalPagadoMonto(Utils.formatMonto(comprobante.getTotal()));
							lineaControlProcesos.setTotalTrabajadores(comprobante.getNumTrabajadores());

							if (tipoPago == Constants.EST_CMP_PAGADO)
							{
								actForm.getListaPagadas().add(lineaControlProcesos);
								contMontoPA += comprobante.getTotal();
								contTrabPA += lineaControlProcesos.getTotalTrabajadores();
							}
							if (tipoPago == Constants.EST_CMP_POR_PAGAR || tipoPago == Constants.EST_CMP_PRECURSADO)
							{
								actForm.getListaNoPagadas().add(lineaControlProcesos);
								contMontoNP += comprobante.getTotal();
								contTrabNP += lineaControlProcesos.getTotalTrabajadores();
							}
							if (tipoPago == Constants.EST_CMP_PAGO_PARCIAL)
							{
								actForm.getListaConDeclaro().add(lineaControlProcesos);
								contMontoCD += comprobante.getTotal();
								contTrabCD += lineaControlProcesos.getTotalTrabajadores();
							}							
							break;
						}
					}
				}

				Collections.sort(actForm.getListaPagadas());
				Collections.sort(actForm.getListaNoPagadas());
				Collections.sort(actForm.getListaConDeclaro());

				actForm.setNumTrabajadoresPA(contTrabPA);
				actForm.setNumTrabajadoresCD(contTrabCD);
				actForm.setNumTrabajadoresNP(contTrabNP);

				actForm.setNumEmpresasPA(this.cantidadEmpresas(listaEmpresasPA));
				actForm.setNumEmpresasCD(this.cantidadEmpresas(listaEmpresasCD));
				actForm.setNumEmpresasNP(this.cantidadEmpresas(listaEmpresasNP));

				actForm.setTotPagadoPA(contMontoPA);
				actForm.setTotPagadoCD(contMontoCD);
				actForm.setTotPagadoNP(contMontoNP);

				actForm.setNumTrabajadoresTotal(contTrabPA + contTrabNP + contTrabCD);
				actForm.setNumEmpresasTotal(actForm.getNumEmpresasPA() + actForm.getNumEmpresasNP() + actForm.getNumEmpresasCD());
				actForm.setTotPagadoTotal(contMontoPA + contMontoNP + contMontoCD);

				actForm.setTotPagadoPAMonto(Utils.formatMonto(contMontoPA));
				actForm.setTotPagadoCDMonto(Utils.formatMonto(contMontoCD));
				actForm.setTotPagadoNPMonto(Utils.formatMonto(contMontoNP));

				actForm.setTotPagadoTotalMonto(Utils.formatMontoD(actForm.getTotPagadoTotal()));

			}
			tx.commit();
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaEstructuraMovPersonalAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}

	/**
	 * busca dentro del hashMap recibido, la descripcion asociada al estado buscado
	 * 
	 * @param idEstado
	 * @param estadosCmps
	 * @return descripcion asociada al estado buscado
	 */
	private String getDescripcionEstado(char idEstado, HashMap estadosCmps)
	{
		if (estadosCmps.containsKey("" + idEstado))
			return (String) estadosCmps.get("" + idEstado);
		return "";
	}

	/**
	 * cantidad empresas
	 * 
	 * @param lista
	 * @return
	 */
	public int cantidadEmpresas(List lista)
	{
		List temp = new ArrayList();
		int n = 0;
		String dato = "";
		for (int a = 0; a < lista.size(); a++)
		{
			dato = String.valueOf(lista.get(a));
			for (int b = 0; b < temp.size(); b++)
			{
				if (dato.equals(String.valueOf(temp.get(b))))
					n++;
			}
			if (n == 0)
				temp.add(dato);
			n = 0;
		}
		return temp.size();
	}
}
