package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class EntidadMutualVO implements Serializable
{
	private static final long serialVersionUID = -114622714435539530L;
	private int id;
	private int idEntPagadora;
	private String nombre;
	
	public EntidadMutualVO() {
		super();
	}

	public EntidadMutualVO(int id) {
		this.id = id;
	}

	public EntidadMutualVO(int id, int idEntPagadora) {
		this.id = id;
		this.idEntPagadora = idEntPagadora;		
	}

	public String getClave()
	{
		return "MUTUAL";
	}

	public boolean isCotizanteRefered()
	{
		return false;
	}

	public boolean isAfcRefered()
	{
		return false;
	}

	public boolean isApvRefered()
	{
		return false;
	}

	public boolean isConvenioRefered()
	{
		return true;
	}

	public boolean isMapeoRefered()
	{
		return false;
	}

	public boolean isTipoDetRefered()
	{
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEntPagadora() {
		return idEntPagadora;
	}

	public void setIdEntPagadora(int idEntPagadora) {
		this.idEntPagadora = idEntPagadora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
