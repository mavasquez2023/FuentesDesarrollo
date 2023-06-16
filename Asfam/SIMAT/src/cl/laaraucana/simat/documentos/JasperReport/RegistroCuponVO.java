package cl.laaraucana.simat.documentos.JasperReport;

import java.awt.image.BufferedImage;

public class RegistroCuponVO {

	private BufferedImage codeBar;
	private String fecha;
	private String hora;

	/**/
	private String firma;

	public RegistroCuponVO() {
	}

	public RegistroCuponVO(BufferedImage codeBar, String fecha, String hora, String firma) {
		super();
		this.codeBar = codeBar;
		this.fecha = fecha;
		this.hora = hora;
		this.firma = firma;
	}

	public BufferedImage getCodeBar() {
		return codeBar;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public String getFirma() {
		return firma;
	}

	public void setCodeBar(BufferedImage codeBar) {
		this.codeBar = codeBar;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

}
