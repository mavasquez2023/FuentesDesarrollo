/**
 * 
 */
package cl.araucana.cotcarserv.dao.VO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author IBM Software Factory
 *
 */
public class PeriodosVO {
	private int id;
	private String nombre;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	
	public Map<String, String> toHashMap(){
		Map<String, String> perVO= new HashMap<String, String>();
		perVO.put("id", String.valueOf(this.id));
		perVO.put("nombre", this.nombre);
		return perVO;
		
	}
}
