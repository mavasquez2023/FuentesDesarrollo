/**
 * UsuariosLDAPServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.usuariosLDAP;

public class UsuariosLDAPServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAPService {

    public UsuariosLDAPServiceLocator() {
    }


    public UsuariosLDAPServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UsuariosLDAPServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for usuariosLDAPPort
    private java.lang.String usuariosLDAPPort_address = "http://localhost:9083/IntegradorLDAP/usuariosLDAPService";

    public java.lang.String getusuariosLDAPPortAddress() {
        return usuariosLDAPPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String usuariosLDAPPortWSDDServiceName = "usuariosLDAPPort";

    public java.lang.String getusuariosLDAPPortWSDDServiceName() {
        return usuariosLDAPPortWSDDServiceName;
    }

    public void setusuariosLDAPPortWSDDServiceName(java.lang.String name) {
        usuariosLDAPPortWSDDServiceName = name;
    }

    public cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl getusuariosLDAPPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(usuariosLDAPPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getusuariosLDAPPort(endpoint);
    }

    public cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl getusuariosLDAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAPPortBindingStub _stub = new cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAPPortBindingStub(portAddress, this);
            _stub.setPortName(getusuariosLDAPPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setusuariosLDAPPortEndpointAddress(java.lang.String address) {
        usuariosLDAPPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAPPortBindingStub _stub = new cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAPPortBindingStub(new java.net.URL(usuariosLDAPPort_address), this);
                _stub.setPortName(getusuariosLDAPPortWSDDServiceName());
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
        if ("usuariosLDAPPort".equals(inputPortName)) {
            return getusuariosLDAPPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.laaraucana.cl/usuariosLDAP", "usuariosLDAPService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/usuariosLDAP", "usuariosLDAPPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("usuariosLDAPPort".equals(portName)) {
            setusuariosLDAPPortEndpointAddress(address);
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
