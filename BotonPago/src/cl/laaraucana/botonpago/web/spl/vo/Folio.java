package cl.laaraucana.botonpago.web.spl.vo;

import javax.xml.bind.annotation.XmlType;

@XmlType(
		propOrder = { "numero", "monto", "detalle" })
public class Folio {
	private String numero;
	private String monto;
	private String detalle;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}
