package cl.araucana.cp.distribuidor.business.secciones;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.SeccionException;

public class Seccion
{
	protected Logger log = Logger.getLogger(Seccion.class);
	protected boolean logear = false;
	protected int numM;
	protected String asterisco = "", noMostrar = "";
	protected ConvenioVO convenio;
	protected HashMap propM;
	protected List tiposSecciones;
	protected HashMap tsHM;
	protected HashMap parametros;
	protected HashMap entidadesCCAF;
	protected int numTrabajadoresSeccion = 0;
	protected boolean cuenta = false;

	public static int IS_AFP_NINGUNA = 0;
	public static int IS_AFP = 1;
	public static int IS_INP = 2;

	protected int []numTrab = new int[12];
	protected double []mTmp = new double[12];

	public Seccion(Integer numM, ConvenioVO convenio, HashMap propM, HashMap parametros, List tiposSecciones, HashMap entidadesCCAF)
	{
		super();
		this.numM = numM.intValue();
		for (int i = 0; i < this.numM; i++)
		{
			this.asterisco += "0";
			this.noMostrar += "0";
		}
		this.convenio = convenio;
		init(propM, tiposSecciones, parametros, entidadesCCAF);
	}

	private void init(HashMap propsM, List tiposSecc, HashMap params, HashMap entsCCAF)
	{
		this.propM = propsM;
		this.tiposSecciones = tiposSecc;
		this.parametros = params;
		this.entidadesCCAF = entsCCAF;
		
		this.tsHM = new HashMap();
		for (Iterator it = this.tiposSecciones.iterator(); it.hasNext();)
		{
			TipoSeccionVO ts = (TipoSeccionVO)it.next();
			this.tsHM.put("" + ts.getId() + ts.getM1Nombre().trim(), new Integer(1));
			this.tsHM.put("" + ts.getId() + ts.getM2Nombre().trim(), new Integer(2));
			this.tsHM.put("" + ts.getId() + ts.getM3Nombre().trim(), new Integer(3));
			this.tsHM.put("" + ts.getId() + ts.getM4Nombre().trim(), new Integer(4));
			this.tsHM.put("" + ts.getId() + ts.getM5Nombre().trim(), new Integer(5));
			this.tsHM.put("" + ts.getId() + ts.getM6Nombre().trim(), new Integer(6));
			this.tsHM.put("" + ts.getId() + ts.getM7Nombre().trim(), new Integer(7));
			this.tsHM.put("" + ts.getId() + ts.getM8Nombre().trim(), new Integer(8));
			this.tsHM.put("" + ts.getId() + ts.getM9Nombre().trim(), new Integer(9));
			this.tsHM.put("" + ts.getId() + ts.getM10Nombre().trim(), new Integer(10));
			this.tsHM.put("" + ts.getId() + ts.getM11Nombre().trim(), new Integer(11));
			this.tsHM.put("" + ts.getId() + ts.getM12Nombre().trim(), new Integer(12));
		}
		for (Iterator it = this.tsHM.keySet().iterator(); it.hasNext();)
		{
			String clave = (String)it.next();
			if (this.logear) 
				this.log.debug("tsHM:clave:" + clave + ":valor:"  + ((Integer)this.tsHM.get(clave)).intValue() + "::");
		}
	}

	public long finishProcess(char tipoProceso, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear) this.log.info("\n\nfinishProcess PADRE:tipoProceso:" + tipoProceso + ":seccion:" + seccion.getIdTipoSeccion() + "::");
			return 0;
		} catch (Exception e)
		{
			throw new SeccionException();
		}
	}

	public void generar(char tipoProceso, CotizanteVO cotizante, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear) this.log.info("\n\ngenerando PADRE:tipoProceso:" + tipoProceso + ":seccion:" + seccion.getIdTipoSeccion() + ":cotizante:" + cotizante.getIdCotizante() + "::");
		} catch (Exception e)
		{
			throw new SeccionException();
		}
	}

	public int getIndice(int id, String str) throws SeccionException
	{
		String nombre = "";
		try
		{
			nombre = (String)this.propM.get(str);
			if (this.logear) this.log.debug("nombre:" + nombre + ":pos:" + ((Integer)this.tsHM.get("" + id + nombre)).intValue() + "::");
			return ((Integer)this.tsHM.get("" + id + nombre)).intValue() - 1;
		} catch (Exception e)
		{
			this.log.error("problemas getIndice", e);
			throw new SeccionException("problema en seccion:getIndice, params:id:" + id + ":str:" + str + ":nombre:" + nombre + "::");
		}
	}

	public long setValores(int idTipoSeccion, String nombre, long valor) throws SeccionException
	{
		try
		{
			int pos = getIndice(idTipoSeccion, idTipoSeccion + nombre);
			if (this.logear) this.log.debug("idTipoSeccion:" + idTipoSeccion + ":nombre:" + nombre + ":pos:" + pos + ":valor:" + valor + "::");
			this.mTmp[pos] = valor;
			//if (valor > 0)
				this.numTrab[pos] += 1;
			return valor;
		} catch (Exception e)
		{
			this.log.error("problemas getIndice", e);
			throw new SeccionException("problema en seccion:setValores, params:idTipoSeccion:" + idTipoSeccion + ":valor:" + valor + ":nombre:" + nombre + "::");
		}
	}

	public long restaValores(int idTipoSeccion, String nombre, long valor) throws SeccionException
	{
		try
		{
			int pos = getIndice(idTipoSeccion, idTipoSeccion + nombre);
			if (this.logear) this.log.debug("idTipoSeccion:" + idTipoSeccion + ":nombre:" + nombre + ":pos:" + pos + ":valor:" + valor + "::");
			this.mTmp[pos] = valor * -1;
			if (valor > 0 && this.numTrab[pos] > 0)
				this.numTrab[pos] -= 1;
			return valor;
		} catch (Exception e)
		{
			this.log.error("problemas getIndice", e);
			throw new SeccionException("problema en seccion:setValores, params:idTipoSeccion:" + idTipoSeccion + ":valor:" + valor + ":nombre:" + nombre + "::");
		}
	}

	public void restar(char tipoProceso, CotizanteVO oldCotiz, SeccionVO seccion) throws SeccionException
	{
		try
		{
			if (this.logear) this.log.info("\n\ngenerando PADRE:tipoProceso:" + tipoProceso + ":seccion:" + seccion.getIdTipoSeccion() + ":cotizante:" + oldCotiz.getIdCotizante() + "::");
		} catch (Exception e)
		{
			throw new SeccionException();
		}
	}

	public int getNumTrabajadoresSeccion()
	{
		return this.numTrabajadoresSeccion;
	}

	public void setNumTrabajadoresSeccion(int numTrabajadoresSeccion)
	{
		this.numTrabajadoresSeccion = numTrabajadoresSeccion;
	}
	public void calculaTotalINP(SeccionVO seccion, int idTipoSeccion) throws SeccionException
	{
		
	}
	
}
