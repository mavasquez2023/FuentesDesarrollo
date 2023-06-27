package cl.araucana.adminCpe.hibernate.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SPLDetPagoVO implements Serializable
{
	private static final long serialVersionUID = 7489553459938965386L;
	private int idDetallePago;
	private long idTrx;
	private long folio;
	private long monto;

	public SPLDetPagoVO()
	{
		super();
	}

	public SPLDetPagoVO(long idTrx, long folio, long monto)
	{
		super();
		this.idTrx = idTrx;
		this.folio = folio;
		this.monto = monto;
	}

	public SPLDetPagoVO(int idDetallePago, long idTrx, long folio, long monto)
	{
		super();
		this.idDetallePago = idDetallePago;
		this.idTrx = idTrx;
		this.folio = folio;
		this.monto = monto;
	}

	public long getFolio()
	{
		return this.folio;
	}
	public void setFolio(long folio)
	{
		this.folio = folio;
	}
	public long getIdTrx()
	{
		return this.idTrx;
	}
	public void setIdTrx(long idTrx)
	{
		this.idTrx = idTrx;
	}
	public long getMonto()
	{
		return this.monto;
	}
	public void setMonto(long monto)
	{
		this.monto = monto;
	}

	public int getIdDetallePago()
	{
		return this.idDetallePago;
	}

	public void setIdDetallePago(int idDetallePago)
	{
		this.idDetallePago = idDetallePago;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
