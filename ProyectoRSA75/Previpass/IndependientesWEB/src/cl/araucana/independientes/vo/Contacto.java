package cl.araucana.independientes.vo;

public class Contacto {

    /*Declaracion de variables de la clase Contacto*/
    private long nroSecuencia;
    private int tipo;
    private int estado;
    private String fechaVerificacion;
    private String fechaBaja;
    private int principalidad;

    /*Creación de los Getting and Setting de la clase.*/
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getFechaBaja() {
        return fechaBaja;
    }
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    public String getFechaVerificacion() {
        return fechaVerificacion;
    }
    public void setFechaVerificacion(String fechaVerificacion) {
        this.fechaVerificacion = fechaVerificacion;
    }
    public long getNroSecuencia() {
        return nroSecuencia;
    }
    public void setNroSecuencia(long nroSecuencia) {
        this.nroSecuencia = nroSecuencia;
    }
    public int getPrincipalidad() {
        return principalidad;
    }
    public void setPrincipalidad(int principalidad) {
        this.principalidad = principalidad;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
