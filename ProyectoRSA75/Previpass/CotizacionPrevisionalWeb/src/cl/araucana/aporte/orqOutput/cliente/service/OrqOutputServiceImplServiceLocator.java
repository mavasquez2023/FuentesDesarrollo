/**
 * OrqOutputServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.aporte.orqOutput.cliente.service;

import cl.araucana.cp.distribuidor.base.Constants;

public class OrqOutputServiceImplServiceLocator extends org.apache.axis.client.Service implements cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplService {

    public OrqOutputServiceImplServiceLocator() {
    }


    public OrqOutputServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OrqOutputServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OrqOutputServiceImpl
    private java.lang.String OrqOutputServiceImpl_address = Constants.URL_WS_INFORMAR_PAGO; //"http://146.83.1.129:9080/AporteWS/services/OrqOutputServiceImpl";

    public java.lang.String getOrqOutputServiceImplAddress() {
        return OrqOutputServiceImpl_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OrqOutputServiceImplWSDDServiceName = "OrqOutputServiceImpl";

    public java.lang.String getOrqOutputServiceImplWSDDServiceName() {
        return OrqOutputServiceImplWSDDServiceName;
    }

    public void setOrqOutputServiceImplWSDDServiceName(java.lang.String name) {
        OrqOutputServiceImplWSDDServiceName = name;
    }

    public cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImpl getOrqOutputServiceImpl() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OrqOutputServiceImpl_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOrqOutputServiceImpl(endpoint);
    }

    public cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImpl getOrqOutputServiceImpl(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplSoapBindingStub _stub = new cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplSoapBindingStub(portAddress, this);
            _stub.setPortName(getOrqOutputServiceImplWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOrqOutputServiceImplEndpointAddress(java.lang.String address) {
        OrqOutputServiceImpl_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplSoapBindingStub _stub = new cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplSoapBindingStub(new java.net.URL(OrqOutputServiceImpl_address), this);
                _stub.setPortName(getOrqOutputServiceImplWSDDServiceName());
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
        if ("OrqOutputServiceImpl".equals(inputPortName)) {
            return getOrqOutputServiceImpl();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.orqOutput.aporte.araucana.cl", "OrqOutputServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.orqOutput.aporte.araucana.cl", "OrqOutputServiceImpl"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OrqOutputServiceImpl".equals(portName)) {
            setOrqOutputServiceImplEndpointAddress(address);
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
