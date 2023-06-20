/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cl.laaraucana.reportesil.utils.Utils;

/**
 * @author IBM Software Factory
 *
 */
public class ResumenLicenciaVO implements Serializable{
	private int rutAfiliado;
	private String dvAfiliado;
	private String nombre;
	private String licencia;
	private int numinterno;
	private String estado;
	private Date fechaDesde;
	private Date fechaHasta;
	private int fechaHastaInt;
	private int tipoLicencia;
	private int dias;
	private int liquidada;
	private String primeraLicencia;
	private String reliquidada;
	private String email;
	private String celular;
	private String direccion;
	private String observacion;
	private String usuario;
	private String diagnostico;
	private String folioPago;
	private List<RentasVO> rentas;
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dvAfiliado
	 */
	public String getDvAfiliado() {
		return dvAfiliado;
	}
	/**
	 * @param dvAfiliado the dvAfiliado to set
	 */
	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the licencia
	 */
	public String getLicencia() {
		return licencia;
	}
	/**
	 * @param licencia the licencia to set
	 */
	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}
	/**
	 * @return the numinterno
	 */
	public int getNuminterno() {
		return numinterno;
	}
	/**
	 * @param numinterno the numinterno to set
	 */
	public void setNuminterno(int numinterno) {
		this.numinterno = numinterno;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @return the fechaDesde in String
	 */
	public String getFechaDesdeStr() {
		return Utils.dateToString2(fechaDesde);
	}
	
	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @return the fechaHasta in String
	 */
	public String getFechaHastaStr() {
		return Utils.dateToString2(fechaHasta);
	}
	/**
	 * @return the fechaHastaInt
	 */
	public int getFechaHastaInt() {
		return Integer.parseInt(Utils.dateToStringAS400(fechaHasta));
	}
	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	/**
	 * @return the dias
	 */
	public int getDias() {
		return dias;
	}
	/**
	 * @param dias the dias to set
	 */
	public void setDias(int dias) {
		this.dias = dias;
	}
	/**
	 * @return the liquidada
	 */
	public int getLiquidada() {
		return liquidada;
	}
	/**
	 * @param liquidada the liquidada to set
	 */
	public void setLiquidada(int liquidada) {
		this.liquidada = liquidada;
	}
	
	/**
	 * @return the tipolicencia
	 */
	public int getTipoLicencia() {
		return tipoLicencia;
	}
	/**
	 * @param tipolicencia the tipolicencia to set
	 */
	public void setTipoLicencia(int tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	
	/**
	 * @return the primeraLicencia
	 */
	public String getPrimeraLicencia() {
		return primeraLicencia;
	}
	/**
	 * @param primeraLicencia the primeraLicencia to set
	 */
	public void setPrimeraLicencia(String primeraLicencia) {
		this.primeraLicencia = primeraLicencia;
	}
	
	/**
	 * @return the reliquidada
	 */
	public String getReliquidada() {
		return reliquidada;
	}
	/**
	 * @param reliquidada the reliquidada to set
	 */
	public void setReliquidada(String reliquidada) {
		this.reliquidada = reliquidada;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the rentas
	 */
	public List<RentasVO> getRentas() {
		return rentas;
	}
	/**
	 * @param rentas the rentas to set
	 */
	public void setRentas(List<RentasVO> rentas) {
		this.rentas = rentas;
	}
	/**
	 * @return the diagnostico
	 */
	public String getDiagnostico() {
		return diagnostico;
	}
	/**
	 * @param diagnostico the diagnostico to set
	 */
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	/**
	 * @return the folioPago
	 */
	public String getFolioPago() {
		return folioPago;
	}
	/**
	 * @param folioPago the folioPago to set
	 */
	public void setFolioPago(String folioPago) {
		this.folioPago = folioPago;
	}
	
	
}
