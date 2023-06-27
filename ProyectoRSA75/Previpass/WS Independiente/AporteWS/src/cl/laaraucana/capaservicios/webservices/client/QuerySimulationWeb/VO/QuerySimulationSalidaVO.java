package cl.laaraucana.capaservicios.webservices.client.QuerySimulationWeb.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.capaservicios.webservices.model.AbstractSalidaVO;

public class QuerySimulationSalidaVO extends AbstractSalidaVO {
	private String montoCuota;
	private String cae;
	private String costoTotal;
	private List<PaymentOptionsSalidaVO> paymentOptionsSalidaList = new ArrayList<PaymentOptionsSalidaVO>();

	public String getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}

	public String getCae() {
		return cae;
	}

	public void setCae(String cae) {
		this.cae = cae;
	}

	public String getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(String costoTotal) {
		this.costoTotal = costoTotal;
	}

	public List<PaymentOptionsSalidaVO> getPaymentOptionsSalidaList() {
		return paymentOptionsSalidaList;
	}

	public void setPaymentOptionsSalidaList(List<PaymentOptionsSalidaVO> paymentOptionsSalidaList) {
		this.paymentOptionsSalidaList = paymentOptionsSalidaList;
	}

}
