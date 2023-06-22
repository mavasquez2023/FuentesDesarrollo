/**
 * EvaluadorCreditoException_DeserProxy.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0445.04 v11904204728
 */

package cse.model.exception;

public class EvaluadorCreditoException_DeserProxy  extends java.lang.Exception  {
    private java.lang.String codigoError;
    private java.lang.String descripcionError;

    public EvaluadorCreditoException_DeserProxy() {
    }

    public java.lang.String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(java.lang.String codigoError) {
        this.codigoError = codigoError;
    }

    public java.lang.String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public java.lang.Object convert() {
      return new cse.model.exception.EvaluadorCreditoException(
        getCodigoError(),
        getDescripcionError());
    }
}
