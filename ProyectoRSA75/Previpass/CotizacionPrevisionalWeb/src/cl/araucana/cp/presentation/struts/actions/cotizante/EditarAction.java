package cl.araucana.cp.presentation.struts.actions.cotizante;

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
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RelacionTipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal;
import cl.araucana.cp.hibernate.dao.ValidacionesDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ConceptoMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.CotizanteMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.EntidadesMgr;
import cl.araucana.cp.presentation.mgr.MapeosMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.presentation.struts.forms.cotizante.CotizacionActionForm;
import cl.araucana.cp.presentation.utils.FactoryView;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;

/*
 * @(#) EditarAction.java 1.30 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author pfrigolett
 * @author cchamblas
 * 
 * @version 1.30
 */
public class EditarAction extends AppAction
{
	public static final String EDITARR = "trabajadorEditarR";
	public static final String EDITARD = "trabajadorEditarD";
	public static final String EDITARG = "trabajadorEditarG";
	public static final String EDITARA = "trabajadorEditarA";
	public static final String EDITARF = "trabajadorEditarF";
	public static final String VERR = "trabajadorVerR";
	public static final String VERD = "trabajadorVerD";
	public static final String VERG = "trabajadorVerG";
	public static final String VERA = "trabajadorVerA";
	public static final String VERF = "trabajadorVerF";
	private EmpresaMgr empresaMgr;
	private ConvenioMgr convenioMgr;
	private ProcesoMgr procesoMgr;
	static Logger logger = Logger.getLogger(EditarAction.class);
	private char tipoProceso;
	private EntidadCCAFVO caja = null;
	private EntidadMutualVO mutual = null;
	private int rutEmpresa = -1, idConvenio = -1;
	private String rutTrab = "-1";
//	clillo 3-12-14 por Reliquidación
	private int periodo=0;
	private int periodo_old=0;
	
	public EditarAction()
	{
		super();
		this.btns.add("aplicar");
		this.btns.add("guardar");
		this.btns.add("cancelar");
	}

