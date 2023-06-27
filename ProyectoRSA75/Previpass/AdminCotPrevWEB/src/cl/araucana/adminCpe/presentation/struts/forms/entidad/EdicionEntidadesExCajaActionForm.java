package cl.araucana.adminCpe.presentation.struts.forms.entidad;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) EdicionEntidadesExCajaActionForm.java 1.7 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author aacuña
 * 
 * @version 1.7
 */
public class EdicionEntidadesExCajaActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private String tipoEdicion;
	private List tiposEdicion;
	private String rutEntidad;
	private String digitoEntidad;
	private String tipoEdicionSeleccionada;
	private List lista;
	private String nombre;
	private int idEntPagadora;
	private int codigoEntidadAntiguo;
	private String codigoEntidad;
	private String codigoEntidadInicial;
	private int idBanco;
	private List listaBancos;
	private boolean nuevaEntidad;
	private String idCtaCte;
	private String nombreEntidad;
	private boolean tieneConvenio;
	private boolean imprime;
	private String origen;
	private String rutPadre;
	private List listaEntidadRegimenImpositivo;
	private String nombreReg;
	private String codigoReg; 
	private String tasaReg;
	private String listaRegimen;
	private String entidadOrigen;
	private int cantidadRegistros;
	private int mostrarBoton;
	

	/**
	 * mostrar boton
	 * @return
	 */
	public int getMostrarBoton() {
		return this.mostrarBoton;
	}

	/**
	 * mostrar boton
	 * @param mostrarBoton
	 */
	public void setMostrarBoton(int mostrarBoton) {
		this.mostrarBoton = mostrarBoton;
	}

	/**
	 * contidad registros
	 * @return
	 */
	public int getCantidadRegistros() {
		return this.cantidadRegistros;
	}

	/**
	 * contidad registros
	 * @param cantidadRegistros
	 */
	public void setCantidadRegistros(int cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * entidad origen
	 * @return
	 */
	public String getEntidadOrigen() {
		return this.entidadOrigen;
	}

	/**
	 * entidad origen
	 * @param entidadOrigen
	 */
	public void setEntidadOrigen(String entidadOrigen) {
		this.entidadOrigen = entidadOrigen;
	}

	/**
	 * codigo regimen
	 * @return
	 */
	public String getCodigoReg() {
		return this.codigoReg;
	}

	/**
	 * codigo regimen
	 * @param codigoReg
	 */
	public void setCodigoReg(String codigoReg) {
		this.codigoReg = codigoReg;
	}

	/**
	 * nombre regimen
	 * @return
	 */
	public String getNombreReg() {
		return this.nombreReg;
	}

	/**
	 * nombre regimen
	 * @param nombreReg
	 */
	public void setNombreReg(String nombreReg) {
		this.nombreReg = nombreReg;
	}

	/**
	 * tasa regimen
	 * @return
	 */
	public String getTasaReg() {
		return this.tasaReg;
	}

	/**
	 * tasa regimen
	 * @param tasaReg
	 */
	public void setTasaReg(String tasaReg) {
		this.tasaReg = tasaReg;
	}

	/**
	 * lista entidad regimen impositivo
	 * @return
	 */
	public List getListaEntidadRegimenImpositivo() {
		return this.listaEntidadRegimenImpositivo;
	}

	/**
	 * lista entidad regimen impositivo
	 * @param listaEntidadRegimenImpositivo
	 */
	public void setListaEntidadRegimenImpositivo(List listaEntidadRegimenImpositivo) {
		this.listaEntidadRegimenImpositivo = listaEntidadRegimenImpositivo;
	}

	/**
	 * codigo entidad
	 * @return
	 */
	public String getCodigoEntidad() {
		return this.codigoEntidad;
	}

	/**
	 * codigo entidad
	 * @param codigoEntidad
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}

	/**
	 * id banco
	 * @return
	 */
	public int getIdBanco() {
		return this.idBanco;
	}

	/**
	 * id banco
	 * @param idBanco
	 */
	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	/**
	 * id cuenta corriente
	 * @return
	 */
	public String getIdCtaCte() {
		return this.idCtaCte;
	}

	/**
	 * id cuenta corriente
	 * @param idCtaCte
	 */
	public void setIdCtaCte(String idCtaCte) {
		this.idCtaCte = idCtaCte;
	}

	/**
	 * entidad pagadora
	 * @return
	 */
	public int getIdEntPagadora() {
		return this.idEntPagadora;
	}

	/**
	 * entidad pagadora
	 * @param idEntPagadora
	 */
	public void setIdEntPagadora(int idEntPagadora) {
		this.idEntPagadora = idEntPagadora;
	}

	/**
	 * imprime
	 * @return
	 */
	public boolean isImprime() {
		return this.imprime;
	}

	/**
	 * imprime
	 * @param imprime
	 */
	public void setImprime(boolean imprime) {
		this.imprime = imprime;
	}

	/**
	 * lista
	 * @return
	 */
	public List getLista() {
		return this.lista;
	}

	/**
	 * lista
	 * @param lista
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}

	/**
	 * lista bancos
	 * @return
	 */
	public List getListaBancos() {
		return this.listaBancos;
	}

	/**
	 * lista bancos
	 * @param listaBancos
	 */
	public void setListaBancos(List listaBancos) {
		this.listaBancos = listaBancos;
	}

	/**
	 * nombre
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * nombre entidad
	 */
	public String getNombreEntidad() {
		return this.nombreEntidad;
	}

	/**
	 * nombre entidad
	 * @param nombreEntidad
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * nueva entidad
	 * @return
	 */
	public boolean isNuevaEntidad() {
		return this.nuevaEntidad;
	}

	/**
	 * nueva entidad
	 * @param nuevaEntidad
	 */
	public void setNuevaEntidad(boolean nuevaEntidad) {
		this.nuevaEntidad = nuevaEntidad;
	}

	/**
	 * tiene convenio
	 * @return
	 */
	public boolean isTieneConvenio() {
		return this.tieneConvenio;
	}

	/**
	 * tiene convenio
	 * @param tieneConvenio
	 */
	public void setTieneConvenio(boolean tieneConvenio) {
		this.tieneConvenio = tieneConvenio;
	}

	/**
	 * tipo edicion
	 * @return
	 */
	public String getTipoEdicion() {
		return this.tipoEdicion;
	}

	/**
	 * tipo edicion
	 * @param tipoEdicion
	 */
	public void setTipoEdicion(String tipoEdicion) {
		this.tipoEdicion = tipoEdicion;
	}

	/**
	 * tipos edicion
	 * @return
	 */
	public List getTiposEdicion() {
		return this.tiposEdicion;
	}

	/**
	 * tipos edicion
	 * @param tiposEdicion
	 */
	public void setTiposEdicion(List tiposEdicion) {
		this.tiposEdicion = tiposEdicion;
	}

	/**
	 * tipo edicionseleccionada
	 * @return
	 */
	public String getTipoEdicionSeleccionada() {
		return this.tipoEdicionSeleccionada;
	}

	/**
	 * tipo edicion seleccionada
	 * @param tipoEdicionSeleccionada
	 */
	public void setTipoEdicionSeleccionada(String tipoEdicionSeleccionada) {
		this.tipoEdicionSeleccionada = tipoEdicionSeleccionada;
	}

	/**
	 * digito entidad
	 * @return
	 */
	public String getDigitoEntidad() {
		return this.digitoEntidad;
	}

	/**
	 * digito entidad
	 * @param digitoEntidad
	 */
	public void setDigitoEntidad(String digitoEntidad) {
		this.digitoEntidad = digitoEntidad;
	}

	/**
	 * rut entidad
	 * @return
	 */
	public String getRutEntidad() {
		return this.rutEntidad;
	}

	/**
	 * rut entidad
	 * @param rutEntidad
	 */
	public void setRutEntidad(String rutEntidad) {
		this.rutEntidad = rutEntidad;
	}

	/**
	 * origen
	 * @return
	 */
	public String getOrigen() {
		return this.origen;
	}

	/**
	 * origen
	 * @param origen
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * codigo entidad antiguo
	 * @return
	 */
	public int getCodigoEntidadAntiguo() {
		return this.codigoEntidadAntiguo;
	}

	/**
	 * codigo entidad antiguo
	 * @param codigoEntidadAntiguo
	 */
	public void setCodigoEntidadAntiguo(int codigoEntidadAntiguo) {
		this.codigoEntidadAntiguo = codigoEntidadAntiguo;
	}

	/**
	 * lista regimen
	 * @return
	 */
	public String getListaRegimen() {
		return this.listaRegimen;
	}

	/**
	 * lista regimen
	 * @param listaRegimen
	 */
	public void setListaRegimen(String listaRegimen) {
		this.listaRegimen = listaRegimen;
	}

	/**
	 * rut padre
	 * @return
	 */
	public String getRutPadre() {
		return this.rutPadre;
	}

	/**
	 * rut padre
	 * @param rutPadre
	 */
	public void setRutPadre(String rutPadre) {
		this.rutPadre = rutPadre;
	}

	/**
	 * codigo entidad inicial
	 * @return
	 */
	public String getCodigoEntidadInicial() {
		return this.codigoEntidadInicial;
	}

	/**
	 * codigo entidad inicial
	 * @param codigoEntidadInicial
	 */
	public void setCodigoEntidadInicial(String codigoEntidadInicial) {
		this.codigoEntidadInicial = codigoEntidadInicial;
	}

	}
