package cl.araucana.wslme.integration.jaxrpc.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ChangePrefix implements Filter {

	public void destroy() {
		// TODO Apéndice de método generado automáticamente

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

	    System.out.println("BEFORE filter");
	    
	    CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) resp);
	    chain.doFilter(req, responseWrapper);
	    String servletResponse = new String(responseWrapper.toString());
	    
	    PrintWriter out = resp.getWriter();
	    out.write(servletResponse.replaceAll("p111", "urn")); // Here you can change the response


	    System.out.println("AFTER filter, original response: " + servletResponse);
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Apéndice de método generado automáticamente

	}

}
