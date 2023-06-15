package cl.araucana.sivegam.vo;

public class UsuariosSivegamVO {

    /* variables de la clase */
    private long usuario_sivegam;
    private String descripcion_tipo_perfil;

    /* generacion de get and set */
    public String getDescripcion_tipo_perfil() {
        return descripcion_tipo_perfil;
    }

    public void setDescripcion_tipo_perfil(String descripcion_tipo_perfil) {
        this.descripcion_tipo_perfil = descripcion_tipo_perfil;
    }

    public long getUsuario_sivegam() {
        return usuario_sivegam;
    }

    public void setUsuario_sivegam(long usuario_sivegam) {
        this.usuario_sivegam = usuario_sivegam;
    }

}
