package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CotizacionPendienteVO extends AuditableVO
{
	private static final long serialVersionUID = 2447942378488325851L;
	protected int rutEmpresa;
	protected int idConvenio;
	protected int idCotizPendiente;
	
//	clillo 3-12-14 por Reliquidación
	protected int periodo;
	
	protected String idCotizante;
	protected String detalle;
	protected List causas;
	protected int idEstadoEdicion;

	public CotizacionPendienteVO()
	{
		super();
	}

	public CotizacionPendienteVO(int rutEmpresa, int idConvenio, int idCotizPendiente)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
	}
	
//	clillo 3-12-14 por Reliquidación
	public CotizacionPendienteVO(int rutEmpresa, int idConvenio, int idCotizPendiente, int periodo)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
		this.periodo = periodo;
	}
	
	public CotizacionPendienteVO(int rutEmpresa, int idConvenio, int idCotizPendiente, String detalle)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
		this.detalle = detalle;
	}
	
	public int getNCausas()
	{
		if (this.causas == null)
			this.causas = new ArrayList();
		return this.causas.size();
	}
	
	public void addCausa(CausaVO c)
	{
		if (this.causas == null)
			this.causas = new ArrayList();
		this.causas.add(c);
	}
	
	public List getCausas()
	{
		return this.causas;
	}

	public void setCausas(List causas)
	{
		this.causas = causas;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public int getIdCotizPendiente()
	{
		return this.idCotizPendiente;
	}

	public void setIdCotizPendiente(int idCotizPendiente)
	{
		this.idCotizPendiente = idCotizPendiente;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public String getDetalle()
	{
		return this.detalle;
	}

	public void setDetalle(String detalle)
	{
		this.detalle = detalle;
	}

	public String getIdCotizante()
	{
		return this.idCotizante;
	}

	public void setIdCotizante(String idCotizante)
	{
		this.idCotizante = idCotizante;
	}
	public int getIdEstadoEdicion()
	{
		return this.idEstadoEdicion;
	}

	public void setIdEstadoEdicion(int idEstadoEdicion)
	{
		this.idEstadoEdicion = idEstadoEdicion;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idCotizante));
		parametros.put("4", String.valueOf(this.detalle));
		return parametros;
	}
	/**
	 * @return el periodo
	 */
	public int getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
}
