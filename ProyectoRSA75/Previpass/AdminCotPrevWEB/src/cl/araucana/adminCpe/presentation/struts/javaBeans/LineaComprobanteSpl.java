package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;

/*
 * @(#) LineaComprobanteSpl.java 1.2 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * 
 * @version 1.2
 */
public class LineaComprobanteSpl implements Comparable, Serializable
{
	private static final long serialVersionUID = -1;

	private String grupo;
	private String empresa;
	private String convenio;
	private String proceso;
	private String monto;
	private long total;
	private String numeroComprobante;
	private boolean comienzo;
	private boolean fin;

	/**
	 * linea comprobante spl
	 * 
	 * @param grupo
	 * @param empresa
	 * @param convenio
	 * @param proceso
	 * @param monto
	 * @param total
	 * @param numeroComprobante
	 * @param comienzo
	 * @param fin
	 */
	public LineaComprobanteSpl(String grupo, String empresa, String convenio, String proceso, String monto, long total, String numeroComprobante, boolean comienzo, boolean fin)
	{
		super();
		this.grupo = grupo;
		this.empresa = empresa;
		this.convenio = convenio;
		this.proceso = proceso;
		this.monto = monto;
		this.total = total;
		this.numeroComprobante = numeroComprobante;
		this.comienzo = comienzo;
		this.fin = fin;
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
	 * empresa
	 * 
	 * @return
	 */
	public String getEmpresa()
	{
		return this.empresa;
	}

	/**
	 * empresa
	 * 
	 * @param empresa
	 */
	public void setEmpresa(String empresa)
	{
		this.empresa = empresa;
	}

	/**
	 * grupo empresa
	 * 
	 * @return
	 */
	public String getGrupo()
	{
		return this.grupo;
	}

	/**
	 * grupo
	 * 
	 * @param grupo
	 */
	public void setGrupo(String grupo)
	{
		this.grupo = grupo;
	}

	/**
	 * monto
	 * 
	 * @return
	 */
	public String getMonto()
	{
		return this.monto;
	}

	/**
	 * monto
	 * 
	 * @param monto
	 */
	public void setMonto(String monto)
	{
		this.monto = monto;
	}

	/**
	 * numero comprobante
	 * 
	 * @return
	 */
	public String getNumeroComprobante()
	{
		return this.numeroComprobante;
	}

	/**
	 * numero comprobante
	 * 
	 * @param numeroComprobante
	 */
	public void setNumeroComprobante(String numeroComprobante)
	{
		this.numeroComprobante = numeroComprobante;
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
	 * linea comprobante spl
	 * 
	 */
	public LineaComprobanteSpl()
	{
		super();
	}

	/**
	 * total
	 * 
	 * @return
	 */
	public long getTotal()
	{
		return this.total;
	}

	/**
	 * total
	 * 
	 * @param total
	 */
	public void setTotal(long total)
	{
		this.total = total;
	}

	/**
	 * comienzo
	 * 
	 * @return
	 */
	public boolean isComienzo()
	{
		return this.comienzo;
	}

	/**
	 * comienzo
	 * 
	 * @param comienzo
	 */
	public void setComienzo(boolean comienzo)
	{
		this.comienzo = comienzo;
	}

	/**
	 * fin
	 * 
	 * @return
	 */
	public boolean isFin()
	{
		return this.fin;
	}

	/**
	 * fin
	 * 
	 * @param fin
	 */
	public void setFin(boolean fin)
	{
		this.fin = fin;
	}

	public int compareTo(Object o)
	{
		LineaComprobanteSpl otro = (LineaComprobanteSpl) o;
		if (this.grupo.compareTo(otro.getGrupo()) == 0)
		{
			if (this.empresa.compareTo(otro.getEmpresa()) == 0)
				return this.convenio.compareTo(otro.getConvenio());
			return this.empresa.compareTo(otro.getEmpresa());
		}
		return this.grupo.compareTo(otro.grupo);
	}
}
