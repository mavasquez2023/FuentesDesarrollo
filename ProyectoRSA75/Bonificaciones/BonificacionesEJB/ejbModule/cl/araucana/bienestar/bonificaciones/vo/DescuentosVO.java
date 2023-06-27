package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

import cl.araucana.bienestar.bonificaciones.model.Caso;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DescuentosVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoDescuento=0;
	private long casoId=0;
	private String estado=null;
	private Date fechaEstado=null;
	private String indicadorDescuento=null;
	private String rut=null;
	private String dv=null;
	private String nombre=null;
	private String apellidoPaterno=null;
	private String apellidoMaterno=null;
	private String oficina=null;
	private double montoDescuento=0;
	private Date fechaDescuento=null;
	private int numeroCuotasBienestar=0;
	private int numeroCuotasConvenio=0;
	private int numeroCuotas=0;
	private int cuotaActual=0;
	private String tipoBono = Caso.TIPOBONO_NO;
	private double aporteConvenio=0;
	private Date fechaOcurrencia=null;
	
	//private String tipoDescuento = DescuentosVO.TIPO_DESCUENTO_NORMAL;
	
	/**public void setTipoDescuento(String tipoDescuento){
		this.tipoDescuento = tipoDescuento;
	}
	
	public String getTipoDescuento(){
		return this.tipoDescuento;
	}*/
	
	/**
	 * Retorna la cuota actual sobre el total de cuotas
	 * @return String cuotaActual/numeroCuotas
	 */
	public String getFullCuota() {
		if(numeroCuotas==0)
			return "Sin Cuotas";
		else
			return String.valueOf(cuotaActual)+"/"+String.valueOf(numeroCuotas);
	}
	
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
	public Date getFechaDescuento() {
		return fechaDescuento;
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
	public String getIndicadorDescuento() {
		return indicadorDescuento;
	}

	/**
	 * @return
	 */
	public double getMontoDescuento() {
		return montoDescuento;
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
	public void setFechaDescuento(Date date) {
		fechaDescuento = date;
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
	public void setIndicadorDescuento(String string) {
		indicadorDescuento = string;
	}

	/**
	 * @param d
	 */
	public void setMontoDescuento(double d) {
		montoDescuento = d;
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
	public String getTipoBono() {
		return tipoBono;
	}

	/**
	 * @param string
	 */
	public void setTipoBono(String string) {
		tipoBono = string;
	}


	/**
	 * @return
	 */
	public long getCodigoDescuento() {
		return codigoDescuento;
	}

	/**
	 * @param l
	 */
	public void setCodigoDescuento(long l) {
		codigoDescuento = l;
	}

	/**
	 * @return
	 */
	public double getAporteConvenio() {
		return aporteConvenio;
	}

	/**
	 * @param d
	 */
	public void setAporteConvenio(double d) {
		aporteConvenio = d;
	}

	/**
	 * @return
	 */
	public Date getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	/**
	 * @param date
	 */
	public void setFechaOcurrencia(Date date) {
		fechaOcurrencia = date;
	}

}
