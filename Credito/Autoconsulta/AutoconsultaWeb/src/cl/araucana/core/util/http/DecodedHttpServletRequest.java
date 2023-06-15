

package cl.araucana.core.util.http;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import cl.araucana.core.util.UserPrincipal;


public class DecodedHttpServletRequest extends HttpServletRequestWrapper {

	private HttpServletRequest httpServletRequest;
	private boolean useJ2EESpec;
	private UserPrincipal userPrincipal;
	private Map parameters;

	public DecodedHttpServletRequest(HttpServletRequest httpServletRequest) {
		this(httpServletRequest, true);
	}

	public DecodedHttpServletRequest(HttpServletRequest httpServletRequest,
			boolean useJ2EESpec) {

		super(httpServletRequest);

		this.httpServletRequest = httpServletRequest;
		this.useJ2EESpec = useJ2EESpec;

		parameters = new HashMap();

		Map originalParameterMap = httpServletRequest.getParameterMap();
		Iterator keyNames = originalParameterMap.keySet().iterator();

		while (keyNames.hasNext()) {
			String keyName = (String) keyNames.next();
			String[] keyValues = httpServletRequest.getParameterValues(keyName);

			parameters.put(keyName, keyValues);
		}

		String userCredentials = httpServletRequest.getParameter("uc");

		if (userCredentials != null) {
			userPrincipal = UserPrincipal.decodeUserCredentials(userCredentials);
			
			if (userPrincipal== null){
				userPrincipal = UserPrincipal.decodeUserCredentials_old(userCredentials);
			}
			if (userPrincipal != null) {

				/*
				 * Credentials injection.
				 */
				parameters.put(
						igetParameter("username"),
						new String[] { userPrincipal.getName() });

				parameters.put(
						igetParameter("password"),
						new String[] { userPrincipal.getPassword() });

				HttpSession session = getSession();

				if (session != null) {
					session.setAttribute("uc.injected", new Boolean(true));
				}
			}

			parameters.remove("uc");
		}
	}

	private String igetParameter(String name) {
		if (name.equals("username")) {
			return (useJ2EESpec) ? "j_username" : "username";
		}

		if (name.equals("password")) {
			return (useJ2EESpec) ? "j_password" : "password";
		}

		throw new IllegalArgumentException("unknown parameter '" + name + "'");
	}

	public void untrack() {
		parameters.remove(igetParameter("username"));
		parameters.remove(igetParameter("password"));
	}

	public String getMethod() {
		return "POST";
	}

	public String getQueryString() {
		return null;
	}

	public ServletInputStream getInputStream() throws IOException {

		if (httpServletRequest.getMethod().equals("GET")) {
			return
					new StringServletInputStream(
							httpServletRequest.getQueryString());
		}

		return httpServletRequest.getInputStream();
	}

	public String getParameter(String name) {
		String[] values = getParameterValues(name);

		return (values != null) ? values[0] : null;
	}

	public Enumeration getParameterNames() {
		return new ParameterNamesEnumeration();
	}

	public String[] getParameterValues(String name) {
		return (String[]) parameters.get(name);
	}

	public Map getParameterMap() {
		return parameters;
	}

	public BufferedReader getReader() throws IOException {

		if (httpServletRequest.getMethod().equals("GET")) {
			return
					new BufferedReader(
							new StringReader(
									httpServletRequest.getQueryString()));
		}

		return httpServletRequest.getReader();
	}

	public String getRemoteUser() {
		if (useJ2EESpec) {
			return httpServletRequest.getRemoteUser();
		}

		Principal principal = getUserPrincipal();

		return (principal == null) ? null : principal.getName();
	}

	public Principal getUserPrincipal() {
		if (useJ2EESpec) {
			return httpServletRequest.getUserPrincipal();
		}

		return new InternalPrincipal();
	}

	private class InternalPrincipal implements Principal {

		public String getName() {
			return (userPrincipal == null) ? null : userPrincipal.getName();
		}

		public String toString() {
			return getName();
		}
	}

	private class ParameterNamesEnumeration implements Enumeration {

		private Iterator iterator;

		private ParameterNamesEnumeration() {
			iterator = parameters.keySet().iterator();
		}

		public boolean hasMoreElements() {
			return iterator.hasNext();
		}

		public Object nextElement() {
			return iterator.next();
		}
	}
}
