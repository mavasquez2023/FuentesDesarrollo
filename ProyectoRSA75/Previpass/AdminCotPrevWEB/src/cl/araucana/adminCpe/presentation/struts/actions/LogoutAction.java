package cl.araucana.adminCpe.presentation.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bh.talon.User;

import cl.araucana.adminCpe.presentation.base.AppAction;
/*
* @(#) LogoutAction.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * 
 * @version 1.2
 */
public class LogoutAction extends AppAction
{

	/**
	 * logo aut
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		request.getSession().invalidate();

		return mapping.findForward("logout");
	}
}
