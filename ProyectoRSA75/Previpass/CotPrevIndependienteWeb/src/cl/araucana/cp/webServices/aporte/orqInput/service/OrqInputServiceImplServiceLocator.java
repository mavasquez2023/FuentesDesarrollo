/**
 * OrqInputServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.cp.webServices.aporte.orqInput.service;

import cl.araucana.cp.distribuidor.base.Constants;

public class OrqInputServiceImplServiceLocator extends org.apache.axis.client.Service implements cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImplService {

    public OrqInputServiceImplServiceLocator() {
    }


    public OrqInputServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OrqInputServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OrqInputServiceImpl
    private java.lang.String OrqInputServiceImpl_address = Constants.URL_WS_OBTENER_PAGO; //"http://146.83.1.129:9080/AporteWS/services/OrqInputServiceImpl";

    public java.lang.String getOrqInputServiceImplAddress() {
        return OrqInputServiceImpl_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OrqInputServiceImplWSDDServiceName = "OrqInputServiceImpl";

    public java.lang.String getOrqInputServiceImplWSDDServiceName() {
        return OrqInputServiceImplWSDDServiceName;
    }

    public void setOrqInputServiceImplWSDDServiceName(java.lang.String name) {
        OrqInputServiceImplWSDDServiceName = name;
    }

    public cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImpl getOrqInputServiceImpl() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OrqInputServiceImpl_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOrqInputServiceImpl(endpoint);
    }

    public cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImpl getOrqInputServiceImpl(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImplSoapBindingStub _stub = new cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImplSoapBindingStub(portAddress, this);
            _stub.setPortName(getOrqInputServiceImplWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOrqInputServiceImplEndpointAddress(java.lang.String address) {
        OrqInputServiceImpl_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImplSoapBindingStub _stub = new cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImplSoapBindingStub(new java.net.URL(OrqInputServiceImpl_address), this);
                _stub.setPortName(getOrqInputServiceImplWSDDServiceName());
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
        if ("OrqInputServiceImpl".equals(inputPortName)) {
            return getOrqInputServiceImpl();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.orqInput.aporte.araucana.cl", "OrqInputServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.orqInput.aporte.araucana.cl", "OrqInputServiceImpl"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OrqInputServiceImpl".equals(portName)) {
            setOrqInputServiceImplEndpointAddress(address);
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
