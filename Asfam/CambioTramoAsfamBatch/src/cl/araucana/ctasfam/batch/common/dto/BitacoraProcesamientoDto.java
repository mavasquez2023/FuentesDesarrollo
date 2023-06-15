package cl.araucana.ctasfam.batch.common.dto;

import java.util.Date;

public class BitacoraProcesamientoDto {
	
	private Integer idBitacora;
	private Integer idPeticionProcesamiento;
	private String codeHebraProcesadora;
	private String resultadoProcesamiento;
	private String detalleResultadoProcesamiento;
	private Date fechaInicioProcesamiento;
	private Date fechaFinProcesamiento;
	private Integer numeroIntento;
	
	public BitacoraProcesamientoDto(){}

	public BitacoraProcesamientoDto(Integer idBitacora,
			Integer idPeticionProcesamiento, String codeHebraProcesadora,
			String resultadoProcesamiento,
			String detalleResultadoProcesamiento,
			Date fechaInicioProcesamiento, Date fechaFinProcesamiento,
			Integer numeroIntento) {
		super();
		this.idBitacora = idBitacora;
		this.idPeticionProcesamiento = idPeticionProcesamiento;
		this.codeHebraProcesadora = codeHebraProcesadora;
		this.resultadoProcesamiento = resultadoProcesamiento;
		this.detalleResultadoProcesamiento = detalleResultadoProcesamiento;
		this.fechaInicioProcesamiento = fechaInicioProcesamiento;
		this.fechaFinProcesamiento = fechaFinProcesamiento;
		this.numeroIntento = numeroIntento;
	}

	public Integer getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(Integer idBitacora) {
		this.idBitacora = idBitacora;
	}

	public Integer getIdPeticionProcesamiento() {
		return idPeticionProcesamiento;
	}

	public void setIdPeticionProcesamiento(Integer idPeticionProcesamiento) {
		this.idPeticionProcesamiento = idPeticionProcesamiento;
	}

	public String getCodeHebraProcesadora() {
		return codeHebraProcesadora;
	}

	public void setCodeHebraProcesadora(String codeHebraProcesadora) {
		this.codeHebraProcesadora = codeHebraProcesadora;
	}

	public String getResultadoProcesamiento() {
		return resultadoProcesamiento;
	}

	public void setResultadoProcesamiento(String resultadoProcesamiento) {
		this.resultadoProcesamiento = resultadoProcesamiento;
	}

	public String getDetalleResultadoProcesamiento() {
		return detalleResultadoProcesamiento;
	}

	public void setDetalleResultadoProcesamiento(
			String detalleResultadoProcesamiento) {
		this.detalleResultadoProcesamiento = detalleResultadoProcesamiento;
	}

	public Date getFechaInicioProcesamiento() {
		return fechaInicioProcesamiento;
	}

	public void setFechaInicioProcesamiento(Date fechaInicioProcesamiento) {
		this.fechaInicioProcesamiento = fechaInicioProcesamiento;
	}

	public Date getFechaFinProcesamiento() {
		return fechaFinProcesamiento;
	}

	public void setFechaFinProcesamiento(Date fechaFinProcesamiento) {
		this.fechaFinProcesamiento = fechaFinProcesamiento;
	}

	public Integer getNumeroIntento() {
		return numeroIntento;
	}

	public void setNumeroIntento(Integer numeroIntento) {
		this.numeroIntento = numeroIntento;
	}
}
