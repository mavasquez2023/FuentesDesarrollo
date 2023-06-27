package cl.araucana.cp.logger.beans;
/*
* @(#) Parametro.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * Esta clase fue generada solo con el proposito de almacenar al inicio de la aplicación los valores 
 * de las tablas de log (parametro, tipoParametro, etc) para la generacion del log. No pretendase usar para
 * otr cosa.
 * @author cllanos
 * 
 * @version 1.1
 */
public class Parametro{
	private String id;
	/**
	 * id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	private String nombre;
	private int largo;
	private int tipo;

	/**
	 * tipo
	 * @return
	 */
	public int getTipo() {
		return this.tipo;
	}
	/**
	 * tipo
	 * @param tipo
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	/**
	 * nombre
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * largo
	 * @return
	 */
	public int getLargo() {
		return this.largo;
	}
	/**
	 * largo
	 * @param largo
	 */
	public void setLargo(int largo) {
		this.largo = largo;
	}
}
