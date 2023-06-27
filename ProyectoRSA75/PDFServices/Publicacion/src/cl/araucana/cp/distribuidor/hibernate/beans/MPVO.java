package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;

public class MPVO extends AuditableVO
{
	private static final long serialVersionUID = 2667216123632986704L;
	private int rutEmpresa;
	private int idConvenio;
	private int idCotizante;
	private int idMovimiento;
	
	CotizacionREVO cotizacion;

	private String idTipoMov;
	private int idTipoMovReal;
	private Date inicio;
	private Date termino;

	public MPVO(int idTipoMovReal)
	{
		super();
		this.idTipoMovReal = idTipoMovReal;
	}

	public MPVO(MovtoPersonalVO mp)
	{
		super();
		this.rutEmpresa = mp.getRutEmpresa();
		this.idConvenio = mp.getIdConvenio();
		this.idCotizante = mp.getIdCotizante();
		this.idMovimiento = mp.getIdMovimiento();
	}

	public MPVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento, int idTipoMovReal)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idTipoMovReal = idTipoMovReal;
		this.idMovimiento = idMovimiento;
	}

	public MPVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idMovimiento = idMovimiento;
		this.cotizacion = new CotizacionREVO(rutEmpresa, idConvenio, idCotizante);
	}

	public MPVO()
	{
		super();
	}

	public MPVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento, String idTipoMov, Date inicio, Date termino)
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

	public CotizacionREVO getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(CotizacionREVO cotizacion) {
		this.cotizacion = cotizacion;
	}

	public int getIdConvenio() {
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}

	public int getIdCotizante() {
		return this.idCotizante;
	}

	public void setIdCotizante(int idCotizante) {
		this.idCotizante = idCotizante;
	}

	public int getIdMovimiento() {
		return this.idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getIdTipoMov() {
		return this.idTipoMov;
	}

	public void setIdTipoMov(String idTipoMov) {
		this.idTipoMov = idTipoMov;
	}

	public int getIdTipoMovReal() {
		return this.idTipoMovReal;
	}

	public void setIdTipoMovReal(int idTipoMovReal) {
		this.idTipoMovReal = idTipoMovReal;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public int getRutEmpresa() {
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public Date getTermino() {
		return this.termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}
}
