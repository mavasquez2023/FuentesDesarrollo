package cl.araucana.adminCpe.presentation.struts.actions.informes;

import java.util.ArrayList;
import java.util.Collections;
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
import cl.araucana.adminCpe.presentation.mgr.NominaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.informes.ComprobSplListarActionForm;
import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaComprobanteSpl;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;

import com.bh.talon.User;

/*
 * @(#) ComprobSplListarAction.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class ComprobSplListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ComprobSplListarAction.class);

	/**
	 * comprobante spl listar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ComprobSplListarActionForm actForm = (ComprobSplListarActionForm) form;

		Session session = null;
		Transaction tx = null;
		try
		{
			String accionInterna = request.getParameter("accionInterna");
			if (accionInterna != null)
			{
				session = HibernateUtil.getSession();
				tx = session.beginTransaction();

				LineaComprobanteSpl lineaComprobanteSpl;

				NominaMgr nominaMgr = new NominaMgr(session);
				ConvenioMgr convenioMgr = new ConvenioMgr(session);
				ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);

				int rutEmpresa = 0;
				int grupo = 0;

				List listaComprobanteSpl = new ArrayList();
				nominaMgr.getEstadosNomina();
				if (accionInterna.equals("TODO"))
				{
					List listaComprobantes = comprobanteMgr.getListaComprobanteSpl();
					List tiposNomina = nominaMgr.getTiposNomina();

					for (Iterator itComprobantes = listaComprobantes.iterator(); itComprobantes.hasNext();)
					{
						ComprobanteVO comprobanteVO = (ComprobanteVO) itComprobantes.next();
						long idCodigoBarra = comprobanteVO.getIdCodigoBarra();

						for (Iterator it = tiposNomina.iterator(); it.hasNext();)
						{
							TipoNominaVO tn = (TipoNominaVO) it.next();
							NominaVO nomina = nominaMgr.getNomina(tn.getIdTipoNomina(), comprobanteVO.getIdCodigoBarra());
							if (nomina != null)
							{
								lineaComprobanteSpl = new LineaComprobanteSpl();
								lineaComprobanteSpl.setNumeroComprobante(String.valueOf(idCodigoBarra));
								lineaComprobanteSpl.setMonto(Utils.formatMonto(comprobanteVO.getTotal()));
								lineaComprobanteSpl.setTotal(comprobanteVO.getTotal());
								lineaComprobanteSpl.setEmpresa(Utils.formatRut(nomina.getRutEmpresa()));
								ConvenioVO convenioVO = convenioMgr.getConvenio(nomina.getRutEmpresa(), nomina.getIdConvenio());
								lineaComprobanteSpl.setConvenio(convenioVO.getDescripcion().trim());
								lineaComprobanteSpl.setProceso(tn.getDescripcion().trim());
								int idGrupoConvenio = convenioVO.getIdGrupoConvenio();
								GrupoConvenioVO grupoConvenioVO = convenioMgr.getGrupoConvenio(idGrupoConvenio);
								String grupoConvenio = "";
								if (grupoConvenioVO != null)
									grupoConvenio = grupoConvenioVO.getNombre().trim();
								lineaComprobanteSpl.setGrupo(grupoConvenio);
								listaComprobanteSpl.add(lineaComprobanteSpl);
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
						}
					}
					if (accionInterna.equals("GRUPO"))
					{
						if (request.getParameter("grupo") != null)
							grupo = Integer.parseInt(request.getParameter("grupo"));
					}
					List listaComprobantes = comprobanteMgr.getListaComprobanteSpl();
					List tiposNomina = nominaMgr.getTiposNomina();

					for (Iterator itComprobantes = listaComprobantes.iterator(); itComprobantes.hasNext();)
					{
						ComprobanteVO comprobanteVO = (ComprobanteVO) itComprobantes.next();
						long idCodigoBarra = comprobanteVO.getIdCodigoBarra();

						for (Iterator it = tiposNomina.iterator(); it.hasNext();)
						{
							TipoNominaVO tn = (TipoNominaVO) it.next();
							NominaVO nomina = nominaMgr.getNomina(tn.getIdTipoNomina(), comprobanteVO.getIdCodigoBarra());
							if (nomina != null)
							{
								lineaComprobanteSpl = new LineaComprobanteSpl();
								lineaComprobanteSpl.setNumeroComprobante(String.valueOf(idCodigoBarra));
								lineaComprobanteSpl.setMonto(Utils.formatMonto(comprobanteVO.getTotal()));
								lineaComprobanteSpl.setTotal(comprobanteVO.getTotal());
								if (accionInterna.equals("GRUPO"))
								{
									lineaComprobanteSpl.setEmpresa(Utils.formatRut(nomina.getRutEmpresa()));
									ConvenioVO convenioVO = convenioMgr.getConvenio(nomina.getRutEmpresa(), nomina.getIdConvenio());
									lineaComprobanteSpl.setConvenio(convenioVO.getDescripcion().trim());
									lineaComprobanteSpl.setProceso(tn.getDescripcion().trim());
									int idGrupoConvenio = convenioVO.getIdGrupoConvenio();
									if (grupo == idGrupoConvenio)
									{
										GrupoConvenioVO grupoConvenioVO = convenioMgr.getGrupoConvenio(idGrupoConvenio);
										String grupoConvenio = "";
										if (grupoConvenioVO != null)
											grupoConvenio = grupoConvenioVO.getNombre().trim();
										lineaComprobanteSpl.setGrupo(grupoConvenio);
										listaComprobanteSpl.add(lineaComprobanteSpl);

										actForm.setLista(listaComprobanteSpl);
									}
								} else if (accionInterna.equals("EMPRESA"))
								{
									String rutEmpNomina = Integer.toString(nomina.getRutEmpresa());
									if (rutEmpNomina.startsWith("" + rutEmpresa))
									{
										lineaComprobanteSpl.setEmpresa(Utils.formatRut(nomina.getRutEmpresa()));
										ConvenioVO convenioVO = convenioMgr.getConvenio(nomina.getRutEmpresa(), nomina.getIdConvenio());
										lineaComprobanteSpl.setConvenio(convenioVO.getDescripcion().trim());
										lineaComprobanteSpl.setProceso(tn.getDescripcion().trim());
										int idGrupoConvenio = convenioVO.getIdGrupoConvenio();
										GrupoConvenioVO grupoConvenioVO = convenioMgr.getGrupoConvenio(idGrupoConvenio);
										String grupoConvenio = "";
										if (grupoConvenioVO != null)
											grupoConvenio = grupoConvenioVO.getNombre().trim();
										lineaComprobanteSpl.setGrupo(grupoConvenio);
										listaComprobanteSpl.add(lineaComprobanteSpl);
									}
								}
								break;
							}
						}
					}
				}
				if (listaComprobanteSpl.size() > 0)
				{
					List retorno = generaTotal(listaComprobanteSpl);
					actForm.setLista(retorno);
				}
				tx.commit();
			} else
				actForm.setLista(null);
			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaComprobanteSplAction.doTask()", ex);
			if (tx != null)
			{
				tx.rollback();
			}
			return mapping.findForward("error");

		}
	}

	/**
	 * para agrupar por grupo de convenios: solo el ultimo registro de un grupo debe tener total
	 * 
	 * @param lista
	 * @return
	 */
	private List generaTotal(List lista)
	{
		List retorno = new ArrayList();
		Collections.sort(lista);
		retorno = lista;
		List _tempRetorno = new ArrayList();
		int largo = retorno.size() - 1;
		int cont = 0;
		long totalMonto = 0;
		for (Iterator _it = retorno.iterator(); _it.hasNext();)
		{
			LineaComprobanteSpl lineaComprobanteSpl = (LineaComprobanteSpl) _it.next();
			totalMonto += lineaComprobanteSpl.getTotal();
			if (cont == largo) // si es el ultimo registro
				lineaComprobanteSpl.setTotal(totalMonto);
			else
			{
				LineaComprobanteSpl lineaTmp = (LineaComprobanteSpl) retorno.get(cont + 1);
				if (lineaComprobanteSpl.getGrupo().equals(lineaTmp.getGrupo())) // hay mas lineas para el mismo grupo
					lineaComprobanteSpl.setTotal(-1);
				else // ultima linea por grupo
				{
					lineaComprobanteSpl.setTotal(totalMonto);
					totalMonto = 0;
				}
			}
			cont++;
			_tempRetorno.add(lineaComprobanteSpl);
		}

		for (int a = 1; a < _tempRetorno.size(); a++)
		{
			LineaComprobanteSpl linea = (LineaComprobanteSpl) _tempRetorno.get(a);
			LineaComprobanteSpl _linea = (LineaComprobanteSpl) _tempRetorno.get(a - 1);
			if (_linea.getTotal() == -1)
			{
				linea.setGrupo("");
				_tempRetorno.set(a, linea);
			}
		}
		return _tempRetorno;
	}
}
