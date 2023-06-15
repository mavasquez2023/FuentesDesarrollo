/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class ConceptoGenVO {
	private int periodo;
	private String concepto;
	private int archivos;
	private int cuotas;
	private long monto;
	
	public ConceptoGenVO() {
		
	}
	
	public ConceptoGenVO(String concepto, int periodo){
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
	 * @return the archivos
	 */
	public int getArchivos() {
		return archivos;
	}

	/**
	 * @param archivos the archivos to set
	 */
	public void setArchivos(int archivos) {
		this.archivos = archivos;
	}

	/**
	 * @return the cuotas
	 */
	public int getCuotas() {
		return cuotas;
	}

	/**
	 * @param cuotas the cuotas to set
	 */
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
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

	public void addMontos(ArchivosVO archivo) {
		this.monto += archivo.getMonto();
		this.archivos += archivo.getArchivos();
		this.cuotas += archivo.getCuotas();
	}
	
	
}
