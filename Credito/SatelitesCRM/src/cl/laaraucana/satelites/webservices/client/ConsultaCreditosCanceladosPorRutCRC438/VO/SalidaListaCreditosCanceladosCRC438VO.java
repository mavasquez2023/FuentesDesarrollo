package cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.VO;

import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaCreditosCanceladosCRC438VO extends AbstractSalidaVO {

	private List<SalidaCreditosCanceladosCRC438VO> listaCreditos;
	private String totalCreditosCancelados;
	   
	public List<SalidaCreditosCanceladosCRC438VO> getListaCreditos() {
		return listaCreditos;
	}
	public void setListaCreditos(List<SalidaCreditosCanceladosCRC438VO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
	public String getTotalCreditosCancelados() {
		return totalCreditosCancelados;
	}
	public void setTotalCreditosCancelados(String totalCreditosCancelados) {
		this.totalCreditosCancelados = totalCreditosCancelados;
	}

	   
	    
}
