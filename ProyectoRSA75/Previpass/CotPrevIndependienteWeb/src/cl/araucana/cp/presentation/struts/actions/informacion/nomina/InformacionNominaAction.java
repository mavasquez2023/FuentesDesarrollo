package cl.araucana.cp.presentation.struts.actions.informacion.nomina;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.presentation.beans.InformacionNomina;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ProcesoMgr;

import com.bh.talon.User;

/*
 * @(#) InformacionNominaAction.java 1.0 13/09/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jlandero
 * 
 * @version 1.0
 */
public class InformacionNominaAction extends AppAction
{
	static Logger logger = Logger.getLogger(InformacionNominaAction.class);

	/**
	 *	Action encargado de mostrar la informacion sobre el listado de errores
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		Session session = null;
		Transaction tx = null;		

		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			ProcesoMgr procesoMgr = new ProcesoMgr(session);
			
			String idEmpresa  =  (String)request.getParameter("idEmpresa");
			String tipoNomina =  (String)request.getParameter("tipoNomina");
			String idConvenio =  (String)request.getParameter("idConvenio");					 					
			
			InformacionNomina infoNomina = procesoMgr.getInformacionNomina(Long.parseLong(idEmpresa), Long.parseLong(idConvenio), tipoNomina);
			
			request.setAttribute("infoNomina", infoNomina);
			request.setAttribute("fechaHoy", new Date());

			tx.commit();

			return mapping.findForward("exito");
		} catch (Exception ex)
		{
			if (tx != null)
				tx.rollback();
			logger.error("Ha ocurrido la siguiente excepcion: ", ex);
			return mapping.findForward("error");
		}
	}
}

