package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
/*
* @(#) MovimientoPersonal.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * 
 * @version 1.1
 */
public class MovtoPersonal implements Serializable
{
	private static final long serialVersionUID = 88332095745305682L;

	private int idMovimiento;
	private int idEntidadSil;

	private int idTipoMovReal;
	private String inicio;
	private String termino;
	
	private boolean bloquear;

	/**
	 * movimiento personal
	 * @param idMovimiento
	 * @param idEntidadSil
	 * @param idTipoMovReal
	 * @param inicio
	 * @param termino
	 */
	public MovtoPersonal(int idMovimiento, int idEntidadSil, int idTipoMovReal, String inicio, String termino) {
		super();
		this.idMovimiento = idMovimiento;
		this.idEntidadSil = idEntidadSil;
		this.idTipoMovReal = idTipoMovReal;
		this.inicio = inicio;
		this.termino = termino;
	}
	public MovtoPersonal()
	{
		super();
	}
	/**
	 * movimiento personal
	 * @param idMovimiento
	 * @param idTipoMovReal
	 * @param inicio
	 * @param termino
	 */
	public MovtoPersonal(int idMovimiento, int idTipoMovReal, String inicio, String termino)
	{
		super();
		this.idMovimiento = idMovimiento;
		this.idTipoMovReal = idTipoMovReal;
		this.inicio = inicio;
		this.termino = termino;
	}
	/**
	 * id movimiento
	 * @return
	 */
	public int getIdMovimiento()
	{
		return this.idMovimiento;
	}
	/**
	 * id movimiento
	 * @param idMovimiento
	 */
	public void setIdMovimiento(int idMovimiento)
	{
		this.idMovimiento = idMovimiento;
	}
	/**
	 * id tipo movimiento real
	 * @return
	 */
	public int getIdTipoMovReal()
	{
		return this.idTipoMovReal;
	}
	/**
	 * id tipo movimiento real
	 * @param idTipoMovReal
	 */
	public void setIdTipoMovReal(int idTipoMovReal)
	{
		this.idTipoMovReal = idTipoMovReal;
	}
	/**
	 * inicio
	 * @return
	 */
	public String getInicio()
	{
		return this.inicio;
	}
	/**
	 * inicio
	 * @param inicio
	 */
	public void setInicio(String inicio)
	{
		this.inicio = inicio;
	}
	/**
	 * termino
	 * @return
	 */
	public String getTermino()
	{
		return this.termino;
	}
	/**
	 * termino
	 * @param termino
	 */
	public void setTermino(String termino)
	{
		this.termino = termino;
	}
	/**
	 * id entidad sil
	 * @return
	 */
	public int getIdEntidadSil() {
		return this.idEntidadSil;
	}
	/**
	 * id entidad sil
	 * @param idEntidadSil
	 */
	public void setIdEntidadSil(int idEntidadSil) {
		this.idEntidadSil = idEntidadSil;
	}
	public boolean isBloquear() {
		return bloquear;
	}
	public void setBloquear(boolean bloquear) {
		this.bloquear = bloquear;
	}
}
