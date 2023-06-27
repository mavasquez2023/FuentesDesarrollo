package cl.araucana.aporte.core;

import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class RemoteStatelessEJBLocator {

    public static EJBHome getEjbRemoteHome(String remoteHomeJndiName,
            Class ejbRemoteHomeClass) throws NamingException  {
        EJBHome remoteHome = null;
//      Properties prop = new Properties();
//      prop.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory"); 
//      prop.put(Context.PROVIDER_URL,"iiop:/localhost:900"); 
        Context ctx = new InitialContext();
        Object narrowFromObj = ctx.lookup(remoteHomeJndiName);
        remoteHome = (EJBHome) PortableRemoteObject.narrow(narrowFromObj, ejbRemoteHomeClass);

        return remoteHome;
    }
}
