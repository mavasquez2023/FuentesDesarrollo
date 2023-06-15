package cl.laaraucana.satelites.certificados.creditocancelado.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaDetalleCreditoCanceladoVO extends AbstractSalidaVO{
	List<SalidaDetalleCreditoCanceladoVO> listaCuotas = new ArrayList<SalidaDetalleCreditoCanceladoVO>();

	
	public SalidaListaDetalleCreditoCanceladoVO() {
		super();
	}


	public List<SalidaDetalleCreditoCanceladoVO> getListaCuotas() {
		return listaCuotas;
	}


	public void setListaCuotas(List<SalidaDetalleCreditoCanceladoVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}


	public SalidaListaDetalleCreditoCanceladoVO(
			List<SalidaDetalleCreditoCanceladoVO> listaCuotas) {
		super();
		this.listaCuotas = listaCuotas;
	}
	

	
	
	
}
