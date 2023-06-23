package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RendicionBCH {
	private BigDecimal idRendicion;
	private String checksum;
	private Date fchImportacion;
	private Date fchCaptura;
	private BigDecimal nroRegistros;

	private List listErrorImportacion = new ArrayList();
	
	public String toString() {
		return new StringBuffer("[RENDICIONBCI::idRendicion=").append(idRendicion)
			.append(",checksum=").append(checksum)
			.append(",fchImportacion=").append(fchImportacion)
			.append(",fchCaptura=").append(fchCaptura)
			.append(",nroRegistros=").append(nroRegistros)			
			.append("]").toString();
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public Date getFchCaptura() {
		return fchCaptura;
	}

	public void setFchCaptura(Date fchCaptura) {
		this.fchCaptura = fchCaptura;
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

	public BigDecimal getNroRegistros() {
		return nroRegistros;
	}

	public void setNroRegistros(BigDecimal nroRegistros) {
		this.nroRegistros = nroRegistros;
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
