/**
 * ValidaNumeroSerieServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.validaNumeroSerie;

public class ValidaNumeroSerieServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieService {

    public ValidaNumeroSerieServiceLocator() {
    }


    public ValidaNumeroSerieServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ValidaNumeroSerieServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ValidaNumeroSeriePort
    private java.lang.String ValidaNumeroSeriePort_address = "http://localhost:9083/WSSinacofi/ValidaNumeroSerieService";

    public java.lang.String getValidaNumeroSeriePortAddress() {
        return ValidaNumeroSeriePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ValidaNumeroSeriePortWSDDServiceName = "ValidaNumeroSeriePort";

    public java.lang.String getValidaNumeroSeriePortWSDDServiceName() {
        return ValidaNumeroSeriePortWSDDServiceName;
    }

    public void setValidaNumeroSeriePortWSDDServiceName(java.lang.String name) {
        ValidaNumeroSeriePortWSDDServiceName = name;
    }

    public cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieImpl getValidaNumeroSeriePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ValidaNumeroSeriePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getValidaNumeroSeriePort(endpoint);
    }

    public cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieImpl getValidaNumeroSeriePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSeriePortBindingStub _stub = new cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSeriePortBindingStub(portAddress, this);
            _stub.setPortName(getValidaNumeroSeriePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setValidaNumeroSeriePortEndpointAddress(java.lang.String address) {
        ValidaNumeroSeriePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSeriePortBindingStub _stub = new cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSeriePortBindingStub(new java.net.URL(ValidaNumeroSeriePort_address), this);
                _stub.setPortName(getValidaNumeroSeriePortWSDDServiceName());
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
        if ("ValidaNumeroSeriePort".equals(inputPortName)) {
            return getValidaNumeroSeriePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaNumeroSerie", "ValidaNumeroSerieService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaNumeroSerie", "ValidaNumeroSeriePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ValidaNumeroSeriePort".equals(portName)) {
            setValidaNumeroSeriePortEndpointAddress(address);
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
