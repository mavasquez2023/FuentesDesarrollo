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
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
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
