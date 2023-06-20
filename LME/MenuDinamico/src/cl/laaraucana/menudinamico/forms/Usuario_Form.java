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
public class Usuario_Form extends ActionForm{
		
	
	private int id_user;
	private String rut_user;
	private String nombre_user;
	private String ape_paterno;
	private String ape_materno;
	private String email_user;	
	private long codMenu;
	private long nodoPadre;
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id_user=0;
		rut_user="";
		nombre_user="";
		ape_paterno="";
		ape_materno="";
		email_user="";	
		codMenu = 0;
		nodoPadre = 0;
    }
	
    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();
	return errors;
    }

	public int getId_user() {
		return id_user;
	}

	public String getRut_user() {
		return rut_user;
	}

	public String getNombre_user() {
		return nombre_user;
	}

	public String getApe_paterno() {
		return ape_paterno;
	}

	public String getApe_materno() {
		return ape_materno;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public void setRut_user(String rut_user) {
		this.rut_user = rut_user;
	}

	public void setNombre_user(String nombre_user) {
		this.nombre_user = nombre_user;
	}

	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}

	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public long getCodMenu() {
		return codMenu;
	}

	public void setCodMenu(long codMenu) {
		this.codMenu = codMenu;
	}

	public long getNodoPadre() {
		return nodoPadre;
	}

	public void setNodoPadre(long nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

}//end class LM_Form
