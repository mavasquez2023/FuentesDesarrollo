package cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.capaservicios.webservices.model.AbstractSalidaVO;

public class SalidaListaQueryContractCashflowInVO extends AbstractSalidaVO {
	private List<SalidaQueryContractCashflowInVO> listaCuotas = new ArrayList<SalidaQueryContractCashflowInVO>();
	private String nroCuenta;
	private String lineaComercial;
	private String cantidadTotalCuotas;

	public SalidaListaQueryContractCashflowInVO() {
	}

	public SalidaListaQueryContractCashflowInVO(List<SalidaQueryContractCashflowInVO> listaCuotas) {
		super();
		this.listaCuotas = listaCuotas;
	}

	public List<SalidaQueryContractCashflowInVO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<SalidaQueryContractCashflowInVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getLineaComercial() {
		return lineaComercial;
	}

	public void setLineaComercial(String lineaComercial) {
		this.lineaComercial = lineaComercial;
	}

	public String getCantidadTotalCuotas() {
		return cantidadTotalCuotas;
	}

	public void setCantidadTotalCuotas(String cantidadTotalCuotas) {
		this.cantidadTotalCuotas = cantidadTotalCuotas;
	}

}
