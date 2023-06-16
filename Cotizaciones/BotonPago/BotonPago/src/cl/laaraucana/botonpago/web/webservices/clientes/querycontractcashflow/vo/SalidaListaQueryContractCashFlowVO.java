package cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;



public class SalidaListaQueryContractCashFlowVO extends AbstractSalidaVO {
	private List<SalidaQueryContractCashFlowVO> listaCuotas = new ArrayList<SalidaQueryContractCashFlowVO>();
	private String nroCuenta;
	private String lineaComercial;
	private String cantidadTotalCuotas;

	public SalidaListaQueryContractCashFlowVO() {
	}

	public SalidaListaQueryContractCashFlowVO(List<SalidaQueryContractCashFlowVO> listaCuotas) {
		super();
		this.listaCuotas = listaCuotas;
	}

	public List<SalidaQueryContractCashFlowVO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<SalidaQueryContractCashFlowVO> listaCuotas) {
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
