package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class ConsultaCreditoVO implements Serializable {
	
	private int titularidad=0;
	private long rutEmpresa=0;
	private String dvEmpresa;
	private long folio=0;
	private Date fechaOtorgamiento=null;
	private int montoCuota=0;
	private int numeroCuotas=0;
	private int codigoEstadoPrestamo=0;
	private Date primerDescuento=null;
	private Date ultimoDescuento=null;
	private int cuotasImpagas=0;
	private boolean repactado=false;
	private int oficinaProceso=0;    //OFICINA PROCESO

	/**
	 * Si el crédito es repactado cambia el
	 * valor del codigo de préstamo a 99
	 * @return
	 */
	public int getCodigoEstadoPrestamo() {
		if (repactado)
			codigoEstadoPrestamo=99;
		return codigoEstadoPrestamo;
	}
	
	/**
	 * @return
	 */
	public int getCuotasPagadas() {
		return numeroCuotas - cuotasImpagas;
	}

	/**
	 * @return
	 */
	public int getCuotasImpagas() {
		return cuotasImpagas;
	}

	/**
	 * @return
	 */
	public String getDvEmpresa() {
		return dvEmpresa;
	}

	/**
	 * @return
	 */
	public Date getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}

	/**
	 * @return
	 */
	public long getFolio() {
		return folio;
	}

	/**
	 * @return
	 */
	public int getMontoCuota() {
		return montoCuota;
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
	public Date getPrimerDescuento() {
		return primerDescuento;
	}

	/**
	 * @return
	 */
	public boolean isRepactado() {
		return repactado;
	}

	/**
	 * @return
	 */
	public long getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @return
	 */
	public int getTitularidad() {
		return titularidad;
	}

	/**
	 * @return
	 */
	public Date getUltimoDescuento() {
		return ultimoDescuento;
	}

	/**
	 * @param i
	 */
	public void setCodigoEstadoPrestamo(int i) {
		codigoEstadoPrestamo = i;
	}

	/**
	 * @param i
	 */
	public void setCuotasImpagas(int i) {
		cuotasImpagas = i;
	}

	/**
	 * @param i
	 */
	public void setDvEmpresa(String i) {
		dvEmpresa = i;
	}

	/**
	 * @param date
	 */
	public void setFechaOtorgamiento(Date date) {
		fechaOtorgamiento = date;
	}

	/**
	 * @param l
	 */
	public void setFolio(long l) {
		folio = l;
	}

	/**
	 * @param i
	 */
	public void setMontoCuota(int i) {
		montoCuota = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotas(int i) {
		numeroCuotas = i;
	}

	/**
	 * @param date
	 */
	public void setPrimerDescuento(Date date) {
		primerDescuento = date;
	}

	/**
	 * @param b
	 */
	public void setRepactado(boolean b) {
		repactado = b;
	}

	/**
	 * @param l
	 */
	public void setRutEmpresa(long l) {
		rutEmpresa = l;
	}

	/**
	 * @param i
	 */
	public void setTitularidad(int i) {
		titularidad = i;
	}

	/**
	 * @param date
	 */
	public void setUltimoDescuento(Date date) {
		ultimoDescuento = date;
	}

	/**
	 * @return Oficina Proceso
	 */
	public int getOficinaProceso() {
		return oficinaProceso;
	}

	/**
	 * @param i
	 */
	public void setOficinaProceso(int i) {
		oficinaProceso = i;
	}

}
