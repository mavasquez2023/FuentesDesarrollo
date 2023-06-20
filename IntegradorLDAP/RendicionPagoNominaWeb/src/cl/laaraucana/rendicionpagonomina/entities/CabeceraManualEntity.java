package cl.laaraucana.rendicionpagonomina.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.CargaManualCab", schema = "TEFDTA")
public class CabeceraManualEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCabecera")
	private long idCabecera;
	@Column(name = "FechaCarga")
	private Date fechaCarga;
	@Column(name = "IdCodConv")
	private String convenio;
	@Column(name = "CodProd")
	private String producto;
	@Column(name = "TotReg")
	private int totalRegistros;
	@Column(name = "TotMonto")
	private long totalMonto;
	@Column(name = "IdEstado")
	private int estado;
	@Column(name = "TotRegPend")
	private int totalPendientes;
	@Column(name = "TotMonPend")
	private long montoPendiente;
	@Column(name = "FechaPago")
	private Date fechaPago;
	@Column(name = "FechaCreacion")
	private Date fechaCreacion;
	@Column(name = "UsrCreacion")
	private String usuarioCreacion;
	/**
	 * @return the idCabecera
	 */
	public long getIdCabecera() {
		return idCabecera;
	}
	/**
	 * @param idCabecera the idCabecera to set
	 */
	public void setIdCabecera(long idCabecera) {
		this.idCabecera = idCabecera;
	}
	/**
	 * @return the fechaCarga
	 */
	public Date getFechaCarga() {
		return fechaCarga;
	}
	/**
	 * @param fechaCarga the fechaCarga to set
	 */
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	/**
	 * @return the convenio
	 */
	public String getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio the convenio to set
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	/**
	 * @return the totalRegistros
	 */
	public int getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	/**
	 * @return the totalMonto
	 */
	public long getTotalMonto() {
		return totalMonto;
	}
	/**
	 * @param totalMonto the totalMonto to set
	 */
	public void setTotalMonto(long totalMonto) {
		this.totalMonto = totalMonto;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return the totalPendientes
	 */
	public int getTotalPendientes() {
		return totalPendientes;
	}
	/**
	 * @param totalPendientes the totalPendientes to set
	 */
	public void setTotalPendientes(int totalPendientes) {
		this.totalPendientes = totalPendientes;
	}
	/**
	 * @return the montoPendiente
	 */
	public long getMontoPendiente() {
		return montoPendiente;
	}
	/**
	 * @param montoPendiente the montoPendiente to set
	 */
	public void setMontoPendiente(long montoPendiente) {
		this.montoPendiente = montoPendiente;
	}
	/**
	 * @return the fechaPago
	 */
	public Date getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	
}
