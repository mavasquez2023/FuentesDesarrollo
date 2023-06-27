package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Timestamp;

public class NominaDCVO extends NominaVO
{
	private static final long serialVersionUID = 693340650752633525L;

	public NominaDCVO()
	{
		super();
	}

	public NominaDCVO(int idConvenio, int rutEmpresa, int idEstado, int idEnvio, String nombre, Timestamp recibida, long crc)
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

	public NominaDCVO(int idConvenio, int rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
	}

	public NominaDCVO(int idConvenio, int idEnvio, int rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
		this.idEnvio = idEnvio;
	}

	public NominaDCVO(int idConvenio, String rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = Integer.parseInt(rutEmpresa);
	}

	public NominaDCVO(int idConvenio, int idEnvio, String rutEmpresa)
	{
		super();
		this.idConvenio = idConvenio;
		this.rutEmpresa = Integer.parseInt(rutEmpresa);
		this.idEnvio = idEnvio;
	}

	public Class getTipoCausa()
	{
		return CausaDCVO.class;
	}

	public Class getTipoCausaAviso()
	{
		return CausaAvisoDCVO.class;
	}

	public Class getTipoCotizacion()
	{
		return CotizacionDCVO.class;
	}

	public Class getTipoCotizacionPendiente()
	{
		return CotizacionPendienteDCVO.class;
	}

	public char getTipoProceso()
	{
		return 'D';
	}

	public String getTipoCotizacionReal()
	{
		return "DEPCONVENI";
	}
}
