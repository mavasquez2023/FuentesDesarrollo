package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.araucana.spl.util.Nulls;

public class RendicionBCI {
	private BigDecimal idRendicion;
	private String checksum = Nulls.STRING;
	private Date fchImportacion = Nulls.DATE;
	private Date fchRendicion = Nulls.DATE;
	private String empresaId = Nulls.STRING;
	private BigDecimal empresaRut = Nulls.BIGDECIMAL;
	private String empresaDv = Nulls.STRING;
	private String empresaNombre = Nulls.STRING;
	private BigDecimal totalRegistros = Nulls.BIGDECIMAL;
	private BigDecimal totalMontos = Nulls.BIGDECIMAL;
	private BigDecimal totalComisiones = Nulls.BIGDECIMAL;
	private BigDecimal cantidadAceptados = Nulls.BIGDECIMAL;
	private BigDecimal montoAceptados = Nulls.BIGDECIMAL;
	private BigDecimal cantidadRechazados = Nulls.BIGDECIMAL;
	private BigDecimal montoRechazados = Nulls.BIGDECIMAL;
	
	private List listErrorImportacion = new ArrayList();

	public String toString() {
		return new StringBuffer("[RENDICIONBCI::idRendicion=").append(idRendicion)
			.append(",checksum=").append(checksum)
			.append(",fchImportacion=").append(fchImportacion)
			.append(",fchRendicion=").append(fchRendicion)
			.append(",empresaId=").append(empresaId)
			.append(",empresaRut=").append(empresaRut)
			.append(",empresaDv=").append(empresaDv)
			.append(",empresaNombre=").append(empresaNombre)
			.append(",totalRegistros=").append(totalRegistros)
			.append(",totalMontos=").append(totalMontos)
			.append(",totalComisiones=").append(totalComisiones)
			.append(",cantidadAceptados=").append(cantidadAceptados)
			.append(",montoAceptados=").append(montoAceptados)
			.append(",cantidadRechazados=").append(cantidadRechazados)
			.append(",montoRechazados=").append(montoRechazados)
			.append("]").toString();
	}

	public BigDecimal getCantidadAceptados() {
		return cantidadAceptados;
	}

	public void setCantidadAceptados(BigDecimal cantidadAceptados) {
		this.cantidadAceptados = cantidadAceptados;
	}

	public BigDecimal getCantidadRechazados() {
		return cantidadRechazados;
	}

	public void setCantidadRechazados(BigDecimal cantidadRechazados) {
		this.cantidadRechazados = cantidadRechazados;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getEmpresaDv() {
		return empresaDv;
	}

	public void setEmpresaDv(String empresaDv) {
		this.empresaDv = empresaDv;
	}

	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}

	public String getEmpresaNombre() {
		return empresaNombre;
	}

	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}

	public BigDecimal getEmpresaRut() {
		return empresaRut;
	}

	public void setEmpresaRut(BigDecimal empresaRut) {
		this.empresaRut = empresaRut;
	}

	public Date getFchImportacion() {
		return fchImportacion;
	}

	public void setFchImportacion(Date fchImportacion) {
		this.fchImportacion = fchImportacion;
	}

	public Date getFchRendicion() {
		return fchRendicion;
	}

	public void setFchRendicion(Date fchRendicion) {
		this.fchRendicion = fchRendicion;
	}

	public BigDecimal getIdRendicion() {
		return idRendicion;
	}

	public void setIdRendicion(BigDecimal idRendicion) {
		this.idRendicion = idRendicion;
	}

	public BigDecimal getMontoAceptados() {
		return montoAceptados;
	}

	public void setMontoAceptados(BigDecimal montoAceptados) {
		this.montoAceptados = montoAceptados;
	}

	public BigDecimal getMontoRechazados() {
		return montoRechazados;
	}

	public void setMontoRechazados(BigDecimal montoRechazados) {
		this.montoRechazados = montoRechazados;
	}

	public BigDecimal getTotalComisiones() {
		return totalComisiones;
	}

	public void setTotalComisiones(BigDecimal totalComisiones) {
		this.totalComisiones = totalComisiones;
	}

	public BigDecimal getTotalMontos() {
		return totalMontos;
	}

	public void setTotalMontos(BigDecimal totalMontos) {
		this.totalMontos = totalMontos;
	}

	public BigDecimal getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(BigDecimal totalRegistros) {
		this.totalRegistros = totalRegistros;
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
