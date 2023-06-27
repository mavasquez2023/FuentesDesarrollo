package cl.araucana.independientes.vo;

public class RepNominaApoAfiVO {
    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaRepNominaApoAfi;
    private int paginaActualRepNominaApoAfi;

    private String nombreAfiliado;
    private String apellidoPaternoAfiliado;
    private String apellidoMaternoAfiliado;
    private String estadoAfiliado;
    private String oficinaAfiliacion;
    private LinRepNominaApoAfiVO2 repNominaApoAfi[];

    public String getArchivoInforme() {
        return archivoInforme;
    }
    public void setArchivoInforme(String archivoInforme) {
        this.archivoInforme = archivoInforme;
    }
    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

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
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public int getPaginaActualRepNominaApoAfi() {
        return paginaActualRepNominaApoAfi;
    }
    public void setPaginaActualRepNominaApoAfi(int paginaActualRepNominaApoAfi) {
        this.paginaActualRepNominaApoAfi = paginaActualRepNominaApoAfi;
    }
    public int[] getPaginaRepNominaApoAfi() {
        return paginaRepNominaApoAfi;
    }
    public void setPaginaRepNominaApoAfi(int[] paginaRepNominaApoAfi) {
        this.paginaRepNominaApoAfi = paginaRepNominaApoAfi;
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
