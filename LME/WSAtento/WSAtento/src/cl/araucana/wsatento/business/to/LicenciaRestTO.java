// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 19-04-2023 10:54:20
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LicenciaTO.java

package cl.araucana.wsatento.business.to;


public class LicenciaRestTO
{
	private String codSucPago;
	private String fechaPago;
	private Integer compin;
	private Integer tipo;
	private Integer dias;
	private String fechaDesde;
	    
    public LicenciaRestTO()
    {
    }

    public LicenciaRestTO(String codSucPago, String fechaPago, Integer compin, Integer tipo, Integer dias, String fechaDesde)
    {
        this.codSucPago = codSucPago;
        this.fechaPago = fechaPago;
        this.compin = compin;
        this.tipo = tipo;
        this.dias = dias;
        this.fechaDesde = fechaDesde;
    }

    public String getCodSucPago()
    {
        return codSucPago;
    }

    public void setCodSucPago(String codSucPago)
    {
        this.codSucPago = codSucPago;
    }

    public Integer getCompin()
    {
        return compin;
    }

    public void setCompin(Integer compin)
    {
        this.compin = compin;
    }

    public Integer getDias()
    {
        return dias;
    }

    public void setDias(Integer dias)
    {
        this.dias = dias;
    }

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

    
    }
