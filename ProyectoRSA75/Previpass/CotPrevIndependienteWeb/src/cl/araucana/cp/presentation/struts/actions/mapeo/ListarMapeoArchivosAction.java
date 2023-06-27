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
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.MapeosMgr;
import cl.araucana.cp.presentation.struts.forms.mapeo.MapeoArchivosActionForm;

import com.bh.talon.User;
/*
* @(#) ListarMapeoArchivosAction.java 1.13 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.13
 */
public class ListarMapeoArchivosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarMapeoArchivosAction.class);
	private ConvenioMgr convenioMgr;
	
	public ListarMapeoArchivosAction() 
	{
		super();	
		this.btns.add("imprimir");
	}
	/**
	 * llena combos edicion
	 * @param actForm
	 * @param usuario
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(MapeoArchivosActionForm actForm, User usuario)  throws DaoException {
		
		UsuarioCP usuarioCP = (UsuarioCP) usuario;
		
		//Obtiene los grupos de convenios relacionados con convenios de empresas que el usuario administra
		//o grupos de convenios sobre los que el usuario tiene permiso de lectura o escritura.
		List convenios = this.convenioMgr.getConveniosEmpresasIn(usuarioCP.getUnionEmpresasLectura());
		Set gruposConvenio = new HashSet(CollectionUtils.collect(convenios,
				new Transformer() {
					public Object transform(Object input) {
						return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
					}
				}));
		
		//Combo grupos de convenios
		//List listaGrupos = new ArrayList(usuarioMgr.getPersona(usuario.getLogin()).getGruposConvenio());
		List listaGrupos = this.convenioMgr.getGruposConveniosIn(gruposConvenio);
		List grupos = new ArrayList();
		GrupoConvenioVO grupo;
		for (Iterator it = listaGrupos.iterator(); it.hasNext();) {
			grupo = (GrupoConvenioVO) it.next();
			grupos.add(new LabelValueBean(grupo.getNombre().trim(), Integer.toString(grupo.getIdGrupoConvenio())));
		}
		Collections.sort(grupos, LabelValueBean.CASE_INSENSITIVE_ORDER);

		actForm.setGruposConvenio(grupos);
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara
	 * al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son
	 * los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>mapeo</dd>
	 * <dt>subAccion</dt>
	 * <dd>archivosLista</dd>
	 * <dt>grupoConvenio</dt>
	 * <dd>El grupo de convenios para el cual se mostrara la lista de mapeos.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MapeoArchivosActionForm actForm = (MapeoArchivosActionForm) form;
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			MapeosMgr mapeosMgr = new MapeosMgr(session);
			this.convenioMgr = new ConvenioMgr(session);

			UsuarioCP usuarioCP = (UsuarioCP) usuario;
			
			//Llena los combos de edicion
			llenaCombosEdicion(actForm, usuario);

			if (actForm.getOpcGrupoConvenio() == null) 
			{
				if (request.getParameter("grupoConvenio") == null)
					actForm.setOpcGrupoConvenio(((LabelValueBean) actForm.getGruposConvenio().get(0)).getValue());
				else
					actForm.setOpcGrupoConvenio(request.getParameter("grupoConvenio"));
			} else
			    request.setAttribute("cambioParam", "accion=mapeo&subAccion=archivosLista&opcGrupoConvenio=" + actForm.getOpcGrupoConvenio());
			
			GrupoConvenioVO grupo = this.convenioMgr.getGrupoConvenio(Integer.parseInt(actForm.getOpcGrupoConvenio()));
			String mapeoNoDef = "Mapeo indefinido";
			MapaNominaVO mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomRem());
			actForm.setDescripcionR(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
			mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomGra());
			actForm.setDescripcionG(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
			mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomRel());
			actForm.setDescripcionA(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);
			mapa = mapeosMgr.getMapaNomina(grupo.getIdMapaNomDep());
			actForm.setDescripcionD(mapa.getIdMapaNom() != Constants.ID_NOMINA_NULA ? mapa.getDescripcion().trim() : mapeoNoDef);

			//Setea el permiso de edicion a los mapas de codigos de los grupos de convenios para los cuales el usuario tiene
			//permiso de escritura. Estos son aquellos que estan relacionados con convenios de empresas en las cuales el
			//usuario es administrador y tambien los que estan relacionados con convenios en los que el usuario tiene permiso
			//de escritura.
			
			//Grupos de convenios relacionados con convenios de empresas en las que el usuario
			//es adminstrador
			List convenios = this.convenioMgr.getConveniosEmpresasIn(usuarioCP.getEmpresasAdmin());
			Set gConvAdmin = new HashSet(CollectionUtils.collect(convenios,
					new Transformer() {
						public Object transform(Object input) {
							return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
						}
					}));
			//Grupos de convenios relacionados con convenios en los que el usuario es encargado
			//de escritura
			convenios = this.convenioMgr.getConveniosIn(usuarioCP.getConveniosEditorRetVO());
			Set gConvEnc = new HashSet(CollectionUtils.collect(convenios,
					new Transformer() {
						public Object transform(Object input) {
							return new Integer(((ConvenioVO) input).getIdGrupoConvenio());
						}
					}));
			Set gruposConvenio = new HashSet(CollectionUtils.union(gConvAdmin, gConvEnc));
			if (gruposConvenio.contains(new Integer(actForm.getOpcGrupoConvenio())))
				actForm.setPuedeEditar(Boolean.TRUE);
			else
				actForm.setPuedeEditar(Boolean.FALSE);
			
			tx.commit();

			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en ListarMapeoArchivosAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
