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
import cl.araucana.adminCpe.presentation.struts.forms.mapeo.ListaCodigosActionForm;
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
* @(#) ListaCodigosAction.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.5
 */
public class ListaCodigosAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ListaCodigosAction.class);

	private MapeosMgr mapeosMgr;
	private ConvenioMgr convenioMgr;
	
	/**
	 * llena combos grupo convenio
	 * @param actForm
	 * @throws DaoException
	 */
	private void llenaCombosEdicion(ListaCodigosActionForm actForm) throws DaoException
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
	 * <dd>codigosFicha</dd>
	 * <dt>grupoConvenio</dt>
	 * <dd>El grupo de convenios para el cual se quieren listar sus codigos.</dd>
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
		ListaCodigosActionForm actForm = (ListaCodigosActionForm) form;
	
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			this.mapeosMgr = new MapeosMgr(session);
			this.convenioMgr = new ConvenioMgr(session); 
			
			int idGrupoConvenio = -1; 
			//Llena los combos de edicion
			llenaCombosEdicion(actForm);

			if (actForm.getOpcGrupoConvenio() == null)
			{
				if (request.getParameter("grupoConvenio") == null)
					actForm.setOpcGrupoConvenio(((LabelValueBean) actForm.getGruposConvenio().get(0)).getValue());
				else
					actForm.setOpcGrupoConvenio(request.getParameter("grupoConvenio"));
			}
			idGrupoConvenio = Integer.parseInt(actForm.getOpcGrupoConvenio());
			
			GrupoConvenioVO grupoConvenio = this.convenioMgr.getGrupoConvenio(idGrupoConvenio);
			actForm.setIdMapaCod(grupoConvenio.getIdMapaCod());
			List listaPension, listaAPV, listaSalud, listaTramoAsigFam, listaGenero, listaTipoMovPersona, listaTipoMovPersAF;
			
			listaPension = this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoPensionVO.class, EntidadPensionVO.class);
			listaSalud = this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoSaludVO.class, EntidadSaludVO.class);//, Constants.ID_SALUD_NINGUNA);
			listaAPV = this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAPVVO.class, EntidadApvVO.class, Constants.SIN_APV);
			listaTipoMovPersona = this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoVO.class, TipoMovimientoPersonalVO.class);
			listaTipoMovPersAF = this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoTipoMovtoAFVO.class, TipoMvtoPersoAFVO.class);
			listaGenero = this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoGeneroVO.class, GeneroVO.class, Constants.GENERO_FALSO);
			listaTramoAsigFam = this.mapeosMgr.getMapeos(grupoConvenio.getIdMapaCod(), MapeoAsFamVO.class, AsigFamVO.class);//, Constants.TRAMO_ASIGFAM_FALSO);

			actForm.setListaAFP(setLineas(listaPension));
			actForm.setListaISAPRE(setLineas(listaSalud));
			actForm.setListaAPV(setLineas(listaAPV));
			actForm.setListMvtoPer(setLineas(listaTipoMovPersona));
			actForm.setListMvtoPerAF(setLineas(listaTipoMovPersAF));
			actForm.setListaGenero(setLineas(listaGenero));
			actForm.setListaTramo(setLineas(listaTramoAsigFam));

			tx.commit();
			
			return mapping.findForward("exito");
		} catch (Exception ex) {
			logger.error("Se produjo una excepcion en ListaCodigosFichaAction.doTask()", ex);
			if (tx != null)
				tx.rollback();
			return mapping.findForward("error");
		}
	}

	/**
	 * lineas
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
				idEntidad = lCodigo.getIdEntidad();
			else
				lCodigo.setNombre("");
		}*/
		return listaTmp;
	}
}
