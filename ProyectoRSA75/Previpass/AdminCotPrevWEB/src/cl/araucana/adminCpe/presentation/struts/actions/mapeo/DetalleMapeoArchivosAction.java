package cl.araucana.adminCpe.presentation.struts.actions.mapeo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConceptoMgr;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.ProcesoMgr;
import cl.araucana.adminCpe.presentation.struts.forms.mapeo.MapeoArchivosActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.LineaMapeoConcepto;

import com.bh.talon.User;

/*
 * @(#) DetalleMapeoArchivosAction.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.7
 */
public class DetalleMapeoArchivosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(DetalleMapeoArchivosAction.class);

	/**
	 * llena combos grupo convenio
	 * 
	 * @param actForm
	 * @param usuario
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(MapeoArchivosActionForm actForm, ConvenioMgr convenioMgr, ProcesoMgr procesoMgr) throws DaoException
	{
		// Combo grupos de convenios
		List listaGrupos = convenioMgr.getListaGruposConvenio();
		List grupos = new ArrayList();
		GrupoConvenioVO grupo;
		for (Iterator it = listaGrupos.iterator(); it.hasNext();)
		{
			grupo = (GrupoConvenioVO) it.next();
			grupos.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
		}
		Collections.sort(grupos, LabelValueBean.CASE_INSENSITIVE_ORDER);

		// Combo tipos de edicion
		List tiposNomina = new ArrayList();
		TipoNominaVO tipoNomina;
		for (Iterator it = procesoMgr.getTiposProceso().iterator(); it.hasNext();)
		{
			tipoNomina = (TipoNominaVO) it.next();
			tiposNomina.add(new LabelValueBean(tipoNomina.getDescripcion().trim(), tipoNomina.getIdTipoNomina()));
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
		boolean editar = false;
		int idGrupoConvenio = -1;
		String tipoNomina = null;
		String nombreTipoNomina = "";
		boolean bGuardar = false;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			ConvenioMgr convenioMgr = new ConvenioMgr(session);
			ConceptoMgr conceptoMgr = new ConceptoMgr(session);
			ProcesoMgr procesoMgr = new ProcesoMgr(session);

			logger.info("DetalleMapeoArchivosAction.doTask** subSubAccion: " + request.getParameter("subSubAccion"));

			if (request.getParameter("subSubAccion").equals("archivosFicha"))
				editar = false;
			else if (request.getParameter("subSubAccion").equals("archivosEditar"))
				editar = true;

			// Llena los combos de edicion
			llenaCombosEdicion(actForm, convenioMgr, procesoMgr);

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
			tipoNomina = actForm.getOpcTipoNomina();
			idGrupoConvenio = Integer.parseInt(actForm.getOpcGrupoConvenio());

			GrupoConvenioVO grupoConvenio = convenioMgr.getGrupoConvenio(idGrupoConvenio);
			int idMapaNom = 0;
			nombreTipoNomina = procesoMgr.getNombreTipoNomina(tipoNomina);
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
				if (request.getParameter("operacion").equals("Guardar"))
				{
					bGuardar = true;

					conceptoMgr.guardarListaMapeosConcepto(actForm.getConsulta(), idMapaNom, tipoNomina, editar);

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardar", "Mapeo", "para " + nombreTipoNomina));
					this.saveMessages(request.getSession(), am);
					redirect = new ActionRedirect(mapping.findForward("guardarRedirect"));
					redirect.addParameter("accion", "mapeo");
					redirect.addParameter("subAccion", "archivosLista");
					redirect.addParameter("subSubAccion", "archivosFicha");
					redirect.addParameter("tipoNomina", actForm.getOpcTipoNomina());
					redirect.addParameter("grupoConvenio", actForm.getOpcGrupoConvenio());
					tx.commit();
					return redirect;

				} else if (request.getParameter("operacion").equals("Cancelar"))
				{
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("grupoConvenio", actForm.getOpcGrupoConvenio());
					redirect.addParameter("accion", "mapeo");
					redirect.addParameter("subAccion", "archivosLista");
					tx.commit();
					return redirect;
				} else if (request.getParameter("operacion").equals("Editar"))
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
						request.setAttribute("errorLargo", "El campo largo de " + concepto.getNombre().trim() + " no debe ser mayor a " + concepto.getLargoMax());
						cambiar = false;
					}
					if (concepto.getObligatorio() == 1)
					{
						lMapeo.setNombre(mapeo.getConcepto().getNombre().trim() + " *");
					} else
					{
						lMapeo.setNombre(mapeo.getConcepto().getNombre().trim());
					}
				} else
				{
					lMapeo.setNombre(mapeo.getConcepto().getNombre().trim());
				}

				lMapeo.setPosicion(mapeo.getPosicion());
				lMapeo.setTipoNomina(String.valueOf(mapeo.getTipoProceso()));
				lMapeo.setTipoSeparador(mapeo.getTipoSeparador());
				lMapeo.setCaracterSeparador(mapeo.getCaracterSeparador());

				consulta.add(lMapeo);
			}
			actForm.setConsulta(consulta);

			if (cambiar)
			{
				tx.commit();
				cambiar = true;
			}

			if (editar)
				return mapping.findForward("editar");
			return mapping.findForward("detalle");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en DetalleMapeoArchivosAction.doTask()", ex);

			if (tx != null)
				tx.rollback();

			if (bGuardar)
			{
				ae.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.noGuardo", "Mapeo", "para " + nombreTipoNomina));
				this.saveErrors(request.getSession(), ae);
				return mapping.findForward("editar");
			}

			return mapping.findForward("error");
		}
	}
}
