

package cl.araucana.core.util.http;


import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.util.UserPrincipal;


public class LoginFilter implements Filter {

	public static final int SECURITY_MODE_J2EE = 0;
	public static final int SECURITY_MODE_PROGRAMMED = 1;
	public static final int SECURITY_MODE_NONE = 2;

	public static final String userPrincipalAttributeName = "userPrincipal";

	private String appName;
	private int securityMode;

	private boolean debug;
	private boolean dump;
	private boolean useRedirector;

	private String loginPage;
	private String loginErrorPage;

	public void init(FilterConfig filterConfig) throws ServletException {

		ServletContext servletContext = filterConfig.getServletContext();

		appName = servletContext.getServletContextName();

		String sDebug = filterConfig.getInitParameter("debug");

		debug = sDebug != null && sDebug.equals("true");

		String sDump = filterConfig.getInitParameter("dump");

		dump = sDump != null && sDump.equals("true");

		String sUseRedirector = filterConfig.getInitParameter("useRedirector");

		useRedirector = sUseRedirector != null && sUseRedirector.equals("true");

		String sSecurityMode = filterConfig.getInitParameter("securityMode");

		if (sSecurityMode == null) {
			throw new ServletException(
					"Missed 'securityMode' filter init parameter");
		}

		if (sSecurityMode.equals("j2ee")) {
			securityMode = SECURITY_MODE_J2EE;
		} else if (sSecurityMode.equals("programmed")) {
			loginPage = filterConfig.getInitParameter("loginPage");

			if (loginPage == null) {
				throw new ServletException(
						"Missed 'loginPage' filter init parameter");
			}

			loginErrorPage = filterConfig.getInitParameter("loginErrorPage");

			if (loginPage == null) {
				throw new ServletException(
						"Missed 'loginErrorPage' filter init parameter");
			}

			securityMode = SECURITY_MODE_PROGRAMMED;
		} else if (sSecurityMode.equals("none")) {
			securityMode = SECURITY_MODE_NONE;
		} else {
			throw new ServletException(
					"Unknown security mode '" + sSecurityMode + "'");
		}

		debug(">> init");
		debug("   securityMode   = " + sSecurityMode);
		debug("   dump request   = " + dump);
		debug("   use redirector = " + useRedirector);

		if (securityMode == SECURITY_MODE_PROGRAMMED) {
		debug("   loginPage      = " + loginPage);
		debug("   loginErrorPage = " + loginErrorPage);
		}

		debug("<< init");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		debug(">> doFilter");

		if (dump) {
			log("DUMP ORIGINAL REQUEST");
			Utils.dumpRequest((HttpServletRequest) request);
		}

		DecodedHttpServletRequest httpRequest =
				new DecodedHttpServletRequest(
						(HttpServletRequest) request, useJ2EESecurityMode());

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (dump) {
			log("DUMP DECODED REQUEST");
			Utils.dumpRequest(httpRequest);
		}

		Utils.checkSpecialParameters(httpRequest);

		/*
		 * Change Location header to hide special parameters.
		 */
		httpResponse.setHeader(
				"Location", httpRequest.getRequestURL().toString());

		if (securityMode == SECURITY_MODE_J2EE) {
			J2EESecurityMode(httpRequest, httpResponse, filterChain);
		} else if (securityMode == SECURITY_MODE_PROGRAMMED) {
			ProgrammedSecurityMode(httpRequest, httpResponse, filterChain);
		} else {
			NoneSecurityMode(httpRequest, httpResponse, filterChain);
		}

		debug("<< doFilter");
	}

	private void J2EESecurityMode(DecodedHttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
					throws IOException, ServletException {

		long ti = System.currentTimeMillis();

		filterChain.doFilter(request, response);

		long tf = System.currentTimeMillis();
		UserPrincipal userPrincipal = Utils.getUserPrincipal(request, true);

		request.untrack();

		debug(
				  "   login "
				+ "user = " + userPrincipal.getName() + " "
				+ "time = " + (tf - ti) + " ms");

		setPrincipal(request, userPrincipal);
	}

	private void ProgrammedSecurityMode(DecodedHttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
					throws IOException, ServletException {

		String principalName = getPrincipalName(request);

		if (principalName != null) {
			request.untrack();
			filterChain.doFilter(request, response);

			return;
		}

		/*
		 *  Forwards unauthenticated requests to login page.
		 */
		UserPrincipal userPrincipal = Utils.getUserPrincipal(request, false);

		request.untrack();

		if (userPrincipal.getName() == null
				|| userPrincipal.getPassword() == null) {

			RequestDispatcher dispatcher =
					request.getRequestDispatcher(loginPage);

			debug("forward login -> " + loginPage);

			dispatcher.forward(request, response);

			return;
		}

		long ti = System.currentTimeMillis();

		UserRegistryConnection connection = null;

		try {
			connection = new UserRegistryConnection(userPrincipal);
		} catch (Exception e) {	// Break dependency with UserRegistryException.
			debug("login failed. [" + e.getMessage() + "]");

			RequestDispatcher dispatcher =
					request.getRequestDispatcher(loginErrorPage);

			debug("forward login error -> " + loginErrorPage);

			dispatcher.forward(request, response);

			return;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception se) {}// Break dependency again.
			}
		}

		long tf = System.currentTimeMillis();

		debug(
				  "   login "
				+ "user= [" + userPrincipal.getName() + "] "
				+ "time= " + (tf - ti) + " ms ");


		setPrincipal(request, userPrincipal);
		filterChain.doFilter(request, response);
	}

	private void NoneSecurityMode(DecodedHttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
					throws IOException, ServletException {

		String principalName = getPrincipalName(request);

		if (principalName == null) {
			UserPrincipal userPrincipal =
					Utils.getUserPrincipal(request, false);

			if (userPrincipal != null) {
				setPrincipal(request, userPrincipal);
			}
		}

		request.untrack();
		filterChain.doFilter(request, response);
	}

	private void setPrincipal(HttpServletRequest request,
			UserPrincipal userPrincipal) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Principal principal = new HttpUserPrincipal(userPrincipal.getName());

		session.setAttribute(userPrincipalAttributeName, principal);

		/*
		 * Credentials injection to HTTP redirector.
		 */
		if (useRedirector) {
			Router.inject(request, userPrincipal);
		}
	}

	private String getPrincipalName(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		Principal principal =
				(Principal) session.getAttribute(userPrincipalAttributeName);

		return (principal == null) ? null : principal.getName();
	}

	public void destroy() {
	}

	private boolean useJ2EESecurityMode() {
		return securityMode == SECURITY_MODE_J2EE;
	}

	private void debug(String message) {
		if (debug) {
			log(message);
		}
	}

	private void log(String message) {
		System.out.println(appName + ".LoginFilter: " + message);
	}
}