	/**
	 * transaction
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
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
			
			String respaldoOperacion = (form.getRespaldoOperacion() != null && form.getRespaldoOperacion().length() > 2) ? form.getRespaldoOperacion().substring(0, 3) : "";
			boolean flgExito = false;
			Session session = HibernateUtil.getSession();
			logger.info("EditarAction:operacion:" + operacion + "::");

			String auxSubSubAccion	= request.getParameter("subSubAccion") != null ? request.getParameter("subSubAccion") : "";

			// hace calculos para determinar si es posible o no editar la nomina:
			// - fecha actual vs fecha limite edicion nomina (tabla parametro)
			// - permisos del usuario logeado (admin empresa, encargado lector/editor)
			// - estado de la nomina
			boolean puedeEditar= true;
			ParametroMgr parametro = new ParametroMgr(HibernateUtil.getSession());
			UsuarioMgr usuarioMgr = new UsuarioMgr(session);
			boolean contErrores = false;

			//csanchez Cuando hay discrepancias de RUT, éste se rescata de la URL.
			int rutEmpresaURL = new Integer( request.getParameter("rutEmpresa").equals("") ? "0" : request.getParameter("rutEmpresa") ).intValue();
			if (rutEmpresaURL != this.rutEmpresa && rutEmpresaURL != 0 ) {
				this.rutEmpresa = rutEmpresaURL;
			}

			int idNivelAcceso = usuarioMgr.getNivelPermiso(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), new Integer(form.getIdConvenio()).intValue(), new Integer(rutEmpresa)
					.intValue());
			if (parametro.plazoCerrado(Constants.PARAM_FIN_EDICION_NOM) == 1 || idNivelAcceso != Constants.NIVEL_ACC_CONV_SUC_EDITOR)
				puedeEditar = false;
			else
			{// esta dentro del plazo de edicion, tiene permisos de editor (admin/encargado), => depende estado de nomina
				ProcesoMgr procesoMgr = new ProcesoMgr(session);
				NominaVO nomina = procesoMgr.getNomina(form.getTipoProceso(), new Integer(rutEmpresa).intValue(), new Integer(form.getIdConvenio()).intValue());
				
				if (nomina != null) {
					if (nomina.getIdEstado() == Constants.EST_NOM_PAGADO			  ||
						nomina.getIdEstado() == Constants.EST_NOM_PAGADO_PARCIALMENTE ||
						nomina.getIdEstado() == Constants.EST_NOM_PRECURSADA		  ||
						nomina.getIdEstado() == Constants.EST_NOM_PREPAGADA)
						puedeEditar = false;
				}
			}

			if (auxSubSubAccion.equals("trabajadorFicha"))
				form.setFlgTrabNomina(1);	//Este campo indica que se viene desde Nómina -> Trabajadores

			if (operacion == null)
				return new ActionRedirect(mapping.findForward("inicio"));

			if (operacion.equals(Constants.TXT_BTNS.getProperty("cancelar"))) // vuelve al inicio
			{
				ActionRedirect redirect;
				if (form.getFlgTrabNomina() == 1)
					redirect = new ActionRedirect(mapping.findForward("trabListaAll"));
				else
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("subSubAccion", "nominaEditar");
				redirect.addParameter("idEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("tipoNomina", form.getTipoProceso());
				return redirect;
			}

			if (operacion.equals("volver"))
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("volver"));
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("subSubAccion", "nominaEditar");
				redirect.addParameter("idEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("tipoNomina", request.getParameter("tipoProceso"));
				return redirect;
			}

			if (operacion.equals("recargar"))
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("recargar"));
				redirect.addParameter("rutEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("idCotizante", form.getRutTrabOrigin());
				redirect.addParameter("tipoProceso", request.getParameter("tipoProceso"));
				redirect.addParameter("operacion", "mod");
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("subSubAccion", "trabajadorVer");
				request.setAttribute("cambioParam", "accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorVer&rutEmpresa=" + form.getRutEmpresa() + "&idConvenio=" + form.getIdConvenio()
						+ "&tipoProceso=" + request.getParameter("tipoProceso") + "&operacion=mod" + "&idCotizPend=" + request.getParameter("idCotizPend") + "&idCotizante="
						+ request.getParameter("idCotizante"));

				return redirect;
			}

			tx = session.beginTransaction();
			this.empresaMgr = new EmpresaMgr(session);
			this.convenioMgr = new ConvenioMgr(session);
			this.procesoMgr = new ProcesoMgr(session);
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			ParametroMgr parametroMgr = new ParametroMgr(session);
			EntidadesMgr entidadesMgr = new EntidadesMgr(session);

			form.setErrores(new ArrayList());
			form.setAvisos(new ArrayList());
			form.setErroresCD(new HashMap());
			form.setMensajesErrores(new HashMap());
			form.setIsAviso(parametroMgr.getTiposCausasWarn());
			List entidadesSIL = entidadesMgr.getEntsSIL();
			form.setEntidadesSIL(entidadesSIL);

			form.setIdSinAFP(Constants.ID_SIN_AFP);
			form.setIdAFPNinguna(Constants.ID_AFP_NINGUNA);
			form.setIdFONASA(Constants.ID_FONASA);
			form.setIdSaludNinguna(Constants.ID_SALUD_NINGUNA);

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
			listParam.add(new Integer(Constants.PARAM_TOLERANCIA_PESO));
			listParam.add(new Integer(Constants.PARAM_PRIMER_PERIODO_VIG_PLENA_SIS));
			listParam.add(new Integer(Constants.PARAM_MINIMO_TRABAJADORES_SIS));
			
			ParametrosHash param = parametroMgr.getParametrosHash(listParam);
			
			//Asepulveda
			
			float ufActual = new Float(param.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
			float topeAfpUF = new Float(param.get("" + Constants.PARAM_TOPE_AFP)).floatValue();
			request.getSession().setAttribute("topeImpAFPPesos", "" + Math.round(ufActual * topeAfpUF));
			
			float ufAnterior = new Float(param.get("" + Constants.PARAM_UF_ANTERIOR)).floatValue();
			float topeInpUF = new Float(param.get("" + Constants.PARAM_TOPE_INP)).floatValue();
			request.getSession().setAttribute("topeImpINPPesos", "" + Math.round(ufAnterior * topeInpUF));			
			
			request.setAttribute("tabActual", "antecedentes");
			
			
			
			if (operacion.equals("mod") || operacion.equals("ver") || operacion.equals("recargar")) // viene desde lista, parametros en request, puede ser p o a
			{
				logger.debug("buscando trabajador");
				this.rutEmpresa = new Integer(request.getParameter("rutEmpresa")).intValue();
				this.idConvenio = new Integer(request.getParameter("idConvenio")).intValue();
				this.tipoProceso = request.getParameter("tipoProceso").charAt(0);
//				clillo 3-12-14 por Reliquidación
				if(this.tipoProceso== 'A'){
					this.periodo = Integer.parseInt(request.getParameter("periodo"));
				}else{
					//this.periodo= Integer.parseInt(param.get("" + Constants.PARAM_PERIODO));
					this.periodo= 0;
				}
				int idCotizante = -1;
				try
				{
					if (request.getParameter("idCotizante") != null && !request.getParameter("idCotizante").equals(""))// aprobado
						idCotizante = new Integer(request.getParameter("idCotizante")).intValue();
					if (form.getCotizante().getIdCotizante() != null && !form.getCotizante().getIdCotizante().equals(""))// aprobado
						idCotizante = new Integer(form.getCotizante().getIdCotizante()).intValue();
				} catch (Exception e)
				{
				}
				if (idCotizante > 0)// aprobado
				{
					this.rutTrab = request.getParameter("idCotizante");
//					clillo 3-12-14 por Reliquidación
					//cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, idCotizante);
					cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, idCotizante, periodo);
					form.setRutTrabOrigin(this.rutTrab);
					form.setMostrar("ap");
//					clillo 3-12-14 por Reliquidación
					form.setPeriodoReli(cotizante.getPeriodo());
					form.setPeriodoReli_old(cotizante.getPeriodo());
					logger.debug("trabajador aprobado:" + idCotizante + ":encontrado?:" + (cotizante == null ? "no" : "si") + "::");
				} else
				// buscar cotizacion pendiente
				{
					int idCotizPend = new Integer(request.getParameter("idCotizPend")).intValue();
//					clillo 3-12-14 por Reliquidación
					//cotPendVO = cotizanteMgr.getCotizPend(this.rutEmpresa, this.idConvenio, this.tipoProceso, idCotizPend);
					cotPendVO = cotizanteMgr.getCotizPend(this.rutEmpresa, this.idConvenio, this.tipoProceso, idCotizPend, this.periodo);
					form.setIdCotizPendiente(idCotizPend);
					this.rutTrab = "" + idCotizPend;// new Integer(cotPendVO.getIdCotizante()).intValue();
					form.setMostrar("pen");
//					clillo 10-12-14 por Reliquidación
					form.setPeriodoReli(cotPendVO.getPeriodo());
					form.setPeriodoReli_old(cotPendVO.getPeriodo());
					logger.debug("trabajador pendiente:" + idCotizPend + ":encontrado?:" + (cotPendVO == null ? "no" : "si") + "::");
				}
				form.setRutEmpresa("" + this.rutEmpresa);
				form.setTipoProceso("" + this.tipoProceso);
				form.setIdConvenio("" + this.idConvenio);

				logger.debug("MODIFICAR:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + this.rutTrab + "::");
			} else
			// viene desde dentro, parametros en form
			{
				this.rutEmpresa = new Integer(form.getRutEmpresa()).intValue();
				this.idConvenio = new Integer(form.getIdConvenio()).intValue();
				this.rutTrab = form.getRutTrabOrigin();
				this.tipoProceso = form.getTipoProceso().charAt(0);
//				clillo 3-12-14 por Reliquidación
				if(this.tipoProceso== 'A'){
					this.periodo = new Integer(form.getPeriodoReli()).intValue();
					this.periodo_old = new Integer(form.getPeriodoReli_old()).intValue();
				}else{
					//this.periodo_old= this.periodo;
				}
				//fin
				if (Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("aplicar")))// puede ser a
				{
					logger.debug("APLICAR:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + this.rutTrab + "::");
//					clillo 3-12-14 por Reliquidación
					//cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, new Integer(this.rutTrab).intValue());
					cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, new Integer(this.rutTrab).intValue(), periodo);
				} else if (operacion.equals("new") || operacion.equals("newR"))// es n
				{
					cotizante = new CotizanteVO();
					if (this.tipoProceso == 'R')
					{
						cotizante.setCotizacion(new CotizacionREVO());
						List tmp = new ArrayList();
						for (int count = 0; count < Constants.NUM_MAX_MOVTO; count++)
							tmp.add(new MovtoPersonal((count - 1), -1, -1, "", ""));
						form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTO);
						form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTO_SIL);

						form.setMovtosPersonal(tmp);
						form.setApvs(setApvs(null));
						form.setNumMaxAPVs(Constants.nAPVs_COTIZANTE);
					} else if (this.tipoProceso == 'G')
						cotizante.setCotizacion(new CotizacionGRVO());
					else if (this.tipoProceso == 'A')
						cotizante.setCotizacion(new CotizacionRAVO());
					else if (this.tipoProceso == 'D')
						cotizante.setCotizacion(new CotizacionDCVO());
					form.setMostrar("new");
					form.setRutTrabOrigin("0");
					this.rutTrab = "0";
					logger.debug("AGREGAR:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + form.getRutTrabOrigin() + "::");
					//csanchez. Variable que indica que el trabajador corresponde a uno Dependiente
					request.setAttribute("tipoTrabajador", "D");
				} else if (operacion.equals("newF"))
				{
					cotizante = new CotizanteVO();
					llenaListas(this.rutEmpresa, true, parametroMgr.getRelacionTipoCausa(), form, entidadesMgr, session);
					cotizante.setCotizacion(new CotizacionREVO());
					List tmp = new ArrayList();
					for (int count = 0; count < Constants.NUM_MAX_MOVTOAF; count++)
						tmp.add(new MovtoPersonal((count - 1), -1, -1, "", ""));
					form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTOAF);
					form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTOAF_SIL);
					form.setMovtosPersonal(tmp);
					form.setApvs(setApvs(null));
					form.setMostrar("new");
					form.setRutTrabOrigin("0");
					form.setTipoProceso(request.getParameter("tipoProceso").toString());

					EmpresaVO empresa = this.empresaMgr.getEmpresa(this.rutEmpresa);
					form.setRazonSocial(empresa.getRazonSocial());
					form.setRutEmpresaFormat(Utils.formatRut(this.rutEmpresa));
					form.setPeriodo(String.valueOf(param.get("" + Constants.PARAM_PERIODO)).trim());

					ConvenioVO convenio = this.convenioMgr.getConvenio(this.rutEmpresa, new Integer(form.getIdConvenio()).intValue());
					setCajaMutual(param, form, convenio);

					this.rutTrab = "0";
					logger.debug("AGREGAR:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + form.getRutTrabOrigin() + "::");
					//csanchez. Variable que indica que el trabajador corresponde a uno Voluntario
					request.setAttribute("tipoTrabajador", "V");
					return mapping.findForward(EDITARF);
				} else if (operacion.equals(Constants.TXT_BTNS.getProperty("guardar")))// , puede ser n, p o a
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					List mps = new ArrayList();
					int i = 1;
					int newRut = Utils.limpiaRut(true, form.getCotizante().getRut());// con/sin puntos, con/sin digito, limpia y asigna sin DV
					int idEntSil = Constants.ENTSIL_FALSO;

					//TODO
					// Si el trabajador es nuevo, se comprueba que no exista.
					if (respaldoOperacion.equals("new")) {
						CotizanteMgr cotMgr = new CotizanteMgr(session);
//						clillo 3-12-14 por Reliquidación
						//CotizanteVO cotTest = cotMgr.getCotizante(String.valueOf(this.rutEmpresa), String.valueOf(this.idConvenio), String.valueOf(newRut));
						CotizanteVO cotTest = cotMgr.getCotizante(String.valueOf(this.rutEmpresa), String.valueOf(this.idConvenio), String.valueOf(newRut), this.periodo);
						if (cotTest != null) {
							ActionMessages am = new ActionMessages();
							if (this.tipoProceso == 'A'){
								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cotizacion.periodo_duplicado"));
							}else{
								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cotizacion.duplicado"));
							}
							this.saveMessages(request, am);
							ActionRedirect redirect = new ActionRedirect(mapping.findForward("error"));
							return redirect;
						}
					}

					if (this.tipoProceso == 'R' && !form.getCotizante().isAfpVoluntario())
					{
						for (Iterator it = form.getMovtosPersonal().iterator(); it.hasNext();)
						{
							MovtoPersonal mp = (MovtoPersonal) it.next();
							if (mp.getIdTipoMovReal() != -1)
							{
								MovtoPersonalVO mpVO = new MovtoPersonalVO(this.rutEmpresa, this.idConvenio, newRut, i++);
								mpVO.setIdTipoMovReal(mp.getIdTipoMovReal());
								mpVO.setInicio(mp.getInicio() != null && !mp.getInicio().equals("") ? new java.sql.Date(dateFormat.parse(mp.getInicio()).getTime()) : null);
								mpVO.setTermino(mp.getTermino() != null && !mp.getTermino().equals("") ? new java.sql.Date(dateFormat.parse(mp.getTermino()).getTime()) : null);
								mps.add(mpVO);
								if (mp.getIdEntidadSil() == -1)
									idEntSil = Constants.ENTSIL_FALSO;
								else
									idEntSil = mp.getIdEntidadSil();
								mpVO.setIdTipoMov(String.valueOf(idEntSil));
							}
						}
					} else if (this.tipoProceso == 'R')	// AFP Voluntario
					{
						for (Iterator it = form.getMovtosPersonal().iterator(); it.hasNext();)
						{
							MovtoPersonal mp = (MovtoPersonal) it.next();
							if (mp.getIdTipoMovReal() != -1)
							{
								MvtoPersoAFVO mpVO = new MvtoPersoAFVO(this.rutEmpresa, this.idConvenio, newRut, i++);
								mpVO.setIdTipoMovReal(mp.getIdTipoMovReal());
								mpVO.setInicio(mp.getInicio() != null && !mp.getInicio().equals("") ? new java.sql.Date(dateFormat.parse(mp.getInicio()).getTime()) : null);
								mpVO.setTermino(mp.getTermino() != null && !mp.getTermino().equals("") ? new java.sql.Date(dateFormat.parse(mp.getTermino()).getTime()) : null);
								mps.add(mpVO);
								if (mp.getIdEntidadSil() == -1)
									idEntSil = Constants.ENTSIL_FALSO;
								else
									idEntSil = mp.getIdEntidadSil();
								mpVO.setIdTipoMov(String.valueOf(idEntSil));
							}
						}
					}
					List listApvs = new ArrayList();
					logger.debug("add APVs:");
					for (Iterator it = form.getApvs().iterator(); it.hasNext();)
					{
						ApvVO apv = (ApvVO) it.next();
						if (apv.getIdEntidadApv() > 0 || apv.getAhorro() > 0)
						{
							logger.info("\tadd APV:" + apv.getIdEntidadApv() + "::" + apv.getMontoFormat() + "::");
							apv.setAhorro(Utils.desFormatMonto(apv.getMontoFormat()));
							apv.setIdConvenio(this.idConvenio);
							apv.setIdCotizante(newRut);
							apv.setRutEmpresa(this.rutEmpresa);
							listApvs.add(apv);
						}
					}
					int entidadSalud = form.getCotizante().getIdEntSaludReal();
					
					CotizanteVO cotizanteVO = FactoryView.viewToCotizanteVO( this.tipoProceso
																		   , form.getCotizante()
																		   , this.rutEmpresa
																		   , this.idConvenio
																		   , newRut
																		   , form.getTipoPrevision()
																		   , idEntSil
																		   , form.getIdCaja()
																		   , listApvs
																		   , this.periodo); //	clillo 3-12-14 por Reliquidación

					NominaVO nominaTemporal = procesoMgr.getNomina(form.getTipoProceso(), new Integer(form.getRutEmpresa()).intValue(), this.idConvenio);

					cotizanteVO.setCotizacion(FactoryView.viewToCotizacionVO( this.tipoProceso
																			, form.getCotizante()
																			, form.getCotizacion()
																			, this.rutEmpresa
																			, this.idConvenio
																			, newRut
																			, form.getTipoPrevision()
																			, form.getIdCaja()
																			, form.getIdMutual()
																			, mps
																			, (nominaTemporal != null ? this.isDatosSIS(nominaTemporal) : false)
																			, this.periodo)); //	clillo 3-12-14 por Reliquidación
					//TODO instrucciones nuevas
					//if sin entidad y fonasa false -> sin entidad
					if (entidadSalud == Constants.ID_SALUD_NINGUNA){
						if (form.getFonasaCheck() == null){
							cotizanteVO.setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
							form.getCotizante().setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
						}else{//else sin entidad y fonasa true -> fonasa
							cotizanteVO.setIdEntSaludReal(Constants.ID_FONASA);
							form.getCotizante().setIdEntSaludReal(Constants.ID_SALUD_NINGUNA);
							form.setFonasaCheck("true");
						}
					}

					if (this.tipoProceso == 'R' && form.getCotizante().isAfpVoluntario())
						cotizanteVO.setAfpVoluntario(true);
					logger.debug("guardando:" + this.rutTrab + "::" + cotizanteVO.getIdCotizante() + "::");
					int ii=0;
					int oldRut = Utils.limpiaRut(form.getIdCotizPendiente() > 0 ? true : false, this.rutTrab);// con/sin puntos, con/sin digito, limpia y asigna sin DV
					List result = new ArrayList();
					try
					{
//						clillo 3-12-14 por Reliquidación
						//result = cotizanteMgr.guardaCotizante(this.tipoProceso, form.getIdCotizPendiente(), user.getLogin(), "" + oldRut, cotizanteVO, form.getIsAviso());
						result = cotizanteMgr.guardaCotizante(this.tipoProceso, form.getIdCotizPendiente(), user.getLogin(), "" + oldRut, cotizanteVO, form.getIsAviso(), periodo_old);
					} catch (Exception e)
					{
						if (e != null && e.getMessage() != null)
						{
							ActionMessages am = new ActionMessages();
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage()));
							this.saveMessages(request, am);
							if (form.getCotizante().isAfpVoluntario())
								return mapping.findForward(EDITARF);
							return mapping.findForward("error");
						}
						result = null;
					}

					if (form.getCotizante().isAfpVoluntario())
					{
						form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTOAF);
						form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTOAF_SIL);
					} else
					{
						form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTO);
						form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTO_SIL);
						form.setNumMaxAPVs(Constants.nAPVs_COTIZANTE);
					}

					if (result == null)// el EJB se cayo al procesar
					{
						ActionErrors ae = new ActionErrors();
						ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cotizacion.errorProceso"));
						this.saveErrors(request, ae);
					} else
					{
						boolean isAviso = cotizanteMgr.isAviso(result, form.getIsAviso());
						//TODO GMALLEA 26-06-2012 Se limpia el resulta para que cuando tenga avisos se eliminan y asi se traran como trabajador sin evisos..
						//result.clear();
						//Todo GMALLEA 26-07-2013 PARA LIMPIAR EL MP DE TIPO 9
						MovtoPersonal movtoPersonal = null;
						List listMPInico = new ArrayList();
						if(result.contains(new Integer(9060))){
							for(Iterator it = form.getMovtosPersonal().iterator(); it.hasNext();){
								movtoPersonal = (MovtoPersonal)it.next();
								if(movtoPersonal.getIdTipoMovReal() == 9){
									movtoPersonal.setInicio("");
									movtoPersonal.setBloquear(true);
								}
								listMPInico.add(movtoPersonal);
							}
							form.getMovtosPersonal().clear();
							form.setMovtosPersonal(listMPInico);
							
						}
						List listMPFin = new ArrayList();
						if(result.contains(new Integer(9070))){
							for(Iterator it = form.getMovtosPersonal().iterator(); it.hasNext();){
								movtoPersonal = (MovtoPersonal)it.next();
								if(movtoPersonal.getIdTipoMovReal() == 9){
									movtoPersonal.setTermino("");
									movtoPersonal.setBloquear(true);
								}
								listMPFin.add(movtoPersonal);
							}
							form.getMovtosPersonal().clear();
							form.setMovtosPersonal(listMPFin);
						}
						
						ValidacionesDAO validacionesDAO = new ValidacionesDAO(session);
						HashMap hasCausaError = validacionesDAO.getTiposCausasErr();
						for (Iterator it = result.iterator(); it.hasNext();)
						{
						
							Integer valor = (Integer) it.next();
							if(hasCausaError.containsKey(""+valor)){
								contErrores = true;
							}
						}
						
						if (result.size() > 0 && !isAviso) // proceso bien, pero encontro errores
						{
							HashMap nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(result);
							form.setErrores((List) nivelErrorTrab.get("errores"));
							form.setAvisos((List) nivelErrorTrab.get("avisos"));
							form.setErroresCD((HashMap) nivelErrorTrab.get("descripcionError"));
	
							ActionErrors ae = new ActionErrors();
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cotizacion.errores"));
							this.saveErrors(request, ae);
						} else if (result.size() > 0 && isAviso) // proceso bien, pero encontro avisos
						{
							HashMap nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(result);
							form.setErrores((List) nivelErrorTrab.get("errores"));
							form.setAvisos((List) nivelErrorTrab.get("avisos"));
							form.setErroresCD((HashMap) nivelErrorTrab.get("descripcionError"));

							ActionMessages am = new ActionMessages();
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cotizacion.avisos"));
							//this.empresaMgr.borraCRCporEmpresa(this.idConvenio, this.rutEmpresa);
							this.saveMessages(request, am);
						} else
						// proceso, y aprobo cotizacion
						{
							form.setIdCotizPendiente(0);
							ActionMessages am = new ActionMessages();
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cotizacion.guardar"));
							//this.empresaMgr.borraCRCporEmpresa(this.idConvenio, this.rutEmpresa);
							this.saveMessages(request, am);
							flgExito = true;
						}
					}
					if (result == null || result.size() > 0)
					{// no se guardo, quedo pendiente
						Cotizante cot = form.getCotizante();
						cot.setIdConvenio(this.idConvenio);
						cot.setRutEmpresa(this.rutEmpresa);

						//GMALLEA 28-06-2013 Se comenta ya que puede tener entidad afc y inp.
						//Si la entidad afc es una afp no quiere decir que cotice en un afp..
						//logger.info("fallo al guardar, IdEntPensionReal:" + cotizanteVO.getIdEntPensionReal() + ":AFC:" + form.getCotizante().getIdEntAFCReal() + "::");
						//if (cotizanteVO.getIdEntPensionReal() == Constants.ID_INP || cotizanteVO.getIdEntPensionReal() == Constants.ID_AFP_NINGUNA)
						//	cot.setIdEntPensionReal(cotizanteVO.getIdEntAfcReal());
						form.setRutTrabFormat(cot.getRut());
						EmpresaVO empresa = this.empresaMgr.getEmpresa(this.rutEmpresa);
						form.setRazonSocial(empresa.getRazonSocial());
						form.setRutEmpresaFormat(Utils.formatRut(this.rutEmpresa));
						ConvenioVO convenio = this.convenioMgr.getConvenio(this.rutEmpresa, new Integer(form.getIdConvenio()).intValue());
						setCajaMutual(param, form, convenio);
						form.getCotizante().setApellidos(form.getCotizante().getApellidoPat() + " " + form.getCotizante().getApellidoMat());

						float porcentajeFONASA = entidadesMgr.getPorcentajeFONASA();
						setParams(porcentajeFONASA, form, param);
						llenaListas(this.rutEmpresa, form.getCotizante().isAfpVoluntario(), parametroMgr.getRelacionTipoCausa(), form, entidadesMgr, session);

						tx.commit();
						tx = null;
						//jlandero
						NominaVO nomina = procesoMgr.getNomina(form.getTipoProceso(), new Integer(form.getRutEmpresa()).intValue(), idConvenio);
						request.getSession().setAttribute("datosSis", String.valueOf(isDatosSIS(nomina)));
						request.getSession().setAttribute("idCCAF", String.valueOf(convenio.getIdCcaf()));

						boolean isPrivada = empresa.getPrivada().intValue() == 0 ? true : false;						
						String periodoInformado = (String)param.get("" + Constants.PARAM_PERIODO);
			            String iniVigSIS = (String)param.get("" + Constants.PARAM_PRIMER_PERIODO_VIG_PLENA_SIS);

						request.getSession().setAttribute("exigirValidacion", this.exigirValidacion(isPrivada, periodoInformado, iniVigSIS, nomina, cotizanteVO.getIdEntPensionReal()));
						//TODO Aviso
						if (flgExito) {
							ActionRedirect redirect = new ActionRedirect(mapping.findForward("volver"));
							redirect.addParameter("accion", "inicio");
							redirect.addParameter("subAccion", "trabajadores");
							redirect.addParameter("subSubAccion", "nominaEditar");
							redirect.addParameter("idEmpresa", form.getRutEmpresa());
							redirect.addParameter("idConvenio", form.getIdConvenio());
							redirect.addParameter("tipoNomina", form.getTipoProceso());
							return redirect;
						}
						if (!flgExito && !contErrores) {
							ActionRedirect redirect = new ActionRedirect(mapping.findForward("volver"));
							redirect.addParameter("accion", "inicio");
							redirect.addParameter("subAccion", "trabajadores");
							redirect.addParameter("subSubAccion", "nominaEditar");
							redirect.addParameter("idEmpresa", form.getRutEmpresa());
							redirect.addParameter("idConvenio", form.getIdConvenio());
							redirect.addParameter("tipoNomina", form.getTipoProceso());
							return redirect;
						}
						if (!flgExito && contErrores) {
							return this.buscaRetornoConError(mapping, form.getTipoProceso().charAt(0));
						}

						return buscaRetorno(mapping, form.getCotizante().isAfpVoluntario(), form.getTipoProceso().charAt(0), null, puedeEditar, form);
					}// si se guardo, se aprobo cotizacion
					{
						this.rutTrab = "" + newRut;
//						clillo 3-12-14 por Reliquidación
						//cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, newRut);
						cotizante = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, newRut, periodo);
						form.setRutTrabOrigin(this.rutTrab);
						form.setMostrar("ap");
					}
				} else if (operacion.equals("")) // onChange de select entidad exCaja, puede ser n, p o a
				{
					logger.debug("cambiaExCaja:Empresa:" + this.rutEmpresa + ":Convenio:" + this.idConvenio + ":TipoProceso:" + this.tipoProceso + ":rutTrabajador:" + this.rutTrab + "::");
					request.setAttribute("tabActual", "inpTab");
					operacion = "exCaja";

					//csanchez
					form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTO);
					form.setNumMaxAPVs(Constants.nAPVs_COTIZANTE);
					form.setOperacion(form.getRespaldoOperacion());
					//System.out.println("Fonasa checked=" + form.getFonasaCheck());
				}
			}
			form.setTolerancia(Integer.parseInt(param.get("" + Constants.PARAM_TOLERANCIA_PESO)));
			logger.debug("rutEmpresa:" + this.rutEmpresa + ":IdConvenio:" + this.idConvenio + "::" + this.rutTrab + "::");

			ConvenioVO convenio = this.convenioMgr.getConvenio(this.rutEmpresa, new Integer(form.getIdConvenio()).intValue());
			setCajaMutual(param, form, convenio);
			boolean isMUTUAL = (this.mutual != null ? true : false);
			boolean isCCAF = (this.caja != null ? true : false);
			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(convenio.getIdGrupoConvenio());

			if (!operacion.equals("exCaja") && !this.rutTrab.equals("0"))// no es ni cambio exCaja ni nuevo
			{
				if (cotizante != null)
				{
					form.setRutTrabFormat(Utils.formatRut(cotizante.getIdCotizante()));
					form.setCotizante(FactoryView.cotizanteVOtoView(this.tipoProceso, cotizante));
					//form.setFonasaCheck("true");
					if (form.getCotizante() != null)
					{
						form.setCotizacion(FactoryView.cotizacionVOtoView(this.tipoProceso, true, cotizante, isCCAF, isMUTUAL));
						if (form.getCotizacion() != null && this.tipoProceso == 'R')
						{
							// reforma
							form.getCotizante().setAfpVoluntario(((CotizacionREVO) cotizante.getCotizacion()).isVoluntario());
							if (form.getCotizante().isAfpVoluntario())
							{
								form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTOAF);
								form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTOAF_SIL);
							} else
							{
								form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTO);
								form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTO_SIL);
								form.setNumMaxAPVs(Constants.nAPVs_COTIZANTE);
								form.setApvs(setApvs(cotizanteMgr.getApvs(cotizante.getRutEmpresa(), cotizante.getIdConvenio(), cotizante.getIdCotizante())));							
							}
							form.setMovtosPersonal(setMovtosPersonal(form, param));
						} else if (form.getCotizacion() == null)
						{
							ActionMessages am = new ActionMessages();
							am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.cotizacionNoExiste"));
							this.saveMessages(request, am);
						}
					}
				} else if (cotPendVO != null)
				{
					logger.debug("pendiente encontrado!!" + cotPendVO.getIdCotizPendiente() + "::");
					form.setMostrar("pen");
					ConceptoMgr conceptoMgr = new ConceptoMgr(session);
					List listaConceptos = conceptoMgr.getListaConceptos("" + this.tipoProceso);
					List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(this.tipoProceso), "" + this.tipoProceso);
					Properties mapConceptos = new Properties();
					mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
					FactoryView fv = new FactoryView();
					fv.setGrupoConvenioVO(grupoConvenio);
					fv.setListasConceptos(listaConceptos, listaMapeo, mapConceptos);
					NamesParser parser = NamesParser.getInstance();
					parser.setAutoLearning(true);
					List lista = cotizanteMgr.getApellCompuestos();
					for (Iterator it = lista.iterator(); it.hasNext();)
						parser.add((((ApellidoCompuestoVO) it.next()).getApellido()).trim());
					parser.setOutputDebug(System.out);

					CotizanteVO cotizanteGuardado = null;
					Cotizante cot = fv.cotizPendVOtoView(this.tipoProceso, cotPendVO, parser);
					cot = cotizanteMgr.setIdsReales(this.tipoProceso, grupoConvenio.getIdMapaCod(), cot);
					
					try
					{
						if (this.tipoProceso == 'R')
//							clillo 3-12-14 por Reliquidación
							//cotizanteGuardado = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, new Integer(cot.getIdCotizante().substring(0,
							//		cot.getIdCotizante().length() - 1)).intValue());
							cotizanteGuardado = cotizanteMgr.getCotizante(this.rutEmpresa, this.idConvenio, this.tipoProceso, new Integer(cot.getIdCotizante().substring(0,
									cot.getIdCotizante().length() - 1)).intValue(), periodo);		
						if (cotizanteGuardado != null && cotizanteGuardado.getCotizacion() == null)
							cotizanteGuardado = null;
					} catch (Exception e)
					{
					}
					form.setCotizante(cot);

					form.setCotizacion(fv.cotizPendVOtoView(this.tipoProceso, cot, cotPendVO, isCCAF, isMUTUAL, parser, cotizanteGuardado));
					if (form.getCotizacion() != null && this.tipoProceso == 'R' && !form.getCotizante().isAfpVoluntario())
					{
						List listValidaApvs = conceptoMgr.getLstValidaAPVs();
						MapeosMgr mapeosMgr = new MapeosMgr(session);
						List listaApv = new ArrayList();
						int prevAdicional = 0;
						try {
							prevAdicional = new Integer(form.getCotizacion().getPrevisionAdicional().trim()).intValue();
						} catch (NumberFormatException e){}
						if (form.getCotizacion().getPrevisionAdicional() != null &&
							!form.getCotizacion().getPrevisionAdicional().equals("") &&
							!form.getCotizacion().getPrevisionAdicional().trim().equals("0") &&
							prevAdicional != 0)
							listaApv = addApv(cot.getIdEntPensionReal(), form.getCotizacion().getPrevisionAdicional(), mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class));
						listaApv = fv.apvPendToView(cotPendVO, listValidaApvs, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class), listaApv);

						List apvsOrd =cotizanteMgr.setIdsRealesAPV(this.tipoProceso, grupoConvenio.getIdMapaCod(), cot.getApvs());
						cot.setApvs(listaApv);
						listaApv=apvsOrd;
						if (listaApv.size() > Constants.nAPVs_COTIZANTE)
							for (int i = Constants.nAPVs_COTIZANTE; i < listaApv.size(); i++)
								listaApv.remove(i);
						form.setApvs(listaApv);

						form.setNumMaxAPVs(Constants.nAPVs_COTIZANTE);
						form.setMovtosPersonal(fv.mpPendToView(cotPendVO, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoVO.class, TipoMovimientoPersonalVO.class), entidadesSIL,
								cotizanteGuardado, form.getCotizante().isAfpVoluntario()));
						form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTO);
						form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTO_SIL);
					} else if (form.getCotizacion() != null && this.tipoProceso == 'R' && form.getCotizante().isAfpVoluntario())
					{
						MapeosMgr mapeosMgr = new MapeosMgr(session);
						form.setMovtosPersonal(fv.mpPendToView(cotPendVO, mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoAFVO.class, TipoMvtoPersoAFVO.class), entidadesSIL,
								cotizanteGuardado, form.getCotizante().isAfpVoluntario()));
						form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTOAF);
						form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTOAF_SIL);
					}
					form.setRutTrabFormat(cot.getRut());
					form.setRutTrabOrigin(cot.getIdCotizante());

					HashMap nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausas(this.tipoProceso, cotPendVO));
					form.setErrores((List) nivelErrorTrab.get("errores"));
					form.setAvisos((List) nivelErrorTrab.get("avisos"));
					form.setErroresCD((HashMap) nivelErrorTrab.get("descripcionError"));
				} else
				{
					ActionMessages am = new ActionMessages();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.cotizacionNoExiste"));
					this.saveMessages(request, am);
					form.setCotizacion(null);
					form.setCotizante(null);
				}
			} else
			{
				int idCotizante = new Integer(Utils.getRutSinDV(this.rutTrab)).intValue();
				if (idCotizante != 0)
				{
					form.setRutTrabFormat(Utils.formatRut(idCotizante));
					form.getCotizante().setRut(form.getRutTrabFormat());
					this.rutTrab = "" + idCotizante;
				}
				form.getCotizante().setApellidos(form.getCotizante().getApellidoPat() + " " + form.getCotizante().getApellidoMat());
				
//				clillo 3-12-14 por Reliquidación
				//cotPendVO = cotizanteMgr.getCotizPend(this.rutEmpresa, this.idConvenio, this.tipoProceso, form.getIdCotizPendiente());
				cotPendVO = cotizanteMgr.getCotizPend(this.rutEmpresa, this.idConvenio, this.tipoProceso, form.getIdCotizPendiente(), this.periodo);

				if (cotPendVO != null)
				{
					HashMap nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausas(this.tipoProceso, cotPendVO));
					form.setErrores((List) nivelErrorTrab.get("errores"));
					form.setAvisos((List) nivelErrorTrab.get("avisos"));
					form.setErroresCD((HashMap) nivelErrorTrab.get("descripcionError"));
				}
			}
			if (form.getErroresCD().size() == 0)
			{
//				clillo 3-12-14 por Reliquidación
				//HashMap _nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausasAvisos(this.tipoProceso, this.rutEmpresa,
				//		this.idConvenio, new Integer(Utils.desFormatMonto(this.rutTrab)).intValue()));
				HashMap _nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausasAvisos(this.tipoProceso, this.rutEmpresa,
						this.idConvenio, new Integer(Utils.desFormatMonto(this.rutTrab)).intValue(), this.periodo));
				if (_nivelErrorTrab.size() == 0)
//					clillo 3-12-14 por Reliquidación
					//_nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausasAvisos(this.tipoProceso, this.rutEmpresa,
					//		this.idConvenio, new Integer(Utils.getRutSinDV(this.rutTrab)).intValue()));
					_nivelErrorTrab = cotizanteMgr.getNivelErrorTipoCausa(cotizanteMgr.getCausasAvisos(this.tipoProceso, this.rutEmpresa,
							this.idConvenio, new Integer(Utils.getRutSinDV(this.rutTrab)).intValue(), this.periodo));
				form.setErrores((List) _nivelErrorTrab.get("errores"));
				form.setAvisos((List) _nivelErrorTrab.get("avisos"));
				form.setErroresCD((HashMap) _nivelErrorTrab.get("descripcionError"));
			}
			logger.debug("rutEmpresa:" + this.rutEmpresa + ":IdConvenio:" + this.idConvenio + "::" + this.rutTrab + "::");

			logger.debug("Cotizante:");
			logger.debug(ToStringBuilder.reflectionToString(form.getCotizante(), ToStringStyle.DEFAULT_STYLE));
			logger.debug("Cotizacion:");
			logger.debug(ToStringBuilder.reflectionToString(form.getCotizacion(), ToStringStyle.MULTI_LINE_STYLE));
			EmpresaVO empresa = this.empresaMgr.getEmpresa(this.rutEmpresa);
			form.setRazonSocial(empresa.getRazonSocial());
			form.setRutEmpresaFormat(Utils.formatRut(this.rutEmpresa));
			
			//jlandero recupero la nomina para saber si debo pedir los datos sis
			NominaVO nomina = procesoMgr.getNomina(form.getTipoProceso(), new Integer(form.getRutEmpresa()).intValue(), idConvenio);
			request.getSession().setAttribute("datosSis", String.valueOf(isDatosSIS(nomina)));
			request.getSession().setAttribute("idCCAF", String.valueOf(convenio.getIdCcaf()));
			
			String periodoInformado = (String)param.get("" + Constants.PARAM_PERIODO);
            String iniVigSIS = (String)param.get("" + Constants.PARAM_PRIMER_PERIODO_VIG_PLENA_SIS);
			
            boolean isPrivada = empresa.getPrivada().intValue() == 0 ? true : false;
            
			request.getSession().setAttribute("exigirValidacion", this.exigirValidacion(isPrivada, periodoInformado, iniVigSIS, nomina, form.getCotizante().getIdEntPensionReal()));

			float porcentajeFONASA = entidadesMgr.getPorcentajeFONASA();
			setParams(porcentajeFONASA, form, param);
			if (form.getCotizante() != null)
				llenaListas(this.rutEmpresa, form.getCotizante().isAfpVoluntario(), parametroMgr.getRelacionTipoCausa(), form, entidadesMgr, session);

			logger.debug("saliendo:tx.commit:rutEmpresa:" + form.getRutEmpresa() + ":IdConvenio:" + form.getIdConvenio() + "::" + form.getRutTrabOrigin() + "::");
			tx.commit();
			tx = null;
			
			//TODO SIN Aviso
			if (flgExito) {
				ActionMessages am = new ActionMessages();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cotizacion.guardar"));
				this.saveMessages(request, am);				
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("volver"));
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("subSubAccion", "nominaEditar");
				redirect.addParameter("idEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("tipoNomina", form.getTipoProceso());
				return redirect;
			}
			//Todo GMALLEA 26-07-2013 PARA LIMPIAR EL MP DE TIPO 9
			MovtoPersonal movtoPersonal = null;
			List listMPInico = new ArrayList();
				for(Iterator it = form.getMovtosPersonal().iterator(); it.hasNext();){
					movtoPersonal = (MovtoPersonal)it.next();
					if(movtoPersonal.getIdTipoMovReal() == 9){
						movtoPersonal.setInicio("");
						movtoPersonal.setBloquear(true);
						}
					listMPInico.add(movtoPersonal);
				}
				form.getMovtosPersonal().clear();
				form.setMovtosPersonal(listMPInico);
			
			List listMPFin = new ArrayList();
				for(Iterator it = form.getMovtosPersonal().iterator(); it.hasNext();){
					movtoPersonal = (MovtoPersonal)it.next();
					if(movtoPersonal.getIdTipoMovReal() == 9){
						movtoPersonal.setTermino("");
						movtoPersonal.setBloquear(true);
					}
					listMPFin.add(movtoPersonal);
				}
				form.getMovtosPersonal().clear();
				form.setMovtosPersonal(listMPFin);
		
			try {
				//Se consulta el estado del Comprobante.
				int cmpEstado = this.procesoMgr.getEstadoComprobante(nomina.getIdCodigoBarras());
				int comprobantePrepagado = 0;
				if (cmpEstado == Constants.EST_CMP_PREPAGADO)
					comprobantePrepagado = 1;
				request.setAttribute("comprobantePrepagado", new Integer(comprobantePrepagado));

				//Se consulta el estado de la nómina
				int nomEstado = nomina.getIdEstado();
				int nominaPrepagada = 0;
				if (nomEstado == Constants.EST_NOM_PREPAGADA)
					nominaPrepagada = 1;
				request.setAttribute("nominaPrepagada", new Integer(nominaPrepagada));
			} catch (Exception e) {
				request.setAttribute("comprobantePrepagado", new Integer(0));
				request.setAttribute("nominaPrepagada",      new Integer(0));
			}
			
			//GMALLEA 08/10/2013 Obtiene el total de los cotizantes
			int totalCotizantes = cotizanteMgr.totalCotizante(this.rutEmpresa, this.idConvenio);
			if(totalCotizantes == 0){
				form.setPrimerCotizante(true);
			}else{
				form.setPrimerCotizante(false);
			}
			//Fin GMALLEA
			
			//clillo 21/04/2016 se agrega flag Productos Caja
			request.setAttribute("prodCaja",Boolean.valueOf(grupoConvenio.isProdCaja()));
			return buscaRetorno(mapping, form.getCotizante().isAfpVoluntario(), form.getTipoProceso().charAt(0), request.getParameter("subSubAccion"), puedeEditar ,form);
		} catch (Exception e)
		{
			logger.error("EditarAction::" + e.getMessage(), e);

			MessageList l = new MessageList();
			l.add(new Message("Ha ocurrido un Error", e.getMessage()));
			request.setAttribute("messageList", l);
			return mapping.findForward(PARAM_ERROR);
		} finally
		{
			if (tx != null)
				try
				{
					tx.rollback();
				} catch (Throwable e)
				{
					logger.warn("Problemas en rollback");
				}
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
		{}
		apv.setMontoFormat(montoApv);
		listaApvs.add(apv);
		return listaApvs;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param ssa
	 * @param puedeEditar
	 * @return
	 */
	private ActionForward buscaRetorno(ActionMapping mapping, boolean isVoluntario, char tp, String ssa, boolean puedeEditar ,CotizacionActionForm form)
	{
		if (ssa != null)
		{
			if ( (tp == 'R' && (ssa.equals("trabajadorVer") || !puedeEditar)) )
			{
				if (isVoluntario)
					return mapping.findForward(VERF);
				return mapping.findForward(VERR);
			} else if ( (tp == 'G' && (ssa.equals("trabajadorVer") || !puedeEditar)) )
				return mapping.findForward(VERG);
			else if ( (tp == 'A' && (ssa.equals("trabajadorVer") || !puedeEditar)) )
				return mapping.findForward(VERA);
			else if ( (tp == 'D' && (ssa.equals("trabajadorVer") || !puedeEditar)) )
				return mapping.findForward(VERD);
		}

		switch (tp)
		{
		case 'R':
			if (isVoluntario){
				//GMALLEA 26-04-2012 Si la nomina ya se guardo con aviso se redirecciona a si misma para que actualiza todos los campos
				if(form.getOperacion().equals("Guardar")){
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("editarCotizanteActualizado"));
					redirect.addParameter("rutEmpresa", form.getRutEmpresa());
					redirect.addParameter("idConvenio", form.getIdConvenio());
					redirect.addParameter("idCotizante", String.valueOf(Utils.limpiaRut(true, form.getCotizante().getRut())));// con/sin puntos, con/sin digito, limpia y asigna sin DV form.getRutTrabOrigin());
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("operacion", "mod");
					redirect.addParameter("accion", "inicio");
					redirect.addParameter("subAccion", "trabajadores");
					redirect.addParameter("subSubAccion", "trabajadorEditar");
					return redirect;	
				}
					return mapping.findForward(EDITARF);
				
			}else{
				if(form.getOperacion().equals("Guardar")){
					ActionRedirect redirect = new ActionRedirect(mapping.findForward("editarCotizanteActualizado"));
					redirect.addParameter("rutEmpresa", form.getRutEmpresa());
					redirect.addParameter("idConvenio", form.getIdConvenio());
					redirect.addParameter("idCotizante", String.valueOf(Utils.limpiaRut(true, form.getCotizante().getRut())));// con/sin puntos, con/sin digito, limpia y asigna sin DV form.getRutTrabOrigin());
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("operacion", "mod");
					redirect.addParameter("accion", "inicio");
					redirect.addParameter("subAccion", "trabajadores");
					redirect.addParameter("subSubAccion", "trabajadorEditar");
					return redirect;	
				}
					return mapping.findForward(EDITARR);
			}
		case 'G':
			if(form.getOperacion().equals("Guardar")){
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("editarCotizanteActualizado"));
				redirect.addParameter("rutEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("idCotizante", String.valueOf(Utils.limpiaRut(true, form.getCotizante().getRut())));// con/sin puntos, con/sin digito, limpia y asigna sin DV form.getRutTrabOrigin());
				redirect.addParameter("tipoProceso", form.getTipoProceso());
				redirect.addParameter("operacion", "mod");
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("subSubAccion", "trabajadorEditar");
				return redirect;	
			}
			return mapping.findForward(EDITARG);
		case 'A':
			if(form.getOperacion().equals("Guardar")){
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("editarCotizanteActualizado"));
				redirect.addParameter("rutEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("idCotizante", String.valueOf(Utils.limpiaRut(true, form.getCotizante().getRut())));// con/sin puntos, con/sin digito, limpia y asigna sin DV form.getRutTrabOrigin());
				redirect.addParameter("tipoProceso", form.getTipoProceso());
				redirect.addParameter("operacion", "mod");
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("subSubAccion", "trabajadorEditar");
				return redirect;	
			}
			return mapping.findForward(EDITARA);
		case 'D':
			if(form.getOperacion().equals("Guardar")){
				ActionRedirect redirect = new ActionRedirect(mapping.findForward("editarCotizanteActualizado"));
				redirect.addParameter("rutEmpresa", form.getRutEmpresa());
				redirect.addParameter("idConvenio", form.getIdConvenio());
				redirect.addParameter("idCotizante", String.valueOf(Utils.limpiaRut(true, form.getCotizante().getRut())));// con/sin puntos, con/sin digito, limpia y asigna sin DV form.getRutTrabOrigin());
				redirect.addParameter("tipoProceso", form.getTipoProceso());
				redirect.addParameter("operacion", "mod");
				redirect.addParameter("accion", "inicio");
				redirect.addParameter("subAccion", "trabajadores");
				redirect.addParameter("subSubAccion", "trabajadorEditar");
				return redirect;	
			}
			return mapping.findForward(EDITARD);
		}
		return mapping.findForward(PARAM_ERROR);
	}

	/**
	 * movimientos personal
	 * 
	 * @param form
	 * @param param
	 * @return
	 * @throws NumberFormatException
	 */
	private List setMovtosPersonal(CotizacionActionForm form, ParametrosHash param) throws NumberFormatException
	{
		List listaMovs = form.getCotizacion().getMovimientoPersonal();
		MovtoPersonal[] listaMovsTmp;
		if (!form.getCotizante().isAfpVoluntario())
		{
			listaMovsTmp = new MovtoPersonal[Constants.NUM_MAX_MOVTO];
			form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTO);
			form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTO_SIL);
		} else
		{
			listaMovsTmp = new MovtoPersonal[Constants.NUM_MAX_MOVTOAF];
			form.setNumMaxMovimientos(Constants.NUM_MAX_MOVTOAF);
			form.setTipoMovtoEntidadSIL(Constants.TIPO_MOVTOAF_SIL);
		}
		int count = 0;
		String periodo = param.get("" + Constants.PARAM_PERIODO);
		int diasXMes = new Integer(param.get("" + Constants.PARAM_DIAS_X_MES)).intValue();
		if (listaMovs != null)
		{
			SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
			if (!form.getCotizante().isAfpVoluntario())
			{
				for (Iterator it = listaMovs.iterator(); it.hasNext() && count < Constants.NUM_MAX_MOVTO;)
				{
					MovtoPersonalVO mp = (MovtoPersonalVO) it.next();
					String inicio = (mp.getInicio() != null && fechaEnMes(mp.getInicio(), diasXMes, periodo) ? dateFormat.format(mp.getInicio()) : "");
					String termino = (mp.getTermino() != null && fechaEnMes(mp.getTermino(), diasXMes, periodo) ? dateFormat.format(mp.getTermino()) : "");
					int idEntSil = form.getCotizante().getIdEntSil();
					if (mp.getIdTipoMovReal() != Constants.TIPO_MOVTO_SIL)
						idEntSil = 0;
					listaMovsTmp[count++] = new MovtoPersonal(mp.getIdMovimiento(), idEntSil, mp.getIdTipoMovReal(), inicio, termino);
				}
			} else
			{
				for (Iterator it = listaMovs.iterator(); it.hasNext() && count < Constants.NUM_MAX_MOVTOAF;)
				{
					MvtoPersoAFVO mp = (MvtoPersoAFVO) it.next();
					String inicio = (mp.getInicio() != null && fechaEnMes(mp.getInicio(), diasXMes, periodo) ? dateFormat.format(mp.getInicio()) : "");
					String termino = (mp.getTermino() != null && fechaEnMes(mp.getTermino(), diasXMes, periodo) ? dateFormat.format(mp.getTermino()) : "");
					int idEntSil = form.getCotizante().getIdEntSil();
					if (mp.getIdTipoMovReal() != Constants.TIPO_MOVTOAF_SIL)
						idEntSil = 0;
					listaMovsTmp[count++] = new MovtoPersonal(mp.getIdMovimiento(), idEntSil, mp.getIdTipoMovReal(), inicio, termino);
				}
			}
		}
		int largo = Constants.NUM_MAX_MOVTO;
		if (form.getCotizante().isAfpVoluntario())
			largo = Constants.NUM_MAX_MOVTOAF;
		for (; count < largo; count++)
			listaMovsTmp[count] = new MovtoPersonal((count - 1), -1, -1, "", "");
		List tmp = new ArrayList();
		for (int i = 0; i < listaMovsTmp.length; i++)
			tmp.add(listaMovsTmp[i]);
		return tmp;
	}

	/**
	 * fecha en mes
	 * 
	 * @param fecha
	 * @param diasXMes
	 * @param periodo
	 * @return
	 */
	public static boolean fechaEnMes(Date fecha, int diasXMes, String periodo)
	{
		// Esta regla de validación fue descartada por La Araucana.
		return true;
		
		/*
		try
		{
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMM/dd");
			formatoFecha.setLenient(false); // Debe hacer esto gc.set (GregorianCalendar. ANNO, 2003);
			Date d = formatoFecha.parse(periodo + "/01", new ParsePosition(0));
			// log.info("d:" + d.getTime() + ":" + d + "::");
			if (fecha.before(d))
			{
				logger.debug("antes:" + fecha.toString() + "::");
				return false;
			}
			logger.debug(periodo + "/" + (diasXMes < 10 ? "0" : "") + diasXMes);
			d = formatoFecha.parse(periodo + "/" + (diasXMes < 10 ? "0" : "") + diasXMes, new ParsePosition(0));
			// log.info("d2:" + d.getTime() + ":" + d + "::");
			if (fecha.after(d))
			{
				logger.debug("despues:" + fecha.toString() + "::");
				return false;
			}
		} catch (Exception e)
		{
			logger.error("problemas edicion cotizante", e);
			return false;
		}

		logger.debug("ok!:" + fecha.toString() + "::");
		return true;
		*/
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
	private void setCajaMutual(ParametrosHash param, CotizacionActionForm form, ConvenioVO convenio) throws DaoException
	{
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
	}


	/**
	 * params
	 * 
	 * @param form
	 * @param param
	 * @throws DaoException
	 */
	private void setParams(float porcentajeFONASA, CotizacionActionForm form, ParametrosHash param)
	{
		float ufActual = new Float(param.get("" + Constants.PARAM_UF_ACTUAL)).floatValue();
		float ufAnterior = new Float(param.get("" + Constants.PARAM_UF_ANTERIOR)).floatValue();

		form.setDiasTopeAF(new Integer(param.get("" + Constants.PARAM_DIAS_TOPE_ASIGFAM)).intValue());
		form.setAporteINPFON(porcentajeFONASA);
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
	 * apvs
	 * 
	 * @param apvs
	 * @return
	 */
	private List setApvs(List apvs)
	{
		List apvsTmp = new ArrayList();
		for (Iterator it = Utils.limpiaListaApv(apvs).iterator(); it.hasNext();)
		{
			ApvVO apv = (ApvVO) it.next();
			apv.setMontoFormat(Utils.formatMonto(apv.getAhorro()));
			if (apv.getIdEntidadApv() == Constants.SIN_APV)
				apv.setIdEntidadApv(Constants.APV_INVALIDO);
			apvsTmp.add(apv);
		}

		for (int a = apvsTmp.size(); a < Constants.nAPVs_COTIZANTE; a++)
			apvsTmp.add(new ApvVO(-1));
		return apvsTmp;
	}

	/**
	 * llena listas
	 * 
	 * @param rutEmpresa
	 * @param isAfpVoluntario
	 * @param form
	 * @param entidadesMgr
	 * @param session
	 * @param idUser
	 * @throws DaoException
	 */
	private void llenaListas(int rutEmp, boolean isAfpVoluntario, List relacionTipoCausa, CotizacionActionForm form, EntidadesMgr entidadesMgr, Session session) throws DaoException
	{
		form.setGeneros(entidadesMgr.getGeneros());
		form.setSucursales(this.empresaMgr.getSucursales("" + rutEmp));
		form.setEntidadesSalud(entidadesMgr.getEntsSalud());
		if (this.tipoProceso != 'D'){
			form.setEntidadesPension(entidadesMgr.getEntsPension(true, isAfpVoluntario));
			
			for (int i = 0 ; i < form.getEntidadesPension().size() ; i++){
				EntidadPensionVO ent = (EntidadPensionVO) form.getEntidadesPension().get(i);
				ent.getSis();
			}
			
		}
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
		
		//gmallea se llena el nuevo select regimen
		List regimenAPV = new ArrayList();
		LabelValueBean labelValueBeanA = new LabelValueBean();
		labelValueBeanA.setLabel(""+Constants.REGIMEN_APV_A);
		labelValueBeanA.setValue(""+Constants.REGIMEN_APV_A);
		regimenAPV.add(labelValueBeanA);
		
		LabelValueBean labelValueBeanB = new LabelValueBean();
		labelValueBeanB.setLabel(""+Constants.REGIMEN_APV_B);
		labelValueBeanB.setValue(""+Constants.REGIMEN_APV_B);
		regimenAPV.add(labelValueBeanB);		
		
		form.setRegimenAPV(regimenAPV);
		
		form.setTramosAsigFam(entidadesMgr.getTramosAsigFam());
		form.setTiposProcesos((List) (new ComprobanteMgr(session)).getTiposProceso());
		if (this.tipoProceso == 'R')
		{
			if (isAfpVoluntario)
			{
				form.setTiposMovPersonal(entidadesMgr.getTiposMovPersonalAF());
				form.setTipoMovimientoInicio(tipoMovimientoAf(form.getTiposMovPersonal(), true));
				form.setTipoMovimientoTermino(tipoMovimientoAf(form.getTiposMovPersonal(), false));
			} else
			{
				form.setTiposMovPersonal(entidadesMgr.getTiposMovPersonal());
				form.setTipoMovimiento(tipoMovimiento(form.getTiposMovPersonal()));
			}
			form.setOrdenIdMovimiento(ordenIdMovimiento(form.getTiposMovPersonal(), isAfpVoluntario));

		}
		form.setMensajesErrores(detalleErrores(form.getErroresCD(), relacionTipoCausa));
	}

	/**
	 * tipoMovimiento
	 * 
	 * @param tipos
	 * @return
	 */
	private String tipoMovimiento(List tipos)
	{
		String retorno = "";
		for (Iterator it = tipos.iterator(); it.hasNext();)
		{
			TipoMovimientoPersonalVO tipo = (TipoMovimientoPersonalVO) it.next();
			retorno += tipo.getFechaTerminoObligatoria() + ",";
		}
		if (retorno.length() > 1)
			retorno = retorno.substring(0, retorno.length() - 1);
		return retorno;
	}

	/**
	 * tipoMovimiento
	 * 
	 * @param tipos,
	 *            inicio
	 * @return
	 */
	private String tipoMovimientoAf(List tipos, boolean inicio)
	{
		String retorno = "";
		for (Iterator it = tipos.iterator(); it.hasNext();)
		{
			TipoMvtoPersoAFVO tipo = (TipoMvtoPersoAFVO) it.next();
			if (!inicio)
			{
				retorno += tipo.getFechaTerminoObligatoria() + ",";
			} else
			{
				retorno += tipo.getFechaInicioObligatoria() + ",";
			}
		}
		if (retorno.length() > 1)
			retorno = retorno.substring(0, retorno.length() - 1);
		return retorno;
	}

	private String ordenIdMovimiento(List tipos, boolean voluntario)
	{
		String retorno = "";
		if (!voluntario)
		{
			for (Iterator it = tipos.iterator(); it.hasNext();)
			{
				TipoMovimientoPersonalVO tipo = (TipoMovimientoPersonalVO) it.next();
				retorno += tipo.getId() + ",";
			}
			if (retorno.length() > 1)
				retorno = retorno.substring(0, retorno.length() - 1);
			return retorno;
		}
		for (Iterator it = tipos.iterator(); it.hasNext();)
		{
			TipoMvtoPersoAFVO tipo = (TipoMvtoPersoAFVO) it.next();
			retorno += tipo.getId() + ",";
		}
		if (retorno.length() > 1)
			retorno = retorno.substring(0, retorno.length() - 1);
		return retorno;
	}
	
	private String mensajes(HashMap erroresCD,String datos)
	{
		String mensaje = "";
		int largo = datos.split(",").length;
		String valor[] = datos.split(",");
		Integer valorInt;
		for (int a=0; a<largo; a++) {
			valorInt = new Integer(valor[a]);

			//TODO 16/02/2012
			//El Aviso 904 ('LA EMPRESA PRESENTA CARGA PARA OTRA CAJA'. De uso de Cargas Familiares) debe tener un tratamiento distinto.
			//La glosa de error se debe extraer de otra tabla (CAUSAAVISOCXP, donde X representa el tipo de Nómina),
			//ya que ésta trae el nombre de la caja a la cuál La empresa presenta carga.
			if ( valorInt.intValue() == Constants.ERROR_CARGAFAM_EN_OTRA_CAJA ) {
				String glosaError = this.convenioMgr.errorCargaFamiliarOtraCaja(this.tipoProceso, this.rutEmpresa, this.idConvenio, this.rutTrab);
				String datosErrorCaja[] = glosaError.split("&"); // TextoDelVN&idCaja&,textoDeLaBDD
				String nombreCaja = "ERROR AL ENCONTRAR LA CAJA";
				try {
					EntidadCCAFVO entidad = (EntidadCCAFVO) this.convenioMgr.getCaja(Integer.valueOf(datosErrorCaja[1]).intValue());
					nombreCaja = entidad.getNombre().trim();
				} catch(Exception e) {}
				glosaError = datosErrorCaja[0] + " " + nombreCaja;
				mensaje += erroresCD.get(valorInt) != null ? "<span class=\"mensaje_aviso\">" + glosaError  + "</span>" : "";
			} else {
				mensaje += erroresCD.get(valorInt) != null ? (String) erroresCD.get(valorInt) : "";
			}
		}
		return mensaje;
	}
	
	private HashMap detalleErrores(HashMap erroresCD, List lista)
	{
		HashMap retorno = new HashMap();
		if(lista != null)
		{
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				RelacionTipoCausaVO rel = (RelacionTipoCausaVO)it.next();
				retorno.put(rel.getCampo().trim(), mensajes(erroresCD, rel.getIdTipoCausas().trim()));
			}
		}		
		return retorno;
	}
	
	private boolean isDatosSIS(NominaVO nomina){
		boolean respuesta = true;
		
		if (nomina.getInformaSIS() == 0 && nomina.getNumCotizaciones() != 0 )
			respuesta = false;
		
		return respuesta;
	}
	
	private String exigirValidacion(boolean isPrivada, String periodoInformado, String iniVigSIS, NominaVO nomina, int idEntPensionReal){
		String respuesta = "";

		if (idEntPensionReal == Constants.ID_INP || idEntPensionReal == Constants.ID_AFP_NINGUNA)
			respuesta = "O";//Por ningun motivo
		else if( !isPrivada )
			respuesta = "O";//Obligatorio
		else{
			if( Integer.parseInt(periodoInformado) >= Integer.parseInt(iniVigSIS) ){
				respuesta = "O";//Obligatorio
			}
			else{
				if( nomina.getInformaSIS() == 1 && nomina.getNumCotizaciones() > 0 ){
					respuesta = "O";//Obligatorio
				}
				if( nomina.getInformaSIS() == 0 && nomina.getNumCotizaciones() == 0 ){
					respuesta = "O";//Opcional
				}
				if( nomina.getInformaSIS() == 0 && nomina.getNumCotizaciones() > 0 ){
					respuesta = "O";//Por ningun motivo
				}
			}
		}

		return respuesta;
	}
	
	private ActionForward buscaRetornoConError(ActionMapping mapping, char tp)
	{
		if(tp == 'R'){
			return mapping.findForward(EDITARR);
		}else if(tp == 'G'){
			return mapping.findForward(EDITARG);
		}else if(tp == 'A'){
			return mapping.findForward(EDITARA);
		}else if(tp == 'D'){
			return mapping.findForward(EDITARD);
		}else{
			return mapping.findForward(PARAM_ERROR);
		}
	}
}