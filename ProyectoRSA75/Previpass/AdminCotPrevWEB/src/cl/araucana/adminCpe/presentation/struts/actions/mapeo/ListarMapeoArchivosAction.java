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
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.ConvenioMgr;
import cl.araucana.adminCpe.presentation.mgr.MapeosMgr;
import cl.araucana.adminCpe.presentation.struts.forms.mapeo.MapeoArchivosActionForm;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) ListarMapeoArchivosAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.2
 */
public class ListarMapeoArchivosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListarMapeoArchivosAction.class);

	private ConvenioMgr convenioMgr;
	
	/**
	 * llena combos grupo convenio
	 * @param actForm
	 * @param usuario
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(MapeoArchivosActionForm actForm)  throws DaoException 
	{
		//Combo grupos de convenios
		List listaGrupos = this.convenioMgr.getListaGruposConvenio();
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
		try 
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			MapeosMgr mapeosMgr = new MapeosMgr(session);
			this.convenioMgr = new ConvenioMgr(session);

			//Llena los combos de edicion
			llenaCombosEdicion(actForm);

			if (actForm.getOpcGrupoConvenio() == null) 
			{
				if (request.getParameter("grupoConvenio") == null)
					actForm.setOpcGrupoConvenio(((LabelValueBean) actForm.getGruposConvenio().get(0)).getValue());
				else
					actForm.setOpcGrupoConvenio(request.getParameter("grupoConvenio"));
			}
			
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
