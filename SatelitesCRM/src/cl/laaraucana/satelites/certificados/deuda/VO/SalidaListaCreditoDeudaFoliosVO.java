package cl.laaraucana.satelites.certificados.deuda.VO;

import java.util.ArrayList;
import java.util.List;

public class SalidaListaCreditoDeudaFoliosVO {

	private List<SalidaCreditoDeudaFoliosVO> salidaList = new ArrayList<SalidaCreditoDeudaFoliosVO>();
	private SalidaCreditoDeudaFoliosVO totales = new SalidaCreditoDeudaFoliosVO();

	public List<SalidaCreditoDeudaFoliosVO> getSalidaList() {
		return salidaList;
	}

	public void setSalidaList(List<SalidaCreditoDeudaFoliosVO> salidaList) {
		this.salidaList = salidaList;
	}

	public SalidaCreditoDeudaFoliosVO getTotales() {
		return totales;
	}

	public void setTotales(SalidaCreditoDeudaFoliosVO totales) {
		this.totales = totales;
	}

}
