package cl.araucana.cp.distribuidor.hibernate.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.CharacterType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import cl.araucana.cp.distribuidor.business.beans.ObjectToLog;
import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EventoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.logger.AuditLogger;

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

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException
	{
		boolean bModificado = false;
		if (!(entity instanceof NodoVO) && !(entity instanceof ParametroVO))
			for (int i = 0; i < state.length; i++)
				if (types[i] instanceof StringType)
				{
					state[i] = String.valueOf(state[i]).toUpperCase();
					bModificado = true;
				} else if (types[i] instanceof CharacterType)
				{
					state[i] = String.valueOf(state[i]).toUpperCase();
					bModificado = true;
					state[i] = new Character(((String) state[i]).charAt(0));
				}

		if (entity instanceof EventoVO)
			return bModificado;
		synchronized (this.inserts)
		{
			if (entity instanceof AuditableVO && this.enabled)
				this.inserts.put(entity.getClass().getName() + "#" + id, new ObjectToLog(entity.getClass().getName(), ((AuditableVO) entity).getParametrosOrdenados()));
		}

		return bModificado;
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException
	{
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

	public void onDelete(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException
	{
		// logger.debug("\nINTER-onDelete:" + entity.getClass().getName() + ":id:" + id + "::");
		if (entity instanceof EventoVO)
			return;
		synchronized (this.deletes)
		{
			if (entity instanceof AuditableVO && this.enabled)
				this.deletes.put(entity.getClass().getName() + "#" + id, new ObjectToLog(entity.getClass().getName(), ((AuditableVO) entity).getParametrosOrdenados()));
		}
	}

	public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
	{
		if (currentState == null || previousState == null)
			return null;
		if (entity instanceof NominaVO || entity instanceof NodoVO)
			return null;

		ArrayList ints = new ArrayList();
		for (int i = 0; i < currentState.length; i++)
		{
			Object current = currentState[i];
			Object previous = previousState[i];
			try
			{
				if (current == previous)
					continue;
				if (current != null && previous != null)
				{
					if (previous.toString().trim().equals(current.toString().trim()))
						continue;
				}
			} catch (Exception e)
			{
				logger.error("problemas findDirty:", e);
			}
			if (types[i].getName().equals("binary"))
				continue;
			ints.add(new Integer(i));
		}
		if (ints.size() > 0)
		{
			int[] intArray = new int[ints.size() + 1];
			int ind = 0;
			for (Iterator iterator = ints.iterator(); iterator.hasNext();)
			{
				Integer integer = (Integer) iterator.next();
				intArray[ind++] = integer.intValue();
			}
			synchronized (this.updates)
			{
				if (entity instanceof AuditableVO && this.enabled)
					this.updates.put(entity.getClass().getName() + "#" + id, new ObjectToLog(entity.getClass().getName(), ((AuditableVO) entity).getParametrosOrdenados()));
			}
			return intArray;
		}
		return new int[0];
	}

	public void beforeTransactionCompletion(Transaction tx)
	{
		try
		{
			logger.debug("\n\nbeforeTransactionCompletion:\n\n");
			logger.debug("\tinserts:");
			synchronized (this.inserts)
			{
				for (Iterator it = this.inserts.values().iterator(); it.hasNext();)
				{
					ObjectToLog objectToLog = (ObjectToLog) it.next();
					logger.debug("\t\tinserts:" + objectToLog.getClassName() + "::");
					AuditLogger.registrarEvento("C", objectToLog);
				}
				this.inserts.clear();
			}
			logger.debug("\tupdates:");
			synchronized (this.updates)
			{
				for (Iterator it = this.updates.values().iterator(); it.hasNext();)
				{
					ObjectToLog objectToLog = (ObjectToLog) it.next();
					logger.debug("\t\tinserts:" + objectToLog.getClassName() + "::");
					AuditLogger.registrarEvento("M", objectToLog);
				}
				this.updates.clear();
			}
			logger.debug("\tdeletes:");
			synchronized (this.deletes)
			{
				for (Iterator it = this.deletes.values().iterator(); it.hasNext();)
				{
					ObjectToLog objectToLog = (ObjectToLog) it.next();
					logger.debug("\t\tinserts:" + objectToLog.getClassName() + "::");
					AuditLogger.registrarEvento("D", objectToLog);
				}
				this.deletes.clear();
			}
		} catch (Exception e)
		{
			logger.error("ERROR MiscInterceptor:beforeTransactionCompletion", e);
		}
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
