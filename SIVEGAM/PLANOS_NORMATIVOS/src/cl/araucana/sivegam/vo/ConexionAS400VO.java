package cl.araucana.sivegam.vo;

public class ConexionAS400VO {

    private String ipServer;
    private String usuarioConexion;
    private String claveConexion;
    private String nombrePrograma;

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

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

    public String getUsuarioConexion() {
        return usuarioConexion;
    }

    public void setUsuarioConexion(String usuarioConexion) {
        this.usuarioConexion = usuarioConexion;
    }

}
