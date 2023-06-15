/*
 * Creado el 14-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @author USIST15
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class LiquidacionesVO implements Serializable{
	
	private String fechaLiquidacion=null;
	private int nroLiquidacion =0;
	private double saldoPrevioLiquidacion = 0;
	private double reajuste = 0;
	private double comision = 0;
	private double totalSolicitadoPesos = 0;
	private double totalPendientePesos = 0;
	private double totalRechazadoPesos = 0;
	private double saldoPosterior = 0;
	private double totalBonificadoPesos = 0;
	private double rebajaSolicitadaEmpresa = 0;
	private long numeroConvenio = 0;
	private Date fechaHoy = new Date();
	
		
	public String getDatosParaSeleccionar(){
		return fechaLiquidacion + " - Nro Liq : " +  nroLiquidacion + " - Monto : " +  totalBonificadoPesos;
	}
	
	/**
	 * @return
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * @return
	 */
	public int getNroLiquidacion() {
		return nroLiquidacion;
	}

	/**
	 * @return
	 */
	public double getSaldoPosterior() {
		return saldoPosterior;
	}

	/**
	 * @return
	 */
	public double getSaldoPrevioLiquidacion() {
		return saldoPrevioLiquidacion;
	}

	/**
	 * @return
	 */
	public double getTotalBonificadoPesos() {
		return totalBonificadoPesos;
	}

	/**
	 * @return
	 */
	public double getTotalPendientePesos() {
		return totalPendientePesos;
	}

	/**
	 * @return
	 */
	public double getTotalRechazadoPesos() {
		return totalRechazadoPesos;
	}

	/**
	 * @return
	 */
	public double getTotalSolicitadoPesos() {
		return totalSolicitadoPesos;
	}

	/**
	 * @param date
	 */
	public void setFechaLiquidacion(String date) {
		fechaLiquidacion = date;
	}

	/**
	 * @param i
	 */
	public void setNroLiquidacion(int i) {
		nroLiquidacion = i;
	}

	/**
	 * @param d
	 */
	public void setSaldoPosterior(double d) {
		saldoPosterior = d;
	}

	/**
	 * @param d
	 */
	public void setSaldoPrevioLiquidacion(double d) {
		saldoPrevioLiquidacion = d;
	}

	/**
	 * @param d
	 */
	public void setTotalBonificadoPesos(double d) {
		totalBonificadoPesos = d;
	}

	/**
	 * @param d
	 */
	public void setTotalPendientePesos(double d) {
		totalPendientePesos = d;
	}

	/**
	 * @param d
	 */
	public void setTotalRechazadoPesos(double d) {
		totalRechazadoPesos = d;
	}

	/**
	 * @param d
	 */
	public void setTotalSolicitadoPesos(double d) {
		totalSolicitadoPesos = d;
	}

	/**
	 * @return Int Numero de convenio
	 */
	public long getNumeroConvenio() {
		return numeroConvenio;
	}

	/**
	 * @param i Nuemro de convenio
	 */
	public void setNumeroConvenio(int i) {
		numeroConvenio = i;
	}

	/**
	 * @param l
	 */
	public void setNumeroConvenio(long l) {
		numeroConvenio = l;
	}

	/**
	 * @return
	 */
	public double getRebajaSolicitadaEmpresa() {
		return rebajaSolicitadaEmpresa;
	}

	/**
	 * @param d
	 */
	public void setRebajaSolicitadaEmpresa(double d) {
		rebajaSolicitadaEmpresa = d;
	}

	/**
	 * @return
	 */
	public Date getFechaHoy() {
		return fechaHoy;
	}

	/**
	 * @param date
	 */
	public void setFechaHoy(Date date) {
		fechaHoy = date;
	}


	/**
	 * @return
	 */
	public double getReajuste() {
		return reajuste;
	}

	/**
	 * @param d
	 */
	public void setReajuste(double d) {
		reajuste = d;
	}

	/**
	 * @return
	 */
	public double getComision() {
		return comision;
	}

	/**
	 * @param d
	 */
	public void setComision(double d) {
		comision = d;
	}

}
