package cl.araucana.cp.distribuidor.hibernate.beans;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class BancoVO extends AuditableVO
{
	private static final long serialVersionUID = 1L;
	
	private int idBanco;
	private String rutBanco;
	private String nombre;
	private String codSpl;
	private int estado;
	

	public String getCodSpl() {
		return this.codSpl;
	}



	public void setCodSpl(String codSpl) {
		this.codSpl = codSpl;
	}



	public int getEstado() {
		return this.estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}



	public int getIdBanco() {
		return this.idBanco;
	}



	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}



	public String getNombre() {
		return this.nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getRutBanco() {
		return this.rutBanco;
	}



	public void setRutBanco(String rutBanco) {
		this.rutBanco = rutBanco;
	}
	
}
