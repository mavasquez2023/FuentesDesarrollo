package utilPub;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.*;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;


import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileReader;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class UtilPub {

	public static int ContarPDF(AS400 system, String fileIndice)
			throws AS400SecurityException, IOException {
		IFSFile file = new IFSFile(system, fileIndice);
		BufferedReader reader;
		int lineas = 0;
		if (file.exists()) {
			reader = new BufferedReader(new IFSFileReader(file));
			while (reader.readLine() != null) {
				++lineas;
			}
			reader.close();
		}
		return lineas;
	}

	public static String[] split(String s, String delimiter) {
		List tokens = new ArrayList();
		int fromIndex = 0;
		int index;
		int length = s.length();
		int delimiterLength = delimiter.length();

		while (fromIndex < length) {
			index = s.indexOf(delimiter, fromIndex);
			if (index < 0) {
				tokens.add(s.substring(fromIndex));
				break;
			}
			tokens.add(s.substring(fromIndex, index));
			fromIndex = index + delimiterLength;
		}
		return (String[]) tokens.toArray(new String[0]);
	}

	public static void RespuestaContentType(HttpServletResponse objResponse,
			String FileName, String ContentType) {
		byte[] buffer = new byte[1024];
		int bytesRead;
		int intLargo;
		File inFile;
		FileInputStream file = null;
		OutputStream out = null;

		try {
			FileName = FileName + "." + ContentType;
			inFile = new File(FileName);
			intLargo = (int) inFile.length();

			objResponse.setContentType("application/" + ContentType);
			objResponse.setHeader("Content-disposition", "inline; filename="
					+ inFile.getName());
			objResponse.setContentLength(intLargo);

			file = new FileInputStream(inFile);
			out = objResponse.getOutputStream();

			while ((bytesRead = file.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			MensajeErrorHTML(objResponse, out, e);
		} catch (IOException e) {
			// ...
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ioe) {
				}
			}
			if (file != null) {
				try {
					file.close();
				} catch (IOException ioe) {
				}
			}
		}
	}

	public static void MensajeErrorHTML(HttpServletResponse objResponse,
			OutputStream out, Exception e) {
		PrintWriter writer = null;
		if (out == null) {
			MensajeErrorHTML(objResponse, writer, e);
		} else {
			writer = new PrintWriter(new OutputStreamWriter(out));
			MensajeHTML(writer, e, "Error", "");
		}
	}

	public static PrintWriter MensajeErrorHTML(HttpServletResponse objResponse,
			PrintWriter writer, Exception e) {
		if (writer == null) {
			try {
				writer = objResponse.getWriter();
			} catch (IOException e1) {
				System.out
						.println("Util:MensajeErrorHTML) Error (IOException): "
								+ e.getMessage());
			}
		}
		
		MensajeHTML(writer, e, "Error", "");
		return writer;
	}

	public static PrintWriter MensajeInfoHTML(HttpServletResponse objResponse,
			PrintWriter writer, String msg) {
		objResponse.setContentType("text/html; charset=UTF-8");
		objResponse.setHeader("Content-Type", "text/html");
		if (writer == null) {
			try {
				writer = objResponse.getWriter();
			} catch (IOException e1) {
				System.out
						.println("Util:MensajeErrorHTML) Error (IOException): "
								+ e1.getMessage());
			}
		}
		MensajeHTML(writer, null, "Información", msg);
		return writer;
	}

	private static void MensajeHTML(PrintWriter writer, Exception e,
			String TipoMensaje, String msg) {
		String message;

		if (e == null) {
			message = msg;
		} else {
			message = e.getClass().getName() + ": " + e.getMessage();
		}

		writer.println("<html>");
		writer.println("<head><title>" + TipoMensaje + "</title></head>");
		//writer.println("<body>");
		writer
				.println("<body bgcolor=#FFFFFF background=\"./Images/bk_isotipo.gif\">");
		writer
				.println("<p><font color=#0066CC size=\"2\" face=Arial, Helvetica, sans-serif>&nbsp;<strong>"
						+ message + "</strong></font></p>");
		writer.println("</body></html>");
		writer.close();
	}
	
	/**
	 * Similar to <code>String.replaceAll</code>, but considering
	 * <code>_JSCode</code> as a String with Javascript and HTML code(i.e:
	 * '\\r' is not taken as a Java regex for instance)
	 * 
	 * @param _JSCode
	 *            the original code to search for matches and replace
	 * @param regex
	 *            String to be found and matched
	 * @param replacement
	 *            String must be put instead of <code>regex</code>
	 * @return String with the replaces. Original <code>_JSCode</code> if its
	 *         null or length == 0
	 */
	public static String replaceAllInJS(String _JSCode, String regex,
			String replacement) {

		if (_JSCode == null || _JSCode.trim().length() == 0) {
			return _JSCode;
		}

		int _JSCodeLength = _JSCode.length();
		int regexLength = regex.length();
		int index = _JSCode.indexOf(regex.toString());

		if (index != -1) {
			_JSCode = _JSCode.substring(0, index) + replacement.toString()
					+ _JSCode.substring(index + regexLength, _JSCodeLength);
		}
		return _JSCode;

	}
	
    /**
	 * Returns the Rut number only from a String with a full Rut with digital
	 * number in the form <i>"xxxxxxx-y"</i>.<br>
	 * Where <i>"xxxxxxxx"</i> is the Rut number and <i>"y"</i> is the Rut
	 * digit number
	 * 
	 * @param rutWithDV
	 *            String with a full Rut in the form <i>"xxxxxxxx-y"</i>
	 * @return String with the Rut number only in the form <i>"xxxxxxxx"</i>.
	 *         <code>null</code> in case of error or malformed rut
	 */
	public static String getRutOnly(String rutWithDV) {
		
		if(rutWithDV != null && rutWithDV.trim().length() == 0) {
			return null;
		}
		
		int guionIndex = rutWithDV.indexOf("-");
		
		if(guionIndex != -1) {
			return rutWithDV.substring(0, guionIndex);
		}
		return null;
	}
	
    public static String getHTML(String html_template, String SPath) {
		String archivo_in = "";
		File SFile;

		try {
			SPath = SPath.substring(0, SPath.lastIndexOf("\\") + 1);
			SPath = SPath.replace('\\', '/');
			SFile = new File(SPath + html_template);
			FileReader file_reader = new FileReader(SFile);
			BufferedReader br_reader = new BufferedReader(file_reader);

			String line = br_reader.readLine();

			while (line != null) {
				archivo_in = archivo_in + line + "\n";
				line = br_reader.readLine();
			}

		} catch (IOException e) {
			return e.toString();
		}

		return archivo_in;
	}
    
    
    public Connection getConnection() throws NamingException, SQLException
    {
	     try
	     {  
	           Hashtable env = new Hashtable();
		       env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
		       javax.naming.Context ctx=new InitialContext(env);
		    
		 
		       DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/cppub");
		       
		       ctx.close();
		       return ds.getConnection();
		  
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		        
		 return null;
    }
    
    public Statement getConnectionScroll() throws NamingException, SQLException
    {
    	Connection con = null;
    	Statement stmt = null;
    	Context ctx = new InitialContext();
    	String DB2Server = (String) ctx.lookup("java:comp/env/DB2Server");
    	String AS400Usuario = (String) ctx.lookup("java:comp/env/AS400Usuario");
    	String AS400Password = (String) ctx.lookup("java:comp/env/AS400Password");

		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			con = DriverManager.getConnection(DB2Server, AS400Usuario, AS400Password);
		} catch (ClassNotFoundException e) {
			System.out.println("(SincronizaProcesos) Error (ClassNotFoundException): " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("(SincronizaProcesos) Error (SQLException): " + e.getMessage());
		}
		
        // GET CONNECTION
		SQLWarning warning = con.getWarnings();
		while (warning != null) {     
			System.out.println("SQLState: " + warning.getSQLState());     
			System.out.println("Message: " + warning.getMessage());     
			warning = warning.getNextWarning();
		}
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        return stmt;
    }
    
    public String rellenaStringConEspacios(String texto, int cantidad) {
    	
    	if (null == texto) {
			texto = "";
		}
    	String s = StringUtils.rightPad(texto.trim(), cantidad, " ");
		return s;
	}
    
    public String rellenaNumerosConCeros(String texto, int cantidad) {
    	if (null == texto) {
			texto = "0";
		}
    	String s= StringUtils.leftPad(texto.trim(), cantidad, "0");
		return s;
	}

	public String formatoFecha(String fecha) {
		//  20130102
		String fechaFormateada = "";
		try {
			String dia = fecha.substring(6, 8);
			String mes = fecha.substring(4, 6);
			String año = fecha.substring(0, 4);
			fechaFormateada = dia + "/" + mes + "/" + año;
		} catch (Exception e) {
			fechaFormateada = "";
		}
		
		return fechaFormateada;
	}
	
	public Object validaNULL(Object object) {
		if(null != object)
		{
			return object;
		}
		else
		{
			return "";
		}
	}

	public Object validaNULLNUM(Object object) {
		if(null != object)
		{
			return object;
		}
		else
		{
			return "0";
		}
	}

}