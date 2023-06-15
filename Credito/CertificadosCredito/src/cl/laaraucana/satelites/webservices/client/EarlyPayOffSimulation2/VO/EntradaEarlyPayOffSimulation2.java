package cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaEarlyPayOffSimulation2 extends AbstractEntradaVO {

	private String contrato;
	private String fechaFullEpo;
	private String tipoPrepago;

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	
	public String getFechaFullEpo() {
		return fechaFullEpo;
	}

	public void setFechaFullEpo(String fechaFullEpo) {
		this.fechaFullEpo = fechaFullEpo;
	}

	public String getTipoPrepago() {
		return tipoPrepago;
	}

	public void setTipoPrepago(String tipoPrepago) {
		this.tipoPrepago = tipoPrepago;
	}

}
