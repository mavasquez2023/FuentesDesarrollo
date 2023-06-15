package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;

public class SolicitanteVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String celular;
	private String rut;
	private String numeroSerie;
	private String captcha;
	private String rutEmpresa;
	private AfiliadoVo afiliado;
	
	
	public AfiliadoVo getAfiliado() {
		return afiliado;
	}
	public void setAfiliado(AfiliadoVo afiliado) {
		this.afiliado = afiliado;
	}
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}
