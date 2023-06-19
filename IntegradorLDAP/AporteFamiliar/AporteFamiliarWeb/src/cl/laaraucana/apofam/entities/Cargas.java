package cl.laaraucana.apofam.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AporteFamiliarPermanente")
public class Cargas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "RutAfiliado")
	private int rutAfiliado;
	@Column(name = "DvAfiliado")
	private String dvAfiliado;
	@Column(name = "NombreAfiliado")
	private String nombreAfiliado;
	@Column(name = "NumCargas")
	private int numeroCargas;
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dvAfiliado
	 */
	public String getDvAfiliado() {
		return dvAfiliado;
	}
	/**
	 * @param dvAfiliado the dvAfiliado to set
	 */
	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}
	/**
	 * @return the nombreAfiliado
	 */
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	/**
	 * @param nombreAfiliado the nombreAfiliado to set
	 */
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	/**
	 * @return the numeroCargas
	 */
	public int getNumeroCargas() {
		return numeroCargas;
	}
	/**
	 * @param numeroCargas the numeroCargas to set
	 */
	public void setNumeroCargas(int numeroCargas) {
		this.numeroCargas = numeroCargas;
	}
	

	
}
