package cl.araucana.tesoreria.util;
 
import java.io.File;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.Resources;


public class WebUtils {
	
	public static String FORMAT_NUMBER = "#";
	
	public static String FORMAT_DATE = "dd/MM/yyyy";
	
	static Logger logger = Logger.getLogger( WebUtils.class );
	
	public static String getCleanData(String data) {
		return data == null ? null : ( data.trim().equals("") ? null : data.trim());
	}
	
	public static Date getDate(String data) throws Exception {
		String cleanData = WebUtils.getCleanData(data);
		if ( cleanData == null )
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		return sdf.parse( cleanData );
	}
	
	public static String getDate(Date data) throws Exception {
		if ( data == null )
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		return sdf.format(data);
	}	
	
	public static String getRealRut(String data) {
		if ( data == null )
			return null;
		String cuerpo = getCuerpoRut( data );
		String digito = getDigitoRut( data );
		return cuerpo + "-" + digito;
	}	


	public static void setError(HttpServletRequest request, String key) throws Exception {
		setError(request, key, null);
	}
	

	public static void setTitle(HttpServletRequest request, String key) {
		request.getSession().setAttribute("message.title", key);
	}	

	public static void setError(HttpServletRequest request, String key, String infoAdicional) throws Exception {
		request.setAttribute("message.error", key);
		if (infoAdicional != null)
			request.setAttribute("message.error.info", infoAdicional);
	}
	

	public static void setErrorList(HttpServletRequest request, String key, List items) throws Exception {
		request.setAttribute("message.error", key);
		if (items != null)
			request.setAttribute("message.error.infolist", items);
	}
	
	
	public static void setMessage(HttpServletRequest request, String key, String infoAdicional) throws Exception {
		request.setAttribute("message.normal", key);
		if (infoAdicional != null)
			request.setAttribute("message.normal.info", infoAdicional);
	}
	

	public static void setConditionalMessage(HttpServletRequest request, String key) throws Exception {
		if (request.getAttribute("message.normal") == null)
			setMessage(request, key);
	}	
	

	public static void setMessage(HttpServletRequest request, String key) throws Exception {
		setMessage(request, key, null);
	}

	public static void setAlert(HttpServletRequest request, String key, String infoAdicional) {
		request.setAttribute("message.alert", key);
		if (infoAdicional != null)
			request.setAttribute("message.alert.info", infoAdicional);
	}

	public static void setAlert(HttpServletRequest request, String key) {
		setAlert(request, key, null);
	}	
	

	public static boolean isValidRut(String rutField) {
		try {
			
			// prepara valores base
			String rut = WebUtils.getCuerpoRut(rutField);
			String dvValue = WebUtils.getDigitoRut(rutField);
			
			Integer.parseInt(rut); // error si no es numerico
			if (dvValue.length() != 1) throw new Exception("Digito incorrecto");
			
			char dvr = '0';
			int suma = 0;
			int mul  = 2;
			for (int i=rut.length()-1; i >= 0; i--) {
				suma = suma + Character.digit(rut.charAt(i),10) * mul;
				if (mul == 7) mul = 2;
				else mul++;
			}
			
			int res = suma % 11;
			if (res==1) dvr = 'K';
			else if (res==0) dvr = '0';
			else {
				  int dvi = 11-res;
				  dvr = Character.forDigit(dvi,10);
			}

			if ( Character.toLowerCase(dvr) == Character.toLowerCase(dvValue.charAt(0)))
				  return true;
				
			return false;
			
		} catch (Exception ex) {
			// ante errores, es invalido
			return false;
		}
	}

	public static void sendAjaxResponse(HttpServletResponse response, String xml) throws Exception {
	    // Escribe la respuesta
	    response.setHeader( "Content-Type", "application/xml" );
	    //gmallea 13-05-2013 se agrega la codificacion UTF-8 para no tener problemas con las tildes
	    response.setContentType( "application/xml ; charset=UTF-8" );
	    response.setHeader("Expires", "0"); 
	    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0"); 
	    response.setHeader("Pragma", "public");    
	    
	    // limpa XML si hay caracteres extraños
	    char especial = (int)26;
	    String newXml = xml.replaceAll("&", "Y").replaceAll(String.valueOf( especial ), ""); 

	    // response.setHeader( "Cache-Control", "no-cache" );
	    Writer writer = response.getWriter();
	    writer.write( newXml );
		
	}
	
