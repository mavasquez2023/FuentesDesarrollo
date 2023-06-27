/**
 * ComercioLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.spl.cliente.webServices.bbva;

public class ComercioLocator extends org.apache.axis.client.Service implements cl.araucana.spl.cliente.webServices.bbva.Comercio {

    public ComercioLocator() {
    }


    public ComercioLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ComercioLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ComercioSoap
    private java.lang.String ComercioSoap_address = "http://certificacion.serviex.cl/BBVA/BP/WSIntegracionComercio/SX.BP.Comercio.asmx";

    public java.lang.String getComercioSoapAddress() {
        return ComercioSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ComercioSoapWSDDServiceName = "ComercioSoap";

    public java.lang.String getComercioSoapWSDDServiceName() {
        return ComercioSoapWSDDServiceName;
    }

    public void setComercioSoapWSDDServiceName(java.lang.String name) {
        ComercioSoapWSDDServiceName = name;
    }

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ComercioSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getComercioSoap(endpoint);
    }

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.araucana.spl.cliente.webServices.bbva.ComercioSoapStub _stub = new cl.araucana.spl.cliente.webServices.bbva.ComercioSoapStub(portAddress, this);
            _stub.setPortName(getComercioSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setComercioSoapEndpointAddress(java.lang.String address) {
        ComercioSoap_address = address;
    }


    // Use to get a proxy class for ComercioSoap12
    private java.lang.String ComercioSoap12_address = "http://certificacion.serviex.cl/BBVA/BP/WSIntegracionComercio/SX.BP.Comercio.asmx";

    public java.lang.String getComercioSoap12Address() {
        return ComercioSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ComercioSoap12WSDDServiceName = "ComercioSoap12";

    public java.lang.String getComercioSoap12WSDDServiceName() {
        return ComercioSoap12WSDDServiceName;
    }

    public void setComercioSoap12WSDDServiceName(java.lang.String name) {
        ComercioSoap12WSDDServiceName = name;
    }

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ComercioSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getComercioSoap12(endpoint);
    }

    public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.araucana.spl.cliente.webServices.bbva.ComercioSoap12Stub _stub = new cl.araucana.spl.cliente.webServices.bbva.ComercioSoap12Stub(portAddress, this);
            _stub.setPortName(getComercioSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setComercioSoap12EndpointAddress(java.lang.String address) {
        ComercioSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.araucana.spl.cliente.webServices.bbva.ComercioSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.spl.cliente.webServices.bbva.ComercioSoapStub _stub = new cl.araucana.spl.cliente.webServices.bbva.ComercioSoapStub(new java.net.URL(ComercioSoap_address), this);
                _stub.setPortName(getComercioSoapWSDDServiceName());
                return _stub;
            }
            if (cl.araucana.spl.cliente.webServices.bbva.ComercioSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.spl.cliente.webServices.bbva.ComercioSoap12Stub _stub = new cl.araucana.spl.cliente.webServices.bbva.ComercioSoap12Stub(new java.net.URL(ComercioSoap12_address), this);
                _stub.setPortName(getComercioSoap12WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ComercioSoap".equals(inputPortName)) {
            return getComercioSoap();
        }
        else if ("ComercioSoap12".equals(inputPortName)) {
            return getComercioSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "Comercio");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ComercioSoap"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ComercioSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ComercioSoap".equals(portName)) {
            setComercioSoapEndpointAddress(address);
        }
        else 
if ("ComercioSoap12".equals(portName)) {
            setComercioSoap12EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
