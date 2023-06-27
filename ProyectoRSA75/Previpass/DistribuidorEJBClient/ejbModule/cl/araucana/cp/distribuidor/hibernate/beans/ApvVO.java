package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

import cl.araucana.cp.distribuidor.base.Constants;

public class ApvVO extends AuditableVO
{
	private static final long serialVersionUID = -4105526122706781710L;

	private int id;

	private int rutEmpresa;
	private int idConvenio;
	private int idCotizante;
	private int idEntidadApv;
	private String idEntidadApvEmp;
	private int ahorro;
	private String montoFormat;
	private char regimen='B';

	public String getMontoFormat()
	{
		return this.montoFormat;
	}

	public void setMontoFormat(String montoFormat)
	{
		this.montoFormat = montoFormat;
	}

	public ApvVO(int id)
	{
		super();
		this.id = id;
	}

	public ApvVO()
	{
		super();
	}
	
	public ApvVO(ApvVO apv)
	{
		super();
		this.id = apv.getId();
		this.rutEmpresa = apv.getRutEmpresa();
		this.idConvenio = apv.getIdConvenio();
		this.idCotizante = apv.getIdCotizante();
		this.idEntidadApv = apv.getIdEntidadApv();
		this.ahorro = apv.getAhorro();
		this.montoFormat = apv.getMontoFormat();
		this.regimen = apv.getRegimen();
	}	

	public ApvVO(int rutEmpresa, int idConvenio, int idCotizante, int idEntidadApv)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idEntidadApv = idEntidadApv;
	}

	public ApvVO(int rutEmpresa, int idConvenio, int idCotizante, int idEntidadApv, int ahorro,char regimen)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idEntidadApv = idEntidadApv;
		this.ahorro = ahorro;
		this.regimen = regimen;

	}

	public ApvVO(int id, int rutEmpresa, int idConvenio, int idCotizante, int idEntidadApv, int ahorro)
	{
		super();
		this.id = id;
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idEntidadApv = idEntidadApv;
		this.ahorro = ahorro;
	}

	public int getAhorro()
	{
		return this.ahorro;
	}
	public void setAhorro(int ahorro)
	{
		this.ahorro = ahorro;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
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
	public int getIdEntidadApv()
	{
		return this.idEntidadApv;
	}
	public void setIdEntidadApv(int idEntidadApv)
	{
		this.idEntidadApv = idEntidadApv;
	}
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.rutEmpresa));
		parametros.put("3", String.valueOf(this.idConvenio));
		parametros.put("4", String.valueOf(this.idCotizante));
		parametros.put("5", String.valueOf(this.idEntidadApv));
		parametros.put("6", String.valueOf(this.ahorro));
		
		return parametros;		
	}

	public char getRegimen() {
		return regimen;
	}

	public void setRegimen(char regimen) {
		this.regimen = regimen;
	}

	/**
	 * @return el idEntidadApvEmp
	 */
	public String getIdEntidadApvEmp() {
		return idEntidadApvEmp;
	}

	/**
	 * @param idEntidadApvEmp el idEntidadApvEmp a establecer
	 */
	public void setIdEntidadApvEmp(String idEntidadApvEmp) {
		this.idEntidadApvEmp = idEntidadApvEmp;
	}
}
