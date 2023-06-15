/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class ArchivosVO {
	private int periodo;
	private String tipo_afiliado;
	private String tipo_archivo;
	private int archivos;
	private int cuotas;
	private long monto;
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
	 * @return the tipo_afiliado
	 */
	public String getTipo_afiliado() {
		return tipo_afiliado;
	}
	/**
	 * @param tipo_afiliado the tipo_afiliado to set
	 */
	public void setTipo_afiliado(String tipo_afiliado) {
		this.tipo_afiliado = tipo_afiliado;
	}
	/**
	 * @return the tipo_archivo
	 */
	public String getTipo_archivo() {
		return tipo_archivo;
	}
	/**
	 * @param tipo_archivo the tipo_archivo to set
	 */
	public void setTipo_archivo(String tipo_archivo) {
		this.tipo_archivo = tipo_archivo;
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
	
	
}
