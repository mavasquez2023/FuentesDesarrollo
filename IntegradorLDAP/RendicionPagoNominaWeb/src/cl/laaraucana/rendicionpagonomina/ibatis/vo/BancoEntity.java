/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.ibatis.vo;

/**
 * @author IBM Software Factory
 *
 */
public class BancoEntity {
	private String codigo;
	private String nombre;
	private String descripcionBES;
	private String descripcionBCI;
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the descripcionBES
	 */
	public String getDescripcionBES() {
		return descripcionBES;
	}
	/**
	 * @param descripcionBES the descripcionBES to set
	 */
	public void setDescripcionBES(String descripcionBES) {
		this.descripcionBES = descripcionBES;
	}
	/**
	 * @return the descripcionBCI
	 */
	public String getDescripcionBCI() {
		return descripcionBCI;
	}
	/**
	 * @param descripcionBCI the descripcionBCI to set
	 */
	public void setDescripcionBCI(String descripcionBCI) {
		this.descripcionBCI = descripcionBCI;
	}
	
	
}
