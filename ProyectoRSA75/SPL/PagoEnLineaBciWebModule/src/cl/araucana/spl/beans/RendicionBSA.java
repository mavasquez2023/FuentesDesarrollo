package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RendicionBSA {
	private BigDecimal idRendicion;
	private String checksum;
	private Date fchImportacion;
	private BigDecimal nroPagos;
	private BigDecimal montoTotal;
	
	private List listErrorImportacion = new ArrayList();
	
	public String toString() {
		return new StringBuffer("[RENDICIONBCI::idRendicion=").append(idRendicion)
			.append(",checksum=").append(checksum)
			.append(",fchImportacion=").append(fchImportacion)
			.append(",nroPagos=").append(nroPagos)
			.append(",montoTotal=").append(montoTotal)
			.append("]").toString();
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public Date getFchImportacion() {
		return fchImportacion;
	}

	public void setFchImportacion(Date fchImportacion) {
		this.fchImportacion = fchImportacion;
	}

	public BigDecimal getIdRendicion() {
		return idRendicion;
	}

	public void setIdRendicion(BigDecimal idRendicion) {
		this.idRendicion = idRendicion;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getNroPagos() {
		return nroPagos;
	}

	public void setNroPagos(BigDecimal nroPagos) {
		this.nroPagos = nroPagos;
	}

	/**
	 * @return the listErrorImportacion
	 */
	public List getListErrorImportacion() {
		return listErrorImportacion;
	}

	/**
	 * @param listErrorImportacion the listErrorImportacion to set
	 */
	public void setListErrorImportacion(List listErrorImportacion) {
		this.listErrorImportacion = listErrorImportacion;
	}

}
