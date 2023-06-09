/**
 * EvaluadorModelosLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos;

public class EvaluadorModelosLocator extends org.apache.axis.client.Service implements net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelos {

/**
 * Evaluador de modelos
 */

    public EvaluadorModelosLocator() {
    }


    public EvaluadorModelosLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EvaluadorModelosLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EvaluadorModelosSoap
    private java.lang.String EvaluadorModelosSoap_address = "https://saas1.ais-int.net/AISRulesScoSaaSWS/EvaluadorModelos.asmx";

    public java.lang.String getEvaluadorModelosSoapAddress() {
        return EvaluadorModelosSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EvaluadorModelosSoapWSDDServiceName = "EvaluadorModelosSoap";

    public java.lang.String getEvaluadorModelosSoapWSDDServiceName() {
        return EvaluadorModelosSoapWSDDServiceName;
    }

    public void setEvaluadorModelosSoapWSDDServiceName(java.lang.String name) {
        EvaluadorModelosSoapWSDDServiceName = name;
    }

    public net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoap getEvaluadorModelosSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EvaluadorModelosSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEvaluadorModelosSoap(endpoint);
    }

    public net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoap getEvaluadorModelosSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoapStub _stub = new net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoapStub(portAddress, this);
            _stub.setPortName(getEvaluadorModelosSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEvaluadorModelosSoapEndpointAddress(java.lang.String address) {
        EvaluadorModelosSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoapStub _stub = new net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoapStub(new java.net.URL(EvaluadorModelosSoap_address), this);
                _stub.setPortName(getEvaluadorModelosSoapWSDDServiceName());
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
        if ("EvaluadorModelosSoap".equals(inputPortName)) {
            return getEvaluadorModelosSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://schemas.ais-int.net/soap/AIS.Rules.WebServices/EvaluadorModelos", "EvaluadorModelos");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://schemas.ais-int.net/soap/AIS.Rules.WebServices/EvaluadorModelos", "EvaluadorModelosSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EvaluadorModelosSoap".equals(portName)) {
            setEvaluadorModelosSoapEndpointAddress(address);
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