	public static boolean validateRut(Object bean, 
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String rut = getCleanData( ValidatorUtils.getValueAsString(bean,field.getProperty()) );
		if (rut != null && !isValidRut( rut )) {			
			errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
			return false;
		}
		
		return true;
	}	
	
	public static String getCuerpoRut(String rut) {
		if (rut == null)
			return null;
		String base = rut.replaceAll("[.]", "").trim();
		if ( base.indexOf('-') >= 0) {
			StringTokenizer st = new StringTokenizer( base, "-" );
			return st.nextToken();
		} else {
			return base.substring(0, base.length() - 1);
		}
	}
	

	public static String getDigitoRut(String rut) {
		if (rut == null)
			return null;
		
		String base = rut.replaceAll("[.]", "").trim();
		
		if ( base.indexOf('-') >= 0) {
			StringTokenizer st = new StringTokenizer( base, "-" );
			st.nextToken(); // bota el cuerpo
			return st.nextToken().toUpperCase();
		} else {
			return base.substring(base.length() - 1).toUpperCase();
		}
	}

	public static String[] getAgnos(int antes, int despues) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(GregorianCalendar.YEAR, -antes);
		int total = antes + despues + 1;
		String[] agnos = new String[total];
		for (int i=0; i < total; i++) {
			agnos[i] = String.valueOf( gc.get(GregorianCalendar.YEAR) );
			gc.add(GregorianCalendar.YEAR, 1);
		}
		return agnos;
	}
	


	public static String validaFormulario(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, MessageResources mr) {
	 	
    	ActionMapping newActionMapping = new ActionMapping();
    	newActionMapping.setPath( mapping.getPath() + "_XML");
    	ActionErrors errors = form.validate(newActionMapping, request);

    	String xmlFinal = null;
    	if ( errors != null ) {
			String errores = "";
	
	    	Iterator it = errors.properties(); 
	    	while (it.hasNext()) {
	    		String property = (String)it.next();
	    		Iterator i = errors.get(property);
	    		while (i.hasNext()) {
	    			ActionMessage message = (ActionMessage)i.next();
	    			errores += ("<message>" + mr.getMessage(message.getKey(), message.getValues()) + "</message>");
	    		}
	    		
	    	}
			if ( !"".equalsIgnoreCase(errores) )
				xmlFinal = "<messages>" + errores + "</messages>"; 	
    	}
    	
    	return xmlFinal;
	}

	public static String getUserName(HttpServletRequest request) {
		if (request.getUserPrincipal() == null) return null;
		return request.getUserPrincipal().getName();
	}
	
	
	public static String getXSL(String path,String nombreXSL)throws Exception{
		
		String xsl = cl.araucana.tesoreria.util.FileUtils.readFile (path+File.separator + nombreXSL, true, "UTF-8"); 
		return xsl;
	} 
	
	public static String traduceCodigo(Object[] lista, String property, String label, String key) throws Exception {
		if ( key == null )
			return "--desconocida--";
		
		String valor = key;
		try {
			Class[] parametersClass = new Class[0];
			Object[] parametersObject = new Object[0];
			String getPropertyMethod = "get" + String.valueOf(property.charAt(0)).toUpperCase() + property.substring(1);
			String getLabelMethod = "get" + String.valueOf(label.charAt(0)).toUpperCase() + label.substring(1);
			
			if (lista != null) {
				boolean found = false;
				for (int i=0; !found && i<lista.length; i++) {
					Object objeto = lista[i];
					Method metodo = objeto.getClass().getMethod(getPropertyMethod, parametersClass);
					Object thisKey = metodo.invoke(objeto, parametersObject);
					if (key.equals(String.valueOf(thisKey).trim())) {
						found = true;
						metodo = objeto.getClass().getMethod(getLabelMethod, parametersClass);
						Object resultado = metodo.invoke(objeto, parametersObject);
						if (resultado !=null )
							valor = String.valueOf(resultado).trim();
					}
					
				}
			}
			
		} catch (Exception ex) {
			logger.error("No pudo traducirse el key --> ", ex);
		}
		
		return key.equals(valor) ? ( "C�digo Nro " + key ) : valor;
	}	
	
}
