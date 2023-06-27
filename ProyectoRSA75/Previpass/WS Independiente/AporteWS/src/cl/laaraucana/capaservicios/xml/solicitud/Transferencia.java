package cl.laaraucana.capaservicios.xml.solicitud;

import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = { "folioInternoSistemaOrigen", "folioTesoreria", "monto" })
public class Transferencia {

	private String folioInternoSistemaOrigen;
	private String folioTesoreria;
	private String monto;
	
	public Transferencia(){}
	
	public Transferencia (String folioInternoSistemaOrigen, String folioTesoreria, String monto){
		this.folioInternoSistemaOrigen = folioInternoSistemaOrigen;
		this.folioTesoreria = folioTesoreria;
		this.monto = monto;
	}
	
	public String getFolioInternoSistemaOrigen() {
		return folioInternoSistemaOrigen;
	}

	public void setFolioInternoSistemaOrigen(String folioInternoSistemaOrigen) {
		this.folioInternoSistemaOrigen = folioInternoSistemaOrigen;
	}

	public String getFolioTesoreria() {
		return folioTesoreria;
	}

	public void setFolioTesoreria(String folioTesoreria) {
		this.folioTesoreria = folioTesoreria;
	}

	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	
}
