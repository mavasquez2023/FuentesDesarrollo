package cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaEarlyPayoffSimulationOUT extends AbstractEntradaVO {
	
	private String idContrato;
	private String fechaFullEpo;
	private String parametro;
	
	public EntradaEarlyPayoffSimulationOUT(){}
	
	public EntradaEarlyPayoffSimulationOUT(String idContrato, String fechaFullEpo){
		
		this.idContrato = idContrato;
		this.fechaFullEpo = fechaFullEpo;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getFechaFullEpo() {
		return fechaFullEpo;
	}

	public void setFechaFullEpo(String fechaFullEpo) {
		this.fechaFullEpo = fechaFullEpo;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
		
}
