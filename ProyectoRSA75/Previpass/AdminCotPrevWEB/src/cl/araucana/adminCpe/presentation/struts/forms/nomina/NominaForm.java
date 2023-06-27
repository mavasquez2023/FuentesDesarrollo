package cl.araucana.adminCpe.presentation.struts.forms.nomina;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) NominaForm.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.4
 */
public class NominaForm extends ActionForm {
	
	private static final long serialVersionUID = -4704281203425810728L;

	private String grupoConvenio;
	private String rutEmpresa;
	private String estadoId;
	private String tipoNominaId;
	private String convenioId;
	private String empresaNombre;
	private String empresaRut;
	
	private String nombreProceso;
	
	private List estados;
	private List tiposNomina;
	private List nominas;
	
	//Ver trabajadores
	private List trabajadores;
	private List trabPendientes;
	private List trabAvisos;
	private String trabajadorRut;
	private String trabajadorNombre;
	private String trabajadorApPaterno;
	
	private String b_rut;
	private String b_nombre;
	private String b_apellido;
	
	private String rutEmpresaBuscar;
	private String grupoConvenioBuscar;
	private String tipoNominaIdBuscar;
	private String estadoIdBuscar;
 
	
	
	private Collection numeroFilas;
	
	/**
	 * nombre proceso
	 * @return
	 */
	public String getNombreProceso() {
			 
			return this.nombreProceso;
		}
		
	/**
	 * nombre proceso
	 * @param nombreProceso
	 */
	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	/**
	 * convierte string compos
	 * @param Convierte a String todos los campos
	 */
	public String toString() {
		String toString = " \n **********************************Contenido de NominaForm: " +
				"\n\t estados:"+this.estados; 
		return toString;
	}
	/**
	 * estados
	 * @return
	 */
	public List getEstados() {
		return this.estados;
	}

	/**
	 * estados
	 * @param estados
	 */
	public void setEstados(List estados) {
		this.estados = estados;
	}

	/**
	 * grupo convenio
	 * @return
	 */
	public String getGrupoConvenio() {
		return this.grupoConvenio;
	}

	/**
	 * grupo convenio
	 * @param grupoConvenio
	 */
	public void setGrupoConvenio(String grupoConvenio) {
		this.grupoConvenio = grupoConvenio;
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
	 * estado id
	 * @return
	 */
	public String getEstadoId() {
		return this.estadoId;
	}

	/**
	 * estado id
	 * @param estadoId
	 */
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	/**
	 * nominas
	 * @return
	 */
	public List getNominas() {
		return this.nominas;
	}

	/**
	 * nominas
	 * @param nominas
	 */
	public void setNominas(List nominas) {
		this.nominas = nominas;
	}

	/**
	 * tipo nomina id
	 * @return
	 */
	public String getTipoNominaId() {
		return this.tipoNominaId;
	}

	/**
	 * tipo nomina id
	 * @param tipoNominaId
	 */
	public void setTipoNominaId(String tipoNominaId) {
		this.tipoNominaId = tipoNominaId;
	}

	/**
	 * tipos nomina
	 * @return
	 */
	public List getTiposNomina() {
		return this.tiposNomina;
	}

	/**
	 * tipos nomina
	 * @param tiposNomina
	 */
	public void setTiposNomina(List tiposNomina) {
		this.tiposNomina = tiposNomina;
	}

	/**
	 * trabajadores
	 * @return
	 */
	public List getTrabajadores() {
		return this.trabajadores;
	}

	/**
	 * trabajadores
	 * @param trabajadores
	 */
	public void setTrabajadores(List trabajadores) {
		this.trabajadores = trabajadores;
	}

	/**
	 * trabajadores pendientes
	 * @return
	 */
	public List getTrabPendientes() {
		return this.trabPendientes;
	}

	/**
	 * trabajadores pendientes
	 * @param trabPendientes
	 */
	public void setTrabPendientes(List trabPendientes) {
		this.trabPendientes = trabPendientes;
	}

	/**
	 * trabajador apellido paterno
	 * @return
	 */
	public String getTrabajadorApPaterno() {
		return this.trabajadorApPaterno;
	}

	/**
	 * trabajador apellido paterno
	 * @param trabajadorApPaterno
	 */
	public void setTrabajadorApPaterno(String trabajadorApPaterno) {
		this.trabajadorApPaterno = trabajadorApPaterno;
	}

	/**
	 * trabajador nombre
	 * @return
	 */
	public String getTrabajadorNombre() {
		return this.trabajadorNombre;
	}

	/**
	 * trabajador nombre
	 * @param trabajadorNombre
	 */
	public void setTrabajadorNombre(String trabajadorNombre) {
		this.trabajadorNombre = trabajadorNombre;
	}

	/**
	 * trabajador rut
	 * @return
	 */
	public String getTrabajadorRut() {
		return this.trabajadorRut;
	}

	/**
	 * trabajador rut
	 * @param trabajadorRut
	 */
	public void setTrabajadorRut(String trabajadorRut) {
		this.trabajadorRut = trabajadorRut;
	}

	/**
	 * convenio id
	 * @return this.el convenioId
	 */
	public String getConvenioId() {
		return this.convenioId;
	}

	/**
	 * convenio id
	 * @param convenioId el convenioId a establecer
	 */
	public void setConvenioId(String convenioId) {
		this.convenioId = convenioId;
	}

	/**
	 * empresa nombre
	 * @return this.el empresaNombre
	 */
	public String getEmpresaNombre() {
		return this.empresaNombre;
	}

	/**
	 * empresa nombre
	 * @param empresaNombre el empresaNombre a establecer
	 */
	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}

