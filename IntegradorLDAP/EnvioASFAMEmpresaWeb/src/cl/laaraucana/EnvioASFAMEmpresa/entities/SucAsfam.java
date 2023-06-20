package cl.laaraucana.EnvioASFAMEmpresa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the idSucAsfam database table.
 * 
 */
@Entity
@Table(name = "SucursalesAsfam")
public class SucAsfam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "idSucursal")
	private String idSucursal;

	@Column(name = "rutAnalista")
	private String rutAnalista;

	@Column(name = "email")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getRutAnalista() {
		return rutAnalista;
	}

	public void setRutAnalista(String rutAnalista) {
		this.rutAnalista = rutAnalista;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}