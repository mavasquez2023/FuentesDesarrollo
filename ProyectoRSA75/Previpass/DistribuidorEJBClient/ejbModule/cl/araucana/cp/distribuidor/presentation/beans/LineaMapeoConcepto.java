package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
/*
* @(#) LineaMapeoConcepto.java 1.1 10/05/2009
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
public class LineaMapeoConcepto implements Serializable
{
	private static final long serialVersionUID = -600917614237644604L;

	private int idMapaNom;
	private String tipoNomina;
	private int idConcepto;
	private String nombre;
	private int posicion;
	private int largo;
	private int tipoSeparador;
	private Character caracterSeparador;
	
	public LineaMapeoConcepto() {}
	/**
	 * id concepto
	 * @return 
	 */
	public int getIdConcepto()
	{
		return this. idConcepto;
	}
	/**
	 * id concepto
	 * @param idConcepto
	 */
	public void setIdConcepto(int idConcepto)
	{
		this.idConcepto = idConcepto;
	}
	/**
	 * id mapa nombre
	 * @return this.
	 */
	public int getIdMapaNom()
	{
		return this. idMapaNom;
	}
	/**
	 * id mapa nombre
	 * @param idMapaNom
	 */
	public void setIdMapaNom(int idMapaNom)
	{
		this.idMapaNom = idMapaNom;
	}
	/**
	 * largo
	 * @return this.
	 */
	public int getLargo()
	{
		return this. largo;
	}
	/**
	 * largo
	 * @param largo
	 */
	public void setLargo(int largo)
	{
		this.largo = largo;
	}
	/**
	 * nombre
	 * @return this.
	 */
	public String getNombre()
	{
		return this. nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	/**
	 * posicion
	 * @return this.
	 */
	public int getPosicion()
	{
		return this. posicion;
	}
	/**
	 * posicion
	 * @param posicion
	 */
	public void setPosicion(int posicion)
	{
		this.posicion = posicion;
	}
	/**
	 * tipo nomina
	 * @return this.
	 */
	public String getTipoNomina()
	{
		return this. tipoNomina;
	}
	/**
	 * tipo nomina
	 * @param tipoNomina
	 */
	public void setTipoNomina(String tipoNom)
	{
		this.tipoNomina = tipoNom;
	}
	public int getTipoSeparador()
	{
		return this.tipoSeparador;
	}
	public void setTipoSeparador(int tipoSeparador)
	{
		this.tipoSeparador = tipoSeparador;
	}
	public Character getCaracterSeparador()
	{
		return this.caracterSeparador;
	}
	public void setCaracterSeparador(Character caracterSeparador)
	{
		this.caracterSeparador = caracterSeparador;
	}
}
