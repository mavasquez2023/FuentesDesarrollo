package cl.araucana.cp.distribuidor.business.beans;

import java.io.Serializable;
import java.util.List;

public class ReportStats implements Serializable
{
	private static final long serialVersionUID = -6396623257530800882L;
	private int numConnDisponibles;
	private int idNodo;
	private String nombreNodo;
	private String msg;
	private List estadisticas;
	private Estadistica totales;

	public ReportStats()
	{
		super();
	}

	public ReportStats(int numConnDisponibles, int idNodo, String nombreNodo, String msg, List estadisticas)
	{
		super();
		this.numConnDisponibles = numConnDisponibles;
		this.idNodo = idNodo;
		this.nombreNodo = nombreNodo;
		this.msg = msg;
		this.estadisticas = estadisticas;
	}

	public ReportStats(int numConnDisponibles, int idNodo, String nombreNodo, String msg, List estadisticas, Estadistica totales)
	{
		super();
		this.numConnDisponibles = numConnDisponibles;
		this.idNodo = idNodo;
		this.nombreNodo = nombreNodo;
		this.msg = msg;
		this.estadisticas = estadisticas;
		this.totales = totales;
	}

	public Estadistica getTotales()
	{
		return this.totales;
	}

	public void setTotales(Estadistica totales)
	{
		this.totales = totales;
	}

	public int getIdNodo()
	{
		return this.idNodo;
	}

	public void setIdNodo(int idNodo)
	{
		this.idNodo = idNodo;
	}

	public List getEstadisticas()
	{
		return this.estadisticas;
	}
	public void setEstadisticas(List estadisticas)
	{
		this.estadisticas = estadisticas;
	}
	public String getMsg()
	{
		return this.msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getNombreNodo()
	{
		return this.nombreNodo;
	}
	public void setNombreNodo(String nombreNodo)
	{
		this.nombreNodo = nombreNodo;
	}

	public int getNumConnDisponibles()
	{
		return this.numConnDisponibles;
	}

	public void setNumConnDisponibles(int numConnDisponibles)
	{
		this.numConnDisponibles = numConnDisponibles;
	}
}
