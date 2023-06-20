package cl.laaraucana.rendicionpagonomina.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dbo.NominaTefCab", schema = "TEFDTA")
public class CabeceraEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCabecera")
	private long idCabecera;
	@Column(name = "IdCodConv")
	private String convenio;
	@Column(name = "CodProd")
	private String producto;
	@Column(name = "FechaEnvio")
	private Date fechaEnvio;
	@Column(name = "NombreNomina")
	private String nombreNomina;
	@Column(name = "CodigoNomina")
	private long codigoNomina;
	@Column(name = "Monto")
	private long monto;
	@Column(name = "Cantidad")
	private int cantidad;
	@Column(name = "EstadoNomina")
	private int estadoNomina;
	@Column(name = "TotalPagado")
	private long totalPagado;
	@Column(name = "CantPagado")
	private int cantidadPagado;
	@Column(name = "TotalRechazado")
	private long totalRechazado;
	@Column(name = "CantRechazado")
	private int cantidadRechazado;
	@Column(name = "TotalDevuelto")
	private long totalDevuelto;
	@Column(name = "CantDevuelto")
	private int cantidadDevuelto;
	@Column(name = "CodRechazoEnvio")
	private int codigoRechazoEnvio;
	@Column(name = "GlosaRechazoEnvio")
	private String glosaRechazoEnvio;
	@Column(name = "CodRechazoRendicion")
	private int codigoRechazoRendicion;
	@Column(name = "GlosaRechazoRendicion")
	private String glosaRechazoRendicion;
	@Column(name = "FechaRendicion")
	private Date FechaRendicion;
	@Column(name = "Plano")
	private byte[] plano;
	@Column(name = "FechaCreacion")
	private Date fechaCreacion;
	@Column(name = "crc")
	private String crc;
	
	@Transient
	private int pendientes;
	@Transient
	private String codigoBanco;

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
	 * @return the fechaEnvio
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * @return the nombreNomina
	 */
	public String getNombreNomina() {
		return nombreNomina;
	}

	/**
	 * @param nombreNomina the nombreNomina to set
	 */
	public void setNombreNomina(String nombreNomina) {
		this.nombreNomina = nombreNomina;
	}

	/**
	 * @return the codigoNomina
	 */
	public long getCodigoNomina() {
		return codigoNomina;
	}

	/**
	 * @param codigoNomina the codigoNomina to set
	 */
	public void setCodigoNomina(long codigoNomina) {
		this.codigoNomina = codigoNomina;
	}

	/**
	 * @return the monto
	 */
	public long getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(long monto) {
		this.monto = monto;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the estadoNomina
	 */
	public int getEstadoNomina() {
		return estadoNomina;
	}

	/**
	 * @param estadoNomina the estadoNomina to set
	 */
	public void setEstadoNomina(int estadoNomina) {
		this.estadoNomina = estadoNomina;
	}

	/**
	 * @return the totalPagado
	 */
	public long getTotalPagado() {
		return totalPagado;
	}

	/**
	 * @param totalPagado the totalPagado to set
	 */
	public void setTotalPagado(long totalPagado) {
		this.totalPagado = totalPagado;
	}

	/**
	 * @return the cantidadPagado
	 */
	public int getCantidadPagado() {
		return cantidadPagado;
	}

	/**
	 * @param cantidadPagado the cantidadPagado to set
	 */
	public void setCantidadPagado(int cantidadPagado) {
		this.cantidadPagado = cantidadPagado;
	}

	/**
	 * @return the totalRechazado
	 */
	public long getTotalRechazado() {
		return totalRechazado;
	}

	/**
	 * @param totalRechazado the totalRechazado to set
	 */
	public void setTotalRechazado(long totalRechazado) {
		this.totalRechazado = totalRechazado;
	}

	/**
	 * @return the cantidadRechazado
	 */
	public int getCantidadRechazado() {
		return cantidadRechazado;
	}

	/**
	 * @param cantidadRechazado the cantidadRechazado to set
	 */
	public void setCantidadRechazado(int cantidadRechazado) {
		this.cantidadRechazado = cantidadRechazado;
	}

	/**
	 * @return the totalDevuelto
	 */
	public long getTotalDevuelto() {
		return totalDevuelto;
	}

	/**
	 * @param totalDevuelto the totalDevuelto to set
	 */
	public void setTotalDevuelto(long totalDevuelto) {
		this.totalDevuelto = totalDevuelto;
	}

	/**
	 * @return the cantidadDevuelto
	 */
	public int getCantidadDevuelto() {
		return cantidadDevuelto;
	}

	/**
	 * @param cantidadDevuelto the cantidadDevuelto to set
	 */
	public void setCantidadDevuelto(int cantidadDevuelto) {
		this.cantidadDevuelto = cantidadDevuelto;
	}

	/**
	 * @return the codigoRechazoEnvio
	 */
	public int getCodigoRechazoEnvio() {
		return codigoRechazoEnvio;
	}

	/**
	 * @param codigoRechazoEnvio the codigoRechazoEnvio to set
	 */
	public void setCodigoRechazoEnvio(int codigoRechazoEnvio) {
		this.codigoRechazoEnvio = codigoRechazoEnvio;
	}

	/**
	 * @return the glosaRechazoEnvio
	 */
	public String getGlosaRechazoEnvio() {
		return glosaRechazoEnvio;
	}

	/**
	 * @param glosaRechazoEnvio the glosaRechazoEnvio to set
	 */
	public void setGlosaRechazoEnvio(String glosaRechazoEnvio) {
		this.glosaRechazoEnvio = glosaRechazoEnvio;
	}

	/**
	 * @return the codigoRechazoRendicion
	 */
	public int getCodigoRechazoRendicion() {
		return codigoRechazoRendicion;
	}

	/**
	 * @param codigoRechazoRendicion the codigoRechazoRendicion to set
	 */
	public void setCodigoRechazoRendicion(int codigoRechazoRendicion) {
		this.codigoRechazoRendicion = codigoRechazoRendicion;
	}

	/**
	 * @return the glosaRechazoRendicion
	 */
	public String getGlosaRechazoRendicion() {
		return glosaRechazoRendicion;
	}

	/**
	 * @param glosaRechazoRendicion the glosaRechazoRendicion to set
	 */
	public void setGlosaRechazoRendicion(String glosaRechazoRendicion) {
		this.glosaRechazoRendicion = glosaRechazoRendicion;
	}

	/**
	 * @return the fechaRendicion
	 */
	public Date getFechaRendicion() {
		return FechaRendicion;
	}

	/**
	 * @param fechaRendicion the fechaRendicion to set
	 */
	public void setFechaRendicion(Date fechaRendicion) {
		FechaRendicion = fechaRendicion;
	}

	/**
	 * @return the plano
	 */
	public byte[] getPlano() {
		return plano;
	}

	/**
	 * @param plano the plano to set
	 */
	public void setPlano(byte[] plano) {
		this.plano = plano;
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
	 * @return the pendientes
	 */
	public int getPendientes() {
		return pendientes;
	}

	/**
	 * @param pendientes the pendientes to set
	 */
	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}

	/**
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the crc
	 */
	public String getCrc() {
		return crc;
	}

	/**
	 * @param crc the crc to set
	 */
	public void setCrc(String crc) {
		this.crc = crc;
	}
	
		
}
