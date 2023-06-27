package cl.araucana.adminCpe.presentation.struts.actions.informes;

import java.text.SimpleDateFormat;
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
import cl.araucana.adminCpe.presentation.struts.forms.informes.ComprobPagoListarActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaComprobantePago;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;

import com.bh.talon.User;

/*
 * @(#) ComprobPagoListarAction.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * 
 * @version 1.17
 */
public class ComprobPagoListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ComprobPagoListarAction.class);

	/**
	 * comprobante pago listar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ComprobPagoListarActionForm actForm = (ComprobPagoListarActionForm) form;

		Session session = null;
		Transaction tx = null;
		try
		{
			String accionInterna = request.getParameter("accionInterna");
			if (accionInterna != null)
			{
				session = HibernateUtil.getSession();
				tx = session.beginTransaction();

				LineaComprobantePago lineaComprobantePago;

				NominaMgr nominaMgr = new NominaMgr(session);
				ConvenioMgr convenioMgr = new ConvenioMgr(session);
				EmpresaMgr empresaMgr = new EmpresaMgr(session);
				ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);

				int rutEmpresa = 0;
				int grupo = 0;
				List listaConvenio = null;

				List listaComprobantePago = new ArrayList();
				nominaMgr.getEstadosNomina();
				if (accionInterna.equals("TODO"))
				{
					List listaComprobantes = comprobanteMgr.getListaComprobantePago();
					HashMap estadosCmps = comprobanteMgr.getEstadosCmps();

					List tiposNomina = nominaMgr.getTiposNomina();
					for (Iterator itComprobantes = listaComprobantes.iterator(); itComprobantes.hasNext();)
					{
						ComprobanteVO comprobanteVO = (ComprobanteVO) itComprobantes.next();

						for (Iterator it = tiposNomina.iterator(); it.hasNext();)
						{
							TipoNominaVO tn = (TipoNominaVO)it.next();
							NominaVO nomina = nominaMgr.getNomina(tn.getIdTipoNomina(), comprobanteVO.getIdCodigoBarra());
							if (nomina != null)
							{
								lineaComprobantePago = new LineaComprobantePago();
								lineaComprobantePago.setEstado(getDescripcionEstado(comprobanteVO.getIdEstado(), estadosCmps));
								SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
								lineaComprobantePago.setFechaEnvio(formatoFecha.format(comprobanteVO.getEmitido()));
								lineaComprobantePago.setTotal(Utils.formatMonto(comprobanteVO.getTotal()));

								lineaComprobantePago.setRutEmpresa(Utils.formatRut(nomina.getRutEmpresa()));
								lineaComprobantePago.setConvenio((convenioMgr.getConvenio(nomina.getRutEmpresa(), nomina.getIdConvenio())).getDescripcion().trim());

								EmpresaVO empresaVO = empresaMgr.getEmpresa(nomina.getRutEmpresa());
								lineaComprobantePago.setRazonSocial(empresaVO.getRazonSocial().trim());
								lineaComprobantePago.setProceso(tn.getDescripcion().trim());

								listaComprobantePago.add(lineaComprobantePago);
								break;
							}
						}
					}
				} else
				{
					if (accionInterna.equals("EMPRESA"))
					{
						if (request.getParameter("rutEmpresa") != null)
						{
							String rutTmp = request.getParameter("rutEmpresa").trim();
							rutTmp = rutTmp.replaceAll("\\.", "");

							int pos = rutTmp.indexOf('-');
							if (pos > -1)
								rutTmp = rutTmp.substring(0, pos);
							rutEmpresa = Integer.parseInt(rutTmp);
							logger.info("RUT:" + rutEmpresa);

							listaConvenio = convenioMgr.getConveniosEmpresa(rutEmpresa);
						}
					}
					if (accionInterna.equals("GRUPO"))
					{
						if (request.getParameter("grupo") != null)
						{
							grupo = Integer.parseInt(request.getParameter("grupo"));
							GrupoConvenioVO grupoConvenioVO = convenioMgr.getGrupoConvenio(grupo);
							if (grupoConvenioVO != null)
								listaConvenio = convenioMgr.getConveniosGrupo(grupoConvenioVO.getIdGrupoConvenio()); //convenioMgr.getConveniosEmpresa(grupoConvenioVO.getIdEmpresa());
							else
								return mapping.findForward("exito");
						}
					}
					if (listaConvenio != null && listaConvenio.size() > 0)
					{
						actForm.setLista(new ArrayList());
						List tiposNomina = nominaMgr.getTiposNomina();
						for (Iterator itGrupo = listaConvenio.iterator(); itGrupo.hasNext();)
						{
							ConvenioVO convenioVO = (ConvenioVO) itGrupo.next();

							rutEmpresa = convenioVO.getIdEmpresa();
							EmpresaVO empresaVO = empresaMgr.getEmpresa(rutEmpresa);

							NominaVO nomina;
							for (Iterator it = tiposNomina.iterator(); it.hasNext();)
							{
								TipoNominaVO tn = (TipoNominaVO)it.next();
								String proceso = tn.getDescripcion().trim();
								nomina = nominaMgr.getNomina(tn.getIdTipoNomina(), rutEmpresa, convenioVO.getIdConvenio());

								if (nomina != null)
								{
									long idCodigoBarra = nomina.getIdCodigoBarras();
									HashMap estadosCmps = comprobanteMgr.getEstadosCmps();
									ComprobanteVO comprobanteVO = comprobanteMgr.getComprobantePago(idCodigoBarra);
									if (comprobanteVO != null)
									{
										lineaComprobantePago = new LineaComprobantePago();
										lineaComprobantePago.setRutEmpresa(Utils.formatRut(rutEmpresa));
										lineaComprobantePago.setRazonSocial(empresaVO.getRazonSocial().trim());
										lineaComprobantePago.setConvenio(convenioVO.getDescripcion().trim());
										lineaComprobantePago.setEstado(getDescripcionEstado(comprobanteVO.getIdEstado(), estadosCmps));
										SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
										lineaComprobantePago.setFechaEnvio(formatoFecha.format(comprobanteVO.getEmitido()));
										lineaComprobantePago.setTotal(Utils.formatMonto(comprobanteVO.getTotal()));
										lineaComprobantePago.setProceso(proceso);
										listaComprobantePago.add(lineaComprobantePago);
									}
								}
							}
						}
					}
				}

				Collections.sort(listaComprobantePago);
				actForm.setLista(listaComprobantePago);

				tx.commit();
			} else
			{
				actForm.setLista(null);
			}
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaComprobantePagoAction.doTask()", ex);
			if (tx != null)
			{
				tx.rollback();
			}
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
}
