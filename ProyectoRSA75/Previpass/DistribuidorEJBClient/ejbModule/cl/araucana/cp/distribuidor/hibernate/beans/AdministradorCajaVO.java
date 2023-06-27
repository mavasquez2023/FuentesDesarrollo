package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class AdministradorCajaVO implements Serializable
{
	private static final long serialVersionUID = -1787391026929640285L;
	
	private int idAdminCaja;
	private int idCCAF;
	
	public AdministradorCajaVO() {}
	
	public AdministradorCajaVO(int idAdminCaja, int idCCAF) {
		super();
		this.idAdminCaja = idAdminCaja;
		this.idCCAF = idCCAF;
	}

	public int getIdAdminCaja()
	{
		return this.idAdminCaja;
	}

	public void setIdAdminCaja(int idAdminCaja)
	{
		this.idAdminCaja = idAdminCaja;
	}

	public int getIdCCAF()
	{
		return this.idCCAF;
	}

	public void setIdCCAF(int idCCAF)
	{
		this.idCCAF = idCCAF;
	}
}