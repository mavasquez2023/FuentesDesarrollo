

package cl.araucana.cp.nominas.receiver;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.receipt.DesEncrypt;

import com.bh.talon.User;


public class AdaptedHttpServletRequestWrapper
		extends HttpServletRequestWrapper {

	private ServletContext servletContext;
	private byte[] content;
	private boolean inputOpened = false;
	private String pk;
	
	public AdaptedHttpServletRequestWrapper(ServletContext servletContext,
			HttpServletRequest request, byte[] content) {
		
		super(request);
		
		this.servletContext = servletContext;
		this.content = content;
	}
	
	public String getContentType() {
		return "application/zip";
	}
	
	public int getContentLength() {
		return content.length;
	}

	public String getHeader(String header) {
		if (!header.equals("Session-ID")) {
			return super.getHeader(header);
		}

		HttpSession session = getSession();
		String sessionID = session.getId();
		
		HashMap sessions = (HashMap) servletContext.getAttribute("sessions");
	
		synchronized (sessions) {
			sessions.put(sessionID, session);
		}
	
		return sessionID;
	}
	
	public BufferedReader getReader() throws IOException {
		
		throw new UnsupportedOperationException();
	}
	
	public ServletInputStream getInputStream() throws IOException {
		
		if (inputOpened) {
			throw new IOException("Input stream was opened previously");
		}
		
		ServletInputStream input = new ServletInputStream() {
			
				ByteArrayInputStream input = new ByteArrayInputStream(content);
				
				public int read() throws IOException {
					
					return input.read();
				}
				
				public void close() throws IOException {
					
					input.close();
				}			
		};
		
		inputOpened = true;
		
		return input;
	}
	
	public String getParameter(String name) {
		if (!name.equals("pk")) {
			return super.getParameter(name);
		}

		if (pk != null) {
			return pk;
		}
		
		HttpSession session = getSession();
		User user = (User) session.getAttribute("currentUser");
		String userID;
		
		if (user != null)  {
			PersonaVO persona = (PersonaVO) user.getUserReference();
			
			userID = Integer.toString(persona.getIdPersona().intValue());
		} else {
			userID = "??";
		}
		
		DesEncrypt cipher = new DesEncrypt();
		
		pk = cipher.codifica(userID, 30000);
		
		return pk;
	}
}
