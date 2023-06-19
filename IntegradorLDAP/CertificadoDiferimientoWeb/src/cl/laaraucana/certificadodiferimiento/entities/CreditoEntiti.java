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
@Table(name = "CreditoDiferimiento")
public class CreditoEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCredito")
	private long idCredito;
	@Column(name = "rutCliente")
	private long rutCliente;
	@Column(name = "dvCliente")
	private String dvCliente;
	@Column(name = "folioCredito")
	private String folioCredito;
	@Column(name = "cuotaDiferir")
	private long cuotaDiferir;
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
	@Column(name = "nombreEmpresa")
	private String nombreEmpresa;

	public long getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(long idCredito) {
		this.idCredito = idCredito;
	}

	public long getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(long rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}

	public String getFolioCredito() {
		return folioCredito;
	}

	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}

	public long getCuotaDiferir() {
		return cuotaDiferir;
	}

	public void setCuotaDiferir(long cuotaDiferir) {
		this.cuotaDiferir = cuotaDiferir;
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

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

}
