package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SPLPagoVO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long idTrx;
	private int pagado;
	private int idConvenio;
	private String ctaCte;
	private int idMedioPago;
	private int codBanco;
	private Date fechaContable;
	private long monto;
	private long pagador;
	Set detallePago;

	/*
	 * Por medio de la TRX enviada al sistema de origen por SPL, se debera
	 * consultar la tabla PAGO, con el contenido del campo ID_Convenio, se debe
	 * accesar la tabla CONVENIO, en donde encontrara la cuenta corriente
	 * asociada (campo CTACTE), para obtener el Banco asociado al convenio
	 * segun la codificacion del sistema de tesoreria, se debera accesar a
	 * la tabla MEDIOPAGO con el contenido del campo id_mediopago de la tabla
	 * CONVENIO.
	 * 
	 * En la tabla MEDIOPAGO, el contenido del campo CODBANCO, correspondera al
	 * valor del banco para el sistema de tesoreria.
	 */

	public SPLPagoVO(long idTrx, int pagado, int idConvenio, String ctaCte, int idMedioPago, int codBanco, Date fechaContable, long monto, Set detallePago)
	{
		super();
		this.idTrx = idTrx;
		this.pagado = pagado;
		this.idConvenio = idConvenio;
		this.ctaCte = ctaCte;
		this.idMedioPago = idMedioPago;
		this.codBanco = codBanco;
		this.fechaContable = fechaContable;
		this.monto = monto;
		this.detallePago = detallePago;
	}

	public Date getFechaContable()
	{
		return this.fechaContable;
	}

	public void setFechaContable(Date fechaContable)
	{
		this.fechaContable = fechaContable;
	}

	public SPLPagoVO()
	{
		super();
	}

	public SPLPagoVO(long idTrx, int pagado, int idConvenio, String ctaCte, int idMedioPago, int codBanco)
	{
		super();
		this.idTrx = idTrx;
		this.pagado = pagado;
		this.idConvenio = idConvenio;
		this.ctaCte = ctaCte;
		this.idMedioPago = idMedioPago;
		this.codBanco = codBanco;
	}

	public SPLPagoVO(long idTrx, int pagado, int idConvenio, String ctaCte, int idMedioPago)
	{
		super();
		this.idTrx = idTrx;
		this.pagado = pagado;
		this.idConvenio = idConvenio;
		this.ctaCte = ctaCte;
		this.idMedioPago = idMedioPago;
	}

	public String getCtaCte()
	{
		return this.ctaCte;
	}

	public void setCtaCte(String ctaCte)
	{
		this.ctaCte = ctaCte;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public int getIdMedioPago()
	{
		return this.idMedioPago;
	}

	public void setIdMedioPago(int idMedioPago)
	{
		this.idMedioPago = idMedioPago;
	}

	public long getIdTrx()
	{
		return this.idTrx;
	}

	public void setIdTrx(long idTrx)
	{
		this.idTrx = idTrx;
	}

	public int getPagado()
	{
		return this.pagado;
	}

	public void setPagado(int pagado)
	{
		this.pagado = pagado;
	}

	public int getCodBanco()
	{
		return this.codBanco;
	}

	public void setCodBanco(int codBanco)
	{
		this.codBanco = codBanco;
	}

	public long getMonto()
	{
		return this.monto;
	}

	public void setMonto(long monto)
	{
		this.monto = monto;
	}

	public Set getDetallePago()
	{
		return this.detallePago;
	}

	public void setDetallePago(Set detallePago)
	{
		this.detallePago = detallePago;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public long getPagador() {
		return pagador;
	}

	public void setPagador(long pagador) {
		this.pagador = pagador;
	}
	
}
