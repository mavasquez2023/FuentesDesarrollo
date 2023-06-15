package cl.laaraucana.compromisototal.VO;

import java.util.Date;

//VO QUE AGRUPA DATOS DE AS400 Y BANKING

public class ContratoVO {

	private String origen;
	private String idContrato;
	private String lineaComercial;
	private Double tasaInteres;
	private Double montoAdeudado;
	private Double montoCuota;
	private String estadoCredito;
	private String repactacion;
	private String reprogramacion;
	private String titular;
	private String rolPagador;
	private Double montoSolicitado;
	private String tipoAfiliado;
	private String plazo;
	private Date fechaOtorgamiento;
	private String idOriginal;

	private String bpAnexo;
	private String rutEmpresa;

	private String nroInscripcion;

	public ContratoVO(String origen, String idContrato, String lineaComercial,
			Double tasaInteres, Double montoAdeudado, Double montoCuota,
			String estadoCredito, String repactacion, String reprogramacion,
			String titular, String rolPagador, Double montoSolicitado,
			String tipoAfiliado, String plazo, Date fechaOtorgamiento,
			String idOriginal, String bpAnexo, String rutEmpresa,
			String nroInscripcion) {
		super();
		this.origen = origen;
		this.idContrato = idContrato;
		this.lineaComercial = lineaComercial;
		this.tasaInteres = tasaInteres;
		this.montoAdeudado = montoAdeudado;
		this.montoCuota = montoCuota;
		this.estadoCredito = estadoCredito;
		this.repactacion = repactacion;
		this.reprogramacion = reprogramacion;
		this.titular = titular;
		this.rolPagador = rolPagador;
		this.montoSolicitado = montoSolicitado;
		this.tipoAfiliado = tipoAfiliado;
		this.plazo = plazo;
		this.fechaOtorgamiento = fechaOtorgamiento;
		this.idOriginal = idOriginal;
		this.bpAnexo = bpAnexo;
		this.rutEmpresa = rutEmpresa;
		this.nroInscripcion = nroInscripcion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getLineaComercial() {
		return lineaComercial;
	}

	public void setLineaComercial(String lineaComercial) {
		this.lineaComercial = lineaComercial;
	}

	public Double getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(Double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public Double getMontoAdeudado() {
		return montoAdeudado;
	}

	public void setMontoAdeudado(Double montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}

	public Double getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public String getEstadoCredito() {
		return estadoCredito;
	}

	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}

	public String getRepactacion() {
		return repactacion;
	}

	public void setRepactacion(String repactacion) {
		this.repactacion = repactacion;
	}

	public String getReprogramacion() {
		return reprogramacion;
	}

	public void setReprogramacion(String reprogramacion) {
		this.reprogramacion = reprogramacion;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getRolPagador() {
		return rolPagador;
	}

	public void setRolPagador(String rolPagador) {
		this.rolPagador = rolPagador;
	}

	public Double getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(Double montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public Date getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}

	public void setFechaOtorgamiento(Date fechaOtorgamiento) {
		this.fechaOtorgamiento = fechaOtorgamiento;
	}

	public String getIdOriginal() {
		return idOriginal;
	}

	public void setIdOriginal(String idOriginal) {
		this.idOriginal = idOriginal;
	}

	public String getBpAnexo() {
		return bpAnexo;
	}

	public void setBpAnexo(String bpAnexo) {
		this.bpAnexo = bpAnexo;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getNroInscripcion() {
		return nroInscripcion;
	}

	public void setNroInscripcion(String nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}

}
