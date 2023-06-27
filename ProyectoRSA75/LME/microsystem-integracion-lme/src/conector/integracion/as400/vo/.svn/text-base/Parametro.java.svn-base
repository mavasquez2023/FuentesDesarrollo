/**
 * 
 */
package conector.integracion.as400.vo;

import java.io.Serializable;

/**
 * Representa un parámetro de entrada y/o salida al invocar un programa AS400.
 * 
 * @author amartoq@microsystem.cl
 */
public class Parametro implements Serializable {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Parámetro de tipo numérico, con ceros a la izquierda.
     */
    public static final int TIPO_NUM = 1;

    /**
     * Parámetro de tipo caracter, con espacios a la derecha.
     */
    public static final int TIPO_CHR = 2;

    /**
     * Parámetro de tipo estructura.
     */
    public static final int TIPO_STR = 3;

    /**
     * Nombre del parámetro.
     */
    public String nombre;

    /**
     * Largo en caracteres del parámetro.
     */
    public int largo;

    /**
     * Valor por omisión del parámetro.
     */
    public Object valor;

    /**
     * Tipo del parámetro (NUM, CHR ó STR).
     */
    public int tipo;

    /**
     * Indica si este parámetro es exclusivamente de salida.
     */
    public boolean isSalida;

    /**
     * Crea un nuevo parámetro.
     * 
     * @param nombre
     *            nombre del parámetro.
     * @param largo
     *            largo en caracteres del parámetro.
     * @param valor
     *            valor por omisión del parámetro.
     * @param tipo
     *            tipo de parámetro.
     * @param isSalida
     *            indica si el parámetro es exclusivamente de salida.
     */
    public Parametro(String nombre, int largo, Object valor, int tipo, boolean isSalida) {
        super();
        this.nombre = nombre;
        this.largo = largo;
        this.valor = valor;
        this.tipo = tipo;
        this.isSalida = isSalida;
    }
}
