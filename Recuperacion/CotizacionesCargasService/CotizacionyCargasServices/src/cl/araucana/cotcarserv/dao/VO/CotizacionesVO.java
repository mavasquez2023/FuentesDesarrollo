/**
 * 
 */
package cl.araucana.cotcarserv.dao.VO;

import java.text.ParseException;
import java.util.Date;

import cl.laaraucana.satelites.Utils.Utils;

/**
 * @author J-Factory
 *
 */
public class CotizacionesVO {
	private int periodo;
	private int rutEmpresa;
	private String dvEmpresa;
	private String oficina;
	private String sucursal;
	private int rutTrabajador;
	private String dvTrabajador;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fechaAfiliacion;
	private String fechaDesvinculacion;
	private String razonSocial;
	private Date dateDesvinculacion;
	private String estado;
	
	/**
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the rutEmpresa
	 */
	public int getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return the dvEmpresa
	 */
	public String getDvEmpresa() {
		return dvEmpresa;
	}
	/**
	 * @param dvEmpresa the dvEmpresa to set
	 */
	public void setDvEmpresa(String dvEmpresa) {
		this.dvEmpresa = dvEmpresa;
	}
	/**
	 * @return the oficina
	 */
	public String getOficina() {
		return oficina;
	}
	/**
	 * @param oficina the oficina to set
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the rutTrabajador
	 */
	public int getRutTrabajador() {
		return rutTrabajador;
	}
	/**
	 * @param rutTrabajador the rutTrabajador to set
	 */
	public void setRutTrabajador(int rutTrabajador) {
		this.rutTrabajador = rutTrabajador;
	}
	/**
	 * @return the dvTrabajador
	 */
	public String getDvTrabajador() {
		return dvTrabajador;
	}
	/**
	 * @param dvTrabajador the dvTrabajador to set
	 */
	public void setDvTrabajador(String dvTrabajador) {
		this.dvTrabajador = dvTrabajador;
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
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return the fechaAfiliacion
	 * @throws ParseException 
	 */
	public String getFechaAfiliacion() throws ParseException {
		return fechaAfiliacion;
	}
	/**
	 * @return the fechaAfiliacion
	 * @throws ParseException 
	 */
	public String getFechaAfiliacionCSV() throws ParseException {
		return Utils.pasaFechaSAPaWEB(fechaAfiliacion.trim());
	}

	/**
	 * @param fechaAfiliacion the fechaAfiliacion to set
	 */
	public void setFechaAfiliacion(String fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}
	/**
	 * @return the fechaDesvinculacion
	 * @throws ParseException 
	 */
	public String getFechaDesvinculacion() throws ParseException {
		return fechaDesvinculacion;
	}

	/**
	 * @param fechaDesvinculacion the fechaDesvinculacion to set
	 */
	public void setFechaDesvinculacion(String fechaDesvinculacion) {
		this.fechaDesvinculacion = fechaDesvinculacion;
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the dateDesvinculacion
	 */
	public Date getDateDesvinculacion() {
		return dateDesvinculacion;
	}
	/**
	 * @param dateDesvinculacion the dateDesvinculacion to set
	 */
	public void setDateDesvinculacion(Date dateDesvinculacion) {
		this.dateDesvinculacion = dateDesvinculacion;
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
	
}
