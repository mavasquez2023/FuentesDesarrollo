package cl.araucana.adminCpe.presentation.struts.actions.nomina;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ComprobanteMgr;
import cl.araucana.adminCpe.presentation.mgr.ConceptoMgr;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.CotizanteMgr;
import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
import cl.araucana.adminCpe.presentation.mgr.EntidadesMgr;
import cl.araucana.adminCpe.presentation.mgr.MapeosMgr;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.adminCpe.presentation.struts.forms.nomina.CotizacionActionForm;
import cl.araucana.adminCpe.utils.FactoryView;
import cl.araucana.core.util.NamesParser;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ApellidoCompuestoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MPVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RelacionTipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;

/*
 * @(#) NominasTrabajadorEditarAction.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.7
 */
public class DetalleTrabajadorAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleTrabajadorAction.class);

	public static final String EDITARR = "trabajadorEditarR";
	public static final String EDITARD = "trabajadorEditarD";
	public static final String EDITARG = "trabajadorEditarG";
	public static final String EDITARA = "trabajadorEditarA";
	public static final String EDITARF = "trabajadorEditarF";

	private EmpresaMgr empresaMgr;
	private ConvenioMgr convenioMgr;
	private ParametroMgr parametroMgr;
	private char tipoProceso;
	private EntidadCCAFVO caja = null;
	private EntidadMutualVO mutual = null;
	private int rutEmpresa = -1, idConvenio = -1;
	private String rutTrab = "-1";

	/**
	 * listar trabajadores
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("---------- NominasTrabajadorEditarAction ------------- ");
		Transaction tx = null;
		try
		{
			String operacion = ""; // (n, a, p): [nuevo, aprobado, pendiente]
			CotizacionPendienteVO cotPendVO = null;
			CotizanteVO cotizante = null;
			CotizacionActionForm form = (CotizacionActionForm) formulario;
			if (request.getParameter("operacion") != null)
				operacion = request.getParameter("operacion");
			else
				operacion = form.getOperacion();
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			logger.info("EditarAction:operacion:" + operacion + "::");
			this.empresaMgr = new EmpresaMgr(session);
			this.convenioMgr = new ConvenioMgr(session);
			this.parametroMgr = new ParametroMgr(session);
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			EntidadesMgr entidadesMgr = new EntidadesMgr(session);
			List entidadesSIL = entidadesMgr.getEntsSIL();
			form.setEntidadesSIL(entidadesSIL);
			form.setErrores(new ArrayList());
			form.setAvisos(new ArrayList());
			form.setErroresCD(new HashMap());

			if (operacion.equals(Constants.TXT_BTNS.getProperty("cancelar"))) // vuelve al inicio
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("cancelar"));
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("idEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("tipoNomina", form.getTipoProceso());
				return redirect;
			}

			List listParam = new ArrayList();
			listParam.add(new Integer(Constants.PARAM_DIAS_TOPE_ASIGFAM));
			listParam.add(new Integer(Constants.PARAM_APORTE_CCAF_FONASA));
			listParam.add(new Integer(Constants.PARAM_TOPE_AFP));
			listParam.add(new Integer(Constants.PARAM_TOPE_INP));
			listParam.add(new Integer(Constants.PARAM_TOPE_INDEMNIZACION));
			listParam.add(new Integer(Constants.PARAM_TOPE_CESANTIA));
			listParam.add(new Integer(Constants.PARAM_MIN_TRAB_PESADO));
			listParam.add(new Integer(Constants.PARAM_MAX_TRAB_PESADO));
			listParam.add(new Integer(Constants.PARAM_MIN_TASA_INDEM));
			listParam.add(new Integer(Constants.PARAM_MAX_TASA_INDEM));
			listParam.add(new Integer(Constants.PARAM_DIAS_X_MES));
			listParam.add(new Integer(Constants.PARAM_UF_ACTUAL));
			listParam.add(new Integer(Constants.PARAM_UF_ANTERIOR));
			listParam.add(new Integer(Constants.PARAM_PERIODO));
			listParam.add(new Integer(Constants.PARAM_AP_TRAB_IND_SEG_CES));
			listParam.add(new Integer(Constants.PARAM_AP_TRAB_PF_SEG_CES));
			listParam.add(new Integer(Constants.PARAM_AP_EMP_PF_SEG_CES));
			listParam.add(new Integer(Constants.PARAM_AP_EMP_IND_SEG_CES));
			listParam.add(new Integer(Constants.PARAM_TASA_FIJA));
			ParametrosHash param = this.parametroMgr.getParametrosHash(listParam);
			request.setAttribute("tabActual", "antecedentes");
			if (operacion.equals("mod") || operacion.equals("ver")) // viene desde lista, parametros en request, puede ser p o a
			{
				logger.info("buscando trabajador");
				this.rutEmpresa = new Integer(request.getParameter("rutEmpresa")).intValue();
				this.idConvenio = new Integer(request.getParameter("idConvenio")).intValue();
				this.tipoProceso = request.getParameter("tipoProceso").charAt(0);
				int idCotizante = -1;
				try
				{
					if (request.getParameter("idCotizante") != null && !request.getParameter("idCotizante").equals(""))// aprobado
						idCotizante = new Integer(request.getParameter("idCotizante")).intValue();
				} catch (Exception e)
				{
				}
				if (idCotizante > 0)// aprobado
				{
					this.rutTrab = request.getParameter("idCotizante");
					cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, idCotizante);
					form.setRutTrabOrigin(this.rutTrab);
					form.setMostrar("ap");
					logger.info("trabajador aprobado:" + idCotizante + ":encontrado?:" + (cotizante == null ? "no" : "si") + "::");
				} else
				// buscar cotizacion pendiente
				{
					int idCotizPend = new Integer(request.getParameter("idCotizPend")).intValue();
					cotPendVO = cotizanteMgr.getCotizPend(this.rutEmpresa, this.idConvenio, this.tipoProceso, idCotizPend);
					form.setIdCotizPendiente(idCotizPend);
					this.rutTrab = "" + idCotizPend;
					form.setMostrar("pen");
					logger.info("trabajador pendiente:" + idCotizPend + ":encontrado?:" + (cotPendVO == null ? "no" : "si") + "::");
				}
				form.setRutEmpresa("" + this.rutEmpresa);
				form.setTipoProceso("" + this.tipoProceso);
				form.setIdConvenio("" + this.idConvenio);
				logger.info("MODIFICAR:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + this.rutTrab + "::");
			} else
			// viene desde dentro, parametros en form
			{
				this.rutEmpresa = new Integer(form.getRutEmpresa()).intValue();
				this.idConvenio = new Integer(form.getIdConvenio()).intValue();
				this.rutTrab = form.getRutTrabOrigin();
				this.tipoProceso = form.getTipoProceso().charAt(0);
				if (Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("aplicar")))// puede ser a
				{
					logger.info("APLICAR:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + this.rutTrab + "::");
					cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, new Integer(this.rutTrab).intValue());
				} else if (operacion.equals("new"))// es n
				{
					cotizante = new CotizanteVO();
					if (this.tipoProceso == 'R')
					{
						cotizante.setCotizacion(new CotizacionREVO());
						List tmp = new ArrayList();
						for (int count = 0; count < 7; count++)
							tmp.add(new MovtoPersonal((count - 1), -1, -1, "", ""));
						form.setMovtosPersonal(tmp);
						form.setApvs(setApvs(null));
					} else if (this.tipoProceso == 'G')
						cotizante.setCotizacion(new CotizacionGRVO());
					else if (this.tipoProceso == 'A')
						cotizante.setCotizacion(new CotizacionRAVO());
					else if (this.tipoProceso == 'D')
						cotizante.setCotizacion(new CotizacionDCVO());
					form.setMostrar("new");
					form.setRutTrabOrigin("0");
					this.rutTrab = "0";
					logger.info("AGREGAR:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + form.getRutTrabOrigin() + "::");
				} else if (operacion.equals("")) // onChange de select
				// entidad exCaja, puede ser
				// n, p o a
				{
//					TODO descomentar envio mail
					/*Mail mail = new Mail(Mail.ERROR, "cchamblas@builderhouse.com", "zimbra", -1, "detalleTrabAdmin@builderhouse.com", "", "");
					mail.setLocalHost("aa.ss.dd");
					mail.setContenido(new StringBuffer("detalleTrabajador, app admin::" + request.getRequestURI() + "?" + request.getQueryString()));

					mail.setDescCorta("detalle trabajador, app admin, edicion?");
					ReportaError.enviar(mail);*/
					// TODO nunca deberia pasar por aca, no es edicion
					logger.info("operacion vacio:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + this.rutTrab + "::");
					request.setAttribute("tabActual", "inpTab");
					operacion = "exCaja";
				}
			}

			logger.info("rutEmpresa:" + this.rutEmpresa + ":IdConvenio:" + this.idConvenio + "::" + this.rutTrab + "::");

			int idGrupoConvenio = setCajaMutual(this.rutEmpresa, param, form);
			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(idGrupoConvenio);

			if (!operacion.equals("exCaja") && !this.rutTrab.equals("0"))// no es ni cambio exCaja ni nuevo
			{
				if (cotizante != null)
				{
					form.setRutTrabFormat(Utils.formatRut(cotizante.getIdCotizante()));
					form.setCotizante(FactoryView.cotizanteVOtoView(this.tipoProceso, cotizante));
					if (form.getCotizante() != null)
					{
						form.setCotizacion(FactoryView.cotizacionVOtoView(this.tipoProceso, true, cotizante, this.caja, this.mutual));
						if (form.getCotizacion() != null && this.tipoProceso == 'R')
						{
							// reforma
							form.getCotizante().setAfpVoluntario(((CotizacionREVO) cotizante.getCotizacion()).isVoluntario());
							form.setApvs(setApvs(cotizanteMgr.getApvs(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante())));
							form.setMovtosPersonal(setMovtosPersonal(form, param));
						}
					}
				} else if (cotPendVO != null)
				{
					logger.info("\n\npendiente encontrado!!" + cotPendVO.getIdCotizPendiente() + "::");
					form.setMostrar("pen");
					ConceptoMgr conceptoMgr = new ConceptoMgr(session);
					List listaConceptos = conceptoMgr.getListaConceptos("" + this.tipoProceso);
					List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(this.tipoProceso), "" + this.tipoProceso);
					Properties mapConceptos = new Properties();
					mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
					FactoryView fv = new FactoryView();
					fv.setListasConceptos(listaConceptos, listaMapeo, mapConceptos);
					NamesParser parser = NamesParser.getInstance();
					parser.setAutoLearning(true);
					List lista = cotizanteMgr.getApellCompuestos();
					for (Iterator it = lista.iterator(); it.hasNext();)
						parser.add((((ApellidoCompuestoVO) it.next()).getApellido()).trim());
					parser.setOutputDebug(System.out);

					Cotizante cot = fv.cotizPendVOtoView(this.tipoProceso, cotPendVO, parser);
					cot = cotizanteMgr.setIdsReales(this.tipoProceso, grupoConvenio.getIdMapaCod(), cot);
					form.setCotizante(cot);

					form.setCotizacion(fv.cotizPendVOtoView(this.tipoProceso, cot, cotPendVO, this.caja, this.mutual, parser));
					if (form.getCotizacion() != null && this.tipoProceso == 'R' && !cot.isAfpVoluntario())
					{
						List listValidaApvs = conceptoMgr.getLstValidaAPVs();
						MapeosMgr mapeosMgr = new MapeosMgr(session);
						List listaApv = new ArrayList();
						if (form.getCotizacion().getPrevisionAdicional() != null && !form.getCotizacion().getPrevisionAdicional().equals("")
								&& !form.getCotizacion().getPrevisionAdicional().trim().equals("0"))
							listaApv = addApv(cot.getIdEntPensionReal(), form.getCotizacion().getPrevisionAdicional(), mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class,
									EntidadApvVO.class));
						listaApv = fv.apvPendToView(cotPendVO, listValidaApvs, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class), listaApv);

						if (listaApv.size() > Constants.nAPVs_COTIZANTE)
							for (int i = Constants.nAPVs_COTIZANTE; i < listaApv.size(); i++)
								listaApv.remove(i);
						form.setApvs(listaApv);
						form.setMovtosPersonal(fv.mpPendToView(cotPendVO, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoVO.class, TipoMovimientoPersonalVO.class), entidadesSIL, Constants.TIPO_MOVTO_SIL));

					} else if (form.getCotizacion() != null && this.tipoProceso == 'R' && cot.isAfpVoluntario())
					{
						MapeosMgr mapeosMgr = new MapeosMgr(session);
						form.setMovtosPersonal(fv.mpPendToView(cotPendVO, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoAFVO.class, TipoMvtoPersoAFVO.class), entidadesSIL, Constants.TIPO_MOVTOAF_SIL));
					}
					form.setRutTrabFormat(cot.getRut());
					form.setRutTrabOrigin(cot.getIdCotizante());

					HashMap nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausas(this.tipoProceso, cotPendVO));
					form.setErrores((List) nivelErrorTrab.get("errores"));
					form.setAvisos((List) nivelErrorTrab.get("avisos"));
					form.setErroresCD((HashMap) nivelErrorTrab.get("descripcionError"));
				} else
				{
					form.setCotizacion(null);
					form.setCotizante(null);
				}
			} else
			{
				int idCotizante = new Integer(this.rutTrab).intValue();
				form.setRutTrabFormat(Utils.formatRut(idCotizante));
				form.getCotizante().setRut(form.getRutTrabFormat());
				form.getCotizante().setApellidos(form.getCotizante().getApellidoPat() + " " + form.getCotizante().getApellidoMat());
			}
			if (form.getErroresCD().size() == 0)
			{
				HashMap _nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausasAvisos(this.tipoProceso, this.rutEmpresa, this.idConvenio, Integer.parseInt(this.rutTrab)));
				form.setErrores((List) _nivelErrorTrab.get("errores"));
				form.setAvisos((List) _nivelErrorTrab.get("avisos"));
				form.setErroresCD((HashMap) _nivelErrorTrab.get("descripcionError"));
			}
			logger.info("rutEmpresa:" + this.rutEmpresa + ":IdConvenio:" + this.idConvenio + "::" + this.rutTrab + "::");

			logger.debug("\n\nCotizante:");
			logger.debug(ToStringBuilder.reflectionToString(form.getCotizante(), ToStringStyle.DEFAULT_STYLE));
			logger.debug("\n\nCotizacion:");
			logger.debug(ToStringBuilder.reflectionToString(form.getCotizacion(), ToStringStyle.DEFAULT_STYLE));
			EmpresaVO empresa = this.empresaMgr.getEmpresa(this.rutEmpresa);
			form.setRazonSocial(empresa.getRazonSocial());
			form.setRutEmpresaFormat(Utils.formatRut(this.rutEmpresa));

			setParams(form, param);
			llenaListas(this.rutEmpresa, form.getCotizante().isAfpVoluntario(), form, entidadesMgr, session);

			logger.info("saliendo::rutEmpresa:" + form.getRutEmpresa() + ":IdConvenio:" + form.getIdConvenio() + "::" + form.getRutTrabOrigin() + "::");
			tx.commit();

			if (form.getTipoProceso().equals("R") && form.getCotizante().isAfpVoluntario())
				return mapping.findForward(EDITARF);
			else if (form.getTipoProceso().equals("R"))
				return mapping.findForward(EDITARR);
			else if (form.getTipoProceso().equals("G"))
				return mapping.findForward(EDITARG);
			else if (form.getTipoProceso().equals("A"))
				return mapping.findForward(EDITARA);
			return mapping.findForward(EDITARD);
		} catch (Exception e)
		{
			logger.error("EditarAction::", e);
			if (tx != null)
				tx.rollback();

			MessageList l = new MessageList();
			l.add(new Message("Ha ocurrido un Error", e.getMessage()));
			request.setAttribute("messageList", l);
			return mapping.findForward("error");
		}
	}

	private List addApv(int idEntPensionReal, String montoApv, List mapeos)
	{
		List listaApvs = new ArrayList();
		ApvVO apv = new ApvVO(Constants.SIN_APV);
		try
		{
			for (Iterator it = mapeos.iterator(); it.hasNext();)
			{
				MapeoAPVVO mapeo = (MapeoAPVVO) it.next();
				if (mapeo.getId() == idEntPensionReal)
				{
					apv.setIdEntidadApv(idEntPensionReal);
					break;
				}
			}
		} catch (Exception e)
		{
		}
		apv.setMontoFormat(montoApv);
		listaApvs.add(apv);
		return listaApvs;
	}

	/**
	 * listar movimientos personal
	 * 
	 * @param form
	 * @param param
	 * @return
	 * @throws NumberFormatException
	 */
	private List setMovtosPersonal(CotizacionActionForm form, ParametrosHash param) throws NumberFormatException
	{
		List listaMovs = form.getCotizacion().getMovimientoPersonal();
		MovtoPersonal[] listaMovsTmp = new MovtoPersonal[7];
		int count = 0;
		String periodo = param.get("" + Constants.PARAM_PERIODO);
		int diasXMes = new Integer(param.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
		if (listaMovs != null)
		{
			SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
			for (Iterator it = listaMovs.iterator(); it.hasNext();)
			{
				MPVO mp = (MPVO) it.next();
				String inicio = (mp.getInicio() != null && fechaEnMes(mp.getInicio(), diasXMes, periodo) ? dateFormat.format(mp.getInicio()) : "");
				String termino = (mp.getTermino() != null && fechaEnMes(mp.getTermino(), diasXMes, periodo) ? dateFormat.format(mp.getTermino()) : "");
				int idEntSil = (mp.getIdTipoMovReal() == 3 ? form.getCotizante().getIdEntSil() : -1);
				listaMovsTmp[count++] = new MovtoPersonal(mp.getIdMovimiento(), idEntSil, mp.getIdTipoMovReal(), inicio, termino);
			}
		}
		for (; count < 7; count++)
			listaMovsTmp[count] = new MovtoPersonal((count - 1), -1, -1, "", "");
		List tmp = new ArrayList();
		for (int i = 0; i < listaMovsTmp.length; i++)
			tmp.add(listaMovsTmp[i]);
		return tmp;
	}

	/**
	 * fehca mes
	 * 
	 * @param fecha
	 * @param diasXMes
	 * @param periodo
	 * @return
	 */
	public static boolean fechaEnMes(Date fecha, int diasXMes, String periodo)
	{
		try
		{
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMM/dd");
			formatoFecha.setLenient(false); // Debe hacer esto gc.set
			// (GregorianCalendar. ANNO, 2003);
			Date d = formatoFecha.parse(periodo + "/01", new ParsePosition(0));
			// log.info("\n\nd:" + d.getTime() + ":" + d + "::");
			if (fecha.before(d))
			{
				logger.debug("antes:" + fecha.toString() + "::");
				return false;
			}
			logger.info(periodo + "/" + (diasXMes < 10 ? "0" : "") + diasXMes);
			d = formatoFecha.parse(periodo + "/" + (diasXMes < 10 ? "0" : "") + diasXMes, new ParsePosition(0));
			// log.info("\n\nd2:" + d.getTime() + ":" + d + "::");
			if (fecha.after(d))
			{
				logger.debug("despues:" + fecha.toString() + "::");
				return false;
			}
		} catch (Exception e)
		{
			return false;
		}

		logger.debug("ok!:" + fecha.toString() + "::");
		return true;
	}

	/**
	 * caja mutual
	 * 
	 * @param rutEmpresa
	 * @param param
	 * @param form
	 * @return
	 * @throws DaoException
	 */
	private int setCajaMutual(int rutEmpresa, ParametrosHash param, CotizacionActionForm form) throws DaoException
	{
		ConvenioVO convenio = this.convenioMgr.getConvenio(rutEmpresa, new Integer(form.getIdConvenio()).intValue());
		this.caja = null;
		this.mutual = null;
		form.setConvenio(convenio.getDescripcion());
		if (convenio.getIdCcaf() != Constants.SIN_CCAF)
		{
			this.caja = this.convenioMgr.getCaja(convenio.getIdCcaf());
			form.setCaja(this.caja);
		} else
			form.setCaja(new EntidadCCAFVO(Constants.SIN_CCAF));
		if (convenio.getIdMutual() != Constants.SIN_MUTUAL)
		{
			this.mutual = this.convenioMgr.getMutual(convenio.getIdMutual());
			form.setNomMutual(this.mutual.getNombre().trim());
			form.setIdMutual(this.mutual.getId());
			form.setTasaMutual(Utils.redondear(convenio.getMutualTasaAdicional() + new Float(param.get("" + Constants.PARAM_TASA_FIJA)).floatValue(), 2));
		} else
			form.setIdMutual(Constants.SIN_MUTUAL);
		return convenio.getIdGrupoConvenio();
	}

	/**
	 * parametros
	 * 
	 * @param form
	 * @param param
	 */
	private void setParams(CotizacionActionForm form, ParametrosHash param)
	{
		float ufActual = new Float(param.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
		float ufAnterior = new Float(param.get("" + Constants.PARAM_UF_ANTERIOR)).floatValue();

		form.setDiasTopeAF(new Integer(param.get("" + Constants.PARAM_DIAS_TOPE_ASIGFAM)).intValue());
		form.setAporteCCAFFON(new Float(param.get("" + Constants.PARAM_APORTE_CCAF_FONASA)).floatValue());
		form.setTopeAFP(Math.round(new Float(param.get("" + Constants.PARAM_TOPE_AFP)).floatValue() * ufActual));
		form.setTopeINP(Math.round(new Float(param.get("" + Constants.PARAM_TOPE_INP)).floatValue() * ufAnterior));
		form.setTopeIndemn(Math.round(new Float(param.get("" + Constants.PARAM_TOPE_INDEMNIZACION)).floatValue() * ufActual));
		form.setTopeCesantia(Math.round(new Float(param.get("" + Constants.PARAM_TOPE_CESANTIA)).floatValue() * ufActual));

		List trabPesado = new ArrayList();
		trabPesado.add(new LabelValueBean(param.get("" + Constants.PARAM_MIN_TRAB_PESADO), String.valueOf(Float.parseFloat(param.get("" + Constants.PARAM_MIN_TRAB_PESADO)))));
		trabPesado.add(new LabelValueBean(param.get("" + Constants.PARAM_MAX_TRAB_PESADO), String.valueOf(Float.parseFloat(param.get("" + Constants.PARAM_MAX_TRAB_PESADO)))));
		form.setPorcentajeTrabPesado(trabPesado);

		form.setMinTasaIndemn(new Float(param.get("" + Constants.PARAM_MIN_TASA_INDEM)).floatValue());
		form.setMaxTasaIndemn(new Float(param.get("" + Constants.PARAM_MAX_TASA_INDEM)).floatValue());
		form.setDiasXMes(new Integer(param.get("" + Constants.PARAM_DIAS_X_MES)).intValue());

		form.setApEmpIndSegCes(new Float(param.get("" + Constants.PARAM_AP_EMP_IND_SEG_CES)).floatValue());
		form.setApEmpPFSegCes(new Float(param.get("" + Constants.PARAM_AP_EMP_PF_SEG_CES)).floatValue());
		form.setApTrabIndSegCes(new Float(param.get("" + Constants.PARAM_AP_TRAB_IND_SEG_CES)).floatValue());
		form.setApTrabPFSegCes(new Float(param.get("" + Constants.PARAM_AP_TRAB_PF_SEG_CES)).floatValue());

		form.setPeriodo(param.get("" + Constants.PARAM_PERIODO));
	}

	/**
	 * lista apv
	 * 
	 * @param apvs
	 * @return
	 */
	private List setApvs(List apvs)
	{
		List apvsTmp = new ArrayList();
		if (apvs != null)
			for (Iterator it = apvs.iterator(); it.hasNext();)
			{
				ApvVO apv = (ApvVO) it.next();
				apv.setMontoFormat(Utils.formatMonto(apv.getAhorro()));
				apvsTmp.add(apv);
			}
		for (int i = apvsTmp.size(); i < 5; i++)
			apvsTmp.add(new ApvVO());
		return apvsTmp;
	}

	/**
	 * llenas listas
	 * 
	 * @param rutEmp
	 * @param isAfpVoluntario
	 * @param form
	 * @param entidadesMgr
	 * @param session
	 * @throws DaoException
	 */
	private void llenaListas(int rutEmp, boolean isAfpVoluntario, CotizacionActionForm form, EntidadesMgr entidadesMgr, Session session) throws DaoException
	{
		form.setGeneros(entidadesMgr.getGeneros());
		form.setSucursales(this.empresaMgr.getSucursales("" + rutEmp));
		form.setEntidadesSalud(entidadesMgr.getEntsSalud(true));
		if (this.tipoProceso != 'D')
			form.setEntidadesPension(entidadesMgr.getEntsPension(true, isAfpVoluntario));
		else
			form.setEntidadesPension(entidadesMgr.getEntsApvs());
		form.setEntidadesAFC(entidadesMgr.getEntsAFC());
		form.setEntidadesExCaja(entidadesMgr.getEntsExCaja());
		if (form.getCotizante() != null && form.getCotizante().getIdEntExCaja() != -1)
			form.setCodRegImp(entidadesMgr.getCodRegImp(form.getCotizante().getIdEntExCaja()));
		else
			form.setCodRegImp(new ArrayList());
		form.setEntidadesApvs(entidadesMgr.getEntsApvs());
		form.setEntidadesSIL(entidadesMgr.getEntsSIL());

		form.setTramosAsigFam(entidadesMgr.getTramosAsigFam());
		form.setTiposProcesos((new ComprobanteMgr(session)).getTiposProceso());
		if (this.tipoProceso == 'R')
		{
			if (isAfpVoluntario)
				form.setTiposMovPersonal(entidadesMgr.getTiposMovPersonalAF());
			else
				form.setTiposMovPersonal(entidadesMgr.getTiposMovPersonal());
		}
		form.setMensajesErrores(detalleErrores(form.getErroresCD()));
	}

	private String mensajes(HashMap erroresCD, String datos)
	{
		String mensaje = "";
		int largo = datos.split(",").length;
		String valor[] = datos.split(",");
		for (int a = 0; a < largo; a++)
		{
			mensaje += erroresCD.get(new Integer(valor[a])) != null ? (String) erroresCD.get(new Integer(valor[a])) : "";
		}
		return mensaje;
	}

	private HashMap detalleErrores(HashMap erroresCD) throws DaoException
	{
		HashMap retorno = new HashMap();
		List lista = this.parametroMgr.getRelacionTipoCausa();
		if (lista != null)
		{
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				RelacionTipoCausaVO rel = (RelacionTipoCausaVO) it.next();
				retorno.put(rel.getCampo().trim(), mensajes(erroresCD, rel.getIdTipoCausas().trim()));
			}
		}
		return retorno;
	}
}
