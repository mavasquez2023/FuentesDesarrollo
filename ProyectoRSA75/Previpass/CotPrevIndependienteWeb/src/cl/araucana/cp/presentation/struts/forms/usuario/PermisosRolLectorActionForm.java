package cl.araucana.cp.presentation.struts.forms.usuario;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) PermisosRolLectorActionForm.java 1.1 10/05/2009
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
public class PermisosRolLectorActionForm extends ActionForm
{
	private static final long serialVersionUID = -5207269849907607489L;

	private String rutEmpresaFmt;
	private String nombreEmpresa;
	private String idLectorEmpresa;
	private String rutEmpresa;
	private String idConvenio;
	private String idGrupoConvenio;
	private List consulta;
	private List nivelesAcceso;
	private int idUsuario;
	private int largoFila;
	private String acc;
	
	private String idSinAcceso;
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
	 * id convenio
	 * @return
	 */
	public String getIdConvenio()
	{
		return this.idConvenio;
	}
	/**
	 * id convenio
	 * @param idConvenio
	 */
	public void setIdConvenio(String idConvenio)
	{
		this.idConvenio = idConvenio;
	}
	/**
	 * niveles acceso
	 * @return
	 */
	public List getNivelesAcceso()
	{
		return this.nivelesAcceso;
	}
	/**
	 * niveles acceso
	 * @param nivelesAcceso
	 */
	public void setNivelesAcceso(List nivelesAcceso)
	{
		this.nivelesAcceso = nivelesAcceso;
	}
	/**
	 * rut empresa
	 * @return
	 */
	public String getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	/**
	 * rut empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * id sin acceso
	 * @return
	 */
	public String getIdSinAcceso()
	{
		return this.idSinAcceso;
	}
	/**
	 * id sin acceso
	 * @param idSinAcceso
	 */
	public void setIdSinAcceso(String idSinAcceso)
	{
		this.idSinAcceso = idSinAcceso;
	}
	/**
	 * id usuario
	 * @return
	 */
	public int getIdUsuario() {
		return this.idUsuario;
	}
	/**
	 * id usuario
	 * @param idUsuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * largo fila
	 * @return
	 */
	public int getLargoFila() {
		return this.largoFila;
	}
	/**
	 * largo fila
	 * @param largoFila
	 */
	public void setLargoFila(int largoFila) {
		this.largoFila = largoFila;
	}
	/**
	 * id grupo convenio
	 * @return
	 */
	public String getIdGrupoConvenio() {
		return this.idGrupoConvenio;
	}
	/**
	 * id grupo convenio
	 * @param idGrupoConvenio
	 */
	public void setIdGrupoConvenio(String idGrupoConvenio) {
		this.idGrupoConvenio = idGrupoConvenio;
	}
	/**
	 * id lector empresa
	 * @return
	 */
	public String getIdLectorEmpresa() {
		return this.idLectorEmpresa;
	}
	/**
	 * id lector empresa
	 * @param idLectorEmpresa
	 */
	public void setIdLectorEmpresa(String idLectorEmpresa) {
		this.idLectorEmpresa = idLectorEmpresa;
	}
	/**
	 * acc
	 * @return
	 */
	public String getAcc() {
		return this.acc;
	}
	/**
	 * acc
	 * @param acc
	 */
	public void setAcc(String acc) {
		this.acc = acc;
	}
	/**
	 * nombre empresa
	 * @return
	 */
	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}
	/**
	 * nombre empresa
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * rut empresa fmt
	 * @return
	 */
	public String getRutEmpresaFmt() {
		return this.rutEmpresaFmt;
	}
	/**
	 * rut empresa fmt
	 * @param rutEmpresaFmt
	 */
	public void setRutEmpresaFmt(String rutEmpresaFmt) {
		this.rutEmpresaFmt = rutEmpresaFmt;
	}
}
