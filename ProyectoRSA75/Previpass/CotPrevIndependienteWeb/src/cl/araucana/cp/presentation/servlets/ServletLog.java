package cl.araucana.cp.presentation.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.UsuarioNoEncontradoException;
import cl.araucana.cp.hibernate.exceptions.InfrastructureException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.UsuarioCP;
import cl.araucana.cp.presentation.mgr.PersonaMgr;

import com.bh.talon.User;

/*
 * @(#) ParametroMgr.java 1.12 10/05/2009
 *
 * Este c&oacute;digo fuente pertenece a la Caja de Compensaci&oacute;n de Asignaci&oacute;n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci&oacute;n y reproducci&oacute;n es confidencial y est&aacute;
 * restringido a los sistemas de informaci&oacute;n propios de la instituci&oacute;n.
 */
/**
 * @author cchamblas
 * 
 * @version 1.12
 */
public class ServletLog extends HttpServlet
{
	private static final long serialVersionUID = -5355992395242209590L;
	private static final Logger logger = Logger.getLogger(ServletLog.class);
	public static final String PARAM_ERROR = "error";
	static public final String USER_BEAN = "currentUser";
	private Properties prop = new Properties();

	/**
	 * Autentifica un conjunto de credenciales. Si el usuario no hace login debe retornar null.
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
		} catch (UsuarioNoEncontradoException ex)
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
	 * post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Session session = HibernateUtil.getSession();
		String uLogin = request.getParameter("xxLogin");
		String uPassword = request.getParameter("xxPass");
		this.prop.load(getClass().getResourceAsStream("/files/mensajesLogin.properties"));
		if (uLogin != null && uPassword != null)
		{
			logger.info("ServletLog::userLogin:" + uLogin + "::");
			int pos = uLogin.indexOf('-');
			if (pos > 0)
				uLogin = uLogin.substring(0, pos);
			Map credentiales = new HashMap();
			credentiales.put("login", uLogin);

			try
			{
				UsuarioCP user = (UsuarioCP) authenticate(request, credentiales);
				if (user == null)
				{// Aca redireccionar a una pagina para indicar que el usuario esta en el registro de usuarios, pero no en la BD.
					request.getSession().invalidate();
					logger.info("usuario autenticado, pero no registrado en CPE, redireccionando a:araucana/login/usuarioNoEncontrado.jsp::");
					request.setAttribute("error", this.prop.get("noRegistrado"));
					RequestDispatcher rd = request.getRequestDispatcher("araucana/login/usuarioNoEncontrado.jsp");
					rd.forward(request, response);
					return;
				}
				PersonaMgr personaMgr = new PersonaMgr(session);
				AdministradorVO admin = personaMgr.getAdministrador(user.getIdPersona());
				EncargadoVO encargado = personaMgr.getEncargado(user.getIdPersona());
				logger.info("es admin?:" + (admin != null ? "si" : "no") + "::");
				logger.info("es admin habilitado?:" + (admin != null && admin.getHabilitado() == Constants.COD_HABILITACION_ADMIN) + ":valor recibido:" + (admin != null ? "" + admin.getHabilitado() : "nada") + "::");
				logger.info("es encargado?:" + (encargado != null ? "si" : "no") + "::");
				logger.info("es encargado habilitado?:" + (encargado != null && encargado.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO) + ":valor recibido:" + (encargado != null ? "" + encargado.getHabilitado() : "nada") + "::");
				boolean isAdminHabilitado = (admin != null && admin.getHabilitado() == Constants.COD_HABILITACION_ADMIN ? true : false);
				boolean isEncHabilitado = (encargado != null && encargado.getHabilitado() == Constants.COD_HABILITACION_ENCARGADO ? true : false);
				logger.info("isAdminHabilitado:" + isAdminHabilitado + ":isEncHabilitado:" + isEncHabilitado + "::");
				if (!isAdminHabilitado && !isEncHabilitado)
				{// no es admin o esta deshabilitado y no es encargado o esta deshabilitado
					request.getSession().invalidate();
					logger.info("usuario autenticado, pero no registrado en CPE, redireccionando a:araucana/login/usuarioNoEncontrado.jsp::");
					request.setAttribute("error", this.prop.get("userDesHabilitado"));
					RequestDispatcher rd = request.getRequestDispatcher("araucana/login/usuarioNoEncontrado.jsp");
					rd.forward(request, response);
					return;
				}
				boolean isAdminEmpHabilitado = personaMgr.isAdminEmpresa(uLogin);
				boolean isEncConvHabilitado = personaMgr.isEscargadoEmpresa(uLogin);
				boolean entraAppCliente = (isAdminEmpHabilitado || isEncConvHabilitado ? true : false);
				logger.info("isAdminEmpHabilitado:" + isAdminEmpHabilitado + ":isEncConvHabilitado:" + isEncConvHabilitado + ":entraAppCliente:" + entraAppCliente + "::");

				if (isAdminHabilitado && ((PersonaVO) user.getUserReference()).isAdminAraucana())
				{
					if (entraAppCliente)
						request.setAttribute("pregunta", "si");//pregunta si desviar a app cliente o entrar a app admin
					else
						request.setAttribute("pregunta", "");
					request.setAttribute("j_username", request.getParameter("xxLogin"));
					request.setAttribute("j_password", request.getParameter("xxPass"));
					RequestDispatcher rd = request.getRequestDispatcher("araucana/login/loginAdmin2.jsp");
					rd.forward(request, response);
					return;
				}

				if (!entraAppCliente)
				{
					request.getSession().invalidate();
					logger.info("usuario autenticado, pero no tiene ni empresas ni convenios habilitados::");
					request.setAttribute("error", this.prop.get("noEmpresa"));
					RequestDispatcher rd = request.getRequestDispatcher("araucana/login/usuarioNoEncontrado.jsp");
					rd.forward(request, response);
					return;
				}

			} catch (Exception e)
			{
				logger.info("problemas al buscar asignaciones a usuario", e);
				request.getSession().invalidate();
				request.setAttribute("error", this.prop.get("error"));
				RequestDispatcher rd = request.getRequestDispatcher("araucana/login/usuarioNoEncontrado.jsp");
				rd.forward(request, response);
				return;
			}
			
			request.setAttribute("j_username", request.getParameter("xxLogin"));
			request.setAttribute("j_password", request.getParameter("xxPass"));
			RequestDispatcher rd = request.getRequestDispatcher("araucana/login/loginCliente.jsp");
			rd.forward(request, response);
		}
	}
}
