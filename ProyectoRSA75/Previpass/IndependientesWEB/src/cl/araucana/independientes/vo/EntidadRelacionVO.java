package cl.araucana.independientes.vo;

public class EntidadRelacionVO {

    /*Declaración de variables de la clase EntidadRelacionVO*/
    private long idSecuencia;
    private long idEntidad;

    /*Creación de los Getting and Setting de la clase.*/
    public long getIdEntidad() {
        return idEntidad;
    }
    public void setIdEntidad(long idEntidad) {
        this.idEntidad = idEntidad;
    }
    public long getIdSecuencia() {
        return idSecuencia;
    }
    public void setIdSecuencia(long idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

}
