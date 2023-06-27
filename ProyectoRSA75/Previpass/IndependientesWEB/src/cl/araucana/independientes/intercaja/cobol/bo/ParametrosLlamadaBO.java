package cl.araucana.independientes.intercaja.cobol.bo;

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

    public void setTextoAS400(AS400Text textoAS400) {
        this.textoAS400 = textoAS400;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
