package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.util.HashMap;

public class MvtoPersoAFVO extends MPVO
{	
	private static final long serialVersionUID = 2974341361485714406L;

	private int rutEmpresa;
	private int idConvenio;
	private int idCotizante;
	private int idMovimiento;
	private String idTipoMvto;
	private int idTipoMovReal;
	private String desc;
	private Date inicio;
	private Date termino;

	public MvtoPersoAFVO(int idTipoMovReal)
	{
		super();
		this.idTipoMovReal = idTipoMovReal;
	}

	public MvtoPersoAFVO(MvtoPersoAFVO mp)
	{
		super();
		this.rutEmpresa = mp.getRutEmpresa();
		this.idConvenio = mp.getIdConvenio();
		this.idCotizante = mp.getIdCotizante();
		this.idMovimiento = mp.getIdMovimiento();
	}

	public MvtoPersoAFVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento, int idTipoMovReal)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idTipoMovReal = idTipoMovReal;
		this.idMovimiento = idMovimiento;
	}

	public MvtoPersoAFVO(int rutEmpresa, int idConvenio, int idCotizante, int idMovimiento)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idMovimiento = idMovimiento;
		this.cotizacion = new CotizacionREVO(rutEmpresa, idConvenio, idCotizante);
	}

	public MvtoPersoAFVO()
	{
		super();
	}
	
	public MvtoPersoAFVO(int rutEmpresa,int idConvenio,int idCotizante,int idMovimiento,String idTipoMvto, Date inicio, Date termino)
	{
		super();
		this.rutEmpresa= rutEmpresa;
		this.idConvenio= idConvenio;
		this.idCotizante= idCotizante;
		this.idMovimiento= idMovimiento;
		this.idTipoMvto= idTipoMvto;
		this.inicio = inicio;
		this.termino =  termino;		
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public int getRutEmpresa() {
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public int getIdMovimiento() {
		return this.idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getIdTipoMvto() {
		return this.idTipoMvto;
	}

	public void setIdTipoMvto(String idTipoMvto) {
		this.idTipoMvto = idTipoMvto;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return this.termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idCotizante));
		parametros.put("4", String.valueOf(this.idMovimiento));
		parametros.put("5", String.valueOf(this.idTipoMvto));
		parametros.put("6", String.valueOf(this.inicio));
		parametros.put("7", String.valueOf(this.termino));
		return parametros;
	}

	public int getIdTipoMovReal() {
		return this.idTipoMovReal;
	}

	public void setIdTipoMovReal(int idTipoMovReal) {
		this.idTipoMovReal = idTipoMovReal;
	}
	
	public CotizacionREVO getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(CotizacionREVO cotizacion) {
		this.cotizacion = cotizacion;
	}
}

