package cl.laaraucana.saldoafavor.vo;


public class SalidaSaldoaFavorVO extends AbstractSalidaVO{
	private String fechaCreacion;
	private int monto;
	private String rut;
	private int tipoAfiliado;
	/**
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the monto
	 */
	public int getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(int monto) {
		this.monto = monto;
	}
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}
	/**
	 * @return the tipoAfiliado
	 */
	public int getTipoAfiliado() {
		return tipoAfiliado;
	}
	/**
	 * @param tipoAfiliado the tipoAfiliado to set
	 */
	public void setTipoAfiliado(int tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	
}
