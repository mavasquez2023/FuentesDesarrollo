package cl.araucana.cp.presentation.struts.actions.mapeo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
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

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.LineaMapeoConcepto;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConceptoMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;
import cl.araucana.cp.presentation.struts.forms.mapeo.MapeoArchivosActionForm;

import com.bh.talon.User;

/*
 * @(#) DetalleMapeoArchivosAction.java 1.19 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author aacuna
 * 
 * @version 1.19
 */
public class DetalleMapeoArchivosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleMapeoArchivosAction.class);
	private ConvenioMgr convenioMgr;
	private ProcesoMgr procesoMgr;
	private String nombreTipoNomina = "";
	private static final int EDITAR = 0;
	private static final int FICHA = 1;
	private static final int IMPRIMIR = 2;

	public DetalleMapeoArchivosAction()
	{
		super();

		this.btns.add("imprimir");
		this.btns.add("guardar");
		this.btns.add("cancelar");
		this.btns.add("editar");
	}

	/**
	 * llena combos edicion
	 * 
	 * @param actForm
	 * @param usuario
	 * @param editar
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(MapeoArchivosActionForm actForm, User usuario, boolean editar) throws DaoException
	{
		UsuarioCP usuarioCP = (UsuarioCP) usuario;

		List convenios;
		Set gruposConvenio;
		if (!editar)
		{
			// Obtiene los grupos de convenios relacionados con convenios de empresas que el usuario administra
			// o grupos de convenios sobre los que el usuario tiene permiso de lectura o escritura.
			convenios = this.convenioMgr.getConveniosEmpresasIn(usuarioCP.getUnionEmpresasLectura());
			gruposConvenio = new HashSet(CollectionUtils.collect(convenios, new Transformer()
			{
				public Object transform(Object input)
				{
					return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
				}
			}));

		} else
		{
			// Grupos de convenios relacionados con convenios de empresas en las que el usuario es adminstrador
			convenios = this.convenioMgr.getConveniosEmpresasIn(usuarioCP.getEmpresasAdmin());
			Set gConvAdmin = new HashSet(CollectionUtils.collect(convenios, new Transformer()
			{
				public Object transform(Object input)
				{
					return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
				}
			}));
			// Grupos de convenios relacionados con convenios en los que el usuario es encargado de escritura
			convenios = this.convenioMgr.getConveniosIn(usuarioCP.getConveniosEditorRetVO());
			Set gConvEnc = new HashSet(CollectionUtils.collect(convenios, new Transformer()
			{
				public Object transform(Object input)
				{
					return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
				}
			}));
			gruposConvenio = new HashSet(CollectionUtils.union(gConvAdmin, gConvEnc));
		}

		// Combo grupos de convenios
		// List listaGrupos = new ArrayList(usuarioMgr.getPersona(usuario.getLogin()).getGruposConvenio());
		List listaGrupos = this.convenioMgr.getGruposConveniosIn(gruposConvenio);
		List grupos = new ArrayList();
		GrupoConvenioVO grupo;
		for (Iterator it = listaGrupos.iterator(); it.hasNext();)
		{
			grupo = (GrupoConvenioVO) it.next();
			grupos.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
		}
		Collections.sort(grupos, LabelValueBean.CASE_INSENSITIVE_ORDER);

		// Combo tipos de edicion
		List listaTipos = new ArrayList(this.procesoMgr.getTiposProceso());
		List tiposNomina = new ArrayList();
		TipoNominaVO tipoNomina;
		for (Iterator it = listaTipos.iterator(); it.hasNext();)
		{
			tipoNomina = (TipoNominaVO) it.next();
			if(actForm.getProdCaja().booleanValue()){
				if(tipoNomina.getIdTipoNomina().equals("R")){
					tiposNomina.add(new LabelValueBean(tipoNomina.getDescripcion().trim(), tipoNomina.getIdTipoNomina()));
					this.nombreTipoNomina = tipoNomina.getDescripcion();
				}
			}else{
				tiposNomina.add(new LabelValueBean(tipoNomina.getDescripcion().trim(), tipoNomina.getIdTipoNomina()));
				if (actForm.getOpcTipoNomina().equals(tipoNomina.getIdTipoNomina()))
					this.nombreTipoNomina = tipoNomina.getDescripcion();
			}
		}

		actForm.setGruposConvenio(grupos);
		actForm.setTiposNomina(tiposNomina);
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>mapeo</dd>
	 * <dt>subAccion</dt>
	 * <dd>archivosLista</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>Si es archivosEditar, presenta la edicion de mapeo de archivos. Si es archivosFicha, presenta el detalle del mapeo de archivos.</dd>
	 * <dt>grupoConvenio</dt>
	 * <dd>El grupo de convenios al que pertenece el mapeo a editar/mostrar.</dd>
	 * <dt>tipoNomina</dt>
	 * <dd>El tipo de nomina al que pertenece el mapeo a editar/mostrar.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Editar, se redirecciona a la edicion del convenio. Si es Guardar, se guarda el contenido del formulario en la BD. Si es Cancelar, se redirecciona a la lista de mapeos de archivo. Si
	 * es Editar, se redirecciona a la edicion de mapeos de archivo.</dd>
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
		MapeoArchivosActionForm actForm = (MapeoArchivosActionForm) form;

		ActionErrors ae = new ActionErrors();
		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;
		int idGrupoConvenio = -1;
		String tipoNomina = null;
		boolean bGuardar = false;
		boolean editar = false;
		int tipoOperacion = 0;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			this.convenioMgr = new ConvenioMgr(session);
			this.procesoMgr = new ProcesoMgr(session);
			ConceptoMgr conceptoMgr = new ConceptoMgr(session);

			UsuarioCP usuarioCP = (UsuarioCP) usuario;

			logger.info("DetalleMapeoArchivosAction.doTask** subSubAccion: " + request.getParameter("subSubAccion"));

			logger.debug("Imprimrir: " + request.getParameter("imprimir"));
			if (request.getParameter("imprimir") == null)
			{
				if (request.getParameter("subSubAccion").equals("archivosFicha"))
					tipoOperacion = FICHA;
				else if (request.getParameter("subSubAccion").equals("archivosEditar"))
				{
					tipoOperacion = EDITAR;
					editar = true;
				}
			} else
			{
				tipoOperacion = IMPRIMIR;
			}

			if ((actForm.getOpcGrupoConvenio() == null) || (actForm.getOpcTipoNomina() == null))
			{
				if ((request.getParameter("grupoConvenio") != null) && (request.getParameter("tipoNomina") != null))
				{
					actForm.setOpcGrupoConvenio(request.getParameter("grupoConvenio"));
					actForm.setOpcTipoNomina(request.getParameter("tipoNomina"));
				} else
				{
					actForm.setOpcGrupoConvenio(((LabelValueBean) actForm.getGruposConvenio().get(0)).getValue());
					actForm.setOpcTipoNomina(((LabelValueBean) actForm.getTiposNomina().get(0)).getValue());
				}
			}
			
			idGrupoConvenio = Integer.parseInt(actForm.getOpcGrupoConvenio());
			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(idGrupoConvenio);
			
			if(grupoConvenio.isProdCaja()){
				actForm.setProdCaja(Boolean.TRUE);
				tipoNomina="R";
			}else{
				actForm.setProdCaja(Boolean.FALSE);
				tipoNomina = actForm.getOpcTipoNomina();
			}
			
			// Llena los combos de edicion
			llenaCombosEdicion(actForm, usuario, tipoOperacion == EDITAR);
			
			//TODO: csanchez
			if (grupoConvenio.isPrevired())
			{
				actForm.setTienePrevired(Boolean.TRUE);
			} else
			{
				actForm.setTienePrevired(Boolean.FALSE);
			}
			
			int idMapaNom = grupoConvenio.getIdMapaNom(tipoNomina.charAt(0));
			this.nombreTipoNomina = this.procesoMgr.getNombreTipoNomina(tipoNomina);
			if (tipoNomina.equals("R"))
				idMapaNom = grupoConvenio.getIdMapaNomRem();
			else if (tipoNomina.equals("A"))
				idMapaNom = grupoConvenio.getIdMapaNomRel();
			else if (tipoNomina.equals("G"))
				idMapaNom = grupoConvenio.getIdMapaNomGra();
			else if (tipoNomina.equals("D"))
				idMapaNom = grupoConvenio.getIdMapaNomDep();

			if (request.getParameter("operacion") != null)
			{
				// Llamado desde adentro
				ActionRedirect redirect = new ActionRedirect();
				if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("guardar")))
				{
					bGuardar = true;

					conceptoMgr.guardarListaMapeosConcepto(actForm.getConsulta(), idMapaNom, tipoNomina);
					conceptoMgr.borraCRCMasivo(tipoNomina.charAt(0), idGrupoConvenio);

					redirect = new ActionRedirect(mapping.findForward("guardarRedirect"));
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "archivosFicha");
					redirect.addParameter("tipoNomina", actForm.getOpcTipoNomina());
					redirect.addParameter("grupoConvenio", actForm.getOpcGrupoConvenio());
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Mapeo", "para " + this.nombreTipoNomina));
					this.saveMessages(request.getSession(), am);
					tx.commit();
					return redirect;

				} else if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("cancelar")))
				{
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("grupoConvenio", actForm.getOpcGrupoConvenio());
					redirect.addParameter("accion", "mapeo");
					redirect.addParameter("subAccion", "archivosLista");

					tx.commit();

					return redirect;
				} else if (request.getParameter("operacion").equals(Constants.TXT_BTNS.getProperty("editar")))
				{
					redirect = new ActionRedirect(mapping.findForward("refresh"));

					redirect.addParameter("grupoConvenio", actForm.getOpcGrupoConvenio());
					redirect.addParameter("tipoNomina", actForm.getOpcTipoNomina());
					redirect.addParameter("accion", request.getParameter("accion"));
					redirect.addParameter("subAccion", request.getParameter("subAccion"));
					redirect.addParameter("subSubAccion", "archivosEditar");

					tx.commit();

					return redirect;
				}
			} else
			{
				// Llamado desde afuera
			}

			// Hace la consulta
			List listaMapeos = conceptoMgr.getListaMapeosConcepto(idMapaNom, tipoNomina);
			boolean cambiar = true;
			MapeoConceptoVO mapeo;
			List consulta = new ArrayList();
			LineaMapeoConcepto lMapeo;
			ConceptoVO concepto = null;

			//Se consulta sólo al primer elemento de la lista de MapeosConcepto por el tipo de separador, ya que es el mismo para toda la lista.
			request.setAttribute("tipoSeparador",     String.valueOf(((MapeoConceptoVO) listaMapeos.get(0)).getTipoSeparador()));
			request.setAttribute("caracterSeparador", ((MapeoConceptoVO) listaMapeos.get(0)).getCaracterSeparador());
			request.setAttribute("tienePrevired",     String.valueOf(actForm.getTienePrevired().booleanValue()));
			request.setAttribute("prodCaja", actForm.getProdCaja());
			
			for (Iterator it = listaMapeos.iterator(); it.hasNext();)
			{
				mapeo = (MapeoConceptoVO) it.next();
				concepto = mapeo.getConcepto();
				lMapeo = new LineaMapeoConcepto();
				lMapeo.setIdConcepto(mapeo.getIdConcepto());
				lMapeo.setIdMapaNom(mapeo.getIdMapa());
				lMapeo.setLargo(mapeo.getLargo());
				if (editar)
				{
					if (mapeo.getLargo() > concepto.getLargoMax())
					{
						if( !(grupoConvenio.isPrevired() && concepto.getId() == 4) ) { //4 = Apellidos del Cotizante
							request.setAttribute("errorLargo", "El campo largo de " + concepto.getNombre().trim() + " no debe ser mayor a " + concepto.getLargoMax());
							cambiar = false;
						}
					}
					if (concepto.getObligatorio() == 1)
						lMapeo.setNombre(mapeo.getConcepto().getNombre().trim() + " *");
					else
						lMapeo.setNombre(mapeo.getConcepto().getNombre().trim());
				} else
					lMapeo.setNombre(mapeo.getConcepto().getNombre().trim());

				lMapeo.setPosicion(mapeo.getPosicion());
				lMapeo.setTipoNomina(String.valueOf(mapeo.getTipoProceso()));
				lMapeo.setTipoSeparador(mapeo.getTipoSeparador());
				lMapeo.setCaracterSeparador(mapeo.getCaracterSeparador());

				consulta.add(lMapeo);
			}
			actForm.setConsulta(consulta);

			if (tipoOperacion != EDITAR)
			{
				// Grupos de convenios relacionados con convenios de empresas en las que el usuario
				// es adminstrador
				List convenios = this.convenioMgr.getConveniosEmpresasIn(usuarioCP.getEmpresasAdmin());
				Set gConvAdmin = new HashSet(CollectionUtils.collect(convenios, new Transformer()
				{
					public Object transform(Object input)
					{
						return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
					}
				}));
				// Grupos de convenios relacionados con convenios en los que el usuario es encargado
				// de escritura
				convenios = this.convenioMgr.getConveniosIn(usuarioCP.getConveniosEditorRetVO());
				Set gConvEnc = new HashSet(CollectionUtils.collect(convenios, new Transformer()
				{
					public Object transform(Object input)
					{
						return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
					}
				}));
				Set gruposConvenioEditables = new HashSet(CollectionUtils.union(gConvAdmin, gConvEnc));

				if (gruposConvenioEditables.contains(new Integer(grupoConvenio.getIdGrupoConvenio())))
					actForm.setPuedeEditar(Boolean.TRUE);
				else
					actForm.setPuedeEditar(Boolean.FALSE);
			}
			if (cambiar)
			{
				tx.commit();
				cambiar = true;
			}

			if (tipoOperacion == EDITAR)
				return mapping.findForward("editar");
			else if (tipoOperacion == FICHA)
				return mapping.findForward("detalle");
			else
			{
				actForm.setNombreGrupoConvenio(this.convenioMgr.getGrupoConvenio(idGrupoConvenio).getNombre().trim());
				Collection tiposProceso = this.procesoMgr.getTiposProceso();
				TipoNominaVO tNom = null;
				for (Iterator it = tiposProceso.iterator(); it.hasNext();)
				{
					tNom = (TipoNominaVO) it.next();
					if (tNom.getIdTipoNomina().equals(tipoNomina))
						break;
				}
				if (tNom != null)
					actForm.setNombreTipoNomina(tNom.getDescripcion().trim());
				return mapping.findForward("imprimir");
			}
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en DetalleMapeoArchivosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();

			if (bGuardar)
			{
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noGuardo", "Mapeo", "para " + this.nombreTipoNomina));
				this.saveErrors(request.getSession(), ae);
				return mapping.findForward("editar");
			}

			return mapping.findForward("error");
		}
	}
}
