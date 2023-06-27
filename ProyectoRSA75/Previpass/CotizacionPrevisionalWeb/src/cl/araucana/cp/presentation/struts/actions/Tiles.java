package cl.araucana.cp.presentation.struts.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.UsuarioNoEncontradoException;
import cl.araucana.cp.hibernate.exceptions.InfrastructureException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.PersonaMgr;

import com.bh.talon.User;

/*
 * @(#) Tiles.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.8
 */
public class Tiles extends AppAction
{
	public static final String PARAM_FORWARD = "forward";

	static Logger logger = Logger.getLogger(Tiles.class);
	static public final String USER_ADMIN_CLIENTE = "loginAdmin";

	/**
	 * Autentifica un conjunto de credenciales. Si el usuario no hace login debe
	 * retornar null.
	 * 
	 * @param request
	 * @param credentials
	 * @return
	 * @throws DaoException
	 */
	protected User authenticate(HttpServletRequest request, Map credentials)
	{
		try
		{
			User user = new UsuarioCP(credentials, HibernateUtil.getSession());
			if (user != null)
			{
				logger.debug("doLogin exitoso para:" + user.getName() + "::");
				request.getSession(true).setAttribute(USER_BEAN, user);
			} else
				logger.info("doLogin fallido para:" + credentials.toString() + "::");
			return user;
		} catch (UsuarioNoEncontradoException e)
		{
			return null;
		} catch (InfrastructureException e)
		{
			return null;
		} catch (DaoException e)
		{
			return null;
		}
	}

	/**
	 * Si encuentra subSubAccion (equivalente a subTab), gatilla esa accion, y
	 * busca el tab (equivalente a subAccion) para determinar cual submenu
	 * pintar.
	 */
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		try
		{
			Session session = HibernateUtil.getSession();
			String uLogin = usuario.getLogin();

			logger.info("entrando a tiles:" + uLogin + "::");
			Map credentiales = new HashMap();
			credentiales.put("login", uLogin);

			UsuarioCP user = (UsuarioCP) authenticate(request, credentiales);
			if (user == null)
			{// Aca redireccionar a una pagina para indicar que el usuario
				// esta en el registro de usuarios, pero no en la BD.
				request.getSession().invalidate();
				logger.info("usuario autenticado, pero no registrado en CPE, redireccionando a:araucana/login/usuarioNoEncontrado.jsp::");
				request.setAttribute("error", "noRegistrado");
				request.getSession().setAttribute("cierraTodo", "si");
				return mapping.findForward(USER_ADMIN_CLIENTE);
			}
			try
			{
				PersonaMgr personaMgr = new PersonaMgr(session);
				AdministradorVO admin = personaMgr.getAdministrador(user.getIdPersona());
				EncargadoVO encargado = personaMgr.getEncargado(user.getIdPersona());
				logger.info("es admin?:" + (admin != null ? "si" : "no") + "::");
				logger.info("es admin habilitado?:" + (admin != null && admin.getHabilitado() == Constants.COD_HABILITACION_ADMIN)
						+ ":valor recibido:" + (admin != null ? "" + admin.getHabilitado() : "nada") + "::");
				logger.info("es encargado?:" + (encargado != null ? "si" : "no") + "::");
				logger.info("es encargado habilitado?:" + (encargado != null && encargado.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO)
						+ ":valor recibido:" + (encargado != null ? "" + encargado.getHabilitado() : "nada") + "::");
				boolean isAdminHabilitado = (admin != null && admin.getHabilitado() == Constants.COD_HABILITACION_ADMIN ? true : false);
				boolean isEncHabilitado = (encargado != null && encargado.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO ? true : false);
				logger.info("isAdminHabilitado:" + isAdminHabilitado + ":isEncHabilitado:" + isEncHabilitado + "::");

				boolean isAdminCaja = personaMgr.isAdministradorCaja(user.getIdPersona());
				if (isAdminCaja)
				{
					request.getSession().setAttribute("isAdminCaja", "si");
				} else
				{
					request.getSession().setAttribute("isAdminCaja", "");
				}
				
				if (!isAdminHabilitado && !isEncHabilitado)
				{// no es admin o esta deshabilitado y no es encargado o esta
					// deshabilitado
					request.getSession().invalidate();
					//Se vuelve a preguntar si es AdminCCAF ya que el invalidate elimina el atributo "isAdminCaja"
					if (isAdminCaja) {
						request.getSession().setAttribute("isAdminCaja", "si");
					}
					logger.info("usuario autenticado, pero no registrado en CPE, redireccionando a:araucana/login/usuarioNoEncontrado.jsp::");
					request.setAttribute("error", "userDesHabilitado");
					request.getSession().setAttribute("cierraTodo", "si");
					return mapping.findForward(USER_ADMIN_CLIENTE);
				}
				boolean isAdminEmpHabilitado = personaMgr.isAdminEmpresa(uLogin);
				boolean isEncConvHabilitado = personaMgr.isEscargadoEmpresa(uLogin);

				boolean entraAppCliente = (isAdminEmpHabilitado || isEncConvHabilitado ? true : false);
				logger.info("isAdminEmpHabilitado:" + isAdminEmpHabilitado + ":isEncConvHabilitado:" + isEncConvHabilitado + ":isAdminCaja:"
						+ isAdminCaja + ":entraAppCliente:" + entraAppCliente + "::");

				if (isAdminHabilitado && ((PersonaVO) user.getUserReference()).isAdminAraucana())
				{
					if (entraAppCliente)
						request.getSession().setAttribute("clAdmin", "si");
					else
						request.getSession().setAttribute("clAdmin", "");
					this.subAccion = "login";
					return mapping.findForward(USER_ADMIN_CLIENTE);
				}

				if (!entraAppCliente)
				{
					request.getSession().invalidate();
					logger.info("usuario autenticado, pero no tiene ni empresas ni convenios habilitados::");
					request.setAttribute("error", "noEmpresa");
					request.getSession().setAttribute("cierraTodo", "si");
					return mapping.findForward(USER_ADMIN_CLIENTE);
				}
				
			} catch (Exception e)
			{
				logger.error("problemas al buscar asignaciones a usuario", e);
				request.getSession().invalidate();
				request.setAttribute("error", "error");
				request.getSession().setAttribute("cierraTodo", "si");
				return mapping.findForward(USER_ADMIN_CLIENTE);
			}
			logger.info("saliendo de tiles hacia inicio");
			request.getSession().setAttribute("cierraAdmin", "si");
			return mapping.findForward(USER_ADMIN_CLIENTE);
		} catch (Exception e)
		{
			logger.error("ERROR en tiles:", e);
			request.setAttribute("listaNominas", new Vector());
			return mapping.findForward(Constants.ACCION_DEFAULT);
		}
	}
}