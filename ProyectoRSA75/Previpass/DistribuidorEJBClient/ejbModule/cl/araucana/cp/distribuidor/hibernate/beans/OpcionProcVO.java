package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class OpcionProcVO extends AuditableVO
{
	private static final long serialVersionUID = -5633812706702731645L;

	private int idOpcion;
	private boolean inpSucursal;
	private boolean mutualSucursal;
	private boolean ccafSucursal;
	private boolean calInp;
	private boolean calMutual;
	private boolean calcCcaf;
	private boolean calcTotSalud;
	private boolean calcTotPrevision;
	private boolean imprimirPlantillas;
	private int numBloqueos;
	private boolean calFonasa;
	 
	
	public OpcionProcVO() {}
	

	public int getIdOpcion()
	{
		return this.idOpcion;
	}
	
	public void setIdOpcion(int idOpcion)
	{
		this.idOpcion = idOpcion;
	}
	
	public boolean getInpSucursal()
	{
		return this.inpSucursal;
	}
	
	public void setInpSucursal(boolean inpSucursal)
	{
		this.inpSucursal = inpSucursal;
	}
	
	public boolean getMutualSucursal()
	{
		return this.mutualSucursal;
	}
	
	public void setMutualSucursal(boolean mutualSucursal)
	{
		this.mutualSucursal = mutualSucursal;
	}
	public boolean getCcafSucursal()
	{
		return this.ccafSucursal;
	}
	
	public void setCcafSucursal(boolean ccafSucursal)
	{
		this.ccafSucursal = ccafSucursal;
	}
	
	
	public boolean getCalInp()
	{
		return this.calInp;
	}
	
	public void setCalInp(boolean calcInp)
	{
		this.calInp = calcInp;
	}
	
	public boolean getCalMutual()
	{
		return this.calMutual;
	}
	
	public void setCalMutual(boolean calMutual)
	{
		this.calMutual = calMutual;
	}
	
	public boolean getCalcCcaf()
	{
		return this.calcCcaf;
	}
	
	public void setCalcCcaf(boolean calcCcaf)
	{
		this.calcCcaf = calcCcaf;
	}
	
	public boolean getCalcTotSalud()
	{
		return this.calcTotSalud;
	}
	
	public void setCalcTotSalud(boolean calcTotSalud)
	{
		this.calcTotSalud = calcTotSalud;
	}
	

	public boolean getCalcTotPrevision()
	{
		return this.calcTotPrevision;
	}
	
	public void setCalcTotPrevision(boolean calcTotPrevision)
	{
		this.calcTotPrevision = calcTotPrevision;
	}
	public boolean getImprimirPlantillas()
	{
		return this.imprimirPlantillas;
	}
	
	public void setImprimirPlantillas(boolean imprimirPlantillas)
	{
		this.imprimirPlantillas = imprimirPlantillas;
	}
	
	
	public int getNumBloqueos()
	{
		return this.numBloqueos;
	}
	
	public void setNumBloqueos(int numBloqueos)
	{
		this.numBloqueos = numBloqueos;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idOpcion));
		parametros.put("2", String.valueOf(this.inpSucursal));
		parametros.put("3", String.valueOf(this.mutualSucursal));
		parametros.put("4", String.valueOf(this.ccafSucursal));
		parametros.put("5", String.valueOf(this.calInp));
		parametros.put("6", String.valueOf(this.calMutual));
		parametros.put("7", String.valueOf(this.calcCcaf));
		parametros.put("8", String.valueOf(this.calcTotSalud));
		parametros.put("9", String.valueOf(this.calcTotPrevision));
		parametros.put("10", String.valueOf(this.imprimirPlantillas));
		parametros.put("11", String.valueOf(this.numBloqueos));
		parametros.put("12", String.valueOf(this.calFonasa));
		return parametros;
	}


	public OpcionProcVO(int idOpcion) {
		super();
		this.idOpcion = idOpcion;
	}


	public boolean isCalFonasa()
	{
		return this.calFonasa;
	}


	public void setCalFonasa(boolean calFonasa)
	{
		this.calFonasa = calFonasa;
	}
}
