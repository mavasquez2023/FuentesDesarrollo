package cl.laaraucana.capaservicios.xml.solicitud;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "sistemaOrigen", "beneficiario", "solicitud" })

public class SolicitudTEF {
	private SistemaOrigen sistemaOrigen;
	private Beneficiario beneficiario;
	private Solicitud solicitud;
	
	public SolicitudTEF(){}
	
	public SolicitudTEF(SistemaOrigen sistemaOrigen, Beneficiario beneficiario, Solicitud solicitud){
		this.sistemaOrigen=sistemaOrigen;
		this.beneficiario=beneficiario;
		this.solicitud=solicitud;
	}

	public SistemaOrigen getSistemaOrigen() {
		return sistemaOrigen;
	}

	@XmlElement
	public void setSistemaOrigen(SistemaOrigen sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	@XmlElement
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	@XmlElement
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	
}
