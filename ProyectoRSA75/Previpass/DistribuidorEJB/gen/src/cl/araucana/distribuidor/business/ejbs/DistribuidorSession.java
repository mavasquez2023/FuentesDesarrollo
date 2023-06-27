package cl.araucana.distribuidor.business.ejbs;

import cl.araucana.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.distribuidor.hibernate.exceptions.InfrastructureException;

/**
 * Remote interface for cl.araucana.distribuidor.business.ejbs.DistribuidorSessionBean bean.
 */
public interface DistribuidorSession extends javax.ejb.EJBObject 
{
	public NodoVO getNodo() throws InfrastructureException, java.rmi.RemoteException;

	public NodoVO getNodoDistribuidor() throws InfrastructureException, java.rmi.RemoteException;

	/**
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public StringBuffer getEnv() throws java.rmi.RemoteException;
}
