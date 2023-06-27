package cl.araucana.cp.distribuidor.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
/*
* @(#) ParametrosSPLVO.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/ 
/**
 * @author aacuna
 * 
 * @version 1.1
 */
public class ParametrosHash implements Serializable
{
	private static final long serialVersionUID = -8198380534137162846L;
	private HashMap valores = new HashMap();
	private static Logger logger = Logger.getLogger(ParametrosHash.class);

	public ParametrosHash()
	{		super();
	}
	/**
	 * parametros splvo
	 * @param valores
	 */
	public ParametrosHash(HashMap valores)
	{
		super();
		this.valores = valores;
	}
	/**
	 * muestra parametros
	 *
	 */
	public void showParams()
	{
		for (Iterator it = this.valores.keySet().iterator(); it.hasNext();)
		{
			String key = (String)it.next();
			logger.debug("show:" + key + "::" + (String)this.valores.get(key) + "::");
		}
	}
	/**
	 * get
	 * @param key
	 * @return
	 */
	public String get(String key)
	{
		if (this.valores.containsKey(key))
			return (String)this.valores.get(key);
		if (this.valores.containsKey(key.toUpperCase()))
			return (String)this.valores.get(key.toUpperCase());
		logger.info("parametro no esta:" + key + "::");
		return "";
	}
	/**
	 * agrega
	 * @param key
	 * @param value
	 */
	public void add(String key, String value)
	{
		this.valores.put(key, value);
	}
	/**
	 * valores
	 * @return
	 */
	public HashMap getValores()
	{
		return this.valores;
	}
	/**
	 * valores
	 * @param valores
	 */
	public void setValores(HashMap valores)
	{
		this.valores = valores;
	}
}
