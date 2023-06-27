package com.microsystem.lme.web;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8492633414798568553L;

	private FormFile theFile;
	private int cbOpcion;
	private String respuesta;

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public int getCbOpcion() {
		return cbOpcion;
	}

	public void setCbOpcion(int cbOpcion) {
		this.cbOpcion = cbOpcion;
	}

	public FormFile getTheFile() {
		return theFile;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
}
