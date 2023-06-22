package cl.laaraucana.recepcionsil.service.vo;

public class EntradaRecepcionSILVO {
	private LicenciaVO licencia;
	private MixtaConsumoVO consumo;
	private MixtaValidacionVO validacion;

	public LicenciaVO getLicencia() {
		return licencia;
	}

	public MixtaConsumoVO getConsumo() {
		return consumo;
	}

	public MixtaValidacionVO getValidacion() {
		return validacion;
	}

	public void setLicencia(LicenciaVO licencia) {
		this.licencia = licencia;
	}

	public void setConsumo(MixtaConsumoVO consumo) {
		this.consumo = consumo;
	}

	public void setValidacion(MixtaValidacionVO validacion) {
		this.validacion = validacion;
	}
}
