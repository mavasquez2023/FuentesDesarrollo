package cl.laaraucana.capaservicios.database.vo;

public class SolicitudSMS {
  private String idSolicitud;
  private String codSeguridad;
  private String fecha;
  private String hora;
  private String estado;
  private String rutCliente;
  private String dvRut;
  private String montoAprobado;
  private String origen;
  private int intentos;

  public String getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(String idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  public String getRutCliente() {
    return rutCliente;
  }

  public void setRutCliente(String rutCliente) {
    this.rutCliente = rutCliente;
  }

  public String getCodSeguridad() {
    return codSeguridad;
  }

  public void setCodSeguridad(String codSeguridad) {
    this.codSeguridad = codSeguridad;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getDvRut() {
    return dvRut;
  }

  public void setDvRut(String dvRut) {
    this.dvRut = dvRut;
  }

  public String getMontoAprobado() {
    return montoAprobado;
  }

  public void setMontoAprobado(String montoAprobado) {
    this.montoAprobado = montoAprobado;
  }

  public String getOrigen() {
    return origen;
  }

  public void setOrigen(String origen) {
    this.origen = origen;
  }

  public int getIntentos() {
    return intentos;
  }

  public void setIntentos(int intentos) {
    this.intentos = intentos;
  }

}
