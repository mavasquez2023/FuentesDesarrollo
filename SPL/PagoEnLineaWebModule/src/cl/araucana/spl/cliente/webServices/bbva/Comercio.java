/**
 * Comercio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.spl.cliente.webServices.bbva;

public interface Comercio extends javax.xml.rpc.Service {
    public java.lang.String getComercioSoapAddress();

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap() throws javax.xml.rpc.ServiceException;

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getComercioSoap12Address();

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap12() throws javax.xml.rpc.ServiceException;

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
