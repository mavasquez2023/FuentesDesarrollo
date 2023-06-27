package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class PagoConvenioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoPago=0;
	private long codigoConvenio=0;
	private String rutConvenio = null;
	private String dvConvenio = null;
	private String nombreConvenio = null;
	private long casoId=0;
	private String estado=null;
	private Date fechaEstado=null;
	private String indicadorPago=null;
	private String indicadorDescuento=null;
	private String rut=null;
	private String dv=null;
	private String nombre=null;
	private String apellidoPaterno=null;
	private String apellidoMaterno=null;
	private String oficina=null;
	private double montoPago=0;
	private Date fechaPago=null;
	private int numeroCuotasBienestar=0;
	private int numeroCuotasConvenio=0;
	private int numeroCuotas=0;
	private int cuotaActual=0;
	private long folioTesoreriaBienestar=0;
	private long conceptoTesoreriaConvenioEgreso=0;
	private String usuario = null;
	private int aporteBienestar=0;
	
	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rut + "-" + dv;
	}

	/**
	 * Retorna el nombre completo del Socio
	 * @return String con el nombre completo
	 */
	public String getFullNombre() {
		return 	nombre + " " + apellidoPaterno + " " + apellidoMaterno;
	}

	/**
	 * Retorna el rut compuesto del Convenio
	 * @return String con el rut
	 */
	public String getFullRutConvenio() {
		return rutConvenio + "-" + dvConvenio;
	}

	/**
	 * @return
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @return
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @return
	 */
	public long getCasoId() {
		return casoId;
	}

	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @return
	 */
	public int getCuotaActual() {
		return cuotaActual;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public Date getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @return
	 */
	public String getIndicadorPago() {
		return indicadorPago;
	}

	/**
	 * @return
	 */
	public double getMontoPago() {
		return montoPago;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasBienestar() {
		return numeroCuotasBienestar;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasConvenio() {
		return numeroCuotasConvenio;
	}

	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setApellidoMaterno(String string) {
		apellidoMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setApellidoPaterno(String string) {
		apellidoPaterno = string;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param i
	 */
	public void setCuotaActual(int i) {
		cuotaActual = i;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param date
	 */
	public void setFechaEstado(Date date) {
		fechaEstado = date;
	}

	/**
	 * @param string
	 */
	public void setIndicadorPago(String string) {
		indicadorPago = string;
	}

	/**
	 * @param d
	 */
	public void setMontoPago(double d) {
		montoPago = d;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotas(int i) {
		numeroCuotas = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasBienestar(int i) {
		numeroCuotasBienestar = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasConvenio(int i) {
		numeroCuotasConvenio = i;
	}

	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public String getDvConvenio() {
		return dvConvenio;
	}

	/**
	 * @return
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @return
	 */
	public String getRutConvenio() {
		return rutConvenio;
	}

	/**
	 * @param string
	 */
	public void setDvConvenio(String string) {
		dvConvenio = string;
	}

	/**
	 * @param string
	 */
	public void setNombreConvenio(String string) {
		nombreConvenio = string;
	}

	/**
	 * @param string
	 */
	public void setRutConvenio(String string) {
		rutConvenio = string;
	}

	/**
	 * @return
	 */
	public Date getFechaPago() {
		return fechaPago;
	}

	/**
	 * @return
	 */
	public long getFolioTesoreriaBienestar() {
		return folioTesoreriaBienestar;
	}

	/**
	 * @param date
	 */
	public void setFechaPago(Date date) {
		fechaPago = date;
	}

	/**
	 * @param l
	 */
	public void setFolioTesoreriaBienestar(long l) {
		folioTesoreriaBienestar = l;
	}

	/**
	 * @return
	 */
	public long getCodigoPago() {
		return codigoPago;
	}

	/**
	 * @param l
	 */
	public void setCodigoPago(long l) {
		codigoPago = l;
	}

	/**
	 * @return
	 */
	public String getIndicadorDescuento() {
		return indicadorDescuento;
	}

	/**
	 * @param string
	 */
	public void setIndicadorDescuento(String string) {
		indicadorDescuento = string;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @return
	 */
	public int getAporteBienestar() {
		return aporteBienestar;
	}

	/**
	 * @param i
	 */
	public void setAporteBienestar(int i) {
		aporteBienestar = i;
	}

	/**
	 * @return
	 */
	public long getConceptoTesoreriaConvenioEgreso() {
		return conceptoTesoreriaConvenioEgreso;
	}

	/**
	 * @param l
	 */
	public void setConceptoTesoreriaConvenioEgreso(long l) {
		conceptoTesoreriaConvenioEgreso = l;
	}

}
