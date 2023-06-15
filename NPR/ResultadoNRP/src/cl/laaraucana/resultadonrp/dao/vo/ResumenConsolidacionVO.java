/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

import java.text.DecimalFormat;

/**
 * @author IBM Software Factory
 *
 */
public class ResumenConsolidacionVO {
	private int periodo;
	private double order;
	private String concepto;
	private int registros;
	private long monto;
	private int periodo_mesanterior;
	private int registros_mesanterior;
	private long monto_mesanterior;
	private double porcentajeDiferenciaRegistros;
	private double porcentajeDiferenciaMonto;
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
	 * @return the registros
	 */
	public String getRegistros() {
		return formateador.format(registros);
	}
	/**
	 * @param registros the registros to set
	 */
	public void setRegistros(int registros) {
		this.registros = registros;
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
	 * @return the registros_mesanterior
	 */
	public String getRegistros_mesanterior() {
		return formateador.format(registros_mesanterior);
	}
	/**
	 * @param registros_mesanterior the registros_mesanterior to set
	 */
	public void setRegistros_mesanterior(int registros_mesanterior) {
		this.registros_mesanterior = registros_mesanterior;
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
	 * @return the porcentajeDiferenciaRegistros
	 */
	public String getPorcentajeDiferenciaRegistros() {
		return formateador.format(porcentajeDiferenciaRegistros);
	}
	/**
	 * @param porcentajeDiferenciaRegistros the porcentajeDiferenciaRegistros to set
	 */
	public void setPorcentajeDiferenciaRegistros(
			double porcentajeDiferenciaRegistros) {
		this.porcentajeDiferenciaRegistros = porcentajeDiferenciaRegistros;
	}
	/**
	 * @return the porcentajeDiferenciaMonto
	 */
	public String getPorcentajeDiferenciaMonto() {
		return formateador.format(porcentajeDiferenciaMonto);
	}
	/**
	 * @param porcentajeDiferenciaMonto the porcentajeDiferenciaMonto to set
	 */
	public void setPorcentajeDiferenciaMonto(double porcentajeDiferenciaMonto) {
		this.porcentajeDiferenciaMonto = porcentajeDiferenciaMonto;
	}
	
	
}
