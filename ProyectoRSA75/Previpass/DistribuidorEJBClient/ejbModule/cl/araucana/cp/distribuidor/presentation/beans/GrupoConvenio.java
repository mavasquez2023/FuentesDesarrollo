package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * @(#) GrupoConvenio.java 1.1 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class GrupoConvenio implements Serializable, Comparable
{
	private static final long serialVersionUID = 3571546489879541627L;
	private int idGrupo;
	private boolean permLector;
	private String nombre;
	private List empresas;

	public GrupoConvenio()
	{
		super();
	}

	/**
	 * grupo convenio
	 * 
	 * @param idGrupo
	 * @param permLector
	 * @param nombre
	 */
	public GrupoConvenio(int idGrupo, boolean permLector, String nombre)
	{
		super();
		this.idGrupo = idGrupo;
		this.permLector = permLector;
		this.nombre = nombre;
		this.empresas = new ArrayList();
	}

	/**
	 * grupo convenio
	 * 
	 * @param idGrupo
	 * @param permLector
	 * @param nombre
	 * @param empresas
	 */
	public GrupoConvenio(int idGrupo, boolean permLector, String nombre, List empresas)
	{
		super();
		this.idGrupo = idGrupo;
		this.permLector = permLector;
		this.nombre = nombre;
		this.empresas = empresas;
	}

	/**
	 * grupo convenio
	 * 
	 * @param idGrupo
	 * @param nombre
	 * @param empresas
	 */
	public GrupoConvenio(int idGrupo, String nombre, List empresas)
	{
		super();
		this.idGrupo = idGrupo;
		this.nombre = nombre;
		this.empresas = empresas;
	}

	/**
	 * empresas
	 * 
	 * @return
	 */
	public List getEmpresas()
	{
		return this.empresas;
	}

	/**
	 * empresas
	 * 
	 * @param empresas
	 */
	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
	}

	/**
	 * id grupo
	 * 
	 * @return
	 */
	public int getIdGrupo()
	{
		return this.idGrupo;
	}

	/**
	 * id grupo
	 * 
	 * @param idGrupo
	 */
	public void setIdGrupo(int idGrupo)
	{
		this.idGrupo = idGrupo;
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
	 * empresa
	 * 
	 * @param idEmpresa
	 * @return
	 */
	public Empresa getEmpresa(int idEmpresa)
	{
		for (Iterator it = this.empresas.iterator(); it.hasNext();)
		{
			Empresa emp = (Empresa) it.next();
			if (idEmpresa == emp.getIdEmpresa())
				return emp;
		}
		return null;
	}

	/**
	 * agrega empresa
	 * 
	 * @param e
	 */
	public void addEmpresa(Empresa e)
	{
		this.empresas.add(e);
	}

	/**
	 * compara nombre
	 */
	public int compareTo(Object o)
	{
		return this.nombre.compareTo(((GrupoConvenio) o).nombre);
	}
}
