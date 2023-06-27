package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) Folio.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.2
 */
public class Folio implements Serializable
{
	private static final long serialVersionUID = -1052200809389300065L;
	private long numero;
	private long monto;
	private String detalle;

	public Folio()
	{
		super();
	}
	/**
	 * folio
	 * @param numero
	 * @param monto
	 * @param detalle
	 */
	public Folio(int numero, long monto, String detalle)
	{
		super();
		this.numero = numero;
		this.monto = monto;
		this.detalle = detalle;
	}
	/**
	 * detalle
	 * @return
	 */
	public String getDetalle()
	{
		return this.detalle;
	}
	/**
	 * detalle
	 * @param detalle
	 */
	public void setDetalle(String detalle)
	{
		this.detalle = detalle;
	}
	/**
	 * Monto
	 * @return
	 */
	public long getMonto()
	{
		return this.monto;
	}
	/**
	 * monto
	 * @param monto
	 */
	public void setMonto(long monto)
	{
		this.monto = monto;
	}
	/**
	 * numero
	 * @return
	 */
	public long getNumero()
	{
		return this.numero;
	}
	/**
	 * numero
	 * @param numero
	 */
	public void setNumero(long numero)
	{
		this.numero = numero;
	}
	/**
	 * xml
	 * @return
	 */
	public String toXml()
	{
		String txt = "<folio>";
		txt += "<numero>" + this.numero + "</numero>";
		txt += "<monto>" + this.monto + "</monto>";
		txt += "<detalle>" + this.detalle + "</detalle>";
		txt += "</folio>";
		return txt;
	}
}
