package cl.laaraucana.tarjetatam.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "View_TAM_Oficinas")
public class Oficina implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idOficina")
	private String codigoOficina;
	@Column(name = "Descripcion")
	private String descripcion;
	/**
	 * @return the codigoOficina
	 */
	public String getCodigoOficina() {
		return codigoOficina;
	}
	/**
	 * @param codigoOficina the codigoOficina to set
	 */
	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
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
