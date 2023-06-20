/**
 * 
 */
package cl.laaraucana.mandato.ibatis.vo;

import java.util.Date;

/**
 * @author IBM Software Factory
 *
 */
public class RechazoVo {
	private int rutAfiliado;
	private String dvAfiliado;
	private int codigoBanco;
	private String numeroCuenta;
	private String motivoRechazo;
	private String sistema;
	private String usuario;
	private String via;
	private int estado;
	private Date fechaDeshabilitacion;
	private Date fechaCreacion;
	private Date horaCreacion;
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dvAfiliado
	 */
	public String getDvAfiliado() {
		return dvAfiliado;
	}
	/**
	 * @param dvAfiliado the dvAfiliado to set
	 */
	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}
	/**
	 * @return the codigoBanco
	 */
	public int getCodigoBanco() {
		return codigoBanco;
	}
	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(int codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	/**
	 * @return the motivoRechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	/**
	 * @param motivoRechazo the motivoRechazo to set
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}
	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the via
	 */
	public String getVia() {
		return via;
	}
	/**
	 * @param via the via to set
	 */
	public void setVia(String via) {
		this.via = via;
	}
	
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return the fechaDeshabilitacion
	 */
	public Date getFechaDeshabilitacion() {
		return fechaDeshabilitacion;
	}
	/**
	 * @param fechaDeshabilitacion the fechaDeshabilitacion to set
	 */
	public void setFechaDeshabilitacion(Date fechaDeshabilitacion) {
		this.fechaDeshabilitacion = fechaDeshabilitacion;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the horaCreacion
	 */
	public Date getHoraCreacion() {
		return horaCreacion;
	}
	/**
	 * @param horaCreacion the horaCreacion to set
	 */
	public void setHoraCreacion(Date horaCreacion) {
		this.horaCreacion = horaCreacion;
	}
	
	
}
