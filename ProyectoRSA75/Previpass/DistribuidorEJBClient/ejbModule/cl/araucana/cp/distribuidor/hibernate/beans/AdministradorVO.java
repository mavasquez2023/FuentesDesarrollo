package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class AdministradorVO extends AuditableVO
{
	private static final long serialVersionUID = -1787391026929640285L;
	
	private int idAdmin;
	private PersonaVO administrador;
	private int habilitado;
	//GMALLEA Se agrega los atributos empresa y independiente para identificar que tipo de administrador es.
	private int empresa;
	private int independiente;
	
	public AdministradorVO() {
		super();
	}

	public AdministradorVO(int idAdmin) {
		super();
		this.idAdmin = idAdmin;
	}

	public PersonaVO getAdministrador()
	{
		return this.administrador;
	}
	
	public void setAdministrador(PersonaVO administrador)
	{
		this.administrador = administrador;
	}
	
	public int getHabilitado()
	{
		return this.habilitado;
	}
	
	public void setHabilitado(int habilitado)
	{
		this.habilitado = habilitado;
	}
	
	public int getIdAdmin()
	{
		return this.idAdmin;
	}

	public void setIdAdmin(int idAdmin)
	{
		this.idAdmin = idAdmin;
	}

	public String toString() 
	{
		return "AdministradorVO[idAdmin: " + this.idAdmin + ", ]";
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idAdmin));
		parametros.put("2", String.valueOf(this.habilitado));
		return parametros;
	}

	public AdministradorVO(int idAdmin, PersonaVO administrador, int habilitado) {
		super();
		this.idAdmin = idAdmin;
		this.administrador = administrador;
		this.habilitado = habilitado;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public int getIndependiente() {
		return independiente;
	}

	public void setIndependiente(int independiente) {
		this.independiente = independiente;
	}
	
	
}
