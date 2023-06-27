package cl.araucana.cp.presentation.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.utils.ProxyLDAP;

import com.bh.talon.User;
/*
* @(#) Inicio.java 1.8 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author cchamblas
 * 
 * @version 1.8
 */
public class InicioAction extends AppAction  
{
	public static final String PARAM_FORWARD = "forward";
	
	static Logger logger = Logger.getLogger(InicioAction.class);

	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		try
		{
			logger.info("\n\n\nentrando a inicio:");
			//Verifico si si se debe forzar el cambio de password por parte del usuario.
			if( ProxyLDAP.mustUserChangePassword( usuario.getLogin()  + "-" + Utils.generaDV(Integer.parseInt(usuario.getLogin()))) ){
				return mapping.findForward("mustUserChangePassword");
			}else
				return mapping.findForward("inicio");
			
		} catch (Exception e)
		{
			logger.error("ERROR en inicio:", e);
			return mapping.findForward(Constants.ACCION_DEFAULT);
		}
	}
	
}
