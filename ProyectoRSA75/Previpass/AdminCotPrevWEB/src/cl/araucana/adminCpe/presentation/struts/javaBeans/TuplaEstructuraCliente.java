package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;

/*
 * @(#) TuplaEstructuraCliente.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author aacuna
 * 
 * @version 1.1
 */
public class TuplaEstructuraCliente implements Serializable, Comparable
{
	private static final long serialVersionUID = 6975037787001979305L;

	private String titulo;
	private String tipo;
	private String acceso;
	private String rut;
	private String nombre;
	private String cargo;

	public TuplaEstructuraCliente()
	{
	}

	/**
	 * acceso
	 * 
	 * @return
	 */
	public String getAcceso()
	{
		return this.acceso;
	}

	/**
	 * acceso
	 * 
	 * @param acceso
	 */
	public void setAcceso(String acceso)
	{
		this.acceso = acceso;
	}

	/**
	 * nombre
	 * 
	 * @return
	 */
	public String getNombre()
	{
		return this.nombre;
	}

	/**
	 * nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * rut
	 * 
	 * @return
	 */
	public String getRut()
	{
		return this.rut;
	}

	/**
	 * rut
	 * 
	 * @param rut
	 */
	public void setRut(String rut)
	{
		this.rut = rut;
	}

	/**
	 * tipo
	 * 
	 * @return
	 */
	public String getTipo()
	{
		return this.tipo;
	}

	/**
	 * tipo
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * titulo
	 * 
	 * @return
	 */
	public String getTitulo()
	{
		return this.titulo;
	}

	/**
	 * titulo
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	/**
	 * cargo
	 * 
	 * @return
	 */
	public String getCargo()
	{
		return this.cargo;
	}

	/**
	 * cargo
	 * 
	 * @param cargo
	 */
	public void setCargo(String cargo)
	{
		this.cargo = cargo;
	}

	public int compareTo(Object o)
	{
		TuplaEstructuraCliente otro = (TuplaEstructuraCliente) o;
		if (this.rut.length() == otro.getRut().length())
			return this.rut.compareTo(otro.getRut());
		return (this.rut.length() < otro.getRut().length() ? -1 : 1);
	}

}
