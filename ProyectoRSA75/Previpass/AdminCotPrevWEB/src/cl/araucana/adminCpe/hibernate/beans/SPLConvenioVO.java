package cl.araucana.adminCpe.hibernate.beans;

public class SPLConvenioVO
{
	private long id;
	private String ctaCte;
	private int idMedioPago;

	public SPLConvenioVO()
	{
		super();
	}

	public SPLConvenioVO(long id, String ctaCte, int medioPago)
	{
		super();
		this.id = id;
		this.ctaCte = ctaCte;
		this.idMedioPago = medioPago;
	}

	public String getCtaCte()
	{
		return this.ctaCte;
	}
	public void setCtaCte(String ctaCte)
	{
		this.ctaCte = ctaCte;
	}
	public long getId()
	{
		return this.id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public int getIdMedioPago() {
		return this.idMedioPago;
	}

	public void setIdMedioPago(int idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
}
