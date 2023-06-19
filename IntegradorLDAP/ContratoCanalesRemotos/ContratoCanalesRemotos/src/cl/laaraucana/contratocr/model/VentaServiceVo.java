package cl.laaraucana.contratocr.model;

import java.io.Serializable;

public class VentaServiceVo implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String salida;
	private String codigo;

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	

}
