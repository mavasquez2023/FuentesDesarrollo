package cl.laaraucana.credito.to;

import java.io.Serializable;
import java.util.Date;

public class SeguroComprometidoTO  extends CreditoBaseTO {
	private long rutNroAfiliado;
	private String rutDgvAfiliado;
	private long porPoliza;
	private long monto;
	private Date fechaInicioCobro;
	private int cargo;
	private Date fechaEstado;
	private long poliza;
	private String tipoSeguro;
	/**
	 * @return el cargo
	 */
	public int getCargo() {
		return cargo;
	}
	/**
	 * @param cargo el cargo a establecer
	 */
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	/**
	 * @return el estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return el fechaEstado
	 */
	public Date getFechaEstado() {
		return fechaEstado;
	}
	/**
	 * @param fechaEstado el fechaEstado a establecer
	 */
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	/**
	 * @return el fechaInicioCobro
	 */
	public Date getFechaInicioCobro() {
		return fechaInicioCobro;
	}
	/**
	 * @param fechaInicioCobro el fechaInicioCobro a establecer
	 */
	public void setFechaInicioCobro(Date fechaInicioCobro) {
		this.fechaInicioCobro = fechaInicioCobro;
	}
	/**
	 * @return el folio
	 */
	public long getFolio() {
		return folio;
	}
	/**
	 * @param folio el folio a establecer
	 */
	public void setFolio(long folio) {
		this.folio = folio;
	}
	/**
	 * @return el monto
	 */
	public long getMonto() {
		return monto;
	}
	/**
	 * @param monto el monto a establecer
	 */
	public void setMonto(long monto) {
		this.monto = monto;
	}
	/**
	 * @return el oficina
	 */
	public String getOficina() {
		return oficina;
	}
	/**
	 * @param oficina el oficina a establecer
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return el poliza
	 */
	public long getPoliza() {
		return poliza;
	}
	/**
	 * @param poliza el poliza a establecer
	 */
	public void setPoliza(long poliza) {
		this.poliza = poliza;
	}
	/**
	 * @return el porPoliza
	 */
	public long getPorPoliza() {
		return porPoliza;
	}
	/**
	 * @param porPoliza el porPoliza a establecer
	 */
	public void setPorPoliza(long porPoliza) {
		this.porPoliza = porPoliza;
	}
	/**
	 * @return el rutDgvAfiliado
	 */
	public String getRutDgvAfiliado() {
		return rutDgvAfiliado;
	}
	/**
	 * @param rutDgvAfiliado el rutDgvAfiliado a establecer
	 */
	public void setRutDgvAfiliado(String rutDgvAfiliado) {
		this.rutDgvAfiliado = rutDgvAfiliado;
	}
	/**
	 * @return el rutNroAfiliado
	 */
	public long getRutNroAfiliado() {
		return rutNroAfiliado;
	}
	/**
	 * @param rutNroAfiliado el rutNroAfiliado a establecer
	 */
	public void setRutNroAfiliado(long rutNroAfiliado) {
		this.rutNroAfiliado = rutNroAfiliado;
	}
	/**
	 * @return el tipoSeguro
	 */
	public String getTipoSeguro() {
		return tipoSeguro;
	}
	/**
	 * @param tipoSeguro el tipoSeguro a establecer
	 */
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
}
