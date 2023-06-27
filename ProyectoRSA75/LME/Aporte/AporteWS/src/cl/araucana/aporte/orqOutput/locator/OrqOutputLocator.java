package cl.araucana.aporte.orqOutput.locator;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.log4j.BasicConfigurator;

import cl.araucana.aporte.core.LocalStatelessEJBFactory;
import cl.araucana.aporte.core.RemoteEJBLocatorException;
import cl.araucana.aporte.core.RemoteStatelessEJBLocator;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputLocalHome;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputRemote;
import cl.araucana.aporte.orqOutput.ejb.OrqOutputRemoteHome;

public class OrqOutputLocator {
    public static OrqOutputLocal getEjbSample(){
        //System.out.println("OrqInputLocator");		
        BasicConfigurator.configure();
        LocalStatelessEJBFactory ejbFactory = new LocalStatelessEJBFactory(); 
        return (OrqOutputLocal) ejbFactory.getInstance(OrqOutputLocalHome.LOCALHOME_JNDI, OrqOutputLocal.LOCAL_IFACE_NAME);
    }

    public static OrqOutputRemote getEjbSampleRemote() throws RemoteEJBLocatorException {
        //System.out.println("DispDatosRemote");
        OrqOutputRemote iface;
        try {
            OrqOutputRemoteHome home = 
                (OrqOutputRemoteHome) RemoteStatelessEJBLocator.getEjbRemoteHome(
                        OrqOutputRemoteHome.REMOTE_HOME_JNDI, OrqOutputRemoteHome.class);
            iface = home.create();
        } catch (RemoteException e) {
            throw new RemoteEJBLocatorException(e);
        } catch (NamingException e) {
            throw new RemoteEJBLocatorException(e);
        } catch (CreateException e) {
            throw new RemoteEJBLocatorException(e);
        }
        return iface;
    }

}
