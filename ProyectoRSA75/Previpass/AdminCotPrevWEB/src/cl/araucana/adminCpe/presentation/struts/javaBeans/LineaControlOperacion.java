package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) LineaControlOperacion.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.2
 */
public class LineaControlOperacion implements Serializable
{
	private static final long serialVersionUID = -1;
	
	private String grupoConvenio;
	private String rutEmpresa;
	private String razonSocial;
	private int cantidadEnvios;
	private String fecha;
	private String horaInicio;
	private String horaTermino;
	private String tiempoTotal;
	private int numeroRegistros;
	private String factorTipo;
	private String nodo;
	private String usuario;
	
	public LineaControlOperacion()
	{
		super();
	}

	/**
	 * cantidad envios
	 * @return
	 */
	public int getCantidadEnvios() {
		return this.cantidadEnvios;
	}

	/**
	 * cantidad envios
	 * @param cantidadEnvios
	 */
	public void setCantidadEnvios(int cantidadEnvios) {
		this.cantidadEnvios = cantidadEnvios;
	}

	/**
	 * factor tipo
	 * @return
	 */
	public String getFactorTipo() {
		return this.factorTipo;
	}
	/**
	 * factor tipo
	 * @param factorTipo
	 */
	public void setFactorTipo(String factorTipo) {
		this.factorTipo = factorTipo;
	}

	/**
	 * fecha
	 * @return
	 */
	public String getFecha() {
		return this.fecha;
	}
	/**
	 * fecha
	 * @param fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * hora inicio
	 * @return
	 */
	public String getHoraInicio() {
		return this.horaInicio;
	}

	/**
	 * hora inicio
	 * @param horaInicio
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * hora termino
	 * @return
	 */
	public String getHoraTermino() {
		return this.horaTermino;
	}
	/**
	 * hora termino
	 * @param horaTermino
	 */
	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}

	/**
	 * nodo
	 * @return
	 */
	public String getNodo() {
		return this.nodo;
	}

	/**
	 * nodo
	 * @param nodo
	 */
	public void setNodo(String nodo) {
		this.nodo = nodo;
	}

	/**
	 * numero registros
	 * @return
	 */
	public int getNumeroRegistros() {
		return this.numeroRegistros;
	}

	/**
	 * numero registros
	 * @param numeroRegistros
	 */
	public void setNumeroRegistros(int numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
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
	 * tiempo total
	 * @return
	 */
	public String getTiempoTotal() {
		return this.tiempoTotal;
	}

	/**
	 * tiempo total
	 * @param tiempoTotal
	 */
	public void setTiempoTotal(String tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	/**
	 * usuario
	 * @return
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * usuario
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
