package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class ValidacionVO implements Serializable
{
	private static Logger logger = Logger.getLogger(ValidacionVO.class);
	private static final long serialVersionUID = 2767069432757657836L;
	private String idValidacion;
	private char tipoProceso;

	private char ejecutarEn;
	private String 	claseValidador;
	private String descripcion;
	private List siguientes;
	private List conceptos;
		
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.idValidacion == null) ? 0 : this.idValidacion.hashCode());
		result = PRIME * result + this.tipoProceso;
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ValidacionVO other = (ValidacionVO) obj;
		if (this.idValidacion == null) {
			if (other.idValidacion != null)
				return false;
		} else if (!this.idValidacion.equals(other.idValidacion))
			return false;
		if (this.tipoProceso != other.tipoProceso)
			return false;
		return true;
	}
	public String getClaseValidador()
	{
		return this.claseValidador;
	}
	public void setClaseValidador(String claseValidador)
	{
		this.claseValidador = claseValidador;
	}
	public List getConceptos()
	{
		return this.conceptos;
	}
	public void setConceptos(List conceptos)
	{
		this.conceptos = conceptos;
	}
	public char getEjecutarEn()
	{
		return this.ejecutarEn;
	}
	public void setEjecutarEn(char ejecutarEn)
	{
		this.ejecutarEn = ejecutarEn;
	}
	public char getTipoProceso()
	{
		return this.tipoProceso;
	}
	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public String getIdValidacion()
	{
		return this.idValidacion;
	}
	public void setIdValidacion(String id)
	{
		this.idValidacion = id;
	}
	public List getSiguientes()
	{
		return this.siguientes;
	}
	public void setSiguientes(List siguientes)
	{
		this.siguientes = siguientes;
	}
	
	public String toString()
	{
		logger.debug("validacion:" + this.getIdValidacion() + ":" + this.getDescripcion() + ":");
		for (Iterator it2 = this.getSiguientes().iterator(); it2.hasNext();)
		{
			NodoSiguienteVO ns = (NodoSiguienteVO) it2.next();
			logger.debug(ns.toString());
		}
		for (Iterator it2 = this.getConceptos().iterator(); it2.hasNext();)
		{
			ConceptoValidacionVO cv = (ConceptoValidacionVO) it2.next();
			logger.debug(cv.toString());
		}
		return "validacion:" + this.getIdValidacion() + ":" + this.getDescripcion() + ":";
	}
	
	public StringBuffer toLine()
	{
		StringBuffer sb = new StringBuffer(this.idValidacion.trim()).append('#').append(this.claseValidador.trim()).append('$');
		for (Iterator it2 = this.getSiguientes().iterator(); it2.hasNext();)
			sb.append(((NodoSiguienteVO) it2.next()).toLine());
		sb.append('$');
		for (Iterator it2 = this.getConceptos().iterator(); it2.hasNext();)
			sb.append(((ConceptoValidacionVO) it2.next()).toLine());
		return sb.append('$');
	}
}
