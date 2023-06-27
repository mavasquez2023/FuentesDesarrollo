package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

import cl.araucana.bienestar.bonificaciones.model.Cobertura;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ParametrosBonificacionCoberturaVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long casoId=0;
	private Date fechaDeOcurrencia=null;
	private Date fechaInicio=null;
	private Date fechaFin=null;
	private long casoIdNoConsiderado=0;
	private String rutSocio=null;
	private String rutCarga=null;
	private double aporteBienestarPrevioMismoCaso=0;
	private long codigoConvenio=0;
	private int monto=0;
	private int montoDescuento=0;
	private int montoAporteIsapre=0;
	private Cobertura cobertura=null;


	/**
	 * @return
	 */
	public double getAporteBienestarPrevioMismoCaso() {
		return aporteBienestarPrevioMismoCaso;
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
	public long getCasoIdNoConsiderado() {
		return casoIdNoConsiderado;
	}

	/**
	 * @return
	 */
	public Cobertura getCobertura() {
		return cobertura;
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
	public Date getFechaDeOcurrencia() {
		return fechaDeOcurrencia;
	}

	/**
	 * @return
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @return
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public int getMontoAporteIsapre() {
		return montoAporteIsapre;
	}

	/**
	 * @return
	 */
	public int getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * @return
	 */
	public String getRutCarga() {
		return rutCarga;
	}

	/**
	 * @return
	 */
	public String getRutSocio() {
		return rutSocio;
	}

	/**
	 * @param d
	 */
	public void setAporteBienestarPrevioMismoCaso(double d) {
		aporteBienestarPrevioMismoCaso = d;
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
	public void setCasoIdNoConsiderado(long l) {
		casoIdNoConsiderado = l;
	}

	/**
	 * @param cobertura
	 */
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param date
	 */
	public void setFechaDeOcurrencia(Date date) {
		fechaDeOcurrencia = date;
	}

	/**
	 * @param date
	 */
	public void setFechaFin(Date date) {
		fechaFin = date;
	}

	/**
	 * @param date
	 */
	public void setFechaInicio(Date date) {
		fechaInicio = date;
	}

	/**
	 * @param i
	 */
	public void setMonto(int i) {
		monto = i;
	}

	/**
	 * @param i
	 */
	public void setMontoAporteIsapre(int i) {
		montoAporteIsapre = i;
	}

	/**
	 * @param i
	 */
	public void setMontoDescuento(int i) {
		montoDescuento = i;
	}

	/**
	 * @param string
	 */
	public void setRutCarga(String string) {
		rutCarga = string;
	}

	/**
	 * @param string
	 */
	public void setRutSocio(String string) {
		rutSocio = string;
	}

}
