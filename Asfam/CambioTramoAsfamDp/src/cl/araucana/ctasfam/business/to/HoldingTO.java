package cl.araucana.ctasfam.business.to;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class HoldingTO extends ActionForm{

	 //--alexis advise 15-06-2012--//
	 
	public HoldingTO(){
		
	}
	
	private static final long serialVersionUID = -3466759431445140699L;
	
	
	private FormFile archivoholding;
	private String tipo;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public FormFile getArchivoholding() {
		return archivoholding;
	}


	public void setArchivoholding(FormFile archivoholding) {
		this.archivoholding = archivoholding;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	

}
