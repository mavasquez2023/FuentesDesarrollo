package cl.araucana.cp.presentation.struts.actions.cotizante;

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
import org.apache.struts.action.ActionRedirect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ConceptoMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.CotizanteMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.presentation.struts.forms.cotizante.ListarActionForm;
import cl.araucana.cp.presentation.utils.FactoryView;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;

/*
 * @(#) ListarAction.java 1.25 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * @author aacuna
 * 
 * @version 1.25
 */
public class ListarAction extends AppAction
{
	public static final String FORWARD            = "cotizantesLista";
	public static final String COTIZACION_EDITAR  = "cotizacionEditar";
	public static final String COTIZACION_FICHA   = "cotizacionFicha";
	public static final String COTIZACION_EDITARR = "cotizacionEditarR";
	public static final String COTIZACION_EDITARF = "cotizacionEditarF";
	public static final String LISTAR_NOMINAS     = "listarNominas";
	
	static Logger logger = Logger.getLogger(ListarAction.class);

	public ListarAction()
	{
		super();
		this.btns.add("filtro");
		this.btns.add("buscar");
		this.btns.add("nuevoTrabajadorR");
		this.btns.add("nuevoTrabajadorF");
		this.btns.add("nuevoTrabajador");
	}

	/**
	 * operacion
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Transaction tx = null;
		
		try
		{
			String operacion = "";
			boolean flgEliminaCotizante = false;
			if (request.getParameter("operacion") != null)
				operacion = request.getParameter("operacion");
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			logger.info("ListarCotizantes:operacion:" + Utils.codificaAcentos(operacion) + "::" + Constants.TXT_BTNS.getProperty("filtro") + "::");
			ListarActionForm form = (ListarActionForm) formulario;
			String rutEmpresa = "";
			CotizanteMgr cotizanteMgr = new CotizanteMgr(session);
			String filtro = null;
			ActionMessages am = new ActionMessages();
			if (!operacion.equals(""))
			{
				rutEmpresa = form.getRutEmpresa();
				if (Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("buscar")))
				{
//					clillo 3-12-14 por Reliquidación
					//CotizanteVO cotizante = cotizanteMgr.getCotizante(Integer.parseInt(form.getRutEmpresa()), Integer.parseInt(form.getConvenio()), form.getTipoProceso().charAt(0), Utils.desFormatRut(form.getRutTrab()));
					CotizanteVO cotizante = cotizanteMgr.getCotizante(Integer.parseInt(form.getRutEmpresa()), Integer.parseInt(form.getConvenio()), form.getTipoProceso().charAt(0), Utils.desFormatRut(form.getRutTrab()), form.getPeriodo());
					List lcc = new ArrayList();
					filtro = "" + Utils.desFormatRut(form.getRutTrab());
					lcc = cotizanteMgr.getListaCotizPend(Integer.parseInt(form.getRutEmpresa()), Integer.parseInt(form.getConvenio()), form.getTipoProceso().charAt(0), filtro);
					// trae cotizante aprobado, con o sin aviso, que no esté también como pendiente. Si está pendiente, muestra mensaje por aproximación
					if (cotizante != null && cotizante.getCotizacion() != null && lcc.size() == 0)
					{
						//28/12/2012
						//Se asumía que SIEMPRE se podía editar el trabajador, por ende también la nómina.
						//Se agrega lógica para preguntar esto.
						boolean puedeEditar = true;
						ParametroMgr parametro = new ParametroMgr(HibernateUtil.getSession());
						UsuarioMgr usuarioMgr = new UsuarioMgr(session);
						int idNivelAcceso = usuarioMgr.getNivelPermiso(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), new Integer(form.getConvenio()).intValue(), new Integer(rutEmpresa).intValue());
						if (parametro.plazoCerrado(Constants.PARAM_FIN_EDICION_NOM) == 1 || idNivelAcceso != Constants.NIVEL_ACC_CONV_SUC_EDITOR)
							puedeEditar = false;
						else {// esta dentro del plazo de edicion, tiene permisos de editor (admin/encargado), => depende estado de nomina
							ProcesoMgr procesoMgr = new ProcesoMgr(session);
							NominaVO nomina = procesoMgr.getNomina(form.getTipoProceso(), Integer.parseInt(form.getRutEmpresa()), Integer.parseInt(form.getConvenio()));
							
							if (nomina != null) {
								if (nomina.getIdEstado() == Constants.EST_NOM_PAGADO			  ||
									nomina.getIdEstado() == Constants.EST_NOM_PAGADO_PARCIALMENTE ||
									nomina.getIdEstado() == Constants.EST_NOM_PRECURSADA		  ||
									nomina.getIdEstado() == Constants.EST_NOM_PREPAGADA)
									puedeEditar = false;
							}
						}
						ActionRedirect redirect;
						if (puedeEditar) {
							redirect = new ActionRedirect(mapping.findForward(COTIZACION_EDITAR));
							redirect.addParameter("operacion", "mod");
							
						} else {
							redirect = new ActionRedirect(mapping.findForward(COTIZACION_FICHA));
							redirect.addParameter("operacion", "ver");
							redirect.addParameter("subSubAccion", "trabajadorVer");
						}
						
						redirect.addParameter("accion", this.accion);
						redirect.addParameter("subAccion", this.subAccion);
						redirect.addParameter("rutEmpresa", form.getRutEmpresa());
						redirect.addParameter("tipoProceso", form.getTipoProceso());
						redirect.addParameter("idConvenio", form.getConvenio());
						redirect.addParameter("idCotizante", "" + Utils.desFormatRut(form.getRutTrab()));
						redirect.addParameter("periodo", "" + form.getPeriodo());
						return redirect;
					}
					//28/12/2012
					//Si el trabajador NO se encuentra, se estaba mostrando "Trabajador fue encontrado, pero en resultados con error." Se modifica para que muestre un mensaje correcto.
					//am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.trabNoEncontradoBusqAprox"));
					if (cotizante == null)
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.trabNoEncontrado"));
					else
						am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.trabNoEncontradoBusqAprox"));
					this.saveMessages(request, am);
				} else if (operacion.equals(Constants.TXT_BTNS.getProperty("nuevoTrabajador")))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward(COTIZACION_EDITAR));
					redirect.addParameter("operacion", "new");
					redirect.addParameter("accion", this.accion);
					redirect.addParameter("subAccion", this.subAccion);
					redirect.addParameter("rutEmpresa", form.getRutEmpresa());
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("idConvenio", form.getConvenio());
					redirect.addParameter("rutTrabajador", "0");
					return redirect;
				} else if (operacion.equals("mod"))// editar
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward(COTIZACION_EDITAR));
					redirect.addParameter("operacion", "mod");
					redirect.addParameter("accion", this.accion);
					redirect.addParameter("subAccion", this.subAccion);
					redirect.addParameter("rutEmpresa", form.getRutEmpresa());
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("idConvenio", form.getConvenio());
					redirect.addParameter("rutTrabajador", form.getRutTrab());
					return redirect;
				} else if (operacion.equals("ver"))// ver ficha
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward(COTIZACION_FICHA));
					redirect.addParameter("operacion", "ver");
					redirect.addParameter("accion", this.accion);
					redirect.addParameter("subAccion", this.subAccion);
					redirect.addParameter("rutEmpresa", form.getRutEmpresa());
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("idConvenio", form.getConvenio());
					redirect.addParameter("rutTrabajador", form.getRutTrab());
					return redirect;
				} else if (operacion.equals("del"))// eliminar
				{
					boolean pendiente = false;
					if (request.getParameter("idCotizPend") != null && !request.getParameter("idCotizPend").equals(""))
						pendiente = true;
					int idTrabajador = new Integer(pendiente ? request.getParameter("idCotizPend") : request.getParameter("idCotizante")).intValue();
					if (request.getParameter("idConvenio") != null && form.getRutEmpresa() != null)
					{
//						clillo 3-12-14 por Reliquidación
						//CotizanteVO cotizante = cotizanteMgr.getCotizante(form.getRutEmpresa(), request.getParameter("idConvenio"), String.valueOf(idTrabajador));
						CotizanteVO cotizante = cotizanteMgr.getCotizante(form.getRutEmpresa(), request.getParameter("idConvenio"), String.valueOf(idTrabajador), form.getPeriodo());
						if (cotizante != null || pendiente)
						{
							int result = 0;
							String identificador = "";
							if (!pendiente)
							{
								identificador = "(" + Utils.formatRut(cotizante.getIdCotizante()) + "-" + Utils.generaDV(cotizante.getIdCotizante()) + "/" + cotizante.getNombre().trim() + " "
										+ cotizante.getApellidoPat().trim() + " " + cotizante.getApellidoMat().trim() + ")";
								logger.info("getConvenio:" + form.getConvenio() + "::");
							}
							try
							{
//								clillo 3-12-14 por Reliquidación
								//result = cotizanteMgr.eliminaCotizante(pendiente, new Integer(form.getRutEmpresa()).intValue(), new Integer(request.getParameter("idConvenio")).intValue(), form
								//		.getTipoProceso().charAt(0), idTrabajador, user.getLogin());
								result = cotizanteMgr.eliminaCotizante(pendiente, new Integer(form.getRutEmpresa()).intValue(), new Integer(request.getParameter("idConvenio")).intValue(), form
										.getTipoProceso().charAt(0), idTrabajador, user.getLogin(), form.getPeriodo());
								tx.commit();
								tx = null;
								flgEliminaCotizante = true;
							} catch (Exception e)
							{
								try
								{
									tx.rollback();
								} catch (Exception ex)
								{
									logger.warn("Problema en rollback", ex);
								}
								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage()));
								this.saveMessages(request, am);
								tx = null;
								
								return mapping.findForward("error");
							}
							if (result != 1)
							{
								ActionErrors ae = new ActionErrors();
								ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.eliminaTrabajador", identificador));
								this.saveErrors(request, ae);
							} else
							{
								am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.borrar", "trabajador", identificador));
								this.saveMessages(request, am);
							}
							form.setConvenio(request.getParameter("idConvenio"));
						} else
						{
							ActionErrors ae = new ActionErrors();
							ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.eliminaTrabajador", ""));
							this.saveErrors(request, ae);
						}
					}
				} else if (operacion.equals(Constants.TXT_BTNS.getProperty("nuevoTrabajadorR")))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward(COTIZACION_EDITARR));
					redirect.addParameter("operacion", "newR");
					redirect.addParameter("accion", this.accion);
					redirect.addParameter("subAccion", this.subAccion);
					redirect.addParameter("subSubAccion", this.subSubAccion);
					redirect.addParameter("rutEmpresa", form.getRutEmpresa());
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("idConvenio", form.getConvenio());
					redirect.addParameter("rutTrabajador", "0");
					return redirect;
				} else if (operacion.equals(Constants.TXT_BTNS.getProperty("nuevoTrabajadorF")))
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward(COTIZACION_EDITARF));
					redirect.addParameter("operacion", "newF");
					redirect.addParameter("accion", this.accion);
					redirect.addParameter("subAccion", this.subAccion);
					redirect.addParameter("subSubAccion", this.subSubAccion);
					redirect.addParameter("rutEmpresa", form.getRutEmpresa());
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("idConvenio", form.getConvenio());
					redirect.addParameter("rutTrabajador", "0");
					return redirect;
				}
			} else
			{
				rutEmpresa = (request.getParameter("idEmpresa") != null && !request.getParameter("idEmpresa").equals("") ? request.getParameter("idEmpresa") : form.getRutEmpresa());
				if (request.getParameter("idConvenio") != null && !request.getParameter("idConvenio").equals(""))
					form.setConvenio(request.getParameter("idConvenio"));
				if (request.getParameter("tipoNomina") != null && !request.getParameter("tipoNomina").equals(""))
					form.setTipoProceso(request.getParameter("tipoNomina"));
			}

			//logger.info(":Empresa:" + form.getRutEmpresa() + ":Convenio:" + form.getConvenio() + ":TipoProceso:" + form.getTipoProceso() + "::");
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			ConceptoMgr conceptoMgr = new ConceptoMgr(session);

			form.setTiposProcesos((List) comprobanteMgr.getTiposProceso());

			logger.info(":Empresa:" + form.getRutEmpresa() + ":Convenio:" + form.getConvenio() + ":TipoProceso:" + form.getTipoProceso() + "::");
			int pagina = request.getParameter("paginaNumero") != null ? (Integer.parseInt(request.getParameter("paginaNumero"))) : 1;
			int primerReg = Constants.NUM_REG_PAG_CL * (pagina - 1);
			HashMap dataTrabajadores = cotizanteMgr.getTrabPaginados(pagina, primerReg, new Integer(rutEmpresa).intValue(), new Integer(form.getConvenio()).intValue(), form.getTipoProceso().charAt(0), filtro);
			List listaTrabajadores = (List) dataTrabajadores.get("aprobados");
			List listaTrabajadoresAvisos = (List) dataTrabajadores.get("avisos");
			List listaTrabPend = (List) dataTrabajadores.get("pendientes");
			int nPaginas = ((Integer)dataTrabajadores.get("nPaginas")).intValue();

// NOMINA_EN_LINEA
//			if (listaTrabajadores.size() == 0 && listaTrabPend.size() == 0 && listaTrabajadoresAvisos.size() == 0)
//			{
//				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinTrabajadoresComp"));
//				this.saveMessages(request, am);
//				form.setTrabajadores(null);
//
//				HashMap combos = null;
//				if (rutEmpresa != null && !rutEmpresa.equals("") && form.getConvenio() != null && !form.getConvenio().equals(""))
//					combos = empresaMgr.getCombosPermisos(true, ((PersonaVO) user.getUserReference()).getIdPersona().intValue(), new Integer(rutEmpresa).intValue(), new Integer(form.getConvenio()).intValue());
//				if (combos == null)
//				{
//					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
//					this.saveMessages(request, am);
//					return mapping.findForward("error");
//				}
//
//				form.setEmpresas((List) combos.get("empresas"));
//				form.setObjEmpresas((List) combos.get("objEmpresas"));
//				EmpresaVO empresa = (EmpresaVO) combos.get("empresa");
//				form.setRazonSocial(empresa.getRazonSocial());
//				form.setRutEmpresa(rutEmpresa);
//				form.setRutEmpresaFormat(Utils.formatRut(empresa.getIdEmpresa()));
//				return mapping.findForward(FORWARD);
//			}

			form.setNumTrabTotal(listaTrabajadores.size() + listaTrabPend.size() + listaTrabajadoresAvisos.size());
			if (form.getNumTrabTotal() == 1)
			{
//				clillo 3-12-14 por Reliquidación
				//CotizanteVO cotizante = cotizanteMgr.getCotizante(Integer.parseInt(rutEmpresa), Integer.parseInt(form.getConvenio()), form.getTipoProceso().charAt(0), Utils.desFormatRut(form.getRutTrab()));
				CotizanteVO cotizante = cotizanteMgr.getCotizante(Integer.parseInt(rutEmpresa), Integer.parseInt(form.getConvenio()), form.getTipoProceso().charAt(0), Utils.desFormatRut(form.getRutTrab()), form.getPeriodo());
				if (cotizante != null && cotizante.getCotizacion() != null)
				{
					ActionRedirect redirect = new ActionRedirect(mapping.findForward(COTIZACION_EDITAR));
					redirect.addParameter("operacion", "mod");
					redirect.addParameter("accion", this.accion);
					redirect.addParameter("subAccion", this.subAccion);
					redirect.addParameter("rutEmpresa", rutEmpresa);
					redirect.addParameter("tipoProceso", form.getTipoProceso());
					redirect.addParameter("idConvenio", form.getConvenio());
					redirect.addParameter("idCotizante", "" + Utils.desFormatRut(form.getRutTrab()));
					return redirect;
				}
			} else if (form.getNumTrabTotal() == 0 && flgEliminaCotizante) {
				//Cuando se trata del último trabajador eliminado se regresa a la página de Gestión de Comprobantes
				return mapping.findForward(LISTAR_NOMINAS);
			}

			HashMap combos = null;
			if (rutEmpresa != null && !rutEmpresa.equals("") && form.getConvenio() != null && !form.getConvenio().equals(""))
				combos = empresaMgr.getCombosPermisos(false, ((PersonaVO) user.getUserReference()).getIdPersona().intValue(), new Integer(rutEmpresa).intValue(), new Integer(form.getConvenio()).intValue());
			if (combos == null)
			{
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
				this.saveMessages(request, am);
				
				return mapping.findForward("error");
			}

			form.setEmpresas((List) combos.get("empresas"));
			form.setObjEmpresas((List) combos.get("objEmpresas"));
			EmpresaVO empresa = (EmpresaVO) combos.get("empresa");
			form.setRazonSocial(empresa.getRazonSocial());
			form.setRutEmpresa(rutEmpresa);
			form.setRutEmpresaFormat(Utils.formatRut(empresa.getIdEmpresa()));
			HashMap sucursales = empresaMgr.getSucursalesHash(rutEmpresa);
			char tipoProceso = form.getTipoProceso().charAt(0);
			//csanchez
			//form.setTrabajadores(FactoryView.cotizanteVOtoView(tipoProceso, listaTrabajadores, sucursales));
			//form.setTrabAvisos(FactoryView.cotizanteAvisoVOtoView(tipoProceso, listaTrabajadoresAvisos, sucursales));
			form.setTrabajadores (FactoryView.cotizanteVOtoView      (tipoProceso, listaTrabajadores,       sucursales, session));
			form.setTrabAvisos   (FactoryView.cotizanteAvisoVOtoView (tipoProceso, listaTrabajadoresAvisos, sucursales, session));
			if (listaTrabPend.size() > 0)
			{
				ConvenioVO convenio = (new ConvenioMgr(session)).getConvenio(new Integer(rutEmpresa).intValue(), new Integer(form.getConvenio()).intValue());
				GrupoConvenioVO grupoConvenio = (new ConvenioMgr(session)).getGrupoConvenio(convenio.getIdGrupoConvenio());
				List listaConceptos = conceptoMgr.getListaConceptos("" + tipoProceso);
				List listaMapeo = conceptoMgr.getListaMapeosConcepto(grupoConvenio.getIdMapaNom(tipoProceso), "" + tipoProceso);
				Properties mapConceptos = new Properties();
				mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
				FactoryView fv = new FactoryView();
				fv.setGrupoConvenioVO(grupoConvenio);
				fv.setListasConceptos(listaConceptos, listaMapeo, mapConceptos);
				//csanchez
				listaTrabPend = fv.pendientestoView(tipoProceso, listaTrabPend, sucursales);
				//listaTrabPend = fv.pendientestoView(tipoProceso, listaTrabPend, sucursales, session);
				Collections.sort(listaTrabPend);
				//pagina resultados pendientes
				int cant = Math.min(listaTrabPend.size(), primerReg + Constants.NUM_REG_PAG_CL) - primerReg;
				form.setTrabPendientes(listaTrabPend.subList(primerReg, primerReg + cant));
				
				//TODO 16-05-2012 GMALLEA Se valida si la operacion es "" se limpia el mensaje y se crea el nuevo.
				if (!operacion.equals("") && !operacion.equals("del")){
					am.clear();
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.trabNoEncontradoBusqAprox"));
				}
			} else
				form.setTrabPendientes(new ArrayList());

			this.saveMessages(request, am);
			// hace calculos para determinar si es posible o no editar la nomina:
			// - fecha actual vs fecha limite edicion nomina (tabla parametro)
			// - permisos del usuario logeado (admin empresa, encargado lector/editor)
			// - estado de la nomina
			ParametroMgr parametro = new ParametroMgr(HibernateUtil.getSession());
			List listParam = new ArrayList();
			listParam.add(new Integer(Constants.PARAM_PERIODO));
			ParametrosHash param = parametro.getParametrosHash(listParam);
			int periodo=0;
			if(tipoProceso=='A'){
				periodo= Integer.parseInt(param.get("" + Constants.PARAM_PERIODO));
			}
			form.setPeriodo(periodo);

			UsuarioMgr usuarioMgr = new UsuarioMgr(session);
			int idNivelAcceso = usuarioMgr.getNivelPermiso(((PersonaVO) user.getUserReference()).getIdPersona().intValue(), new Integer(form.getConvenio()).intValue(), new Integer(rutEmpresa)
					.intValue());
			if (parametro.plazoCerrado(Constants.PARAM_FIN_EDICION_NOM) == 1 || idNivelAcceso != Constants.NIVEL_ACC_CONV_SUC_EDITOR)
				form.setEditNomina(0);
			else
			{// esta dentro del plazo de edicion, tiene permisos de editor (admin/encargado), => depende estado de nomina
				ProcesoMgr procesoMgr = new ProcesoMgr(session);
				NominaVO nomina = procesoMgr.getNomina(form.getTipoProceso(), new Integer(rutEmpresa).intValue(), new Integer(form.getConvenio()).intValue());
				
				if (nomina != null) {
					if (nomina.getIdEstado() == Constants.EST_NOM_PAGADO ||
						nomina.getIdEstado() == Constants.EST_NOM_PAGADO_PARCIALMENTE ||
						nomina.getIdEstado() == Constants.EST_NOM_PRECURSADA ||
						nomina.getIdEstado() == Constants.EST_NOM_PREPAGADA)
						form.setEditNomina(0);
					else
						form.setEditNomina(1);
				} else {
					return mapping.findForward(LISTAR_NOMINAS);
				}
			}

			form.setNumeroFilas(Utils.generaPaginacion(pagina, nPaginas, Constants.NUM_PAG_CL));// enlaces listos, llegar y mostrar. llama a funcion JS
			request.setAttribute("cambioParam", "accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar&idConvenio=" + form.getConvenio() + "&idEmpresa=" + form.getRutEmpresa()
					+ "&tipoNomina=" + form.getTipoProceso());

			return mapping.findForward(FORWARD);
		} catch (Exception e)
		{
			logger.error("ListarAction::" + e.getMessage(), e);

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
				} catch (Throwable ex)
				{
					logger.warn("Problemas en rollback", ex);
				}
		}
	}
}
