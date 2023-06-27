package cl.araucana.cp.presentation.struts.actions.envioRecepcion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.exceptions.SesionException;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.DispatcherMgr;
import cl.araucana.cp.receipt.DesEncrypt;

import com.bh.talon.User;

/*
 * @(#) DispatcherAction.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.4
 */
public class DispatcherAction extends AppAction
{
	static Logger logger = Logger.getLogger(DispatcherAction.class);

	public static final String FORWARD_LOGIN = "login";
	public static final String FORWARD_ERROR = "error";
	public static final String WEB_CONTEXT_URL = "$$WEB_CONTEXT_URL$$";
	public static final String CODIGO_SESSION = "$$CODIGO_SESSION$$";
	public static final String HOST = "$$HOST$$";
	public static final String CODE_BASE = "$$CODE_BASE$$";
	public static final String DISPATCHER_RESOURCE = "/jnlp/dispatcher.txt";
	public boolean loggear = true;

	/**
	 * buffered reader
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BufferedReader reader = null;
		PrintWriter writer = null;
		try
		{
			if (user == null)
				throw new SesionException("sesion expirada");
			DesEncrypt encriptador = new DesEncrypt();
			String idPersona = "" + ((PersonaVO) user.getUserReference()).getIdPersona();
			logger.debug(HibernateUtil.getConfiguration().getProperties());
			logger.debug("idPersona:" + idPersona + "::");
			String latencia = getParametro(Constants.PARAM_TIEMPO_SESSION_CLIENTE);
			logger.debug("sessionCliente:" + latencia + "::");

			String codigo = encriptador.codifica(idPersona, Long.parseLong(latencia));
			logger.debug(codigo);

			// printParameters(request);
			String webContextURL = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			String host = "http://" + request.getServerName() + ":" + request.getServerPort() + "/";
			String codeBase = "codebase=\"http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "\"";

			InputStream input = getClass().getResourceAsStream(DISPATCHER_RESOURCE);
			reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"));
			String line;
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setContentType("application/x-java-jnlp-file");
			response.setHeader("Pragma", "no-cache");

			writer = response.getWriter();

			while ((line = reader.readLine()) != null)
			{
				if (line.indexOf(WEB_CONTEXT_URL) != -1)
					line = line.substring(0, line.indexOf("$$")) + webContextURL + line.substring(line.lastIndexOf("$$") + 2);
				else if (line.indexOf(CODE_BASE) != -1)
					line = line.substring(0, line.indexOf("$$")) + codeBase + line.substring(line.lastIndexOf("$$") + 2);
				else if (line.indexOf(CODIGO_SESSION) != -1)
					line = line.substring(0, line.indexOf("$$")) + codigo + line.substring(line.lastIndexOf("$$") + 2);
				else if (line.indexOf(HOST) != -1)
					line = line.substring(0, line.indexOf("$$")) + host + line.substring(line.lastIndexOf("$$") + 2);
				writer.println(line);
				logger.debug(line);
			}
			logger.debug("fin archivo");

			return null;
		} catch (SesionException e)
		{
			return mapping.findForward(FORWARD_LOGIN);
		} catch (Exception e)
		{
			logger.error("problemas acrga dispatcher", e);
			ActionMessages am = new ActionMessages();
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.cargaDispatcher"));
			this.saveMessages(request, am);
			return mapping.findForward(FORWARD_ERROR);
		} finally
		{
			try
			{
				if (reader != null)
					reader.close();
			} catch (IOException e)
			{
			}
			try
			{
				if (writer != null)
					writer.close();
			} catch (Exception e)
			{
			}
		}
	}

	/**
	 * parametro
	 * 
	 * @param nombreParametro
	 * @param idUser
	 * @return el valor del parametro indicado
	 */
	private String getParametro(int id)
	{
		try
		{
			DispatcherMgr dispatcherMgr = new DispatcherMgr(HibernateUtil.getSession());
			return dispatcherMgr.getParametro(id).getValor().trim();
		} catch (Exception e)
		{
			logger.error("Problemas al obtener parametro", e);
			return null;
		}
	}
}
