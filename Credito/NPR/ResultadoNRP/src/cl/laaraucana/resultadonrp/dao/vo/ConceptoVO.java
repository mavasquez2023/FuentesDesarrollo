/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class ConceptoVO {
	private int periodo;
	private String concepto;
	private int registros;
	private long monto;
	
	public ConceptoVO() {
		
	}
	
	public ConceptoVO(String concepto, int periodo){
		this.concepto= concepto;
		this.periodo= periodo;
	}
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
	public int getRegistros() {
		return registros;
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
	public long getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(long monto) {
		this.monto = monto;
	}
	
	public void addMontos(ProductoVO producto) {
		this.monto += producto.getMonto();
		this.registros += producto.getRegistros();
	}
	
	public void addMontos(FolioVO folio) {
		this.monto += folio.getMonto();
		this.registros += folio.getRegistros();
	}
	
}
