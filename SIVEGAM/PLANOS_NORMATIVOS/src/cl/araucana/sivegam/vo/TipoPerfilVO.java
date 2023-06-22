package cl.araucana.sivegam.vo;

public class TipoPerfilVO {

    /* variables de la clase */
    private long tipo_perfil;
    private String descripcion_tipo_perfil;
    private int flag_funcionalidad_1;
    private int flag_funcionalidad_2;
    private int flag_funcionalidad_3;
    private long flag_funcionalidad_4;

    /* Generacion de get and set */
    public String getDescripcion_tipo_perfil() {
        return descripcion_tipo_perfil;
    }

    public void setDescripcion_tipo_perfil(String descripcion_tipo_perfil) {
        this.descripcion_tipo_perfil = descripcion_tipo_perfil;
    }

    public int getFlag_funcionalidad_1() {
        return flag_funcionalidad_1;
    }

    public void setFlag_funcionalidad_1(int flag_funcionalidad_1) {
        this.flag_funcionalidad_1 = flag_funcionalidad_1;
    }

    public int getFlag_funcionalidad_2() {
        return flag_funcionalidad_2;
    }

    public void setFlag_funcionalidad_2(int flag_funcionalidad_2) {
        this.flag_funcionalidad_2 = flag_funcionalidad_2;
    }

    public int getFlag_funcionalidad_3() {
        return flag_funcionalidad_3;
    }

    public void setFlag_funcionalidad_3(int flag_funcionalidad_3) {
        this.flag_funcionalidad_3 = flag_funcionalidad_3;
    }

    public long getFlag_funcionalidad_4() {
        return flag_funcionalidad_4;
    }

    public void setFlag_funcionalidad_4(long flag_funcionalidad_4) {
        this.flag_funcionalidad_4 = flag_funcionalidad_4;
    }

    public long getTipo_perfil() {
        return tipo_perfil;
    }

    public void setTipo_perfil(long tipo_perfil) {
        this.tipo_perfil = tipo_perfil;
    }

}
