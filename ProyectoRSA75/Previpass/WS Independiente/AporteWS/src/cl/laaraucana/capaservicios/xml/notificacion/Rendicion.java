package cl.laaraucana.capaservicios.xml.notificacion;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "folioStl", "beneficiario", "transferencia" })
public class Rendicion {

	private String folioStl;
	private Beneficiario beneficiario;
	private Transferencia transferencia;
	
	public Rendicion (){}
	
	public Rendicion (String folioStl, Beneficiario beneficiario, Transferencia transferencia){
		this.folioStl=folioStl;
		this.beneficiario=beneficiario;
		this.transferencia=transferencia;
	}
	
	public String getFolioStl() {
		return folioStl;
	}
	public void setFolioStl(String folioStl) {
		this.folioStl = folioStl;
	}
	

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	@XmlElement
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}
	@XmlElement
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	
}
