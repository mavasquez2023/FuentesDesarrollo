package cl.laaraucana.licenciascompin.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comunas")
public class Comuna implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "IdComuna")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Nombre")
	private String descripcion;
	@Column(name = "Slug")
	private String Slug;
	@Column(name = "Region")
	private int region;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSlug() {
		return Slug;
	}

	public void setSlug(String slug) {
		Slug = slug;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	 
}
