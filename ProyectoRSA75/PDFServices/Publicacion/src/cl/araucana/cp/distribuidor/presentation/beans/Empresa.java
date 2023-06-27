package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * @(#) Empresa.java 1.6 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.6
 */
public class Empresa implements Serializable, Comparable
{
	private static final long serialVersionUID = 9204311324072854851L;
	private int idEmpresa;
	private boolean permLector;
	private String rut;
	private String razonSocial;

	private List convenios = new ArrayList();

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 * @param razonSocial
	 */
	public Empresa(int idEmpresa, String razonSocial)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.razonSocial = razonSocial;
	}

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 * @param rut
	 * @param razonSocial
	 */
	public Empresa(int idEmpresa, String rut, String razonSocial)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.rut = rut;
		this.razonSocial = razonSocial;
	}

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 * @param permLector
	 * @param rut
	 * @param razonSocial
	 */
	public Empresa(int idEmpresa, boolean permLector, String rut, String razonSocial)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.permLector = permLector;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.convenios = new ArrayList();
	}

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 * @param permLector
	 * @param rut
	 * @param razonSocial
	 * @param convenios
	 */
	public Empresa(int idEmpresa, boolean permLector, String rut, String razonSocial, List convenios)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.permLector = permLector;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.convenios = convenios;
	}

	public Empresa()
	{
		super();
	}

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 */
	public Empresa(int idEmpresa)
	{
		super();
		this.idEmpresa = idEmpresa;
	}

	/**
	 * empresa
	 * 
	 * @param idEmpresa
	 * @param rut
	 * @param razonSocial
	 * @param convenios
	 */
	public Empresa(int idEmpresa, String rut, String razonSocial, List convenios)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.convenios = convenios;
	}

	/**
	 * convenios
	 * 
	 * @return
	 */
	public List getConvenios()
	{
		return this.convenios;
	}

	/**
	 * convenios
	 * 
	 * @param convenios
	 */
	public void setConvenios(List convenios)
	{
		this.convenios = convenios;
	}

	/**
	 * id empresa
	 * 
	 * @return
	 */
	public int getIdEmpresa()
	{
		return this.idEmpresa;
	}

	/**
	 * id empresa
	 * 
	 * @param idEmpresa
	 */
	public void setIdEmpresa(int idEmpresa)
	{
		this.idEmpresa = idEmpresa;
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
	 * permiso lector
	 * 
	 * @return
	 */
	public boolean isPermLector()
	{
		return this.permLector;
	}

	/**
	 * permiso lector
	 * 
	 * @param permLector
	 */
	public void setPermLector(boolean permLector)
	{
		this.permLector = permLector;
	}

	/**
	 * permiso encargado lector
	 * 
	 * @param idConvenio
	 * @return
	 */
	public PermEncargadoLector getPermEncargadoLector(int idConvenio)
	{
		for (Iterator it = this.convenios.iterator(); it.hasNext();)
		{
			PermEncargadoLector perm = (PermEncargadoLector) it.next();
			if (idConvenio == perm.getIdConvenio())
				return perm;
		}
		return null;
	}

	/**
	 * agregar permiso encargado lector
	 * 
	 * @param perm
	 */
	public void addPermEncargadoLector(PermEncargadoLector perm)
	{
		this.convenios.add(perm);
	}

	/**
	 * compara id empresa
	 */
	public int compareTo(Object o)
	{
		return new Integer(this.idEmpresa).compareTo(new Integer(((Empresa) o).idEmpresa));
	}
}
