package cl.araucana.wslme.business.to;

public class Operador {
	private String codigoOperador;
	private String claveOperador;
	
	public Operador(){}
	
	public Operador(String codigoOperador, String claveOperador) {
		this.codigoOperador = codigoOperador;
		this.claveOperador = claveOperador;
	}

	public String getClaveOperador() {
		return claveOperador;
	}

	public void setClaveOperador(String claveOperador) {
		this.claveOperador = claveOperador;
	}

	public String getCodigoOperador() {
		return codigoOperador;
	}

	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}
}
