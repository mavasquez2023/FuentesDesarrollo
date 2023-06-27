package cl.araucana.aporte.orqInput.locator;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.log4j.BasicConfigurator;

import cl.araucana.aporte.core.LocalStatelessEJBFactory;
import cl.araucana.aporte.core.RemoteEJBLocatorException;
import cl.araucana.aporte.core.RemoteStatelessEJBLocator;
import cl.araucana.aporte.orqInput.ejb.OrqInputLocal;
import cl.araucana.aporte.orqInput.ejb.OrqInputLocalHome;
import cl.araucana.aporte.orqInput.ejb.OrqInputRemote;
import cl.araucana.aporte.orqInput.ejb.OrqInputRemoteHome;

public class OrqInputLocator {
    public static OrqInputLocal getEjbSample(){
        //System.out.println("OrqInputLocator");		
        BasicConfigurator.configure();
        LocalStatelessEJBFactory ejbFactory = new LocalStatelessEJBFactory(); 
        return (OrqInputLocal) ejbFactory.getInstance(OrqInputLocalHome.LOCALHOME_JNDI, OrqInputLocal.LOCAL_IFACE_NAME);
    }

    public static OrqInputRemote getEjbSampleRemote() throws RemoteEJBLocatorException {
        //System.out.println("DispDatosRemote");
        OrqInputRemote iface;
        try {
            OrqInputRemoteHome home = 
                (OrqInputRemoteHome) RemoteStatelessEJBLocator.getEjbRemoteHome(
                        OrqInputRemoteHome.REMOTE_HOME_JNDI, OrqInputRemoteHome.class);
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