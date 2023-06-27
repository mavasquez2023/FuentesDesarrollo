package cl.araucana.aporte.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


/**
 * Manejador para la invocación de un EJB, no modificar
 * @author everis
 *
 */
public class LocalStatelessEJBProxyHandler implements InvocationHandler {

    private static final Logger s_logger =
        Logger.getLogger(LocalStatelessEJBProxyHandler.class);

    private String ref;
    private Object _service;

    /**
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * 
     * @param proxy
     * @param method
     * @param args
     * @return 
     * @throws
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object service = lookupService();

        Object result=null;

        try {
            result = method.invoke(service, args);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            s_logger.error("Excepcion de tipo InvocationTargetException inexperada en la invocacion del EJB: ", e);
            throw targetException;
        } catch (RuntimeException e) {
            s_logger.error("Excepcion de tipo RutimeException inexperada en la invocacion del EJB: ", e);
            throw new LocalEJBException("RutimeException inexperada en la invocacion del EJB: ", e);
        } catch (Exception e) {
            s_logger.error("Excepcion inexperada en la invocacion del EJB: ", e);
            throw new Exception(e.getMessage());
        } 
        return result;
    }

    /**
     * 
     * @return
     * @throws NamingException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private Object lookupService() throws NamingException, NameNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object service = getService();
        if ( service == null ) {
            InitialContext ctx = new InitialContext();
            Object o = ctx.lookup(ref);
            Method m = o.getClass().getMethod("create", (Class[]) null);
            service = m.invoke(o, (Object[]) null);
            setService(service);
        }
        return service;
    }

    public void listContext(Context c) throws NamingException {

        String name = "";

        NamingEnumeration enums = c.list(name);                
        while (enums.hasMore()) {
            NameClassPair ncPair = (NameClassPair)enums.next();
            System.out.print(ncPair.getName() + " (type ");
            System.out.println(ncPair.getClassName() + ")");
            if(c.lookup(ncPair.getName()) instanceof Context){
                Context otroC = (Context) c.lookup(ncPair.getName());
                listContext(otroC);
            }
        }
    }

    private synchronized Object getService() {
        return _service;
    }

    private synchronized void setService(Object p_service) {
        _service = p_service;
    }

}
