package cl.laaraucana.compromisototal.webservice.client.queryContractHeader.VO;

import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;

public class EntradaContractHeaderVO extends AbstractEntradaVO  {
	
	private String acNUM_EXT;
	private String rut;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getAcNUM_EXT() {
		return acNUM_EXT;
	}

	public void setAcNUM_EXT(String acNUM_EXT) {
		this.acNUM_EXT = acNUM_EXT;
	}
	
	

}
