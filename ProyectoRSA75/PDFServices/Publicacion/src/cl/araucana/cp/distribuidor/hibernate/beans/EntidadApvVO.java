package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class EntidadApvVO implements Serializable
{
	private static final long serialVersionUID = 3385547316328968061L;
	private int id;
	private int idEntPagadora;
	private String nombre;

	public EntidadApvVO()
	{
		super();
	}

	public EntidadApvVO(Integer id) {
		this.id = id.intValue();
	}

	public EntidadApvVO(Integer id, Integer idEntPagadora)
	{
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
	}

	public String getClave()
	{
		return "APV";
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
		return true;
	}

	public boolean isConvenioRefered()
	{
		return false;
	}

	public boolean isMapeoRefered()
	{
		return true;
	}

	public boolean isTipoDetRefered()
	{
		return true;
	}

	public Class getMapeoAsociado()
	{
		return MapeoAPVVO.class;
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
