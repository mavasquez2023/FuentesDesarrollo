package cl.araucana.independientes.vo;

public class LinRepNominaSolDesafAfiVO {

    /*Declaracion de variables*/
    private String fechaIngreso;
    private String folio;
    private String rut;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private String analista;
    private String lugarAfiliacion;
    private String estadoSolicitud;

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }
    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getFolio() {
        return folio;
    }
    public void setFolio(String folio) {
        this.folio = folio;
    }
    public String getLugarAfiliacion() {
        return lugarAfiliacion;
    }
    public void setLugarAfiliacion(String lugarAfiliacion) {
        this.lugarAfiliacion = lugarAfiliacion;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getAnalista() {
        return analista;
    }
    public void setAnalista(String analista) {
        this.analista = analista;
    }

}
