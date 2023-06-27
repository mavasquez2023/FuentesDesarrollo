package cl.araucana.adminCpe.hibernate.utils;

/*
* @(#) MiscInterceptor.java 1.6 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.6
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.CharacterType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import cl.araucana.adminCpe.logger.AuditLogger;
import cl.araucana.cp.distribuidor.business.beans.ObjectToLog;
import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AvisosVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EventoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;

public class MiscInterceptor extends EmptyInterceptor 
{
	private static final long serialVersionUID = 7990146411872733929L;
	private static final Logger logger = Logger.getLogger(MiscInterceptor.class);		
	private HashMap inserts = new HashMap();
	private HashMap updates = new HashMap();
	private HashMap deletes = new HashMap();

	public MiscInterceptor()
	{
		super();
	}

	/**
	 * @param
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException 
	{
		logger.debug("\nINTER-save:" + entity.getClass().getName() + ":id:" + id + "::");
		boolean bModificado = false;
		if (!(entity instanceof NodoVO) && !(entity instanceof ParametroVO) && !(entity instanceof AvisosVO))
			for (int i = 0; i < state.length; i++)
				if (types[i] instanceof StringType)
				{
					state[i] = String.valueOf(state[i]).toUpperCase();
					bModificado = true;				
				} else if (types[i] instanceof CharacterType)
				{
					state[i] = String.valueOf(state[i]).toUpperCase();
					bModificado = true;
					state[i] = new Character(((String)state[i]).charAt(0));
				}

		if (entity instanceof EventoVO) 
			return bModificado;
		synchronized (this.inserts) 
		{
			if (entity instanceof AuditableVO) 
				this.inserts.put(entity.getClass().getName() + "#" + id, new ObjectToLog(entity.getClass().getName(), ((AuditableVO)entity).getParametrosOrdenados()));
		}
		/*
		AuditLogger.registrarEvento("C", entity);*/

		return bModificado;
	}
	
	/**
	 * @param
	 */
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException 
	{
		logger.debug("\nINTER-onFlushDirty:" + entity.getClass().getName() + ":id:" + id + "::");
		boolean bModificado = false;

		if (!(entity instanceof NodoVO) && !(entity instanceof ParametroVO) && !(entity instanceof AvisosVO))
			for (int i = 0; i < currentState.length; i++)
				if (types[i] instanceof StringType)
				{
					currentState[i] = String.valueOf(currentState[i]).toUpperCase();
					bModificado = true;
				}else if (types[i] instanceof CharacterType)
				{
					currentState[i] = String.valueOf(currentState[i]).toUpperCase();
					bModificado = true;
					currentState[i] = new Character(((String)currentState[i]).charAt(0));
				}

		return bModificado;
	}

	/**
	 * 
	 * @param entity
	 * @param id
	 * @param currentState
	 * @param previousState
	 * @param propertyNames
	 * @param types
	 * @throws CallbackException
	 */
	public void onDelete(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException
	{
		logger.debug("\nINTER-onDelete:" + entity.getClass().getName() + ":id:" + id + "::");
		if (entity instanceof EventoVO) 
			return;
		synchronized (this.deletes) 
		{
			if (entity instanceof AuditableVO) 
				this.deletes.put(entity.getClass().getName() + "#" + id, new ObjectToLog(entity.getClass().getName(), ((AuditableVO)entity).getParametrosOrdenados()));
		}
	}

	/**
	 * @param
	 */
	public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) 
	{
        if(currentState == null || previousState == null)
            return null;

        ArrayList ints = new ArrayList();
        for(int i = 0; i < currentState.length; i++) 
        {
            Object current = currentState[i];
            Object previous = previousState[i];
            try
            {
                if(current == previous) 
                	continue;
                if(current != null && previous != null)
                {
                    if(previous.toString().trim().equals(current.toString().trim()))
                    	continue;
                    /*Type type = types[i];
                    if(!types[i].getReturnedClass().equals(Date.class)) 
                    	continue;*/
                }
            } catch(Exception e)
            {
            	logger.error("findDirty:", e);
            }
            logger.debug("\t distintos...:" + id + ":campo:" + propertyNames[i] + ":tipo:" + types[i].getName() + "::");
            logger.debug("valorAnterior:" + previous + "::");
            logger.debug("valorActual  :" + current + "::");
            ints.add(new Integer(i));
        }
        if(ints.size() > 0)
        {
            int[] intArray = new int[ints.size() + 1];
            int ind = 0;
            for(Iterator iterator = ints.iterator(); iterator.hasNext();) 
            {
                Integer integer = (Integer) iterator.next();
                intArray[ind++] = integer.intValue();
            }
			synchronized (this.updates) 
			{
				if (entity instanceof AuditableVO) 
					this.updates.put(entity.getClass().getName() + "#" + id, new ObjectToLog(entity.getClass().getName(), ((AuditableVO)entity).getParametrosOrdenados()));
			}
            return intArray;
        }
        return new int[0];
    }


	/**
	 * @param
	 */
	public void beforeTransactionCompletion(Transaction tx)
	{
		try
		{
			Session session = HibernateUtil.getSession();
			logger.debug("\n\nbeforeTransactionCompletion:\n\n");
			logger.debug("\tinserts:");
			synchronized (this.inserts) 
			{
				for (Iterator it = this.inserts.values().iterator(); it.hasNext();)
				{
					ObjectToLog objectToLog = (ObjectToLog)it.next();
					logger.debug("\t\tinserts:" + objectToLog.getClassName() + "::");
					AuditLogger.registrarEvento("C", objectToLog, session);
				}
				this.inserts.clear();
			}
			logger.debug("\tupdates:");
			synchronized (this.updates) 
			{
				for (Iterator it = this.updates.values().iterator(); it.hasNext();)
				{
					ObjectToLog objectToLog = (ObjectToLog)it.next();
					logger.debug("\t\tinserts:" + objectToLog.getClassName() + "::");
					AuditLogger.registrarEvento("M", objectToLog, session);
				}
				this.updates.clear();
			}
			logger.debug("\tdeletes:");
			synchronized (this.deletes) 
			{
				for (Iterator it = this.deletes.values().iterator(); it.hasNext();)
				{
					ObjectToLog objectToLog = (ObjectToLog)it.next();
					logger.debug("\t\tinserts:" + objectToLog.getClassName() + "::");
					AuditLogger.registrarEvento("D", objectToLog, session);
				}
				this.deletes.clear();
			}
		} catch (Exception e)
		{
			logger.error("ERROR MiscInterceptor:beforeTransactionCompletion", e);
		}
	}
}
