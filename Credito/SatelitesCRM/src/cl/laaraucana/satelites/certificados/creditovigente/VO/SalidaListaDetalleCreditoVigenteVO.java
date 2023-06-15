package cl.laaraucana.satelites.certificados.creditovigente.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaDetalleCreditoVigenteVO extends AbstractSalidaVO {
	private List<SalidaDetalleCreditoVigenteVO> listaCuotas = new ArrayList<SalidaDetalleCreditoVigenteVO>();
	private int cuotasPendientes;
	public SalidaListaDetalleCreditoVigenteVO() {
	}

	
	public SalidaListaDetalleCreditoVigenteVO(
			List<SalidaDetalleCreditoVigenteVO> listaCuotas) {
		super();
		this.listaCuotas = listaCuotas;
	}


	public List<SalidaDetalleCreditoVigenteVO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(
			List<SalidaDetalleCreditoVigenteVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}


	/**
	 * @return the cuotasPendientes
	 */
	public int getCuotasPendientes() {
		return cuotasPendientes;
	}


	/**
	 * @param cuotasPendientes the cuotasPendientes to set
	 */
	public void setCuotasPendientes(int cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}
	
}
