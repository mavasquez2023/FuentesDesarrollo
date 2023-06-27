/**
 * ConsultaMailService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.wsmail.test;

public interface ConsultaMailService extends javax.xml.rpc.Service {
    public java.lang.String getConsultaMailAddress();

    public cl.araucana.wsmail.test.ConsultaMail getConsultaMail() throws javax.xml.rpc.ServiceException;

    public cl.araucana.wsmail.test.ConsultaMail getConsultaMail(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
