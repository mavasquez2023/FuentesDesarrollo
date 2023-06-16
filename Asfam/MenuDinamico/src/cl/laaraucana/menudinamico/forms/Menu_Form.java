package cl.laaraucana.menudinamico.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class Menu_Form extends ActionForm{
		
	
	private long codMenu;
	
    private long nodoPadre;
    
    private String flgHoja;
    
    private String enlace;
    
    private String etiqueta;
    
    private long nivel;
    
    private String descripcion;
    
    private String seguridad;
	
    private String linkInterno;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		codMenu=0;
	    nodoPadre=0;
	    flgHoja="";
	    enlace="";
	    etiqueta=""; 
	    nivel=0;
	    descripcion ="";
	    seguridad = "";
	    linkInterno="";
    }
	
    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();
	return errors;
    }

	public long getCodMenu() {
		return codMenu;
	}

	public long getNodoPadre() {
		return nodoPadre;
	}

	public String getFlgHoja() {
		return flgHoja;
	}

	public String getEnlace() {
		return enlace;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public long getNivel() {
		return nivel;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getSeguridad() {
		return seguridad;
	}
	
	public String getLinkInterno() {
		return linkInterno;
	}

	public void setLinkInterno(String linkInterno) {
		this.linkInterno = linkInterno;
	}

	public void setCodMenu(long codMenu) {
		this.codMenu = codMenu;
	}

	public void setNodoPadre(long nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	public void setFlgHoja(String flgHoja) {
		this.flgHoja = flgHoja;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public void setNivel(long nivel) {
		this.nivel = nivel;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}

	
    
}//end class LM_Form
