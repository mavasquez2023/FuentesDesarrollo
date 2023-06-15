package cl.laaraucana.satelites.main.VO;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class RepactarTablaVO extends AbstractSalidaVO {

	private int CuotaDesde;
	private double totalPrima;

	public int getCuotaDesde() {
		return CuotaDesde;
	}

	public void setCuotaDesde(int cuotaDesde) {
		CuotaDesde = cuotaDesde;
	}

	public double getTotalPrima() {
		return totalPrima;
	}

	public void setTotalPrima(double totalPrima) {
		this.totalPrima = totalPrima;
	}

}
