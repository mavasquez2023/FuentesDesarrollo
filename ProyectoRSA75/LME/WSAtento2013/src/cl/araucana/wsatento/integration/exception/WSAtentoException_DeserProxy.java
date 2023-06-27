/**
 * WSAtentoException_DeserProxy.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf10631.06 v81706232132
 */

package cl.araucana.wsatento.integration.exception;

public class WSAtentoException_DeserProxy  extends java.lang.Exception  {
    private java.lang.String codigo;
    private java.lang.String mensaje;

    public WSAtentoException_DeserProxy() {
    }

    public java.lang.String getCodigo() {
        return codigo;
    }

    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }

    public java.lang.String getMensaje() {
        return mensaje;
    }

    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

    public java.lang.Object convert() {
      cl.araucana.wsatento.integration.exception.WSAtentoException _e;
      _e = new cl.araucana.wsatento.integration.exception.WSAtentoException(
        getCodigo(),
        getMensaje());
      if (!((getCodigo()== null && getCodigo()== _e.getCodigo()) || (getCodigo()!= null && getCodigo().equals(_e.getCodigo()))) || 
!((getMensaje()== null && getMensaje()== _e.getMensaje()) || (getMensaje()!= null && getMensaje().equals(_e.getMensaje())))      ) {
        _e = new cl.araucana.wsatento.integration.exception.WSAtentoException();
        _e.setCodigo(getCodigo());
        _e.setMensaje(getMensaje());
      }
      return _e;
    }
}
