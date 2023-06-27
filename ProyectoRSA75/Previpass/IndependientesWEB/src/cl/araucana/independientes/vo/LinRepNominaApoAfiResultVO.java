package cl.araucana.independientes.vo;

public class LinRepNominaApoAfiResultVO {

    private String nombreAfiliado;
    private String apellidoPaternoAfiliado;
    private String apellidoMaternoAfiliado;
    private String estadoAfiliado;
    private String oficinaAfiliacion;
    private LinRepNominaApoAfiVO2 repNominaApoAfi[];

    public String getEstadoAfiliado() {
        return estadoAfiliado;
    }
    public void setEstadoAfiliado(String estadoAfiliado) {
        this.estadoAfiliado = estadoAfiliado;
    }
    public String getNombreAfiliado() {
        return nombreAfiliado;
    }
    public void setNombreAfiliado(String nombreAfiliado) {
        this.nombreAfiliado = nombreAfiliado;
    }
    public LinRepNominaApoAfiVO2[] getRepNominaApoAfi() {
        return repNominaApoAfi;
    }
    public void setRepNominaApoAfi(LinRepNominaApoAfiVO2[] repNominaApoAfi) {
        this.repNominaApoAfi = repNominaApoAfi;
    }
    public String getApellidoMaternoAfiliado() {
        return apellidoMaternoAfiliado;
    }
    public void setApellidoMaternoAfiliado(String apellidoMaternoAfiliado) {
        this.apellidoMaternoAfiliado = apellidoMaternoAfiliado;
    }
    public String getApellidoPaternoAfiliado() {
        return apellidoPaternoAfiliado;
    }
    public void setApellidoPaternoAfiliado(String apellidoPaternoAfiliado) {
        this.apellidoPaternoAfiliado = apellidoPaternoAfiliado;
    }
    public String getOficinaAfiliacion() {
        return oficinaAfiliacion;
    }
    public void setOficinaAfiliacion(String oficinaAfiliacion) {
        this.oficinaAfiliacion = oficinaAfiliacion;
    }	
}
