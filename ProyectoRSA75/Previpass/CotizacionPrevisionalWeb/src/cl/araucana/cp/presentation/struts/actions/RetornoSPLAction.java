package cl.araucana.cp.presentation.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bh.talon.User;

import cl.araucana.cp.presentation.base.AppAction;
/*
* @(#) RetornoSPLAction.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class RetornoSPLAction extends AppAction
{

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("retorno");
	}

}
