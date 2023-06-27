package cl.araucana.adminCpe.presentation.struts.actions.mapeo;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.MapeosMgr;
import cl.araucana.adminCpe.presentation.struts.forms.mapeo.EdicionCodigosActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAsFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoGeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.LineaCodigoFicha;

import com.bh.talon.User;

/*
 * @(#) EdicionCodigosAction.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.8
 */
public class EdicionCodigosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(EdicionCodigosAction.class);

	private MapeosMgr mapeosMgr;
	private ConvenioMgr convenioMgr;

	/**
	 * llena combos grupo convenio
	 * 
	 * @param actForm
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(EdicionCodigosActionForm actForm) throws DaoException
	{

		// Combo grupos de convenios
		List listaGrupos = this.convenioMgr.getListaGruposConvenio();
		List grupos = new ArrayList();
		GrupoConvenioVO grupo;
		for (Iterator it = listaGrupos.iterator(); it.hasNext();)
		{
			grupo = (GrupoConvenioVO) it.next();
			grupos.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
		}
		Collections.sort(grupos, LabelValueBean.CASE_INSENSITIVE_ORDER);

		// Combo tipos de edicion
		List listaTipos = new ArrayList();
		listaTipos.add(new LabelValueBean("AFP", "AFP"));
		listaTipos.add(new LabelValueBean("ISAPRE", "ISAPRE"));
		listaTipos.add(new LabelValueBean("APV", "APV"));
		listaTipos.add(new LabelValueBean("MOVIMIENTO DE PERSONAL", "MVTO"));
		listaTipos.add(new LabelValueBean("MOVTO PERSONAL AFIL. VOLUNTARIA", "MVTOAF"));
		listaTipos.add(new LabelValueBean("GENERO", "GENERO"));
		listaTipos.add(new LabelValueBean("TRAMO ASIGNACION FAMILIAR", "TRAMO"));

		actForm.setGruposConvenio(grupos);
		actForm.setTiposEdicion(listaTipos);
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>mapeo</dd>
	 * <dt>subAccion</dt>
	 * <dd>codigosEditar</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>Si es archivosEditar, presenta la edicion de mapeo de archivos. Si es archivosFicha, presenta el detalle del mapeo de archivos.</dd>
	 * <dt>grupoConvenio</dt>
	 * <dd>El grupo de convenios al que pertenece los codigos editar.</dd>
	 * <dt>tipoEdicion</dt>
	 * <dd>El tipo de codigos que se quiere editar: AFP, ISAPRE, etc.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es Guardar, se guarda el contenido del formulario en la BD. Si es Cancelar, se redirecciona a la lista de codigos.</dd>
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
		EdicionCodigosActionForm actForm = (EdicionCodigosActionForm) form;

		ActionMessages am = new ActionMessages();
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			this.mapeosMgr = new MapeosMgr(session);
			this.convenioMgr = new ConvenioMgr(session);

			int idGrupoConvenio = -1;
			String tipoEdicion = "";
			if (request.getParameter("operacion") != null)
			{
				// Lamado desde dentro
				boolean bGuardar = false;
				ActionRedirect redirect = new ActionRedirect();
				if (request.getParameter("operacion").equals("Guardar"))
				{
					bGuardar = true;
					GrupoConvenioVO gConv = this.convenioMgr.getGrupoConvenio(Integer.parseInt(actForm.getOpcGrupoConvenio()));
					if (actForm.getOpcTipoEdicion().equals("AFP"))
						this.mapeosMgr.guardaMapeo(gConv.getIdMapaCod(), actForm.getLista(), MapeoPensionVO.class);
					else if (actForm.getOpcTipoEdicion().equals("ISAPRE"))
						this.mapeosMgr.guardaMapeo(gConv.getIdMapaCod(), actForm.getLista(), MapeoSaludVO.class);
					else if (actForm.getOpcTipoEdicion().equals("APV"))
						this.mapeosMgr.guardaMapeo(gConv.getIdMapaCod(), actForm.getLista(), MapeoAPVVO.class);
					else if (actForm.getOpcTipoEdicion().equals("MVTO"))
						this.mapeosMgr.guardaMapeo(gConv.getIdMapaCod(), actForm.getLista(), MapeoTipoMovtoVO.class);
					else if (actForm.getOpcTipoEdicion().equals("MVTOAF"))
						this.mapeosMgr.guardaMapeo(gConv.getIdMapaCod(), actForm.getLista(), MapeoTipoMovtoAFVO.class);
					else if (actForm.getOpcTipoEdicion().equals("GENERO"))
						this.mapeosMgr.guardaMapeo(gConv.getIdMapaCod(), actForm.getLista(), MapeoGeneroVO.class);
					else if (actForm.getOpcTipoEdicion().equals("TRAMO"))
						this.mapeosMgr.guardaMapeo(gConv.getIdMapaCod(), actForm.getLista(), MapeoAsFamVO.class);

					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("grupoConvenio", actForm.getOpcGrupoConvenio());
					redirect.addParameter("tipoEdicion", actForm.getOpcTipoEdicion());
					redirect.addParameter("accion", "mapeo");
					redirect.addParameter("subAccion", "codigosFicha");
				} else if (request.getParameter("operacion").equals("Cancelar"))
				{
					redirect = new ActionRedirect(mapping.findForward("cancelar"));
					redirect.addParameter("grupoConvenio", actForm.getOpcGrupoConvenio());
					redirect.addParameter("accion", "mapeo");
					redirect.addParameter("subAccion", "codigosFicha");
				}

				tx.commit();

				if (bGuardar)
				{
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("exito.guardarConv"));
					this.saveMessages(request.getSession(), am);
				}
				return redirect;
			}

			// Llena los combos de edicion
			llenaCombosEdicion(actForm);

			if (request.getParameter("grupoConvenio") != null)
			{
				idGrupoConvenio = Integer.parseInt(request.getParameter("grupoConvenio"));
				tipoEdicion = request.getParameter("tipoEdicion");
			} else
			{
				idGrupoConvenio = Integer.parseInt(actForm.getOpcGrupoConvenio());
				tipoEdicion = actForm.getOpcTipoEdicion();
			}
			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(idGrupoConvenio);
			actForm.setIdMapaCod(grupoConvenio.getIdMapaCod());

			if (tipoEdicion.equals("AFP"))
				setLineas(actForm, this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoPensionVO.class, EntidadPensionVO.class));
			else if (tipoEdicion.equals("ISAPRE"))
				setLineas(actForm, this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoSaludVO.class, EntidadSaludVO.class, Constants.ID_SALUD_NINGUNA));
			else if (tipoEdicion.equals("APV"))
				setLineas(actForm, this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class, Constants.SIN_APV));
			else if (tipoEdicion.equals("MVTO"))
				setLineas(actForm, this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoVO.class, TipoMovimientoPersonalVO.class));
			else if (tipoEdicion.equals("MVTOAF"))
				setLineas(actForm, this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoAFVO.class, TipoMvtoPersoAFVO.class));
			else if (tipoEdicion.equals("GENERO"))
				setLineas(actForm, this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoGeneroVO.class, GeneroVO.class, Constants.GENERO_FALSO));
			else if (tipoEdicion.equals("TRAMO"))
				setLineas(actForm, this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAsFamVO.class, AsigFamVO.class)); //, Constants.TRAMO_ASIGFAM_FALSO));
			else
				actForm.setLista(null);
			if (actForm.getLista() != null)
				actForm.setNumCodigos(actForm.getLista().size());
			actForm.setOpcGrupoConvenio(Integer.toString(idGrupoConvenio));
			actForm.setOpcTipoEdicion(tipoEdicion);

			tx.commit();

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			logger.error("Se produjo una excepcion en ListaCodigosFichaAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}

	/**
	 * lineas
	 * 
	 * @param actForm
	 * @param lista
	 */
	private void setLineas(EdicionCodigosActionForm actForm, List lista)
	{
		actForm.setLista(new ArrayList());
		List listaTmp = new ArrayList();
		int idEntidad = -3433121;
		String nombre = "";
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			MapeoVO mapeo = (MapeoVO) it.next();
			LineaCodigoFicha lCodigo = new LineaCodigoFicha();
			lCodigo.setIdMapaCod(mapeo.getIdMapaCod());
			lCodigo.setIdEntidad(mapeo.getId());
			lCodigo.setIdCodigo(mapeo.getCodigo().trim());
			lCodigo.setIdCodigoNew(lCodigo.getIdCodigo());
			if (idEntidad != mapeo.getId())
			{
				idEntidad = mapeo.getId();
				nombre = mapeo.getEntidad().getNombre().trim();
			}
			lCodigo.setNombre(nombre);
			listaTmp.add(lCodigo);
		}
		Collections.sort(listaTmp);
		idEntidad = -3433121;
		for (Iterator it = listaTmp.iterator(); it.hasNext();)
		{
			LineaCodigoFicha lCodigo = (LineaCodigoFicha) it.next();
			if (idEntidad != lCodigo.getIdEntidad())
				idEntidad = lCodigo.getIdEntidad();
			else
				lCodigo.setNombre("");
		}
		actForm.setLista(listaTmp);
	}
}
