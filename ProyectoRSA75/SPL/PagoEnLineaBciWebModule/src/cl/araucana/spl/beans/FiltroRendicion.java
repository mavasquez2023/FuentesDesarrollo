package cl.araucana.spl.beans;

import java.math.BigDecimal;


public class FiltroRendicion {
	private BigDecimal idConvenio;
	private String fechasContables;
	private String idsPagos;
	
	/**
	 * @return the idsPagos
	 */
	public String getIdsPagos() {
		return idsPagos;
	}

	/**
	 * @param idsPagos the idsPagos to set
	 */
	public void setIdsPagos(String idsPagos) {
		this.idsPagos = idsPagos;
	}

	public String toString() {
		return new StringBuffer("[FILTRO::idConvenio=").append(idConvenio)
			.append(",fechasContables=").append(fechasContables)
			.append("]").toString();
	}

	/**
	 * @return the fechasContables
	 */
	public String getFechasContables() {
		return fechasContables;
	}

	/**
	 * @param fechasContables the fechasContables to set
	 */
	public void setFechasContables(String fechasContables) {
		this.fechasContables = fechasContables;
	}

	/**
	 * @return the idConvenio
	 */
	public BigDecimal getIdConvenio() {
		return idConvenio;
	}

	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(BigDecimal idConvenio) {
		this.idConvenio = idConvenio;
	}
	
	
}

