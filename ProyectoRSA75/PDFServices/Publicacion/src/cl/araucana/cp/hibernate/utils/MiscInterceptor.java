package cl.araucana.cp.hibernate.utils;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.CharacterType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;

/*
 * @(#) MapeoTesoreriaDao.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.7
 */
public class MiscInterceptor extends EmptyInterceptor
{
	private static final long serialVersionUID = 7990146411872733929L;
	private static final Logger logger = Logger.getLogger(MiscInterceptor.class);
	private HashMap inserts = new HashMap();
	private HashMap updates = new HashMap();
	private HashMap deletes = new HashMap();
	private boolean enabled = true;

	public MiscInterceptor()
	{
		super();
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException
	{
		//logger.debug("\nINTER-onFlushDirty:" + entity.getClass().getName() + ":id:" + id + "::");
		boolean bModificado = false;

		if (!(entity instanceof NodoVO) && !(entity instanceof ParametroVO))
			for (int i = 0; i < currentState.length; i++)
				if (types[i] instanceof StringType)
				{
					currentState[i] = String.valueOf(currentState[i]).toUpperCase();
					bModificado = true;
				} else if (types[i] instanceof CharacterType)
				{
					currentState[i] = String.valueOf(currentState[i]).toUpperCase();
					bModificado = true;
					currentState[i] = new Character(((String) currentState[i]).charAt(0));
				}
		return bModificado;
	}

	public void disable()
	{
		this.enabled = false;
	}

	public void enable()
	{
		this.enabled = true;
	}
}
