package cl.araucana.cp.presentation.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.DirectStringAttribute;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.hibernate.dao.PersonaDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.PersonaMgr;
import cl.araucana.cp.web.menu.Menus;
import cl.araucana.cp.web.menu.MenusConfig;
import cl.araucana.cp.web.menu.PathNavegacion;
import cl.araucana.cp.web.menu.Tab;

import com.bh.talon.Activity;
import com.bh.talon.User;
import com.bh.talon.actions.BaseAction;

/*
 * @(#) AppAction.java 1.47 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.47
 */
abstract public class AppAction extends BaseAction
{
	private static final Logger logger = Logger.getLogger(AppAction.class);
	public static final String PARAM_ERROR = "error";
	protected String accion;
	protected String subAccion;
	protected String subSubAccion;
	protected List btns = new ArrayList();
	protected boolean administrador = false;

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
	 * nombre clase
	 * 
	 * @param fullyQualifiedClassName
	 * @return
	 */
	public String nombreClase(String fullyQualifiedClassName)
	{

		return fullyQualifiedClassName.substring(fullyQualifiedClassName.lastIndexOf('.') + 1);
	}

	/**
	 * execute
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Session session = HibernateUtil.getSession();
		this.accion = (request.getParameter("accion") != null ? request.getParameter("accion") : Constants.ACCION_DEFAULT);
		this.subAccion = (request.getParameter("subAccion") != null ? request.getParameter("subAccion") : "");
		this.subSubAccion = (request.getParameter("subSubAccion") != null ? request.getParameter("subSubAccion") : "");
		String userName = null;

		logger.info("AppAction::accion:" + this.accion + "::");
		if (request.getRemoteUser() != null && (request.getSession() == null || request.getSession().getAttribute(USER_BEAN) == null))
		{
			limpiaCacheWeb();
			String userLogin = request.getRemoteUser();
			logger.info("AppAction::userLogin:" + userLogin + "::");
			int pos = userLogin.indexOf('-');
			if (pos > 0)
				userLogin = userLogin.substring(0, pos);
			Map credentiales = new HashMap();
			credentiales.put("login", userLogin);
			UsuarioCP user = (UsuarioCP) doLogin(request, credentiales);
			
			PersonaDAO personaDAO = new PersonaDAO(session);
			
			List listPersona = personaDAO.getListTipoPersona(userLogin);
			
			if (user == null || listPersona.size() == 0)
			{// Aca redireccionar a una pagina para indicar que el usuario esta en el registro de usuarios, pero no en la BD.
				request.getSession().invalidate();

				return mapping.findForward("usuarioNoBD");
			}
			logger.info("***USUARIO AUTENTICADO***:");
			logger.debug(user.toString());

			this.agregaPermisos(credentiales, user);
			HibernateUtil.setIdUser("", "" + ((PersonaVO) user.getUserReference()).getIdPersona());
			request.setAttribute("usuarioActivo", user.getName().trim());
			userName = ((PersonaVO) user.getUserReference()).getIdPersona().toString();
		} else if (request.getSession().getAttribute(USER_BEAN) != null)
		{
			UsuarioCP usuario = (UsuarioCP) request.getSession().getAttribute(USER_BEAN);
			usuario.iniciarSeguridad(HibernateUtil.getSession());
			HibernateUtil.setIdUser("", "" + ((PersonaVO) usuario.getUserReference()).getIdPersona());
			request.setAttribute("usuarioActivo", usuario.getName().trim());
			userName = ((PersonaVO) usuario.getUserReference()).getIdPersona().toString();
		} else
			logger.info("\n\n\n***SIN SESSION!!!***:");
		PersonaMgr personaMgr = new PersonaMgr(session);
		this.administrador = userName == null ? false : personaMgr.isAdminEmpresa(userName);
		ActionForward result = super.perform(mapping, form, request, response);
		if (result != null && !this.subAccion.equals("login"))
		{
			ParametroMgr parametro = new ParametroMgr(HibernateUtil.getSession());
			request.setAttribute("bloqueoEdicionNom", "" + parametro.plazoCerrado(Constants.PARAM_FIN_EDICION_NOM));
			request.setAttribute("bloqueoPagoCaja", "" + parametro.plazoCerrado(Constants.PARAM_FIN_PAGO_CAJA_IND));
			request.setAttribute("bloqueoPagoLinea", "" + parametro.plazoCerrado(Constants.PARAM_FIN_PAGO_LINEA_IND));

			request.setAttribute("linkContactenos", "" + parametro.getParam(Constants.PARAM_LINK_CONTACTENOS));
			request.setAttribute("linkHome", "" + parametro.getParam(Constants.PARAM_LINK_HOME));
			request.setAttribute("descWin", "" + parametro.getParam(Constants.PARAM_INST_JRE_WIN));
			request.setAttribute("descLinux", "" + parametro.getParam(Constants.PARAM_INST_JRE_LINUX));
			request.setAttribute("aviso", parametro.getAviso());
			request.setAttribute("calendario", parametro.getCalendario());
			request.setAttribute("urlMisPlanillas", parametro.getParam(Constants.PARAM_URL_MIS_PLANILLAS));

			if (!this.subAccion.equals("imprimir"))
			{
				revisaMenus(request);
				setBotones(request);
			}
			request.setAttribute("periodo", parametro.getPeriodoFormat());
		}

		/*Transaction tx = HibernateUtil.getSession().getTransaction();
		if (tx != null && tx.isActive())
			try
			{
				//TODO descomentar envio mail
				//Mail mail = new Mail(Mail.ERROR, "cchamblas@builderhouse.com", "zimbra", -1, "tx@builderhouse.com", "", "");
				//mail.setLocalHost("aa.ss.dd");
				//mail.setContenido(new StringBuffer("AppAction::transaccion sin cerrar en::" + request.getRequestURI() + "?" + request.getQueryString()));

				//mail.setDescCorta("tx no cerada en app cliente");
				//ReportaError.enviar(mail);
				tx.rollback();
				logger.warn("\n\n\nAppAction::transaccion sin cerrar en::" + request.getRequestURI() + "?" + request.getQueryString());
			} catch (Throwable e)
			{
				logger.warn("Lanzable capturado y absorbido:", e);
			}*/

