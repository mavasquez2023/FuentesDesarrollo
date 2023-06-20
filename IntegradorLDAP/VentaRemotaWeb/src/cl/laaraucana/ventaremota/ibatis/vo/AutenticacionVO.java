/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.vo;

/**
 * @author IBM Software Factory
 *
 */
public class AutenticacionVO {
	private String codigo;
	private int orden;
	private String nombre;
	private int habilitada;
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
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	public void setOrden(int orden) {
		this.orden = orden;
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
	 * @return the habilitada
	 */
	public int getHabilitada() {
		return habilitada;
	}
	/**
	 * @param habilitada the habilitada to set
	 */
	public void setHabilitada(int habilitada) {
		this.habilitada = habilitada;
	}
	
	
}
