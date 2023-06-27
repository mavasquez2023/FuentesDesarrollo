package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;

/*
 * @(#) LineaComprobantePago.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * 
 * @version 1.1
 */
public class LineaComprobantePago implements Serializable, Comparable
{
	private static final long serialVersionUID = -1;

	private String rutEmpresa;
	private String convenio;
	private String proceso;
	private String razonSocial;
	private String total;
	private String fechaEnvio;
	private String estado;

	public LineaComprobantePago()
	{
		super();
	}

	/**
	 * convenio
	 * 
	 * @return
	 */
	public String getConvenio()
	{
		return this.convenio;
	}

	/**
	 * convenio
	 * 
	 * @param convenio
	 */
	public void setConvenio(String convenio)
	{
		this.convenio = convenio;
	}

	/**
	 * estado
	 * 
	 * @return
	 */
	public String getEstado()
	{
		return this.estado;
	}

	/**
	 * estado
	 * 
	 * @param estado
	 */
	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	/**
	 * fecha envio
	 * 
	 * @return
	 */
	public String getFechaEnvio()
	{
		return this.fechaEnvio;
	}

	/**
	 * fecha envio
	 * 
	 * @param fechaEnvio
	 */
	public void setFechaEnvio(String fechaEnvio)
	{
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * proceso
	 * 
	 * @return
	 */
	public String getProceso()
	{
		return this.proceso;
	}

	/**
	 * proceso
	 * 
	 * @param proceso
	 */
	public void setProceso(String proceso)
	{
		this.proceso = proceso;
	}

	/**
	 * razon social
	 * 
	 * @return
	 */
	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	/**
	 * razon social
	 * 
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * rut empresa
	 * 
	 * @return
	 */
	public String getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	/**
	 * rut empresa
	 * 
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * total
	 * 
	 * @return
	 */
	public String getTotal()
	{
		return this.total;
	}

	/**
	 * total
	 * 
	 * @param total
	 */
	public void setTotal(String total)
	{
		this.total = total;
	}

	public int compareTo(Object o)
	{
		LineaComprobantePago otro = (LineaComprobantePago)o;
		if (this.rutEmpresa.compareTo(otro.getRutEmpresa()) == 0)
			return this.convenio.compareTo(otro.getConvenio());
		return this.rutEmpresa.compareTo(otro.getRutEmpresa());
	}
}
