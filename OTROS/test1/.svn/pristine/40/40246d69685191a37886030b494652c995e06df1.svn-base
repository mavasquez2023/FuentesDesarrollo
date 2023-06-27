package com.alexis.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@Table(name="USUARIO")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;

	@Column(name="ACTIVO")
	private int activo;

	@Column(name="APELLIDOS")
	private String apellidos;

	@Column(name="DIRECCION")
	private String direccion;

	@Column(name="EMAIL")
	private String email;

	@Column(name="NOMBRES")
	private String nombres;

	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="TELEFONO")
	private String telefono;

	@Column(name="USERNAME")
	private String username;
  

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles;

	public Usuario() {
	}

	 

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getActivo() {
		return this.activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
	public List<Role> getRoles() {
		return this.roles;
	}
    
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setUsuario(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setUsuario(null);

		return role;
	}

}