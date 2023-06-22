/**
 * PlatinumImplService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService;

public interface PlatinumImplService extends javax.xml.rpc.Service {
    public java.lang.String getPlatinumServiceAddress();

    public cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl getPlatinumService() throws javax.xml.rpc.ServiceException;

    public cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl getPlatinumService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
