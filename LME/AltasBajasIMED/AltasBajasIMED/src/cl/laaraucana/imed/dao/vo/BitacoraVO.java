/**
 * 
 */
package cl.laaraucana.imed.dao.vo;

import java.util.Date;

/**
 * @author IBM Software Factory
 *
 */
public class BitacoraVO {
	private String rutBeneficiario;
	private String rutTitular;
	private String tipoEvento;
	private short estado;
	private String mensaje;
	/**
	 * @return the rutBeneficiario
	 */
	public String getRutBeneficiario() {
		return rutBeneficiario;
	}
	/**
	 * @param rutBeneficiario the rutBeneficiario to set
	 */
	public void setRutBeneficiario(String rutBeneficiario) {
		this.rutBeneficiario = rutBeneficiario;
	}
	/**
	 * @return the rutTitular
	 */
	public String getRutTitular() {
		return rutTitular;
	}
	/**
	 * @param rutTitular the rutTitular to set
	 */
	public void setRutTitular(String rutTitular) {
		this.rutTitular = rutTitular;
	}
	/**
	 * @return the tipoEvento
	 */
	public String getTipoEvento() {
		return tipoEvento;
	}
	/**
	 * @param tipoEvento the tipoEvento to set
	 */
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	/**
	 * @return the estado
	 */
	public short getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(short estado) {
		this.estado = estado;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
