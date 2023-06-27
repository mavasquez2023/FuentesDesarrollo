package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;


public class RepresentanteLegalVO extends AuditableVO
{
	private static final long serialVersionUID = 5002746565350466313L;

	private int idRepresentanteLegal;
	private PersonaVO representanteLegal;

	public RepresentanteLegalVO() {
		super();
	}

	public RepresentanteLegalVO(int idRepresentanteLegal) 
	{
		super();
		this.idRepresentanteLegal = idRepresentanteLegal;
	}

	public int getIdRepresentanteLegal()
	{
		return this.idRepresentanteLegal;
	}

	public RepresentanteLegalVO(int idRepresentanteLegal, PersonaVO representanteLegal) 
	{
		super();
		this.idRepresentanteLegal = idRepresentanteLegal;
		this.representanteLegal = representanteLegal;
	}

	public void setIdRepresentanteLegal(int idRepresentanteLegal)
	{
		this.idRepresentanteLegal = idRepresentanteLegal;
	}
	
	public PersonaVO getRepresentanteLegal()
	{
		return this.representanteLegal;
	}
	
	public void setRepresentanteLegal(PersonaVO representanteLegal)
	{
		this.representanteLegal = representanteLegal;
	}
	
	public String toString() {
		return "RepresentanteLegalVO[idRepresentanteLegal: " + this.idRepresentanteLegal
			+ ", nombres: \"" + this.representanteLegal.getNombres() + "\"]";
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idRepresentanteLegal));
		return parametros;
	}
	
}
