package cl.araucana.independientes.vo;

public class EntidadRelacionVO {

    /*Declaraci�n de variables de la clase EntidadRelacionVO*/
    private long idSecuencia;
    private long idEntidad;

    /*Creaci�n de los Getting and Setting de la clase.*/
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
