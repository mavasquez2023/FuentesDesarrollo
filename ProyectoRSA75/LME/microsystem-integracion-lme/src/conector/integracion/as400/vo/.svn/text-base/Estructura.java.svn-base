/**
 * 
 */
package conector.integracion.as400.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa una estructura COBOL.
 * 
 * @author amartoq@microsystem.cl
 */
public class Estructura {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Número de filas con la misma estructura.
     */
    private int filas = 0;

    /**
     * Largo de caracteres total de esta estructura.
     */
    private int largo = 0;

    /**
     * Listado de parámetros.
     */
    private List parametros = new ArrayList();

    /**
     * Lista de valores para cada fila.
     */
    private List valores = new ArrayList();

    /**
     * Crea una nueva estructura con una única fila.
     */
    public Estructura() {
        this(1);
    }

    /**
     * Crea una nueva estructura con la cantidad de filas especificadas.
     * 
     * @param filas
     *            número de filas de la estructura.
     */
    public Estructura(int filas) {
        this.filas = filas;
        for (int i = 0; i < filas; i++) {
            valores.add(new HashMap());
        }
    }

    /**
     * Agrega un parámetro y copia el valor por omisión a todas las filas.
     * 
     * @param parametro
     *            objeto parámetro con el valor por omisión.
     */
    private void agregarParametro(Parametro parametro) {
        parametros.add(parametro);
        for (int i = 0; i < filas; i++) {
            Map valorMap = (Map) valores.get(i);
            valorMap.put(parametro.nombre, parametro.valor);
        }
        largo += filas * parametro.largo;
    }

    /**
     * Agrega un parámetro numérico de entrada a esta estructura.
     * 
     * @param nombre
     *            nombre del parámetro.
     * @param largo
     *            largo en caracteres del parámetro.
     * @param valor
     *            valor de entrada del parámetro.
     */
    public void paramEntradaNum(String nombre, int largo, long valor) {
        Parametro parametro = new Parametro(nombre, largo, new Long(valor), Parametro.TIPO_NUM, false);
        agregarParametro(parametro);
    }

    /**
     * Agrega un parámetro de caracteres de entrada a esta estructura.
     * 
     * @param nombre
     *            nombre del parámetro.
     * @param largo
     *            largo en caracteres del parámetro.
     * @param valor
     *            valor de entrada del parámetro.
     */
    public void paramEntradaChr(String nombre, int largo, String valor) {
        Parametro parametro = new Parametro(nombre, largo, new String(valor), Parametro.TIPO_CHR, false);
        agregarParametro(parametro);
    }

    /**
     * Agrega una subestructura a esta estructura.
     * 
     * @param nombre
     *            nombre de la subestructura
     * @param estructura
     *            estructura a agregar.
     */
    public void paramEstructura(String nombre, Estructura estructura) {
        Parametro parametro = new Parametro(nombre, estructura.largo, estructura, Parametro.TIPO_STR, true);
        agregarParametro(parametro);
    }

    /**
     * Agrega un parámetro numérico de salida a esta estructura.
     * 
     * @param nombre
     *            nombre del parámetro.
     * @param largo
     *            largo en caracteres del parámetro.
     */
    public void paramSalidaNum(String nombre, int largo) {
        Parametro parametro = new Parametro(nombre, largo, new Long(0), Parametro.TIPO_NUM, true);
        agregarParametro(parametro);
    }

    /**
     * Agrega un parámetro de caracteres de salida a esta estructura.
     * 
     * @param nombre
     *            nombre del parámetro.
     * @param largo
     *            largo en caracteres del parámetro.
     */
    public void paramSalidaChr(String nombre, int largo) {
        Parametro parametro = new Parametro(nombre, largo, new String(), Parametro.TIPO_CHR, true);
        agregarParametro(parametro);
    }

