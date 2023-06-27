package cl.araucana.adminCpe.presentation.struts.actions.conceptoTeso;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.base.AppAction;
import cl.araucana.adminCpe.presentation.mgr.TesoreriaMgr;
import cl.araucana.adminCpe.presentation.struts.forms.conceptoTeso.ConceptoTesoListarActionForm;

import com.bh.talon.User;
/*
* @(#) ConceptoTesoListarAction.java 1.1 10/05/2009
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
public class ConceptoTesoListarAction extends AppAction
{
	private static Logger logger = Logger.getLogger(ConceptoTesoListarAction.class);

	public ConceptoTesoListarAction() 
	{
		super();
	}
	
	/**
	 * listar concepto tesoreria
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ConceptoTesoListarActionForm actForm = (ConceptoTesoListarActionForm) form;
	
		Session session = null;
		try 
		{
			session = HibernateUtil.getSession();
			
			TesoreriaMgr tesoreriaMgr = new TesoreriaMgr(session);
			
			List lista  = tesoreriaMgr.getConceptoTesoreria();			
			
			actForm.setLista(lista);
			
			return mapping.findForward("exito");
		} catch (Exception ex) 
		{
			logger.error("Se produjo una excepcion en ListaConceptoTesoreriaActionForm.doTask()", ex);
			return mapping.findForward("error");
		}
	}
}
