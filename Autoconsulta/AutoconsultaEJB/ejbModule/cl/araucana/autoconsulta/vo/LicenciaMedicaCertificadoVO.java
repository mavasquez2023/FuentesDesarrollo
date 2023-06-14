package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class LicenciaMedicaCertificadoVO implements Serializable {
	
	private String fechaDesde=null;
	private String fechaHasta=null;
	private int diasLicencia=0;
	private int diasPagados=0;
	private int subsidioPagado=0;
	private int rentaImponible=0;
	private double porcentajeCotizacion=0;
	private int codInstitutoPrevisional=0;
	private String letraAmpliacion = null;
	private int licImpNum=0;;
	private String nomInstitutoPrevisional=null;
	private double rentaImponibleCotizacion=0;
	private double cotizacionSalud=0;	
	private	double cotizacionPension=0;
	private String fechaMesCotizacion=null;
	private int porcentajeSeguroCesantia=0;
	private double rentaImp90 = 0;
	private double cotizacionCesantia = 0;
	private String temporalPeriodo = null;

	/**
	 * Devuelve el subsidio pagado.
	 * Pero se debe restar el seguro de cesantia.
	 * @return
	 */
	public int getSubsidioPagado() {
		return subsidioPagado - (int)cotizacionCesantia;
	}


	/**
	 * @return
	 */
	public int getCodInstitutoPrevisional() {
		return codInstitutoPrevisional;
	}

	/**
	 * @return
	 */
	public double getCotizacionCesantia() {
		return cotizacionCesantia;
	}

	/**
	 * @return
	 */
	public double getCotizacionPension() {
		return cotizacionPension;
	}

	/**
	 * @return
	 */
	public double getCotizacionSalud() {
		return cotizacionSalud;
	}

	/**
	 * @return
	 */
	public int getDiasLicencia() {
		return diasLicencia;
	}

	/**
	 * @return
	 */
	public int getDiasPagados() {
		return diasPagados;
	}

	/**
	 * @return
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @return
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @return
	 */
	public String getFechaMesCotizacion() {
		return fechaMesCotizacion;
	}

	/**
	 * @return
	 */
	public String getLetraAmpliacion() {
		return letraAmpliacion;
	}

	/**
	 * @return
	 */
	public int getLicImpNum() {
		return licImpNum;
	}

	/**
	 * @return
	 */
	public String getNomInstitutoPrevisional() {
		return nomInstitutoPrevisional;
	}

	/**
	 * @return
	 */
	public double getPorcentajeCotizacion() {
		return porcentajeCotizacion;
	}

	/**
	 * @return
	 */
	public int getPorcentajeSeguroCesantia() {
		return porcentajeSeguroCesantia;
	}

	/**
	 * @return
	 */
	public double getRentaImp90() {
		return rentaImp90;
	}

	/**
	 * @return
	 */
	public int getRentaImponible() {
		return rentaImponible;
	}

	/**
	 * @return
	 */
	public double getRentaImponibleCotizacion() {
		return rentaImponibleCotizacion;
	}

	/**
	 * @return
	 */
	public String getTemporalPeriodo() {
		return temporalPeriodo;
	}

	/**
	 * @param i
	 */
	public void setCodInstitutoPrevisional(int i) {
		codInstitutoPrevisional = i;
	}

	/**
	 * @param d
	 */
	public void setCotizacionCesantia(double d) {
		cotizacionCesantia = d;
	}

	/**
	 * @param d
	 */
	public void setCotizacionPension(double d) {
		cotizacionPension = d;
	}

	/**
	 * @param d
	 */
	public void setCotizacionSalud(double d) {
		cotizacionSalud = d;
	}

	/**
	 * @param i
	 */
	public void setDiasLicencia(int i) {
		diasLicencia = i;
	}

	/**
	 * @param i
	 */
	public void setDiasPagados(int i) {
		diasPagados = i;
	}

	/**
	 * @param string
	 */
	public void setFechaDesde(String string) {
		fechaDesde = string;
	}

	/**
	 * @param string
	 */
	public void setFechaHasta(String string) {
		fechaHasta = string;
	}

	/**
	 * @param string
	 */
	public void setFechaMesCotizacion(String string) {
		fechaMesCotizacion = string;
	}

	/**
	 * @param string
	 */
	public void setLetraAmpliacion(String string) {
		letraAmpliacion = string;
	}

	/**
	 * @param i
	 */
	public void setLicImpNum(int i) {
		licImpNum = i;
	}

	/**
	 * @param string
	 */
	public void setNomInstitutoPrevisional(String string) {
		nomInstitutoPrevisional = string;
	}

	/**
	 * @param d
	 */
	public void setPorcentajeCotizacion(double d) {
		porcentajeCotizacion = d;
	}

	/**
	 * @param i
	 */
	public void setPorcentajeSeguroCesantia(int i) {
		porcentajeSeguroCesantia = i;
	}

	/**
	 * @param d
	 */
	public void setRentaImp90(double d) {
		rentaImp90 = d;
	}

	/**
	 * @param i
	 */
	public void setRentaImponible(int i) {
		rentaImponible = i;
	}

	/**
	 * @param d
	 */
	public void setRentaImponibleCotizacion(double d) {
		rentaImponibleCotizacion = d;
	}

	/**
	 * @param i
	 */
	public void setSubsidioPagado(int i) {
		subsidioPagado = i;
	}

	/**
	 * @param string
	 */
	public void setTemporalPeriodo(String string) {
		temporalPeriodo = string;
	}

}
