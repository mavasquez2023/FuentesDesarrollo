package cl.araucana.ctasfam.batch.common.dto;

public class PropiedadConfiguracionDto {
	
	private String llave;
	private String valor;
	
	
	public PropiedadConfiguracionDto(){}
	
	public PropiedadConfiguracionDto(String llave, String valor) {
		this.llave = llave;
		this.valor = valor;
	}
	
	public String getLlave() {
		return llave;
	}
	public void setLlave(String llave) {
		this.llave = llave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
