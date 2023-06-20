package cl.laaraucana.EnvioASFAMEmpresa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RegistroAsfam")
public class RegistroEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "rutAfi")
	private long rutAfi;
	@Column(name = "dvAfi")
	private String dvAfi;
	@Column(name = "nomafi")
	private String nomafi;
	@Column(name = "telefono")
	private String telefono;
	@Column(name = "email")
	private String email;
	@Column(name = "viaIngreso")
	private String viaIngreso;
	@Column(name = "idSucursal")
	private String sucursal;
	@Column(name = "FECCRE")
	private Date FECCRE;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getNomafi() {
		return nomafi;
	}

	public void setNomafi(String nomafi) {
		this.nomafi = nomafi;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getViaIngreso() {
		return viaIngreso;
	}

	public void setViaIngreso(String viaIngreso) {
		this.viaIngreso = viaIngreso;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFECCRE() {
		return FECCRE;
	}

	public void setFECCRE(Date fECCRE) {
		FECCRE = fECCRE;
	}

}
