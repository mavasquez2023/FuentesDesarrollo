package cl.araucana.cp.presentation.struts.actions.mapeo;

import java.util.ArrayList;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
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
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.MapeosMgr;
import cl.araucana.cp.presentation.struts.forms.mapeo.ListaCodigosActionForm;

import com.bh.talon.User;

/*
 * @(#) ListaCodigosAction.java 1.17 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * @author ccostagliola
 * 
 * @version 1.17
 */
public class ListaCodigosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaCodigosAction.class);
	private ConvenioMgr convenioMgr;

	public ListaCodigosAction()
	{
		super();
		this.btns.add("imprimir");
	}

	/**
	 * llena combos edicion
	 * 
	 * @param actForm
	 * @param usuario
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(ListaCodigosActionForm actForm, User usuario) throws DaoException
	{
		UsuarioCP usuarioCP = (UsuarioCP) usuario;

		// Obtiene los grupos de convenios relacionados con convenios de empresas que el usuario administra
		// o grupos de convenios sobre los que el usuario tiene permiso de lectura o escritura.
		List convenios = this.convenioMgr.getConveniosEmpresasIn(usuarioCP.getUnionEmpresasLectura());
		Set gruposConvenio = new HashSet(CollectionUtils.collect(convenios, new Transformer()
		{
			public Object transform(Object input)
			{
				return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
			}
		}));

		// Combo grupos de convenios
		// List listaGrupos = convenioMgr.getListaGruposConveniosEncargado(((PersonaVO) usuario.getUserReference()).getIdPersona().intValue());
		List listaGrupos = this.convenioMgr.getGruposConveniosIn(gruposConvenio);
		List grupos = new ArrayList();
		GrupoConvenioVO grupo;
		for (Iterator it = listaGrupos.iterator(); it.hasNext();)
		{
			grupo = (GrupoConvenioVO) it.next();
			grupos.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
		}
		Collections.sort(grupos, LabelValueBean.CASE_INSENSITIVE_ORDER);

		actForm.setGruposConvenio(grupos);
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>mapeo</dd>
	 * <dt>subAccion</dt>
	 * <dd>codigosFicha</dd>
	 * <dt>grupoConvenio</dt>
	 * <dd>El grupo de convenios para el cual se quieren listar sus codigos.</dd>
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
		ListaCodigosActionForm actForm = (ListaCodigosActionForm) form;

		Session session = null;
		Transaction tx = null;
		try
		{
			//Carga una página en blanco cuando se selecciona "Definición de Códigos" en el menu lateral izquierdo.
			String auxFlgBlanco = request.getParameter("flgBlanco") == null ? "0" : request.getParameter("flgBlanco");
			if (auxFlgBlanco.equals("1"))
				return mapping.findForward("blanco");

			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			// Instancia los managers correspondientes
			MapeosMgr mapeosMgr = new MapeosMgr(session);
			this.convenioMgr = new ConvenioMgr(session);

			UsuarioCP usuarioCP = (UsuarioCP) usuario;

			int idGrupoConvenio = -1;

			boolean bImprimir = false;
			if (request.getParameter("imprimir") != null)
				bImprimir = true;

			// Llena los combos de edicion
			llenaCombosEdicion(actForm, usuario);

			if (actForm.getOpcGrupoConvenio() == null)
			{
				if (request.getParameter("grupoConvenio") == null)
					actForm.setOpcGrupoConvenio(((LabelValueBean) actForm.getGruposConvenio().get(0)).getValue());
				else
					actForm.setOpcGrupoConvenio(request.getParameter("grupoConvenio"));
			} else
				request.setAttribute("cambioParam", "accion=mapeo&subAccion=codigosFicha&opcGrupoConvenio=" + actForm.getOpcGrupoConvenio());
			idGrupoConvenio = Integer.parseInt(actForm.getOpcGrupoConvenio());

			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(idGrupoConvenio);
			actForm.setIdMapaCod(grupoConvenio.getIdMapaCod());
			actForm.setNombreGrupoConvenio(grupoConvenio.getNombre().trim());
			List listaPension, listaAPV, listaSalud, listaTramoAsigFam, listaGenero, listaTipoMovPersona, listaTipoMovPerAF;

			listaPension = mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoPensionVO.class, EntidadPensionVO.class);
			listaSalud = mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoSaludVO.class, EntidadSaludVO.class);//, Constants.ID_SALUD_NINGUNA);
			listaAPV = mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class, Constants.SIN_APV);
			listaTipoMovPersona = mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoVO.class, TipoMovimientoPersonalVO.class);
			listaTipoMovPerAF = mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoAFVO.class, TipoMvtoPersoAFVO.class);
			listaGenero = mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoGeneroVO.class, GeneroVO.class, Constants.GENERO_FALSO);
			listaTramoAsigFam = mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAsFamVO.class, AsigFamVO.class);//, Constants.TRAMO_ASIGFAM_FALSO);

			actForm.setListaAFP(setLineas(listaPension));
			actForm.setListaISAPRE(setLineas(listaSalud));
			actForm.setListaAPV(setLineas(listaAPV));
			actForm.setListMvtoPer(setLineas(listaTipoMovPersona));
			actForm.setListMvtoPerAF(setLineas(listaTipoMovPerAF));
			actForm.setListaGenero(setLineas(listaGenero));
			actForm.setListaTramo(setLineas(listaTramoAsigFam));

			// Setea el permiso de edicion a los mapas de codigos de los grupos de convenios para los cuales el usuario tiene
			// permiso de escritura. Estos son aquellos que estan relacionados con convenios de empresas en las cuales el
			// usuario es administrador y tambien los que estan relacionados con convenios en los que el usuario tiene permiso
			// de escritura.

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
			Set gruposConvenio = new HashSet(CollectionUtils.union(gConvAdmin, gConvEnc));
			if (gruposConvenio.contains(new Integer(idGrupoConvenio)))
				actForm.setPuedeEditar(Boolean.TRUE);
			else
				actForm.setPuedeEditar(Boolean.FALSE);

			tx.commit();
			
			if (!bImprimir)
				return mapping.findForward("exito");
			return mapping.findForward("imprimir");
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
	 * @param lista
	 * @return
	 */
	private List setLineas(List lista)
	{
		int idEntidad = -3433121;
		List listaTmp = new ArrayList();
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
		/*for (Iterator it = listaTmp.iterator(); it.hasNext();)
		{
			LineaCodigoFicha lCodigo = (LineaCodigoFicha) it.next();
			if (idEntidad != lCodigo.getIdEntidad())
			{
				idEntidad = lCodigo.getIdEntidad();
			} else
				lCodigo.setNombre("");
		}*/
		return listaTmp;
	}
}
