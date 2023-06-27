package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) Persona.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class Persona implements Serializable 
{
	private static final long serialVersionUID = 458208747980746335L;
	private String idPersonaFmt = "";
	private int idComuna;
	private int idRegion;
	private int idCiudad;
	private int idHabilitado;
	private String nombres = "";
	private String apellidoPaterno = "";
	private String apellidoMaterno = "";
	private String apellidos = "";
	private String email = "";
	private String telefono = "";
	private String codTelefono = "";
	private String celular;
	private String fax = "";
	private String codFax = "";
	private String direccion = "";
	private String numero = "";
	private String dpto = "";
	private boolean adminAraucana;

	public Persona() 
	{
		super();
	}

	/**
	 * administrador araucana
	 * @return
	 */
	public boolean isAdminAraucana() {
		return this.adminAraucana;
	}
	/**
	 * administrador araucana
	 * @param adminAraucana
	 */
	public void setAdminAraucana(boolean adminAraucana) {
		this.adminAraucana = adminAraucana;
	}
	/**
	 * apellido materno
	 * @return
	 */
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}
	/**
	 * apellido materno
	 * @param apellidoMaterno
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * apellido paterno
	 * @return
	 */
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}
	/**
	 * apellido paterno
	 * @param apellidoPaterno
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * apellidos
	 * @return
	 */
	public String getApellidos() {
		return this.apellidos;
	}
	/**
	 * apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * celular
	 * @return
	 */
	public String getCelular() {
		return this.celular;
	}
	/**
	 * celular
	 * @param celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * direccion
	 * @return
	 */
	public String getDireccion() {
		return this.direccion;
	}
	/**
	 * direccion
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * departamento
	 * @return
	 */
	public String getDpto() {
		return this.dpto;
	}
	/**
	 * departamento
	 * @param dpto
	 */
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	/**
	 * correo
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * correo
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * fax
	 * @return
	 */
	public String getFax() {
		return this.fax;
	}
	/**
	 * fax
	 * @param fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * id comuna
	 * @return
	 */
	public int getIdComuna() {
		return this.idComuna;
	}
	/**
	 * id comuna
	 * @param idComuna
	 */
	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
	}
	/**
	 * id persona fmt
	 * @return
	 */
	public String getIdPersonaFmt() {
		return this.idPersonaFmt;
	}
	/**
	 * id persona fmt
	 * @param idPersonaFmt
	 */
	public void setIdPersonaFmt(String idPersonaFmt) {
		this.idPersonaFmt = idPersonaFmt;
	}
	/**
	 * nombres
	 * @return
	 */
	public String getNombres() {
		return this.nombres;
	}
	/**
	 * nombres
	 * @param nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * numero
	 * @return
	 */
	public String getNumero() {
		return this.numero;
	}
	/**
	 * numero
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * telefono
	 * @return
	 */
	public String getTelefono() {
		return this.telefono;
	}
	/**
	 * telefono
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * codigo fax
	 * @return
	 */
	public String getCodFax() {
		return this.codFax;
	}
	/**
	 * codigo fax
	 * @param codFax
	 */
	public void setCodFax(String codFax) {
		this.codFax = codFax;
	}
	/**
	 * codigo telefono
	 * @return
	 */
	public String getCodTelefono() {
		return this.codTelefono;
	}
	/**
	 * codigo telefono
	 * @param codTelefono
	 */
	public void setCodTelefono(String codTelefono) {
		this.codTelefono = codTelefono;
	}

	/**
	 * ud ciudad
	 * @return
	 */
	public int getIdCiudad() {
		return this.idCiudad;
	}
	/**
	 * id ciudad
	 * @param idCiudad
	 */
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	/**
	 * id region
	 * @return
	 */
	public int getIdRegion() {
		return this.idRegion;
	}

	/**
	 * id region
	 * @param idRegion
	 */
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	/**
	 * id habilitado
	 * @return
	 */
	public int getIdHabilitado() {
		return this.idHabilitado;
	}
	/**
	 * id habilitado
	 * @param idHabilitado
	 */
	public void setIdHabilitado(int idHabilitado) {
		this.idHabilitado = idHabilitado;
	}

}
