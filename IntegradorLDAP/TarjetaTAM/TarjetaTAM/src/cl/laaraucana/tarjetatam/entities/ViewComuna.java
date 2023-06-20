package cl.laaraucana.tarjetatam.entities;

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
@Table(name = "View_TAM_Comunas")
public class ViewComuna implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "IdComuna")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idComuna;
	@Column(name = "Nombre")
	private String descripcion;
	/**
	 * @return the idComuna
	 */
	public String getIdComuna() {
		return idComuna;
	}
	/**
	 * @param idComuna the idComuna to set
	 */
	public void setIdComuna(String idComuna) {
		this.idComuna = idComuna;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

	 
}
