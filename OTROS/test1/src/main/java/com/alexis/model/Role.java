package com.alexis.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the ROLE database table.
 * 
 */
@Entity
@Table(name="ROLE")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ROL")
	private long idRol;
	@Column(name="ROLE")
	private String role;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USER")
	private Usuario usuario;

	public Role() {
	}



	public long getIdRol() {
		return idRol;
	}



	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}



	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	 

}