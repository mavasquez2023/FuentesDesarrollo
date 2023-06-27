package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;

public class NominaRAVO extends NominaVO
{
	private static final long serialVersionUID = -5419889115946605133L;
	private Date inicio;
	private Date termino;

	public NominaRAVO()
	{
		super();
	}

	public NominaRAVO(int idConvenio, int rutEmpresa, int idEstado, int idEnvio, String nombre, Timestamp recibida, long crc)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
		this.idEstado = idEstado;
		this.idEnvio = idEnvio;
		this.nombre = nombre;
		this.recibida = recibida;
		this.crc = crc;
	}

	public NominaRAVO(int idConvenio, int rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
	}

	public NominaRAVO(int idConvenio, int idEnvio, int rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
		this.idEnvio = idEnvio;
	}

	public NominaRAVO(int idConvenio, String rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = Integer.parseInt(rutEmpresa);
	}

	public NominaRAVO(int idConvenio, int idEnvio, String rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = Integer.parseInt(rutEmpresa);
		this.idEnvio = idEnvio;
	}

	public Date getInicio()
	{
		return this.inicio;
	}

	public void setInicio(Date inicio)
	{
		this.inicio = inicio;
	}

	public Date getTermino()
	{
		return this.termino;
	}

	public void setTermino(Date termino)
	{
		this.termino = termino;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", "" + this.rutEmpresa);
		parametros.put("2", "" + this.idConvenio);
		parametros.put("3", "" + this.idEstado);
		parametros.put("4", "" + this.idCodigoBarras);
		parametros.put("5", "" + this.idEnvio);
		parametros.put("6", String.valueOf(this.nombre));
		parametros.put("7", String.valueOf(this.inicio));
		parametros.put("8", String.valueOf(this.termino));
		parametros.put("9", String.valueOf(this.recibida));
		parametros.put("10", String.valueOf(this.aceptada));
		parametros.put("11", String.valueOf(this.crc));
		parametros.put("12", "" + this.numReenvios);
		parametros.put("13", "" + this.numCotizaciones);
		parametros.put("14", "" + this.numCotizOK);
		parametros.put("15", "" + this.numCotizCorregidas);
		return parametros;
	}

	public Class getTipoCausa()
	{
		return CausaRAVO.class;
	}

	public Class getTipoCausaAviso()
	{
		return CausaAvisoRAVO.class;
	}

	public Class getTipoCotizacion()
	{
		return CotizacionRAVO.class;
	}

	public Class getTipoCotizacionPendiente()
	{
		return CotizacionPendienteRAVO.class;
	}

	public char getTipoProceso()
	{
		return 'A';
	}

	public String getTipoCotizacionReal()
	{
		return "RELIQUIDAC";
	}
}
