package cl.laaraucana.licenciascompin.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sucLicencias")
public class SucLicencias implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idSucLicencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "idComuna")
	private int idcomuna;
	@Column(name = "sucursal")
	private String sucursal;
	@Column(name = "email")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIdcomuna() {
		return idcomuna;
	}

	public void setIdcomuna(int idcomuna) {
		this.idcomuna = idcomuna;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
