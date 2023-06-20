package cl.laaraucana.diferimientoEspecial.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BitDiferimientoEspecial")
public class BitaEspecialEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBita")
	private long idBita;
	@Column(name = "rutAfi")
	private long rutAfi;
	@Column(name = "dvAfi")
	private String dvAfi;
	@Column(name = "foliocre")
	private String foliocre;
	@Column(name = "numcuota")
	private long numcuota;
	@Column(name = "montocuota")
	private long montocuota;
	@Column(name = "fecVencActual")
	private Date fecVencActual;
	@Column(name = "fecVencNuevo")
	private Date fecVencNuevo;
	@Column(name = "rutEmp")
	private long rutEmp;
	@Column(name = "dvEmp")
	private String dvEmp;
	@Column(name = "correo")
	private String correo;
	@Column(name = "autorizado")
	private String autorizado;
	@Column(name = "serie")
	private String serie;
	@Column(name = "fecAutorizacion")
	private Date fecAutorizacion;

	public long getIdBita() {
		return idBita;
	}

	public void setIdBita(long idBita) {
		this.idBita = idBita;
	}

	public long getRutAfi() {
		return rutAfi;
	}

	public void setRutAfi(long rutAfi) {
		this.rutAfi = rutAfi;
	}

	public String getDvAfi() {
		return dvAfi;
	}

	public void setDvAfi(String dvAfi) {
		this.dvAfi = dvAfi;
	}

	public String getFoliocre() {
		return foliocre;
	}

	public void setFoliocre(String foliocre) {
		this.foliocre = foliocre;
	}

	public long getNumcuota() {
		return numcuota;
	}

	public void setNumcuota(long numcuota) {
		this.numcuota = numcuota;
	}

	public long getMontocuota() {
		return montocuota;
	}

	public void setMontocuota(long montocuota) {
		this.montocuota = montocuota;
	}

	public Date getFecVencActual() {
		return fecVencActual;
	}

	public void setFecVencActual(Date fecVencActual) {
		this.fecVencActual = fecVencActual;
	}

	public Date getFecVencNuevo() {
		return fecVencNuevo;
	}

	public void setFecVencNuevo(Date fecVencNuevo) {
		this.fecVencNuevo = fecVencNuevo;
	}

	public long getRutEmp() {
		return rutEmp;
	}

	public void setRutEmp(long rutEmp) {
		this.rutEmp = rutEmp;
	}

	public String getDvEmp() {
		return dvEmp;
	}

	public void setDvEmp(String dvEmp) {
		this.dvEmp = dvEmp;
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

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Date getFecAutorizacion() {
		return fecAutorizacion;
	}

	public void setFecAutorizacion(Date fecAutorizacion) {
		this.fecAutorizacion = fecAutorizacion;
	}

}
