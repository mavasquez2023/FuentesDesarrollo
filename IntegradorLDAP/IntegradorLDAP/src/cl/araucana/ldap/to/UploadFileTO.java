/**
 * 
 */
package cl.araucana.ldap.to;

import cl.araucana.core.util.Rut;

/**
 * @author usist199
 *
 */
public class UploadFileTO {
private Rut rut;
private String estado;
private String celular;
private String email;
private String nombre="Usuario";
private String apellido="NN";

/**
 * @return el estado
 */
public String getEstado() {
	return estado;
}
/**
 * @param estado el estado a establecer
 */
public void setEstado(String estado) {
	this.estado = estado;
}
/**
 * @return el rut
 */
public Rut getRut() {
	return rut;
}
/**
 * @param rut el rut a establecer
 */
public void setRut(Rut rut) {
	this.rut = rut;
}
/**
 * @return el celular
 */
public String getCelular() {
	return celular;
}
/**
 * @param celular el celular a establecer
 */
public void setCelular(String celular) {
	this.celular = celular;
}
/**
 * @return el email
 */
public String getEmail() {
	return email;
}
/**
 * @param email el email a establecer
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return el apellido
 */
public String getApellido() {
	return apellido;
}
/**
 * @param apellido el apellido a establecer
 */
public void setApellido(String apellido) {
	this.apellido = apellido;
}
/**
 * @return el nombre
 */
public String getNombre() {
	return nombre;
}
/**
 * @param nombre el nombre a establecer
 */
public void setNombre(String nombre) {
	this.nombre = nombre;
}


}
