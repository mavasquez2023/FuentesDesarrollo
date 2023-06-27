package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ConvenioVO extends AuditableVO implements Comparable
{
	private static final long serialVersionUID = 7332905484536757971L;

	private int idEmpresa;
	private int idConvenio;
	private int idGrupoConvenio;
	private int idMapaNomRem;
	private int idMapaNomGra;
	private int idMapaNomDep;
	private int idMapaNomRel;
	private int idMapaCod;
	private int idOpcion;
	private int idCcaf;
	private int idMutual;
	private String descripcion;
	private int habilitado;
	private Date creado;
	private Date ultimoUso;
	private int mutualNumeroAdherente;
	private float mutualTasaAdicional;
	private int mutualCalculoIndividual;// 0: no calcular individual
										// 1: si calcular individual
	private int numNominas;
	private int numNominasOk;
	private int numNominasCorregidas;
	private int numCotizaciones;
	private int numCotizacionesOk;
	private int numCotizacionesCorregidas;
	private int numBloqueos;
	private int idActividad;

	private int calculoMovPersonal; // para saber que hacer con los montos de movimiento de personal:
									// 0: se mantiene el primer registro que encuentra
									// 1: suman los montos encontrados para un mismo cotizante.
	Set encargados;
	List comprobantes;
	
	private Boolean editable;

	public ConvenioVO(ConvenioVO conv, GrupoConvenioVO grupo) //se usa para mezclar valores de grupo que sobreescriben los de convenio
	{
		super();
		this.idEmpresa = conv.getIdEmpresa();
		this.idConvenio = conv.getIdConvenio();
		this.idGrupoConvenio = conv.getIdGrupoConvenio();
		this.idCcaf = conv.getIdCcaf();
		this.idMutual = conv.getIdMutual();
		this.descripcion = conv.getDescripcion();
		this.habilitado = conv.getHabilitado();
		this.creado = conv.getCreado();
		this.ultimoUso = conv.getUltimoUso();
		this.mutualNumeroAdherente = conv.getMutualNumeroAdherente();
		this.mutualTasaAdicional = conv.getMutualTasaAdicional();
		this.mutualCalculoIndividual = conv.getMutualCalculoIndividual();
		this.numNominas = conv.getNumNominas();
		this.numNominasOk = conv.getNumNominasOk();
		this.numNominasCorregidas = conv.getNumNominasCorregidas();
		this.numCotizaciones = conv.getNumCotizaciones();
		this.numCotizacionesOk = conv.getNumCotizacionesOk();
		this.numCotizacionesCorregidas = conv.getNumCotizacionesCorregidas();
		this.numBloqueos = conv.getNumBloqueos();
		this.idActividad = conv.getIdActividad();
		this.calculoMovPersonal = conv.getCalculoMovPersonal();
		

		this.idMapaNomRem = grupo.getIdMapaNomRem();
		this.idMapaNomGra = grupo.getIdMapaNomGra();
		this.idMapaNomDep = grupo.getIdMapaNomDep();
		this.idMapaNomRel = grupo.getIdMapaNomRel();
		this.idMapaCod = grupo.getIdMapaCod();
		this.idOpcion = grupo.getIdOpcion();
	}

	public ConvenioVO()
	{
		super();
	}

	public ConvenioVO(int idEmpresa, int idConvenio)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.idConvenio = idConvenio;
	}

	public ConvenioVO(int idEmpresa, int idConvenio, int idGrupoConvenio, int idMapaNomRem, int idMapaNomGra, int idMapaNomDep, int idMapaNomRel, int idMapaCod, int idOpcion, int idCcaf, int idMutual, String descripcion, int habilitado, Date creado, Date ultimoUso, int mutualNumeroAdherente, float mutualTasaAdicional, int mutualCalculoIndividual, int numNominas, int numNominasOk, int numNominasCorregidas, int numCotizaciones, int numCotizacionesOk, int numCotizacionesCorregidas, int numBloqueos, int calculoMovPersonal)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.idConvenio = idConvenio;
		this.idGrupoConvenio = idGrupoConvenio;
		this.idMapaNomRem = idMapaNomRem;
		this.idMapaNomGra = idMapaNomGra;
		this.idMapaNomDep = idMapaNomDep;
		this.idMapaNomRel = idMapaNomRel;
		this.idMapaCod = idMapaCod;
		this.idOpcion = idOpcion;
		this.idCcaf = idCcaf;
		this.idMutual = idMutual;
		this.descripcion = descripcion;
		this.habilitado = habilitado;
		this.creado = creado;
		this.ultimoUso = ultimoUso;
		this.mutualNumeroAdherente = mutualNumeroAdherente;
		this.mutualTasaAdicional = mutualTasaAdicional;
		this.mutualCalculoIndividual = mutualCalculoIndividual;
		this.numNominas = numNominas;
		this.numNominasOk = numNominasOk;
		this.numNominasCorregidas = numNominasCorregidas;
		this.numCotizaciones = numCotizaciones;
		this.numCotizacionesOk = numCotizacionesOk;
		this.numCotizacionesCorregidas = numCotizacionesCorregidas;
		this.numBloqueos = numBloqueos;
		this.calculoMovPersonal = calculoMovPersonal;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.idEmpresa;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ConvenioVO other = (ConvenioVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idEmpresa != other.idEmpresa)
			return false;
		return true;
	}

	public void sumNumNominasCorr()
	{
		this.numNominasCorregidas++;
	}

	public void sumNumNominas()
	{
		this.numNominas++;
	}

	public void sumNumNominasOK()
	{
		this.numNominasOk++;
	}

	public void restNumCotizaciones()
	{
		this.numCotizaciones--;
	}

	public void restNumNominas()
	{
		this.numNominas--;
	}

	public void restNumCotizOK()
	{
		this.numCotizacionesOk--;
	}

	public void sumNumCotizOK()
	{
		this.numCotizacionesOk++;
	}

	public void sumNumCotizCorr()
	{
		this.numCotizacionesCorregidas++;
	}

	public int getIdMapaNom(char tipoProceso)
	{
		if (tipoProceso == 'R')
			return this.idMapaNomRem;
		else if (tipoProceso == 'G')
			return this.idMapaNomGra;
		else if (tipoProceso == 'A')
			return this.idMapaNomRel;
		else if (tipoProceso == 'D')
			return this.idMapaNomDep;
		return 0;
	}

	public Date getCreado()
	{
		return this.creado;
	}

	public void setCreado(Date creado)
	{
		this.creado = creado;
	}

	public String getDescripcion()
	{
		return this.descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public int getHabilitado()
	{
		return this.habilitado;
	}

	public void setHabilitado(int habilitado)
	{
		this.habilitado = habilitado;
	}

	public int getIdCcaf()
	{
		return this.idCcaf;
	}

	public void setIdCcaf(int idCcaf)
	{
		this.idCcaf = idCcaf;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public int getIdEmpresa()
	{
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa)
	{
		this.idEmpresa = idEmpresa;
	}

	public int getIdGrupoConvenio()
	{
		return this.idGrupoConvenio;
	}

	public void setIdGrupoConvenio(int idGrupoConvenio)
	{
		this.idGrupoConvenio = idGrupoConvenio;
	}

	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}

	public void setIdMapaCod(int idMapaCod)
	{
		this.idMapaCod = idMapaCod;
	}

	public int getIdMapaNomDep()
	{
		return this.idMapaNomDep;
	}

	public void setIdMapaNomDep(int idMapaNomDep)
	{
		this.idMapaNomDep = idMapaNomDep;
	}

	public int getIdMapaNomGra()
	{
		return this.idMapaNomGra;
	}

	public void setIdMapaNomGra(int idMapaNomGra)
	{
		this.idMapaNomGra = idMapaNomGra;
	}

	public int getIdMapaNomRel()
	{
		return this.idMapaNomRel;
	}

	public void setIdMapaNomRel(int idMapaNomRel)
	{
		this.idMapaNomRel = idMapaNomRel;
	}

	public int getIdMapaNomRem()
	{
		return this.idMapaNomRem;
	}

	public void setIdMapaNomRem(int idMapaNomRem)
	{
		this.idMapaNomRem = idMapaNomRem;
	}

	public int getIdOpcion()
	{
		return this.idOpcion;
	}

	public void setIdOpcion(int idOpcion)
	{
		this.idOpcion = idOpcion;
	}

	public int getIdMutual()
	{
		return this.idMutual;
	}

	public void setIdMutual(int idMutual)
	{
		this.idMutual = idMutual;
	}

	public int getMutualCalculoIndividual()
	{
		return this.mutualCalculoIndividual;
	}

	public void setMutualCalculoIndividual(int mutualCalculoIndividual)
	{
		this.mutualCalculoIndividual = mutualCalculoIndividual;
	}

	public int getMutualNumeroAdherente()
	{
		return this.mutualNumeroAdherente;
	}

	public void setMutualNumeroAdherente(int mutualNumeroAdherente)
	{
		this.mutualNumeroAdherente = mutualNumeroAdherente;
	}

	public float getMutualTasaAdicional()
	{
		return this.mutualTasaAdicional;
	}

	public void setMutualTasaAdicional(float mutualTasaAdicional)
	{
		this.mutualTasaAdicional = mutualTasaAdicional;
	}

	public int getNumBloqueos()
	{
		return this.numBloqueos;
	}

	public void setNumBloqueos(int numBloqueos)
	{
		this.numBloqueos = numBloqueos;
	}

	public int getNumCotizaciones()
	{
		return this.numCotizaciones;
	}

	public void addNumCotizaciones(int numCotiz)
	{
		this.numCotizaciones += numCotiz;
	}

	public void setNumCotizaciones(int numCotizaciones)
	{
		this.numCotizaciones = numCotizaciones;
	}

	public int getNumCotizacionesCorregidas()
	{
		return this.numCotizacionesCorregidas;
	}

	public void setNumCotizacionesCorregidas(int numCotizacionesCorregidas)
	{
		this.numCotizacionesCorregidas = numCotizacionesCorregidas;
	}

	public int getNumCotizacionesOk()
	{
		return this.numCotizacionesOk;
	}

	public void addNumCotizacionesOk(int numCotizOk)
	{
		this.numCotizacionesOk += numCotizOk;
	}

	public void setNumCotizacionesOk(int numCotizacionesOk)
	{
		this.numCotizacionesOk = numCotizacionesOk;
	}

	public int getNumNominas()
	{
		return this.numNominas;
	}

	public void setNumNominas(int numNominas)
	{
		this.numNominas = numNominas;
	}

	public int getNumNominasCorregidas()
	{
		return this.numNominasCorregidas;
	}

	public void setNumNominasCorregidas(int numNominasCorregidas)
	{
		this.numNominasCorregidas = numNominasCorregidas;
	}

	public int getNumNominasOk()
	{
		return this.numNominasOk;
	}

	public void setNumNominasOk(int numNominasOk)
	{
		this.numNominasOk = numNominasOk;
	}

	public Date getUltimoUso()
	{
		return this.ultimoUso;
	}

	public void setUltimoUso(Date ultimoUso)
	{
		this.ultimoUso = ultimoUso;
	}

	public int getCalculoMovPersonal()
	{
		return this.calculoMovPersonal;
	}

	public void setCalculoMovPersonal(int calculoMovPersonal)
	{
		this.calculoMovPersonal = calculoMovPersonal;
	}

	public Set getEncargados()
	{
		return this.encargados;
	}

	public void setEncargados(Set encargados)
	{
		this.encargados = encargados;
	}

	public int compareTo(Object otroConvenio) 
	{
		ConvenioVO otro = (ConvenioVO) otroConvenio;
		return (new Integer(this.getIdConvenio())).compareTo(new Integer(otro.getIdConvenio()));
	}

	public int getIdActividad()
	{
		return this.idActividad;
	}

	public void setIdActividad(int idActividad)
	{
		this.idActividad = idActividad;
	}

	public List getComprobantes() 
	{
		return this.comprobantes;
	}

	public void setComprobantes(List comprobantes) 
	{
		this.comprobantes = comprobantes;
	}

	public void addComprobante(Object comprobante) 
	{
		if (this.comprobantes == null) 
			this.comprobantes = new ArrayList();
		this.comprobantes.add(comprobante);
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();

		parametros.put("1", String.valueOf(this.idEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idMapaNomRem));
		parametros.put("4", String.valueOf(this.idMapaNomGra));
		parametros.put("5", String.valueOf(this.idMapaNomDep));
		parametros.put("6", String.valueOf(this.idMapaNomRel));
		parametros.put("7", String.valueOf(this.idMapaCod));
		parametros.put("8", String.valueOf(this.idOpcion));
		parametros.put("9", String.valueOf(this.idCcaf));
		parametros.put("10", String.valueOf(this.descripcion));
		parametros.put("11", String.valueOf(this.habilitado));
		parametros.put("12", String.valueOf(this.creado));
		parametros.put("13", String.valueOf(this.ultimoUso));
		parametros.put("14", String.valueOf(this.mutualNumeroAdherente));
		parametros.put("15", String.valueOf(this.mutualTasaAdicional));
		parametros.put("16", String.valueOf(this.mutualCalculoIndividual));
		parametros.put("17", String.valueOf(this.numNominas));
		parametros.put("18", String.valueOf(this.numNominasOk));
		parametros.put("19", String.valueOf(this.numNominasCorregidas));
		parametros.put("20", String.valueOf(this.numCotizaciones));
		parametros.put("21", String.valueOf(this.numCotizacionesOk));
		parametros.put("22", String.valueOf(this.numCotizacionesCorregidas));
		parametros.put("23", String.valueOf(this.numBloqueos));
		parametros.put("24", String.valueOf(this.idGrupoConvenio));
		parametros.put("25", String.valueOf(this.idMutual));
		parametros.put("26", String.valueOf(this.calculoMovPersonal));

		return parametros;		
	}

	public Boolean getEditable()
	{
		return this.editable;
	}

	public void setEditable(Boolean editable)
	{
		this.editable = editable;
	}
	
	public String toString() {
		return "ConvenioVO[idEmpresa: " + this.idEmpresa
			+ ", idConvenio: " + this.idConvenio
			+ ", \"" +  this.descripcion.trim() + "\"]";
	}
}
