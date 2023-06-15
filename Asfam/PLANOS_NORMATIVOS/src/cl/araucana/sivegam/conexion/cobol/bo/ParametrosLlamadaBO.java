package cl.araucana.sivegam.conexion.cobol.bo;

import com.ibm.as400.access.AS400Text;

public class ParametrosLlamadaBO {

    private Object valor;
    private String tipo;
    private int largo;
    private String direccion;
    private AS400Text textoAS400;

    public AS400Text getTextoAS400() {
        return textoAS400;
    }

    public void setTextoAS400(AS400Text textoAS400In) {
        this.textoAS400 = textoAS400In;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccionIn) {
        this.direccion = direccionIn;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largoIn) {
        this.largo = largoIn;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipoIn) {
        this.tipo = tipoIn;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valorIn) {
        this.valor = valorIn;
    }
}