		logger.info("AppAction::saliendo::");
		HibernateUtil.closeSession();
		return result;
	}

	/**
	 * limpia cache web
	 * 
	 */
	public void limpiaCacheWeb()
	{
		try
		{
			logger.info("limpiaCacheWeb");
			CacheManager.create();
			CacheManager cm = CacheManager.getInstance();
			String[] nombres = cm.getCacheNames();

			if (nombres != null)
			{
				for (int i = 0; i < nombres.length; i++)
				{
					Ehcache ehCache = cm.getEhcache(nombres[i]);
					ehCache.removeAll();
					ehCache.clearStatistics();
				}
			}
		} catch (Exception e)
		{
			logger.info("ERROR en limpiaCache WEB:" + e);
		}
	}

	/**
	 * agrega permisos
	 * 
	 * @param credenciales
	 * @param usuario
	 * @param session
	 * @param request
	 */
	private void agregaPermisos(Map credenciales, UsuarioCP usuario)
	{
		if (!usuario.getEmpresasAdmin().isEmpty())
			credenciales.put("rol", "ADE");
		else
		{
			if (!usuario.getConveniosEditor().isEmpty())
				credenciales.put("rol", "ECE");
			else
			{
				if (usuario.getNumConLector() > 0)
					credenciales.put("rol", "ECL");
				else
					credenciales.put("rol", "---");
			}
		}
	}

	/**
	 * botones
	 * 
	 * @param request
	 */
	protected void setBotones(HttpServletRequest request)
	{
		for (Iterator it = this.btns.iterator(); it.hasNext();)
		{
			String lavelBtn = (String) it.next();
			request.setAttribute(lavelBtn, Constants.TXT_BTNS.getProperty(lavelBtn, "Boton sin valor!"));
		}
	}

	/**
	 * revisa menus
	 * 
	 * @param request
	 */
	protected void revisaMenus(HttpServletRequest request)
	{
		try
		{
			HttpSession session = request.getSession(true);
			Menus menus = (Menus) session.getAttribute(Constants.SESSION_KEY_MENUS);
			if (menus == null)
			{
				MenusConfig config = (MenusConfig) getServlet().getServletContext().getAttribute(MenusConfig.KEY_MENUS_CONFIG);
				menus = new Menus(config.getModulos(), this.administrador);

				session.setAttribute(Constants.SESSION_KEY_MENUS, menus);
			}
			logger.info("\nmenus cargados correctamente con " + menus);

			request.setAttribute("accion", this.accion);
			request.setAttribute("subAccion", this.subAccion);
			request.setAttribute("subSubAccion", this.subSubAccion);
			logger.info(new StringBuffer("accion:").append(this.accion).append(":subAccion:").append(this.subAccion).append(":req:").append(request.getParameter("subAccion")).append(":"));

			request.setAttribute("accion", this.accion);

			if (!this.subAccion.equals(""))
			{
				logger.info(new StringBuffer("subSubAccion encontrado:").append(this.subSubAccion).append(":"));
				llenaNavegacion(request, request.getParameter("limpiaPath"));

				Tab tabActivo = menus.buscarTab(this.accion, this.subAccion);
				if (tabActivo != null)
					request.setAttribute("activar", tabActivo.getActivar());// link a activar
				else
				{
					logger.debug(new StringBuffer("tab null:").append(this.accion).append(":").append(this.subSubAccion).append(":"));
					request.setAttribute("activar", "tab null");// link a activar
				}
				if (request.getParameter("destino") != null)
				{
					request.setAttribute("enlace", "accion=" + this.accion + "&subAccion=" + this.subAccion + "&subSubAccion=" + request.getParameter("destino"));
					logger.debug(new StringBuffer("destino:").append("accion=").append(this.accion).append("&subAccion=").append(this.subAccion).append("&subSubAccion=").append(
							request.getParameter("destino")).append(":"));
				}
				return;
			}
			this.subSubAccion = "";
			llenaNavegacion(request, "");
			request.setAttribute("activar", "");
		} catch (Exception e)
		{
			logger.error("Error en menu:", e);
		}
	}

	/**
	 * authorized
	 */
	protected boolean isAuthorized(User user, Activity activity) throws Exception
	{
		logger.info("AppAction:" + user + "::" + activity + "::");
		return true;
	}

	/**
	 * llena navegacion
	 * 
	 * @param request
	 * @param accion
	 * @param subAccion
	 * @param subSubAccion
	 * @param limpiaPath
	 */
	public void llenaNavegacion(HttpServletRequest request, String limpiaPath)
	{
		Vector lista = (Vector) request.getSession().getAttribute(Constants.SESSION_PATH_NAVEGACION);
		Vector newLista = new Vector();
		String url = request.getRequestURI() + "?" + (request.getAttribute("cambioParam") != null ? (String) request.getAttribute("cambioParam") : request.getQueryString());

		String clave = this.accion + "#" + this.subAccion + "#" + this.subSubAccion;
		logger.debug(":limpiaPath:" + limpiaPath + ":clave:" + clave + ":url:" + url + "::");
		if (limpiaPath == null)
		{
			if ((lista != null && !this.subAccion.equals("")))
			{
				logger.debug("lista inicial:" + lista.size() + ":");
				int count = -1;

				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					PathNavegacion path = (PathNavegacion) it.next();
					count++;
					logger.debug(":i:" + count + ":" + path + "::");
					if (path.getNombre().equals("") || path.getNombre().equals("Error"))
						continue;
					if (path.getClave().equals(clave))
					{
						path.setUrl(url);
						path.setActual(true);
						newLista.add(path);
						request.getSession().setAttribute(Constants.SESSION_PATH_NAVEGACION, newLista);
						request.setAttribute(Constants.SESSION_PATH_NAVEGACION, newLista);
						return;
					}
					path.setActual(false);
					if (count == lista.size() - 1 && request.getSession().getAttribute("nombre") != null)
					{
						String nombre = ((DirectStringAttribute) request.getSession().getAttribute("nombre")).toString();
						if (nombre == null)
							nombre = "XXX";
						path.setNombre(nombre);
					}
					boolean flag = false;
					for (Iterator it2 = newLista.iterator(); it2.hasNext();)
					{
						PathNavegacion p = (PathNavegacion) it2.next();
						if (path.getNombre().equals(p.getNombre()))
						{
							flag = true;
							newLista.remove(p);
							logger.debug("nombre igual:" + p.getNombre() + ":break:");
							break;
						}
					}
					if (flag)
						break;
					if (path.getNombre().equals("Inicio"))
						continue;
					newLista.add(path);
				}
			}
		} else
			logger.debug("\n\nlimpiando path navegacion!");

		PathNavegacion x = new PathNavegacion(this.subAccion, url, clave, true);
		logger.info("agregando:" + x + "::");
		newLista.add(x);
		request.getSession().setAttribute(Constants.SESSION_PATH_NAVEGACION, newLista);
		request.setAttribute(Constants.SESSION_PATH_NAVEGACION, newLista);
	}

}
