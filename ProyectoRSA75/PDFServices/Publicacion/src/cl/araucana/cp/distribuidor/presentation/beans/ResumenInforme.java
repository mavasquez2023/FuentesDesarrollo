package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.ArrayList;

import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

public class ResumenInforme implements Serializable
{
	private CotizanteVO cotizante;
	
	private ArrayList listadoDetalle;	
	
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
	
	
	
}