    /**
     * Retorna el valor numérico de un parámetro para la fila especificada.
     * 
     * @param fila
     *            número de fila.
     * @param nombre
     *            nombre del parámetro.
     * @return el valor del parámetro en la fila.
     */
    public long valorNum(int fila, String nombre) {
        Map valorMap = (Map) valores.get(fila);
        Long valor = (Long) valorMap.get(nombre);
        return valor.intValue();
    }

    /**
     * Retorna el valor numérico de un parámetro para la primera fila.
     * 
     * @param nombre
     *            nombre del parámetro.
     * @return el valor del parámetro en la primera fila.
     */
    public long valorNum(String nombre) {
        return valorNum(0, nombre);
    }

    /**
     * Retorna el valor de caracteres de un parámetro para la fila especificada.
     * 
     * @param fila
     *            número de fila.
     * @param nombre
     *            nombre del parámetro.
     * @return el valor del parámetro en la fila.
     */
    public String valorChr(int fila, String nombre) {
        Map valorMap = (Map) valores.get(fila);
        String valor = (String) valorMap.get(nombre);
        return valor;
    }

    /**
     * Retorna el valor de caracteres de un parámetro para la primera fila.
     * 
     * @param nombre
     *            nombre del parámetro.
     * @return el valor del parámetro en la primera fila.
     */
    public String valorChr(String nombre) {
        return valorChr(0, nombre).replace((char)0, ' ');
    }

    /**
     * Retorna la subestructura en la fila especificada.
     * 
     * @param fila
     *            número de fila.
     * @param nombre
     *            nombre de la subestructura.
     * @return la subestructura en la fila.
     */
    public Estructura getStruct(int fila, String nombre) {
        Map valorMap = (Map) valores.get(fila);
        Estructura valor = (Estructura) valorMap.get(nombre);
        return valor;
    }

    /**
     * Retorna la subestructura para la primera fila.
     * 
     * @param nombre
     *            nombre de la subestructura.
     * @return la subestructura en la primera fila.
     */
    public Estructura getEstructura(String nombre) {
        return getStruct(0, nombre);
    }

    /**
     * Retorna esta estructura como un string largo posición.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < parametros.size(); j++) {
                Parametro parametro = (Parametro) parametros.get(j);
                String data = getBufferParametro(i, parametro);
                buffer.append(data);
            }
        }
        return buffer.toString();
    }

    /**
     * Analiza el string en formato largo posición y almacena los datos en la
     * estructura.
     * 
     * @param string
     *            string con los datos en formato largo posición.
     */
    public void parseString(String string) {
        StringBuffer buffer = new StringBuffer(string);
        for (int i = 0; i < filas; i++) {
            Map valorMap = (Map) valores.get(i);
            for (int j = 0; j < parametros.size(); j++) {
                Parametro parametro = (Parametro) parametros.get(j);
                int len = Math.min(buffer.length(), parametro.largo);
                String data = "";
                if (len != 0) {
                    data = buffer.substring(0, len);
                    buffer.delete(0, len);
                }
                if (parametro.tipo == Parametro.TIPO_NUM) {
                    String valor = quitaIzq(data, '0').trim();
                    valor = valor.trim().length() == 0 ? "0" : valor;
                    valorMap.put(parametro.nombre, new Long(valor));
                } else if (parametro.tipo == Parametro.TIPO_CHR) {
                    String valor = quitaDer(data, ' ');
                    valorMap.put(parametro.nombre, new String(valor));
                } else if (parametro.tipo == Parametro.TIPO_STR) {
                    Estructura e = (Estructura) valorMap.get(parametro.nombre);
                    e.parseString(data);
                }
            }
        }
    }

    /**
     * Analiza el string en formato largo posición y almacena los datos en el
     * parámetro especificado.
     * 
     * @param fila
     *            número de fila.
     * @param parametro
     *            parámetro correspondiente.
     * @param data
     *            string en formato largo posición con los datos.
     */
    public void setBufferParametro(int fila, Parametro parametro, String data) {
        Map valorMap = (Map) valores.get(fila);
        if (data == null) {
            data = "";
        }
        if (parametro.tipo == Parametro.TIPO_NUM) {
            String valor = quitaIzq(data, '0');
            valor = valor.length() == 0 ? "0" : valor;
            valorMap.put(parametro.nombre, new Long(valor));
        } else if (parametro.tipo == Parametro.TIPO_CHR) {
            String valor = quitaDer(data, ' ');
            valorMap.put(parametro.nombre, new String(valor));
        } else if (parametro.tipo == Parametro.TIPO_STR) {
            Estructura e = (Estructura) valorMap.get(parametro.nombre);
            e.parseString(data);
        }
    }

