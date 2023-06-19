package cl.laaraucana.muvu.ibatis.vo;

import org.apache.log4j.Logger;


public class AfiliadoMuvuVo {
	private static final Logger logger = Logger.getLogger(AfiliadoMuvuVo.class);
	private Long rut;
	private String dv;
	private String correoElectronico;
	private String motivo;
	
	public AfiliadoMuvuVo() {
	}

	public Long getRut() {
		return rut;
	}

	public void setRut(Long rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	
}
