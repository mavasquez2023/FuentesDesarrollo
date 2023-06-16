package cl.araucana.ctasfam.business.to;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class CargaArchivoTO extends ActionForm{
	 //--alexis advise 15-06-2012--//
	 
	private static final long serialVersionUID = 3564732010550346260L;


	public CargaArchivoTO(){
		
	}

	
	private FormFile archivo;
	private String etapa;
	private String rutEmpresa;


	public FormFile getArchivo() {
		return archivo;
	}


	public void setArchivo(FormFile archivo) {
		this.archivo = archivo;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public String getEtapa() {
		return etapa;
	}


	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}


	public String getRutEmpresa() {
		return rutEmpresa;
	}


	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	
	
	
	
	
	
}
