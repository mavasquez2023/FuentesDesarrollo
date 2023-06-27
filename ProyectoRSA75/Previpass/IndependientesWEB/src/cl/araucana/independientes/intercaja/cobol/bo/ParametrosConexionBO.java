package cl.araucana.independientes.intercaja.cobol.bo;

public class ParametrosConexionBO {

    /* Declaracion de variables de la clase.
     * ipServer: direccion IP de la máquina que contiene AS400.
     * usuarioConexion: nombre del usuario con permisos para iniciar una conexion en la maquina AS400.
     * claveConexion: clave del usuario para iniciar la conexion a la maquina AS400.
     * programa: programa compilado en la máquina AS400 y que será llamado por el sistema independientes.
     * */	

    private String ipServer;
    private String usuarioConexion ;
    private String claveConexion;
    private String programa;


    /*Declaracion de get and set*/
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
