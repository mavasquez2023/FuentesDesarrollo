package cl.araucana.tesoreria.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * Tag para traducir valores desde Object[] que hay en memoria
 * 
 * <p>
 * Registro de Versiones:<ul>
 * <li>21-06-2010, emoya (schema Ltda.): Versión inicial</li>
 * </ul><p>
 * 
 * <b>Todos los derechos reservados - La Araucana C.C.A.F.</b><p>
 * <p>
 *
 */
public class TraductorTag extends TagSupport {
	
	/** Serial */
	private static final long serialVersionUID = 1L;	

	/** Objeto en SESSION sonde esta la lista de Object[] */
	String name;
	
	/** atributo por el cual buscar */
	String property;

	/** atributo con la traduccion */
	String label;
	
	/** Valor buscado */
	String key;
	
    public int doStartTag() throws JspException  {
        try  {
            JspWriter out = pageContext.getOut();
            String realKey = key;
            if (key.startsWith("${"))
            	realKey = (String)this.pageContext.getAttribute(key.substring(2, key.length()-1));

    		String valor = WebUtils.traduceCodigo((Object[])this.pageContext.getSession().getAttribute(name), property, label, realKey.trim());
    		out.print( valor );
        } catch(Exception e) {
        	Logger.getLogger(this.getClass()).debug("Se omite: " + e);
        }
        return SKIP_BODY;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

    
}