package cl.araucana.adminCpe.presentation.struts.actions.nomina;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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
import cl.araucana.adminCpe.presentation.mgr.ConceptoMgr;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.CotizanteMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.MapeosMgr;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.adminCpe.presentation.struts.forms.nomina.ConsultaExcelForm;
import cl.araucana.adminCpe.utils.FactoryView;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizacion;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.util.reporte.ConstructorEXCEL;

import com.bh.talon.User;

/*
 * @(#) ConsultaExcelAction.java 1.3 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.3
 */
public class ConsultaExcelAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ConsultaExcelAction.class);
	private char tipoNomina;
	private HashMap entsSalud = new HashMap();
	private HashMap entsAFP = new HashMap();
	private HashMap entsAPV = new HashMap();
	private HashMap apvs = new HashMap();

	/**
	 * excel
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		logger.info("-----> ConsultaExcelAction");
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			ConsultaExcelForm actForm = (ConsultaExcelForm) form;
			logger.info("idConv " + request.getParameter("idConv"));
			logger.info("rutEmp " + request.getParameter("rutEmp"));

			logger.info("formulario --> " + actForm);

			int idConvenio = Integer.parseInt(request.getParameter("idConv"));
			actForm.setIdConvenio(request.getParameter("idConv"));
			int rutEmpresa = Integer.parseInt(request.getParameter("rutEmp"));
			actForm.setRutEmpresa(Utils.formatRut(rutEmpresa));
			this.tipoNomina = request.getParameter("tipoNom").charAt(0);
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			ParametroMgr parametroMgr = new ParametroMgr(session);
			ConceptoMgr conceptoMgr = new ConceptoMgr(session);
			EntidadesMgr entidadesMgr = new EntidadesMgr(session);

			// seteo datos comunes a todos los cotizantes
			ConvenioVO convenio = convenioMgr.getConvenio(rutEmpresa, idConvenio);
			int idGrupoConvenio = convenio.getIdGrupoConvenio();
			ParametroVO parametro = parametroMgr.getParametro(Constants.PARAM_TASA_FIJA);
			GrupoConvenioVO grupoConvenio = convenioMgr.getGrupoConvenio(idGrupoConvenio);
			List listaRegImp = entidadesMgr.getRegImp();

			EntidadCCAFVO caja = entidadesMgr.getCaja(convenio.getIdCcaf());
			EntidadMutualVO mutual = entidadesMgr.getMutual(convenio.getIdMutual());

			List detalleExcel = new ArrayList();

			List cotizantes = cotizanteMgr.getCotizantesNomina(rutEmpresa, idConvenio, this.tipoNomina);
			if (cotizantes.size() > 0)
			{
				this.entsSalud = entidadesMgr.getEntidadesHash(false, 0, EntidadSaludVO.class);
				this.entsAFP = entidadesMgr.getEntidadesHash(false, 0, EntidadPensionVO.class);
				this.entsAPV = entidadesMgr.getEntidadesHash(false, 0, EntidadApvVO.class);
				if (this.tipoNomina == 'R')
					this.apvs = cotizanteMgr.getApvsHash(rutEmpresa, idConvenio);
			}
			for (Iterator it = cotizantes.iterator(); it.hasNext();)
			{
				CotizanteVO cotVO = (CotizanteVO) it.next();
				Cotizante cotizante = FactoryView.cotizanteVOtoView(this.tipoNomina, cotVO);
				logger.debug("procesando:" + cotizante.getIdCotizante() + "::");
				Cotizacion cotizacion = FactoryView.cotizacionVOtoView(this.tipoNomina, false, cotVO, caja, mutual);
				if (this.tipoNomina == 'R')
				{
					cotizante.setApvs(setApvs(cotizante.getIdCotizante()));
					cotizante.setTasaCotizacion("" + buscaTasa(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp(), listaRegImp));
					cotizacion.setSaludObligFONASA("" + (new Integer(cotizacion.getSaludObligFONASA()).intValue() + new Integer(cotizacion.getSaludObligISAPRE()).intValue()));
				} else if (this.tipoNomina == 'G')
				{
					cotizante.setTasaCotizacion("" + buscaTasa(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp(), listaRegImp));
				} else if (this.tipoNomina == 'A')
				{
					cotizante.setTasaCotizacion("" + buscaTasa(cotizante.getIdEntExCaja(), cotizante.getIdRegimenImp(), listaRegImp));
				} else if (this.tipoNomina == 'D')
					cotizacion.setTipoRegimenPrev(cotizacion.getTipoRegimenPrev().equals("2") ? "AFP" : cotizacion.getTipoRegimenPrev().equals("1") ? "INP" : "");

				cotizante = setNombresReales(this.tipoNomina, cotizante);
				cotizante.setCotizacion(cotizacion);
				detalleExcel.add(cotizante);
			}
			// PENDIENTES
			List cotizPendientes = cotizanteMgr.getListaCotizPend(rutEmpresa, idConvenio, this.tipoNomina);
			if (cotizPendientes.size() > 0)
			{
				List listaConceptos = conceptoMgr.getListaConceptos("" + this.tipoNomina);
				List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(this.tipoNomina), "" + this.tipoNomina);
				Properties mapNombres = new Properties();
				mapNombres.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));

				HashMap mapeosConcep = new HashMap();
				for (Iterator itM = listaMapeo.iterator(); itM.hasNext();)
				{
					MapeoConceptoVO mc = (MapeoConceptoVO) itM.next();
					mapeosConcep.put("" + mc.getIdConcepto(), mc);
				}

				FactoryView fv = new FactoryView();
				fv.setListasConceptos(listaConceptos, listaMapeo, mapNombres);

				for (Iterator it = cotizPendientes.iterator(); it.hasNext();) // por cada Pendiente
				{
					try
					{
						CotizacionPendienteVO cotizPendVO = (CotizacionPendienteVO) it.next();
						Cotizante cot = fv.cotizPendVOtoView(this.tipoNomina, cotizPendVO, null);
						cot = cotizanteMgr.setNombresReales(this.tipoNomina, false, grupoConvenio.getIdMapaCod(), cot);
						Cotizacion cotizacion = fv.cotizPendVOtoView(this.tipoNomina, cot, cotizPendVO, caja, mutual, null);
						if (this.tipoNomina == 'R')
						{
							cot.setTasaCotizacion("" + buscaTasa(cot.getIdEntExCaja(), cot.getIdRegimenImp(), listaRegImp));
							cotizacion.setSaludObligFONASA("" + (new Integer(cotizacion.getSaludObligFONASA()).intValue() + new Integer(cotizacion.getSaludObligISAPRE()).intValue()));
							MapeosMgr mapeosMgr = new MapeosMgr(session);
							List listValidaApvs = conceptoMgr.getLstValidaAPVs();
							cot.setApvs(fv.apvPendToView(cotizPendVO, listValidaApvs, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class), new ArrayList()));
						} else if (this.tipoNomina == 'G')
							cot.setTasaCotizacion("" + buscaTasa(cot.getIdEntExCaja(), cot.getIdRegimenImp(), listaRegImp));
						else if (this.tipoNomina == 'D')
							cotizacion.setTipoRegimenPrev(cotizacion.getTipoRegimenPrev().equals("2") ? "AFP" : cotizacion.getTipoRegimenPrev().equals("1") ? "INP" : "");
						cot.setCotizacion(cotizacion);
						detalleExcel.add(cot);
					} catch (NumberFormatException e)
					{
						logger.error(" NumberFormatException en consulta excel ");
						continue;
					}
				}
			}
			actForm.setDetalleExcel(detalleExcel);

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Consulta-" + rutEmpresa + "-" + this.tipoNomina + ".xls");

			//PrintWriter writer = response.getWriter();
			OutputStream output= response.getOutputStream();
			// generaTabla(this.tipoNomina, detalleExcel, writer, actForm);
			ConstructorEXCEL.generaTabla(this.tipoNomina, detalleExcel, output, idGrupoConvenio, Utils.formatRut(rutEmpresa), idConvenio, 0,(caja != null ? caja.getNombre().trim() : ""),
					(mutual != null ? mutual.getNombre().trim() : ""), parametro.getValor(), "" + convenio.getMutualTasaAdicional(), Constants.TIPO_EMPRESA, true);

			//writer.close();
			output.close();
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ConsultaAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
		return null;
	}

	/**
	 * lista apv
	 * 
	 * @param apvs
	 * @return
	 */
	private List setApvs(String idCotizante)
	{
		List apvsTmp = new ArrayList();
		int count = 0;
		List listaApvs = (List) this.apvs.get(idCotizante);
		if (listaApvs != null)
			for (Iterator it = listaApvs.iterator(); it.hasNext();)
			{
				if (count == 5)
					break;
				ApvVO apv = (ApvVO) it.next();
				apv.setMontoFormat(Utils.formatMonto(apv.getAhorro()));
				apvsTmp.add(apv);
				count++;
			}
		for (int i = apvsTmp.size(); i < 5; i++)
			apvsTmp.add(new ApvVO());
		return apvsTmp;
	}

	/**
	 * busca tasa
	 * 
	 * @param idEntExCaja
	 * @param idRegImp
	 * @param listaRegImp
	 * @return
	 */
	public float buscaTasa(int idEntExCaja, int idRegImp, List listaRegImp)
	{
		if (listaRegImp != null)
			for (Iterator it = listaRegImp.iterator(); it.hasNext();)
			{
				RegImpositivoVO regImp = (RegImpositivoVO) it.next();
				if (regImp.getIdEntExCaja() == idEntExCaja && regImp.getIdRegImpositivo() == idRegImp)
					return regImp.getTasaPension();
			}
		return 0;
	}

	/**
	 * cotizantes nombre reales
	 * 
	 * @param tipoProceso
	 * @param cot
	 * @return
	 * @throws DaoException
	 */
	public Cotizante setNombresReales(char tipoProceso, Cotizante cot)
	{
		if (tipoProceso != 'D')
		{
			if (this.entsSalud.containsKey("" + cot.getIdEntSaludReal()))
				cot.setIdEntSalud((String) this.entsSalud.get("" + cot.getIdEntSaludReal()));
			if (this.entsAFP.containsKey("" + cot.getIdEntPensionReal()))
				cot.setIdEntPension((String) this.entsAFP.get("" + cot.getIdEntPensionReal()));
			if (cot.getIdEntAFCReal() != Constants.ID_INP && this.entsAFP.containsKey("" + cot.getIdEntAFCReal()))
				cot.setIdEntAFC((String) this.entsAFP.get("" + cot.getIdEntAFCReal()));
		} else if (this.entsAPV.containsKey("" + cot.getIdEntidadAPVCReal()))
			cot.setIdEntidadAPVC((String) this.entsAPV.get("" + cot.getIdEntidadAPVCReal()));
		return cot;
	}
}
