/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author IBM Software Factory
 *
 */
public class ResumenGeneracionVO {
	private int periodo;
	private double order;
	private String concepto;
	private long monto;
	private int cuotas;
	private int archivos;
	private int periodo_mesanterior;
	private long monto_mesanterior;
	private int cuotas_mesanterior;
	private int archivos_mesanterior;
	DecimalFormat formateador = new DecimalFormat("###,###.##");
	
	/**
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the order
	 */
	public double getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(double order) {
		this.order = order;
	}
	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return the monto
	 */
	public String getMonto() {
		return formateador.format(monto);
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(long monto) {
		this.monto = monto;
	}
	/**
	 * @return the cuotas
	 */
	public String getCuotas() {
		return formateador.format(cuotas);
	}
	/**
	 * @param cuotas the cuotas to set
	 */
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	/**
	 * @return the archivos
	 */
	public String getArchivos() {
		return formateador.format(archivos);
	}
	/**
	 * @param archivos the archivos to set
	 */
	public void setArchivos(int archivos) {
		this.archivos = archivos;
	}
	/**
	 * @return the periodo_mesanterior
	 */
	public int getPeriodo_mesanterior() {
		return periodo_mesanterior;
	}
	/**
	 * @param periodo_mesanterior the periodo_mesanterior to set
	 */
	public void setPeriodo_mesanterior(int periodo_mesanterior) {
		this.periodo_mesanterior = periodo_mesanterior;
	}
	/**
	 * @return the monto_mesanterior
	 */
	public String getMonto_mesanterior() {
		return formateador.format(monto_mesanterior);
	}
	/**
	 * @param monto_mesanterior the monto_mesanterior to set
	 */
	public void setMonto_mesanterior(long monto_mesanterior) {
		this.monto_mesanterior = monto_mesanterior;
	}
	/**
	 * @return the cuotas_mesanterior
	 */
	public String getCuotas_mesanterior() {
		return formateador.format(cuotas_mesanterior);
	}
	/**
	 * @param cuotas_mesanterior the cuotas_mesanterior to set
	 */
	public void setCuotas_mesanterior(int cuotas_mesanterior) {
		this.cuotas_mesanterior = cuotas_mesanterior;
	}
	/**
	 * @return the archivos_mesanterior
	 */
	public String getArchivos_mesanterior() {
		return formateador.format(archivos_mesanterior);
	}
	/**
	 * @param archivos_mesanterior the archivos_mesanterior to set
	 */
	public void setArchivos_mesanterior(int archivos_mesanterior) {
		this.archivos_mesanterior = archivos_mesanterior;
	}


}
