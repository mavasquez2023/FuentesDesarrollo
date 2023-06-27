package cl.araucana.cp.services;


public interface InServiceInd_SEI extends java.rmi.Remote
{
  public cl.araucana.cp.services.vo.ErrorResultVO creaIndependiente(int rut,java.lang.String dv,java.lang.String apellidoPaterno,java.lang.String apellidoMaterno,java.lang.String nombres,int genero,int codigoActividadEconomica,java.lang.String tipoDireccion,java.lang.String direccion,java.lang.String numero,java.lang.String dpto,int idComuna,java.lang.String email,java.lang.String telefono,java.lang.String fax,java.lang.String celular);
}