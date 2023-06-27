/*
 * Creado el 06-03-2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package utilPub;

/**
 */
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class OnDemandFilter implements Filter {

	private transient FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		MyHttpServletRequestWrapper var = new MyHttpServletRequestWrapper((HttpServletRequest) req);
		String uri = var.getRequestURI();
		String address = var.getRemoteAddr();
		String parameters = "";
		
		if (var.getMethod().equals("POST")) {
			parameters = var.getParameters();
		}
		
		System.out.println(">> OnDemand ... [remoteAddr=" + address + "] - [URL=" + uri + "] -->" + parameters);
		chain.doFilter(var, res);
		System.out.println("<< OnDemand ... [remoteAddr=" + address + "] - [URL=" + uri + "] -->" + parameters);
	}
	
	class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
		
		private HttpServletRequest req;
		private MyServletInputStream is;
		private Map names = new HashMap();
		
		public MyHttpServletRequestWrapper(HttpServletRequest req) throws IOException {
			super(req);

			this.req = req;
			is = new MyServletInputStream(req.getInputStream());			
		}
		
		public String getParameters() {
			String s = new String(is.getBuffer());
			
			return s.replace('+', ' ');
		}
		
		public Enumeration getParameterNames() {
			// System.out.println("call getParameterNames()");
			
			return new MyEnumeration(names.keySet());
		}
		
		public String getParameter(String name) {
			// System.out.println("call getParameter(" + name + ")");
			
			return (String) names.get(name);
		}
		
		public String[] getParameterValues(String name) {
			String value = getParameter(name);
			
			if (value != null) {
				return new String[] { value };
			}
			
			return null;
		}
		
		public ServletInputStream getInputStream() throws IOException {
			// System.out.println("call getInputStream()");
			
			return is;
		}
	}
	
	class MyEnumeration implements Enumeration {
		
		Iterator iterator;
		
		public MyEnumeration(Set keys) {
			iterator = keys.iterator();
		}
		
		public boolean hasMoreElements() {
			return iterator.hasNext();
		}
		
		public Object nextElement() {
			return iterator.next();
		}
	}
	
	class MyServletInputStream extends ServletInputStream {
		
		private ByteArrayInputStream ba;
		private byte[] buffer = new byte[8192];
		private int readed = 0;
				
		public MyServletInputStream(ServletInputStream is) throws IOException {
			readed = is.read(buffer);
			
			// System.out.println("buffer: [" + new String(buffer, 0, readed) + "]");
			
			ba = new ByteArrayInputStream(buffer, 0, readed);
		}
		
		public byte[] getBuffer() {
			byte[] b = new byte[readed];
			
			System.arraycopy(buffer, 0, b, 0, b.length);
			 
			return b;
		}
		
		public int read() throws IOException {
			return ba.read();
		}
		
		public int read(byte[] b) throws IOException {
			return ba.read(b);
		}
		
		public int read(byte[] b, int offset, int length) throws IOException {
			return ba.read(b, offset, length);
		}

		public int readLine(byte[] b, int offset, int length) throws IOException {
			// System.out.println("call readLine()");
			
			int ch;
			int ilength = 0;
			int ooffset = offset;
			
			while ((ch = ba.read()) != -1 && ch != '\n' && ilength++ < length) {
				b[offset++] = (byte) ch;
			}
						
			if (ch == -1 && ilength == 0) {
				return -1;
			}

			// System.out.println("line = [" + new String(b, ooffset, ilength) + "]");
						
			return ilength;
		}
		
		public long skip(long n) throws IOException {
			return ba.skip(n);
		}
		
		public int available() throws IOException {
			return ba.available();
		}
		
		public void close() throws IOException {
			ba.close();
		}
		
		public void mark(int readlimit) {
			ba.mark(readlimit);
		}
		
		public void reset() throws IOException {
			ba.reset();
		}
		
		public boolean markSupported() {
			return ba.markSupported();
		}
	}
}
