package cl.araucana.ctasfam.batch.common.dto;


public class CantidadPropuestasAfiliadoDto {
	private String origen;
	private Integer secDigit;
	private Integer cantidad;
	
	public CantidadPropuestasAfiliadoDto(){}
	
	public CantidadPropuestasAfiliadoDto(Integer cantidad){
		this.cantidad = cantidad;
	}
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public Integer getSecDigit() {
		return secDigit;
	}
	public void setSecDigit(Integer secDigit) {
		this.secDigit = secDigit;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
