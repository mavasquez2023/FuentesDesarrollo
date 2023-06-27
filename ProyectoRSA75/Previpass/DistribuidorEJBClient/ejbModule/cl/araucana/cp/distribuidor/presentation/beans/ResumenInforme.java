package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class ResumenInforme implements Serializable
{
	private CotizanteVO cotizante;
	
	private Cotizante cotizantePendiente;
	
	private ArrayList listadoDetalle;
	
	private List avisos;
	
	private List errores;
		
	public CotizanteVO getCotizante()
	{
		return this.cotizante;
	}
	public void setCotizante(CotizanteVO cotizante)
	{
		this.cotizante = cotizante;
	}
	public ArrayList getListadoDetalle()
	{
		return this.listadoDetalle;
	}
	public void setListadoDetalle(ArrayList listadoDetalle)
	{
		this.listadoDetalle = listadoDetalle;
	}
	public Cotizante getCotizantePendiente()
	{
		return this.cotizantePendiente;
	}
	public void setCotizantePendiente(Cotizante cotizantePendiente)
	{
		this.cotizantePendiente = cotizantePendiente;
	}
	/**
	 * @return the avisos
	 */
	public List getAvisos()
	{
		return this.avisos;
	}
	/**
	 * @param avisos the avisos to set
	 */
	public void setAvisos(List avisos)
	{
		this.avisos = avisos;
	}
	/**
	 * @return the errores
	 */
	public List getErrores()
	{
		return this.errores;
	}
	/**
	 * @param errores the errores to set
	 */
	public void setErrores(List errores)
	{
		this.errores = errores;
	}
	
	
	
	
}
