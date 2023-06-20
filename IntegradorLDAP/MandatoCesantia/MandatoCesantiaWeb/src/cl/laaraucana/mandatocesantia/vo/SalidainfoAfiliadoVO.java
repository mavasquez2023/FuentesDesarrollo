package cl.laaraucana.mandatocesantia.vo;

import java.util.Date;
import java.util.List;



public class SalidainfoAfiliadoVO extends AbstractSalidaVO{
	private String nombreCompleto;
	private Date fechaNacimiento;
	private boolean trabajador;
	private boolean pensionado;
	private boolean deudordirecto;
	private List<AnexoAfiliadoVO> anexos;
	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the trabajador
	 */
	public boolean isTrabajador() {
		return trabajador;
	}
	/**
	 * @param trabajador the trabajador to set
	 */
	public void setTrabajador(boolean trabajador) {
		this.trabajador = trabajador;
	}
	/**
	 * @return the pensionado
	 */
	public boolean isPensionado() {
		return pensionado;
	}
	/**
	 * @param pensionado the pensionado to set
	 */
	public void setPensionado(boolean pensionado) {
		this.pensionado = pensionado;
	}
	/**
	 * @return the deudordirecto
	 */
	public boolean isDeudordirecto() {
		return deudordirecto;
	}
	/**
	 * @param deudordirecto the deudordirecto to set
	 */
	public void setDeudordirecto(boolean deudordirecto) {
		this.deudordirecto = deudordirecto;
	}
	/**
	 * @return the anexos
	 */
	public List<AnexoAfiliadoVO> getAnexos() {
		return anexos;
	}
	/**
	 * @param anexos the anexos to set
	 */
	public void setAnexos(List<AnexoAfiliadoVO> anexos) {
		this.anexos = anexos;
	}
	
	
	
}
