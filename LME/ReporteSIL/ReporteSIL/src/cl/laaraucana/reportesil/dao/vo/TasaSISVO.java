/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class TasaSISVO {
	private int periodoInicial;
	private int periodoFinal;
	private double tasa;
	/**
	 * @return the periodoInicial
	 */
	public int getPeriodoInicial() {
		return periodoInicial;
	}
	/**
	 * @param periodoInicial the periodoInicial to set
	 */
	public void setPeriodoInicial(int periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	/**
	 * @return the periodoFinal
	 */
	public int getPeriodoFinal() {
		return periodoFinal;
	}
	/**
	 * @param periodoFinal the periodoFinal to set
	 */
	public void setPeriodoFinal(int periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	/**
	 * @return the tasa
	 */
	public double getTasa() {
		return tasa;
	}
	/**
	 * @param tasa the tasa to set
	 */
	public void setTasa(double tasa) {
		this.tasa = tasa;
	}
	
}
