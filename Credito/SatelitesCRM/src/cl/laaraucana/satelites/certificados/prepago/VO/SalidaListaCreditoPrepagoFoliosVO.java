package cl.laaraucana.satelites.certificados.prepago.VO;

import java.util.ArrayList;
import java.util.List;

public class SalidaListaCreditoPrepagoFoliosVO {

	private List<SalidaCreditoPrepagoFoliosVO> salidaList = new ArrayList<SalidaCreditoPrepagoFoliosVO>();
	private SalidaCreditoPrepagoFoliosVO totales = new SalidaCreditoPrepagoFoliosVO();

	public List<SalidaCreditoPrepagoFoliosVO> getSalidaList() {
		return salidaList;
	}

	public void setSalidaList(List<SalidaCreditoPrepagoFoliosVO> salidaList) {
		this.salidaList = salidaList;
	}

	public SalidaCreditoPrepagoFoliosVO getTotales() {
		return totales;
	}

	public void setTotales(SalidaCreditoPrepagoFoliosVO totales) {
		this.totales = totales;
	}

}
