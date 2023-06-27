package com.alexis.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACTO database table.
 * 
 */
@Entity
@Table(name="CONTACTO")
@NamedQuery(name="Contacto.findAll", query="SELECT c FROM Contacto c")
public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CONTACTO")
	private long idContacto;

	@Column(name="ACTIVO")
	private int activo;

	@Column(name="APELLIDOS")
	private String apellidos;

	@Column(name="AVATAR")
	private String avatar;

	@Column(name="DIRECCION")
	private String direccion;

	@Column(name="EMAIL")
	private String email;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="TELEFONO")
	private String telefono;

	public Contacto() {
	}

	 

	public long getIdContacto() {
		return idContacto;
	}



	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
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

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}