	/**
	 * empresa rut
	 * @return this.el empresaRut
	 */
	public String getEmpresaRut() {
		return this.empresaRut;
	}

	/**
	 * empresa rut
	 * @param empresaRut el empresaRut a establecer
	 */
	public void setEmpresaRut(String empresaRut) {
		this.empresaRut = empresaRut;
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
	 * b apellido
	 * @return
	 */
	public String getB_apellido() {
		return this.b_apellido;
	}

	/**
	 * b apellido
	 * @param b_apellido
	 */
	public void setB_apellido(String b_apellido) {
		this.b_apellido = b_apellido;
	}


	/**
	 * b nombre
	 * @return
	 */
	public String getB_nombre() {
		return this.b_nombre;
	}

	/**
	 * b nombre
	 * @param b_nombre
	 */
	public void setB_nombre(String b_nombre) {
		this.b_nombre = b_nombre;
	}

	/**
	 * b rut
	 * @return
	 */
	public String getB_rut() 
	{
		return this.b_rut;
	}

	/**
	 * b rut
	 * @param b_rut
	 */
	public void setB_rut(String b_rut) 
	{
		this.b_rut = b_rut;
	}

	/**
	 * estado id buscar
	 * @return
	 */
	public String getEstadoIdBuscar() 
	{
		return this.estadoIdBuscar;
	}

	/**
	 * estado id buscar
	 * @param estadoIdBuscar
	 */
	public void setEstadoIdBuscar(String estadoIdBuscar) 
	{
		this.estadoIdBuscar = estadoIdBuscar;
	}

	/**
	 * grupo convenio buscar
	 * @return
	 */
	public String getGrupoConvenioBuscar() 
	{
		return this.grupoConvenioBuscar;
	}

	/**
	 * grupo convenio buscar
	 * @param grupoConvenioBuscar
	 */
	public void setGrupoConvenioBuscar(String grupoConvenioBuscar) 
	{
		this.grupoConvenioBuscar = grupoConvenioBuscar;
	}

	/**
	 * rut empresa buscar
	 * @return
	 */
	public String getRutEmpresaBuscar() 
	{
		return this.rutEmpresaBuscar;
	}

	/**
	 * rut empresa buscar
	 * @param rutEmpresaBuscar
	 */
	public void setRutEmpresaBuscar(String rutEmpresaBuscar) 
	{
		this.rutEmpresaBuscar = rutEmpresaBuscar;
	}

	/**
	 * tipo nomina id buscar
	 * @return
	 */
	public String getTipoNominaIdBuscar() 
	{
		return this.tipoNominaIdBuscar;
	}

	/**
	 * tipo nomina id buscar
	 * @param tipoNominaIdBuscar
	 */
	public void setTipoNominaIdBuscar(String tipoNominaIdBuscar) 
	{
		this.tipoNominaIdBuscar = tipoNominaIdBuscar;
	}

	public List getTrabAvisos() 
	{
		return this.trabAvisos;
	}

	public void setTrabAvisos(List trabAvisos) 
	{
		this.trabAvisos = trabAvisos;
	}
}
