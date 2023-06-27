/**
 * CertificadoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.autoconsulta.ws;

public class CertificadoServiceLocator extends org.apache.axis.client.Service implements cl.araucana.autoconsulta.ws.CertificadoService {

	public CertificadoServiceLocator() {
    }


    public CertificadoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CertificadoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Certificado
    //private java.lang.String Certificado_address = "https://localhost:9443/WSCertificado/services/Certificado";
    private java.lang.String Certificado_address = "http://localhost:9081/WSCertificado/services/Certificado";

    public java.lang.String getCertificadoAddress() {
        return Certificado_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CertificadoWSDDServiceName = "Certificado";

    public java.lang.String getCertificadoWSDDServiceName() {
        return CertificadoWSDDServiceName;
    }

    public void setCertificadoWSDDServiceName(java.lang.String name) {
        CertificadoWSDDServiceName = name;
    }

    public cl.araucana.autoconsulta.ws.Certificado getCertificado() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Certificado_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCertificado(endpoint);
    }

    public cl.araucana.autoconsulta.ws.Certificado getCertificado(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	System.out.println("Ruta WS: "+portAddress);
            cl.araucana.autoconsulta.ws.CertificadoSoapBindingStub _stub = new cl.araucana.autoconsulta.ws.CertificadoSoapBindingStub(portAddress, this);
            _stub.setPortName(getCertificadoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCertificadoEndpointAddress(java.lang.String address) {
        Certificado_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.araucana.autoconsulta.ws.Certificado.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.autoconsulta.ws.CertificadoSoapBindingStub _stub = new cl.araucana.autoconsulta.ws.CertificadoSoapBindingStub(new java.net.URL(Certificado_address), this);
                _stub.setPortName(getCertificadoWSDDServiceName());
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
        if ("Certificado".equals(inputPortName)) {
            return getCertificado();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.araucana.cl", "CertificadoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.araucana.cl", "Certificado"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Certificado".equals(portName)) {
            setCertificadoEndpointAddress(address);
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
