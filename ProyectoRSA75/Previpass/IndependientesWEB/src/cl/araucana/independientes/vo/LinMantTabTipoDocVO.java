package cl.araucana.independientes.vo;

public class LinMantTabTipoDocVO {

    /*Declaracion de variables*/
    private String codigo;
    private String nombre;
    private String estado;
    private String obligatorio;
    private String tipoSolicitud;

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }
    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }
    public String getObligatorio() {
        return obligatorio;
    }
    public void setObligatorio(String obligatorio) {
        this.obligatorio = obligatorio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
