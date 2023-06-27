package cl.araucana.aporte.dispDatos.locator;


import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.log4j.BasicConfigurator;

import cl.araucana.aporte.core.LocalStatelessEJBFactory;
import cl.araucana.aporte.core.RemoteEJBLocatorException;
import cl.araucana.aporte.core.RemoteStatelessEJBLocator;
import cl.araucana.aporte.dispDatos.ejb.DispDatosLocal;
import cl.araucana.aporte.dispDatos.ejb.DispDatosLocalHome;
import cl.araucana.aporte.dispDatos.ejb.DispDatosRemote;
import cl.araucana.aporte.dispDatos.ejb.DispDatosRemoteHome;


public class DispDatosLocator {
    public static DispDatosLocal getEjbSample(){
        //System.out.println("DispDatosLocator");		
        BasicConfigurator.configure();
        LocalStatelessEJBFactory ejbFactory = new LocalStatelessEJBFactory(); 
        return (DispDatosLocal) ejbFactory.getInstance(DispDatosLocalHome.LOCALHOME_JNDI, DispDatosLocal.LOCAL_IFACE_NAME);
    }

    public static DispDatosRemote getEjbSampleRemote() throws RemoteEJBLocatorException {
        //System.out.println("DispDatosRemote");
        DispDatosRemote iface;
        try {
            DispDatosRemoteHome home = 
                (DispDatosRemoteHome) RemoteStatelessEJBLocator.getEjbRemoteHome(
                        DispDatosRemoteHome.REMOTE_HOME_JNDI, DispDatosRemoteHome.class);
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
