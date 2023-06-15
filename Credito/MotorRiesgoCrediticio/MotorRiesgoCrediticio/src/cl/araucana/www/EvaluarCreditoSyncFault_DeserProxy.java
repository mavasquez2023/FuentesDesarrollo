/**
 * EvaluarCreditoSyncFault_DeserProxy.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package cl.araucana.www;

public class EvaluarCreditoSyncFault_DeserProxy  extends java.lang.Exception  {
    private java.lang.String mensajeError;

    public EvaluarCreditoSyncFault_DeserProxy() {
    }

    public java.lang.String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(java.lang.String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public java.lang.Object convert() {
        cl.araucana.www.EvaluarCreditoSyncFault e = new cl.araucana.www.EvaluarCreditoSyncFault(
          getMensajeError());

          if (!(getMensajeError().equals(e.getMensajeError()))) {
                 e = new cl.araucana.www.EvaluarCreditoSyncFault(getMessage());
                 e.setMensajeError(getMensajeError());
          }
          return e;
    }
}
