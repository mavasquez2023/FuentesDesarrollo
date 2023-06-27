package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;


public class FoliacionVO extends AuditableVO
{
	private static final long serialVersionUID = 833344881870425601L;
	protected int idEntPagadora;
	protected int idFoliacion;
	protected int foliosEnUso;
	protected int folioInicial;
	protected int folioFinal;
	protected int folioActual;

	public FoliacionVO(int idEntPagadora, int idFoliacion) 
	{
		super();
		this.idEntPagadora = idEntPagadora;
		this.idFoliacion = idFoliacion;
	}

	public FoliacionVO(Integer idFoliacion, Integer idEntPagadora, Integer foliosEnUso, 
						Integer folioInicial, Integer folioFinal, Integer folioActual)
	{
		super();
		this.idFoliacion = idFoliacion.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
		this.foliosEnUso = foliosEnUso.intValue();
		this.folioInicial = folioInicial.intValue();
		this.folioFinal = folioFinal.intValue();
		this.folioActual = folioActual.intValue();
	}

	public FoliacionVO(int idFoliacion, int idEntPagadora, int foliosEnUso, 
							int folioInicial, int folioFinal, int folioActual)
	{
		super();
		this.idFoliacion = idFoliacion;
		this.idEntPagadora = idEntPagadora;
		this.foliosEnUso = foliosEnUso;
		this.folioInicial = folioInicial;
		this.folioFinal = folioFinal;
		this.folioActual = folioActual;
	}

	public FoliacionVO()
	{
		super();
	}

	public int getIdEntPagadora()
	{
		return this.idEntPagadora;
	}
	public void setIdEntPagadora(int idEntPagadora)
	{
		this.idEntPagadora = idEntPagadora;
	}
	
	public int getIdFoliacion()
	{
		return this.idFoliacion;
	}
	public void setIdFoliacion(int idFoliacion)
	{
		this.idFoliacion = idFoliacion;
	}
	
	public int getFoliosEnUso()
	{
		return this.foliosEnUso;
	}
	public void setFoliosEnUso(int foliosEnUso)
	{
		this.foliosEnUso = foliosEnUso;
	}
	
	public int getFolioInicial()
	{
		return this.folioInicial;
	}
	public void setFolioInicial(int folioInicial)
	{
		this.folioInicial = folioInicial;
	}
	
	public int getFolioFinal()
	{
		return this.folioFinal;
	}
	public void setFolioFinal(int folioFinal)
	{
		this.folioFinal = folioFinal;
	}
	
	public int getFolioActual()
	{
		return this.folioActual;
	}
	public void setFolioActual(int folioActual)
	{
		this.folioActual = folioActual;
	}
	
	public String toString() 
	{
		return "FoliacionVO \n [idFoliacion: " + this.idFoliacion + "]\n"
		 + " [idEntPagadora: " + this.idEntPagadora + "]\n"
		 + " [foliosEnUso: " + this.foliosEnUso + "]\n"
		 + " [folioInicial: " + this.folioInicial + "]\n"
		 + " [folioFinal: " + this.folioFinal + "]\n"
		 + " [folioActual: " + this.folioActual + "]\n";
	}

	

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idFoliacion));
		parametros.put("2", String.valueOf(this.idEntPagadora));
		parametros.put("3", String.valueOf(this.foliosEnUso));
		parametros.put("4", String.valueOf(this.folioInicial));
		parametros.put("5", String.valueOf(this.folioFinal));
		parametros.put("6", String.valueOf(this.folioActual));
		return parametros;
	}
}
