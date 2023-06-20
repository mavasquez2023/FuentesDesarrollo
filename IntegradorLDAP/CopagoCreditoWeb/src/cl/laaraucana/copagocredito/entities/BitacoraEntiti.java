package cl.laaraucana.copagocredito.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BitacoraProgramaCopago")
public class BitacoraEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBita")
	private long idBita;
	@Column(name = "RutCliente")
	private long rutCliente;
	@Column(name = "dvCliente")
	private String dvCliente;
	@Column(name = "NCredito")
	private long nCredito;
	@Column(name = "Ncuota")
	private long ncuota;
	@Column(name = "ValorCuota")
	private long valorCuota;
	@Column(name = "MontoBeneficio")
	private long montoBeneficio;
	@Column(name = "Correo")
	private String correo;
	@Column(name = "Autorizado")
	private String autorizado;
	@Column(name = "Fecha")
	private Date fecha;

	public long getIdBita() {
		return idBita;
	}

	public void setIdBita(long idBita) {
		this.idBita = idBita;
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

	public long getnCredito() {
		return nCredito;
	}

	public void setnCredito(long nCredito) {
		this.nCredito = nCredito;
	}

	public long getNcuota() {
		return ncuota;
	}

	public void setNcuota(long ncuota) {
		this.ncuota = ncuota;
	}

	public long getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(long valorCuota) {
		this.valorCuota = valorCuota;
	}

	public long getMontoBeneficio() {
		return montoBeneficio;
	}

	public void setMontoBeneficio(long montoBeneficio) {
		this.montoBeneficio = montoBeneficio;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
