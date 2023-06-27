package cl.araucana.wsatento.integration.jaxrpc.ws;


public interface WSCalidad_SEI extends java.rmi.Remote
{
  public int valCalidad(java.lang.String usuario,java.lang.String clave,java.lang.String rut,int calidad) throws cl.araucana.wsatento.integration.exception.WSAtentoException;
}