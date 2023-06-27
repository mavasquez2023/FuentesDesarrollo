/*
 * Creado el 05-09-2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package publicacion.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;

import publicacion.BitacoraTO;
import publicacion.dao.BitacoraPubDAO;
import publicacion.dao.PubDAOFactory;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SaveParamFilter implements Filter {

	private transient FilterConfig filterConfig = null;
	private PubDAOFactory pubdao= null;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		MyHttpServletRequestWrapper var = new MyHttpServletRequestWrapper(
				(HttpServletRequest) req);
		try {
			pubdao= new PubDAOFactory();
			BitacoraTO filtro= new BitacoraTO();
			if (var.getMethod().equals("POST") || var.getMethod().equals("GET")) {
				HttpSession session = var.getSession();
				//Seteando holding desde session
				Object holding = session.getAttribute("holding");
				if(holding==null){
					holding="";
				}
				filtro.setHolding(holding.toString());
				//Seteando Periodo desde session
				String periodo = session.getAttribute("periodo").toString();
				filtro.setPeriodo(periodo);
				//Seteando Usuario desde session
				String usuario = session.getAttribute("usuario").toString();
				filtro.setUsuario(usuario);
				//Seteando Usuario desde request
				String ip=req.getRemoteAddr();
				filtro.setIp(ip);
				
				//Steando el nombre del tipo Documento
				String tipodoc= var.getParameter("_folder");
				String intipo= var.getParameter("inTipo");
				if(intipo!=null){
					tipodoc=getTipoPlanilla(intipo);
				}
				if(tipodoc==null){
					tipodoc= session.getAttribute("folder").toString();
				}
				if(tipodoc.indexOf(':')>0){
					tipodoc= tipodoc.substring(tipodoc.indexOf(':')+1);
				}
				session.setAttribute("folder", tipodoc);
				filtro.setTipo_documento(tipodoc);
				
				//Seteando el rango de periodos de búsqueda
				String fechaProceso= var.getParameter("FechaProceso");
				String fechaProceso2= var.getParameter("FechaProceso2");
				if(fechaProceso!=null && fechaProceso2!=null && !fechaProceso2.equals(fechaProceso)){
					fechaProceso+= " " + fechaProceso2;
				}
				filtro.setRango(fechaProceso);
				
				//Seteando la accion
				String accion= var.getParameter("_accion");
				if(accion==null){
					String cmd= var.getParameter("cmd");
					String code= var.getParameter("code");
					if(cmd != null || code != null){
						accion="Descargar";
					}
				}
				//En Descargar Comprobante Pago no rescata Buffer de request, se setea por default el valor Descargar 
				if(accion==null){
					accion="Descargar";
				}
				filtro.setAccion(accion);
			}
			//insertando la bitacora en BD
			BitacoraPubDAO bitacora= new BitacoraPubDAO(pubdao.getConnection());
			bitacora.insert(filtro);
			
			
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		finally{
			//cerrando conexiones Db2
			if(pubdao!=null){
				pubdao.closeConnectionDAO();
			}
			//encadenando filtro
			chain.doFilter(var, res);
		}
	}
	
	public String getTipoPlanilla(String intipo){
		String tipodoc="";
		if(intipo.equals("1")){
			tipodoc="Planillas AFP";
		}else if(intipo.equals("2")){
			tipodoc="Planillas APV";
		}else if(intipo.equals("3")){
			tipodoc="Planillas Cajas";
		}else if(intipo.equals("4")){
			tipodoc="Planillas INP";
		}else if(intipo.equals("5")){
			tipodoc="Planillas Isapres";
		}else if(intipo.equals("6")){
			tipodoc="Planillas Mutuales";
		}else if(intipo.equals("11")){
			tipodoc="Planillas AFPs TP";
		}else if(intipo.equals("16")){
			tipodoc="Planillas AFPs SIL";
		}
		return tipodoc;
	}
	
	class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

		private HttpServletRequest req;

		private MyServletInputStream is;

		private Map names = new HashMap();

		public MyHttpServletRequestWrapper(HttpServletRequest req)
				throws IOException {
			super(req);

			this.req = req;
			is = new MyServletInputStream(req.getInputStream());

			String parameters = getParameters();
			String[] pairs = parameters.split("\\&");

			// System.out.println(">> " + parameters + " npairs=" +
			// parameters.length());

			for (int i = 0; i < pairs.length; i++) {
				String[] pair = pairs[i].split("=");
				String valor="";
				if(pair.length>1){
					valor = pair[1].replaceAll("\\%2C", ",");
					valor = valor.replaceAll("\\%3A", ":");
				}
				// System.out.println(">> pair : " + pair[0] + " : " + pair[1]);
				names.put(pair[0], valor);
			}
		}

		public String getParameters() {
			String s = "";
			try {
				s=new String(is.getBuffer());
			} catch (Exception e) {
				System.out.println(">>No BUFFER en Request");
			}

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

			// System.out.println("buffer: [" + new String(buffer, 0, readed) +
			// "]");

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

		public int readLine(byte[] b, int offset, int length)
				throws IOException {
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

			// System.out.println("line = [" + new String(b, ooffset, ilength) +
			// "]");

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
