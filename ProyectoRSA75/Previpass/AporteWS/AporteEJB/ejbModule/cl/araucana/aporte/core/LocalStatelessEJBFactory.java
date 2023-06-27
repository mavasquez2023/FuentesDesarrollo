package cl.araucana.aporte.core;

import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

public class LocalStatelessEJBFactory {

    private static final Logger LOGGER = Logger.getLogger(LocalStatelessEJBFactory.class);

    /**
     * Obtiene la instancia de un EJB
     * @param ref
     * @param interfaceName
     * @return
     */
    public Object getInstance(String ref, String interfaceName) {

        Class interfaceClass = null;
        try {
            interfaceClass = Class.forName(interfaceName);
        } catch (ClassNotFoundException e) {
            LOGGER.error("No se pudo cargar la interfaz del EJB: " + interfaceName, e);
        }

        LocalStatelessEJBProxyHandler handler = new LocalStatelessEJBProxyHandler();
        handler.setRef(ref);
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{interfaceClass}, handler);
    }

}
