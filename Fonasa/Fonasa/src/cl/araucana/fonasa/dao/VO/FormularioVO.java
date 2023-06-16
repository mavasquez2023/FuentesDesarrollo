/**
 * 
 */
package cl.araucana.fonasa.dao.VO;

import cl.araucana.fonasa.business.to.ResponseFormFonasaTO;

/**
 * @author IBM Software Factory
 *
 */
public class FormularioVO {
private int codigoOficina;
private String nombreOficina;
private int fechaDesde;
private int fechaHasta;
private int estadoLicencia;
private int fechaPago;
private String rutAfiliado;
private int tipoFormulario;
private int numeroFormulario;
private int numeroLicencia;
private int folioPago;
private int folioTesoreria;
private String estadoPago;

ResponseFormFonasaTO responseWS;

public int getCodigoOficina() {
	return codigoOficina;
}
public void setCodigoOficina(int codigoOficina) {
	this.codigoOficina = codigoOficina;
}
public String getNombreOficina() {
	return nombreOficina;
}
public void setNombreOficina(String nombreOficina) {
	this.nombreOficina = nombreOficina;
}
public int getFechaDesde() {
	return fechaDesde;
}
public void setFechaDesde(int fechaDesde) {
	this.fechaDesde = fechaDesde;
}
public int getFechaHasta() {
	return fechaHasta;
}
public void setFechaHasta(int fechaHasta) {
	this.fechaHasta = fechaHasta;
}
public int getEstadoLicencia() {
	return estadoLicencia;
}
public void setEstadoLicencia(int estadoLicencia) {
	this.estadoLicencia = estadoLicencia;
}
public int getFechaPago() {
	return fechaPago;
}
public void setFechaPago(int fechaPago) {
	this.fechaPago = fechaPago;
}
public String getRutAfiliado() {
	return rutAfiliado;
}
public void setRutAfiliado(String rutAfiliado) {
	this.rutAfiliado = rutAfiliado;
}

public int getTipoFormulario() {
	return tipoFormulario;
}
public void setTipoFormulario(int tipoFormulario) {
	this.tipoFormulario = tipoFormulario;
}
public int getNumeroFormulario() {
	return numeroFormulario;
}
public void setNumeroFormulario(int numeroFormulario) {
	this.numeroFormulario = numeroFormulario;
}

public int getNumeroLicencia() {
	return numeroLicencia;
}
public void setNumeroLicencia(int numeroLicencia) {
	this.numeroLicencia = numeroLicencia;
}
public int getFolioPago() {
	return folioPago;
}
public void setFolioPago(int folioPago) {
	this.folioPago = folioPago;
}
public int getFolioTesoreria() {
	return folioTesoreria;
}
public void setFolioTesoreria(int folioTesoreria) {
	this.folioTesoreria = folioTesoreria;
}
public String getEstadoPago() {
	return estadoPago;
}
public void setEstadoPago(String estadoPago) {
	this.estadoPago = estadoPago;
}
public ResponseFormFonasaTO getResponseWS() {
	return responseWS;
}
public void setResponseWS(ResponseFormFonasaTO responseWS) {
	this.responseWS = responseWS;
}
//****************
public short getEstado() {
	return responseWS.getEstado();
}
public void setEstado(short estado) {
	this.responseWS.setEstado(estado);
}
public String getGlosaEstado() {
	return responseWS.getGlosaEstado();
}
public void setGlosaEstado(String glosaEstado) {
	this.responseWS.setGlosaEstado(glosaEstado);
}
public short getCodEstado() {
	return responseWS.getCodEstado();
}
public void setCodEstado(short codEstado) {
	this.responseWS.setCodEstado(codEstado);
}
public String getComentario() {
	return responseWS.getComentario();
}
public void setComentario(String comentario) {
	this.responseWS.setComentario(comentario);
}
public String getFechaEvento() {
	return responseWS.getFechaEvento();
}
public void setFechaEvento(String fechaEvento) {
	this.responseWS.setFechaEvento(fechaEvento);
}


}
