/**
 * CuentaBancariaServicioServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.transferencias;

public class CuentaBancariaServicioServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicio.transferencias.CuentaBancariaServicioService {

    public CuentaBancariaServicioServiceLocator() {
    }


    public CuentaBancariaServicioServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CuentaBancariaServicioServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CuentaBancariaServicioPort
    private java.lang.String CuentaBancariaServicioPort_address = "http://localhost:9083/WSCuentaBancariaWeb/CuentaBancariaServicioService";

    public java.lang.String getCuentaBancariaServicioPortAddress() {
        return CuentaBancariaServicioPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CuentaBancariaServicioPortWSDDServiceName = "CuentaBancariaServicioPort";

    public java.lang.String getCuentaBancariaServicioPortWSDDServiceName() {
        return CuentaBancariaServicioPortWSDDServiceName;
    }

    public void setCuentaBancariaServicioPortWSDDServiceName(java.lang.String name) {
        CuentaBancariaServicioPortWSDDServiceName = name;
    }

    public cl.laaraucana.servicio.transferencias.CuentaBancariaServicioImpl getCuentaBancariaServicioPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CuentaBancariaServicioPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCuentaBancariaServicioPort(endpoint);
    }

    public cl.laaraucana.servicio.transferencias.CuentaBancariaServicioImpl getCuentaBancariaServicioPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.servicio.transferencias.CuentaBancariaServicioPortBindingStub _stub = new cl.laaraucana.servicio.transferencias.CuentaBancariaServicioPortBindingStub(portAddress, this);
            _stub.setPortName(getCuentaBancariaServicioPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCuentaBancariaServicioPortEndpointAddress(java.lang.String address) {
        CuentaBancariaServicioPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.servicio.transferencias.CuentaBancariaServicioImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.servicio.transferencias.CuentaBancariaServicioPortBindingStub _stub = new cl.laaraucana.servicio.transferencias.CuentaBancariaServicioPortBindingStub(new java.net.URL(CuentaBancariaServicioPort_address), this);
                _stub.setPortName(getCuentaBancariaServicioPortWSDDServiceName());
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
        if ("CuentaBancariaServicioPort".equals(inputPortName)) {
            return getCuentaBancariaServicioPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.laaraucana.cl/cuentabancaria", "CuentaBancariaServicioService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/cuentabancaria", "CuentaBancariaServicioPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CuentaBancariaServicioPort".equals(portName)) {
            setCuentaBancariaServicioPortEndpointAddress(address);
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
