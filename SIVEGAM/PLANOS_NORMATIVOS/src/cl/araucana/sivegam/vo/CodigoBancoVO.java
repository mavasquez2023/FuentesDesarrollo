package cl.araucana.sivegam.vo;

public class CodigoBancoVO {

    /* variables de la clase. */
    private int id_codigo_banco;
    private String desc_codigo_banco;
    private int cod_banco_normativa;
    private String desc_cod_banco_normativa;

    /* generacion de get and set */
    public int getCod_banco_normativa() {
        return cod_banco_normativa;
    }

    public void setCod_banco_normativa(int cod_banco_normativa) {
        this.cod_banco_normativa = cod_banco_normativa;
    }

    public String getDesc_cod_banco_normativa() {
        return desc_cod_banco_normativa;
    }

    public void setDesc_cod_banco_normativa(String desc_cod_banco_normativa) {
        this.desc_cod_banco_normativa = desc_cod_banco_normativa;
    }

    public String getDesc_codigo_banco() {
        return desc_codigo_banco;
    }

    public void setDesc_codigo_banco(String desc_codigo_banco) {
        this.desc_codigo_banco = desc_codigo_banco;
    }

    public int getId_codigo_banco() {
        return id_codigo_banco;
    }

    public void setId_codigo_banco(int id_codigo_banco) {
        this.id_codigo_banco = id_codigo_banco;
    }
}
