package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Timestamp;

public class NominaREVO extends NominaVO
{
	private static final long serialVersionUID = 7586366445168525677L;

	public NominaREVO()
	{
		super();
	}

	public NominaREVO(int idConvenio, int rutEmpresa, int idEstado, int idEnvio, String nombre, Timestamp recibida, long crc)
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

	public NominaREVO(int idConvenio, int rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
	}

	public NominaREVO(int idConvenio, int idEnvio, int rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
		this.idEnvio = idEnvio;
	}

	public NominaREVO(int idConvenio, String rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = Integer.parseInt(rutEmpresa);
	}

	public NominaREVO(int idConvenio, int idEnvio, String rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = Integer.parseInt(rutEmpresa);
		this.idEnvio = idEnvio;
	}

	public Class getTipoCausa()
	{
		return CausaREVO.class;
	}

	public Class getTipoCausaAviso()
	{
		return CausaAvisoREVO.class;
	}

	public Class getTipoCotizacion()
	{
		return CotizacionREVO.class;
	}

	public Class getTipoCotizacionPendiente()
	{
		return CotizacionPendienteREVO.class;
	}

	public char getTipoProceso()
	{
		return 'R';
	}

	public String getTipoCotizacionReal()
	{
		return "REMUNERAC";
	}
}
