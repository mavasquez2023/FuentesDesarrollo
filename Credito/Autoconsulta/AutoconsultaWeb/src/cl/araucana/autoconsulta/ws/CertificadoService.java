/**
 * CertificadoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.autoconsulta.ws;

public interface CertificadoService extends javax.xml.rpc.Service {
    public java.lang.String getCertificadoAddress();

    public cl.araucana.autoconsulta.ws.Certificado getCertificado() throws javax.xml.rpc.ServiceException;

    public cl.araucana.autoconsulta.ws.Certificado getCertificado(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
