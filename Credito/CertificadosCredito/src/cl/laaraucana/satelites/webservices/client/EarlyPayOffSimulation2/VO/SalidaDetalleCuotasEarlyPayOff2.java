package cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO;

public class SalidaDetalleCuotasEarlyPayOff2 {

	private String fechaDePago;
	private double totalAPagar;
	private String incluyeCuotasTransito;

	public String getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(String fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public double getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	/**
	 * @return the incluyeCuotasTransito
	 */
	public String getIncluyeCuotasTransito() {
		return incluyeCuotasTransito;
	}

	/**
	 * @param incluyeCuotasTransito the incluyeCuotasTransito to set
	 */
	public void setIncluyeCuotasTransito(String incluyeCuotasTransito) {
		this.incluyeCuotasTransito = incluyeCuotasTransito;
	}
	
	
}
