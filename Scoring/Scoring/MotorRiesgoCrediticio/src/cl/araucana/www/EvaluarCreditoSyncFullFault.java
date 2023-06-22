/**
 * EvaluarCreditoSyncFullFault.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0445.04 v11904204728
 */

package cl.araucana.www;

public class EvaluarCreditoSyncFullFault  extends java.lang.Exception  {
    private java.lang.String mensajeError;

    public EvaluarCreditoSyncFullFault(
           java.lang.String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public java.lang.String getMensajeError() {
        return mensajeError;
    }

}
