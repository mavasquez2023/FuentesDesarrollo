package cl.laaraucana.certificadodiferimiento.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BitacoraDiferimiento")
public class BitacoraEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBitacora")
	private long idBitacora;
	@Column(name = "rutAfiliado")
	private long rutAfiliado;
	@Column(name = "dvAfiliado")
	private String dvAfiliado;
	@Column(name = "folioCredito")
	private String folioCredito;
	@Column(name = "numCuotaDiferir")
	private int numCuotaDiferir;
	@Column(name = "montoCuota")
	private long montoCuota;
	@Column(name = "fechaVencActual")
	private Date fechaVencActual;
	@Column(name = "fechaVencNuevo")
	private Date fechaVencNuevo;
	@Column(name = "rutEmpresa")
	private long rutEmpresa;
	@Column(name = "dvEmpresa")
	private String dvEmpresa;
	@Column(name = "correo")
	private String correo;
	@Column(name = "autorizado")
	private String autorizado;
	@Column(name = "nSerie")
	private String nSerie;
	@Column(name = "fechaAutorizacion")
	private Date fechaAutorizacion;
	@Column(name = "celular")
	private String celular;
	@Column(name = "codigoVerificacion")
	private String codigoVerificacion;
	@Column(name = "ipacceso")
	private String ipacceso;
	 

	public long getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(long idBitacora) {
		this.idBitacora = idBitacora;
	}

	public long getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(long rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public String getDvAfiliado() {
		return dvAfiliado;
	}

	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}

	public String getFolioCredito() {
		return folioCredito;
	}

	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}

	public int getNumCuotaDiferir() {
		return numCuotaDiferir;
	}

	public void setNumCuotaDiferir(int numCuotaDiferir) {
		this.numCuotaDiferir = numCuotaDiferir;
	}

	public long getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(long montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Date getFechaVencActual() {
		return fechaVencActual;
	}

	public void setFechaVencActual(Date fechaVencActual) {
		this.fechaVencActual = fechaVencActual;
	}

	public Date getFechaVencNuevo() {
		return fechaVencNuevo;
	}

	public void setFechaVencNuevo(Date fechaVencNuevo) {
		this.fechaVencNuevo = fechaVencNuevo;
	}

	public long getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(long rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getDvEmpresa() {
		return dvEmpresa;
	}

	public void setDvEmpresa(String dvEmpresa) {
		this.dvEmpresa = dvEmpresa;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	public String getnSerie() {
		return nSerie;
	}

	public void setnSerie(String nSerie) {
		this.nSerie = nSerie;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
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
	 * @return the codigoVerificacion
	 */
	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}

	/**
	 * @param codigoVerificacion the codigoVerificacion to set
	 */
	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}

	/**
	 * @return the ipacceso
	 */
	public String getIpacceso() {
		return ipacceso;
	}

	/**
	 * @param ipacceso the ipacceso to set
	 */
	public void setIpacceso(String ipacceso) {
		this.ipacceso = ipacceso;
	}
	
	

}