    /**
     * Retorna un string largo posición para el parámetro y fila especificados.
     * 
     * @param fila
     *            número de fila.
     * @param parametro
     *            parámetro correspondiente.
     * @return un string largo posición con el valor del parámetro.
     */
    public String getBufferParametro(int fila, Parametro parametro) {
        Map valorMap = (Map) valores.get(fila);
        String valor = valorMap.get(parametro.nombre).toString();
        String data = null;
        if (parametro.tipo == Parametro.TIPO_NUM) {
            data = rellenaIzq(valor, '0', parametro.largo);
        } else if (parametro.tipo == Parametro.TIPO_CHR) {
            data = rellenaDer(valor, ' ', parametro.largo);
        } else if (parametro.tipo == Parametro.TIPO_STR) {
            data = valor;
        }
        return data;
    }

    /**
     * Rellena a la izquierda con el caracter especificado.
     * 
     * @param original
     *            string original.
     * @param fill
     *            caracter de relleno.
     * @param largo
     *            largo máximo del string.
     * @return un nuevo string rellenado a la izquierda.
     */
    protected String rellenaIzq(String original, char fill, int largo) {
        StringBuffer buffer = new StringBuffer(original);
        while (buffer.length() < largo) {
            buffer.insert(0, fill);
        }
        return buffer.toString();
    }

    /**
     * Rellena a la derecha con el caracter especificado.
     * 
     * @param original
     *            string original.
     * @param fill
     *            caracter de relleno.
     * @param largo
     *            largo máximo del string.
     * @return un nuevo string rellenado a la derecha.
     */
    protected String rellenaDer(String original, char fill, int largo) {
        StringBuffer buffer = new StringBuffer(original);
        while (buffer.length() < largo) {
            buffer.append(fill);
        }
        return buffer.toString();
    }

    /**
     * Elimina caracteres a la izquierda.
     * 
     * @param original
     *            string original.
     * @param fill
     *            caracter de relleno a eliminar.
     * @return un nuevo string sin el caracter de relleno a la izquierda.
     */
    protected String quitaIzq(String original, char fill) {
        StringBuffer buffer = new StringBuffer(original);
        while (buffer.length() > 0 && buffer.charAt(0) == fill) {
            buffer.deleteCharAt(0);
        }
        return buffer.toString();
    }

    /**
     * Elimina caracteres a la derecha.
     * 
     * @param original
     *            string original.
     * @param fill
     *            caracter de relleno a eliminar.
     * @return un nuevo string sin el caracter de relleno a la derecha.
     */
    protected String quitaDer(String original, char fill) {
        StringBuffer buffer = new StringBuffer(original);
        while (buffer.length() > 0 && buffer.charAt(buffer.length() - 1) == fill) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }

    /**
     * Retorna la cantidad de filas de esta estructura.
     * 
     * @return número de filas de la estructura.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Retorna la cantidad de parámetros definidos en esta estructura.
     * 
     * @return número de parámetros de la estructura.
     */
    public int getNumeroParametros() {
        return parametros.size();
    }

    /**
     * Retorna la cantidad de caracteres totales requeridos para representar
     * esta estructura en formato largo posición.
     * 
     * @return largo de caracteres totales.
     */
    public int getLargo() {
        return largo;
    }

    /**
     * Retorna el parámetro i-ésimo.
     * 
     * @param index
     *            índice del parámetro.
     * @return el parámetro i-ésimo.
     */
    public Parametro getParametro(int index) {
        return (Parametro) parametros.get(index);
    }
}
