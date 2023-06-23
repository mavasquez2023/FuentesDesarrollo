package cl.araucana.tesoreria.dao.dvo;

import java.io.Serializable;

public class ReporteComprobantesDVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String oficinaCodigo;
	private String oficinaNombre;
	private String comprobantesNumero; // numero de comprobantes por oficina
	private String comprobantesMonto; // monto en pesos, suma de todos los comprobantes

	public String getOficinaCodigo() {
		return oficinaCodigo;
	}

	public void setOficinaCodigo(String oficinaCodigo) {
		this.oficinaCodigo = oficinaCodigo;
	}

	public String getOficinaNombre() {
		return oficinaNombre;
	}

	public void setOficinaNombre(String oficinaNombre) {
		this.oficinaNombre = oficinaNombre;
	}

	public String getComprobantesNumero() {
		return comprobantesNumero;
	}

	public void setComprobantesNumero(String comprobantesNumero) {
		this.comprobantesNumero = comprobantesNumero;
	}

	public String getComprobantesMonto() {
		return comprobantesMonto;
	}

	public void setComprobantesMonto(String comprobantesMonto) {
		this.comprobantesMonto = comprobantesMonto;
	}
}
