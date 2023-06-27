package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) Banco.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class Banco implements Serializable
{
	private static final long serialVersionUID = -6515940281883972858L;
	private String codigo;

	public Banco()
	{
		super();
	}
	/**
	 * banco
	 * @param codigo
	 */
	public Banco(String codigo)
	{
		super();
		this.codigo = codigo;
	}
	/**
	 * codigo
	 * @return
	 */
	public String getCodigo()
	{
		return this.codigo;
	}
	/**
	 * codigo
	 * @param codigo
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	/**
	 * xml
	 * @return
	 */
	public String toXml()
	{
		return "<codigo>" + this.codigo + "</codigo>\n";
	}	
}
//<codigo>BCI</codigo><codigo>TBC</codigo><codigo>BCH</codigo><codigo>BSA</codigo>