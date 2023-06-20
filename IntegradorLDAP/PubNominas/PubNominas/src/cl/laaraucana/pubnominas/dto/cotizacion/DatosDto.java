package cl.laaraucana.pubnominas.dto.cotizacion;

public class DatosDto {

	private String rutEmpresa;
	private String periodo;
	private String codigoOficina;
	private String codigoSucursal;

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the codigoOficina
	 */
	public String getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @param codigoOficina the codigoOficina to set
	 */
	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	/**
	 * @return the codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * @param codigoSucursal the codigoSucursal to set
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}
	
}
