package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.HashMap;

public class EntidadSaludVO implements Serializable
{
	private static final long serialVersionUID = 8112701390457508984L;
	private int id;
	private int idEntPagadora;
	private float tasaSalud;
	private String nombre;

	public EntidadSaludVO() {
		super();
	}
	
	public EntidadSaludVO(Integer id) {
		this.id = id.intValue();
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

	public float getTasaSalud() {
		return this.tasaSalud;
	}

	public void setTasaSalud(float tasaSalud) {
		this.tasaSalud = tasaSalud;
	}

	public HashMap getParametrosOrdenados() {
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.idEntPagadora));
		parametros.put("3", String.valueOf(this.tasaSalud));
		return parametros;
	}

	public String getClave() {
		return "ISAPRE";
	}

	public boolean isCotizanteRefered()
	{
		return true;
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
		return MapeoSaludVO.class;
	}
}