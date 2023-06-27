package cl.araucana.adminCpe.presentation.struts.actions.estructuras;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;


import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.EstructuraMgr;
import cl.araucana.adminCpe.presentation.struts.forms.estructuras.MovPersonalAfListarActionForm;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;

import com.bh.talon.User;
/*
* @(#) movPersonalAfListarAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * @author aacuña
 * 
 * @version 1.2
 */
public class MovPersonalAfListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(MovPersonalAfListarAction.class);

	public MovPersonalAfListarAction() 
	{
		super();
	}
	
	/**
	 * mov personal af listar
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MovPersonalAfListarActionForm actForm = (MovPersonalAfListarActionForm) form;
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			//Instancia los managers correspondientes
			EstructuraMgr estructuraMgr = new EstructuraMgr(session);

			List lista = estructuraMgr.getEstructuraTipoMovPersonalAf();
			Iterator t=lista.iterator();
			while(t.hasNext())
			{
				TipoMvtoPersoAFVO tipo=(TipoMvtoPersoAFVO)t.next();
				tipo.setNombre(tipo.getNombre().trim());
			}
			actForm.setLista(lista);
			
			tx.commit();
			
			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListaEstructuraMovPersonalAction.doTask()", ex);
			if (tx != null) 
				tx.rollback();
			return mapping.findForward("error");
		}
	}
}
