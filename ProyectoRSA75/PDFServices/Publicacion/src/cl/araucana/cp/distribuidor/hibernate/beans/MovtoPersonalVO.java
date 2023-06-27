package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MovtoPersonalVO extends MPVO
{
	private static final long serialVersionUID = -1347398298295627676L;
	private int rutEmpresa;
	private int idConvenio;
	private int idCotizante;
	private int idMovimiento;

	private String idTipoMov;
	private int idTipoMovReal;
	private Date inicio;
	private Date termino;

	public MovtoPersonalVO(int idTipoMovReal)
	{
		super();
		this.idTipoMovReal = idTipoMovReal;
	}

	public MovtoPersonalVO(MovtoPersonalVO mp)
	{
		super();
		this.rutEmpresa = mp.getRutEmpresa();
		this.idConvenio = mp.getIdConvenio();
		this.idCotizante = mp.getIdCotizante();
		this.idMovimiento = mp.getIdMovimiento();
	}

	public MovtoPersonalVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento, int idTipoMovReal)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idTipoMovReal = idTipoMovReal;
		this.idMovimiento = idMovimiento;
	}

	public MovtoPersonalVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idMovimiento = idMovimiento;
		this.cotizacion = new CotizacionREVO(rutEmpresa, idConvenio, idCotizante);
	}

	public MovtoPersonalVO()
	{
		super();
	}

	public MovtoPersonalVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento, String idTipoMov, Date inicio, Date termino)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idMovimiento = idMovimiento;
		this.idTipoMov = idTipoMov;
		this.inicio = inicio;
		this.termino = termino;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}
	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}
	public int getIdCotizante()
	{
		return this.idCotizante;
	}
	public void setIdCotizante(int idCotizante)
	{
		this.idCotizante = idCotizante;
	}
	public int getIdMovimiento()
	{
		return this.idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento)
	{
		this.idMovimiento = idMovimiento;
	}
	public String getIdTipoMov()
	{
		return this.idTipoMov;
	}
	public void setIdTipoMov(String idTipoMov)
	{
		this.idTipoMov = idTipoMov;
	}
	public Date getInicio()
	{
		return this.inicio;
	}
	public void setInicio(Date inicio)
	{
		this.inicio = inicio;
	}
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	public Date getTermino()
	{
		return this.termino;
	}
	public void setTermino(Date termino)
	{
		this.termino = termino;
	}

	public CotizacionREVO getCotizacion()
	{
		return this.cotizacion;
	}

	public void setCotizacion(CotizacionREVO cotizacion)
	{
		this.cotizacion = cotizacion;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getIdTipoMovReal()
	{
		return this.idTipoMovReal;
	}

	public void setIdTipoMovReal(int idTipoMovReal)
	{
		this.idTipoMovReal = idTipoMovReal;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idCotizante));
		parametros.put("4", String.valueOf(this.idMovimiento));
		parametros.put("5", String.valueOf(this.idTipoMovReal));
		parametros.put("6", String.valueOf(this.inicio));
		parametros.put("7", String.valueOf(this.termino));
		return parametros;
	}
}
