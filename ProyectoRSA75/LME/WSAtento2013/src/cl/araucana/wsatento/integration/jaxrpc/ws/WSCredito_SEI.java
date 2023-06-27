package cl.araucana.wsatento.integration.jaxrpc.ws;


public interface WSCredito_SEI extends java.rmi.Remote
{
  public cl.araucana.wsatento.integration.jaxrpc.bean.Credito[] getCreditos(java.lang.String usuario,java.lang.String clave,java.lang.String rut) throws cl.araucana.wsatento.integration.exception.WSAtentoException;
}