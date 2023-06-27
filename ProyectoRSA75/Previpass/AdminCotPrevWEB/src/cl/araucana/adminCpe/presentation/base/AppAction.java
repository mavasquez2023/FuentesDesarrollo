package cl.araucana.adminCpe.presentation.base;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Transaction;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;

import com.bh.talon.Activity;
import com.bh.talon.User;
import com.bh.talon.actions.BaseAction;
/*
* @(#) AppAction.java 1.14 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.14
 */
abstract public class AppAction extends BaseAction
{
	private static final Logger logger = Logger.getLogger(AppAction.class);
	protected String accion;
	protected String subAccion;
	protected String subSubAccion;
	protected List btns = new ArrayList();
	
	/**
	 * Autentifica un conjunto de credenciales. Si el usuario no hace login debe retornar null.
	 */
	protected User authenticate(Map credentials) throws Exception
	{
		try
		{
			return new UsuarioCP(credentials, HibernateUtil.getSession());
		} catch (Exception ex) 
		{
			return null;
		}
	}

	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HibernateUtil.getSession();
		ParametroMgr parametro = new ParametroMgr(HibernateUtil.getSession());
		request.getSession().setAttribute("periodo", parametro.getPeriodoFormat());
		this.accion = (request.getParameter("accion") != null ? request.getParameter("accion") : Constants.ACCION_DEFAULT);
		this.subAccion = (request.getParameter("subAccion") != null ? request.getParameter("subAccion") : "");
		this.subSubAccion = (request.getParameter("subSubAccion") != null ? request.getParameter("subSubAccion") : "");


		if (request.getRemoteUser() != null && (request.getSession() == null || request.getSession().getAttribute(USER_BEAN) == null))
		{
			String userLogin = request.getRemoteUser();
			int pos = userLogin.indexOf('-');
			if (pos > 0)
				userLogin = userLogin.substring(0, pos);
			logger.info("AppAction::userLoginNew:" + userLogin + "::");
			request.getSession().setAttribute("listaPath", null);
			Map credentiales = new HashMap();
			credentiales.put("login", userLogin);
			Usuario usuario = (Usuario) doLogin(request, credentiales);
			if ((usuario == null) || !((PersonaVO) usuario.getUserReference()).isAdminAraucana()) 
			{
				//Aca redireccionar a una pagina para indicar que el usuario esta en
				//el registro de usuarios, pero no en la BD, o que no es administrador Araucana.
				request.getSession().invalidate();
				if (usuario == null)
					return mapping.findForward("usuarioNoBD");
				return mapping.findForward("usuarioNoAdmin");
			}
			request.getSession().setAttribute("usuarioActivo", usuario.getName().trim());
			HibernateUtil.setIdUser("" + ((PersonaVO)usuario.getUserReference()).getIdPersona());				
		} else if (request.getSession().getAttribute(USER_BEAN) != null)
		{
			Usuario usuario = (Usuario) request.getSession().getAttribute(USER_BEAN);
			request.setAttribute("usuarioActivo", usuario.getName().trim());
			HibernateUtil.setIdUser("" + ((PersonaVO)usuario.getUserReference()).getIdPersona());	
		}

		ActionForward result = super.perform(mapping, form, request, response);
		Transaction tx = HibernateUtil.getSession().getTransaction();
		if (tx != null && tx.isActive())
			try
			{
				//TODO descomentar envio mail
				/*Mail mail = new Mail(Mail.ERROR, "cchamblas@builderhouse.com", "zimbra", -1, "tx@builderhouse.com", "", "");
				mail.setLocalHost("aa.ss.dd");
				mail.setContenido(new StringBuffer("AppAction::transaccion sin cerrar en::" + request.getRequestURI() + "?" + request.getQueryString()));

				mail.setDescCorta("tx no cerada en app admin");
				ReportaError.enviar(mail);*/
				tx.rollback();
				logger.warn("\n\n\nAppAction::transaccion sin cerrar en::" + request.getRequestURI() + "?" + request.getQueryString());
			} catch (Throwable e)
			{
				logger.warn("Lanzable capturado y absorbido:", e);
			}
		if (result != null)
		{
			setBotones(request);
			revisaMenus(request, mapping);
			HibernateUtil.closeSession();
			return result;
		}
		// nunca debiese pasar a esta instancia pero por si acaso...
		HibernateUtil.closeSession();
		return null;
	}

	/**
	 * 
	 * @param request
	 */
	protected void setBotones(HttpServletRequest request)
	{
		for (Iterator it = this.btns.iterator(); it.hasNext();)
		{
			String lavelBtn = (String)it.next();
			request.setAttribute(lavelBtn, Constants.TXT_BTNS.getProperty(lavelBtn, "Boton sin valor!"));
		}
	}

	/**
	 * revisa menus
	 * @param request
	 * @param mapping
	 */
	protected void revisaMenus(HttpServletRequest request, ActionMapping mapping)
	{
		try
		{
			HttpSession session = request.getSession(true);

			Enumeration e = request.getParameterNames();
			StringBuffer sb = new StringBuffer(request.getRequestURL() + "?");
			while (e.hasMoreElements())
			{
				String key = (String)e.nextElement();
				sb.append(key + "=" + request.getParameter(key) + "&");
			}
			String param = (request.getAttribute("cambioParam") != null ? (String)request.getAttribute("cambioParam") : mapping.getParameter());
			
			String url = "<a href=\""+ sb.toString() + "\" target=\"BODY\" class=\"links\">" + param + "</a>";
			String etiqueta = "<span class=\"botonera_ppalactivada\">" + param + "</span>";
			List lista = (List)session.getAttribute("listaPath");
			List newLista = new ArrayList();
			boolean enc = false;
			if (lista == null || request.getParameter("limpiaPath") != null)
				newLista.add(etiqueta);
			else
			{
				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					String valor = (String)it.next();
					if (valor != null && valor.indexOf(param) >= 0)
					{
						newLista.add(etiqueta);
						enc = true;
						break;
					} else if (valor != null && valor.startsWith("<a"))
						newLista.add(valor);					
				}
				if (!enc)
					newLista.add(etiqueta);
			}
			newLista.add(url);
			session.setAttribute("listaPath", newLista);
		} catch (Exception e)
		{
			logger.error("Error en menu:" + e.getMessage() + ":", e);
		}
	}

	protected boolean isAuthorized(User arg0, Activity arg1) throws Exception 
	{
		logger.info("isAuthorized:" + arg0 + "::" + arg1 + ":");
		return false;
	}
}
