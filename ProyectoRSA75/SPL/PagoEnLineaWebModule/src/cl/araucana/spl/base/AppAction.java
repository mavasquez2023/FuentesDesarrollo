package cl.araucana.spl.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.util.Renderer;

import com.bh.talon.Activity;
import com.bh.talon.User;
import com.bh.talon.actions.BaseAction;

public abstract class AppAction extends BaseAction {
	public static final ResourceBundle resourceApp = ResourceBundle.getBundle("cl.araucana.spl.resources.ApplicationResources");
	private static final Logger logger = Logger.getLogger(AppAction.class);
	
	protected User authenticate(Map credentials) throws Exception {
		return null;
	}

	protected boolean isAuthorized(User user, Activity activity) throws Exception {
		return true;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getRemoteUser();
		if (username == null) {
			// TODO: Revisar esto [[SEGURIDAD]]
			username = "admin";
		}
		UsuarioWeb usuarioWeb = new UsuarioWeb(username, username);
		if (logger.isDebugEnabled()) {
			logger.debug("Usuario autentificado: " + usuarioWeb);
		}
		request.getSession().setAttribute(USER_BEAN, usuarioWeb);

		return super.perform(mapping, form, request, response);
	}
	
	protected void redirect(HttpServletResponse resp, String url) throws IOException {
		resp.sendRedirect(url);
	}
	
	protected void plainResponse(HttpServletResponse resp, String msg) throws IOException {
		resp.setContentType("text/plain");
		PrintWriter pw = resp.getWriter();
		String fecha = new Renderer().formatDatetime(new Date());
		pw.println(fecha + ": " + msg);
	}
	
	protected String getRootContext(HttpServletRequest req) {
		return req.getContextPath().substring(1);
	}
	
	/*************************/
	//Funcionalidades gili
	protected String getContenidoLlamada(HttpServletRequest req) throws IOException {
		logger.debug("Character-encoding:"+req.getCharacterEncoding());
		logger.debug("Content-type:"+req.getContentType());
		logger.debug("Content-length informado:"+req.getContentLength());
		
		logger.debug("getMethod() informado:"+req.getMethod());

		StringBuffer sb = new StringBuffer();

		ServletInputStream is = req.getInputStream();
		int length = 512;
		int offset = 0;
		int readedBytes = 0;
		byte[] bytes = new byte[length];
		while ((readedBytes = is.read(bytes)) != -1) {
			sb.append(new String(bytes, 0, readedBytes));
			offset += readedBytes;
		}
		logger.debug("Content-length efectivo:"+offset);

		return sb.toString();
	}
	
	protected String parseParameter(String paramName, String unparsed)  throws UnsupportedEncodingException  {
		String token = null;
		StringTokenizer st0 = new StringTokenizer(unparsed, "&");
		Renderer render = new Renderer();
		String par = null;
		String nombre = null;
		String valor = null;
		try {
			while (st0.hasMoreTokens()) {
				par = st0.nextToken();
				StringTokenizer st = new StringTokenizer(par, "=");
				nombre = st.nextToken();
				if (paramName.equals(nombre)) {
					valor = st.nextToken();
					token = render.decodeURL(valor);
					break;			
				}
			}
		} catch (NoSuchElementException nse) {
			logger.debug("Exception "+nse.getMessage());
		}
		return token;
	}
	
	protected void sendResponse(String respuesta, HttpServletResponse res) throws IOException {
		res.setContentLength(respuesta.getBytes().length);
		PrintWriter writer = res.getWriter();
		writer.print(respuesta);
		writer.flush();
		writer.close();
	}
	
	
	
}
