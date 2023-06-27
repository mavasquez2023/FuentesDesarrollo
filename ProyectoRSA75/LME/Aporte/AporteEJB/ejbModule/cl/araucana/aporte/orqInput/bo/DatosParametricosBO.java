package cl.araucana.aporte.orqInput.bo;

public class DatosParametricosBO {
    private int entrada;
    private int control;
    private int salida;
    private int error;
    private String glserror;
    private String ipServer;
    private String usuarioConexion;
    private String claveConexion;
    private String programa;

    public String getClaveConexion() {
        return claveConexion;
    }
    public void setClaveConexion(String claveConexion) {
        this.claveConexion = claveConexion;
    }
    public String getIpServer() {
        return ipServer;
    }
    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }
    public String getPrograma() {
        return programa;
    }
    public void setPrograma(String programa) {
        this.programa = programa;
    }
    public String getUsuarioConexion() {
        return usuarioConexion;
    }
    public void setUsuarioConexion(String usuarioConexion) {
        this.usuarioConexion = usuarioConexion;
    }
    public int getControl() {
        return control;
    }
    public void setControl(int control) {
        this.control = control;
    }
    public int getEntrada() {
        return entrada;
    }
    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }
    public int getError() {
        return error;
    }
    public void setError(int error) {
        this.error = error;
    }
    public int getSalida() {
        return salida;
    }
    public void setSalida(int salida) {
        this.salida = salida;
    }
    public String getGlserror() {
        return glserror;
    }
    public void setGlserror(String glserror) {
        this.glserror = glserror;
    }
}
