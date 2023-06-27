package cl.araucana.adminCpe.presentation.struts.forms.entidad;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) EdicionEntidadesSilActionForm.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.5
 */
public class EdicionEntidadesSilActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private String tipoEdicion;
	private List tiposEdicion;
	private String tipoEdicionSeleccionada;
	private List lista;
	private String listaFolios;
	private String nombre;
	private int codigoEntidadAntiguo;
	
	private int idFoliacion;
	private int folioActual;
	private int folioInicial;
	private int folioFinal;
	private int idEntPagadora;
	private int foliosEnUso;
	private int largoFolios;
 
	private String rutEntidad;
	private String digitoEntidad;
	private String codigoEntidad;
	private int idBanco;
	private List listaBancos;
	private boolean nuevaEntidad;
	private String idCtaCte;
	private String nombreEntidad;
	private boolean tieneConvenio;
	private boolean imprime;

	//Datos Cuenta Corriente para Transferencia
	private boolean generaCheque;
	private int idBancoSpl;
	private String idCtaCteSpl;
	
	private String correoContacto;
	private String nombreContacto;
	private String entidadFTP;
	private String carpetaFTP;	
	private String usuarioFTP;
	private String claveFTP;
	private int tipoArchMP;	
	


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
	 * folio actual
	 * @return
	 */
	public int getFolioActual() {
		return this.folioActual;
	}

	/**
	 * folio actual
	 * @param folioActual
	 */
	public void setFolioActual(int folioActual) {
		this.folioActual = folioActual;
	}

	/**
	 * folio final
	 * @return
	 */
	public int getFolioFinal() {
		return this.folioFinal;
	}

	/**
	 * folio final
	 * @param folioFinal
	 */
	public void setFolioFinal(int folioFinal) {
		this.folioFinal = folioFinal;
	}

	/**
	 * folio inicial
	 * @return
	 */
	public int getFolioInicial() {
		return this.folioInicial;
	}

	/**
	 * folio inicial
	 * @param folioInicial
	 */
	public void setFolioInicial(int folioInicial) {
		this.folioInicial = folioInicial;
	}

	/**
	 * folio en uso
	 * @return
	 */
	public int getFoliosEnUso() {
		return this.foliosEnUso;
	}

	/**
	 * folio en uso
	 * @param foliosEnUso
	 */
	public void setFoliosEnUso(int foliosEnUso) {
		this.foliosEnUso = foliosEnUso;
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
	 * id entidad pagadora
	 * @return
	 */
	public int getIdEntPagadora() {
		return this.idEntPagadora;
	}

	/**
	 * id entidad pagadora
	 * @param idEntPagadora
	 */
	public void setIdEntPagadora(int idEntPagadora) {
		this.idEntPagadora = idEntPagadora;
	}

	/**
	 * id folioacion
	 * @return
	 */
	public int getIdFoliacion() {
		return this.idFoliacion;
	}

	/**
	 * id foliacion
	 * @param idFoliacion
	 */
	public void setIdFoliacion(int idFoliacion) {
		this.idFoliacion = idFoliacion;
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
	 * set nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * nombre entidad
	 * @return
	 */
	public String getNombreEntidad() {
		return this.nombreEntidad;
	}

	/**
	 * set nombre entenido
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
	 * get tipos edicion
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
	 * lista folios
	 * @return
	 */
	public String getListaFolios() {
		return this.listaFolios;
	}

	/**
	 * lista folios 
	 * @param listaFolios
	 */
	public void setListaFolios(String listaFolios) {
		this.listaFolios = listaFolios;
	}

	/**
	 * largo folios
	 * @return
	 */
	public int getLargoFolios() {
		return this.largoFolios;
	}

	/**
	 * largo folios
	 * @param largoFolios
	 */
	public void setLargoFolios(int largoFolios) {
		this.largoFolios = largoFolios;
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

	public boolean isGeneraCheque()
	{
		return this.generaCheque;
	}

	public void setGeneraCheque(boolean generaCheque)
	{
		this.generaCheque = generaCheque;
	}

	public int getIdBancoSpl()
	{
		return this.idBancoSpl;
	}

	public void setIdBancoSpl(int idBancoSpl)
	{
		this.idBancoSpl = idBancoSpl;
	}

	public String getIdCtaCteSpl()
	{
		return this.idCtaCteSpl;
	}

	public void setIdCtaCteSpl(String idCtaCteSpl)
	{
		this.idCtaCteSpl = idCtaCteSpl;
	}

	public String getCarpetaFTP() {
		return carpetaFTP;
	}

	public void setCarpetaFTP(String carpetaFTP) {
		this.carpetaFTP = carpetaFTP;
	}

	public String getClaveFTP() {
		return claveFTP;
	}

	public void setClaveFTP(String claveFTP) {
		this.claveFTP = claveFTP;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getEntidadFTP() {
		return entidadFTP;
	}

	public void setEntidadFTP(String entidadFTP) {
		this.entidadFTP = entidadFTP;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public int getTipoArchMP() {
		return tipoArchMP;
	}

	public void setTipoArchMP(int tipoArchMP) {
		this.tipoArchMP = tipoArchMP;
	}

	public String getUsuarioFTP() {
		return usuarioFTP;
	}

	public void setUsuarioFTP(String usuarioFTP) {
		this.usuarioFTP = usuarioFTP;
	}
}
