package cl.araucana.cp.presentation.struts.javaBeans;


/*
* @(#) LineaComprobanteAPagar.java 1.1 10/05/2009
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
public class LineaComprobantesAPagar implements Comparable
{
	private long codigoBarra;
	private String rutFmt;
	private String razonSocial;
	private String nombreConvenio;
	private long total;
	private int idConvenio;

	/**
	 * codigo barra
	 * @return
	 */
	public long getCodigoBarra()
	{
		return this.codigoBarra;
	}

	/**
	 * codigo barra
	 * @param codigoBarra
	 */
	public void setCodigoBarra(long codigoBarra)
	{
		this.codigoBarra = codigoBarra;
	}

	/**
	 * nombre convenio
	 * @return
	 */
	public String getNombreConvenio()
	{
		return this.nombreConvenio;
	}

	/**
	 * nombre convenio
	 * @param nombreConvenio
	 */
	public void setNombreConvenio(String nombreConvenio)
	{
		this.nombreConvenio = nombreConvenio;
	}

	/**
	 * razon social
	 * @return
	 */
	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	/**
	 * razon social
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * rut fmt
	 * @return
	 */
	public String getRutFmt()
	{
		return this.rutFmt;
	}

	/**
	 * rut fmt
	 * @param rutFmt
	 */
	public void setRutFmt(String rutFmt)
	{
		this.rutFmt = rutFmt;
	}

	/**
	 * total
	 * @return
	 */
	public long getTotal()
	{
		return this.total;
	}

	/**
	 * total
	 * @param total
	 */
	public void setTotal(long total)
	{
		this.total = total;
	}

	/**
	 * codigo barra
	 */
	public String toString() 
	{
		return "[codigoBarra: " + this.codigoBarra + ", rutFmt: \"" + this.rutFmt + "\", razonSocial: \"" + this.razonSocial
			+ "\", nombreConvenio: \"" + this.nombreConvenio + "\", total: " + this.total + "]"; 
	}

	public int compareTo(Object o) 
	{
		LineaComprobantesAPagar otro = (LineaComprobantesAPagar)o;
		int comp = this.rutFmt.compareTo(otro.getRutFmt());
		if (comp == 0)
		{
			return new Integer(this.idConvenio).compareTo(new Integer(otro.getIdConvenio()));
		}
		return comp;
	}

	public int getIdConvenio() 
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio) 
	{
		this.idConvenio = idConvenio;
	}
}
