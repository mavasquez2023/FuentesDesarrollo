package cl.araucana.adminCpe.presentation.struts.forms.usuario;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ListarAUsuariosActionForm.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.4
 */
public class ListarUsuariosActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -5355975351170549992L;

	private List consulta;
	private String rut;
	private String nombre;
	private String apellidos;
	//vagurto
	private String mostrarLista;
	private String nombreGrupoConvenio;
	private String codigoGrupoConvenio;
	private String rutEmpresa;
	private String razonSocial;
	private Collection numeroFilas;

	private String rutOculto;
	private String nombreOculto;
	private String apellidosOculto;
	private String nombreGrupoOculto;
	private String codigoGrupoOculto;
	private String rutEmpresaOculto;
	private String razonSocialOculto;

	/**
	 * consulta
	 * @return
	 */
	public List getConsulta()
	{
		return this.consulta;
	}

	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(List consulta)
	{
		this.consulta = consulta;
	}

	/**
	 * apellidos
	 * @return
	 */
	public String getApellidos()
	{
		return this.apellidos;
	}

	/**
	 * apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	/**
	 * nombre
	 * @return
	 */
	public String getNombre()
	{
		return this.nombre;
	}

	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * rut
	 * @return
	 */
	public String getRut()
	{
		return this.rut;
	}

	/**
	 * rut
	 * @param rut
	 */
	public void setRut(String rut)
	{
		this.rut = rut;
	}

	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}

	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * mostrar lista
	 * @return
	 */
	public String getMostrarLista() {
		return this.mostrarLista;
	}

	/**
	 * mostrar lista
	 * @param mostrarLista
	 */
	public void setMostrarLista(String mostrarLista) {
		this.mostrarLista = mostrarLista;
	}

	/**
	 * codigo grupo convenio
	 * @return
	 */
	public String getCodigoGrupoConvenio() {
		return this.codigoGrupoConvenio;
	}

	/**
	 * codigo grupo convenio
	 * @param codigoGrupoConvenio
	 */
	public void setCodigoGrupoConvenio(String codigoGrupoConvenio) {
		this.codigoGrupoConvenio = codigoGrupoConvenio;
	}

	/**
	 * nombre grupo convenio
	 * @return
	 */
	public String getNombreGrupoConvenio() {
		return this.nombreGrupoConvenio;
	}

	/**
	 * nombre grupo convenio
	 * @param nombreGrupoConvenio
	 */
	public void setNombreGrupoConvenio(String nombreGrupoConvenio) {
		this.nombreGrupoConvenio = nombreGrupoConvenio;
	}

	/**
	 * razon social
	 * @return
	 */
	public String getRazonSocial() {
		return this.razonSocial;
	}

	/**
	 * razon social
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * rut empresa
	 * @return
	 */
	public String getRutEmpresa() {
		return this.rutEmpresa;
	}

	/**
	 * rut empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * apellidos ocultos
	 * @return
	 */
	public String getApellidosOculto() {
		return this.apellidosOculto;
	}

	/**
	 * apellidos ocultos
	 * @param apellidosOculto
	 */
	public void setApellidosOculto(String apellidosOculto) {
		this.apellidosOculto = apellidosOculto;
	}

	/**
	 * codigo grupo oculto
	 * @return
	 */
	public String getCodigoGrupoOculto() {
		return this.codigoGrupoOculto;
	}

	/**
	 * codigo grupo oculto
	 * @param codigoGrupoOculto
	 */
	public void setCodigoGrupoOculto(String codigoGrupoOculto) {
		this.codigoGrupoOculto = codigoGrupoOculto;
	}

	/**
	 * nombre grupo oculto
	 * @return
	 */
	public String getNombreGrupoOculto() {
		return this.nombreGrupoOculto;
	}

	/**
	 * nombre grupo oculto
	 * @param nombreGrupoOculto
	 */
	public void setNombreGrupoOculto(String nombreGrupoOculto) {
		this.nombreGrupoOculto = nombreGrupoOculto;
	}

	/**
	 * nombre oculto
	 * @return
	 */
	public String getNombreOculto() {
		return this.nombreOculto;
	}

	/**
	 * nombre oculto
	 * @param nombreOculto
	 */
	public void setNombreOculto(String nombreOculto) {
		this.nombreOculto = nombreOculto;
	}

	/**
	 * razon social oculto
	 * @return
	 */
	public String getRazonSocialOculto() {
		return this.razonSocialOculto;
	}

	/**
	 * razon social oculto
	 * @param razonSocialOculto
	 */
	public void setRazonSocialOculto(String razonSocialOculto) {
		this.razonSocialOculto = razonSocialOculto;
	}

	/**
	 * rut empresa oculto
	 * @return
	 */
	public String getRutEmpresaOculto() {
		return this.rutEmpresaOculto;
	}

	/**
	 * rut empresa oculto
	 * @param rutEmpresaOculto
	 */
	public void setRutEmpresaOculto(String rutEmpresaOculto) {
		this.rutEmpresaOculto = rutEmpresaOculto;
	}

	/**
	 * rut oculto
	 * @return
	 */
	public String getRutOculto() {
		return this.rutOculto;
	}

	/**
	 * rut oculto
	 * @param rutOculto
	 */
	public void setRutOculto(String rutOculto) {
		this.rutOculto = rutOculto;
	}
	
}
