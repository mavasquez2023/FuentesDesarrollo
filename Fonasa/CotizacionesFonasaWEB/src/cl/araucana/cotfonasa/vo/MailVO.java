package cl.araucana.cotfonasa.vo;

import java.io.File;

public class MailVO {
	private String de;
	private String [] para;
	private String [] copia;
	private String asunto;
	private String mensaje;
	
	private File [] adjuntos;
	
	public MailVO(){}

	public MailVO(String de, String [] para, String asunto, String mensaje) {
		this.de = de;
		this.para = para;
		this.asunto = asunto;
		this.mensaje = mensaje;
		
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

	public String[] getCopia() {
		return copia;
	}

	public void setCopia(String[] copia) {
		this.copia = copia;
	}

	public String[] getPara() {
		return para;
	}

	public void setPara(String[] para) {
		this.para = para;
	}
}
