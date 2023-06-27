/**
 * 
 */
package conector.lme.ws.cliente.operador;

/**
 * Representa la respuesta a la invocación de un servicio medipass.
 * 
 * @author amartoq@microsystem.cl
 */
public class RespuestaServicio {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Código de retorno; 0 == OK, 1 == ERROR.
     */
    private int estado;

    /**
     * Glosa de retorno.
     */
    private String gloEstado;

    /**
     * Crea una nueva respuesta del servicio.
     * 
     * @param estado
     *            código de retorno.
     * @param gloEstado
     *            glosa de retorno.
     */
    public RespuestaServicio(int estado, String gloEstado) {
        super();
        this.estado = estado;
        this.gloEstado = gloEstado;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @return the gloEstado
     */
    public String getGloEstado() {
        return gloEstado;
    }
}
