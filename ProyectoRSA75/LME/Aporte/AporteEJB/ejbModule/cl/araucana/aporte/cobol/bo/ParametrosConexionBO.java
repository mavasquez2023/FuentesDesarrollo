package cl.araucana.aporte.cobol.bo;

public class ParametrosConexionBO {
    private String ipServer;
    private String usuarioConexion ;
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
}
