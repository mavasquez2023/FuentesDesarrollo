package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class EntidadCCAFVO implements Serializable
{
	private static final long serialVersionUID = 7564649989706572217L;
	private int id;
	private int idEntPagadora;
	private String nombre;
	
	private int porcentajeFonasa;
	private int asigFam;
	private int creditos;
	private int leasing;
	private int dental;
	private int segurosVida;

	public EntidadCCAFVO() {
		super();
	}

	public EntidadCCAFVO(int id) {
		this.id = id;
	}

	public EntidadCCAFVO(int id, int idEntPagadora) {
		this.id = id;
		this.idEntPagadora = idEntPagadora;
	}

	public EntidadCCAFVO(int id, int idEntPagadora, int porcentajeFonasa, int asigFam, int creditos, int leasing, int dental, int segurosVida) {
		this.id = id;
		this.idEntPagadora = idEntPagadora;
		this.porcentajeFonasa = porcentajeFonasa;
		this.asigFam = asigFam;
		this.creditos = creditos;
		this.leasing = leasing;
		this.dental = dental;
		this.segurosVida = segurosVida;
	}

	public int getAsigFam()
	{
		return this.asigFam;
	}

	public void setAsigFam(int asigFam)
	{
		this.asigFam = asigFam;
	}

	public int getCreditos()
	{
		return this.creditos;
	}

	public void setCreditos(int creditos)
	{
		this.creditos = creditos;
	}

	public int getDental()
	{
		return this.dental;
	}

	public void setDental(int dental)
	{
		this.dental = dental;
	}

	public int getLeasing()
	{
		return this.leasing;
	}

	public void setLeasing(int leasing)
	{
		this.leasing = leasing;
	}

	public int getPorcentajeFonasa()
	{
		return this.porcentajeFonasa;
	}

	public void setPorcentajeFonasa(int porcentajeFonasa)
	{
		this.porcentajeFonasa = porcentajeFonasa;
	}

	public int getSegurosVida()
	{
		return this.segurosVida;
	}

	public void setSegurosVida(int segurosVida)
	{
		this.segurosVida = segurosVida;
	}

	public String getClave()
	{
		return "CAJA";
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
