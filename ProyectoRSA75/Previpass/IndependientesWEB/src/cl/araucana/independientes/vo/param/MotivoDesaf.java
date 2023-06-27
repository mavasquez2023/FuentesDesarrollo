package cl.araucana.independientes.vo.param;

/*Clase Geográfico.
 * Contiene las variables que contandrán la informacion de los datos de los motivos de desafiliacion usados en el sistema.
 * */
public class MotivoDesaf {

    /*Declaración de variables de la clase MotivoDesaf.*/
    private int codigo = 0;
    private String glosa = null;
    private int idMotivo;
    private String desMotivo;
    private int idDescMotivo;
    private String desDescMotivo;

    /*Creación de Getting and Setting de clase MotivoDesaf.*/

    public String getDesDescMotivo() {
        return desDescMotivo;
    }
    public void setDesDescMotivo(String desDescMotivo) {
        this.desDescMotivo = desDescMotivo;
    }
    public String getDesMotivo() {
        return desMotivo;
    }
    public void setDesMotivo(String desMotivo) {
        this.desMotivo = desMotivo;
    }
    public int getIdDescMotivo() {
        return idDescMotivo;
    }
    public void setIdDescMotivo(int idDescMotivo) {
        this.idDescMotivo = idDescMotivo;
    }
    public int getIdMotivo() {
        return idMotivo;
    }
    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getGlosa() {
        return glosa;
    }
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }


}
