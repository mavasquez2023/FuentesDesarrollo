package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.List;

public class CargaFamiliarVO implements Serializable 
{
	private static final long serialVersionUID = 4548024932185313541L;

	private int idCargaFamiliar;
	private int idEntidadCCAF;
	private int rutEmpresa;
	private char dvEmpresa;
	private int rutTrabajador;
	private char dvTrabajador;
	private String nombreTrabajador;
	private int idTramoAF;
	
	private List tiposCargas;
	
	public CargaFamiliarVO()
	{
		super();
	}

	public char getDvEmpresa()
	{
		return this.dvEmpresa;
	}

	public void setDvEmpresa(char dvEmpresa)
	{
		this.dvEmpresa = dvEmpresa;
	}

	public char getDvTrabajador()
	{
		return this.dvTrabajador;
	}

	public void setDvTrabajador(char dvTrabajador)
	{
		this.dvTrabajador = dvTrabajador;
	}

	public int getIdCargaFamiliar()
	{
		return this.idCargaFamiliar;
	}

	public void setIdCargaFamiliar(int idCargaFamiliar)
	{
		this.idCargaFamiliar = idCargaFamiliar;
	}

	public int getIdEntidadCCAF()
	{
		return this.idEntidadCCAF;
	}

	public void setIdEntidadCCAF(int idEntidadCCAF)
	{
		this.idEntidadCCAF = idEntidadCCAF;
	}

	public int getIdTramoAF()
	{
		return this.idTramoAF;
	}

	public void setIdTramoAF(int idTramoAF)
	{
		this.idTramoAF = idTramoAF;
	}

	public String getNombreTrabajador()
	{
		return this.nombreTrabajador;
	}

	public void setNombreTrabajador(String nombreTrabajador)
	{
		this.nombreTrabajador = nombreTrabajador;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public int getRutTrabajador()
	{
		return this.rutTrabajador;
	}

	public void setRutTrabajador(int rutTrabajador)
	{
		this.rutTrabajador = rutTrabajador;
	}

	public List getTiposCargas()
	{
		return this.tiposCargas;
	}

	public void setTiposCargas(List tiposCargas)
	{
		this.tiposCargas = tiposCargas;
	}
}