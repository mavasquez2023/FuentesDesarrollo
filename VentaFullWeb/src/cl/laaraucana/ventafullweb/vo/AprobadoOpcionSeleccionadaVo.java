package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;

public class AprobadoOpcionSeleccionadaVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String opcionMonto;
	private String opcionPlazo;
	private String montoCuota;		
	

	/**
	 * @return the opcionMonto
	 */
	public String getOpcionMonto() {
		return opcionMonto;
	}



	/**
	 * @param opcionMonto the opcionMonto to set
	 */
	public void setOpcionMonto(String opcionMonto) {
		this.opcionMonto = opcionMonto;
	}



	/**
	 * @return the opcionPlazo
	 */
	public String getOpcionPlazo() {
		return opcionPlazo;
	}



	/**
	 * @param opcionPlazo the opcionPlazo to set
	 */
	public void setOpcionPlazo(String opcionPlazo) {
		this.opcionPlazo = opcionPlazo;
	}



	/**
	 * @return the montoCuota
	 */
	public String getMontoCuota() {
		return montoCuota;
	}



	/**
	 * @param montoCuota the montoCuota to set
	 */
	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
