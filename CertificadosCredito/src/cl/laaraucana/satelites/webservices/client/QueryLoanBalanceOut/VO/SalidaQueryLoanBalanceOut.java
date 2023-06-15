package cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaQueryLoanBalanceOut extends AbstractSalidaVO {

	private String NroContrato;
	private String NroCuota;
	private String FechaVto;
	private String MontoCuota;
	private String MontoMora;
	private String Moneda;
	List<DetalleMontoQueryLoanBalanceOut> detalleMontoList = new ArrayList<DetalleMontoQueryLoanBalanceOut>();

	public String getNroContrato() {
		return NroContrato;
	}

	public void setNroContrato(String nroContrato) {
		NroContrato = nroContrato;
	}

	public String getNroCuota() {
		return NroCuota;
	}

	public void setNroCuota(String nroCuota) {
		NroCuota = nroCuota;
	}

	public String getFechaVto() {
		return FechaVto;
	}

	public void setFechaVto(String fechaVto) {
		FechaVto = fechaVto;
	}

	public String getMontoCuota() {
		return MontoCuota;
	}

	public void setMontoCuota(String montoCuota) {
		MontoCuota = montoCuota;
	}

	public String getMontoMora() {
		return MontoMora;
	}

	public void setMontoMora(String montoMora) {
		MontoMora = montoMora;
	}

	public String getMoneda() {
		return Moneda;
	}

	public void setMoneda(String moneda) {
		Moneda = moneda;
	}

	public List<DetalleMontoQueryLoanBalanceOut> getDetalleMontoList() {
		return detalleMontoList;
	}

	public void setDetalleMontoList(List<DetalleMontoQueryLoanBalanceOut> detalleMontoList) {
		this.detalleMontoList = detalleMontoList;
	}

}
