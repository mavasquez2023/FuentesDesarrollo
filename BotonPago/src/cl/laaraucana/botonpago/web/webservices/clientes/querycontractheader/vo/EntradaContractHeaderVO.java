package cl.laaraucana.botonpago.web.webservices.clientes.querycontractheader.vo;

import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;

public class EntradaContractHeaderVO extends AbstractEntradaVO {

	private String acnum_ext;
	private String rut;

	public String getAcnum_ext() {
		return acnum_ext;
	}

	public void setAcnum_ext(String acnum_ext) {
		this.acnum_ext = acnum_ext;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

}
