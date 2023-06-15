package cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaConsultaDatosPagosCreditosVO extends AbstractEntradaVO{
    protected String folio;
    protected String oficinaProceso;
    
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getOficinaProceso() {
		return oficinaProceso;
	}
	public void setOficinaProceso(String oficinaProceso) {
		this.oficinaProceso = oficinaProceso;
	}
    
    

}
