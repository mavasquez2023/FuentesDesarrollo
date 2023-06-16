/**
 * WSFonaCajasSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public interface WSFonaCajasSoap extends java.rmi.Remote {

    /**
     * Actualizar el estado de las Licencias Formulario que se tramitan
     * entre las CCAF y Fonasa.
     */
    public WSFonaCajasNM.RespActEstLicCCAF actEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, WSFonaCajasNM.EntrActEstLicCCAF[] listaEstLCC) throws java.rmi.RemoteException;

    /**
     * Actualizar el estado de las Licencias Formulario que se tramitan
     * entre las CCAF y Fonasa.
     */
    public WSFonaCajasNM.RespInfEstLicCCAF infEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, WSFonaCajasNM.EntrInfEstLicCCAF[] listaEstLCC) throws java.rmi.RemoteException;

    /**
     * Certificar si el Trabajador esta acreditado por Fonasa para
     * una fecha de Emision dada.
     */
    public WSFonaCajasNM.RespLicCertifTrab licCertifTrab(java.lang.String rutBeneficiario, java.lang.String fecCertifica, java.lang.String rutInstitucion, java.lang.String codigoUsuario, java.lang.String claveUsuario) throws java.rmi.RemoteException;

    /**
     * Entregar los estados asociados a una Licencia Medica Formulario.
     */
    public WSFonaCajasNM.RespVerEstLicCCAF verEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, short tipFormulario, int numFormulario) throws java.rmi.RemoteException;

    /**
     * Entregar los estados asociados a una Licencia Medica Formulario.
     */
    public WSFonaCajasNM.RespConFormLCC conFormuLCC(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, short tipFormulario, int numFormulario) throws java.rmi.RemoteException;
}
