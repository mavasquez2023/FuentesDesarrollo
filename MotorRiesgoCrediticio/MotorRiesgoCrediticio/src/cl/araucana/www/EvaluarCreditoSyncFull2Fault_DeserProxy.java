/**
 * EvaluarCreditoSyncFull2Fault_DeserProxy.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0445.04 v11904204728
 */

package cl.araucana.www;

public class EvaluarCreditoSyncFull2Fault_DeserProxy  extends java.lang.Exception  {
    private java.lang.String mensajeError;

    public EvaluarCreditoSyncFull2Fault_DeserProxy() {
    }

    public java.lang.String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(java.lang.String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public java.lang.Object convert() {
      return new cl.araucana.www.EvaluarCreditoSyncFull2Fault(
        getMensajeError());
    }
}
