package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class MapeoConceptoVO extends AuditableVO
{
	private static Logger logger = Logger.getLogger(MapeoConceptoVO.class);
	private static final long serialVersionUID = 7662815869068534928L;
	private char tipoProceso;
	private int idConcepto;
	private int idMapa;
	
	private int largo;
	private int posicion;

	private ConceptoVO concepto;

	public MapeoConceptoVO()
	{
		super();
	}

	public MapeoConceptoVO(int largo, int posicion)
	{
		super();
		this.largo = largo;
		this.posicion = posicion;
	}


	public int getIdConcepto()
	{
		return this.idConcepto;
	}


	public void setIdConcepto(int idConcepto)
	{
		this.idConcepto = idConcepto;
	}


	public int getIdMapa()
	{
		return this.idMapa;
	}


	public void setIdMapa(int idMapa)
	{
		this.idMapa = idMapa;
	}


	public int getLargo()
	{
		return this.largo;
	}


	public void setLargo(int largo)
	{
		this.largo = largo;
	}


	public int getPosicion()
	{
		return this.posicion;
	}


	public void setPosicion(int posicion)
	{
		this.posicion = posicion;
	}


	public char getTipoProceso()
	{
		return this.tipoProceso;
	}


	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}


	public String toString()
	{
		logger.debug("idConcepto:" + this.idConcepto + "::");
		return "MapeoConceptoVO[idMapa: " + this.idMapa
			+ ", idConcepto: " + this.idConcepto
			+ ", descripcion: SIN DESCRIPCION"
			+ "\", posicion: " + this.posicion
			+ ", largo: " + this.largo
			+ "]";
	}

	public ConceptoVO getConcepto()
	{
		return this.concepto;
	}

	public void setConcepto(ConceptoVO concepto)
	{
		this.concepto = concepto;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", "" + this.idMapa);
		parametros.put("2", "" + this.tipoProceso);
		parametros.put("3", "" + this.idConcepto);
		parametros.put("3", "" + this.posicion);
		parametros.put("3", "" + this.largo);
		return parametros;
	}
}
