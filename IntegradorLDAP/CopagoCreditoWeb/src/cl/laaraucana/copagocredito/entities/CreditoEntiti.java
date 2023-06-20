package cl.laaraucana.copagocredito.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProgramaCopago")
public class CreditoEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
