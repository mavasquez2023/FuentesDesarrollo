

/*
 * @(#) PagarAction.java 1.0 28/10/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cp.presentation.struts.actions.nomina;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;

import com.bh.talon.User;


/**
 * @author gpavez
 * 
 * @version 1.0
 */
public class CrearAction extends AppAction {
	
	private static Logger logger = Logger.getLogger(CrearAction.class);

	/**
	 * Procesa el request para generar la respuesta html que se le entregara
	 * al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son
	 * los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>admin</dd>
	 * <dt>subAccion</dt>
	 * <dd>empresas</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>conveniosLista</dd>
	 * <dt>rutEmpresa</dt>
	 * <dd>El rut de la empresa (<code>int</code>) para la que se quiere mostrar los convenios.</dd>
	 * </dl> 
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * 
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String sIdEmpresa = request.getParameter("idEmpresa");
		String sIdConvenio = request.getParameter("idConvenio");
		String sTipoNomina = request.getParameter("tipoNomina");
		
		logger.debug("CrearNominaAction::" + sIdEmpresa + ":" + sIdConvenio + ":" + sTipoNomina);

		int idPersona = Integer.parseInt(user.getLogin());
		int idEmpresa = Integer.parseInt(sIdEmpresa);
		int idConvenio = Integer.parseInt(sIdConvenio);
		char tipoNomina = sTipoNomina.charAt(0);
		
		Transaction tx = null;

		try {
			Session session = HibernateUtil.getSession();
			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			
			tx = session.beginTransaction();

			procesoMgr.crearNominaEnLinea(idPersona, idEmpresa, idConvenio, tipoNomina);
			tx.commit();
			
			return mapping.findForward("exito");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			
			e.printStackTrace();
		}
		
		return mapping.findForward("error");		
	}
}
