package cl.araucana.wslme.business.to;

import java.io.File;

public class MailTO {
	private String de;
	private String [] para;
	private String [] conCopia;
	private String asunto;
	private String mensaje;
	
	private File [] adjuntos;
	
	public MailTO(){}

	public MailTO(String de, String [] para, String asunto, String mensaje, File[] adjuntos, String [] conCopia) {
		this.de = de;
		this.para = para;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.adjuntos = adjuntos;
		this.conCopia = conCopia;
	}

	public File[] getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(File[] adjuntos) {
		this.adjuntos = adjuntos;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String[] getConCopia() {
		return conCopia;
	}

	public void setConCopia(String[] conCopia) {
		this.conCopia = conCopia;
	}

	public String[] getPara() {
		return para;
	}

	public void setPara(String[] para) {
		this.para = para;
	}
}
